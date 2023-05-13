package nurdanemin.rentalservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import nurdanemin.rentalservice.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository repository;

    public void checkIfRentalExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("RENTAL_NOT_EXISTS");
        }
    }
}