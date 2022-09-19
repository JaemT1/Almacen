package co.edu.uniquindio.programacion.modelo.producto;

/**
 * @author Juan Tunubala, Emanuel Rodríguez y Nicolás Ríos
 */
public class ProductoPerecedero {
    private String fechaVencimiento;
    
    //Constructors
    public ProductoPerecedero(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public ProductoPerecedero() {
    }
       
    
    //Getters y Setters

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    
}
