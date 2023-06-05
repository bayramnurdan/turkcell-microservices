package nurdanemin.invoiceservice.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.invoiceservice.business.abstracts.InvoiceService;
import nurdanemin.invoiceservice.business.dto.response.GetAllInvoicesResponse;
import nurdanemin.invoiceservice.business.dto.response.GetInvoiceResponse;
import nurdanemin.invoiceservice.repository.InvoiceRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/invoices")
public class InvoicesController {
    private final InvoiceService service;
    private final InvoiceRepository repository;

    @GetMapping
    @PreAuthorize("hasAnyRole('admin')")
    public List<GetAllInvoicesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    // TODO: Karar ver
    public GetInvoiceResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

}
