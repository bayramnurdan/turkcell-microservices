package nurdanemin.inventoryservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.events.CarCreatedEvent;
import nurdanemin.commonpackage.events.CarDeletedEvent;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.inventoryservice.business.abstracts.CarService;
import nurdanemin.inventoryservice.business.dto.requests.create.CreateCarRequest;
import nurdanemin.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import nurdanemin.inventoryservice.business.dto.responses.create.CreateCarResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetCarResponse;
import nurdanemin.inventoryservice.business.dto.responses.update.UpdateCarResponse;
import nurdanemin.inventoryservice.business.kafka.producer.InventoryProducer;
import nurdanemin.inventoryservice.entities.Car;
import nurdanemin.inventoryservice.entities.enums.State;
import nurdanemin.inventoryservice.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repository;
    private final ModelMapperService mapper;
    private final InventoryProducer producer;

    @Override
    public List<GetAllCarsResponse> getAll() {
        var cars = repository.findAll();  //var sağa bakıyor, sade gözükmesi için
        var response = cars
                .stream()
                .map(car -> mapper.forResponse().map(car, GetAllCarsResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetCarResponse getById(UUID id) {
        var car = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(car, GetCarResponse.class);
        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        var car = mapper.forRequest().map(request, Car.class);
        car.setId(UUID.randomUUID());
        car.setState(State.Available);
        var createdCar = repository.save(car);
        sendKafkaCarCreatedEvent(createdCar);


        var response = mapper.forResponse().map(car, CreateCarResponse.class);
        return response;
    }

    @Override
    public UpdateCarResponse update(UUID id, UpdateCarRequest request) {
        var car = mapper.forRequest().map(request, Car.class);
        car.setId(id);
        repository.save(car);
        var response = mapper.forResponse().map(car, UpdateCarResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
        sendKafkaCarDeletedEvent(id);
    }

    private void sendKafkaCarCreatedEvent(Car createdCar) {
        var event = mapper.forResponse().map(createdCar, CarCreatedEvent.class);
        producer.sendMessage(event);

    }

    private void sendKafkaCarDeletedEvent(UUID id) {
        producer.sendMessage(new CarDeletedEvent(id));
    }
}
