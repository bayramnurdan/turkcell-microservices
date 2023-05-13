package nurdanemin.rentalservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
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
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final ModelMapperService mapper;
    private final RentalBusinessRules rules;


    @Override
    public List<GetAllRentalsResponse> getAll() {
        var rentals = repository.findAll();
        var response = rentals
                .stream()
                .map(rental -> mapper.forResponse().map(rental, GetAllRentalsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetRentalResponse getById(UUID id) {
        rules.checkIfRentalExists(id);
        var rental = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(rental, GetRentalResponse.class);

        return response;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        var rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(null);
        rental.setTotalPrice(getTotalPrice(rental));
        rental.setRentedAt(LocalDate.now());
        repository.save(rental);
        var response = mapper.forResponse().map(rental, CreateRentalResponse.class);

        return response;
    }

    @Override
    public UpdateRentalResponse update(UUID id, UpdateRentalRequest request) {
        rules.checkIfRentalExists(id);
        var rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(id);
        repository.save(rental);
        var response = mapper.forResponse().map(rental, UpdateRentalResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfRentalExists(id);
        repository.deleteById(id);
    }

    private double getTotalPrice(Rental rental) {
        return rental.getDailyPrice() * rental.getRentedForDays();
    }
}
