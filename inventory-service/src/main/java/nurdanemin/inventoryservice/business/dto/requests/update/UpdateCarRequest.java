package nurdanemin.inventoryservice.business.dto.requests.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCarRequest {
    @NotBlank
    @NotNull
    private UUID modelId;
    @Min(value = 2000)
    private int modelYear;

    @NotNull
    @NotBlank
    // TODO : Add regex
    private String plate;

    @Min(value = 1)
    private double dailyPrice;
}
