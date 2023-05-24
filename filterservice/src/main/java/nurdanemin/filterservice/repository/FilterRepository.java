package nurdanemin.filterservice.repository;

import nurdanemin.filterservice.entities.Filter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface FilterRepository extends MongoRepository<Filter, UUID> {
    void deleteAllByBrandId(UUID brandId);

    void deleteAllByModelId(UUID modelId);

    void deleteAllByCarId(UUID carId);

    Filter findByCarId(UUID carId);
}
