package nurdanemin.maintenanceservice.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nurdanemin.maintenanceservice.business.abstracts.MaintenanceService;
import nurdanemin.maintenanceservice.business.dto.request.CreateMaintenanceRequest;
import nurdanemin.maintenanceservice.business.dto.response.create.CreateMaintenanceResponse;
import nurdanemin.maintenanceservice.business.dto.response.get.GetAllMaintenancesResponse;
import nurdanemin.maintenanceservice.business.dto.response.get.GetMaintenanceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/maintenances")
public class MaintenancesController {
    private final MaintenanceService service;

    @GetMapping
    public List<GetAllMaintenancesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    GetMaintenanceResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMaintenanceResponse add(@Valid @RequestBody CreateMaintenanceRequest request) {
        return service.add(request);
    }

    @PutMapping("/return}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void returnCarFromMaintenance(@RequestParam UUID carId) {
        service.returnCarFromMaintenance(carId);
    }

}