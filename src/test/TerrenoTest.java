package test;

import main.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TerrenoTest {

    Terreno terreno1;
    Terreno terreno2;
    Parcelas parcela1;
    Parcelas parcela2;

    @Before
    public void createTerreno()
    {
        terreno1 = new Terreno(1,68,
                new Ubicacion[]{new Ubicacion(2, 3), new Ubicacion(1, 4), new Ubicacion(5, 5)},
                new Ubicacion(2,3));

        terreno2 = new Terreno(2,150,
                new Ubicacion[]{new Ubicacion(6, 6), new Ubicacion(6, 18), new Ubicacion(18, 6), new Ubicacion(18, 18)},
                new Ubicacion(12,12));

        parcela1 = new Parcelas(1,1,
                new Ubicacion[]{new Ubicacion(2, 3), new Ubicacion(1, 4), new Ubicacion(2, 5)},
                new Ubicacion(2,3));
        parcela2 = new Parcelas(2,1,
                new Ubicacion[]{new Ubicacion(3, 5), new Ubicacion(3, 3)},
                new Ubicacion(3,4));

        terreno1.addParcela(parcela1);
        terreno1.addParcela(parcela2);

        HashMap<Integer, Parcelas> map = new HashMap<>();
        map.put(parcela1.getIdParcela(), parcela1);
        map.put(parcela2.getIdParcela(), parcela2);
        terreno2.setParcelasTerreno(map);
    }

    @Test
    public void getTerrenosInfo()
    {
        assertEquals(1, terreno1.getId());
        assertEquals(68,terreno1.getTamano());
        assertArrayEquals( new Ubicacion[]{new Ubicacion(2, 3), new Ubicacion(1, 4), new Ubicacion(5, 5)}, terreno1.getLimites());
        assertEquals(new Ubicacion(2,3), parcela1.getUbicacion());
        HashMap<Integer, Parcelas> expectedParcelasInTerreno = new HashMap<>();
        expectedParcelasInTerreno.put(1,parcela1);
        expectedParcelasInTerreno.put(2,parcela2);
        assertEquals(expectedParcelasInTerreno.size(),terreno1.getParcelasTerreno().size());

        for (Integer key : expectedParcelasInTerreno.keySet())
        {
            Parcelas expectedValue = expectedParcelasInTerreno.get(key);

            // Verifica si el mapa actual contiene la clave
            assertTrue(terreno1.getParcelasTerreno().containsKey(key));

            // Compara los valores
            Parcelas actualValue = terreno1.getParcelasTerreno().get(key);
            assertEquals(expectedValue, actualValue);
        }
    }

    @Test
    public void setTerrenosInfo()
    {
        Terreno terreno3 = new Terreno(3,0,
                new Ubicacion[]{},
                new Ubicacion(0,0));

        terreno3.setTamano(terreno2.getTamano());
        terreno3.setUbicacion(terreno2.getUbicacion());
        terreno3.setLimites(terreno2.getLimites());
        terreno3.setParcelasTerreno(terreno2.getParcelasTerreno());

        assertNotEquals(terreno3.getId(), terreno2.getId());
        assertEquals(terreno3.getTamano(), terreno2.getTamano());
        assertArrayEquals(terreno3.getLimites(), terreno2.getLimites());
        assertEquals(terreno3.getUbicacion(), terreno2.getUbicacion());
        assertEquals(terreno3.getParcelasTerreno().size(), terreno2.getParcelasTerreno().size());
        for (Integer key : terreno3.getParcelasTerreno().keySet())
        {
            Parcelas expectedValue = terreno3.getParcelasTerreno().get(key);

            // Verifica si el mapa actual contiene la clave
            assertTrue(terreno2.getParcelasTerreno().containsKey(key));

            // Compara los valores
            Parcelas actualValue = terreno2.getParcelasTerreno().get(key);
            assertEquals(expectedValue, actualValue);
        }
    }

    @Test
    public void addAndRemoveParcelas()
    {
        Terreno terreno3 = new Terreno(3,0,
                new Ubicacion[]{},
                new Ubicacion(0,0));

        terreno3.addParcela(parcela1);
        terreno3.addParcela(parcela2);

        assertEquals(terreno3.getParcelasTerreno().size(), terreno2.getParcelasTerreno().size());
        for (Integer key : terreno3.getParcelasTerreno().keySet())
        {
            Parcelas expectedValue = terreno3.getParcelasTerreno().get(key);

            // Verifica si el mapa actual contiene la clave
            assertTrue(terreno2.getParcelasTerreno().containsKey(key));

            // Compara los valores
            Parcelas actualValue = terreno2.getParcelasTerreno().get(key);
            assertEquals(expectedValue, actualValue);
        }

        terreno3.borrarParcela(1);
        terreno3.borrarParcela(2);
        assertEquals(0, terreno3.getParcelasTerreno().size());


    }
}
