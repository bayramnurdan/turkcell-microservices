package nurdanemin.commonpackage.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetCarResponse {
    private UUID id;
    private UUID modelId;
    private int modelYear;
    private String modelName;
    private String modelBrandName;
    private String plate;
    private double dailyPrice;

}
