package edu.escuelaing.arem.ASE.app.model;

public class ServicioModel {

    private String nombre;
    public ServicioModel(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public  void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
