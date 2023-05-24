package nurdanemin.filterservice.business.abstracts;

import nurdanemin.filterservice.business.dto.responses.GetAllFiltersResponse;
import nurdanemin.filterservice.business.dto.responses.GetFilterResponse;
import nurdanemin.filterservice.entities.Filter;

import java.util.List;
import java.util.UUID;

public interface FilterService {
    List<GetAllFiltersResponse> getAll();

    GetFilterResponse getById(UUID id);

    void add(Filter filter);

    void delete(UUID id);

    void deleteByCarId(UUID carId);

    void deleteAllByBrandId(UUID brandId);

    void deleteAllByModelId(UUID modelId);

    Filter getByCarId(UUID carId);
}
