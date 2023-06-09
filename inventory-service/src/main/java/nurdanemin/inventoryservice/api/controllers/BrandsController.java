package nurdanemin.inventoryservice.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nurdanemin.inventoryservice.business.abstracts.BrandService;
import nurdanemin.inventoryservice.business.dto.requests.create.CreateBrandRequest;
import nurdanemin.inventoryservice.business.dto.requests.update.UpdateBrandRequest;
import nurdanemin.inventoryservice.business.dto.responses.create.CreateBrandResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetAllBrandsResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetBrandResponse;
import nurdanemin.inventoryservice.business.dto.responses.update.UpdateBrandResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/brands")
public class BrandsController {
    private final BrandService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public List<GetAllBrandsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public GetBrandResponse getById(@PathVariable UUID id) {
        return service.getById(id);

    }

    @PostMapping
    @PreAuthorize("hasAnyRole('admin')")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse add(@Valid @RequestBody CreateBrandRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public UpdateBrandResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateBrandRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }


}
