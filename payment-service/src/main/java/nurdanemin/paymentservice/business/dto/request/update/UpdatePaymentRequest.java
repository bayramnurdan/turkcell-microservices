package nurdanemin.paymentservice.business.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.commonpackage.utils.dto.PaymentRequest;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePaymentRequest extends PaymentRequest {
    private double balance;
}
