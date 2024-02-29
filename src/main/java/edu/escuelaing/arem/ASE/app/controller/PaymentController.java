package edu.escuelaing.arem.ASE.app.controller;

import edu.escuelaing.arem.ASE.app.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/api/public_service/management/payment")
    public String addPayment() {
        return paymentService.addPayment();
    }

    @GetMapping("/api/public_service/management/payments/{user}")
    public String getPayments(@PathVariable String user) {
        return "Hola desde el controlador con anotaciones Spring!";
    }

    @GetMapping("/api/public_service/management/payments/status/{user}")
    public String getPaymentsStatus(@PathVariable String user) {
        return "Hola desde el controlador con anotaciones Spring!";
    }

}
