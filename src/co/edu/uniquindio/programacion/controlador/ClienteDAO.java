package co.edu.uniquindio.programacion.controlador;

import co.edu.uniquindio.programacion.modelo.cliente.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * @author Juan Tunubala, Emanuel Rodríguez y Nicolás Ríos
 */

public class ClienteDAO {
    private Connection conn;

    private String usuario;

    private String contrasena;

    private String urlCadena;

    public ClienteDAO() {
        inicializar();
    }

    //Conexión a la base de datos
    
    /**
     * Método que inicializa los valores para la posterior conexión
     */
    public void inicializar() {
        try {
            this.urlCadena = "jdbc:mysql://localhost:3306/Almacen";
            
            this.usuario = "root";
            
            this.contrasena = "admin";
            
            final String driver = "com.mysql.cj.jdbc.Driver";
            
            Class.forName(driver);
            
            System.out.println("Iniciado con Exito");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error inicializando conexión " + ex.getMessage());
        }
    }
    
    /**
     * Método que establece la conexión con la base de datos
     */
    public void establecerConexion() {
        try {
            conn = DriverManager.getConnection(urlCadena, usuario, contrasena);
            System.out.println("Conexion establecida");
        } catch (SQLException ex) {
            System.out.println("Error establecerConexion " + ex.getMessage());
        }
    }

    /**
     * Método que cierra la conexión con la base de datos
     */
    public void cerrarConexion() {

        try {
            conn.close();
            conn = null;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Funciones DAO Cliente
    
    /**
     * Función que permite guardar un registro de cliente en la base de datos
     * @param cliente Cliente a guardar
     */
    public void guardarCliente(Cliente cliente) {
        establecerConexion();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("insert into almacen.cliente values (?,?,?,"
                    + "?,?,?, ?, ?, ?);");
  
            pstmt.setString(1, cliente.getNombres());
            pstmt.setString(2, cliente.getApellidos());
            pstmt.setInt(3, cliente.getIdentificacion());
            pstmt.setString(4, cliente.getDireccion());
            pstmt.setString(5, String.valueOf(cliente.getTelefono()));
            if (cliente.getTipoCliente().getClienteNatural() != null) {
                pstmt.setString(6, "Natural");
                pstmt.setString(7, cliente.getTipoCliente().getClienteNatural().getEmail());
                pstmt.setString(8, cliente.getTipoCliente().getClienteNatural().getFechaNacimiento());
                pstmt.setString(9, "No Requiere");
            }else{
                pstmt.setString(6, "Jurídico");
                pstmt.setString(7, "No Requiere");
                pstmt.setString(8, "No Requiere");
                pstmt.setString(9, cliente.getTipoCliente().getClienteJuridico().getNit());
            }
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en guardarCliente " + ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    cerrarConexion();
                    pstmt.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando guardarCliente " + ex.getMessage());
                }
            }
        }
    }
    
    /**
     * Método que permite mostrar los clientes en un JTable
     * @return Retorna el modelo de tabla que será puesto en la tabla de clientes
     */
    public DefaultTableModel mostrarClientes(){
        String [] nombresColumnas = {"Nombre", "Apellidos", "Identificación", "Dirección", "Teléfono", "Tipo Cliente", "Email", "Fecha Nacimiento", "Nit"};
        String [] registros = new String[9];
        
        DefaultTableModel modelo = new DefaultTableModel(null, nombresColumnas);

        establecerConexion();
        ResultSet resultados = null;
        PreparedStatement pstmt= null;
        
        try {
            resultados = conn.createStatement().executeQuery("select * from almacen.cliente;");

            while (resultados.next()) {
                
                registros[0] = resultados.getString("nombre");
                registros[1] = resultados.getString("apellidos");
                registros[2] = resultados.getString("identificacion");
                registros[3] = resultados.getString("direccion");
                registros[4] = resultados.getString("telefono");
                registros[5] = resultados.getString("tipo_cliente");
                registros[6] = resultados.getString("email");
                registros[7] = resultados.getString("fecha_nacimiento");
                registros[8] = resultados.getString("nit");
                modelo.addRow(registros);
            }
        } catch (SQLException ex) {
            System.out.println("Error en mostrarClientes " + ex.getMessage());
        }finally{
            if (conn!=null) {
                try {
                    conn.close();
                    resultados.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando mostrarClientes " + ex.getMessage());
                }
            }
        }
        return modelo;
    }
    
    /**
     * Método que permite eliminar un cliente
     * @param identificacion Identificación del cliente a eliminar
     */
    public void eliminarCliente(String identificacion){
        establecerConexion();
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement("delete from almacen.cliente where identificacion = ? ;");
                    pstmt.setString(1, identificacion);
                    pstmt.executeUpdate();
                    pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Error en eliminarCliente " + ex.getMessage());
        }finally{
            if (conn != null){
                try {
                    cerrarConexion();
                    pstmt.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando eliminarCliente: " + ex.getMessage());
                }
            }
        }
    }
    
    /**
     * Metodo que busca un cliente por la identificacion
     * @param identificacion Identificación del cliente a buscar
     * @return Retorna los datos del cliente encontrado
     */
    public Cliente buscarCliente(String identificacion){
        establecerConexion();
        Cliente clienteEncontrado = null;
        PreparedStatement pstmt = null;
        ResultSet resultados = null;
        try {
            pstmt = conn.prepareStatement("select * from almacen.cliente where identificacion like ? ;");
            pstmt.setString(1, identificacion);
            resultados = pstmt.executeQuery();
            
            String nombres = "";
            String apellidos = "";
            int identificacionC = 0;
            String direccion = "";
            String telefono = "";
            String tipoCliente = "";
            String email = "";
            String fechaNacimiento = "";
            String nit = "";
            
            while (resultados.next()) {
                
                nombres = resultados.getString(1);
                apellidos = resultados.getString(2);
                identificacionC = Integer.parseInt(resultados.getString(3));
                direccion = resultados.getString(4);
                telefono = resultados.getString(5);
                tipoCliente = resultados.getString(6);
                email = resultados.getString(7);
                fechaNacimiento = resultados.getString(8);
                nit = resultados.getString(9);                             
            }
            if (tipoCliente.equals("Natural")) {
                    ClienteNatural clNatural = new ClienteNatural(email,fechaNacimiento);
                    TipoCliente tpClienteN = new TipoCliente(clNatural);
                    clienteEncontrado = new Cliente(nombres, apellidos, identificacionC, direccion, telefono, tpClienteN);
                }else if (tipoCliente.equals("Jurídico")) {
                    ClienteJuridico clJuridico = new ClienteJuridico(nit);
                    TipoCliente tpClienteJ = new TipoCliente(clJuridico);
                    clienteEncontrado = new Cliente(nombres, apellidos, identificacionC, direccion, telefono, tpClienteJ);
                }           
        } catch (SQLException ex) {
            System.out.println("Error en buscarCliente: " + ex.getMessage());
        }finally{
            if (conn!=null) {
                try {
                    conn.close();
                    pstmt.close();
                    resultados.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando buscarCliente " + ex.getMessage());
                }
                
            }
        }
        return clienteEncontrado;
    }
    
    /**
     * Método que permite editar clientes
     * @param cliente Cliente a editar
     */
    public void editarCliente(Cliente cliente) {
        establecerConexion();
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement("update almacen.cliente set nombre = ?,"
                    + "apellidos = ?,direccion = ?,telefono = ?, email = ?, fecha_nacimiento = ?, nit = ? where identificacion like ?;");
                    pstmt.setString(1, cliente.getNombres());
                    pstmt.setString(2, cliente.getApellidos());
                    pstmt.setString(3, cliente.getDireccion());
                    pstmt.setString(4, cliente.getTelefono());
                    if (cliente.getTipoCliente().getClienteNatural() == null) {
                        pstmt.setString(5, "No Requiere");
                        pstmt.setString(6, "No Requiere");
                        pstmt.setString(7, String.valueOf(cliente.getTipoCliente().getClienteJuridico().getNit()));
                    }else{
                        pstmt.setString(5, cliente.getTipoCliente().getClienteNatural().getEmail());
                        pstmt.setString(6, cliente.getTipoCliente().getClienteNatural().getFechaNacimiento());
                        pstmt.setString(7, "No Requiere");
                    }                    
                    pstmt.setString(8, String.valueOf(cliente.getIdentificacion()));
                    pstmt.executeUpdate();
                    pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Error en editarCliente " + ex.getMessage());
        }finally{
            if (conn != null){
                try {
                    cerrarConexion();
                    pstmt.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando editarCliente: " + ex.getMessage());
                }
            }
        }
        
    }
    
}