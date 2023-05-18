package nurdanemin.rentalservice.business.dto.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.commonpackage.events.Event;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRentalRequest implements Event {
    @NotNull
    private UUID carId;
    @Min(1)
    private double dailyPrice;
    @Min(1)
    private int rentedForDays;
}

