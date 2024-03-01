package edu.escuelaing.arem.ASE.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.arem.ASE.app.model.LoginUser;
import edu.escuelaing.arem.ASE.app.model.usuario;
import edu.escuelaing.arem.ASE.app.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService us;

    @Autowired
    public UserController(UserService usuarioDAO) {
        this.us = usuarioDAO;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody usuario user){
        us.create(user);
        return new ResponseEntity<>("Usuario Creado", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUser loginForm) {
        String correo = loginForm.getCorreo();
        String password = loginForm.getPassword();
        //String rol = "USER"; // o "ADMIN", dependiendo del rol que esperas

        if (us.login(correo, password)) {
            return new ResponseEntity<>("Login exitoso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Correo, contraseña o rol incorrectos", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{correo}/rol")
    public ResponseEntity<String> obtenerRolUsuario(@PathVariable String correo) {
        String rol = us.obtenerRolUsuario(correo);
        
        if (rol != null) {
            return new ResponseEntity<>(rol, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario no encontrado o rol no asignado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{correo}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable String correo) {
        boolean eliminado = us.eliminarUsuarioPorCorreo(correo);
        
        if (eliminado) {
            return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
