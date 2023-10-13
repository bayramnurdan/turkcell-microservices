package nurdanemin.inventoryservice.business.dto.requests.create;

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

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCarRequest {
    @NotNull
    private UUID modelId;
    @Min(value = 2000)
    @NotFutureYear
    private int modelYear;


    @NotBlank
    @Pattern(regexp = Regex.PLATE)
    private String plate;

    @Min(value = 1)
    private double dailyPrice;
}