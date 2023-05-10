package nurdanemin.inventoryservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.inventoryservice.repository.BrandRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private final BrandRepository repository;


}
