package nurdanemin.commonpackage.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
public class CarDeletedEvent {
    private UUID carId;

}
