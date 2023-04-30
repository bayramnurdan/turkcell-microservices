package nurdanemin.inventoryservice.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.inventoryservice.business.abstracts.CarService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cars")
public class CarsController {
    private final CarService service;

}
