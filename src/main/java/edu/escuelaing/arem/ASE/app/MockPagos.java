package edu.escuelaing.arem.ASE.app;

import edu.escuelaing.arem.ASE.app.configuration.InternalConstantValue.EstadoTrx;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MockPagos {

    public EstadoTrx mockRealizarPago() {
        // Obtener todos los valores del enum
        EstadoTrx[] estados = EstadoTrx.values();

        // Obtener un índice aleatorio
        int indiceAleatorio = new Random().nextInt(estados.length);

        // Seleccionar el estado de pago aleatorio
        EstadoTrx estadoAleatorio = estados[indiceAleatorio];

        return estadoAleatorio;
    }

}
