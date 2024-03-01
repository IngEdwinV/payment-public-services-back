package edu.escuelaing.arem.ASE.app.service;

import edu.escuelaing.arem.ASE.app.configuration.InternalConstantValue;
import edu.escuelaing.arem.ASE.app.excepcion.Exception;
import edu.escuelaing.arem.ASE.app.model.Factura;
import edu.escuelaing.arem.ASE.app.model.usuario;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Objects;

@Service
public class FacturaService {

    private final JdbcTemplate jdbcTemplate;

    private final ServicioService service;

    private final UserService user;

    public FacturaService(JdbcTemplate jdbcTemplate, ServicioService service, UserService user) {
        this.jdbcTemplate = jdbcTemplate;
        this.service = service;
        this.user = user;
    }

    public void crearFactura(Factura factura) {
        // Verificar si existe el usuario
        if (!usuarioExiste(factura.getIdUsuario())) {
            throw new Exception.UsuarioNotFoundException("No se encontró el usuario con ID: " + factura.getIdUsuario());
        }

        // Verificar si existe el servicio
        if (!servicioExiste(factura.getIdServicio())) {
            throw new Exception.ServicioNotFoundException("No se encontró el servicio con ID: " + factura.getIdServicio());
        }

        // Puedes agregar más validaciones según tus necesidades

        String sql = "INSERT INTO factura (id, id_usuario, id_servicio, valor, estado, fecha_factura) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, new Object[]{
                factura.getIdUsuario(),
                factura.getIdServicio(),
                factura.getValor(),
                InternalConstantValue.EstadoFactura.ACTIVO,
                factura.getFechaFactura(),
        });
    }

    public void modificarFactura(String id, Factura factura) {
        // Implementa la lógica de modificación de factura aquí
    }

    public Factura buscarFactura(String id) {
        // Implementa la lógica de búsqueda de factura aquí
        return null;
    }

    private Boolean usuarioExiste(int userId) {
        usuario us = user.getUsuario(userId);
        return us.getId() == userId;
    }

    private Boolean servicioExiste(int serviceId) {
        String serv =service.getService(serviceId);
        return Objects.equals(serv, serviceId);
    }
}

