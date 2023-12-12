package test;

import main.Alquiler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlquilerTest {
    Alquiler alquiler;
    @Before
    public void createParcelas()
    {
        alquiler = new Alquiler(0, "01-01-2002", "01-01-2002", 1, 1, 2, "E12345678");
    }

    @Test
    public void testGetters() {
        assertEquals("E12345678", alquiler.getDniArrendatario());
        assertEquals(1, alquiler.getDuracion());
        assertEquals("01-01-2002", alquiler.getFechaFin());
        assertEquals("01-01-2002", alquiler.getFechaInicio());
        assertEquals(2, alquiler.getIdParcela());
        float delta = 0.0001f; // Define la tolerancia según tus necesidades
        assertEquals(1, alquiler.getImporte(),delta);
        //TODO: delta faltaba, error de código(test) encontrado en pruebas
    }
}
