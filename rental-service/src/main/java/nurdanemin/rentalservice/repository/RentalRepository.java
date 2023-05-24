package nurdanemin.rentalservice.repository;

import nurdanemin.rentalservice.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RentalRepository extends JpaRepository<Rental, UUID> {
    boolean existsByCarId(UUID carId);
}
