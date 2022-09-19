package co.edu.uniquindio.programacion.modelo.cliente;

/**
 * @author Juan Tunubala, Emanuel Rodríguez y Nicolás Ríos
 */
public class ClienteNatural {
    private String email;
    private String fechaNacimiento;
    
    //Constructor
    public ClienteNatural(String email, String fechaNacimiento) {
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    //Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    } 
}
