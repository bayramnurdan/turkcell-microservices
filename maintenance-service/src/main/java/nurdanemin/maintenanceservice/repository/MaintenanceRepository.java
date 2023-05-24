package nurdanemin.maintenanceservice.repository;

import nurdanemin.maintenanceservice.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaintenanceRepository extends JpaRepository<Maintenance, UUID> {
    Maintenance findByCarId(UUID carId);

    boolean existsByCarIdAndCompletedIsFalse(UUID carId);


}
