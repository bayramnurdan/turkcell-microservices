package nurdanemin.rentalservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.events.rental.RentalCreatedEvent;
import nurdanemin.commonpackage.events.rental.RentalCreatedForInvoiceEvent;
import nurdanemin.commonpackage.events.rental.RentalDeletedEvent;
import nurdanemin.commonpackage.kafka.producer.KafkaProducer;
import nurdanemin.commonpackage.utils.dto.CreateRentalPaymentRequest;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.rentalservice.api.clients.CarClient;
import nurdanemin.rentalservice.api.clients.PayClient;
import nurdanemin.rentalservice.business.abstracts.RentalService;
import nurdanemin.rentalservice.business.dto.requests.CreateRentalRequest;
import nurdanemin.rentalservice.business.dto.requests.UpdateRentalRequest;
import nurdanemin.rentalservice.business.dto.responses.CreateRentalResponse;
import nurdanemin.rentalservice.business.dto.responses.GetAllRentalsResponse;
import nurdanemin.rentalservice.business.dto.responses.GetRentalResponse;
import nurdanemin.rentalservice.business.dto.responses.UpdateRentalResponse;
import nurdanemin.rentalservice.business.rules.RentalBusinessRules;
import nurdanemin.rentalservice.entities.Rental;
import nurdanemin.rentalservice.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final ModelMapperService mapper;
    private final RentalBusinessRules rules;
    private final KafkaProducer producer;
    private final PayClient payClient;
    private final CarClient carClient;


    @Override
    public List<GetAllRentalsResponse> getAll() {
        var rentals = repository.findAll();
        return rentals.stream().map(rental -> mapper.forResponse().map(rental, GetAllRentalsResponse.class)).toList();
    }

    @Override
    public GetRentalResponse getById(UUID id) {
        rules.checkIfRentalExists(id);
        var rental = repository.findById(id).orElseThrow();
        return mapper.forResponse().map(rental, GetRentalResponse.class);
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        //TODO : hala yapılacak şeyler var
        rules.ensureCarIsAvailable(request.getCarId());

        var rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(null);
        rental.setTotalPrice(getTotalPrice(rental));
        rental.setRentedAt(LocalDate.now());

        var paymentrequest = mapper.forResponse().map(request.getPaymentRequest(), CreateRentalPaymentRequest.class);
        paymentrequest.setPrice(getTotalPrice(rental));
        payClient.pay(paymentrequest);

        repository.save(rental);

        producer.sendMessage(new RentalCreatedEvent(request.getCarId()), "rental-created");

        var response = mapper.forResponse().map(rental, CreateRentalResponse.class);

        var event = createRentalCreatedForInvoiceEvent(request.getCarId(), request);
        producer.sendMessage(event, "rental-created-for-invoice");


        return response;
    }


    @Override
    public UpdateRentalResponse update(UUID id, UpdateRentalRequest request) {
        rules.checkIfRentalExists(id);
        var rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(id);
        repository.save(rental);
        return mapper.forResponse().map(rental, UpdateRentalResponse.class);
    }


    @Override
    public void delete(UUID id) {
        rules.checkIfRentalExists(id);
        sendKafkaRentalDeletedEvent(id);
        repository.deleteById(id);
    }

    private double getTotalPrice(Rental rental) {
        return rental.getDailyPrice() * rental.getRentedForDays();
    }

    private void sendKafkaRentalDeletedEvent(UUID id) {
        var carId = repository.findById(id).orElseThrow().getCarId();
        producer.sendMessage(new RentalDeletedEvent(carId), "rental-deleted");
    }

    private RentalCreatedForInvoiceEvent createRentalCreatedForInvoiceEvent(UUID carId, CreateRentalRequest request) {
        var carResponse = carClient.getById(request.getCarId());

        RentalCreatedForInvoiceEvent event = new RentalCreatedForInvoiceEvent();
        event.setBrandName(carResponse.getModelBrandName());
        event.setPlate(carResponse.getPlate());
        event.setRentedAt(LocalDateTime.now());
        event.setDailyPrice(carResponse.getDailyPrice());
        event.setRentedForDays(request.getRentedForDays());
        event.setTotalPrice(request.getRentedForDays() * carResponse.getDailyPrice());
        event.setCardHolder(request.getPaymentRequest().getCardHolder());
        event.setModelYear(carResponse.getModelYear());
        return event;
    }


}
