package nurdanemin.maintenanceservice.api.clients;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.utils.dto.ClientResponse;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class CarClientForMaintenanceFalllBack implements CarClientForMaintenance {
    @Override
    @Retry(name = "inventory-service")
    public ClientResponse checkIfCarAvailable(UUID carId) {
        log.info("INVENTORY_SERVICE_IS_DOWN");
        throw new BusinessException(("INVENTORY_SERVICE_IS_NOT_AVAILABLE_NOW"));
    }
}
