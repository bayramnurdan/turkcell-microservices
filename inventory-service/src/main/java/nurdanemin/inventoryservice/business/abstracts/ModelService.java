package nurdanemin.inventoryservice.business.abstracts;

import nurdanemin.inventoryservice.business.dto.requests.create.CreateModelRequest;
import nurdanemin.inventoryservice.business.dto.requests.update.UpdateModelRequest;
import nurdanemin.inventoryservice.business.dto.responses.create.CreateModelResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetAllModelsResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetModelResponse;
import nurdanemin.inventoryservice.business.dto.responses.update.UpdateModelResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelsResponse> getAll();

    GetModelResponse getById(int id);

    CreateModelResponse add(CreateModelRequest request);

    UpdateModelResponse update(UpdateModelRequest request);

    void delete(int id);
}
