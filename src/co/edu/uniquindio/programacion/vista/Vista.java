package co.edu.uniquindio.programacion.vista;

import co.edu.uniquindio.programacion.controlador.*;
import co.edu.uniquindio.programacion.modelo.cliente.*;
import co.edu.uniquindio.programacion.modelo.producto.*;
import co.edu.uniquindio.programacion.modelo.venta.DetalleVenta;
import co.edu.uniquindio.programacion.modelo.venta.Venta;
import java.awt.Color;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author Juan Tunubala, Emanuel Rodríguez y Nicolás Ríos
 */
public class Vista extends javax.swing.JFrame {
    Producto productoComprar;

    int item = 0;
    int codigoProducto = 0;
    double totalAPagar = 0;
    double precioPr = 0;
    int cantidad = 0;
    double iva = 0;

    ClienteDAO miClienteDAO = new ClienteDAO();
    ProductoDAO miProductoDAO = new ProductoDAO();
    VentaDAO miVentaDAO = new VentaDAO();
    DefaultTableModel modelo = new DefaultTableModel();
   
    public Vista() {
        initComponents();
        this.setLocationRelativeTo(null);
        generarFecha();
        generarIdVenta();
        setearVendedor();
    }

    //Clientes
    
    /**
     * Método que oculta los campos de texto de la sección de clientes
     */
    public void ocultarTxtFieldsCliente() {
        txtNombre.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtIdentificacion.setEnabled(false);
        txtNit.setEnabled(false);
        txtEmail.setEnabled(false);
        txtFechaNac.setEnabled(false);
    }
    
    /**
    *Método que habilita los campos de texto de la sección de clientes
    */
    public void mostrarTxtFieldsCliente() {
        txtNombre.setEnabled(true);
        txtApellidos.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtIdentificacion.setEnabled(true);
        txtNit.setEnabled(true);
        txtEmail.setEnabled(true);
        txtFechaNac.setEnabled(true);
    }

    /**
    *Método que vacía los campos de texto de la sección de clientes
    */
    public void limpiarTxtFieldsCliente() {
        txtNombre.setText(null);
        txtApellidos.setText(null);
        txtDireccion.setText(null);
        txtTelefono.setText(null);
        txtIdentificacion.setText(null);
        txtNit.setText(null);
        txtEmail.setText(null);
        txtFechaNac.setText(null);
    }

    //Productos

    /**
    *Método que deshabilita los campos de texto de la sección de productos
    */
    public void ocultarTxtFieldsProducto() {
        txtCodigoProd.setEnabled(false);
        txtNombreProd.setEnabled(false);
        txtDescrProd.setEnabled(false);
        txtValorUn.setEnabled(false);
        txtCantExs.setEnabled(false);
        txtFechaVenc.setEnabled(false);
        txtFechaEnv.setEnabled(false);
        txtPesoEnv.setEnabled(false);
        CboxPaisOrigen.setEnabled(false);
        txtCodigoAprob.setEnabled(false);
        txtTempRecom.setEnabled(false);
    }
 
    /**
    *Método que deshabilita los campos de texto de tipos de producto
    */
    public void ocultarTxtFieldsTiposProducto() {
        txtFechaVenc.setEnabled(false);
        txtFechaEnv.setEnabled(false);
        txtPesoEnv.setEnabled(false);
        CboxPaisOrigen.setEnabled(false);
        txtCodigoAprob.setEnabled(false);
        txtTempRecom.setEnabled(false);
    }

    /**
    *Método que habilita los campos de texto de producto
    */
    public void mostrarTxtFieldsProducto() {
        txtCodigoProd.setEnabled(true);
        txtNombreProd.setEnabled(true);
        txtDescrProd.setEnabled(true);
        txtValorUn.setEnabled(true);
        txtCantExs.setEnabled(true);
        txtFechaVenc.setEnabled(true);
        txtFechaEnv.setEnabled(true);
        txtPesoEnv.setEnabled(true);
        CboxPaisOrigen.setEnabled(true);
        txtCodigoAprob.setEnabled(true);
        txtTempRecom.setEnabled(true);
    }
    
    /**
    *Método que vacía los campos de texto de la sección de productos
    */
    public void limpiarTxtFieldsProducto() {
        txtCodigoProd.setText(null);
        txtNombreProd.setText(null);
        txtDescrProd.setText(null);
        txtValorUn.setText(null);
        txtCantExs.setText(null);
        txtFechaVenc.setText(null);
        txtFechaEnv.setText(null);
        txtPesoEnv.setText(null);
        txtCodigoAprob.setText(null);
        txtTempRecom.setText(null);
    }

    //Ventas

    /**
    *Método que genera el código o id de la venta
    */
    public void generarIdVenta() {
        int idVenta = miVentaDAO.obtenerIdVentas();
        if (idVenta == 0) {
            txtCodigoVenta.setText("000001");
        } else {
            int incremento = idVenta;
            incremento += 1;
            txtCodigoVenta.setText("00000" + incremento);
        }
    }

    /**
    *Método que genera la fecha actual en la interfaz grafica
    */
    public void generarFecha() {
        Calendar calendario = new GregorianCalendar();
        int mes = calendario.get(Calendar.MONTH);
        txtFechaVenta.setText("" + calendario.get(Calendar.DAY_OF_MONTH) + "-" + (mes + 1) + "-" + calendario.get(Calendar.YEAR));
    }

    /**
     * Método que setea el nombre del vendedor
     */
    public void setearVendedor(){
       txtVendedor.setText("Ema Amado Bustos");
    }
    
    /**
     * Método que reinicia los campos de la sección de ventas
     */
    public void reiniciarCampos(){
        txtIdClienteVenta.setText(null);
        txtCodProducto.setText(null);
        txtPrecProducto.setText(null);
        txtCliente.setText(null);
        txtIdClienteVenta.setEnabled(true);
        txtProducto.setText(null);
        txtStock.setText(null);
        txtIva.setText(null);
        txtTotalAPagar.setText(null);
        spnCantProducto.setValue(0);
        limpiarTabla();
    }
    
    /**
     * Método que limpia la tabla de ventas
     */
    public void limpiarTabla(){
        for (int i = 0; i < tblVentas.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1; 
            
        }
    }

    /**
    * Método que agrega un producto dependiendo de su tipo
    */
    public void agregarProducto() {
        double total = 0;
        modelo = (DefaultTableModel)tblVentas.getModel();
        item+=1;
        codigoProducto = productoComprar.getCodigo();
        String descripcion = productoComprar.getDescripcion();
        precioPr = Double.parseDouble(txtPrecProducto.getText());
        cantidad = Integer.parseInt(spnCantProducto.getValue().toString());
        int stock = Integer.parseInt(txtStock.getText());
        total = cantidad * precioPr;
        if (stock > 0 && cantidad < stock) {
            Object[]registro = new Object[6];
            registro[0]=item;
            registro[1]= codigoProducto;
            registro[2]= descripcion;
            registro[3]= cantidad;
            registro[4]= precioPr;
            registro[5]= total;
            modelo.addRow(registro);
            tblVentas.setModel(modelo);
            calcularTotal();
        }else 
           JOptionPane.showMessageDialog(null, "Stock producto no disponiblr =(");
    }
    
    /**
    *Método que calcula el valor total a pagar de la compra realizada
    */
    public void calcularTotal(){
        totalAPagar = 0;
        for (int i = 0; i < tblVentas.getRowCount(); i++) {
            cantidad = Integer.parseInt(tblVentas.getValueAt(i, 3).toString());
            precioPr = Double.parseDouble(tblVentas.getValueAt(i, 4).toString());
            totalAPagar += (cantidad * precioPr);
        }
        calcularIva();
        txtTotalAPagar.setText(""+totalAPagar);  
    } 

    /**
    *Método que calcula el IVA de la compra 
    */
    public void calcularIva(){
       iva = 0;
       iva = (totalAPagar * 0.19);
       //totalAPagar += iva;
       txtIva.setText(""+iva);
    }

    /**
    *Método que guarda la venta en la base de datos
    */
    public void guardarVenta() {
        int codigoVenta = Integer.parseInt(txtCodigoVenta.getText());
        String fechaVenta = txtFechaVenta.getText();
        int idCliente = Integer.parseInt(txtIdClienteVenta.getText());
        double total = totalAPagar;
        double iva = this.iva;
        Venta miVenta = new Venta(codigoVenta, fechaVenta, idCliente, total, iva);
        miVentaDAO.guardarVenta(miVenta);
    }
    
    /**
    *Método que guarda el detalle de venta en la base de datos
    */
    public void guardarDetalleVenta(){
        int codigoVenta = Integer.parseInt(txtCodigoVenta.getText());
        for (int i = 0; i < tblVentas.getRowCount(); i++) {
            int codProd = Integer.parseInt(tblVentas.getValueAt(i, 1).toString());
            int cant = Integer.parseInt(tblVentas.getValueAt(i, 3).toString());
            double subTotal = Double.parseDouble(tblVentas.getValueAt(i, 5).toString());
            DetalleVenta dtVenta = new DetalleVenta(cant, codProd, subTotal);
            miVentaDAO.guardarDetalleVenta(dtVenta,codigoVenta);            
        }
    }
    
    /**
     * Método que actualiza el stock en la base de datos cuando se realiza una compra
     */
    public void actualizarStock(){
        for (int i = 0; i < tblVentas.getRowCount(); i++) {
            Producto producto;
            codigoProducto = Integer.parseInt(tblVentas.getValueAt(i, 1).toString());
            cantidad = Integer.parseInt(tblVentas.getValueAt(i, 3).toString());
            producto = miProductoDAO.buscarProducto(codigoProducto);
            int stockActualizado = producto.getCantExistente() - cantidad;
            miProductoDAO.actualizarStockProducto(stockActualizado, codigoProducto);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImgFrutas = new javax.swing.JLabel();
        Minimizar = new javax.swing.JLabel();
        Cerrar = new javax.swing.JLabel();
        PnlVenta = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        PnlCliente = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        PnlProducto = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        PnlInicio = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        lblImgLogo = new javax.swing.JLabel();
        LblNombreEmpresa = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        PestPrincipal = new javax.swing.JTabbedPane();
        PestIni = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        PestProduct1 = new javax.swing.JPanel();
        CboxTipoProd = new javax.swing.JComboBox<>();
        lblTipoProducto = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblDesc = new javax.swing.JLabel();
        lblValorUnitario = new javax.swing.JLabel();
        lblCantExistente = new javax.swing.JLabel();
        lblFechaVenc = new javax.swing.JLabel();
        lblFechaEnv = new javax.swing.JLabel();
        lblPesoEnv = new javax.swing.JLabel();
        lblPaisOrigen = new javax.swing.JLabel();
        lblTempRecom = new javax.swing.JLabel();
        lblCodAprob = new javax.swing.JLabel();
        txtNombreProd = new javax.swing.JTextField();
        txtCodigoProd = new javax.swing.JTextField();
        txtDescrProd = new javax.swing.JTextField();
        txtValorUn = new javax.swing.JTextField();
        txtFechaVenc = new javax.swing.JTextField();
        txtCantExs = new javax.swing.JTextField();
        txtFechaEnv = new javax.swing.JTextField();
        txtPesoEnv = new javax.swing.JTextField();
        txtTempRecom = new javax.swing.JTextField();
        txtCodigoAprob = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        CboxPaisOrigen = new javax.swing.JComboBox<>();
        lblOperProd = new javax.swing.JLabel();
        CboxOperacProd = new javax.swing.JComboBox<>();
        PnlModificarProd = new javax.swing.JPanel();
        lblModificarProd = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        PnlEliminarProd = new javax.swing.JPanel();
        lblEliminarProd = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        PnlListarProd = new javax.swing.JPanel();
        lblListarProd = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        PnlAgregarProd = new javax.swing.JPanel();
        lblAgregarProd = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        btnBuscarProd = new javax.swing.JButton();
        jLabel95 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        CboxTipoCliente = new javax.swing.JComboBox<>();
        txtApellidos = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtIdentificacion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtFechaNac = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        CboxOperaciones = new javax.swing.JComboBox<>();
        PnlAgregar = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        PnlEliminar = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        PnlListar = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        PnlModificar = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        PnlInfEmpr = new javax.swing.JPanel();
        lblImgLogoVenta = new javax.swing.JLabel();
        lblPuntoVenta = new javax.swing.JLabel();
        lblVentaPr = new javax.swing.JLabel();
        lblAlpD = new javax.swing.JLabel();
        lblNro = new javax.swing.JLabel();
        txtCodigoVenta = new javax.swing.JTextField();
        PnlInfVenta = new javax.swing.JPanel();
        lblIdCliente = new javax.swing.JLabel();
        txtIdClienteVenta = new javax.swing.JTextField();
        lblCodProducto = new javax.swing.JLabel();
        txtCodProducto = new javax.swing.JTextField();
        lblPrecioProducto = new javax.swing.JLabel();
        txtPrecProducto = new javax.swing.JTextField();
        lblCantProducto = new javax.swing.JLabel();
        spnCantProducto = new javax.swing.JSpinner();
        btnBuscarProducto = new javax.swing.JButton();
        btnBuscarCliente = new javax.swing.JButton();
        btnAgregarProducto = new javax.swing.JButton();
        txtFechaVenta = new javax.swing.JTextField();
        lblCliente = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        lblVendedor = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        txtVendedor = new javax.swing.JTextField();
        PnlTblVenta = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnGenerarVenta = new javax.swing.JButton();
        lblTotalAPagar = new javax.swing.JLabel();
        lblIva = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();
        txtTotalAPagar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1100, 800));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImgFrutas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ProductosArriba.png"))); // NOI18N
        getContentPane().add(lblImgFrutas, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 820, 160));

        Minimizar.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        Minimizar.setText("_");
        Minimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MinimizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MinimizarMouseEntered(evt);
            }
        });
        getContentPane().add(Minimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 0, -1, 20));

        Cerrar.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        Cerrar.setText("X");
        Cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CerrarMouseEntered(evt);
            }
        });
        getContentPane().add(Cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1084, 0, 20, -1));

        PnlVenta.setBackground(new java.awt.Color(255, 255, 255));
        PnlVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PnlVentaMouseClicked(evt);
            }
        });
        PnlVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        jLabel5.setText("Ventas");
        PnlVenta.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 40));

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/VentaIzq.png"))); // NOI18N
        PnlVenta.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/degradado_suave_izq.jpg"))); // NOI18N
        PnlVenta.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -220, -1, 280));

        getContentPane().add(PnlVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 170, 60));

        PnlCliente.setBackground(new java.awt.Color(255, 255, 255));
        PnlCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PnlClienteMouseClicked(evt);
            }
        });
        PnlCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        jLabel44.setText("Clientes");
        PnlCliente.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 100, 40));

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ClientesIzq.png"))); // NOI18N
        PnlCliente.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/degradado_suave_izq.jpg"))); // NOI18N
        PnlCliente.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -120, -1, 280));

        getContentPane().add(PnlCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 170, 60));

        PnlProducto.setBackground(new java.awt.Color(255, 255, 255));
        PnlProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PnlProductoMouseClicked(evt);
            }
        });
        PnlProducto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        jLabel18.setText("Productos");
        PnlProducto.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, 40));

        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ProductoIzq.png"))); // NOI18N
        PnlProducto.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/degradado_suave_izq.jpg"))); // NOI18N
        PnlProducto.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -70, -1, 370));

        getContentPane().add(PnlProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 170, 60));

        PnlInicio.setBackground(new java.awt.Color(255, 255, 255));
        PnlInicio.setForeground(new java.awt.Color(255, 255, 255));
        PnlInicio.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                PnlInicioMouseMoved(evt);
            }
        });
        PnlInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PnlInicioMouseClicked(evt);
            }
        });
        PnlInicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        jLabel3.setText("Inicio");
        PnlInicio.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/inicio.png"))); // NOI18N
        PnlInicio.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/degradado_suave_izq.jpg"))); // NOI18N
        PnlInicio.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 430));

        getContentPane().add(PnlInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 170, 60));

        lblImgLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/huevos.png"))); // NOI18N
        lblImgLogo.setText("Logo");
        getContentPane().add(lblImgLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 90));

        LblNombreEmpresa.setFont(new java.awt.Font("Brush Script MT", 0, 48)); // NOI18N
        LblNombreEmpresa.setText("Mis Webos");
        getContentPane().add(LblNombreEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/degradado_suave_arriba.jpg"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 950, 160));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/degradado_suave_izq.jpg"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 180, 840));

        PestPrincipal.setBackground(new java.awt.Color(0, 204, 204));
        PestPrincipal.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        PestIni.setBackground(new java.awt.Color(255, 255, 255));
        PestIni.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel2.setText("Esperamos que tu estancia en esta página sea agradable");
        PestIni.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, -1));

        jLabel19.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel19.setText("Bienvenidos a nuestro almacén  ");
        PestIni.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, -1));

        jLabel21.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel21.setText("Presiona \"Clientes\" para gestionar los clientes");
        PestIni.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        jLabel22.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel22.setText("Dale a \"Productos\" para gestionar los productos");
        PestIni.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));

        jLabel23.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel23.setText("Crea nuevas compras en la sección \"Ventas\"");
        PestIni.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Inicio_opaco40.png"))); // NOI18N
        PestIni.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, -1, -1));

        PestPrincipal.addTab("Inicio", PestIni);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        PestProduct1.setBackground(new java.awt.Color(255, 255, 255));
        PestProduct1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CboxTipoProd.setBackground(new java.awt.Color(179, 196, 236));
        CboxTipoProd.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        CboxTipoProd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Perecedero", "Refrigerado", "Envasado", " " }));
        CboxTipoProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204)));
        CboxTipoProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboxTipoProdActionPerformed(evt);
            }
        });
        PestProduct1.add(CboxTipoProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 26, 130, -1));

        lblTipoProducto.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblTipoProducto.setText("Tipo Producto");
        PestProduct1.add(lblTipoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 29, -1, -1));

        lblCodigo.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblCodigo.setText("Código");
        PestProduct1.add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 70, -1, -1));

        lblNombre.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblNombre.setText("Nombre");
        PestProduct1.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 111, -1, -1));

        lblDesc.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblDesc.setText("Descripción");
        PestProduct1.add(lblDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 152, -1, -1));

        lblValorUnitario.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblValorUnitario.setText("Valor Unitario");
        PestProduct1.add(lblValorUnitario, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 188, -1, -1));

        lblCantExistente.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblCantExistente.setText("Cantidad Existente");
        PestProduct1.add(lblCantExistente, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 229, -1, -1));

        lblFechaVenc.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblFechaVenc.setText("Fecha Vencimiento");
        PestProduct1.add(lblFechaVenc, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 265, -1, -1));

        lblFechaEnv.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblFechaEnv.setText("Fecha Envasado");
        PestProduct1.add(lblFechaEnv, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 301, -1, -1));

        lblPesoEnv.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblPesoEnv.setText("Peso Envase");
        PestProduct1.add(lblPesoEnv, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 337, -1, -1));

        lblPaisOrigen.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblPaisOrigen.setText("País Origen");
        PestProduct1.add(lblPaisOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 373, -1, -1));

        lblTempRecom.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblTempRecom.setText("Temperatura Recomendada");
        PestProduct1.add(lblTempRecom, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 445, -1, -1));

        lblCodAprob.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblCodAprob.setText("Código Aprobación");
        PestProduct1.add(lblCodAprob, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 409, -1, -1));

        txtNombreProd.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtNombreProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PestProduct1.add(txtNombreProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 108, 324, -1));

        txtCodigoProd.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtCodigoProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PestProduct1.add(txtCodigoProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 67, 324, -1));

        txtDescrProd.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtDescrProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PestProduct1.add(txtDescrProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 149, 324, -1));

        txtValorUn.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtValorUn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PestProduct1.add(txtValorUn, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 185, 324, -1));

        txtFechaVenc.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtFechaVenc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PestProduct1.add(txtFechaVenc, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 262, 324, -1));

        txtCantExs.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtCantExs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PestProduct1.add(txtCantExs, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 226, 324, -1));

        txtFechaEnv.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtFechaEnv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PestProduct1.add(txtFechaEnv, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 298, 324, -1));

        txtPesoEnv.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtPesoEnv.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(120, 145, 204), 2, true));
        PestProduct1.add(txtPesoEnv, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 334, 324, -1));

        txtTempRecom.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtTempRecom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PestProduct1.add(txtTempRecom, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, 310, -1));

        txtCodigoAprob.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtCodigoAprob.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PestProduct1.add(txtCodigoAprob, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 406, 324, -1));

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Descripción", "ValorUnitario", "CantExistente", "TipoProducto", "FechaEnvasado", "PesoEnvase", "PaisOrigen", "CódigoAprov", "TempRecomendada", "FechaVencimiento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tblProductos);

        PestProduct1.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 930, 110));

        CboxPaisOrigen.setBackground(new java.awt.Color(179, 196, 236));
        CboxPaisOrigen.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        CboxPaisOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Colombia", "Chile", "Argentina", "Perú", "Ecuador", " " }));
        CboxPaisOrigen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PestProduct1.add(CboxPaisOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, -1, -1));

        lblOperProd.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblOperProd.setText("Operación");
        PestProduct1.add(lblOperProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 21, -1, 30));

        CboxOperacProd.setBackground(new java.awt.Color(179, 196, 236));
        CboxOperacProd.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        CboxOperacProd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Agregar", "Eliminar", "Modificar" }));
        CboxOperacProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        CboxOperacProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboxOperacProdActionPerformed(evt);
            }
        });
        PestProduct1.add(CboxOperacProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, -1, -1));

        PnlModificarProd.setBackground(new java.awt.Color(255, 255, 255));
        PnlModificarProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PnlModificarProdMouseClicked(evt);
            }
        });
        PnlModificarProd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblModificarProd.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        lblModificarProd.setText("Modificar");
        PnlModificarProd.add(lblModificarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/download.png"))); // NOI18N
        PnlModificarProd.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        PestProduct1.add(PnlModificarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 290, 110, 40));

        PnlEliminarProd.setBackground(new java.awt.Color(255, 255, 255));
        PnlEliminarProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PnlEliminarProdMouseClicked(evt);
            }
        });
        PnlEliminarProd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEliminarProd.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        lblEliminarProd.setText("Eliminar");
        PnlEliminarProd.add(lblEliminarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1214594.png"))); // NOI18N
        PnlEliminarProd.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        PestProduct1.add(PnlEliminarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 170, 110, 40));

        PnlListarProd.setBackground(new java.awt.Color(255, 255, 255));
        PnlListarProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PnlListarProdMouseClicked(evt);
            }
        });
        PnlListarProd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblListarProd.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        lblListarProd.setText("Listar");
        PnlListarProd.add(lblListarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/287625.png"))); // NOI18N
        PnlListarProd.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        PestProduct1.add(PnlListarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 230, 110, 40));

        PnlAgregarProd.setBackground(new java.awt.Color(255, 255, 255));
        PnlAgregarProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PnlAgregarProdMouseClicked(evt);
            }
        });
        PnlAgregarProd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAgregarProd.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        lblAgregarProd.setText("Agregar");
        PnlAgregarProd.add(lblAgregarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/61183.png"))); // NOI18N
        PnlAgregarProd.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        PestProduct1.add(PnlAgregarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 110, 110, 40));

        btnBuscarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        btnBuscarProd.setBorder(null);
        btnBuscarProd.setBorderPainted(false);
        btnBuscarProd.setOpaque(false);
        btnBuscarProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarProdMouseClicked(evt);
            }
        });
        PestProduct1.add(btnBuscarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, -1, 30));

        jLabel95.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_productos_opaco.png"))); // NOI18N
        PestProduct1.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, -1, -1));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(PestProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 35, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(PestProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 97, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane7.setViewportView(jPanel24);

        PestPrincipal.addTab("Producto", jScrollPane7);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Corbel", 0, 16)); // NOI18N
        jLabel8.setText("Nombre");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jLabel9.setFont(new java.awt.Font("Corbel", 0, 16)); // NOI18N
        jLabel9.setText("Apellidos");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jLabel10.setFont(new java.awt.Font("Corbel", 0, 16)); // NOI18N
        jLabel10.setText("Identificación");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, -1));

        jLabel11.setFont(new java.awt.Font("Corbel", 0, 16)); // NOI18N
        jLabel11.setText("Dirección");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        txtNombre.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        jPanel6.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 320, -1));

        jLabel12.setFont(new java.awt.Font("Corbel", 0, 16)); // NOI18N
        jLabel12.setText("Telefono");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        CboxTipoCliente.setBackground(new java.awt.Color(179, 196, 236));
        CboxTipoCliente.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        CboxTipoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Juridico", "Natural" }));
        CboxTipoCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        CboxTipoCliente.setName(""); // NOI18N
        CboxTipoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboxTipoClienteActionPerformed(evt);
            }
        });
        jPanel6.add(CboxTipoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 110, -1));

        txtApellidos.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtApellidos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(120, 145, 204), 2, true));
        jPanel6.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 320, -1));

        jLabel13.setFont(new java.awt.Font("Corbel", 0, 16)); // NOI18N
        jLabel13.setText("Tipo Cliente");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        txtDireccion.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtDireccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        jPanel6.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 320, -1));

        txtIdentificacion.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtIdentificacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        jPanel6.add(txtIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 320, -1));

        txtTelefono.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtTelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        jPanel6.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 320, -1));

        jLabel14.setFont(new java.awt.Font("Corbel", 0, 16)); // NOI18N
        jLabel14.setText("Nit");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        jLabel15.setFont(new java.awt.Font("Corbel", 0, 16)); // NOI18N
        jLabel15.setText("Email");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        txtNit.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtNit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        jPanel6.add(txtNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 320, -1));

        txtEmail.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        jPanel6.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 320, -1));

        jLabel16.setFont(new java.awt.Font("Corbel", 0, 16)); // NOI18N
        jLabel16.setText("Fecha Nacimiento");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, -1, -1));

        txtFechaNac.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtFechaNac.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        jPanel6.add(txtFechaNac, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 320, -1));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "  Nombre", "Apellidos", "Dirección", "Identificación", "Telefono", "NIT", "Email", "Fecha Nacimiento", "Tipo Cliente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 940, 100));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        btnBuscar.setBorder(null);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setOpaque(false);
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
        });
        jPanel6.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, -1, 30));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        jLabel17.setText("Operación");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, -1, -1));

        CboxOperaciones.setBackground(new java.awt.Color(179, 196, 236));
        CboxOperaciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Agregar", "Eliminar", "Modificar" }));
        CboxOperaciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        CboxOperaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboxOperacionesActionPerformed(evt);
            }
        });
        jPanel6.add(CboxOperaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, -1, -1));

        PnlAgregar.setBackground(new java.awt.Color(255, 255, 255));
        PnlAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PnlAgregarMouseClicked(evt);
            }
        });
        PnlAgregar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel52.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel52.setText("Agregar");
        PnlAgregar.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/61183.png"))); // NOI18N
        PnlAgregar.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jPanel6.add(PnlAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 110, 40));

        PnlEliminar.setBackground(new java.awt.Color(255, 255, 255));
        PnlEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PnlEliminarMouseClicked(evt);
            }
        });
        PnlEliminar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel62.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel62.setText("Eliminar");
        PnlEliminar.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1214594.png"))); // NOI18N
        PnlEliminar.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jPanel6.add(PnlEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, 110, 40));

        PnlListar.setBackground(new java.awt.Color(255, 255, 255));
        PnlListar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PnlListarMouseClicked(evt);
            }
        });
        PnlListar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel64.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel64.setText("Listar");
        PnlListar.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/287625.png"))); // NOI18N
        PnlListar.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jPanel6.add(PnlListar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 220, 110, 40));

        PnlModificar.setBackground(new java.awt.Color(255, 255, 255));
        PnlModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PnlModificarMouseClicked(evt);
            }
        });
        PnlModificar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel66.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel66.setText("Modificar");
        PnlModificar.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/download.png"))); // NOI18N
        PnlModificar.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jPanel6.add(PnlModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 280, 150, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_cliente_opaco.png"))); // NOI18N
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 20, 520, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 88, Short.MAX_VALUE))
        );

        jScrollPane9.setViewportView(jPanel3);

        PestPrincipal.addTab("Clientes", jScrollPane9);

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PnlInfEmpr.setBackground(new java.awt.Color(255, 255, 255));
        PnlInfEmpr.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnlInfEmpr.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImgLogoVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/huevos.png"))); // NOI18N
        lblImgLogoVenta.setText("Logo");
        PnlInfEmpr.add(lblImgLogoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 170, 90));

        lblPuntoVenta.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        lblPuntoVenta.setText("PUNTO DE VENTA ARMENIA");
        PnlInfEmpr.add(lblPuntoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, 30));

        lblVentaPr.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        lblVentaPr.setText("VENTA DE PRODUCTOS");
        PnlInfEmpr.add(lblVentaPr, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 190, 40));

        lblAlpD.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        lblAlpD.setText("Al POR MAYOR Y MENOR");
        PnlInfEmpr.add(lblAlpD, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 190, 20));

        lblNro.setFont(new java.awt.Font("Corbel", 1, 36)); // NOI18N
        lblNro.setText("Nro:");
        PnlInfEmpr.add(lblNro, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 80, 50));

        txtCodigoVenta.setEditable(false);
        txtCodigoVenta.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        txtCodigoVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PnlInfEmpr.add(txtCodigoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 90, 40));

        jPanel16.add(PnlInfEmpr, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 903, 160));

        PnlInfVenta.setBackground(new java.awt.Color(255, 255, 255));
        PnlInfVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnlInfVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIdCliente.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblIdCliente.setText("Id Cliente:");
        PnlInfVenta.add(lblIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, 30));

        txtIdClienteVenta.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtIdClienteVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PnlInfVenta.add(txtIdClienteVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 320, -1));

        lblCodProducto.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblCodProducto.setText("Cod. Producto:");
        PnlInfVenta.add(lblCodProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 30));

        txtCodProducto.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtCodProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PnlInfVenta.add(txtCodProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 290, -1));

        lblPrecioProducto.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblPrecioProducto.setText("Prec. Producto:");
        PnlInfVenta.add(lblPrecioProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 110, 20));

        txtPrecProducto.setEditable(false);
        txtPrecProducto.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtPrecProducto.setForeground(new java.awt.Color(0, 0, 255));
        txtPrecProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PnlInfVenta.add(txtPrecProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 290, -1));

        lblCantProducto.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblCantProducto.setText("Cant. Producto:");
        PnlInfVenta.add(lblCantProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 120, 30));
        PnlInfVenta.add(spnCantProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 280, 30));

        btnBuscarProducto.setFont(new java.awt.Font("Corbel", 0, 13)); // NOI18N
        btnBuscarProducto.setText("Buscar");
        btnBuscarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarProductoMouseClicked(evt);
            }
        });
        PnlInfVenta.add(btnBuscarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 80, -1));

        btnBuscarCliente.setFont(new java.awt.Font("Corbel", 0, 13)); // NOI18N
        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarClienteMouseClicked(evt);
            }
        });
        PnlInfVenta.add(btnBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 80, -1));

        btnAgregarProducto.setFont(new java.awt.Font("Corbel", 0, 13)); // NOI18N
        btnAgregarProducto.setText("Agregar");
        btnAgregarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarProductoMouseClicked(evt);
            }
        });
        PnlInfVenta.add(btnAgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 80, -1));

        txtFechaVenta.setEditable(false);
        txtFechaVenta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFechaVenta.setForeground(new java.awt.Color(0, 0, 255));
        PnlInfVenta.add(txtFechaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 80, 30));

        lblCliente.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblCliente.setText("Cliente:");
        PnlInfVenta.add(lblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 60, 23));

        lblProducto.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblProducto.setText("Producto:");
        PnlInfVenta.add(lblProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 70, 23));

        lblStock.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblStock.setText("Stock:");
        PnlInfVenta.add(lblStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 50, 23));

        lblVendedor.setFont(new java.awt.Font("Corbel", 0, 17)); // NOI18N
        lblVendedor.setText("Vendedor:");
        PnlInfVenta.add(lblVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 80, 23));

        txtCliente.setEditable(false);
        txtCliente.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtCliente.setForeground(new java.awt.Color(0, 51, 255));
        txtCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PnlInfVenta.add(txtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 290, -1));

        txtProducto.setEditable(false);
        txtProducto.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtProducto.setForeground(new java.awt.Color(0, 0, 255));
        txtProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PnlInfVenta.add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, 290, -1));

        txtStock.setEditable(false);
        txtStock.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtStock.setForeground(new java.awt.Color(0, 0, 255));
        txtStock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PnlInfVenta.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 290, -1));

        txtVendedor.setEditable(false);
        txtVendedor.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtVendedor.setForeground(new java.awt.Color(0, 51, 255));
        txtVendedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PnlInfVenta.add(txtVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 120, 290, -1));

        jPanel16.add(PnlInfVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 181, 903, 160));

        PnlTblVenta.setBackground(new java.awt.Color(255, 255, 255));
        PnlTblVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnlTblVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Código", "Descripción", "Cant", "Precio", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVentas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblVentas.setCellEditor(tblVentas.getCellEditor());
        jScrollPane6.setViewportView(tblVentas);
        if (tblVentas.getColumnModel().getColumnCount() > 0) {
            tblVentas.getColumnModel().getColumn(2).setPreferredWidth(450);
            tblVentas.getColumnModel().getColumn(5).setPreferredWidth(150);
        }

        PnlTblVenta.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 879, 141));

        btnCancelar.setText("CANCELAR");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });
        PnlTblVenta.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        btnGenerarVenta.setText("GENERAR VENTA");
        btnGenerarVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGenerarVentaMouseClicked(evt);
            }
        });
        PnlTblVenta.add(btnGenerarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, -1, -1));

        lblTotalAPagar.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        lblTotalAPagar.setText("Total a Pagar:");
        PnlTblVenta.add(lblTotalAPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, -1, 20));

        lblIva.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        lblIva.setText("IVA (19%):");
        PnlTblVenta.add(lblIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 90, -1));

        txtIva.setEditable(false);
        txtIva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PnlTblVenta.add(txtIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 160, 20));

        txtTotalAPagar.setEditable(false);
        txtTotalAPagar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 145, 204), 2));
        PnlTblVenta.add(txtTotalAPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 170, 160, 20));

        jPanel16.add(PnlTblVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 348, 900, 210));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 246, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel15);

        PestPrincipal.addTab("Ventas", jScrollPane5);

        getContentPane().add(PestPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 950, 780));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizarMouseClicked
        this.setExtendedState(1);
    }//GEN-LAST:event_MinimizarMouseClicked

    private void CerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_CerrarMouseClicked

    private void CerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseEntered
        Cerrar.setBackground(Color.red);
    }//GEN-LAST:event_CerrarMouseEntered

    private void MinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizarMouseEntered
        Minimizar.setBackground(Color.yellow);
    }//GEN-LAST:event_MinimizarMouseEntered

    private void PnlInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PnlInicioMouseClicked
        PestPrincipal.setSelectedIndex(0);
    }//GEN-LAST:event_PnlInicioMouseClicked

    private void PnlInicioMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PnlInicioMouseMoved
        PestPrincipal.setBackground(new Color(214, 180, 248));
    }//GEN-LAST:event_PnlInicioMouseMoved

    private void PnlProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PnlProductoMouseClicked
        PestPrincipal.setSelectedIndex(1);
    }//GEN-LAST:event_PnlProductoMouseClicked

    private void PnlClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PnlClienteMouseClicked
        PestPrincipal.setSelectedIndex(2);
    }//GEN-LAST:event_PnlClienteMouseClicked

    private void PnlVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PnlVentaMouseClicked
        PestPrincipal.setSelectedIndex(3);
    }//GEN-LAST:event_PnlVentaMouseClicked

    private void CboxOperacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboxOperacionesActionPerformed
        switch (CboxOperaciones.getSelectedItem().toString()) {
            case "Eliminar":
                ocultarTxtFieldsCliente();
                txtIdentificacion.setEnabled(true);
                break;
            case "Agregar":
                mostrarTxtFieldsCliente();
                break;
            case "Modificar":
                ocultarTxtFieldsCliente();
                txtIdentificacion.setEnabled(true);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_CboxOperacionesActionPerformed

    private void CboxTipoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboxTipoClienteActionPerformed
        if (CboxTipoCliente.getSelectedItem().toString().equals("Juridico")) {
            txtEmail.setEnabled(false);
            txtFechaNac.setEnabled(false);
            txtNit.setEnabled(true);
        } else if (CboxTipoCliente.getSelectedItem().toString().equals("Natural")) {
            txtNit.setEnabled(false);
            txtEmail.setEnabled(true);
            txtFechaNac.setEnabled(true);
        }
    }//GEN-LAST:event_CboxTipoClienteActionPerformed

    private void PnlAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PnlAgregarMouseClicked

        if (CboxTipoCliente.getSelectedItem().toString().equals("Juridico")) {
            String nombres = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String direccion = txtDireccion.getText();
            int identificacion = Integer.parseInt(txtIdentificacion.getText());
            String tel = txtTelefono.getText();
            String nit = txtNit.getText();
            String tipoCliente = CboxTipoCliente.getSelectedItem().toString();
            ClienteJuridico clJuridico = new ClienteJuridico(nit);
            TipoCliente tpCliente = new TipoCliente(clJuridico);
            Cliente miCliente = new Cliente(nombres, apellidos, identificacion, direccion, tel, tpCliente);
            this.miClienteDAO.guardarCliente(miCliente);
            limpiarTxtFieldsCliente();
            JOptionPane.showMessageDialog(null, "!El Cliente ha sido agregado con exito¡ =)");
        } else if (CboxTipoCliente.getSelectedItem().toString().equals("Natural")) {
            String nombres2 = txtNombre.getText();
            String apellidos2 = txtApellidos.getText();
            String direccion2 = txtDireccion.getText();
            int identificacion2 = Integer.parseInt(txtIdentificacion.getText());
            String telefono2 = txtTelefono.getText();
            String email2 = txtEmail.getText();
            String fechaNac2 = txtFechaNac.getText();
            String tpCliente = CboxTipoCliente.getSelectedItem().toString();
            ClienteNatural clNatural2 = new ClienteNatural(email2, fechaNac2);
            TipoCliente tpCliente2 = new TipoCliente(clNatural2);
            Cliente miCliente2 = new Cliente(nombres2, apellidos2, identificacion2, direccion2, telefono2, tpCliente2);
            miClienteDAO.guardarCliente(miCliente2);
            limpiarTxtFieldsCliente();
            JOptionPane.showMessageDialog(null, "!El Cliente ha sido agregado con exito¡ =)");
        }

    }//GEN-LAST:event_PnlAgregarMouseClicked

    private void PnlListarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PnlListarMouseClicked
        JOptionPane.showMessageDialog(null, "El directorio de los clientes");
        DefaultTableModel modelo = miClienteDAO.mostrarClientes();
        tblClientes.setModel(modelo);
        tblClientes.setDefaultEditor(Object.class, null);
    }//GEN-LAST:event_PnlListarMouseClicked

    private void PnlEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PnlEliminarMouseClicked
        String identificacion = txtIdentificacion.getText();
        miClienteDAO.eliminarCliente(identificacion);
        JOptionPane.showMessageDialog(null, "!El Cliente ha sido eliminado con exito¡ =)");
        limpiarTxtFieldsCliente();
    }//GEN-LAST:event_PnlEliminarMouseClicked

    private void PnlModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PnlModificarMouseClicked
        String identificacion = txtIdentificacion.getText();
        Cliente clEditado = miClienteDAO.buscarCliente(identificacion);
        if (clEditado.getTipoCliente().getClienteNatural() != null) {
            clEditado.setNombres(txtNombre.getText());
            clEditado.setApellidos(txtApellidos.getText());
            clEditado.setDireccion(txtDireccion.getText());
            clEditado.setTelefono(txtTelefono.getText());
            clEditado.getTipoCliente().getClienteNatural().setEmail(txtEmail.getText());
            clEditado.getTipoCliente().getClienteNatural().setFechaNacimiento(txtFechaNac.getText());
            miClienteDAO.editarCliente(clEditado);
            JOptionPane.showMessageDialog(null, "!El Cliente ha sido modificado con exito¡ =)");
            limpiarTxtFieldsCliente();
        } else {
            clEditado.setNombres(txtNombre.getText());
            clEditado.setApellidos(txtApellidos.getText());
            clEditado.setDireccion(txtDireccion.getText());
            clEditado.setTelefono(txtTelefono.getText());
            clEditado.getTipoCliente().getClienteJuridico().setNit(txtNit.getText());
            miClienteDAO.editarCliente(clEditado);
            JOptionPane.showMessageDialog(null, "!El Cliente ha sido modificado con exito¡ =)");
            limpiarTxtFieldsCliente();
        }
    }//GEN-LAST:event_PnlModificarMouseClicked

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked
        String identificacion = txtIdentificacion.getText();
        Cliente clEditado = miClienteDAO.buscarCliente(identificacion);
        if (clEditado != null) {
            JOptionPane.showMessageDialog(null, "!El Cliente ha sido encontrado con exito, ahora puede modificarlo¡ =)");
        } else {
            JOptionPane.showMessageDialog(null, "!El Cliente no ha sido encontrado¡ =(");
        }

        if (clEditado.getTipoCliente().getClienteNatural() != null) {
            mostrarTxtFieldsCliente();
            txtIdentificacion.setEnabled(false);
            txtNit.setEnabled(false);
        } else {
            mostrarTxtFieldsCliente();
            txtIdentificacion.setEnabled(false);
            txtEmail.setEnabled(false);
            txtFechaNac.setEnabled(false);
        }
    }//GEN-LAST:event_btnBuscarMouseClicked

    private void CboxTipoProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboxTipoProdActionPerformed
        switch (CboxTipoProd.getSelectedItem().toString()) {
            case "Perecedero":
                ocultarTxtFieldsTiposProducto();
                txtFechaVenc.setEnabled(true);
                break;
            case "Refrigerado":
                ocultarTxtFieldsTiposProducto();
                txtCodigoAprob.setEnabled(true);
                txtTempRecom.setEnabled(true);
                break;
            case "Envasado":
                ocultarTxtFieldsTiposProducto();
                txtFechaEnv.setEnabled(true);
                txtPesoEnv.setEnabled(true);
                CboxPaisOrigen.setEnabled(true);
                break;
            default:
                break;
        }

    }//GEN-LAST:event_CboxTipoProdActionPerformed

    private void CboxOperacProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboxOperacProdActionPerformed
        switch (CboxOperacProd.getSelectedItem().toString()) {
            case "Agregar":
                mostrarTxtFieldsProducto();
                break;
            case "Eliminar":
                ocultarTxtFieldsProducto();
                txtCodigoProd.setEnabled(true);
                break;
            case "Modificar":
                ocultarTxtFieldsProducto();
                txtCodigoProd.setEnabled(true);
                break;
            default:
                break;
        }    }//GEN-LAST:event_CboxOperacProdActionPerformed

    private void btnBuscarProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarProdMouseClicked
        int codigo = Integer.parseInt(txtCodigoProd.getText());
        Producto miProducto = miProductoDAO.buscarProducto(codigo);

        if (miProducto != null) {
            JOptionPane.showMessageDialog(null, "¡El Producto ha sido encontrado con exito, ahora puede modificarlo! =)");
        } else {
            JOptionPane.showMessageDialog(null, "¡El Producto no ha sido encontrado! =(");
        }

        if (miProducto.getTipoProducto().getPrPerecedero() != null) {
            ocultarTxtFieldsProducto();
            txtNombreProd.setEnabled(true);
            txtDescrProd.setEnabled(true);
            txtValorUn.setEnabled(true);
            txtCantExs.setEnabled(true);
            txtFechaVenc.setEnabled(true);
        } else if (miProducto.getTipoProducto().getPrEnvasado() != null) {
            ocultarTxtFieldsProducto();
            txtNombreProd.setEnabled(true);
            txtDescrProd.setEnabled(true);
            txtValorUn.setEnabled(true);
            txtCantExs.setEnabled(true);
            txtFechaEnv.setEnabled(true);
            txtPesoEnv.setEnabled(true);
            CboxPaisOrigen.setEnabled(true);
        } else if (miProducto.getTipoProducto().getPrRefrigerado() != null) {
            ocultarTxtFieldsProducto();
            txtNombreProd.setEnabled(true);
            txtDescrProd.setEnabled(true);
            txtValorUn.setEnabled(true);
            txtCantExs.setEnabled(true);
            txtCodigoAprob.setEnabled(true);
            txtTempRecom.setEnabled(true);
        }
    }//GEN-LAST:event_btnBuscarProdMouseClicked

    private void PnlAgregarProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PnlAgregarProdMouseClicked
        int codigoProd = Integer.parseInt(txtCodigoProd.getText());
        String nombreProd = txtNombreProd.getText();
        String descrProd = txtDescrProd.getText();
        double valorUn = Double.parseDouble(txtValorUn.getText());
        int cantExs = Integer.parseInt(txtCantExs.getText());
        String tipoProd = CboxTipoProd.getSelectedItem().toString();

        switch (CboxTipoProd.getSelectedItem().toString()) {
            case "Perecedero":
                String fechaVencimiento = txtFechaVenc.getText();
                ProductoPerecedero prPerecedero = new ProductoPerecedero(fechaVencimiento);
                TipoProducto tpProductoPer = new TipoProducto(prPerecedero);
                Producto miproductoPer = new Producto(codigoProd, nombreProd, descrProd, valorUn, cantExs, tpProductoPer);
                miProductoDAO.guardarProducto(miproductoPer, tipoProd);
                miProductoDAO.guardarProductoPerecedero(miproductoPer);
                limpiarTxtFieldsProducto();
                JOptionPane.showMessageDialog(null, "!El Producto ha sido agregado con exito¡ =)");
                break;
            case "Refrigerado":
                String codAprob = txtCodigoAprob.getText();
                double tempRecom = Double.parseDouble(txtTempRecom.getText());
                ProductoRefrigerado prRefrigerado = new ProductoRefrigerado(codAprob, tempRecom);
                TipoProducto tpProductoRef = new TipoProducto(prRefrigerado);
                Producto miproductoRef = new Producto(codigoProd, nombreProd, descrProd, valorUn, cantExs, tpProductoRef);
                miProductoDAO.guardarProducto(miproductoRef, tipoProd);
                miProductoDAO.guardarProductoRefrigerado(miproductoRef);
                limpiarTxtFieldsProducto();
                JOptionPane.showMessageDialog(null, "!El Producto ha sido agregado con exito¡ =)");
                break;
            case "Envasado":
                String fechaEnv = txtFechaEnv.getText();
                double pesoEnv = Double.parseDouble(txtPesoEnv.getText());
                String paisOrigen = CboxPaisOrigen.getSelectedItem().toString();
                ProductoEnvasado prEnvasado = new ProductoEnvasado(fechaEnv, pesoEnv, paisOrigen);
                TipoProducto tpProductoEnv = new TipoProducto(prEnvasado);
                Producto miproductoEnv = new Producto(codigoProd, nombreProd, descrProd, valorUn, cantExs, tpProductoEnv);
                miProductoDAO.guardarProducto(miproductoEnv, tipoProd);
                miProductoDAO.guardarProductoEnvasado(miproductoEnv);
                limpiarTxtFieldsProducto();
                JOptionPane.showMessageDialog(null, "!El Producto ha sido agregado con exito¡ =)");
                break;
            default:
                break;
        }

    }//GEN-LAST:event_PnlAgregarProdMouseClicked

    private void PnlListarProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PnlListarProdMouseClicked
        JOptionPane.showMessageDialog(null, "El directorio de los productos =)");
        DefaultTableModel modelo = miProductoDAO.mostrarProductosPerecederos();
        modelo = miProductoDAO.mostrarProductosEnvasados(modelo);
        modelo = miProductoDAO.mostrarProductosRefrigerados(modelo);
        tblProductos.setModel(modelo);
        tblProductos.setDefaultEditor(Object.class, null);
    }//GEN-LAST:event_PnlListarProdMouseClicked

    private void PnlEliminarProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PnlEliminarProdMouseClicked
        int codigo = Integer.parseInt(txtCodigoProd.getText());
        miProductoDAO.eliminarProducto(codigo);
        JOptionPane.showMessageDialog(null, "!El Producto ha sido eliminado con exito¡ =)");
        limpiarTxtFieldsCliente();
    }//GEN-LAST:event_PnlEliminarProdMouseClicked

    private void PnlModificarProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PnlModificarProdMouseClicked
        int codigo = Integer.parseInt(txtCodigoProd.getText());
        Producto miProducto = miProductoDAO.buscarProducto(codigo);
        if (miProducto.getTipoProducto().getPrPerecedero() != null) {
            miProducto.setNombre(txtNombreProd.getText());
            miProducto.setDescripcion(txtDescrProd.getText());
            miProducto.setValorUnitario(Double.parseDouble(txtValorUn.getText()));
            miProducto.setCantExistente(Integer.parseInt(txtCantExs.getText()));
            miProducto.getTipoProducto().getPrPerecedero().setFechaVencimiento(txtFechaVenc.getText());
            miProductoDAO.editarProducto(miProducto);
            miProductoDAO.editarProductoPerecedero(miProducto);
            JOptionPane.showMessageDialog(null, "!El Producto ha sido modificado con exito¡ =)");
            limpiarTxtFieldsProducto();
        } else if (miProducto.getTipoProducto().getPrEnvasado() != null) {
            miProducto.setNombre(txtNombreProd.getText());
            miProducto.setDescripcion(txtDescrProd.getText());
            miProducto.setValorUnitario(Double.parseDouble(txtValorUn.getText()));
            miProducto.setCantExistente(Integer.parseInt(txtCantExs.getText()));
            miProducto.getTipoProducto().getPrEnvasado().setFechaEnvasado(txtFechaEnv.getText());
            miProducto.getTipoProducto().getPrEnvasado().setPesoEnvase(Double.parseDouble(txtPesoEnv.getText()));
            miProducto.getTipoProducto().getPrEnvasado().setPaisOrigen(CboxPaisOrigen.getSelectedItem().toString());
            miProductoDAO.editarProducto(miProducto);
            miProductoDAO.editarProductoEnvasado(miProducto);
            JOptionPane.showMessageDialog(null, "!El Producto ha sido modificado con exito¡ =)");
            limpiarTxtFieldsProducto();
        } else if (miProducto.getTipoProducto().getPrRefrigerado() != null) {
            miProducto.setNombre(txtNombreProd.getText());
            miProducto.setDescripcion(txtDescrProd.getText());
            miProducto.setValorUnitario(Double.parseDouble(txtValorUn.getText()));
            miProducto.setCantExistente(Integer.parseInt(txtCantExs.getText()));
            miProducto.getTipoProducto().getPrRefrigerado().setCodigoAprobacion(txtCodigoAprob.getText());
            miProducto.getTipoProducto().getPrRefrigerado().setTempRecomendada(Double.parseDouble(txtTempRecom.getText()));
            miProductoDAO.editarProducto(miProducto);
            miProductoDAO.editarProductoRefrigerado(miProducto);
            JOptionPane.showMessageDialog(null, "!El Producto ha sido modificado con exito¡ =)");
            limpiarTxtFieldsProducto();
        }
    }//GEN-LAST:event_PnlModificarProdMouseClicked

    private void btnBuscarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarClienteMouseClicked
        String identificacion = txtIdClienteVenta.getText();
        Cliente clienteComprador = miClienteDAO.buscarCliente(identificacion);
        txtCliente.setText(clienteComprador.getNombres()+clienteComprador.getApellidos());
        txtIdClienteVenta.setEnabled(false);
    }//GEN-LAST:event_btnBuscarClienteMouseClicked

    private void btnBuscarProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarProductoMouseClicked
        codigoProducto = Integer.parseInt(txtCodProducto.getText());
        productoComprar = miProductoDAO.buscarProducto(codigoProducto);
        txtProducto.setText(productoComprar.getNombre());
        txtPrecProducto.setText(String.valueOf(productoComprar.getValorUnitario()));
        txtStock.setText(String.valueOf(productoComprar.getCantExistente()));
    }//GEN-LAST:event_btnBuscarProductoMouseClicked

    private void btnAgregarProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarProductoMouseClicked
        agregarProducto();
    }//GEN-LAST:event_btnAgregarProductoMouseClicked

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        reiniciarCampos();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnGenerarVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerarVentaMouseClicked
        if (txtTotalAPagar.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar datos para comprar!");
        } else {
            totalAPagar = totalAPagar + iva;
            txtTotalAPagar.setText(String.valueOf(totalAPagar));
            guardarVenta();
            guardarDetalleVenta();
            actualizarStock();
            JOptionPane.showMessageDialog(this, "Venta exitosa!");
            reiniciarCampos();
            generarIdVenta();
        }  
    }//GEN-LAST:event_btnGenerarVentaMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>,
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CboxOperacProd;
    private javax.swing.JComboBox<String> CboxOperaciones;
    private javax.swing.JComboBox<String> CboxPaisOrigen;
    private javax.swing.JComboBox<String> CboxTipoCliente;
    private javax.swing.JComboBox<String> CboxTipoProd;
    private javax.swing.JLabel Cerrar;
    private javax.swing.JLabel LblNombreEmpresa;
    private javax.swing.JLabel Minimizar;
    private javax.swing.JPanel PestIni;
    private javax.swing.JTabbedPane PestPrincipal;
    private javax.swing.JPanel PestProduct1;
    private javax.swing.JPanel PnlAgregar;
    private javax.swing.JPanel PnlAgregarProd;
    private javax.swing.JPanel PnlCliente;
    private javax.swing.JPanel PnlEliminar;
    private javax.swing.JPanel PnlEliminarProd;
    private javax.swing.JPanel PnlInfEmpr;
    private javax.swing.JPanel PnlInfVenta;
    private javax.swing.JPanel PnlInicio;
    private javax.swing.JPanel PnlListar;
    private javax.swing.JPanel PnlListarProd;
    private javax.swing.JPanel PnlModificar;
    private javax.swing.JPanel PnlModificarProd;
    private javax.swing.JPanel PnlProducto;
    private javax.swing.JPanel PnlTblVenta;
    private javax.swing.JPanel PnlVenta;
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarProd;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblAgregarProd;
    private javax.swing.JLabel lblAlpD;
    private javax.swing.JLabel lblCantExistente;
    private javax.swing.JLabel lblCantProducto;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblCodAprob;
    private javax.swing.JLabel lblCodProducto;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblEliminarProd;
    private javax.swing.JLabel lblFechaEnv;
    private javax.swing.JLabel lblFechaVenc;
    private javax.swing.JLabel lblIdCliente;
    private javax.swing.JLabel lblImgFrutas;
    private javax.swing.JLabel lblImgLogo;
    private javax.swing.JLabel lblImgLogoVenta;
    private javax.swing.JLabel lblIva;
    private javax.swing.JLabel lblListarProd;
    private javax.swing.JLabel lblModificarProd;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNro;
    private javax.swing.JLabel lblOperProd;
    private javax.swing.JLabel lblPaisOrigen;
    private javax.swing.JLabel lblPesoEnv;
    private javax.swing.JLabel lblPrecioProducto;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblPuntoVenta;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblTempRecom;
    private javax.swing.JLabel lblTipoProducto;
    private javax.swing.JLabel lblTotalAPagar;
    private javax.swing.JLabel lblValorUnitario;
    private javax.swing.JLabel lblVendedor;
    private javax.swing.JLabel lblVentaPr;
    private javax.swing.JSpinner spnCantProducto;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCantExs;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCodProducto;
    private javax.swing.JTextField txtCodigoAprob;
    private javax.swing.JTextField txtCodigoProd;
    private javax.swing.JTextField txtCodigoVenta;
    private javax.swing.JTextField txtDescrProd;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFechaEnv;
    private javax.swing.JTextField txtFechaNac;
    private javax.swing.JTextField txtFechaVenc;
    private javax.swing.JTextField txtFechaVenta;
    private javax.swing.JTextField txtIdClienteVenta;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreProd;
    private javax.swing.JTextField txtPesoEnv;
    private javax.swing.JTextField txtPrecProducto;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTempRecom;
    private javax.swing.JTextField txtTotalAPagar;
    private javax.swing.JTextField txtValorUn;
    private javax.swing.JTextField txtVendedor;
    // End of variables declaration//GEN-END:variables
}
