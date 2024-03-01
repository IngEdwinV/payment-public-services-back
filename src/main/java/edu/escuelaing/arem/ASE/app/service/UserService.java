package edu.escuelaing.arem.ASE.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public void create(usuario user){
        String sql = "INSERT INTO usuario (id, nombre, apellido, password, correo, telefono, direccion, id_rol) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setInt(1, usuario.getId());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellido());
            preparedStatement.setString(4, usuario.getPassword());
            preparedStatement.setString(5, usuario.getCorreo());
            preparedStatement.setString(6, usuario.getTelefono());
            preparedStatement.setString(7, usuario.getDireccion());
            preparedStatement.setInt(8, usuario.getIdRol());
        });
    }

    public boolean login (String correo, String password){
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
}
