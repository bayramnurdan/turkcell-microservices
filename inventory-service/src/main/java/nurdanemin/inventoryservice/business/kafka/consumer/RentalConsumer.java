package nurdanemin.inventoryservice.business.kafka.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.events.rental.RentalCreatedEvent;
import nurdanemin.commonpackage.events.rental.RentalDeletedEvent;
import nurdanemin.inventoryservice.business.abstracts.CarService;
import nurdanemin.inventoryservice.entities.enums.State;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RentalConsumer {
    private final CarService service;

    @KafkaListener(
            topics = "rental-created-for-invoice",
            groupId = "rental-create-for-invoice"
    )
    public void consume(RentalCreatedEvent event) {
        service.changeStateByCarId(State.Rented, event.getCarId());
        log.info("Rental created event consumed {}", event);
    }

    @KafkaListener(
            topics = "rental-deleted",
            groupId = "inventory-rental-delete"
    )
    public void consume(RentalDeletedEvent event) {
        service.changeStateByCarId(State.Available, event.getCarId());
        log.info("Rental deleted event consumed {}", event);
    }
}
