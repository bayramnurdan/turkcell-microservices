package nurdanemin.maintenanceservice.business.concretes;

import lombok.RequiredArgsConstructor;
import nurdanemin.commonpackage.events.maintenance.CreateMaintenanceEvent;
import nurdanemin.commonpackage.events.maintenance.ReturnMaintenanceEvent;
import nurdanemin.commonpackage.kafka.producer.KafkaProducer;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.maintenanceservice.business.abstracts.MaintenanceService;
import nurdanemin.maintenanceservice.business.dto.request.CreateMaintenanceRequest;
import nurdanemin.maintenanceservice.business.dto.response.create.CreateMaintenanceResponse;
import nurdanemin.maintenanceservice.business.dto.response.get.GetAllMaintenancesResponse;
import nurdanemin.maintenanceservice.business.dto.response.get.GetMaintenanceResponse;
import nurdanemin.maintenanceservice.business.rules.MaintenanceRules;
import nurdanemin.maintenanceservice.entities.Maintenance;
import nurdanemin.maintenanceservice.repository.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository repository;
    private final ModelMapperService mapper;
    private final KafkaProducer producer;
    private final MaintenanceRules rules;


    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        var maintenances = repository.findAll();
        var response = maintenances
                .stream()
                .map(maintenance -> mapper.forResponse().map(maintenance, GetAllMaintenancesResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetMaintenanceResponse getById(UUID id) {
        rules.checkIfMaintenanceExists(id);
        return mapper.forResponse().map(repository.findById(id).orElseThrow(), GetMaintenanceResponse.class);
    }

    @Override
    public void returnCarFromMaintenance(UUID carId) {
        rules.checkIfActiveMaintenanceExists(carId);
        var maintenance = repository.findByCarId(carId);
        maintenance.setCompleted(true);
        maintenance.setEndDate(LocalDateTime.now());
        repository.save(maintenance);
        producer.sendMessage(new ReturnMaintenanceEvent(carId), "return-maintenance");
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        rules.ensureCarIsAvailable(request.getCarId());
        var maintenance = mapper.forRequest().map(request, Maintenance.class);
        maintenance.setId(UUID.randomUUID());
        maintenance.setStartDate(LocalDateTime.now());
        maintenance.setCompleted(false);
        maintenance.setEndDate(null);
        var createdMaintenance = repository.save(maintenance);
        producer.sendMessage(new CreateMaintenanceEvent(request.getCarId()), "maintenance-created");
        return mapper.forResponse().map(createdMaintenance, CreateMaintenanceResponse.class);

    }


}
