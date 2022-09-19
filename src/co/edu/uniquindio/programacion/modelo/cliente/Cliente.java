package co.edu.uniquindio.programacion.modelo.cliente;

/**
 * @author Juan Tunubala, Emanuel Rodríguez y Nicolás Ríos
 */
public class Cliente {
    private String nombres;
    private String apellidos;
    private int identificacion;
    private String direccion;
    private String telefono;
    private TipoCliente tipoCliente;

    //Constructor
    public Cliente(String nombres, String apellidos, int identificacion, String direccion, String telefono, TipoCliente tipoCliente) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoCliente = tipoCliente;
    }
    
    //Getters y Setters
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    } 
}
