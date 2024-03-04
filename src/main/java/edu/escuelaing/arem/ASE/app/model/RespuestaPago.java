package edu.escuelaing.arem.ASE.app.model;

public class RespuestaPago {
    private String idTransaccion;

    public RespuestaPago(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }
}