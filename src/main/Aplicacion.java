package main;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Aplicacion {

    protected HashMap<Integer, Terreno> listaTerrenos;
    protected HashMap<Integer, Parcelas> listaParcelas;
    protected HashMap<String, Arrendatario> listaArrendatarios;

    private int idTerrenos;
    private int idParcela;
    private int idAlquiler;
    private int idRecibo;

    private HashMap<Integer,Recibo> listaRecibos;
    private HashMap<Integer,Alquiler> listaAlquileres;


    /**
     * Devuelve lista de todos los terrenos
     * @return lista de los terrenos
     */
    public HashMap<Integer, Terreno> getListaTerrenos() {
        return listaTerrenos;
    }

    /**
     * Devuelve lista de todqs las parcelas
     * @return lista de las parcelas
     */
    public HashMap<Integer, Parcelas> getListaParcelas() {
        return listaParcelas;
    }

    public HashMap<String, Arrendatario> getListaArrendatarios() {
        return listaArrendatarios;
    }

    public HashMap<Integer, Recibo> getListaRecibos() {
        return listaRecibos;
    }

    public HashMap<Integer, Alquiler> getListaAlquileres() {
        return listaAlquileres;
    }

    /**
     * Creas la aplicación inicializando los ILF propuestos para el ciclo
     */
    public Aplicacion()
    {
        this.listaTerrenos = new HashMap<>();
        this.listaParcelas = new HashMap<>();
        this.listaArrendatarios = new HashMap<>();
        this.idTerrenos = 1;
        this.idParcela = 1;
        this.idAlquiler = 1;
        this.idRecibo = 1;
        this.listaRecibos = new HashMap<>();
        this.listaAlquileres = new HashMap<>();
    }

    /**
     * Creas un terreno, añadiendole los siguintes parámetros, y aumentas en 1 el oidentificador de terrenos
     * @param tamano se corresponde al tamaño del terreno
     * @param limites array con los limites del terreno. Pueden ser tantos como quieras
     * @param ubi Una ubicación que es el punto de referencia del terreno
     * @return el identificador de este terreno
     */
    public int addTerreno(int tamano, Ubicacion[] limites, Ubicacion ubi)
    {
        Terreno terreno = new Terreno(idTerrenos,tamano,limites,ubi);
        listaTerrenos.put(idTerrenos,terreno);
        idTerrenos++;
        return  idTerrenos-1;
    }

    /**
     * Crea una parcela asociada al terreno del que se pasa el identificador
     * @param idTerreno Identificador del terreno al que va a pertencer esa parcela
     * @param limites array con los limites del terreno. Pueden ser tantos como quieras
     * @param ubi Una ubicación que es el punto de referencia de la parcela
     * @return el identificador de esta parcela
     * @throws NoTerrenoException si no existe el terreno del que forma parte la parcela
     */
    public int addParcela(int idTerreno, Ubicacion[] limites, Ubicacion ubi) throws NoTerrenoException {
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
            return idParcela-1;
        }

    }

    /**
     * Borra el terreno indicado
     * @param idTerreno indica el terreno a borrar
     * @return 0 si la ha borrado, -1 si no existe
     */
    public int removeTerreno(int idTerreno)
    {
        Terreno borrado = listaTerrenos.remove(idTerreno);
        if(borrado == null)
            return  -1;
        else
            return 0;
    }

    /**
     * Borra la parcela indicado
     * @param idParcela indica la parcela a borrar
     * @return 0 si la ha borrado, -1 si no existe
     */
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
     * Este método nos permite modificar un parámetro de un Terreno.
     * @param elementosMod es un array de enteros que tiene 3 posiciones,
     *                     y tiene un 0 en el parámetro que se desea modificar y un 1
     *                     en los elementos que no se desean modificar.
     * @param tamano un entero con un número o -1 si se desea modificar o no
     * @param limites un array de Ubicacion o null si se desea modificar o no
     * @param ubi una Ubicacion o null si se desea modificar o no
     * @param idTerreno el ID del Terreno que se desea modificar
     * @throws NoTerrenoException si no hay un Terreno con ese ID
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
     * Este método nos permite modificar un parámetro de una Parcela.
     * @param elementosMod es un array de enteros que tiene 2 posiciones,
     *                     y tiene un 0 en el parámetro que se desea modificar y un 1
     *                     en los elementos que no se desean modificar.
     * @param limites un array de Ubicacion o null si se desea modificar o no
     * @param ubi una Ubicacion o null si se desea modificar o no
     * @param idParcela el ID de la Parcela que se desea modificar
     * @throws NoParcelaException si no hay una Parcela con ese ID
     */

    public void modParcela(int[] elementosMod, Ubicacion[] limites,
                           Ubicacion ubi, int idParcela) throws NoParcelaException {
        Parcelas parcelas = listaParcelas.get(idParcela);
        if(parcelas == null)
        {
            throw new NoParcelaException("No existe esta parcela");
        }

         if(elementosMod[0] == 0) //Want to modify limites
             parcelas.setLimites(limites);

        if(elementosMod[1] == 0) //Want to modify ubication
            parcelas.setUbicacion(ubi);

        Terreno ter = listaTerrenos.get(parcelas.getIdTerreno());
        ter.addParcela(parcelas); // Al hacer un put sobre un elemento ya existente lo sobreescribe
    }

    /**
     * Este método te da los datos que le pides acerca del terreno que le indicas
     * @param elementosMod un array de enteros de 3 elementos, en los cuales se indica con 0 el elemento que quieres
     *                     que te devuelva, y con un 1 los que no.
     * @param idTerreno el identificador del terreno del que obtener los datos.
     * @return un array con los objetos que has pedido.
     * @throws NoTerrenoException si el @param idTerreno que le pasas no se corresponde a ningún terreno en el sistema
     */
    public Object[] getTerreno(int[] elementosMod, int idTerreno) throws NoTerrenoException {
        Object[] e = new Object[3];
        Terreno terreno = listaTerrenos.get(idTerreno);
        if(terreno == null) //Cuando el terreno que se quiere añadir no existe
        {
            throw new NoTerrenoException("No existe terreno con ese id");
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

    /**
     * Este método te da los datos que le pides acerca de la parcela que le indicas
     * @param elementosMod un array de enteros de 2 elementos, en los cuales se indica con 0 el elemento que quieres
     *                     que te devuelva, y con un 1 los que no.
     * @param idParcela el identificador de la parcela del que obtener los datos.
     * @return un array con los objetos que has pedido.
     * @throws NoParcelaException si el @param idParcela que le pasas no se corresponde a ningún terreno en el sistema
     */
    public Object[] getParcela(int[] elementosMod, int idParcela) throws NoParcelaException {
        Parcelas parcelas = listaParcelas.get(idParcela);
        if(parcelas == null)
        {
            throw new NoParcelaException("");
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

    /**
     * Este metodo crea un arrendatario con identificador dni si no existe
     * con ese dni, y lo añade al sistema
     * @param dni identificador del arrendatario
     * @param edad edad del arrendatario
     * @param sexo sexo del arrendatario. Puede ser H o M
     * @param aval el aval que tra ese nuevo arrendatario
     * @return dni del arrendatario creado o null si ya existia
     */
    public String addArrendatario(String dni, int edad, char sexo, String aval)
    {
        Arrendatario arr = listaArrendatarios.get(dni);
        if (arr == null)
        {
            arr = new Arrendatario(dni,edad,sexo,aval);
            listaArrendatarios.put(dni,arr);
            return dni;
        }
        else
            return null;
    }

    /**
     * Este metodo borra el arrendatario asociado a ese dni y
     * devuelve el dni de ese arrendatario o null si no
     * existia en el sistema arrendatario con ese dni
     * @param dni
     * @return
     */
    public String removeArrendatario(String dni)
    {
        Arrendatario arr = listaArrendatarios.remove(dni);
        if(arr != null)
            return  arr.getDni();
        else
            return null;
    }

    public void modArrendatario(int[] elementosMod, String dni, int edad,
                                char sexo, String aval)
    {
        Arrendatario arr = listaArrendatarios.get(dni);
        if(arr == null)
            System.out.println("No existe ese arrendatario");
        else
        {
            System.out.println("Arrendatario ha actualizar = " + arr);
            if (elementosMod[0] == 0) //Want to modify edad
                arr.setEdad(edad);

            if (elementosMod[1] == 0) //Want to modify sexo
                arr.setSexo(sexo);
            if(elementosMod[2] == 0) //Wants to modify aval
                arr.setAval(aval);

            System.out.println("Arrendatario actualizado = " + arr);

        }
    }

    public int generarRecibos(String tipoImpuesto, float impuesto)
    {
        Set<Integer> alquileres = listaAlquileres.keySet();
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        // Formatear la fecha y hora según tus preferencias
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaHoraActual.format(formato);
        int j = 0;

        for(Integer i: alquileres)
        {
            Alquiler a = listaAlquileres.get(i);
            Recibo r = new Recibo(idRecibo, fechaFormateada, a.getImporte(), tipoImpuesto, impuesto, false, a.getId());
            listaRecibos.put(idRecibo,r);
            idRecibo++;
            j++;
            System.out.println("Recibo creado: " + r);
        }
        return  j;
    }

    public int addAlquiler(String fechaInicio, String fechaFin, int duracion, float importe, int idParcela, String dniArrendatario) throws NoParcelaException, NoArrendatarioException
    {
        if (listaParcelas.get(idParcela) == null)
            throw new NoParcelaException("No existe la parcela que se quiere alquilar");
        if (listaArrendatarios.get(dniArrendatario) == null)
            throw new NoArrendatarioException("No existe el arrendatario que alquila la parcela");
        Alquiler alquiler = new Alquiler(idAlquiler, fechaInicio, fechaFin, duracion, importe, idParcela, dniArrendatario);
        idAlquiler++;
        listaAlquileres.put(alquiler.idAlquiler, alquiler);
        listaArrendatarios.get(dniArrendatario).addAlquiler(alquiler);
        System.out.println(alquiler.toString());
        return alquiler.idAlquiler;
    }

    public int removeAlquiler(int idAlquiler)
    {
        return (listaAlquileres.remove(idAlquiler) != null) ? 1 : -1;
    }

    public boolean pagarRecibo(int numRecibo)
    {
        if (listaRecibos.get(numRecibo) == null)
            return false;
        listaRecibos.get(numRecibo).pagado();
        return true;
    }
}