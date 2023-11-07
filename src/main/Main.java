package main;

import java.util.Map;

public class Main {

    public static void visorTerrenosYParcelas(Aplicacion aplicacion)
    {
        //Ver las parcelas de los terrenos
        for(Map.Entry<Integer,Terreno> entry: aplicacion.getListaTerrenos().entrySet())
        {
            int idTerreno = entry.getKey();
            Terreno terr = entry.getValue();
            System.out.println("---------------Datos de terreno con id = " + idTerreno + " ------------------");
            int[] arrayGetters = {0,0,0};
            Object[] datosTerreno = aplicacion.getTerreno(arrayGetters, idTerreno);
            System.out.println("-> Tamaño = " + datosTerreno[0]);
            System.out.println("-> Ubicación = " + datosTerreno[2].toString());
            Ubicacion[] limites = (Ubicacion[]) datosTerreno[1];
            System.out.print("-> Limites = [");
            for(int i = 0; i < terr.getLimites().length; i++)
                System.out.print(limites[i].toString());
            System.out.println("]");
            System.out.println("----> Parcelas ");

            for(Map.Entry<Integer,Parcelas> entry2: terr.getParcelasTerreno().entrySet())
            {
                int idParcela = entry2.getKey();
                Parcelas par = entry2.getValue();
                int[] arrayGetters2 = {0,0};
                Object[] datosParcela = aplicacion.getParcela(arrayGetters2,idParcela);
                System.out.println("-------Datos de parcela con id = " + idParcela + " ----------");
                System.out.println("-> Ubicación = " + datosParcela[1]);
                Ubicacion[] limitesP = (Ubicacion[]) datosParcela[0];
                System.out.print("-> Limites = [");
                for(int i = 0; i < par.getLimites().length; i++)
                    System.out.print(limitesP[i]);
                System.out.println("]");
            }
            System.out.println("---------------Fin de datos de terreno con id = " + idTerreno + " ------------------");
        }
    }

    public static void main(String[] args) throws NoTerrenoException {
        Aplicacion aplicacion = new Aplicacion();

        aplicacion.addTerreno(144,
                new Ubicacion[]{new Ubicacion(12, 12), new Ubicacion(12, 24), new Ubicacion(24, 12),
                        new Ubicacion(24, 24)}
                , new Ubicacion(18, 18));
        aplicacion.addTerreno(100,
                new Ubicacion[]{new Ubicacion(0, 0), new Ubicacion(10, 0), new Ubicacion(0, 10),
                        new Ubicacion(10, 10)}
                , new Ubicacion(5, 5));
        aplicacion.addParcela(1, new Ubicacion[]{new Ubicacion(12, 12), new Ubicacion(12, 14),
                new Ubicacion(14, 12), new Ubicacion(14, 14)}, new Ubicacion(13, 13));

        aplicacion.addParcela(1,new Ubicacion[]{new Ubicacion(22, 22), new Ubicacion(22, 24),
                new Ubicacion(24, 22), new Ubicacion(24, 24)}, new Ubicacion(23, 23));

        aplicacion.addParcela(2,new Ubicacion[]{new Ubicacion(1, 1), new Ubicacion(1, 3),
                new Ubicacion(3, 1), new Ubicacion(3, 3)}, new Ubicacion(2, 2));
        aplicacion.addParcela(2,new Ubicacion[]{new Ubicacion(6, 6), new Ubicacion(6, 10),
                new Ubicacion(10, 6), new Ubicacion(10, 10)}, new Ubicacion(8, 8));

        aplicacion.addParcela(1,new Ubicacion[]{new Ubicacion(15, 15), new Ubicacion(15, 19),
                new Ubicacion(15, 19), new Ubicacion(19, 19)}, new Ubicacion(17, 17));

        //Ver las parcelas de los terrenos
        visorTerrenosYParcelas(aplicacion);
    }
}
