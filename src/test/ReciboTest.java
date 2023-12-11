package test;

import main.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReciboTest {

    Recibo recibo;
    @Before
    public void create()
    {
        recibo= new Recibo(1,"2023-11-09", 121.1F,"IVA",13.1F,false,1);
    }

    @Test
    public void getters()
    {
        assertEquals(recibo.getNumRecibo(),1);
        assertEquals(recibo.getFechaEmision(),"2023-11-09");
        assertEquals(recibo.getIdAlquiler(),1);
        assertEquals(recibo.getTipoImpuesto(),"IVA");
        float delta = 0.0001f; // Define la tolerancia según tus necesidades
        assertEquals(recibo.getImpuesto(), 13.1, delta);
        assertEquals(recibo.getImporteAlquiler(),121.1, delta);
        assertEquals(recibo.isPagado(),false);
    }

    @Test
    public void pagarRecibo()
    {
        recibo.pagado();
        assertEquals(recibo.isPagado(),true);
    }


}
