package edu.escuelaing.arem.ASE.app.model;

import edu.escuelaing.arem.ASE.app.configuration.InternalConstantValue.EstadoFactura;

import java.math.BigDecimal;
import java.util.Date;

public class Factura {

    private int idUsuario;
    private int idServicio;
    private BigDecimal valor;
    private EstadoFactura estado;
    private Date fechaFactura;
    private int idTrx;

    public int getIdTrx() {
        return idTrx;
    }

    // Constructor por defecto
    public Factura() {
    }

    // Constructor con parámetros
    public Factura(int idUsuario, int idServicio, BigDecimal valor, EstadoFactura estado, Date fechaFactura) {
        this.idUsuario = idUsuario;
        this.idServicio = idServicio;
        this.valor = valor;
        this.estado = estado;
        this.fechaFactura = fechaFactura;
    }

    // Getters y setters

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public EstadoFactura getEstado() {
        return estado;
    }

    public void setEstado(EstadoFactura estado) {
        this.estado = estado;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    // Puedes agregar otros métodos o validaciones según tus necesidades

    @Override
    public String toString() {
        return "Factura{" +
                ", idUsuario=" + idUsuario +
                ", idServicio=" + idServicio +
                ", valor=" + valor +
                ", estado='" + estado + '\'' +
                ", fechaFactura=" + fechaFactura +
                ", idTrx=" + idTrx +
                '}';
    }
}

