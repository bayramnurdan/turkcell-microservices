package nurdanemin.inventoryservice.business.kafka.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.events.maintenance.CreateMaintenanceEvent;
import nurdanemin.commonpackage.events.maintenance.ReturnMaintenanceEvent;
import nurdanemin.inventoryservice.business.abstracts.CarService;
import nurdanemin.inventoryservice.entities.enums.State;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MaintenanceConsumer {

    private final CarService service;

    @KafkaListener(topics = "return-maintenance", groupId = "return-maintenanced")
    public void consume(ReturnMaintenanceEvent event) {
        service.changeStateByCarId(State.AVAILABLE, event.getCarId());
        log.info("Maintenance returned event consumed {}", event);
    }

    @KafkaListener(topics = "maintenance-created", groupId = "maintenance-create")
    public void consume(CreateMaintenanceEvent event) {
        service.changeStateByCarId(State.UNDER_MAINTENANCE, event.getCarId());
        log.info("Maintenance created event consumed {}", event);
    }


}
