package nurdanemin.inventoryservice.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.inventoryservice.business.abstracts.ModelService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/models")
public class ModelsController {
    private final ModelService service;
}
