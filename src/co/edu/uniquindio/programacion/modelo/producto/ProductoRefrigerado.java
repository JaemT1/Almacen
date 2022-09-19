package co.edu.uniquindio.programacion.modelo.producto;

/**
 * @author Juan Tunubala, Emanuel Rodríguez y Nicolás Ríos
 */
public class ProductoRefrigerado {
    private String codigoAprobacion;
    private double tempRecomendada;
    
    //Constructor

    public ProductoRefrigerado(String codigoAprobacion, double tempRecomendada) {
        this.codigoAprobacion = codigoAprobacion;
        this.tempRecomendada = tempRecomendada;
    }
    
    //Getters y Setters

    public String getCodigoAprobacion() {
        return codigoAprobacion;
    }

    public void setCodigoAprobacion(String codigoAprobacion) {
        this.codigoAprobacion = codigoAprobacion;
    }

    public double getTempRecomendada() {
        return tempRecomendada;
    }

    public void setTempRecomendada(double tempRecomendada) {
        this.tempRecomendada = tempRecomendada;
    }
    
}
