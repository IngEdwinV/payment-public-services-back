package edu.escuelaing.arem.ASE.app.model;

public class ServicioModel {

    private int id;
    private String nombre;
    
    public ServicioModel() {
    }

    public ServicioModel(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }
    public  void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
