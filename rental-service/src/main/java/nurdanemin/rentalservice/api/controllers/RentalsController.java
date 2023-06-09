package nurdanemin.rentalservice.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nurdanemin.rentalservice.business.abstracts.RentalService;
import nurdanemin.rentalservice.business.dto.requests.CreateRentalRequest;
import nurdanemin.rentalservice.business.dto.requests.UpdateRentalRequest;
import nurdanemin.rentalservice.business.dto.responses.CreateRentalResponse;
import nurdanemin.rentalservice.business.dto.responses.GetAllRentalsResponse;
import nurdanemin.rentalservice.business.dto.responses.GetRentalResponse;
import nurdanemin.rentalservice.business.dto.responses.UpdateRentalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rentals")
public class RentalsController {
    private final RentalService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('admin')")
    public List<GetAllRentalsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin', 'user')")
    public GetRentalResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('user', 'admin')")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateRentalResponse add(@Valid @RequestBody CreateRentalRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public UpdateRentalResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateRentalRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}