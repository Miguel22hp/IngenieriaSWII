package main;

import java.util.Map;


public class MainSQLite {
    static java.util.Scanner in;

    public static void main(String[] args) throws NoTerrenoException {
        AplicacionSQLite aplicacion = new AplicacionSQLite();
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
                    //anadirParcela(aplicacion);
                    break;

                case 3:
                    System.out.println("Modificar Datos Terreno: 3");
                    //modificarTerrenos(aplicacion);
                    break;

                case 4:
                    System.out.println("Modificar Datos Parcela: 4");
                    //modificarParcelas(aplicacion);
                    break;

                case 5:
                    System.out.println("Obtener Datos Terreno: 5");
                    verTerrenos(aplicacion);

                    break;
                /*
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
*/
                default:
                    System.out.println("No hay operación asociadan");
            }

            System.out.println("");
            System.out.println("");
            System.out.println("");
        }

    }
    private static void anadirTerreno(AplicacionSQLite aplicacion) {
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

    /**
     * Muestra los parametros que te interese ver del terreno que le solicites
     * indicando su identificador, o de todos en caso de que escribas 0
     * @param aplicacion
     */
    private static void verTerrenos(AplicacionSQLite aplicacion) throws NoTerrenoException {
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
            /*for(Map.Entry<Integer,Terreno> entry: aplicacion.getListaTerrenos().entrySet()) {
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
            }*/
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
}
