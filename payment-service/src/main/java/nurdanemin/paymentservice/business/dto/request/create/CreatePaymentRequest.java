package nurdanemin.paymentservice.business.dto.request.create;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.commonpackage.utils.dto.PaymentRequest;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest extends PaymentRequest {
    @Min(value = 1)
    private double balance;
}
