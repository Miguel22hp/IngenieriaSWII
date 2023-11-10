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

                default:
                    System.out.println("No hay operación asociadan");
            }

            System.out.println("");
            System.out.println("");
            System.out.println("");
        }

        
    }

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
            ubicacion = new Ubicacion(coordX,coordY);
            limites[i] = ubicacion;
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
            ubicacion = new Ubicacion(coordX,coordY);
            limites[i] = ubicacion;
        }

        int idTerreno = aplicacion.addTerreno(tamano,limites,ubicacion); //Cambiar el método para que devuelva el identificador.
                                                         // Interesante para hacer get y set a futuro. Añadir a defectos
                                                        // diseño. Corregir en Aplicacion, AplicacionTest.
        System.out.println("El identificador del terreno creado es "+ idTerreno);
    }

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
