package main;

public class Alquiler {
    int idAlquiler;
    String fechaInicio;
    String fechaFin;
    int duracion;
    float importe;
    String dniArrendatario;
    int idParcela;

    public Alquiler(int idAlquiler, String fechaInicio, String fechaFin, int duracion, float importe, int idParcela, String dniArrendatario)
    {
        this.dniArrendatario = dniArrendatario;
        this.duracion = duracion;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.idAlquiler = idAlquiler;
        this.importe = importe;
        this.idParcela = idParcela; //TODO: ERROR código encontrado en pruebas
    }

    public String getFechaInicio()
    {
        return fechaInicio;
    }

    public String getFechaFin()
    {
        return fechaFin;
    }

    public int getDuracion()
    {
        return duracion;
    }

    public float getImporte()
    {
        return importe;
    }

    public int getIdParcela()
    {
        return idParcela;
    }

    public String getDniArrendatario()
    {
        return dniArrendatario;
    }
}
