package main;

public class Recibo {
    int numRecibo;
    String fechaEmision;
    float importeAlquiler;
    String tipoImpuesto;
    float impuesto;
    boolean pagado;
    int idAlquiler;

    public Recibo(int numRecibo, String fechaEmision, float importeAlquiler, String tipoImpuesto, float impuesto, boolean pagado, int idAlquiler) {
        this.numRecibo = numRecibo;
        this.fechaEmision = fechaEmision;
        this.importeAlquiler = importeAlquiler;
        this.tipoImpuesto = tipoImpuesto;
        this.impuesto = impuesto;
        this.pagado = pagado;
        this.idAlquiler = idAlquiler;
    }

    public void pagado() {
        this.pagado = true;
    }

    public int getNumRecibo() {
        return numRecibo;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public float getImporteAlquiler() {
        return importeAlquiler;
    }

    public String getTipoImpuesto() {
        return tipoImpuesto;
    }

    public float getImpuesto() {
        return impuesto;
    }

    public boolean isPagado() {
        return pagado;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }
}
