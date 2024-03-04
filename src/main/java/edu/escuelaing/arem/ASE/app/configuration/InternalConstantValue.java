package edu.escuelaing.arem.ASE.app.configuration;

public class InternalConstantValue {
    public enum EstadoFactura {
        ACTIVO,
        INACTIVO,
        VENCIDA,
        PAGADO,
        NO_PAGADO
    }

    public enum EstadoTrx {
        OK,
        FALLIDO,
        PAGO_TARDE,
        PENDIENTE
    }
}
