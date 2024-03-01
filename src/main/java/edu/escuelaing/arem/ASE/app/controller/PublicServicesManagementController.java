package edu.escuelaing.arem.ASE.app.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/services")
public class PublicServicesManagementController {

    @PostMapping("/api/public_service/management/service")
    public String addService() {
        return "Hola desde el controlador con anotaciones Spring!";
    }

    @PutMapping("/api/public_service/management/{service_id}")
    public String updateService(@PathVariable String service_id) {
        return "Hola desde el controlador con anotaciones Spring!";
    }

    @GetMapping("/api/public_service/management/service/{service_id}")
    public String getService(@PathVariable String service_id) {
        return "Hola desde el controlador con anotaciones Spring!";
    }

    @DeleteMapping("/api/public_service/management/service/{service_id}")
    public String deleteService(@PathVariable String service_id) {
        return "Hola desde el controlador con anotaciones Spring!";
    }
}
