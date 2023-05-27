package nurdanemin.invoiceservice.business.kafka.consumer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.events.rental.RentalCreatedForInvoiceEvent;
import nurdanemin.invoiceservice.business.abstracts.InvoiceService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Getter
@Setter
@AllArgsConstructor
public class RentalConsumer {
    private final InvoiceService service;

    @KafkaListener(
            topics = "rental-created-for-invoice",
            groupId = "rental-create-for-invoice"
    )
    public void consume(RentalCreatedForInvoiceEvent event) {
        service.create(event);
        log.info("Rental created for invoice event consumed {}", event);
    }
}
