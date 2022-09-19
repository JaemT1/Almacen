package co.edu.uniquindio.programacion.controlador;

import co.edu.uniquindio.programacion.modelo.producto.*;
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
public class ProductoDAO {
   
    private Connection conn;

    private String usuario;

    private String contrasena;

    private String urlCadena;

    public ProductoDAO() {
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
    *Método que establece conexion con la base de datos
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
    *Método que cierra la conexion con la base de datos
    */
    public void cerrarConexion() {

        try {
            conn.close();
            conn = null;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Funciones DAO del producto
    
    /**
     * Método que permite agregar un producto
     * @param producto Producto a agregar
     * @param tipoProducto Su tipo
     */
    public void guardarProducto(Producto producto, String tipoProducto) {
        establecerConexion();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("insert into almacen.producto values (?,?,?,?,?,?);");
  
            pstmt.setInt(1, producto.getCodigo());
            pstmt.setString(2, producto.getNombre());
            pstmt.setString(3, producto.getDescripcion());
            pstmt.setDouble(4, producto.getValorUnitario());
            pstmt.setInt(5, producto.getCantExistente());
            pstmt.setString(6, tipoProducto);
            
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en guardarProducto " + ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    cerrarConexion();
                    pstmt.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando guardarProducto " + ex.getMessage());
                }
            }
        }
    }
    
    /**
     * Método que permite agregar un producto envasado
     * @param producto Producto a agregar
     */
    public void guardarProductoEnvasado(Producto producto) {
        establecerConexion();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("insert into almacen.producto_envasado values (?,?,?,?);");
  
            pstmt.setInt(1, producto.getCodigo());
            pstmt.setString(2, producto.getTipoProducto().getPrEnvasado().getFechaEnvasado());
            pstmt.setDouble(3, producto.getTipoProducto().getPrEnvasado().getPesoEnvase());
            pstmt.setString(4, producto.getTipoProducto().getPrEnvasado().getPaisOrigen());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en guardarProductoEnvasado " + ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    cerrarConexion();
                    pstmt.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando guardarProductoEnvasado " + ex.getMessage());
                }
            }
        }
    }
    
    /**
     * Método que permite agregar un producto perecedero
     * @param producto Producto a agregar
     */
    public void guardarProductoPerecedero(Producto producto) {
        establecerConexion();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("insert into almacen.producto_perecedero values (?,?);");
  
            pstmt.setInt(1, producto.getCodigo());
            pstmt.setString(2, producto.getTipoProducto().getPrPerecedero().getFechaVencimiento());    

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en guardarProductoPerecedero " + ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    cerrarConexion();
                    pstmt.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando guardarProductoPerecedero " + ex.getMessage());
                }
            }
        }
    }
    
    /**
     * Método que permite agregar un producto refrigerado
     * @param producto Producto a agregar
     */
    public void guardarProductoRefrigerado(Producto producto) {
        establecerConexion();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("insert into almacen.producto_refrigerado values (?,?,?);");
  
            pstmt.setInt(1, producto.getCodigo());
            pstmt.setString(2, producto.getTipoProducto().getPrRefrigerado().getCodigoAprobacion());
            pstmt.setDouble(3, producto.getTipoProducto().getPrRefrigerado().getTempRecomendada());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en guardarProductoRefrigerado " + ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    cerrarConexion();
                    pstmt.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando guardarProductoRefrigerado " + ex.getMessage());
                }
            }
        }
    }
    
    /**
     * Función que muestra todos los productos perecederos
     * @return Retorna el modelo que será puesto en la tabla de productos
     */
    public DefaultTableModel mostrarProductosPerecederos(){
        String [] nombresColumnas = {"Código", "Nombre", "Descripción", "ValorUnitario", "CantidadExistente", 
            "TipoProducto", "FechaEnvase", "PesoEnvase", "PaisOrigen", "CódigoAprob", "TempRecomendada", "FechaVenc"};
        String [] registros = new String[12];
        
        DefaultTableModel modelo = new DefaultTableModel(null, nombresColumnas);

        establecerConexion();
        ResultSet resultados = null;
        PreparedStatement pstmt= null;
        
        try {
            resultados = conn.createStatement().executeQuery("SELECT producto.codigo,nombre,descripcion,valor_unitario,cantidad_existente,tipo_producto,fecha_vencimiento FROM almacen.producto\n" +
                                                             "INNER JOIN producto_perecedero\n" +
                                                             "ON producto_perecedero.codigo = producto.codigo;");
            while (resultados.next()) {
                
                registros[0] = resultados.getString("codigo");
                registros[1] = resultados.getString("nombre");
                registros[2] = resultados.getString("descripcion");
                registros[3] = resultados.getString("valor_unitario");
                registros[4] = resultados.getString("cantidad_existente");
                registros[5] = resultados.getString("tipo_producto");
                registros[6] = "----";
                registros[7] = "----";
                registros[8] = "----";
                registros[9] = "----";
                registros[10] = "----";
                registros[11] = resultados.getString("fecha_vencimiento");
                modelo.addRow(registros);
            }
        } catch (SQLException ex) {
            System.out.println("Error en mostrarProductosPerecederos " + ex.getMessage());
        }finally{
            if (conn!=null) {
                try {
                    conn.close();
                    resultados.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando mostrarProductosPerecederos " + ex.getMessage());
                }
            }
        }
        return modelo;
    }
    
    /**
     * Función que muestra todos los productos envasados
     * @param modelo Modelo que ya tiene los datos de los productos antes obtenidos
     * @return Retorna el modelo que será puesto en la tabla de productos
     */
    public DefaultTableModel mostrarProductosEnvasados(DefaultTableModel modelo){
        String [] registros = new String[12];
        
        DefaultTableModel modeloAux = modelo;

        establecerConexion();
        ResultSet resultados = null;
        PreparedStatement pstmt= null;
        
        try {
            resultados = conn.createStatement().executeQuery("SELECT producto.codigo,nombre,descripcion,valor_unitario,cantidad_existente,tipo_producto,fecha_envasado,peso_envase,pais_origen FROM almacen.producto\n" +
                                                             "INNER JOIN producto_envasado\n" +
                                                             "WHERE producto_envasado.codigo = producto.codigo;");
            while (resultados.next()) {
                
                registros[0] = resultados.getString("codigo");
                registros[1] = resultados.getString("nombre");
                registros[2] = resultados.getString("descripcion");
                registros[3] = resultados.getString("valor_unitario");
                registros[4] = resultados.getString("cantidad_existente");
                registros[5] = resultados.getString("tipo_producto");
                registros[6] = resultados.getString("fecha_envasado");
                registros[7] = resultados.getString("peso_envase");
                registros[8] = resultados.getString("pais_origen");
                registros[9] = "----";
                registros[10] = "----";
                registros[11] = "----";
                modeloAux.addRow(registros);
            }
        } catch (SQLException ex) {
            System.out.println("Error en mostrarProductosEnvasados " + ex.getMessage());
        }finally{
            if (conn!=null) {
                try {
                    conn.close();
                    resultados.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando mostrarProductosEnvasados " + ex.getMessage());
                }
            }
        }
        return modeloAux;
    }
    
    /**
     * Función que muestra todos los productos refrigerados
     * @param modelo Modelo que ya tiene los datos de los productos antes obtenidos
     * @return Retorna el modelo que será puesto en la tabla de productos
     */
    public DefaultTableModel mostrarProductosRefrigerados(DefaultTableModel modelo){
        String [] registros = new String[12];
        
        DefaultTableModel modeloAux = modelo;

        establecerConexion();
        ResultSet resultados = null;
        PreparedStatement pstmt= null;
        
        try {
            resultados = conn.createStatement().executeQuery("SELECT producto.codigo,nombre,descripcion,valor_unitario,cantidad_existente,tipo_producto,codigo_aprobacion,temperatura_recomendada FROM almacen.producto\n" +
                                                             "INNER JOIN producto_refrigerado\n" +
                                                             "WHERE producto_refrigerado.codigo = producto.codigo;");
            while (resultados.next()) {
                
                registros[0] = resultados.getString("codigo");
                registros[1] = resultados.getString("nombre");
                registros[2] = resultados.getString("descripcion");
                registros[3] = resultados.getString("valor_unitario");
                registros[4] = resultados.getString("cantidad_existente");
                registros[5] = resultados.getString("tipo_producto");
                registros[6] = "----";
                registros[7] = "----";
                registros[8] = "----";
                registros[9] = resultados.getString("codigo_aprobacion");
                registros[10] = resultados.getString("temperatura_recomendada");
                registros[11] = "----";
                modeloAux.addRow(registros);
            }
        } catch (SQLException ex) {
            System.out.println("Error en mostrarProductosRefrigerados " + ex.getMessage());
        }finally{
            if (conn!=null) {
                try {
                    conn.close();
                    resultados.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando mostrarProductosRefrigerados " + ex.getMessage());
                }
            }
        }
        return modeloAux;
    }
    
    /**
     * Función que elimina un producto en base a su código
     * @param codigo Códigp del producto a eliminar
     */
    public void eliminarProducto(int codigo){
        establecerConexion();
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement("delete from almacen.producto where codigo = ? ;");
                    pstmt.setInt(1, codigo);
                    pstmt.executeUpdate();
                    pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Eror en eliminarProducto " + ex.getMessage());
        }finally{
            if (conn != null){
                try {
                    cerrarConexion();
                    pstmt.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando eliminarProducto: " + ex.getMessage());
                }
            }
        }
    }
    
    /**
     * Función que permite editar un producto base
     * @param producto Producto a editar
     */
    public void editarProducto(Producto producto) {
        establecerConexion();
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement("update almacen.producto set nombre = ?,descripcion = ?,valor_unitario = ?, cantidad_existente = ? where codigo like ?;");
                    
                    pstmt.setString(1, producto.getNombre());
                    pstmt.setString(2, producto.getDescripcion());
                    pstmt.setDouble(3, producto.getValorUnitario());
                    pstmt.setInt(4, producto.getCantExistente());
                    pstmt.setInt(5, producto.getCodigo());
                    
                    pstmt.executeUpdate();
                    pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Error en editarProducto " + ex.getMessage());
        }finally{
            if (conn != null){
                try {
                    cerrarConexion();
                    pstmt.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando editarProducto: " + ex.getMessage());
                }
            }
        }
        
    }
    
    /**
     * Función que permite editar un producto perecedero
     * @param producto Producto a editar
     */
    public void editarProductoPerecedero(Producto producto) {
        establecerConexion();
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement("update almacen.producto_perecedero set fecha_vencimiento = ? where codigo like ?;");
                    
                    pstmt.setString(1, producto.getTipoProducto().getPrPerecedero().getFechaVencimiento());
                    pstmt.setInt(2, producto.getCodigo());
                    
                    pstmt.executeUpdate();
                    pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Error en editarProductoPerecedero " + ex.getMessage());
        }finally{
            if (conn != null){
                try {
                    cerrarConexion();
                    pstmt.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando editarProductoPerecedero: " + ex.getMessage());
                }
            }
        }
        
    }
    
    /**
     * Función que permite editar un producto Envasado
     * @param producto Producto a editar
     */
    public void editarProductoEnvasado(Producto producto) {
        establecerConexion();
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement("update almacen.producto_envasado set fecha_envasado = ?, peso_envase = ?, pais_origen = ? where codigo like ?;");
                    
                    pstmt.setString(1, producto.getTipoProducto().getPrEnvasado().getFechaEnvasado());
                    pstmt.setDouble(2, producto.getTipoProducto().getPrEnvasado().getPesoEnvase());
                    pstmt.setString(3, producto.getTipoProducto().getPrEnvasado().getPaisOrigen());
                    pstmt.setInt(4, producto.getCodigo());
                    
                    pstmt.executeUpdate();
                    pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Error en editarProductoEnvasado: " + ex.getMessage());
        }finally{
            if (conn != null){
                try {
                    cerrarConexion();
                    pstmt.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando editarProductoEnvasado: " + ex.getMessage());
                }
            }
        }
        
    }
    
    /**
     * Función que permite editar un producto Refrigerado
     * @param producto Producto a editar
     */
    public void editarProductoRefrigerado(Producto producto) {
        establecerConexion();
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement("update almacen.producto_refrigerado set codigo_aprobacion = ?, temperatura_recomendada = ? where codigo like ?;");
                    
                    pstmt.setString(1, producto.getTipoProducto().getPrRefrigerado().getCodigoAprobacion());
                    pstmt.setDouble(2, producto.getTipoProducto().getPrRefrigerado().getTempRecomendada());
                    pstmt.setInt(3, producto.getCodigo());
                    
                    pstmt.executeUpdate();
                    pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Error en editarProductoRefrigerado: " + ex.getMessage());
        }finally{
            if (conn != null){
                try {
                    cerrarConexion();
                    pstmt.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando editarProductoRefrigerado: " + ex.getMessage());
                }
            }
        }
        
    }
    
    /**
     * Método que actualiza la cantidad existente de un producto en la base de datos
     * @param cantidadNueva La nueva cantidad a establecer
     * @param codigoProducto El código del producto a modificar
     */
    public void actualizarStockProducto(int cantidadNueva, int codigoProducto) {
        establecerConexion();
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement("update almacen.producto set cantidad_existente = ? where codigo = ?;");
                
                    pstmt.setInt(1, cantidadNueva);
                    pstmt.setInt(2, codigoProducto);
                    
                    pstmt.executeUpdate();
                    pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Error en actualizarStockProducto: " + ex.getMessage());
        }finally{
            if (conn != null){
                try {
                    cerrarConexion();
                    pstmt.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando actualizarStockProducto: " + ex.getMessage());
                }
            }
        }
        
    }
    
    /**
     * Método que permite buscar un producto en base a un codigo
     * @param codigo Código del producto a buscar
     * @return Retorna el producto encontrado
     */
    public Producto buscarProducto(int codigo){
        
        Producto miProducto;
        
        String nombre = "";
        String descripcion = "";
        double valorUnitario = 0;
        int cantExistente = 0;
        String tipoProducto = "";

        establecerConexion();
        ResultSet resultados = null;
        PreparedStatement pstmt= null;
        
        try {
            resultados = conn.createStatement().executeQuery("SELECT nombre,descripcion,valor_unitario,cantidad_existente,tipo_producto FROM almacen.producto WHERE codigo = " + codigo + ";");
            while (resultados.next()) {
                
                nombre = resultados.getString(1);
                descripcion = resultados.getString(2);
                valorUnitario = resultados.getDouble(3);
                cantExistente = resultados.getInt(4);
                tipoProducto = resultados.getString(5);

            }   
        } catch (SQLException ex) {
            System.out.println("Error en mostrarProductosPerecederos " + ex.getMessage());
        }finally{
            if (conn!=null) {
                try {
                    conn.close();
                    resultados.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando mostrarProductosPerecederos " + ex.getMessage());
                }
            }
        }
        
        miProducto = new Producto();
            
        switch (tipoProducto) {
            case "Perecedero":
                ProductoPerecedero prPerecedero = buscarProductoPerecedero(codigo);
                TipoProducto tpProductoPer = new TipoProducto(prPerecedero);
                miProducto = new Producto(codigo, nombre, descripcion, valorUnitario, cantExistente, tpProductoPer);
                break;
            case "Refrigerado":
                ProductoRefrigerado prRefrigerado = buscarProductoRefrigerado(codigo);
                TipoProducto tpProductoRefri = new TipoProducto(prRefrigerado);
                miProducto = new Producto(codigo, nombre, descripcion, valorUnitario, cantExistente, tpProductoRefri);  
                break;
            case "Envasado":
                ProductoEnvasado prEnvasado = buscarProductoEnvasado(codigo);
                TipoProducto tpProductoEnvasado = new TipoProducto(prEnvasado);
                miProducto = new Producto(codigo, nombre, descripcion, valorUnitario, cantExistente, tpProductoEnvasado); 
                break;
            default:
                break;
        }
        
        
        return miProducto;
    }
    
    /**
     * Método que permite buscar un producto perecedero en base a un codigo
     * @param codigo Código del producto a buscar
     * @return Retorna el producto encontrado
     */
    public ProductoPerecedero buscarProductoPerecedero(int codigo){
        
        ProductoPerecedero miProductoPer;

        establecerConexion();
        ResultSet resultados = null;
        PreparedStatement pstmt= null;
        
        String fechaVenc = "";

        try {
            resultados = conn.createStatement().executeQuery("SELECT fecha_vencimiento FROM almacen.producto_perecedero WHERE codigo = " + codigo + ";");
            while (resultados.next()) {
                fechaVenc = resultados.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error en buscarProductoPerecedero " + ex.getMessage());
        }finally{
            if (conn!=null) {
                try {
                    conn.close();
                    resultados.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando buscarProductoPerecedero " + ex.getMessage());
                }
            }
        }
        miProductoPer = new ProductoPerecedero(fechaVenc);
        return miProductoPer;
    }
    
    /**
     * Método que permite buscar un producto envasado en base a un codigo
     * @param codigo Código del producto a buscar
     * @return Retorna el producto encontrado
     */
    public ProductoEnvasado buscarProductoEnvasado(int codigo){
        
        ProductoEnvasado miProductoEnv;

        establecerConexion();
        ResultSet resultados = null;
        PreparedStatement pstmt= null;
        
        String fechaEnv = "";
        double pesoEnvase = 0;
        String paisOrigen = "";

        try {
            resultados = conn.createStatement().executeQuery("SELECT fecha_envasado, peso_envase, pais_origen  FROM almacen.producto_envasado WHERE codigo = " + codigo + ";");
            while (resultados.next()) {
                fechaEnv = resultados.getString(1);
                pesoEnvase = resultados.getDouble(2);
                paisOrigen = resultados.getString(3);
            }
        } catch (SQLException ex) {
            System.out.println("Error en buscarProductoEnvasado " + ex.getMessage());
        }finally{
            if (conn!=null) {
                try {
                    conn.close();
                    resultados.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando buscarProductoEnvasado " + ex.getMessage());
                }
            }
        }
        miProductoEnv = new ProductoEnvasado(fechaEnv, pesoEnvase, paisOrigen);
        return miProductoEnv;
    }
    
    /**
     * Método que permite buscar un producto refrigerado en base a un codigo
     * @param codigo Código del producto a buscar
     * @return Retorna el producto encontrado
     */
    public ProductoRefrigerado buscarProductoRefrigerado(int codigo){
        
        ProductoRefrigerado miProductoRef;

        establecerConexion();
        ResultSet resultados = null;
        PreparedStatement pstmt= null;
        
        String codigoAprobacion = "";
        double tempRecomendada = 0;

        try {
            resultados = conn.createStatement().executeQuery("SELECT codigo_aprobacion, temperatura_recomendada FROM almacen.producto_refrigerado WHERE codigo = " + codigo + ";");
            while (resultados.next()) {
                codigoAprobacion = resultados.getString(1);
                tempRecomendada = resultados.getDouble(2);              
            }
        } catch (SQLException ex) {
            System.out.println("Error en buscarProductoRefrigerado " + ex.getMessage());
        }finally{
            if (conn!=null) {
                try {
                    conn.close();
                    resultados.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando buscarProductoRefrigerado " + ex.getMessage());
                }
            }
        }
        miProductoRef = new ProductoRefrigerado(codigoAprobacion, tempRecomendada);
        return miProductoRef;
    }

}