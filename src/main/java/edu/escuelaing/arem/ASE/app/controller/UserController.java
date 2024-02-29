package edu.escuelaing.arem.ASE.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/api/public_service/management/user/register")
    public String userRegister() {
        return "Hola desde el controlador con anotaciones Spring!";
    }

    @GetMapping("/api") // /public_service/management/user/validate/{user}")
    public String userValidate(){//@PathVariable String user) {
        return "Hola desde el controlador con anotaciones Spring!";
    }
}
