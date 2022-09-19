package co.edu.uniquindio.programacion.modelo.producto;

/**
 * @author Juan Tunubala, Emanuel Rodríguez y Nicolás Ríos
 */
public class Producto {
    private int codigo;
    private String nombre;
    private String descripcion;
    private double valorUnitario;
    private int cantExistente;
    private TipoProducto tipoProducto;
    
    //Constructors

    public Producto() {
    }

    public Producto(int codigo, String nombre, String descripcion, double valorUnitario, int cantExistente) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valorUnitario = valorUnitario;
        this.cantExistente = cantExistente;
    }
    
    
    public Producto(int codigo, String nombre, String descripcion, double valorUnitario, int cantExistente, TipoProducto tipoProducto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valorUnitario = valorUnitario;
        this.cantExistente = cantExistente;
        this.tipoProducto = tipoProducto;
    }
    
    //Getters y Setters

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getCantExistente() {
        return cantExistente;
    }

    public void setCantExistente(int cantExistente) {
        this.cantExistente = cantExistente;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
    
}
