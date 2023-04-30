package nurdanemin.inventoryservice.business.abstracts;

import nurdanemin.inventoryservice.business.dto.requests.create.CreateBrandRequest;
import nurdanemin.inventoryservice.business.dto.requests.update.UpdateBrandRequest;
import nurdanemin.inventoryservice.business.dto.responses.create.CreateBrandResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetAllBrandsResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetBrandResponse;
import nurdanemin.inventoryservice.business.dto.responses.update.UpdateBrandResponse;

import java.util.List;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();

    GetBrandResponse getById(int id);

    CreateBrandResponse add(CreateBrandRequest request);

    UpdateBrandResponse update(UpdateBrandRequest request);

    void delete(int id);
}
