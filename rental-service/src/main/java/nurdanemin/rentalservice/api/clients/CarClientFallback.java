package nurdanemin.rentalservice.api.clients;

import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.utils.dto.ClientResponse;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class CarClientFallback implements CarClient {
    @Override
    public ClientResponse checkIfCarAvailable(UUID carId) {
        log.info("INVENTORY_SERVICE_IS_DOWN");
        throw new BusinessException(("INVENTORY_SERVICE_IS_NOT_AVAILABLE_NOW"));
    }

    public String carAvailibilityNotKnown() {
        return "CAR_AVAILABILITY_NOT_KNOWN_TRY_ANOTHER";
    }
}
