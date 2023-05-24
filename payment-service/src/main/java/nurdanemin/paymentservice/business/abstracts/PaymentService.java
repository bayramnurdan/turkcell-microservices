package nurdanemin.paymentservice.business.abstracts;

import nurdanemin.commonpackage.utils.dto.ClientResponse;
import nurdanemin.commonpackage.utils.dto.CreateRentalPaymentRequest;
import nurdanemin.paymentservice.business.dto.request.create.CreatePaymentRequest;
import nurdanemin.paymentservice.business.dto.request.update.UpdatePaymentRequest;
import nurdanemin.paymentservice.business.dto.response.create.CreatePaymentResponse;
import nurdanemin.paymentservice.business.dto.response.get.GetAllPaymentsResponse;
import nurdanemin.paymentservice.business.dto.response.get.GetPaymentResponse;
import nurdanemin.paymentservice.business.dto.response.update.UpdatePaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();

    GetPaymentResponse getById(UUID id);

    CreatePaymentResponse add(CreatePaymentRequest request);

    UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request);

    void delete(UUID id);

    ClientResponse processRentalPayment(CreateRentalPaymentRequest request);


}
