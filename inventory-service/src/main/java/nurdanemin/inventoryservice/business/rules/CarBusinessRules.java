package nurdanemin.inventoryservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.inventoryservice.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository repository;
}
