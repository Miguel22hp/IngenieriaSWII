package main;
import main.Ubicacion;

import java.util.HashMap;
import java.util.Map;

public class Terreno 
{
    int id;
    int tamano;
    Ubicacion[] limites;
    Ubicacion ubicacion;
    HashMap<Integer, Parcelas> parcelasTerreno;
    
    public Terreno(int id, int tamano, Ubicacion[] limites, Ubicacion ubicacion) {
        this.id = id;
        this.tamano = tamano;
        this.limites = limites;
        this.ubicacion = ubicacion;
        this.parcelasTerreno = new HashMap<>();
        // creates the hashmaps where the parcelas of a terreno are kept
    }
    public int getId() {
        return id;
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
    public Map<Integer, Parcelas> getParcelasTerreno() {
        return parcelasTerreno;
    }
    public void setParcelasTerreno(Map<Integer,Parcelas> parcelasTerreno) {
        this.parcelasTerreno = (HashMap<Integer, Parcelas>) parcelasTerreno;
    }

    public void addParcela(Parcelas parcela){
        parcelasTerreno.put(parcela.getIdParcela(), parcela);
    }

    public int borrarParcela(int idParcela)
    {
        Parcelas borrado = parcelasTerreno.remove(idParcela);
        if(borrado == null)
            return  -2;
        else
            return 0;
    }


}
