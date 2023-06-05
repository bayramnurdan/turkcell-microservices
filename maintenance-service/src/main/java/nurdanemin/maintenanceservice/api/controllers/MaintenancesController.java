package nurdanemin.maintenanceservice.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nurdanemin.maintenanceservice.business.abstracts.MaintenanceService;
import nurdanemin.maintenanceservice.business.dto.request.CreateMaintenanceRequest;
import nurdanemin.maintenanceservice.business.dto.response.create.CreateMaintenanceResponse;
import nurdanemin.maintenanceservice.business.dto.response.get.GetAllMaintenancesResponse;
import nurdanemin.maintenanceservice.business.dto.response.get.GetMaintenanceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/maintenances")
public class MaintenancesController {
    private final MaintenanceService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('admin')")
    public List<GetAllMaintenancesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
        //TODO : KARAR ver
    GetMaintenanceResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('admin')")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMaintenanceResponse add(@Valid @RequestBody CreateMaintenanceRequest request) {
        return service.add(request);
    }

    @PutMapping("/return")
    @PreAuthorize("hasAnyRole('admin')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void returnCarFromMaintenance(@RequestParam UUID carId) {
        service.returnCarFromMaintenance(carId);
    }

}