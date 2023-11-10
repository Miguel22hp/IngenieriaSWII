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

    /**
     * Constructor de la clase Terreno.
     * @param id El ID del Terreno.
     * @param tamano El tamaño del Terreno.
     * @param limites Un array de Ubicacion que representa los límites del Terreno.
     * @param ubicacion La Ubicacion del Terreno.
     */
    public Terreno(int id, int tamano, Ubicacion[] limites, Ubicacion ubicacion) {
        this.id = id;
        this.tamano = tamano;
        this.limites = limites;
        this.ubicacion = ubicacion;
        this.parcelasTerreno = new HashMap<>();
        // creates the hashmaps where the parcelas of a terreno are kept
    }

    /**
     * Obtiene el id del Terreno.
     * @return El id del Terreno.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el tamaño del Terreno.
     * @return El tamaño del Terreno.
     */
    public int getTamano() {
        return tamano;
    }

    /**
     * Establece el tamaño del Terreno.
     * @param tamano El nuevo tamaño del Terreno.
     */
    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    /**
     * Obtiene los límites del Terreno.
     * @return Un array de Ubicacion que representa los límites del Terreno.
     */
    public Ubicacion[] getLimites() {
        return limites;
    }

    /**
     * Establece los límites del Terreno.
     * @param limites Un array de Ubicacion que representa los nuevos límites del Terreno.
     */
    public void setLimites(Ubicacion[] limites) {
        this.limites = limites;
    }

    /**
     * Obtiene la Ubicacion del Terreno.
     * @return La Ubicacion del Terreno.
     */
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    /**
     * Establece la Ubicacion del Terreno.
     * @param ubicacion La nueva Ubicacion del Terreno.
     */
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Obtiene un mapa que contiene las Parcelas del Terreno.
     * @return Un mapa que contiene las Parcelas del Terreno.
     */
    public Map<Integer, Parcelas> getParcelasTerreno() {
        return parcelasTerreno;
    }

    /**
     * Establece las Parcelas del Terreno.
     * @param parcelasTerreno Un mapa que contiene las nuevas Parcelas del Terreno.
     */
    public void setParcelasTerreno(Map<Integer,Parcelas> parcelasTerreno) {
        this.parcelasTerreno = (HashMap<Integer, Parcelas>) parcelasTerreno;
    }

    /**
     * Añade una Parcela al Terreno.
     * @param parcela La Parcela que se añadirá al Terreno.
     */
    public void addParcela(Parcelas parcela){
        parcelasTerreno.put(parcela.getIdParcela(), parcela);
    }

    /**
     * Borra una Parcela del Terreno.
     * @param idParcela El ID de la Parcela que se borrará.
     * @return 0 si la Parcela se borra correctamente, -2 si la Parcela no existe.
     */
    public int borrarParcela(int idParcela)
    {
        Parcelas borrado = parcelasTerreno.remove(idParcela);
        if(borrado == null)
            return  -2;
        else
            return 0;
    }


}
