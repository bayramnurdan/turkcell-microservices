package nurdanemin.paymentservice.adapters;

import nurdanemin.commonpackage.utils.constants.Messages;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PosServiceAdapter implements PosService {
    @Override
    public void pay() {
        boolean isPaymentSuccessful = new Random().nextBoolean();
        if (!isPaymentSuccessful) throw new BusinessException(Messages.Payment.Failed);

    }
}
