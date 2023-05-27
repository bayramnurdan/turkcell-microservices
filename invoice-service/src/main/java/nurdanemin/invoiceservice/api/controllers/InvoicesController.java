package nurdanemin.invoiceservice.api.controllers;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import nurdanemin.invoiceservice.business.abstracts.InvoiceService;
import nurdanemin.invoiceservice.business.dto.response.GetAllInvoicesResponse;
import nurdanemin.invoiceservice.business.dto.response.GetInvoiceResponse;
import nurdanemin.invoiceservice.entities.Invoice;
import nurdanemin.invoiceservice.repository.InvoiceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/invoices")
public class InvoicesController {
    private final InvoiceService service;
    private final InvoiceRepository repository;

    @GetMapping
    public List<GetAllInvoicesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetInvoiceResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostConstruct
    public void add() {
        repository.save(new Invoice());
    }


}
