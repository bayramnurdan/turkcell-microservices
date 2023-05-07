package nurdanemin.inventoryservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import nurdanemin.inventoryservice.business.abstracts.BrandService;
import nurdanemin.inventoryservice.business.dto.requests.create.CreateBrandRequest;
import nurdanemin.inventoryservice.business.dto.requests.update.UpdateBrandRequest;
import nurdanemin.inventoryservice.business.dto.responses.create.CreateBrandResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetAllBrandsResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetBrandResponse;
import nurdanemin.inventoryservice.business.dto.responses.update.UpdateBrandResponse;
import nurdanemin.inventoryservice.entities.Brand;
import nurdanemin.inventoryservice.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository repository;

    private final ModelMapperService mapper;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        var brands = repository.findAll();
        var response = brands
                .stream()
                .map(brand -> mapper.forResponse().map(brand, GetAllBrandsResponse.class))
                .toList();
        return response;


    }

    @Override
    public GetBrandResponse getById(UUID id) {
        var brand = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(brand, GetBrandResponse.class);
        return response;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {

        var brand = mapper.forRequest().map(request, Brand.class);
        brand.setId(null);
        repository.save(brand);
        var response = mapper.forResponse().map(brand, CreateBrandResponse.class);
        return response;

    }

    @Override
    public UpdateBrandResponse update(UUID id, UpdateBrandRequest request) {
        var brand = mapper.forRequest().map(request, Brand.class);
        brand.setId(id);
        repository.save(brand);
        var response = mapper.forResponse().map(brand, UpdateBrandResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);

    }
}