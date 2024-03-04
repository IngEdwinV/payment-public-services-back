package edu.escuelaing.arem.ASE.app.controller;

import edu.escuelaing.arem.ASE.app.model.Factura;
import edu.escuelaing.arem.ASE.app.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/factura")
@CrossOrigin(origins = "*")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping
    public ResponseEntity<String> crearFactura(@RequestBody Factura factura) {
        facturaService.crearFactura(factura);
        return new ResponseEntity<>("Factura creada", HttpStatus.CREATED);
    }

    @PutMapping("/estado/{id}")
    public ResponseEntity<String> modificarEstadoFactura(@PathVariable int id, @RequestBody Factura factura) {
        facturaService.modificarEstadoFactura(id, factura);
        return new ResponseEntity<>("Factura modificada", HttpStatus.OK);
    }

    @PutMapping("/valor/{id}")
    public ResponseEntity<String> modificarValorFactura(@PathVariable int id, @RequestBody Factura factura) {
        facturaService.modificarValorFactura(id, factura);
        return new ResponseEntity<>("Factura modificada", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> buscarFactura(@PathVariable int id) {
        Factura factura = facturaService.getFactura(id);
        return new ResponseEntity<>(factura, HttpStatus.OK);
    }

    @GetMapping("/by_user/{userId}")
    public ResponseEntity<?> buscarFacturasByUser(@PathVariable int userId) {
        List<Object>  factura =  facturaService.getFacturaByUser(userId);
        if (factura != null) {
            return new ResponseEntity<>(factura, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
