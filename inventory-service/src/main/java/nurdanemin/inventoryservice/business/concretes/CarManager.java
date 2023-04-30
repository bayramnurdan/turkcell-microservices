package nurdanemin.inventoryservice.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.inventoryservice.business.abstracts.CarService;
import nurdanemin.inventoryservice.business.dto.requests.create.CreateCarRequest;
import nurdanemin.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import nurdanemin.inventoryservice.business.dto.responses.create.CreateCarResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import nurdanemin.inventoryservice.business.dto.responses.get.GetCarResponse;
import nurdanemin.inventoryservice.business.dto.responses.update.UpdateCarResponse;
import nurdanemin.inventoryservice.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repoistory;

    @Override
    public List<GetAllCarsResponse> getAll() {
        return null;
    }

    @Override
    public GetCarResponse getById(int id) {
        return null;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        return null;
    }

    @Override
    public UpdateCarResponse update(UpdateCarRequest request) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
