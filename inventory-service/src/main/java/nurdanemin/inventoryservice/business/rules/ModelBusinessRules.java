package nurdanemin.inventoryservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.inventoryservice.repository.ModelRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private final ModelRepository repository;
}
