package nurdanemin.inventoryservice.business.abstracts;

import nurdanemin.inventoryservice.business.dto.requests.create.CreateCarRequest;
import nurdanemin.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import nurdanemin.inventoryservice.business.dto.responses.create.CreateCarResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetCarResponse;
import nurdanemin.inventoryservice.business.dto.responses.update.UpdateCarResponse;

import java.util.List;

public interface CarService {
    List<GetAllCarsResponse> getAll();

    GetCarResponse getById(int id);

    CreateCarResponse add(CreateCarRequest request);

    UpdateCarResponse update(UpdateCarRequest request);

    void delete(int id);

}
