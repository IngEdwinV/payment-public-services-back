package edu.escuelaing.arem.ASE.app.controller;

import edu.escuelaing.arem.ASE.app.model.Factura;
import edu.escuelaing.arem.ASE.app.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping
    public ResponseEntity<String> crearFactura(@RequestBody Factura factura) {
        facturaService.crearFactura(factura);
        return new ResponseEntity<>("Factura creada", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarFactura(@PathVariable String id, @RequestBody Factura factura) {
        facturaService.modificarFactura(id, factura);
        return new ResponseEntity<>("Factura modificada", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> buscarFactura(@PathVariable String id) {
        Factura factura = facturaService.buscarFactura(id);
        return new ResponseEntity<>(factura, HttpStatus.OK);
    }

}
