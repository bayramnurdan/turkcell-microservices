package nurdanemin.maintenanceservice.business.rules;

import lombok.RequiredArgsConstructor;
import nurdanemin.commonpackage.utils.dto.ClientResponse;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import nurdanemin.maintenanceservice.api.clients.CarClientForMaintenance;
import nurdanemin.maintenanceservice.repository.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MaintenanceRules {
    private final MaintenanceRepository repository;
    private final CarClientForMaintenance carClientForMaintenance;


    public void checkIfMaintenanceExistsByCarId(UUID carId) {
        if (!repository.existsByCarId(carId)) {
            throw new BusinessException("MAINTENANCE_FOR_CAR_NOT_EXISTS");
        }
    }

    public void checkIfMaintenanceExists(UUID id) {
        if (!repository.existsByCarId(id)) {
            throw new BusinessException("MAINTENANCE_NOT_EXISTS");
        }
    }


    public void ensureCarIsAvailable(UUID carId) {
        ClientResponse response = carClientForMaintenance.checkIfCarAvailable(carId);
        if (!response.isSuccess()) {
            throw new BusinessException(response.getMessage());
        }
    }
}
