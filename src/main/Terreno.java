package main;
import main.Ubicacion;

public class Terreno 
{
    int id;
    int tamano;
    Ubicacion limites[];
    Ubicacion ubicacion;
    Parcelas parcelasTerreno[];
    
    public Terreno(int id, int tamano, Ubicacion[] limites, Ubicacion ubicacion, Parcelas[] parcelasTerreno) {
        this.id = id;
        this.tamano = tamano;
        this.limites = limites;
        this.ubicacion = ubicacion;
        this.parcelasTerreno = parcelasTerreno;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getTamano() {
        return tamano;
    }
    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
    public Ubicacion[] getLimites() {
        return limites;
    }
    public void setLimites(Ubicacion[] limites) {
        this.limites = limites;
    }
    public Ubicacion getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
    public Parcelas[] getParcelasTerreno() {
        return parcelasTerreno;
    }
    public void setParcelasTerreno(Parcelas[] parcelasTerreno) {
        this.parcelasTerreno = parcelasTerreno;
    }


}
