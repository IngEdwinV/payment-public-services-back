package edu.escuelaing.arem.ASE.app.excepcion;

public class Exception extends RuntimeException {

    public Exception(String message) {
        super(message);
    }

    public static class UsuarioNotFoundException extends Exception {
        public UsuarioNotFoundException(String message) {
            super(message);
        }
    }

    public static class ServicioNotFoundException extends Exception {
        public ServicioNotFoundException(String message) {
            super(message);
        }
    }

    public static class FacturaNotFoundException extends Exception {
        public FacturaNotFoundException(String message) {
            super(message);
        }
    }
}