package nurdanemin.paymentservice.business.rules;

import lombok.RequiredArgsConstructor;
import nurdanemin.commonpackage.utils.constants.Messages;
import nurdanemin.commonpackage.utils.dto.CreateRentalPaymentRequest;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import nurdanemin.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentBusinessRules {
    private final PaymentRepository repository;

    public void checkIfPaymentExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Payment.NotFound);
        }
    }

    public void checkIfBalanceIdEnough(double balance, double price) {
        if (balance < price) {
            throw new BusinessException(Messages.Payment.NotEnoughMoney);
        }
    }

    public void checkIfCardExists(String cardNumber) {
        if (repository.existsByCardNumber(cardNumber)) {
            throw new BusinessException(Messages.Payment.CardNumberAlreadyExists);
        }
    }

    public void checkIfPaymentIsValid(CreateRentalPaymentRequest request) {
        if (!repository.existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(request.getCardNumber(), request.getCardHolder(), request.getCardExpirationYear(), request.getCardExpirationMonth(), request.getCardCvv())) {
            throw new BusinessException(Messages.Payment.NotAValidPayment);
        }
    }
}
