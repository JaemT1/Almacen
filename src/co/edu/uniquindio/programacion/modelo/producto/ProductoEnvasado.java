package co.edu.uniquindio.programacion.modelo.producto;

/**
 * @author Juan Tunubala, Emanuel Rodríguez y Nicolás Ríos
 */
public class ProductoEnvasado {
    private String fechaEnvasado;
    private double pesoEnvase;
    private String paisOrigen;
    
    //Constructor

    public ProductoEnvasado(String fechaEnvasado, double pesoEnvase, String paisOrigen) {
        this.fechaEnvasado = fechaEnvasado;
        this.pesoEnvase = pesoEnvase;
        this.paisOrigen = paisOrigen;
    }
    
    //Getters y Setters

    public String getFechaEnvasado() {
        return fechaEnvasado;
    }

    public void setFechaEnvasado(String fechaEnvasado) {
        this.fechaEnvasado = fechaEnvasado;
    }

    public double getPesoEnvase() {
        return pesoEnvase;
    }

    public void setPesoEnvase(double pesoEnvase) {
        this.pesoEnvase = pesoEnvase;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }
    
}
