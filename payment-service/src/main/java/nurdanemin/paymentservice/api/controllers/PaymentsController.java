package nurdanemin.paymentservice.api.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nurdanemin.commonpackage.utils.dto.ClientResponse;
import nurdanemin.commonpackage.utils.dto.CreateRentalPaymentRequest;
import nurdanemin.paymentservice.business.abstracts.PaymentService;
import nurdanemin.paymentservice.business.dto.request.create.CreatePaymentRequest;
import nurdanemin.paymentservice.business.dto.request.update.UpdatePaymentRequest;
import nurdanemin.paymentservice.business.dto.response.create.CreatePaymentResponse;
import nurdanemin.paymentservice.business.dto.response.get.GetAllPaymentsResponse;
import nurdanemin.paymentservice.business.dto.response.get.GetPaymentResponse;
import nurdanemin.paymentservice.business.dto.response.update.UpdatePaymentResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentsController {
    private final PaymentService service;

    @GetMapping
    public List<GetAllPaymentsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@PathVariable UUID id, @Valid @RequestBody UpdatePaymentRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PostMapping("/pay")
    public ClientResponse pay(@RequestBody CreateRentalPaymentRequest request) {
        return service.processRentalPayment(request);

    }
}
