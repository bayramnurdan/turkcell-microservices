package nurdanemin.filterservice.business.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.events.maintenance.CreateMaintenanceEvent;
import nurdanemin.commonpackage.events.maintenance.ReturnMaintenanceEvent;
import nurdanemin.filterservice.business.abstracts.FilterService;
import nurdanemin.filterservice.entities.Filter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MaintenanceConsumer {
    private final FilterService service;

    @KafkaListener(topics = "maintenance-created", groupId = "filter-maintenance-create")
    public void consume(CreateMaintenanceEvent event) {
        Filter filter = service.getByCarId(event.getCarId());
        filter.setState("Maintenance");
        service.add(filter);
        log.info("Maintenance created event consumed {}", event);
    }


    @KafkaListener(topics = "return-maintenance", groupId = "filter-maintenance-return")
    public void consume(ReturnMaintenanceEvent event) {
        var filter = service.getByCarId(event.getCarId());
        filter.setState("Available");
        service.add(filter);
        log.info("Maintenance return event consumed {}", event);
    }


}