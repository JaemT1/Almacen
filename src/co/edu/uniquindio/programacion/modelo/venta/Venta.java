package co.edu.uniquindio.programacion.modelo.venta;

/**
 * @author Juan Tunubala, Emanuel Rodríguez y Nicolás Ríos
 */
public class Venta {
    private int codigoVenta;
    private String fechaVenta;
    private DetalleVenta detalleVenta;
    private int cliente;
    private double total;
    private double iva;

    //Constructor

    public Venta(int codigoVenta, String fechaVenta, DetalleVenta detalleVenta, int cliente, double total, double iva) {
        this.codigoVenta = codigoVenta;
        this.fechaVenta = fechaVenta;
        this.detalleVenta = detalleVenta;
        this.cliente = cliente;
        this.total = total;
        this.iva = iva;
    }

    public Venta(int codigoVenta, String fechaVenta, int cliente, double total, double iva) {
        this.codigoVenta = codigoVenta;
        this.fechaVenta = fechaVenta;
        this.cliente = cliente;
        this.total = total;
        this.iva = iva;
    }

    
 
    //Getters and Setters

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public DetalleVenta getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }
    


}
