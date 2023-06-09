package nurdanemin.maintenanceservice.business.dto.response.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllMaintenancesResponse {
    private UUID id;
    private String information;
    private boolean completed;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private UUID carId;
}
