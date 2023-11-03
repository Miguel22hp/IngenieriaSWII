package main;
public class Parcelas 
{
    int idParcela;
    int idTerreno;
    Ubicacion[] limites;
    Ubicacion ubicacion;

    public Parcelas(int idParcela, int idTerreno, Ubicacion[] limites, Ubicacion ubicacion) {
        this.idTerreno = idTerreno;
        this.idParcela = idParcela;
        this.limites = limites;
        this.ubicacion = ubicacion;
    }
    public int getIdParcela() {
        return idParcela;
    }
    public int getIdTerreno() {
        return idTerreno;
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
}
