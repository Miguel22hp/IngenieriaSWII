package main;
public class Parcelas 
{
    int id_parcela;
    int id_terreno;
    Ubicacion limites[];
    Ubicacion ubicacion;

    public Parcelas(int id_parcela, int id_terreno, Ubicacion[] limites, Ubicacion ubicacion) {
        this.id_terreno = id_terreno;
        this.id_parcela = id_parcela;
        this.limites = limites;
        this.ubicacion = ubicacion;
    }
    public int getId_parcela() {
        return id_parcela;
    }
    public void setId_parcela(int id_parcela) {
        this.id_parcela = id_parcela;
    }
    public int getId_terreno() {
        return id_terreno;
    }
    public void setId_terreno(int id_terreno) {
        this.id_terreno = id_terreno;
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
