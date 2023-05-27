package nurdanemin.invoiceservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.events.rental.RentalCreatedForInvoiceEvent;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.invoiceservice.business.abstracts.InvoiceService;
import nurdanemin.invoiceservice.business.dto.response.CreateInvoiceResponse;
import nurdanemin.invoiceservice.business.dto.response.GetAllInvoicesResponse;
import nurdanemin.invoiceservice.business.dto.response.GetInvoiceResponse;
import nurdanemin.invoiceservice.entities.Invoice;
import nurdanemin.invoiceservice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class InvoiceManager implements InvoiceService {
    private final ModelMapperService mapper;
    private final InvoiceRepository repository;

    @Override
    public CreateInvoiceResponse create(RentalCreatedForInvoiceEvent event) {
        var invoice = mapper.forRequest().map(event, Invoice.class);
        //invoice.setId(UUID.randomUUID());
        var savedInvoice = repository.save(invoice);
        return mapper.forResponse().map(savedInvoice, CreateInvoiceResponse.class);

    }

    @Override
    public List<GetAllInvoicesResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(invoice -> mapper.forResponse().map(invoice, GetAllInvoicesResponse.class))
                .toList();
    }

    @Override
    public GetInvoiceResponse getById(UUID id) {
        return mapper.forResponse().map(repository.findById(id).orElseThrow(), GetInvoiceResponse.class);
    }
}
