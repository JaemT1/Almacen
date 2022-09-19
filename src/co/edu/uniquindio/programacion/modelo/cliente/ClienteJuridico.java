package co.edu.uniquindio.programacion.modelo.cliente;

/**
 * @author Juan Tunubala, Emanuel Rodríguez y Nicolás Ríos
 */
public class ClienteJuridico {
    private String nit;
    
    //Constructor

    public ClienteJuridico(String nit) {
        this.nit = nit;
    }
    
    //Getters y Setters
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }   
}
