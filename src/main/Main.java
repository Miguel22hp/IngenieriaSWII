package main;

import java.util.Map;

public class Main {

    static java.util.Scanner in;

    public static void main(String[] args) throws NoTerrenoException {
        Aplicacion aplicacion = new Aplicacion();
        in = new java.util.Scanner(System.in);

        while (true) {
            System.out.println("Operaciones con sus números:");
            System.out.println("Salir del sistema : -1");
            System.out.println("Añadir Terreno : 1");
            System.out.println("Añadir Parcelas : 2");
            System.out.println("Modificar Datos Terreno: 3");
            System.out.println("Modificar Datos Parcela: 4");
            System.out.println("Obtener Datos Terreno: 5");
            System.out.println("Obtener Datos Parcela: 6");
            System.out.println("Borrar un Terreno: 7");
            System.out.println("Borrar una Parcela: 8");
            System.out.println("Añadir un arrendatario: 9");
            System.out.println("Borrar un arrendatario: 10");
            System.out.println("Modificar un arrendatario: 11");
            System.out.println("Generar recibos: 14");
            System.out.println("Escribe la operación que deseas hacer a continuación:");

            int operacionSeleccionada = in.nextInt();

            switch (operacionSeleccionada)
            {
                case -1:
                    System.out.println("Saliendo del sistema.");
                    return;
                case 1:
                    System.out.println("Añadir Terreno");
                    anadirTerreno(aplicacion);
                    break;

                case 2:
                    System.out.println("Añadir Parcelas : 2");
                    anadirParcela(aplicacion);
                    break;

                case 3:
                    System.out.println("Modificar Datos Terreno: 3");
                    modificarTerrenos(aplicacion);
                    break;

                case 4:
                    System.out.println("Modificar Datos Parcela: 4");
                    modificarParcelas(aplicacion);
                    break;

                case 5:
                    System.out.println("Obtener Datos Terreno: 5");
                    verTerrenos(aplicacion);

                    break;

                case 6:
                    System.out.println("Obtener Datos Parcela: 6");
                    verParcelas(aplicacion);
                    break;

                case 7:
                    System.out.println("Borrar un Terreno: 7");
                    borrarTerreno(aplicacion);
                    break;
                case 8:
                    System.out.println("Borrar una Parcela: 8");
                    borrarParcela(aplicacion);
                    break;
                case 9:
                    System.out.println("Añadir un arrendatario: 9");
                    anadirArrendatario(aplicacion);
                    break;
                case 10:
                    System.out.println("Borrar un arrendatario: 10");
                    borrarArrendatario(aplicacion);
                    break;
                case 11:
                    System.out.println("Modificar un arrendatario: 11");
                    modificarArrendatario(aplicacion);
                    break;
                case 14:
                    System.out.println("Generar recibos: 14");
                    generarRecibo(aplicacion);
                    break;


                default:
                    System.out.println("No hay operación asociada");
            }

            System.out.println("");
            System.out.println("");
            System.out.println("");
        }

        
    }

    private static void generarRecibo(Aplicacion aplicacion) {
        System.out.println("Introduce un 0 si el recibo tiene un impuesto IVA o 1 si es IRPF: ");
        int val = in.nextInt();
        String tipoImpuesto = "";

        if(val == 0)
            tipoImpuesto = "IVA";
        else if (val == 1)
            tipoImpuesto = "IRPF";
        else {
            System.out.println("Tipo de impuesto no existente");
            return;
        }

        System.out.println("Introduce lo que se debe pagar en impuestos: ");
        float impuesto = in.nextFloat();

        System.out.println("Numero de recibos generados " + aplicacion.generarRecibos(tipoImpuesto,impuesto));

    }

    private static void modificarArrendatario(Aplicacion aplicacion) {
        System.out.println("Introduce el dni del arrendatario: ");
        String dni = in.next();

        System.out.println("Selecciona los datos que quieras modificar, poniendo un 0 si quieres ver ese dato, o " +
                "un 1 si no te interesa ver ese dato:");
        System.out.println("Edad del arrendatario:");
        int edadM = in.nextInt();
        int edad = -1;
        if(edadM == 0)
        {
            System.out.println("Introduce la nueva edad del arrendatario");
            edad = in.nextInt();
        }
        System.out.println("Sexo del arrendatario:");
        int sexoM = in.nextInt();
        char sexo = ' '; //TODO: ERROR AL INICIALIZAR CHAR CORREGIDO EN PRUEBAS
        if(sexoM == 0)
        {
            System.out.println("Introduce el nuevo sexo del arrendatario:");
            sexo = in.next().charAt(0);
        }
        System.out.println("Aval del arrendatario:");
        int avalM = in.nextInt();
        String aval = "";
        if(avalM == 0)
        {
            System.out.println("Introduce el nuevo aval del arrendatario:");
            aval = in.next();
        }

        int[] array = {edadM,sexoM,avalM};


        aplicacion.modArrendatario(array,dni,edad,sexo,aval);


    }

    private static void borrarArrendatario(Aplicacion aplicacion) {
        System.out.println("Introduce el dni del arrendatario: ");
        String dni = in.next();

        String dniBorrado = aplicacion.removeArrendatario(dni);
        if(dniBorrado != null)
            System.out.println("Arrendatario con dni " + dniBorrado + " borrado del sistema");
        else
            System.out.println("No existía arrendatario con ese dni");

    }

    private static void anadirArrendatario(Aplicacion aplicacion) {
        System.out.println("Introduce el dni del arrendatario: ");
        String dni = in.next();

        System.out.print("Edad del arrendatario: ");
        int edad = in.nextInt();

        System.out.print("Introduce el sexo del arrendatario, con una H para hombre y una M para mujer: ");
        char sexo = in.next().charAt(0);

        System.out.println("Introduce el aval del arrendatario: ");
        String aval = in.next();

        String arrendatarioCreado = aplicacion.addArrendatario(dni,edad,sexo,aval);
        if(arrendatarioCreado == null)
            System.out.println("Ya existe un arrendatario con ese dni");
        else
            System.out.println("Arrendatario creado con dni " + arrendatarioCreado);

    }

    /**
     * Borra la parcela asociada al id que entra por entrada estandar, si
     * existe una parcela con ese identificador
     * @param aplicacion
     */
    private static void borrarParcela(Aplicacion aplicacion) {
        System.out.println("Seleccionar el identificador de la parcela que quieres borrar");
        System.out.print("\tId de la parcela:");
        int idParcela = in.nextInt();
        int res = aplicacion.removeParcela(idParcela);
        if(res == -1)
            System.out.println("No existe parcela con ese id");
        else
            System.out.println("Parcela borrado");
    }

    /**
     * Borra el terreno asociado al id que entra por entrada estandar, si
     * existe un terreno con ese identificador
     * @param aplicacion
     */
    private static void borrarTerreno(Aplicacion aplicacion) {
        System.out.println("Seleccionar el identificador del terreno que quieres borrar");
        System.out.print("\tId del terreno:");
        int idTerreno = in.nextInt();
        int res = aplicacion.removeTerreno(idTerreno);
        if(res == -1)
            System.out.println("No existe terreno con ese id");
        else
            System.out.println("Terreno borrado");
    }

    /**
     * Añade una parcela con la ubicación y los límites
     * que va recibiendo por entrada estandar.
     * @param aplicacion
     */
    private static void anadirParcela(Aplicacion aplicacion) {
         System.out.print("Id del terreno al que pertenece la parcela:");
        int idTerreno = in.nextInt();

        System.out.println("Valor de la ubicación:");
        System.out.print("\tCoordenada X:");
        int coordX = in.nextInt();
        System.out.print(" ");
        System.out.print("\tCoordenada Y:");
        int coordY = in.nextInt();
        Ubicacion ubicacion = new Ubicacion(coordX,coordY);

        System.out.println("Valor de los límites:");
        System.out.print("\tNúmero de ubicaciones para indicar límites:");
        int numUbiEnLimites = in.nextInt();
        Ubicacion[] limites = new Ubicacion[numUbiEnLimites];
        for(int i = 0; i < numUbiEnLimites; i++)
        {
            System.out.println("Valor de la ubicación:");
            System.out.print("\tCoordenada X:");
            coordX = in.nextInt();
            System.out.print(" ");
            System.out.print("\tCoordenada Y:");
            coordY = in.nextInt();
            Ubicacion ubicacion2 = new Ubicacion(coordX,coordY);
            limites[i] = ubicacion2;
        }

        try {
            int idParcela = aplicacion.addParcela(idTerreno, limites, ubicacion); //Cambiar el método para que devuelva el identificador.
                                                                    // Interesante para hacer get y set a futuro. Añadir a defectos
                                                                    // diseño. Corregir en Aplicacion, AplicacionTest.
            System.out.println("El identificador de la parcela creada es "+ idParcela);
            //System.out.println("El identificador de la parcela creada es ");
        }catch (NoTerrenoException e)
        {
            System.out.println("No existe terreno con tal identificador");
        }
    }

    /**
     * Modifica la parcela asociada al identificador que recibe por la pantalla,
     * si existe. En función de los datos que el usuario quiere modificar, le
     * permite que escriba los datos de aquellos que quiera modificar entre
     * ubicación y límites
     * @param aplicacion
     */
    private static void modificarParcelas(Aplicacion aplicacion) {

        System.out.print("\tId de la parcela:");
        int idTerreno = in.nextInt();
        System.out.println("\tSelecciona los datos que quieras modificar, poniendo un 0 si quieres ver ese dato, o " +
                "un 1 si no te interesa ver ese dato:");

        System.out.print("\t\tModificar los Limites del terreno:");
        int limitesM = in.nextInt();
        Ubicacion[] limites = null; //Defecto de codigo solucionado durante pruebas. Ya solucionado

        if( limitesM == 0)
        {
            System.out.print("\tNúmero de ubicaciones para indicar límites:");
            int numUbiEnLimites = in.nextInt();
            limites = new Ubicacion[numUbiEnLimites];
            for(int i = 0; i < numUbiEnLimites; i++)
            {
                System.out.println("Valor de la ubicación:");
                System.out.print("\tCoordenada X:");
                int coordX = in.nextInt();
                System.out.print(" ");
                System.out.print("\tCoordenada Y:");
                int coordY = in.nextInt();
                Ubicacion ubicacion = new Ubicacion(coordX,coordY);
                limites[i] = ubicacion;
            }
        }


        System.out.print("\t\tModificar la Ubicación del terreno:");
        int ubicacionM = in.nextInt();
        Ubicacion ubicacion = null;
        if(ubicacionM == 0)
        {
            System.out.print("\t\t\tUbicación del terreno:");
            System.out.print("\t\t\t\tCoordenada X:");
            int coordX = in.nextInt();
            System.out.print("\t\t\t\tCoordenada Y:");
            int coordY = in.nextInt();
            ubicacion = new Ubicacion(coordX,coordY);
        }


        int[] array = {limitesM,ubicacionM};
        try {
            aplicacion.modParcela(array, limites, ubicacion, idTerreno);
        }catch (NoParcelaException e)
        {
            System.out.println("No existe parcela con tal identificador");
        }

        //Meterlo en un try and catch y lanzar una excepción en caso de que se meta un id que no exista terreno
        //Fallo de código detectado durante código


    }

    /**
     * Muestra los parametros que te interese ver de la parcela que le solicites
     * indicando su identificador, o de todas en caso de que escribas 0
     * @param aplicacion
     */
    private static void verParcelas(Aplicacion aplicacion) {
        System.out.println("Seleccionar el identificador de la parcela. Si se escribe 0 se muestra el " +
                "de todas las parcelas en el sistema. Sino seleccionar el identificador de la parcela que buscas");
        System.out.print("\tId de la parcela:");
        int idParcela = in.nextInt();
        System.out.println("\tSelecciona los datos que quieras ver, poniendo un 0 si quieres ver ese dato, o " +
                "un 1 si no te interesa ver ese dato:");

        System.out.print("\t\tLimites del terreno:");
        int limites = in.nextInt();
        System.out.print("\t\tUbicación del terreno:");
        int ubicacion = in.nextInt();
        int[] array = {limites,ubicacion};

        if(idParcela == 0) //Si se quieren ver todos los terrenos
        {
            for(Map.Entry<Integer,Parcelas> entry: aplicacion.getListaParcelas().entrySet())
            {
                int idParcela2 = entry.getKey();
                System.out.println("---------------Datos de parcela con id = " + idParcela2 + " ------------------");
                try {
                    Object[] datosParcela = aplicacion.getParcela(array, idParcela2);
                    if (datosParcela[1] != null)
                        System.out.println("-> Ubicación = " + datosParcela[1].toString());
                    if (datosParcela[0] != null) {
                        Ubicacion[] limites2 = (Ubicacion[]) datosParcela[0];
                        System.out.print("-> Limites = [");
                        for (int i = 0; i < limites2.length; i++)
                            System.out.print(limites2[i].toString());
                        System.out.println("]");
                    }
                    System.out.println("---------------Fin de datos de terreno con id = " + idParcela2 + " ------------------");
                }catch (NoParcelaException e) {
                    System.out.println("No existe parcela con tal identificador");
                }
            }
        }
        else
        {
            //TODO:
            //Meterlo en un try and catch y lanzar una excepción en caso de que se meta un id que no exista terreno
            try {
                Object[] datosParcela = aplicacion.getParcela(array, idParcela); //Obtienes los datos. Debería de cazarse una excepción
                System.out.println("---------------Datos de terreno con id = " + idParcela + " ------------------");
                if (datosParcela[1] != null)
                    System.out.println("-> Ubicación = " + datosParcela[1].toString());
                if (datosParcela[0] != null) {
                    Ubicacion[] limites2 = (Ubicacion[]) datosParcela[0];
                    System.out.print("-> Limites = [");
                    for (int i = 0; i < limites2.length; i++)
                        System.out.print(limites2[i].toString());
                    System.out.println("]");
                }
                System.out.println("---------------Fin de datos de terreno con id = " + idParcela + " ------------------");
            }catch (NoParcelaException e) {
                System.out.println("No existe parcela con tal identificador");
            }
        }
    }

    /**
     * Muestra los parametros que te interese ver del terreno que le solicites
     * indicando su identificador, o de todos en caso de que escribas 0
     * @param aplicacion
     */
    private static void verTerrenos(Aplicacion aplicacion) throws NoTerrenoException {
        System.out.println("Seleccionar el identificador del terreno. Si se escribe 0 se muestra el " +
                "de todos los terrenos en el sistema. Sino seleccionar el identificador del terreno que buscas");
        System.out.print("\tId del terreno:");
        int idTerreno = in.nextInt();
        System.out.println("\tSelecciona los datos que quieras ver, poniendo un 0 si quieres ver ese dato, o " +
                "un 1 si no te interesa ver ese dato:");
        System.out.print("\t\tTamaño del terreno:");
        int tamaño = in.nextInt();
        System.out.print("\t\tLimites del terreno:");
        int limites = in.nextInt();
        System.out.print("\t\tUbicación del terreno:");
        int ubicacion = in.nextInt();
        int[] array = {tamaño,limites,ubicacion};

        if(idTerreno == 0) //Si se quieren ver todos los terrenos
        {
            for(Map.Entry<Integer,Terreno> entry: aplicacion.getListaTerrenos().entrySet()) {
                int idTerreno2 = entry.getKey();
                System.out.println("---------------Datos de terreno con id = " + idTerreno2 + " ------------------");
                try{
                Object[] datosTerreno = aplicacion.getTerreno(array, idTerreno2);
                if (datosTerreno[0] != null)
                    System.out.println("-> Tamaño = " + datosTerreno[0]);
                if (datosTerreno[2] != null)
                    System.out.println("-> Ubicación = " + datosTerreno[2].toString());
                if (datosTerreno[1] != null) {
                    Ubicacion[] limites2 = (Ubicacion[]) datosTerreno[1];
                    System.out.print("-> Limites = [");
                    for (int i = 0; i < limites2.length; i++)
                        System.out.print(limites2[i].toString());
                    System.out.println("]");
                }
                System.out.println("---------------Fin de datos de terreno con id = " + idTerreno2 + " ------------------");
                }catch (NoTerrenoException e){
                    System.out.println("No existe terreno con ese id");
                }
            }
        }
        else
        {
            try {
                Object[] datosTerreno = aplicacion.getTerreno(array, idTerreno); //Obtienes los datos.
                System.out.println("---------------Datos de terreno con id = " + idTerreno + " ------------------");
                if (datosTerreno[0] != null)
                    System.out.println("-> Tamaño = " + datosTerreno[0]);
                if (datosTerreno[2] != null)
                    System.out.println("-> Ubicación = " + datosTerreno[2].toString());
                if (datosTerreno[1] != null) {
                    Ubicacion[] limites2 = (Ubicacion[]) datosTerreno[1];
                    System.out.print("-> Limites = [");
                    for (int i = 0; i < limites2.length; i++)
                        System.out.print(limites2[i].toString());
                    System.out.println("]");
                }
                System.out.println("---------------Fin de datos de terreno con id = " + idTerreno + " ------------------");
            }catch (NoTerrenoException e){
                System.out.println("No existe terreno con ese id");
            }
        }

    }

    /**
     * Añade un terreno con el tamaño, la ubicación y los límites
     * que va recibiendo por entrada estandar.
     * @param aplicacion
     */
    private static void anadirTerreno(Aplicacion aplicacion) {
        System.out.print("Valor del tamaño:");
        int tamano = in.nextInt();

        System.out.println("Valor de la ubicación:");
        System.out.print("\tCoordenada X:");
        int coordX = in.nextInt();
        System.out.print(" ");
        System.out.print("\tCoordenada Y:");
        int coordY = in.nextInt();
        Ubicacion ubicacion = new Ubicacion(coordX,coordY);

        System.out.println("Valor de los límites:");
        System.out.print("\tNúmero de ubicaciones para indicar límites:");
        int numUbiEnLimites = in.nextInt();
        Ubicacion[] limites = new Ubicacion[numUbiEnLimites];
        for(int i = 0; i < numUbiEnLimites; i++)
        {
            System.out.println("Valor de la ubicación:");
            System.out.print("\tCoordenada X:");
            coordX = in.nextInt();
            System.out.print(" ");
            System.out.print("\tCoordenada Y:");
            coordY = in.nextInt();
            Ubicacion ubicacion2 = new Ubicacion(coordX,coordY);
            limites[i] = ubicacion2;
        }

        int idTerreno = aplicacion.addTerreno(tamano,limites,ubicacion); //Cambiar el método para que devuelva el identificador.
                                                         // Interesante para hacer get y set a futuro. Añadir a defectos
                                                        // diseño. Corregir en Aplicacion, AplicacionTest.
        System.out.println("El identificador del terreno creado es "+ idTerreno);
    }

    /**
     * Modifica el terreno asociado al identificador que recibe por la pantalla,
     * si existe. En función de los datos que el usuario quiere modificar, le
     * permite que escriba los datos de aquellos que quiera modificar entre tamaño,
     * ubicación y límites
     * @param aplicacion
     */
    private static void modificarTerrenos(Aplicacion aplicacion) {

        System.out.print("\tId del terreno:");
        int idTerreno = in.nextInt();
        System.out.println("\tSelecciona los datos que quieras modificar, poniendo un 0 si quieres ver ese dato, o " +
                "un 1 si no te interesa ver ese dato:");
        System.out.print("\t\tModificar el Tamaño del terreno:");
        int tamanoM = in.nextInt();
        int tamano = -1;
        if(tamanoM == 0)
        {
            System.out.print("\t\t\tTamaño del terreno:");
            tamano = in.nextInt();
        }

        System.out.print("\t\tModificar los Limites del terreno:");
        int limitesM = in.nextInt();
        Ubicacion[] limites = null; //Defecto de codigo solucionado durante pruebas. Ya solucionado

        if( limitesM == 0)
        {
            System.out.print("\tNúmero de ubicaciones para indicar límites:");
            int numUbiEnLimites = in.nextInt();
            limites = new Ubicacion[numUbiEnLimites];
            for(int i = 0; i < numUbiEnLimites; i++)
            {
                System.out.println("Valor de la ubicación:");
                System.out.print("\tCoordenada X:");
                int coordX = in.nextInt();
                System.out.print(" ");
                System.out.print("\tCoordenada Y:");
                int coordY = in.nextInt();
                Ubicacion ubicacion = new Ubicacion(coordX,coordY);
                limites[i] = ubicacion;
            }
        }


        System.out.print("\t\tModificar la Ubicación del terreno:");
        int ubicacionM = in.nextInt();
        Ubicacion ubicacion = null;
        if(ubicacionM == 0)
        {
            System.out.print("\t\t\tUbicación del terreno:");
            System.out.print("\t\t\t\tCoordenada X:");
            int coordX = in.nextInt();
            System.out.print("\t\t\t\tCoordenada Y:");
            int coordY = in.nextInt();
            ubicacion = new Ubicacion(coordX,coordY);
        }


        int[] array = {tamanoM,limitesM,ubicacionM};

        try {
            aplicacion.modTerreno(array, tamano, limites, ubicacion, idTerreno);
        }catch (NoTerrenoException e) {
            System.out.println("No existe terreno con ese id");
        }

    }
    
    
}
