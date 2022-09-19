package co.edu.uniquindio.programacion.modelo.venta;

/**
 * @author Juan Tunubala, Emanuel Rodríguez y Nicolás Ríos
 */
public class DetalleVenta {
    private int cantProduc;
    private int productVendido;
    private double subtotal;
    
    //Constructor

    public DetalleVenta(int cantProduc, int productVendido, double subtotal) {
        this.cantProduc = cantProduc;
        this.productVendido = productVendido;
        this.subtotal = subtotal;
    }
    
    //Getters and Setters

    public int getCantProduc() {
        return cantProduc;
    }

    public void setCantProduc(int cantProduc) {
        this.cantProduc = cantProduc;
    }

    public int getProductVendido() {
        return productVendido;
    }

    public void setProductVendido(int productVendido) {
        this.productVendido = productVendido;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

}
