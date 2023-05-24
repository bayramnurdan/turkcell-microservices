package nurdanemin.filterservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.filterservice.business.abstracts.FilterService;
import nurdanemin.filterservice.business.dto.responses.GetAllFiltersResponse;
import nurdanemin.filterservice.business.dto.responses.GetFilterResponse;
import nurdanemin.filterservice.entities.Filter;
import nurdanemin.filterservice.repository.FilterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FilterManager implements FilterService {
    private final FilterRepository repository;
    private ModelMapperService mapper;

    @Override
    public List<GetAllFiltersResponse> getAll() {
        var filters = repository.findAll();
        var response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFiltersResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetFilterResponse getById(UUID id) {
        var filter = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(filter, GetFilterResponse.class);

        return response;
    }

    @Override
    public void add(Filter filter) {

        repository.save(filter);
    }

    @Override
    public void delete(UUID id) {

        repository.deleteById(id);
    }

    @Override
    public void deleteByCarId(UUID carId) {
        repository.deleteAllByCarId(carId);
    }

    @Override
    public void deleteAllByBrandId(UUID brandId) {
        repository.deleteAllByBrandId(brandId);

    }

    @Override
    public void deleteAllByModelId(UUID modelId) {
        repository.deleteAllByModelId(modelId);

    }

    @Override
    public Filter getByCarId(UUID carId) {
        return repository.findByCarId(carId);
    }

}
