package nurdanemin.inventoryservice.business.abstracts;

import nurdanemin.inventoryservice.business.dto.requests.create.CreateModelRequest;
import nurdanemin.inventoryservice.business.dto.requests.update.UpdateModelRequest;
import nurdanemin.inventoryservice.business.dto.responses.create.CreateModelResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetAllModelsResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetModelResponse;
import nurdanemin.inventoryservice.business.dto.responses.update.UpdateModelResponse;

import java.util.List;
import java.util.UUID;

public interface ModelService {
    List<GetAllModelsResponse> getAll();

    GetModelResponse getById(UUID id);

    CreateModelResponse add(CreateModelRequest request);

    UpdateModelResponse update(UUID id, UpdateModelRequest request);

    void delete(UUID id);
}
