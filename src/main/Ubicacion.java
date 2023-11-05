package main;

import java.util.Objects;

public class Ubicacion
{
    int x;
    int y;

    public Ubicacion (int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public int getX() { return x; }
    
    public int getY(){ return y; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ubicacion ubicacion = (Ubicacion) o;
        return x == ubicacion.x && y == ubicacion.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
