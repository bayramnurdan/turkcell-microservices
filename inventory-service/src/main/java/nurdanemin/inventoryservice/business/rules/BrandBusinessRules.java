package nurdanemin.inventoryservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.inventoryservice.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private final BrandRepository repository;

    public void checIfCarExists(UUID id) {
        if (!repository.existsById(id)) {
            // TODO Runtime Exception
            throw new RuntimeException("CAR_NOT_EXISTS");

        }

    }
}
