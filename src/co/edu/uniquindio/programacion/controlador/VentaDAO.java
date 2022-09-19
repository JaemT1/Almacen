package co.edu.uniquindio.programacion.controlador;

import co.edu.uniquindio.programacion.modelo.venta.DetalleVenta;
import co.edu.uniquindio.programacion.modelo.venta.Venta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Juan Tunubala, Emanuel Rodríguez y Nicolás Ríos
 */
public class VentaDAO {
    private Connection conn;

    private String usuario;

    private String contrasena;

    private String urlCadena;

    public VentaDAO() {
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
    *Método que establece la conexion con la base de datos
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
    *Método que cierra la conexión con la base de datos
    */
    public void cerrarConexion() {

        try {
            conn.close();
            conn = null;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Funciones DAO de las ventas
    
    /**
     * Método que obtiene el último id de venta
     * @return Retorna el último id de venta
     */
    public int obtenerIdVentas(){       
        
        int id_venta = 0;

        establecerConexion();
        ResultSet resultados = null;
        PreparedStatement pstmt= null;
        
        try {
            resultados = conn.createStatement().executeQuery("SELECT max(codigo_venta) from almacen.ventas;");
            while (resultados.next()) {
                
                id_venta = resultados.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error en obtenerIdVentas " + ex.getMessage());
        }finally{
            if (conn!=null) {
                try {
                    conn.close();
                    resultados.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando obtenerIdVentas " + ex.getMessage());
                }
            }
        }
        return id_venta;
    }

    /**
     * Método que guarda la venta en la base de datos
     * @param venta Datos de la venta a guardar
     */
    public void guardarVenta(Venta venta) {
        establecerConexion();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("insert into almacen.ventas values (?,?,?,?,?);");
  
            pstmt.setInt(1, venta.getCodigoVenta());
            pstmt.setString(2, venta.getFechaVenta());
            pstmt.setInt(3, venta.getCliente());
            pstmt.setDouble(4, venta.getTotal());
            pstmt.setDouble(5, venta.getIva());
            
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en guardarVenta " + ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    cerrarConexion();
                    pstmt.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando guardarVenta " + ex.getMessage());
                }
            }
        }
    }

    /**
     * Método que guarda el detalle de venta en la base de datos
     * @param detalleVenta Detalles de la venta
     * @param codigoVenta Código de la venta realizada
     */
    public void guardarDetalleVenta(DetalleVenta detalleVenta, int codigoVenta) {
        establecerConexion();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("insert into almacen.detalle_venta values (?,?,?,?);");
  
            pstmt.setInt(1, codigoVenta);
            pstmt.setInt(2, detalleVenta.getCantProduc());
            pstmt.setInt(3, detalleVenta.getProductVendido());
            pstmt.setDouble(4, detalleVenta.getSubtotal());
            
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en guardarDetalleVenta " + ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    cerrarConexion();
                    pstmt.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando guardarDetalleVenta " + ex.getMessage());
                }
            }
        }
    }
    
}
