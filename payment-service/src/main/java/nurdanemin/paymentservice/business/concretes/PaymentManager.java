package nurdanemin.paymentservice.business.concretes;

import lombok.RequiredArgsConstructor;
import nurdanemin.commonpackage.utils.dto.ClientResponse;
import nurdanemin.commonpackage.utils.dto.CreateRentalPaymentRequest;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.paymentservice.adapters.PosService;
import nurdanemin.paymentservice.business.abstracts.PaymentService;
import nurdanemin.paymentservice.business.dto.request.create.CreatePaymentRequest;
import nurdanemin.paymentservice.business.dto.request.update.UpdatePaymentRequest;
import nurdanemin.paymentservice.business.dto.response.create.CreatePaymentResponse;
import nurdanemin.paymentservice.business.dto.response.get.GetAllPaymentsResponse;
import nurdanemin.paymentservice.business.dto.response.get.GetPaymentResponse;
import nurdanemin.paymentservice.business.dto.response.update.UpdatePaymentResponse;
import nurdanemin.paymentservice.business.rules.PaymentBusinessRules;
import nurdanemin.paymentservice.entities.Payment;
import nurdanemin.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository repository;
    private final PaymentBusinessRules rules;
    private final ModelMapperService mapper;
    private final PosService posService;

    @Override
    public List<GetAllPaymentsResponse> getAll() {
        var payments = repository.findAll();
        return payments.stream().map(payment -> mapper.forResponse()
                .map(payment, GetAllPaymentsResponse.class)).toList();
    }

    @Override
    public GetPaymentResponse getById(UUID id) {
        rules.checkIfPaymentExists(id);
        return mapper.forResponse().map(repository.findById(id), GetPaymentResponse.class);
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        rules.checkIfCardExists(request.getCardNumber());
        var payment = mapper.forResponse().map(request, Payment.class);
        payment.setId(UUID.randomUUID());
        var createdPayment = repository.save(payment);
        return mapper.forResponse().map(createdPayment, CreatePaymentResponse.class);
    }

    @Override
    public UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request) {
        rules.checkIfPaymentExists(id);
        var payment = mapper.forResponse().map(request, Payment.class);
        payment.setId(id);
        var updatedPayment = repository.save(payment);
        return mapper.forResponse().map(updatedPayment, UpdatePaymentResponse.class);

    }

    @Override
    public void delete(UUID id) {
        rules.checkIfPaymentExists(id);
        repository.deleteById(id);

    }

    public ClientResponse pay(CreateRentalPaymentRequest request, Payment payment) {
        var response = new ClientResponse();
        try {
            rules.checkIfPaymentIsValid(request);
            rules.checkIfBalanceIdEnough(payment.getBalance(), request.getPrice());
            posService.pay();
            payment.setBalance(payment.getBalance() - request.getPrice());
            repository.save(payment);
            response.setSuccess(true);


        } catch (BusinessException exception) {
            response.setSuccess(false);
            response.setMessage(exception.getMessage());

        }
        return response;
    }


    @Override
    public ClientResponse processRentalPayment(CreateRentalPaymentRequest request) {
        Payment payment = repository.findByCardNumber(request.getCardNumber());
        return pay(request, payment);
    }
}
