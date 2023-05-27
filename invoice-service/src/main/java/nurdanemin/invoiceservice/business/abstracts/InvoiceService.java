package nurdanemin.invoiceservice.business.abstracts;


import nurdanemin.commonpackage.events.rental.RentalCreatedForInvoiceEvent;
import nurdanemin.invoiceservice.business.dto.response.CreateInvoiceResponse;
import nurdanemin.invoiceservice.business.dto.response.GetAllInvoicesResponse;
import nurdanemin.invoiceservice.business.dto.response.GetInvoiceResponse;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {

    CreateInvoiceResponse create(RentalCreatedForInvoiceEvent event);

    List<GetAllInvoicesResponse> getAll();

    GetInvoiceResponse getById(UUID id);

}
