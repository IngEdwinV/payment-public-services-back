package edu.escuelaing.arem.ASE.app.service;

import edu.escuelaing.arem.ASE.app.configuration.InternalConstantValue;
import edu.escuelaing.arem.ASE.app.excepcion.Exception;
import edu.escuelaing.arem.ASE.app.model.Factura;
import edu.escuelaing.arem.ASE.app.model.usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> ae5648810da76d6b1196c36273d75e64520bd140
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
<<<<<<< HEAD

        String sql = "INSERT INTO factura (id_usuario, id_servicio, valor, estado, fecha_factura) " +
                "VALUES (?, ?, ?, ?, ?)";
=======

        // Puedes agregar más validaciones según tus necesidades

        String sql = "INSERT INTO factura (id, id_usuario, id_servicio, valor, estado, fecha_factura) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
>>>>>>> ae5648810da76d6b1196c36273d75e64520bd140

        jdbcTemplate.update(sql, new Object[]{
                factura.getIdUsuario(),
                factura.getIdServicio(),
                factura.getValor(),
<<<<<<< HEAD
                InternalConstantValue.EstadoFactura.ACTIVO.toString(),
=======
                InternalConstantValue.EstadoFactura.ACTIVO,
>>>>>>> ae5648810da76d6b1196c36273d75e64520bd140
                factura.getFechaFactura(),
        });
    }

    public void modificarEstadoFactura(int id, Factura factura) {
        if (getFactura(id) == null)
            throw new Exception.FacturaNotFoundException("No se encontró la factura con ID: " + id);

        String sql = "UPDATE factura SET estado = ? WHERE id = ?";

        jdbcTemplate.update(sql, factura.getEstado().toString(), id);
    }

    public void modificarValorFactura(int id, Factura factura) {
        if (getFactura(id) == null)
            throw new Exception.FacturaNotFoundException("No se encontró la factura con ID: " + id);

        String sql = "UPDATE factura SET valor = ? WHERE id = ?";

        jdbcTemplate.update(sql, factura.getValor(), id);
    }

    public Factura getFactura(int id) {
        String sql = "SELECT * FROM factura WHERE id = ?";
        try {
            // Utilizamos BeanPropertyRowMapper para mapear las filas de la consulta a un objeto Usuario
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Factura.class));
        } catch (java.lang.Exception e) {
            System.err.println("No se encontró la factura con ID '" + id + "': " + e.getMessage());
            return null;
        }
    }

    public List<Object> getFacturaByUser(int userId) {
        String sql = "SELECT * FROM factura WHERE id_usuario = ?";
        try {
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
                Factura factura = new Factura();
                factura.setId(rs.getInt("id"));
                factura.setIdUsuario(rs.getInt("id_usuario"));
                factura.setIdServicio(rs.getInt("id_servicio"));
                factura.setValor(rs.getBigDecimal("valor"));
                factura.setEstado(InternalConstantValue.EstadoFactura.valueOf(rs.getString("estado")));
                factura.setFechaFactura(rs.getDate("fecha_factura"));
                return factura;
            }, userId);
        } catch (java.lang.Exception e) {
            System.err.println("Error al obtener todas las facturas por usuario: " + e.getMessage());
            return null;
        }
    }

    private Boolean usuarioExiste(int userId) {
        usuario us = user.getUsuario(userId);
        return us.getId() == userId;
    }

    private Boolean servicioExiste(int serviceId) {
<<<<<<< HEAD
        String serv = service.getService(serviceId);
        return Objects.nonNull(serv);
=======
        String serv =service.getService(serviceId);
        return Objects.equals(serv, serviceId);
>>>>>>> ae5648810da76d6b1196c36273d75e64520bd140
    }
}

