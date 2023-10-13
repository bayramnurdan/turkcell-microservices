package nurdanemin.inventoryservice.business.dto.requests.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.commonpackage.utils.annotations.NotFutureYear;
import nurdanemin.commonpackage.utils.constants.Regex;
import nurdanemin.inventoryservice.entities.enums.State;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCarRequest {
    @NotBlank
    @NotNull
    private UUID modelId;

    @NotFutureYear
    @Min(value = 2000)
    private int modelYear;

    @NotNull
    @Pattern(regexp = Regex.PLATE)
    private String plate;
    @NotNull
    private State state;

    @Min(value = 1)
    private double dailyPrice;
}