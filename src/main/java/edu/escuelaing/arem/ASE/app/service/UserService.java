package edu.escuelaing.arem.ASE.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import edu.escuelaing.arem.ASE.app.model.usuario;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(usuario user) {
        String sql = "INSERT INTO usuario (id, nombre, apellido, password, correo, telefono, direccion, id_rol) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getNombre());
            preparedStatement.setString(3, user.getApellido());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getCorreo());
            preparedStatement.setString(6, user.getTelefono());
            preparedStatement.setString(7, user.getDireccion());
            preparedStatement.setInt(8, 1);
        });
    }

    public boolean login(String correo, String password) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE correo = ? AND password = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, correo, password);
        return count > 0;
    }

    public String obtenerRolUsuario(String correo) {
        String sql = "SELECT r.rol " +
                "FROM usuario u " +
                "INNER JOIN rol r ON u.id_rol = r.id " +
                "WHERE u.correo = ?";

        try {
            return jdbcTemplate.queryForObject(sql, String.class, correo);
        } catch (Exception e) {
            System.err.println("Error al obtener el rol para el usuario con correo '" + correo + "': " + e.getMessage());
            return null;
        }
    }

    public boolean eliminarUsuarioPorCorreo(String correo) {
        String sql = "DELETE FROM usuario WHERE correo = ?";
        int filasAfectadas = jdbcTemplate.update(sql, correo);

        return filasAfectadas > 0;
    }

    public usuario getUsuario(int userId) {
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try {
            // Utilizamos BeanPropertyRowMapper para mapear las filas de la consulta a un objeto Usuario
            return jdbcTemplate.queryForObject(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(usuario.class));
        } catch (Exception e) {
            System.err.println("No se encontró el usuario con ID '" + userId + "': " + e.getMessage());
            return null;
        }
    }

    public List<Object> obtenerTodosUsuarios() {
        String sql = "SELECT id, nombre, apellido, correo, telefono, direccion FROM usuario";
        
        try {
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
                usuario usuario = new usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                // Puedes agregar más atributos según la estructura de tu tabla
                return usuario;
            });
        } catch (Exception e) {
            System.err.println("Error al obtener todos los usuarios: " + e.getMessage());
            return null;
        }
    }
}
