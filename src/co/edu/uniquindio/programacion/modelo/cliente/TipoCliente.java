package co.edu.uniquindio.programacion.modelo.cliente;

/**
 * @author Juan Tunubala, Emanuel Rodríguez y Nicolás Ríos
 */
public class TipoCliente {
    ClienteNatural clienteNatural;
    ClienteJuridico clienteJuridico;
    
    //Constructors

    public TipoCliente(ClienteNatural clienteNatural) {
        this.clienteNatural = clienteNatural;
    }

    public TipoCliente(ClienteJuridico clienteJuridico) {
        this.clienteJuridico = clienteJuridico;
    }
    
    
    
    //Getters y Setters
    public ClienteNatural getClienteNatural() {
        return clienteNatural;
    }

    public void setClienteNatural(ClienteNatural clienteNatural) {
        this.clienteNatural = clienteNatural;
    }

    public ClienteJuridico getClienteJuridico() {
        return clienteJuridico;
    }

    public void setClienteJuridico(ClienteJuridico clienteJuridico) {
        this.clienteJuridico = clienteJuridico;
    }
}
