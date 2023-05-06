package nurdanemin.inventoryservice.business.abstracts;

import nurdanemin.inventoryservice.business.dto.requests.create.CreateBrandRequest;
import nurdanemin.inventoryservice.business.dto.requests.update.UpdateBrandRequest;
import nurdanemin.inventoryservice.business.dto.responses.create.CreateBrandResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetAllBrandsResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetBrandResponse;
import nurdanemin.inventoryservice.business.dto.responses.update.UpdateBrandResponse;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();

    GetBrandResponse getById(UUID id);

    CreateBrandResponse add(CreateBrandRequest request);

    UpdateBrandResponse update(UUID id, UpdateBrandRequest request);

    void delete(UUID id);
}
