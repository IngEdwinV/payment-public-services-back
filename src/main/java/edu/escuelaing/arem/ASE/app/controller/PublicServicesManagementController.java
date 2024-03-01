package edu.escuelaing.arem.ASE.app.controller;

import edu.escuelaing.arem.ASE.app.model.ServicioModel;
import edu.escuelaing.arem.ASE.app.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/services")
public class PublicServicesManagementController {

    private final ServicioService service;

    @Autowired
    public PublicServicesManagementController(ServicioService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<String> addService(@RequestBody ServicioModel servicioModel) {
        service.createService(servicioModel);
        return new ResponseEntity<>("servicio publico Creado", HttpStatus.CREATED);
    }

    @PutMapping("/update/{service_id}")
    public ResponseEntity<String> updateService(@PathVariable String service_id,
                                                @RequestBody ServicioModel servicioModel) {
        service.updateService(service_id, servicioModel);
        return new ResponseEntity<>("servicio publico modificado", HttpStatus.OK);
    }

    @GetMapping("/{service_id}")
    public ResponseEntity<String> getService(@PathVariable Int service_id) {
        String ser = service.getService(service_id);
        if (ser != null) {
            return new ResponseEntity<>(ser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("servicio no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{service_id}")
    public ResponseEntity<String> deleteService(@PathVariable String service_id) {
        boolean delete = service.deleteService(service_id);

        if (delete) {
            return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Object>> obtenerTodosServicios() {
        List<Object> servicios = service.getAllServices();
        
        if (servicios != null) {
            return new ResponseEntity<>(servicios, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
