package clases;

import java.util.Date;

public class Factura {
    private String numeroFactura;
    private String cliente;
    private Date fecha;
    private double montoTotal;
    private String firmaDigital;
    

    public Factura() {
    }

    public Factura(String numeroFactura, String cliente, Date fecha, double montoTotal, String firmaDigital) {
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
          this.fecha = fecha != null ? new Date(fecha.getTime()) : null;
        this.montoTotal = montoTotal;
        this.firmaDigital = firmaDigital;
    }
   

  

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getFirmaDigital() {
        return firmaDigital;
    }

    public void setFirmaDigital(String firmaDigital) {
        this.firmaDigital = firmaDigital;
    }
}
