package nurdanemin.inventoryservice.business.abstracts;

import nurdanemin.commonpackage.utils.dto.ClientResponse;
import nurdanemin.inventoryservice.business.dto.requests.create.CreateCarRequest;
import nurdanemin.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import nurdanemin.inventoryservice.business.dto.responses.create.CreateCarResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetCarResponse;
import nurdanemin.inventoryservice.business.dto.responses.update.UpdateCarResponse;
import nurdanemin.inventoryservice.entities.enums.State;

import java.util.List;
import java.util.UUID;

public interface CarService {
    List<GetAllCarsResponse> getAll();

    GetCarResponse getById(UUID id);

    CreateCarResponse add(CreateCarRequest request);

    UpdateCarResponse update(UUID id, UpdateCarRequest request);

    void delete(UUID id);

    ClientResponse checkIfCarAvailable(UUID id);

    void changeStateByCarId(State state, UUID id);


}
