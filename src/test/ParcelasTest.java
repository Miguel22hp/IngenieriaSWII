package test;

import main.Parcelas;
import main.Terreno;
import main.Ubicacion;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ParcelasTest {

    Terreno terreno;
    Parcelas parcela1;
    Parcelas parcela2;
    @Before
    public void createParcelas()
    {
        terreno = new Terreno(1,68,
                new Ubicacion[]{new Ubicacion(2, 3), new Ubicacion(1, 4), new Ubicacion(5, 5)},
                new Ubicacion(2,3));
        parcela1 = new Parcelas(1,1,
                new Ubicacion[]{new Ubicacion(2, 3), new Ubicacion(1, 4), new Ubicacion(2, 5)},
                new Ubicacion(2,3));
        parcela2 = new Parcelas(2,1,
                new Ubicacion[]{new Ubicacion(3, 5), new Ubicacion(3, 3)},
                new Ubicacion(3,4));
    }

    @Test
    public void testGetters() {
        assertEquals(1,parcela1.getIdParcela());
        assertEquals(1, parcela1.getIdTerreno());
        Ubicacion[] limitesExpected =  new Ubicacion[]{new Ubicacion(2, 3), new Ubicacion(1, 4), new Ubicacion(2, 5)};
        Ubicacion[] limitesReturned = parcela1.getLimites();
        assertArrayEquals(limitesExpected, limitesReturned);
        assertEquals(new Ubicacion(2,3), parcela1.getUbicacion());
    }

    @Test
    public void testSetters() {
        parcela2.setUbicacion(new Ubicacion(3,4));
        parcela2.setLimites(new Ubicacion[]{new Ubicacion(1, 1), new Ubicacion(1, 5), new Ubicacion(5, 3)});
        assertEquals(new Ubicacion(3,4), parcela2.getUbicacion());
        assertArrayEquals(new Ubicacion[]{new Ubicacion(1, 1), new Ubicacion(1, 5), new Ubicacion(5, 3)},
                parcela2.getLimites());
    }

}
