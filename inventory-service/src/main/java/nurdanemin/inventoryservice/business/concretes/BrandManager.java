package nurdanemin.inventoryservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.inventoryservice.business.abstracts.BrandService;
import nurdanemin.inventoryservice.business.dto.requests.create.CreateBrandRequest;
import nurdanemin.inventoryservice.business.dto.requests.update.UpdateBrandRequest;
import nurdanemin.inventoryservice.business.dto.responses.create.CreateBrandResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetAllBrandsResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetBrandResponse;
import nurdanemin.inventoryservice.business.dto.responses.update.UpdateBrandResponse;
import nurdanemin.inventoryservice.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository repository;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        return null;
    }

    @Override
    public GetBrandResponse getById(int id) {
        return null;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
        return null;
    }

    @Override
    public UpdateBrandResponse update(UpdateBrandRequest request) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
