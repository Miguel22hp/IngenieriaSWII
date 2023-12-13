package test;

import main.*;
import org.junit.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

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
        aplicacion.addArrendatario("1234P", 33, 'H',"Avalado por Banco");
    }

    @Test
    public void addTerrenoReturn() throws NoTerrenoException {
        int resultado = aplicacion.addTerreno(200,
                new Ubicacion[]{new Ubicacion(10, 10), new Ubicacion(10, 20), new Ubicacion(20, 10),
                        new Ubicacion(20, 20)},
                new Ubicacion(15, 15));
        int resultado2 = aplicacion.addTerreno(99,
                new Ubicacion[]{new Ubicacion(10, 10), new Ubicacion(10, 20), new Ubicacion(20, 10),
                        new Ubicacion(20, 20)},
                new Ubicacion(15, 15));

        assertEquals(3, resultado);
        assertEquals(4, resultado2);
    }

    @Test
public void addParcelaReturn() throws NoParcelaException, NoTerrenoException {


    int resultado = aplicacion.addParcela(1,
            new Ubicacion[]{new Ubicacion(12, 12), new Ubicacion(12, 14),
                    new Ubicacion(14, 12), new Ubicacion(14, 14)},
            new Ubicacion(13, 13));
    int resultado2 = aplicacion.addParcela(1,
            new Ubicacion[]{new Ubicacion(12, 12), new Ubicacion(12, 14),
                    new Ubicacion(14, 12), new Ubicacion(14, 14)},
            new Ubicacion(13, 13));
    int resultado3 = aplicacion.addParcela(2,
            new Ubicacion[]{new Ubicacion(12, 12), new Ubicacion(12, 14),
                    new Ubicacion(14, 12), new Ubicacion(14, 14)},
            new Ubicacion(13, 13));

    assertEquals(2, resultado);
    assertEquals(3, resultado2);
    assertEquals(4, resultado3);
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
    public  void modTerrenoNoExistente()
    {
        assertThrows(NoTerrenoException.class, () -> {
            aplicacion.modTerreno(new int[]{1,1,1},-1,null, null, 9);
        });
    }

    @Test
    public void modParcelas() throws NoParcelaException {
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
    public void modParcelasUnParametro() throws NoParcelaException {
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
    public  void modParcelaNoExistente()
    {
        assertThrows(NoParcelaException.class, () -> {
            aplicacion.modParcela(new int[]{1,1},null, null, 4);
        });
    }


    @Test
    public void getTerrenoTodosDatos() throws NoTerrenoException {
        int[] elementosADevolver = {0,0,0};
        Object[] o = aplicacion.getTerreno(elementosADevolver,1);
        assertEquals(144, o[0]);
        assertArrayEquals(new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,24), new Ubicacion(24,12),
                        new Ubicacion(24,24)}, (Ubicacion[]) o[1]);
        assertEquals(new Ubicacion(18, 18), o[2]);
    }

    @Test
    public void getTerrenoTamanoYUbicacion() throws NoTerrenoException {
        int[] elementosADevolver = {0,1,0};
        Object[] o = aplicacion.getTerreno(elementosADevolver,1);
        assertEquals(144, o[0]);
        assertNull(o[1]);
        assertEquals(new Ubicacion(18, 18), o[2]);
    }

        @Test
    public void getTerrenoLimites() throws NoTerrenoException {
        int[] elementosADevolver = {1,0,1};
        Object[] o = aplicacion.getTerreno(elementosADevolver,1);
        assertNull(o[0]);
        assertArrayEquals(new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,24), new Ubicacion(24,12),
                        new Ubicacion(24,24)}, (Ubicacion[]) o[1]);
        assertNull(o[2]);
    }

    @Test
    public void getParcelasTodosDatos() throws NoParcelaException {
        int[] elementosADevolver = {0,0};
        Object[] o = aplicacion.getParcela(elementosADevolver,1);
        assertArrayEquals(new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,14),
                new Ubicacion(14,12), new Ubicacion(14,14)}, (Ubicacion[]) o[0]);
        assertEquals(new Ubicacion(13, 13), o[1]);
    }

    @Test
    public void getParcelasLimites() throws NoParcelaException {
        int[] elementosADevolver = {0,1};
        Object[] o = aplicacion.getParcela(elementosADevolver,1);
        assertArrayEquals(new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,14),
                new Ubicacion(14,12), new Ubicacion(14,14)}, (Ubicacion[]) o[0]);
        assertNull(o[1]);
    }

    @Test
    public void getParcelasUbicacion() throws NoParcelaException {
        int[] elementosADevolver = {1,0};
        Object[] o = aplicacion.getParcela(elementosADevolver,1);
        assertNull(o[0]);
        assertEquals(new Ubicacion(13, 13), o[1]);
    }

    @Test
    public void getTerrenoNoExistente()
    {
        assertThrows(NoTerrenoException.class, () -> {
            aplicacion.getTerreno(new int[]{1,1,1}, 9);
        });
    }

    @Test
    public void getParcelaNoExistente()
    {
        assertThrows(NoParcelaException.class, () -> {
            aplicacion.getParcela(new int[]{1,1}, 9);
        });
    }

    @Test
    public void anadirArrendatarioYaEnElSistema()
    {
        assertEquals(aplicacion.addArrendatario("1234P", 33, 'H',"Avalado por Banco"), null);
    }

    @Test
    public void anadirArrendatario()
    {
        assertEquals(aplicacion.addArrendatario("1235P", 33, 'H',"Avalado por Banco"), "1235P");
    }

    @Test
    public void borrarArrendatarioYaEnElSistema()
    {
        assertEquals(aplicacion.removeArrendatario("1234P"),"1234P");
    }

    @Test
    public void borrarArrendatarioNoEnElSistema()
    {
        assertEquals(aplicacion.removeArrendatario("1235P"),null);
    }


    @Test
    public void modificarArrendatario()
    {

        aplicacion.modArrendatario(new int[]{0,0,0},"1234P",21,'M',"Aval");
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getEdad(),21);
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getSexo(),'M');
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getAval(),"Aval");

    }

    @Test
    public void modificarParametrosSueltosArrendatario()
    {
        aplicacion.modArrendatario(new int[]{0,1,1},"1234P",21,'H',"Aval");
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getEdad(),21);
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getSexo(),'H');
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getAval(),"Avalado por Banco");

        aplicacion.modArrendatario(new int[]{1,0,1},"1234P",13,'M',"Aval");
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getEdad(),21);
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getSexo(),'M');
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getAval(),"Avalado por Banco");

        aplicacion.modArrendatario(new int[]{1,1,0},"1234P",13,'M',"Aval");
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getEdad(),21);
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getSexo(),'M');
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getAval(),"Aval");

        aplicacion.modArrendatario(new int[]{0,0,1},"1234P",28,'H',"Aval2");
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getEdad(),28);
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getSexo(),'H');
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getAval(),"Aval");

        aplicacion.modArrendatario(new int[]{1,0,0},"1234P",13,'M',"Aval2");
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getEdad(),28);
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getSexo(),'M');
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getAval(),"Aval2");

        aplicacion.modArrendatario(new int[]{0,1,0},"1234P",13,'M',"Aval3");
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getEdad(),13);
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getSexo(),'M');
        assertEquals(aplicacion.getListaArrendatarios().get("1234P").getAval(),"Aval3");

    }

    @Test
    public void testGenerarRecibos() throws NoParcelaException, NoArrendatarioException, NoTerrenoException {
        aplicacion.addParcela(1, new Ubicacion[]{new Ubicacion(16,16), new Ubicacion(12,14),
                new Ubicacion(14,12), new Ubicacion(14,14)}, new Ubicacion(50,50));
        aplicacion.addParcela(1, new Ubicacion[]{new Ubicacion(12,12), new Ubicacion(12,14),
                new Ubicacion(14,12), new Ubicacion(14,14)}, new Ubicacion(99,99));
        assertEquals(aplicacion.addArrendatario("1235P", 33, 'H',"Avalado por Banco"), "1235P");
        aplicacion.addAlquiler("01-01-2002", "01-01-2002", 1, 1, 1, "1235P");
        aplicacion.addAlquiler("01-01-2002", "01-01-2002", 1, 1, 2, "1235P");

        // Llamar al método generarRecibos con valores de prueba
        int recibosGenerados = aplicacion.generarRecibos("IVA", 5f);

        // Verificar que el método devuelve el resultado esperado
        assertEquals(2, recibosGenerados);
        // Verificar que los recibos se hayan generado y almacenado correctamente
        assertNotNull(aplicacion.getListaRecibos().get(1));
        assertNotNull(aplicacion.getListaRecibos().get(2));

        Set<Integer> alquileres = aplicacion.getListaAlquileres().keySet();
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        // Formatear la fecha y hora según tus preferencias
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaHoraActual.format(formato);

        String resultadoEsperado = "Recibo{numRecibo=1, fechaEmision='" + fechaFormateada + "', importeAlquiler=1.0, tipoImpuesto='IVA', impuesto=5.0, pagado=false, idAlquiler=1}";
        assertEquals(aplicacion.getListaRecibos().get(1).toString(),resultadoEsperado);
        String resultadoEsperado2 = "Recibo{numRecibo=2, fechaEmision='" + fechaFormateada + "', importeAlquiler=1.0, tipoImpuesto='IVA', impuesto=5.0, pagado=false, idAlquiler=2}";
        assertEquals(aplicacion.getListaRecibos().get(2).toString(),resultadoEsperado2);
    }

    @Test
    public void testGenerarRecibosSinAlquileres() throws NoParcelaException, NoArrendatarioException, NoTerrenoException {
        assertEquals(aplicacion.generarRecibos("IVA", 5f), 0);
    }

    //TODO:crear recibo tests y alquiler

    @Test
    public void testAlquilerDeArrendatario() throws NoParcelaException, NoArrendatarioException {
        aplicacion.addArrendatario("1235P", 33, 'H',"Avalado por Banco");
        int id = aplicacion.addAlquiler("01-01-2002", "01-01-2002", 1, 1, 1, "1235P");
        Alquiler a = new Alquiler(1,"01-01-2002", "01-01-2002", 1, 1, 1, "1235P");
        assertEquals(Optional.of(aplicacion.getListaAlquileres().get(id).getId()), Optional.of(aplicacion.getListaArrendatarios().get("1235P").getAlquileres().get(0)));
        assertEquals(aplicacion.getListaAlquileres().get(id).getId(),id);
        System.out.println();
        assertEquals(a.toString(),aplicacion.getListaAlquileres().get(id).toString());
    }

    @Test
    public void anadirAlquilerSinParcelaNiArrendatatrio() throws NoParcelaException, NoArrendatarioException {
        assertThrows(NoArrendatarioException.class, () -> {
            aplicacion.addAlquiler("01-01-2002", "01-01-2002", 1, 1, 1, "1235P");
        });
        assertThrows(NoParcelaException.class, () -> {
            aplicacion.addAlquiler("01-01-2002", "01-01-2002", 1, 1, 99, "1235P");
        });
    }

    @Test
    public void removeAlquiler()  throws NoParcelaException, NoArrendatarioException {
        aplicacion.addArrendatario("1235P", 33, 'H',"Avalado por Banco");
        int id = aplicacion.addAlquiler("01-01-2002", "01-01-2002", 1, 1, 1, "1235P");
        assertEquals(1, aplicacion.getListaAlquileres().size());
        aplicacion.removeAlquiler(id);
        assertEquals(0, aplicacion.getListaAlquileres().size());
    }
    @Test
    public void testPagarRecibo() throws NoTerrenoException, NoParcelaException, NoArrendatarioException {
        aplicacion.addArrendatario("1235P", 33, 'H',"Avalado por Banco");
        aplicacion.addParcela(1, new Ubicacion[]{new Ubicacion(16,16), new Ubicacion(12,14),
                new Ubicacion(14,12), new Ubicacion(14,14)}, new Ubicacion(50,50));
        aplicacion.addAlquiler("01-01-2002", "01-01-2002", 1, 1, 1, "1235P");

        aplicacion.generarRecibos("IVA", 5f);
        assertTrue(aplicacion.pagarRecibo(aplicacion.getListaRecibos().get(1).getNumRecibo()));
        assertTrue(aplicacion.getListaRecibos().get(1).isPagado());

        assertFalse(aplicacion.pagarRecibo(3));
    }

}
