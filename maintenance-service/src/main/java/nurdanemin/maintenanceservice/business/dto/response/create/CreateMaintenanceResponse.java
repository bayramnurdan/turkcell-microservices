package nurdanemin.maintenanceservice.business.dto.response.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateMaintenanceResponse {
    private UUID id;
    private String information;
    private boolean completed;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private UUID carId;
}
