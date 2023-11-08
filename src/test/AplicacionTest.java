package test;

import main.*;
import org.junit.*;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class AplicacionTest {
    Aplicacion aplicacion;

    @Before
    public void createApp() throws NoTerrenoException
    {
        this.aplicacion = new Aplicacion();
        aplicacion.addTerreno(144,
                new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,24), new Ubicacion(24,12),
                        new Ubicacion(24,24)}
                ,new Ubicacion(18,18));
        aplicacion.addTerreno(100,
                new Ubicacion[]{new Ubicacion(0,0), new Ubicacion(10,0), new Ubicacion(0,10),
                        new Ubicacion(10,10)}
                ,new Ubicacion(5,5));
        aplicacion.addParcela(1, new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,14),
                new Ubicacion(14,12), new Ubicacion(14,14)}, new Ubicacion(13,13));
    }

    @Test
    public void addTerrenoYParcela()
    {
        HashMap<Integer,Terreno> expectedListaTerrenos = new HashMap<>();
        expectedListaTerrenos.put(1,new Terreno(1,144,
                new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,24), new Ubicacion(24,12),
                        new Ubicacion(24,24)}
                ,new Ubicacion(18,18)));
        expectedListaTerrenos.put(2,new Terreno(2,100,
                new Ubicacion[]{new Ubicacion(0,0), new Ubicacion(10,0), new Ubicacion(0,10),
                        new Ubicacion(10,10)}
                ,new Ubicacion(5,5)));
        expectedListaTerrenos.get(1).addParcela(new Parcelas(1,1, new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,14),
                new Ubicacion(14,12), new Ubicacion(14,14)}, new Ubicacion(13,13)));

        assertEquals(expectedListaTerrenos.size(), aplicacion.getListaTerrenos().size());
        for (Integer key : expectedListaTerrenos.keySet())
        {
            Terreno expectedTerreno = expectedListaTerrenos.get(key);

            // Verifica si el mapa actual contiene la clave
            assertTrue(aplicacion.getListaTerrenos().containsKey(key));

            // Compara los valores

            Terreno terr = aplicacion.getListaTerrenos().get(key);
            assertEquals(expectedTerreno.getId(), terr.getId());
            assertEquals(expectedTerreno.getTamano(), terr.getTamano());
            assertArrayEquals(expectedTerreno.getLimites(), terr.getLimites());
            assertEquals(expectedTerreno.getUbicacion(), terr.getUbicacion());
            for (Integer key2 : expectedTerreno.getParcelasTerreno().keySet())
            {
                Parcelas expectedValue = expectedTerreno.getParcelasTerreno().get(key2);

                // Verifica si el mapa actual contiene la clave
                assertTrue(terr.getParcelasTerreno().containsKey(key2));

                // Compara los valores
                Parcelas actualValue = terr.getParcelasTerreno().get(key2);
                assertEquals(expectedValue.getIdParcela(),actualValue.getIdParcela());
                assertEquals(expectedValue.getIdTerreno(), actualValue.getIdTerreno());
                assertArrayEquals(expectedValue.getLimites(), actualValue.getLimites());
                assertEquals(expectedValue.getUbicacion(), actualValue.getUbicacion());
            }
        }
    }

    @Test
    public void addParcelaSinTerreno()
    {
        assertThrows(NoTerrenoException.class, () -> {
            aplicacion.addParcela(4, new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,14),
                new Ubicacion(14,12), new Ubicacion(14,14)}, new Ubicacion(13,13));
        });
    }

    @Test
    public void removeTerrenoYParcela()
    {
        //Hasta aquí se comprueba que tiene
        HashMap<Integer,Terreno> expectedListaTerrenos = new HashMap<>();
        expectedListaTerrenos.put(1,new Terreno(1,144,
                new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,24), new Ubicacion(24,12),
                        new Ubicacion(24,24)}
                ,new Ubicacion(18,18)));


        //borra terreno de aplicacion
        aplicacion.removeTerreno(2);
        aplicacion.removeParcela(1);

        //Comprueba lo esperado y lo obtenido
        assertEquals(expectedListaTerrenos.size(), aplicacion.getListaTerrenos().size());
        for (Integer key : expectedListaTerrenos.keySet())
        {
            Terreno expectedTerreno = expectedListaTerrenos.get(key);

            // Verifica si el mapa actual contiene la clave
            assertTrue(aplicacion.getListaTerrenos().containsKey(key));

            // Compara los valores

            Terreno terr = aplicacion.getListaTerrenos().get(key);
            assertEquals(expectedTerreno.getId(), terr.getId());
            assertEquals(expectedTerreno.getTamano(), terr.getTamano());
            assertArrayEquals(expectedTerreno.getLimites(), terr.getLimites());
            assertEquals(expectedTerreno.getUbicacion(), terr.getUbicacion());
            for (Integer key2 : expectedTerreno.getParcelasTerreno().keySet())
            {
                Parcelas expectedValue = expectedTerreno.getParcelasTerreno().get(key2);

                // Verifica si el mapa actual contiene la clave
                assertTrue(terr.getParcelasTerreno().containsKey(key2));

                // Compara los valores
                Parcelas actualValue = terr.getParcelasTerreno().get(key2);
                assertEquals(expectedValue.getIdParcela(),actualValue.getIdParcela());
                assertEquals(expectedValue.getIdTerreno(), actualValue.getIdTerreno());
                assertArrayEquals(expectedValue.getLimites(), actualValue.getLimites());
                assertEquals(expectedValue.getUbicacion(), actualValue.getUbicacion());
            }
        }
    }

    @Test
    public void modTerrenoTamano() throws NoTerrenoException {
        //Modifica tamaño terreno
        HashMap<Integer,Terreno> expectedListaTerrenos = new HashMap<>();
        expectedListaTerrenos.put(1,new Terreno(1,141,
                new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,24), new Ubicacion(24,12),
                        new Ubicacion(24,24)}
                ,new Ubicacion(18,18)));
        expectedListaTerrenos.put(2,new Terreno(2,100,
                new Ubicacion[]{new Ubicacion(0,0), new Ubicacion(10,0), new Ubicacion(0,10),
                        new Ubicacion(10,10)}
                ,new Ubicacion(5,5)));
        expectedListaTerrenos.get(1).addParcela(new Parcelas(1,1, new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,14),
                new Ubicacion(14,12), new Ubicacion(14,14)}, new Ubicacion(13,13)));

        //Modificacion terrenos almacenados por app
        int[] mod = {0,1,1};
        aplicacion.modTerreno(mod,141,null, null, 1);

        //Checkeo de las modificaciones
        assertEquals(expectedListaTerrenos.size(), aplicacion.getListaTerrenos().size());
        for (Integer key : expectedListaTerrenos.keySet())
        {
            Terreno expectedTerreno = expectedListaTerrenos.get(key);

            // Verifica si el mapa actual contiene la clave
            assertTrue(aplicacion.getListaTerrenos().containsKey(key));

            // Compara los valores

            Terreno terr = aplicacion.getListaTerrenos().get(key);
            assertEquals(expectedTerreno.getId(), terr.getId());
            assertEquals(expectedTerreno.getTamano(), terr.getTamano());
            assertArrayEquals(expectedTerreno.getLimites(), terr.getLimites());
            assertEquals(expectedTerreno.getUbicacion(), terr.getUbicacion());
            for (Integer key2 : expectedTerreno.getParcelasTerreno().keySet())
            {
                Parcelas expectedValue = expectedTerreno.getParcelasTerreno().get(key2);

                // Verifica si el mapa actual contiene la clave
                assertTrue(terr.getParcelasTerreno().containsKey(key2));

                // Compara los valores
                Parcelas actualValue = terr.getParcelasTerreno().get(key2);
                assertEquals(expectedValue.getIdParcela(),actualValue.getIdParcela());
                assertEquals(expectedValue.getIdTerreno(), actualValue.getIdTerreno());
                assertArrayEquals(expectedValue.getLimites(), actualValue.getLimites());
                assertEquals(expectedValue.getUbicacion(), actualValue.getUbicacion());
            }
        }
    }

    @Test
    public void modTerrenoLimitesYUbicacion() throws NoTerrenoException {
        //Modifica tamaño terreno
        HashMap<Integer,Terreno> expectedListaTerrenos = new HashMap<>();
        expectedListaTerrenos.put(1,new Terreno(1,144,
                new Ubicacion[]{new Ubicacion(12,11), new Ubicacion(12,24), new Ubicacion(24,12),
                        new Ubicacion(24,23)}
                ,new Ubicacion(18,16)));
        expectedListaTerrenos.put(2,new Terreno(2,100,
                new Ubicacion[]{new Ubicacion(0,0), new Ubicacion(10,0), new Ubicacion(0,10),
                        new Ubicacion(10,10)}
                ,new Ubicacion(5,5)));
        expectedListaTerrenos.get(1).addParcela(new Parcelas(1,1, new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,14),
                new Ubicacion(14,12), new Ubicacion(14,14)}, new Ubicacion(13,13)));

        //Modificacion terrenos almacenados por app
        int[] mod = {1,0,0};
        aplicacion.modTerreno(mod,-1,new Ubicacion[]{new Ubicacion(12,11), new Ubicacion(12,24),
                new Ubicacion(24,12),new Ubicacion(24,23)}, new Ubicacion(18,16), 1);

        //Checkeo de las modificaciones
        assertEquals(expectedListaTerrenos.size(), aplicacion.getListaTerrenos().size());
        for (Integer key : expectedListaTerrenos.keySet())
        {
            Terreno expectedTerreno = expectedListaTerrenos.get(key);

            // Verifica si el mapa actual contiene la clave
            assertTrue(aplicacion.getListaTerrenos().containsKey(key));

            // Compara los valores

            Terreno terr = aplicacion.getListaTerrenos().get(key);
            assertEquals(expectedTerreno.getId(), terr.getId());
            assertEquals(expectedTerreno.getTamano(), terr.getTamano());
            assertArrayEquals(expectedTerreno.getLimites(), terr.getLimites());
            assertEquals(expectedTerreno.getUbicacion(), terr.getUbicacion());
            for (Integer key2 : expectedTerreno.getParcelasTerreno().keySet())
            {
                Parcelas expectedValue = expectedTerreno.getParcelasTerreno().get(key2);

                // Verifica si el mapa actual contiene la clave
                assertTrue(terr.getParcelasTerreno().containsKey(key2));

                // Compara los valores
                Parcelas actualValue = terr.getParcelasTerreno().get(key2);
                assertEquals(expectedValue.getIdParcela(),actualValue.getIdParcela());
                assertEquals(expectedValue.getIdTerreno(), actualValue.getIdTerreno());
                assertArrayEquals(expectedValue.getLimites(), actualValue.getLimites());
                assertEquals(expectedValue.getUbicacion(), actualValue.getUbicacion());
            }
        }
    }

    @Test
    public void modParcelas()
    {
        //Modifica tamaño terreno
        HashMap<Integer,Terreno> expectedListaTerrenos = new HashMap<>();
        expectedListaTerrenos.put(1,new Terreno(1,144,
                new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,24), new Ubicacion(24,12),
                        new Ubicacion(24,24)}
                ,new Ubicacion(18,18)));
        expectedListaTerrenos.put(2,new Terreno(2,100,
                new Ubicacion[]{new Ubicacion(0,0), new Ubicacion(10,0), new Ubicacion(0,10),
                        new Ubicacion(10,10)}
                ,new Ubicacion(5,5)));
        expectedListaTerrenos.get(1).addParcela(new Parcelas(1,1, new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,14),
                new Ubicacion(14,12), new Ubicacion(14,16)}, new Ubicacion(12,14)));

        //Modificacion terrenos almacenados por app
        int[] mod = {0,0};
        aplicacion.modParcela(mod,new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,14),
                new Ubicacion(14,12), new Ubicacion(14,16)}, new Ubicacion(12,14), 1);

        //Checkeo de las modificaciones
        assertEquals(expectedListaTerrenos.size(), aplicacion.getListaTerrenos().size());
        for (Integer key : expectedListaTerrenos.keySet())
        {
            Terreno expectedTerreno = expectedListaTerrenos.get(key);

            // Verifica si el mapa actual contiene la clave
            assertTrue(aplicacion.getListaTerrenos().containsKey(key));

            // Compara los valores

            Terreno terr = aplicacion.getListaTerrenos().get(key);
            assertEquals(expectedTerreno.getId(), terr.getId());
            assertEquals(expectedTerreno.getTamano(), terr.getTamano());
            assertArrayEquals(expectedTerreno.getLimites(), terr.getLimites());
            assertEquals(expectedTerreno.getUbicacion(), terr.getUbicacion());
            for (Integer key2 : expectedTerreno.getParcelasTerreno().keySet())
            {
                Parcelas expectedValue = expectedTerreno.getParcelasTerreno().get(key2);

                // Verifica si el mapa actual contiene la clave
                assertTrue(terr.getParcelasTerreno().containsKey(key2));

                // Compara los valores
                Parcelas actualValue = terr.getParcelasTerreno().get(key2);
                assertEquals(expectedValue.getIdParcela(),actualValue.getIdParcela());
                assertEquals(expectedValue.getIdTerreno(), actualValue.getIdTerreno());
                assertArrayEquals(expectedValue.getLimites(), actualValue.getLimites());
                assertEquals(expectedValue.getUbicacion(), actualValue.getUbicacion());
            }
        }
    }

    @Test
    public void modParcelasUnParametro()
    {
        //Modifica tamaño terreno
        HashMap<Integer, Terreno> expectedListaTerrenos = new HashMap<>();
        expectedListaTerrenos.put(1, new Terreno(1, 144,
                new Ubicacion[]{new Ubicacion(12, 12), new Ubicacion(12, 24), new Ubicacion(24, 12),
                        new Ubicacion(24, 24)}
                , new Ubicacion(18, 18)));
        expectedListaTerrenos.put(2, new Terreno(2, 100,
                new Ubicacion[]{new Ubicacion(0, 0), new Ubicacion(10, 0), new Ubicacion(0, 10),
                        new Ubicacion(10, 10)}
                , new Ubicacion(5, 5)));
        expectedListaTerrenos.get(1).addParcela(new Parcelas(1, 1, new Ubicacion[]{new Ubicacion(12, 12), new Ubicacion(12, 14),
                new Ubicacion(14, 12), new Ubicacion(14, 16)}, new Ubicacion(13, 13)));

        //Modificacion terrenos almacenados por app
        int[] mod = {0, 1};
        aplicacion.modParcela(mod, new Ubicacion[]{new Ubicacion(12, 12), new Ubicacion(12, 14),
                new Ubicacion(14, 12), new Ubicacion(14, 16)}, null, 1);

        //Checkeo de las modificaciones
        assertEquals(expectedListaTerrenos.size(), aplicacion.getListaTerrenos().size());
        for (Integer key : expectedListaTerrenos.keySet()) {
            Terreno expectedTerreno = expectedListaTerrenos.get(key);

            // Verifica si el mapa actual contiene la clave
            assertTrue(aplicacion.getListaTerrenos().containsKey(key));

            // Compara los valores

            Terreno terr = aplicacion.getListaTerrenos().get(key);
            assertEquals(expectedTerreno.getId(), terr.getId());
            assertEquals(expectedTerreno.getTamano(), terr.getTamano());
            assertArrayEquals(expectedTerreno.getLimites(), terr.getLimites());
            assertEquals(expectedTerreno.getUbicacion(), terr.getUbicacion());
            for (Integer key2 : expectedTerreno.getParcelasTerreno().keySet()) {
                Parcelas expectedValue = expectedTerreno.getParcelasTerreno().get(key2);

                // Verifica si el mapa actual contiene la clave
                assertTrue(terr.getParcelasTerreno().containsKey(key2));

                // Compara los valores
                Parcelas actualValue = terr.getParcelasTerreno().get(key2);
                assertEquals(expectedValue.getIdParcela(), actualValue.getIdParcela());
                assertEquals(expectedValue.getIdTerreno(), actualValue.getIdTerreno());
                assertArrayEquals(expectedValue.getLimites(), actualValue.getLimites());
                assertEquals(expectedValue.getUbicacion(), actualValue.getUbicacion());
            }
        }
    }

    @Test
    public void getTerrenoTodosDatos()
    {
        int[] elementosADevolver = {0,0,0};
        Object[] o = aplicacion.getTerreno(elementosADevolver,1);
        assertEquals(144, o[0]);
        assertArrayEquals(new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,24), new Ubicacion(24,12),
                        new Ubicacion(24,24)}, (Ubicacion[]) o[1]);
        assertEquals(new Ubicacion(18, 18), o[2]);
    }

    @Test
    public void getTerrenoTamanoYUbicacion()
    {
        int[] elementosADevolver = {0,1,0};
        Object[] o = aplicacion.getTerreno(elementosADevolver,1);
        assertEquals(144, o[0]);
        assertNull(o[1]);
        assertEquals(new Ubicacion(18, 18), o[2]);
    }

        @Test
    public void getTerrenoLimites()
    {
        int[] elementosADevolver = {1,0,1};
        Object[] o = aplicacion.getTerreno(elementosADevolver,1);
        assertNull(o[0]);
        assertArrayEquals(new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,24), new Ubicacion(24,12),
                        new Ubicacion(24,24)}, (Ubicacion[]) o[1]);
        assertNull(o[2]);
    }

    @Test
    public void getParcelasTodosDatos()
    {
        int[] elementosADevolver = {0,0};
        Object[] o = aplicacion.getParcela(elementosADevolver,1);
        assertArrayEquals(new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,14),
                new Ubicacion(14,12), new Ubicacion(14,14)}, (Ubicacion[]) o[0]);
        assertEquals(new Ubicacion(13, 13), o[1]);
    }

    @Test
    public void getParcelasLimites()
    {
        int[] elementosADevolver = {0,1};
        Object[] o = aplicacion.getParcela(elementosADevolver,1);
        assertArrayEquals(new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,14),
                new Ubicacion(14,12), new Ubicacion(14,14)}, (Ubicacion[]) o[0]);
        assertNull(o[1]);
    }

    @Test
    public void getParcelasUbicacion()
    {
        int[] elementosADevolver = {1,0};
        Object[] o = aplicacion.getParcela(elementosADevolver,1);
        assertNull(o[0]);
        assertEquals(new Ubicacion(13, 13), o[1]);
    }

    //TODO:Comprobar que saltan las nuevas excepciones


}
