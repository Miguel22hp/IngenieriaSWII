package main;

import java.util.Objects;

public class Ubicacion
{
    int x;
    int y;

    /**
     * Crea una ubicación definida por dos coordenadas
     * @param x coordenada x de la ubicación definido
     * @param y coordenada y de la ubicación definido
     */
    public Ubicacion (int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Establece la coordenada x de la ubicación.
     * @param x La nueva coordenada x de la ubicación.
     */
    public void setX(int x) { this.x = x; }

    /**
     * Establece la coordenada y de la ubicación.
     * @param y La nueva coordenada y de la ubicación.
     */
    public void setY(int y) { this.y = y; }

    /**
     * Obtiene la coordenada x de la ubicación.
     * @return La coordenada x de la ubicación.
     */
    public int getX() { return x; }

    /**
     * Obtiene la coordenada y de la ubicación.
     * @return La coordenada y de la ubicación.
     */
    public int getY(){ return y; }

    /**
     * Compara la ubicación con otro objeto para verificar la igualdad.
     * @param o El objeto con el que se compara la ubicación.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ubicacion ubicacion = (Ubicacion) o;
        return x == ubicacion.x && y == ubicacion.y;
    }

    /**
     * Genera un código hash único para la ubicación.
     * @return El código hash de la ubicación.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Representación en cadena de la ubicación en formato "(x, y)".
     * @return La representación en cadena de la ubicación.
     */
    @Override
    public String toString() {
        return "(" + x +", " + y + ")";
    }
}
