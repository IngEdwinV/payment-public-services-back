package edu.escuelaing.arem.ASE.app.model;


public class usuario {
    static int id;
    static String nombre;
    static String apellido;
    static String password;
    static String correo;
    static String telefono;
    static String direccion;
    static int id_rol;

    public usuario (int id, String nombre, String apellido, String password, String correo, String telefono, String direccion, int id_rol) {
        usuario.id = id;
        usuario.nombre = nombre;
        usuario.apellido = apellido;
        usuario.password = password;
        usuario.correo = correo;
        usuario.telefono = telefono;
        usuario.direccion = direccion;
        usuario.id_rol = id_rol;
    }

    // Getters
    public static int getId() {
        return id;
    }

    public static String getNombre() {
        return nombre;
    }

    public static String getApellido() {
        return apellido;
    }

    public static String getPassword() {
        return password;
    }

    public static String getCorreo() {
        return correo;
    }

    public static String getTelefono() {
        return telefono;
    }

    public static String getDireccion() {
        return direccion;
    }

    public static int getIdRol() {
        return id_rol;
    }

    // Setters
    public void setId(int id) {
        usuario.id = id;
    }

    public void setNombre(String nombre) {
        usuario.nombre = nombre;
    }

    public void setApellido(String apellido) {
        usuario.apellido = apellido;
    }

    public void setPassword(String password) {
        usuario.password = password;
    }

    public void setCorreo(String correo) {
        usuario.correo = correo;
    }

    public void setTelefono(String telefono) {
        usuario.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        usuario.direccion = direccion;
    }

    public void setIdRol(int id_rol) {
        usuario.id_rol = id_rol;
    }

}
