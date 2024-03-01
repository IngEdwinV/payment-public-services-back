package edu.escuelaing.arem.ASE.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payments")
@CrossOrigin(origins = "*")
class PaymentController {


    @GetMapping("/api/public_service/management/payments/{user}")
    public String getPayments(@PathVariable String user) {
        return "Hola desde el controlador con anotaciones Spring!";
    }

    @GetMapping("/api/public_service/management/payments/status/{user}")
    public String getPaymentsStatus(@PathVariable String user) {
        return "Hola desde el controlador con anotaciones Spring!";
    }

}
