package main;

import java.util.HashMap;

public class Aplicacion {

    protected HashMap<Integer,Terreno> listaTerrenos;
    protected HashMap<Integer,Parcelas> listaParcelas;
    protected HashMap<Integer, Arrendatario> listaArrendatarios;
    private int idTerrenos;
    private int idParcela;

    public HashMap<Integer, Terreno> getListaTerrenos() {
        return listaTerrenos;
    }

    public HashMap<Integer, Parcelas> getListaParcelas() {
        return listaParcelas;
    }

    public Aplicacion()
    {
        this.listaTerrenos = new HashMap<>();
        this.listaParcelas = new HashMap<>();
        this.listaArrendatarios = new HashMap<>();
        this.idTerrenos = 1;
        this.idParcela = 1;
    }

    public void addTerreno(int tamano, Ubicacion[] limites, Ubicacion ubi)
    {
        Terreno terreno = new Terreno(idTerrenos,tamano,limites,ubi);
        listaTerrenos.put(idTerrenos,terreno);
        idTerrenos++;
    }

    public void addParcela(int idTerreno, Ubicacion[] limites, Ubicacion ubi) throws NoTerrenoException {
        Parcelas parcelas = new Parcelas(idParcela, idTerreno, limites, ubi);
        Terreno terreno = listaTerrenos.get(idTerreno);
        if(terreno == null) //Cuando el terreno que se quiere añadir no existe
        {
            throw new NoTerrenoException("No existe Terreno al que pertenece la parcela");
        }
        else
        {
            terreno.addParcela(parcelas);
            listaParcelas.put(idParcela,parcelas);
            idParcela++;
        }
    }

    public int removeTerreno(int idTerreno)
    {
        Terreno borrado = listaTerrenos.remove(idTerreno);
        if(borrado == null)
            return  -1;
        else
            return 0;
    }

    public int removeParcela(int idParcela)
    {

        Parcelas borrado = listaParcelas.remove(idParcela);
        if(borrado == null)
            return  -1;
        else {
            int idTerrenoParcela = borrado.getIdTerreno();
            Terreno ter = listaTerrenos.get(idTerrenoParcela);
            return ter.borrarParcela(idParcela);
        }
    }


    /**
     * This method allows us to modify a param of a Terreno
     * @param elementosMod is an array of integers that has 3 positions,
     *                     and has a 0 on the param he wants to modify and a 1
     *                     in the elements he doesn't want to modify
     * @param tamano an int with a number or -1 if you want to modyfy or not
     * @param limites an array of Ubicacion or null  if you want to modyfy or not
     * @param ubi an Ubication or null  if you want to modyfy or not
     * @param idTerreno the id of the Terreno you want to modify
     * @throws NoTerrenoException if there is not a Terreno with that id
     */
    public  void modTerreno(int[] elementosMod, int tamano,
                            Ubicacion[] limites, Ubicacion ubi, int idTerreno) throws NoTerrenoException {
        Terreno terreno = listaTerrenos.get(idTerreno);
        if(terreno == null) //Cuando el terreno que se quiere añadir no existe
        {
            throw new NoTerrenoException("No existe Terreno al que pertenece la parcela");
        }
        if(elementosMod[0] == 0) //Want to modify tamaño
            terreno.setTamano(tamano);

        if(elementosMod[1] == 0) //Want to modify limites
            terreno.setLimites(limites);

        if(elementosMod[2] == 0) //Want to modify ubication
            terreno.setUbicacion(ubi);
    }

    /**
     * This method allows us to modify a param of a Terreno
     * @param elementosMod is an array of integers that has 2 positions,
     *                     and has a 0 on the param he wants to modify and a 1
     *                     in the elements he doesn't want to modify
     * @param limites an array of Ubicacion or null  if you want to modyfy or not
     * @param ubi an Ubication or null  if you want to modyfy or not
     * @param idParcela the id of the Parcela you want to modify
     */
    public void modParcela(int[] elementosMod, Ubicacion[] limites,
                           Ubicacion ubi, int idParcela)
    {
        Parcelas parcelas = listaParcelas.get(idParcela);
        if(parcelas == null)
        {
            //TODO:NoParcelaException
        }

         if(elementosMod[0] == 0) //Want to modify limites
             parcelas.setLimites(limites);

        if(elementosMod[1] == 0) //Want to modify ubication
            parcelas.setUbicacion(ubi);

        Terreno ter = listaTerrenos.get(parcelas.getIdTerreno());
        ter.addParcela(parcelas); // Al hacer un put sobre un elemento ya existente lo sobreescribe
    }

    public Object[] getTerreno(int[] elementosMod, int idTerreno)
    {
        Object[] e = new Object[3];
        Terreno terreno = listaTerrenos.get(idTerreno);
        if(terreno == null) //Cuando el terreno que se quiere añadir no existe
        {
            //throw new NoTerrenoException("");
        }
        if(elementosMod[0] == 0) //Want to get tamaño
            e[0] = terreno.getTamano();
        else
            e[0] = null;

        if(elementosMod[1] == 0) //Want to get limites
            e[1] = terreno.getLimites();
        else
            e[1] = null;

        if(elementosMod[2] == 0) //Want to get ubication
            e[2] = terreno.getUbicacion();
        else
            e[2] = null;

        return e;
    }

    public Object[] getParcela(int[] elementosMod, int idParcela)
    {
        Parcelas parcelas = listaParcelas.get(idParcela);
        if(parcelas == null)
        {
            //TODO:NoParcelaException
        }
        Object[] e = new Object[3];
         if(elementosMod[0] == 0) //Want to get limites
             e[0] = parcelas.getLimites();
         else
            e[0] = null;

        if(elementosMod[1] == 0) //Want to get ubication
            e[1] = parcelas.getUbicacion();
        else
            e[1] = null;

        return e;
    }

}