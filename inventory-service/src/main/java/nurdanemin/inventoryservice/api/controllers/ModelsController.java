package nurdanemin.inventoryservice.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nurdanemin.inventoryservice.business.abstracts.ModelService;
import nurdanemin.inventoryservice.business.dto.requests.create.CreateModelRequest;
import nurdanemin.inventoryservice.business.dto.requests.update.UpdateModelRequest;
import nurdanemin.inventoryservice.business.dto.responses.create.CreateModelResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetAllModelsResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetModelResponse;
import nurdanemin.inventoryservice.business.dto.responses.update.UpdateModelResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/models")
public class ModelsController {
    private final ModelService service;

    @GetMapping
    public List<GetAllModelsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetModelResponse getById(@PathVariable UUID id) {
        return service.getById(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateModelResponse add(@Valid @RequestBody CreateModelRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateModelResponse update(@PathVariable UUID id, @RequestBody UpdateModelRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
