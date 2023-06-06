package nurdanemin.rentalservice.api.clients;

import io.github.resilience4j.retry.annotation.Retry;
import nurdanemin.commonpackage.utils.dto.ClientResponse;
import nurdanemin.commonpackage.utils.dto.CreateRentalPaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", fallback = PayClientFallback.class)
public interface PayClient {

    @PostMapping("/api/payments/pay")
    @Retry(name = "payment-service")
    ClientResponse pay(@RequestBody CreateRentalPaymentRequest request);

}
