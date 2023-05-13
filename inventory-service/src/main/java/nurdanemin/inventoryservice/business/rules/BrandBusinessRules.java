package nurdanemin.inventoryservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import nurdanemin.inventoryservice.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private final BrandRepository repository;

    public void checkIfBrandExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("BRAND_NOT_EXISTS");
        }
    }


}
