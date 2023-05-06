package nurdanemin.inventoryservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.inventoryservice.entities.enums.State;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int modelYear;
    private String plate;
    @Enumerated(EnumType.STRING)
    private State state;
    private double dailyPrice;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

}
