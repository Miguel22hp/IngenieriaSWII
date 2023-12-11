package test;

import main.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ArrendatarioTest {

    Terreno terreno1;
    Arrendatario arrendatario1;
    Parcelas parcela1;
    Alquiler alquiler;
    @Before
    public void create()
    {
        terreno1 = new Terreno(1,68,
            new Ubicacion[]{new Ubicacion(2, 3), new Ubicacion(1, 4), new Ubicacion(5, 5)},
            new Ubicacion(2,3));

        parcela1 = new Parcelas(1,1,
                new Ubicacion[]{new Ubicacion(2, 3), new Ubicacion(1, 4), new Ubicacion(2, 5)},
                new Ubicacion(2,3));

        arrendatario1 = new Arrendatario("1234P",33,'M',"Avalado por el banco Santander");
    }

    @Test
    public void addAlquiler()
    {

    }

    @Test
    public void getters()
    {
        assertEquals(arrendatario1.getDni(),"1234P");
        assertEquals(arrendatario1.getEdad(),33);
        assertEquals(arrendatario1.getSexo(),'M');
        assertEquals(arrendatario1.getAval(), "Avalado por el banco Santander");
        assertArrayEquals(arrendatario1.getAlquileres().toArray(),new Integer[0]);
    }

    @Test
    public void setters()
    {
        arrendatario1.setSexo('H');
        arrendatario1.setEdad(31);
        arrendatario1.setAval("Avalado por el Banco Central Europeo");
        List<Integer> lista = new ArrayList<>();
        lista.add(3);
        lista.add(5);
        arrendatario1.setAlquileres(lista);

        assertEquals(arrendatario1.getSexo(), 'H');
        assertEquals(arrendatario1.getEdad(),31);
        assertEquals(arrendatario1.getAval(), "Avalado por el Banco Central Europeo");
        assertArrayEquals(arrendatario1.getAlquileres().toArray(),new Integer[]{3,5});
    }
}
