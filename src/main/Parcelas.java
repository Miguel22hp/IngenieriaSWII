package main;
public class Parcelas 
{
    int idParcela;
    int idTerreno;
    Ubicacion[] limites;
    Ubicacion ubicacion;

    /**
     * Constructor de la clase Parcelas.
     * Crea una parcela con la información proporcionada.
     * @param idParcela Identificador único de la parcela.
     * @param idTerreno Identificador único del terreno al que pertenece la parcela.
     * @param limites Limites geográficos de la parcela definidos por un arreglo de ubicaciones.
     * @param ubicacion Ubicación de referencia de la parcela.
     */
    public Parcelas(int idParcela, int idTerreno, Ubicacion[] limites, Ubicacion ubicacion) {
        this.idTerreno = idTerreno;
        this.idParcela = idParcela;
        this.limites = limites;
        this.ubicacion = ubicacion;
    }

    /**
     * Obtiene el identificador único de la parcela.
     * @return El identificador único de la parcela.
     */
    public int getIdParcela() {
        return idParcela;
    }

    /**
     * Obtiene el identificador único del terreno al que pertenece la parcela.
     * @return El identificador único del terreno al que pertenece la parcela.
     */
    public int getIdTerreno() {
        return idTerreno;
    }

    /**
     * Obtiene los límites geográficos de la parcela.
     * @return Los límites geográficos de la parcela como un arreglo de ubicaciones.
     */
    public Ubicacion[] getLimites() {
        return limites;
    }

    /**
     * Establece los límites geográficos de la parcela.
     * @param limites Los nuevos límites geográficos de la parcela como un arreglo de ubicaciones.
     */
    public void setLimites(Ubicacion[] limites) {
        this.limites = limites;
    }

    /**
     * Obtiene la ubicación de referencia de la parcela.
     * @return La ubicación de referencia de la parcela.
     */
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    /**
     * Establece la ubicación de referencia de la parcela.
     * @param ubicacion La nueva ubicación de referencia de la parcela.
     */
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }    
}
