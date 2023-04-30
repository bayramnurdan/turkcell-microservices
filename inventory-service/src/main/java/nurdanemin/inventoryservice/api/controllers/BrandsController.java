package nurdanemin.inventoryservice.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.inventoryservice.business.abstracts.BrandService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/brands")
public class BrandsController {
    private final BrandService service;


}
