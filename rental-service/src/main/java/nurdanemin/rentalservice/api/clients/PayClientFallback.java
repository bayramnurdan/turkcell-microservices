package nurdanemin.rentalservice.api.clients;

import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.utils.dto.ClientResponse;
import nurdanemin.commonpackage.utils.dto.CreateRentalPaymentRequest;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PayClientFallback implements PayClient {
    @Override
    public ClientResponse pay(CreateRentalPaymentRequest request) {
        log.info("PAYMENT_FAILED");
        throw new BusinessException(("PAYMENT_FAILURE"));
    }
}
