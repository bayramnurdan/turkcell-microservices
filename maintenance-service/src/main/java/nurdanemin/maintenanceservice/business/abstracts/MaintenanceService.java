package nurdanemin.maintenanceservice.business.abstracts;

import nurdanemin.maintenanceservice.business.dto.request.CreateMaintenanceRequest;
import nurdanemin.maintenanceservice.business.dto.response.create.CreateMaintenanceResponse;
import nurdanemin.maintenanceservice.business.dto.response.get.GetAllMaintenancesResponse;
import nurdanemin.maintenanceservice.business.dto.response.get.GetMaintenanceResponse;

import java.util.List;
import java.util.UUID;

public interface MaintenanceService {
    List<GetAllMaintenancesResponse> getAll();

    GetMaintenanceResponse getById(UUID id);

    void returnCarFromMaintenance(UUID carId); //TODO :Tekrar düşün

    CreateMaintenanceResponse add(CreateMaintenanceRequest request);


}
