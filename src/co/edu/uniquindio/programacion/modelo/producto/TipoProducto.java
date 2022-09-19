package co.edu.uniquindio.programacion.modelo.producto;

/**
 * @author Juan Tunubala, Emanuel Rodríguez y Nicolás Ríos
 */
public class TipoProducto {
    private ProductoPerecedero prPerecedero;
    private ProductoEnvasado prEnvasado;
    private ProductoRefrigerado prRefrigerado;
    
    //Constructores
    public TipoProducto(ProductoPerecedero prPerecedero) {
        this.prPerecedero = prPerecedero;
    }

    public TipoProducto(ProductoEnvasado prEnvasado) {
        this.prEnvasado = prEnvasado;
    }

    public TipoProducto(ProductoRefrigerado prRefrigerado) {
        this.prRefrigerado = prRefrigerado;
    }
    
    //Getters y Setters

    public ProductoPerecedero getPrPerecedero() {
        return prPerecedero;
    }

    public void setPrPerecedero(ProductoPerecedero prPerecedero) {
        this.prPerecedero = prPerecedero;
    }

    public ProductoEnvasado getPrEnvasado() {
        return prEnvasado;
    }

    public void setPrEnvasado(ProductoEnvasado prEnvasado) {
        this.prEnvasado = prEnvasado;
    }

    public ProductoRefrigerado getPrRefrigerado() {
        return prRefrigerado;
    }

    public void setPrRefrigerado(ProductoRefrigerado prRefrigerado) {
        this.prRefrigerado = prRefrigerado;
    }
    
}
