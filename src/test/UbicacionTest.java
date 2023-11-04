
import main.Ubicacion;
import org.junit.Test;
import static org.junit.Assert.*;
public class UbicacionTest {
    @Test
    public void testGetters() {
        Ubicacion ubicacion = new Ubicacion(3, 5);
        assertEquals(3, ubicacion.getX());
        assertEquals(5, ubicacion.getY());
    }

    @Test
    public void testSetters() {
        Ubicacion ubicacion = new Ubicacion(0, 0);
        ubicacion.setX(7);
        ubicacion.setY(9);
        assertEquals(7, ubicacion.getX());
        assertEquals(9, ubicacion.getY());
    }

}
