package nurdanemin.inventoryservice.business.kafka.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.events.rental.RentalCreatedEvent;
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
            topics = "rental-created",
            groupId = "inventory-rental-create"
    )
    public void consume(RentalCreatedEvent event) {
        service.changeStateByCarId(State.Rented, event.getCarId());
        log.info("Rental created event consumed {}", event);
    }
}
