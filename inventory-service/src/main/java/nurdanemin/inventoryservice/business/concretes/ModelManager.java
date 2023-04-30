package nurdanemin.inventoryservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.inventoryservice.business.abstracts.ModelService;
import nurdanemin.inventoryservice.business.dto.requests.create.CreateModelRequest;
import nurdanemin.inventoryservice.business.dto.requests.update.UpdateModelRequest;
import nurdanemin.inventoryservice.business.dto.responses.create.CreateModelResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetAllModelsResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetModelResponse;
import nurdanemin.inventoryservice.business.dto.responses.update.UpdateModelResponse;
import nurdanemin.inventoryservice.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelRepository repository;

    @Override
    public List<GetAllModelsResponse> getAll() {
        return null;
    }

    @Override
    public GetModelResponse getById(int id) {
        return null;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        return null;
    }

    @Override
    public UpdateModelResponse update(UpdateModelRequest request) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
