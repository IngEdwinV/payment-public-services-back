package edu.escuelaing.arem.ASE.app.model;


public class usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String password;
    private String correo;
    private String telefono;
    private String direccion;
    private int id_rol;

    public usuario(){
        
    }

    public usuario (int id, String nombre, String apellido, String password, String correo, String telefono, String direccion, int id_rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.id_rol = id_rol;
    }

    // Getters
    public  int getId() {
        return id;
    }

    public  String getNombre() {
        return nombre;
    }

    public  String getApellido() {
        return apellido;
    }

    public  String getPassword() {
        return password;
    }

    public  String getCorreo() {
        return correo;
    }

    public  String getTelefono() {
        return telefono;
    }

    public  String getDireccion() {
        return direccion;
    }

    public  int getIdRol() {
        return id_rol;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setIdRol(int id_rol) {
        this.id_rol = id_rol;
    }

}
