package nurdanemin.rentalservice.business.abstracts;

import nurdanemin.rentalservice.business.dto.requests.CreateRentalRequest;
import nurdanemin.rentalservice.business.dto.requests.UpdateRentalRequest;
import nurdanemin.rentalservice.business.dto.responses.CreateRentalResponse;
import nurdanemin.rentalservice.business.dto.responses.GetAllRentalsResponse;
import nurdanemin.rentalservice.business.dto.responses.GetRentalResponse;
import nurdanemin.rentalservice.business.dto.responses.UpdateRentalResponse;

import java.util.List;
import java.util.UUID;

public interface RentalService {
    List<GetAllRentalsResponse> getAll();

    GetRentalResponse getById(UUID id);

    CreateRentalResponse add(CreateRentalRequest request);

    UpdateRentalResponse update(UUID id, UpdateRentalRequest request);

    void delete(UUID id);
}