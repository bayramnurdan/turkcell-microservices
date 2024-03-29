package nurdanemin.inventoryservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.dto.GetModelResponse;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.inventoryservice.business.abstracts.ModelService;
import nurdanemin.inventoryservice.business.dto.requests.create.CreateModelRequest;
import nurdanemin.inventoryservice.business.dto.requests.update.UpdateModelRequest;
import nurdanemin.inventoryservice.business.dto.responses.create.CreateModelResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetAllModelsResponse;
import nurdanemin.inventoryservice.business.dto.responses.update.UpdateModelResponse;
import nurdanemin.inventoryservice.business.rules.ModelBusinessRules;
import nurdanemin.inventoryservice.entities.Model;
import nurdanemin.inventoryservice.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelBusinessRules rules;
    private ModelRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllModelsResponse> getAll() {
        var models = repository.findAll();
        return models.stream().map(model -> mapper.forResponse().map(model, GetAllModelsResponse.class)).toList();
    }

    @Override
    public GetModelResponse getById(UUID id) {
        rules.checkIfModelExists(id);
        var Model = repository.findById(id).orElseThrow();
        return mapper.forResponse().map(Model, GetModelResponse.class);

    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        var model = mapper.forRequest().map(request, Model.class);
        model.setId(null);
        repository.save(model);
        return mapper.forResponse().map(model, CreateModelResponse.class);
    }

    @Override
    public UpdateModelResponse update(UUID id, UpdateModelRequest request) {
        rules.checkIfModelExists(id);
        var model = mapper.forRequest().map(request, Model.class);
        model.setId(id);
        repository.save(model);
        return mapper.forResponse().map(model, UpdateModelResponse.class);
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfModelExists(id);
        repository.deleteById(id);
    }
}