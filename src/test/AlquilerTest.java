package test;

import main.Alquiler;
import main.Parcelas;
import main.Terreno;
import main.Ubicacion;

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
        assertEquals(1, alquiler.getImporte());
    }
}
