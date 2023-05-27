package nurdanemin.invoiceservice.repository;

import nurdanemin.invoiceservice.entities.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface InvoiceRepository extends MongoRepository<Invoice, UUID> {
}
