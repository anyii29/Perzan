package controlador;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import view.Main1;
import modelo.CategoriaVO;
import modelo.ClienteDAO;
import modelo.ClienteVO;
import modelo.CompraDAO;
import modelo.CompraVO;
import modelo.DetalleCompraDAO;
import modelo.DetalleCompraVO;
import modelo.DetalleVentaDAO;
import modelo.DetalleVentaVO;
import modelo.EmpleadoDAO;
import modelo.EmpleadoVO;
import modelo.Encrypt;
import modelo.MarcaVO;
import modelo.ProductoDAO;
import modelo.ProductoVO;
import modelo.ProveedorDAO;
import modelo.ProveedorVO;
import modelo.VentaDAO;
import modelo.VentaVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class Administrador implements Initializable {

	private Main1 main1;
	private Validar validar;
	private Encrypt encrypt;
	private CategoriaVO catVO;
	private MarcaVO marVO;
	private ProductoVO productoVO;
	private EmpleadoVO empleadoVO;
	private ProveedorVO proveedorVO;
	private ClienteVO clienteVO;
	private CompraVO compraVO;
	private DetalleCompraVO detalleCompraVO;
	private VentaVO ventaVO;
	private DetalleVentaVO detalleVentaVO;
	private ProductoDAO productoDAO;
	private EmpleadoDAO empleadoDAO;
	private ProveedorDAO proveedorDAO;
	private ClienteDAO clienteDAO;
	private CompraDAO compraDAO;
	private DetalleCompraDAO detalleCompraDAO;
	private VentaDAO ventaDAO;
	private DetalleVentaDAO detalleVentaDAO;
	private ObservableList<ProductoVO> productos;
	private ObservableList<CategoriaVO> categorias;
	private ObservableList<MarcaVO> marcas;
	private ObservableList<EmpleadoVO> empleados;
	private ObservableList<ProveedorVO> proveedores;
	private ObservableList<ClienteVO> clientes;
	private ObservableList<CompraVO> compras;
	private ObservableList<DetalleCompraVO> detalleCompras;
	private ObservableList<VentaVO> ventas;
	private ObservableList<DetalleVentaVO> detalleVentas;
	
	public Administrador(){
		validar = new Validar();
		encrypt = new Encrypt();
		 catVO = new CategoriaVO();
		 marVO = new MarcaVO();
		 productoVO = new ProductoVO();
		 empleadoVO = new EmpleadoVO();
		 proveedorVO = new ProveedorVO();
		 clienteVO = new ClienteVO();
		 compraVO = new CompraVO();
		 detalleCompraVO = new DetalleCompraVO();
		 ventaVO = new VentaVO();
		 detalleVentaVO = new DetalleVentaVO();
		 productoDAO = new ProductoDAO();
		 empleadoDAO = new EmpleadoDAO();
		 proveedorDAO = new ProveedorDAO();
		 clienteDAO = new ClienteDAO();
		 compraDAO = new CompraDAO();
		 detalleCompraDAO = new DetalleCompraDAO();
		 ventaDAO = new VentaDAO();
		 detalleVentaDAO = new DetalleVentaDAO();
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		cbTipo.getItems().addAll("interior", "exterior");
		this.fillCategoria();
		this.fillMarca();
		this.tableProducto();
		this.fillTableProducto();
		this.tableCategoria();
		this.fillTableCategoria();
		this.tableMarca();
		this.fillTableMarca();
		this.tableEmpleado();
		this.fillTableEmpleado();
		this.tableProveedor();
		this.fillTableProveedor();
		this.tableCliente();
		this.fillTableCliente();
		this.tableCompra();
		this.fillTableCompra();
		this.tableDetalleCompra();
		this.fillTableDetalleCompra();
		this.tableVenta();
		this.fillTableVenta();
		this.tableDetalleVenta();
		this.fillTableDetalleVenta();
		
	}
    
    @FXML private Tooltip ttApMaterno11;
    @FXML private Tooltip ttNombreProd;
    @FXML private Tooltip ttNombreEmp1;
    @FXML private Tooltip ttContrasenaP;
    @FXML private Tooltip ttNewContrasenaP;
    @FXML private Tooltip ttUsuarioP;
    @FXML private Tooltip ttCantidadProd;
    @FXML private Tooltip ttApMaterno1;
    @FXML private Tooltip ttApPaterno;
    @FXML private Tooltip ttDireccion;    
    @FXML private Tooltip ttTelefono1;    
    @FXML private Tooltip ttNombreEmp11; 
    @FXML private Tooltip ttNombreEmp;     
    @FXML private Tooltip ttPrecioProd;      
    @FXML private Tooltip ttCantidadProd1;    
    @FXML private Tooltip ttCantidadProd2;    
    @FXML private Tooltip ttPassword;        
    @FXML private Tooltip ttDireccion11;   
    @FXML private Tooltip ttTelefono;        
    @FXML private Tooltip ttApMaterno;      
    @FXML private Tooltip ttConfContrasenaP;  
    @FXML private Tooltip ttDireccion1;     
    @FXML private Tooltip ttApPaterno11;     
    @FXML private Tooltip ttUsuario;      
    @FXML private Tooltip ttTelefono11;   
    @FXML private Tooltip ttPrecioProd1;  
    @FXML private Tooltip ttApPaterno1;   
    @FXML private Button btnLimpiarEmp;  
    @FXML private Button btnEliminarCli;
    @FXML private Button btnModificarPro;
    @FXML private Button btnGuardarPro;
    @FXML private Button btnModificarEmp;
    @FXML private Button btnLimpiarPro;
    @FXML private Button btnLimpiarProd;
    @FXML private Button btnGuardarEmp;
    @FXML private Button btnModificarMar; 
    @FXML private Button btnEliminar;     
    @FXML private Button btnIngresarMar;     
    @FXML private Button btnCancelarP;     
    @FXML private Button btnEliminarEmp1;
    @FXML private Button btnModificarCli;     
    @FXML private Button btnGuardarCli; 
    @FXML private Button btnEliminarEmp;   
    @FXML private Button btnModificar;    
    @FXML private Button btnGuardarProd;    
    @FXML private Button btnBuscar;    
    @FXML private Button btnAceptarP;  
    @FXML private Button btnActualizarRecepcion;    
    @FXML private Button btnEliminarCat;     
    @FXML private Button btnCerrarSesion;   
    @FXML private Button btnModificarCat;    
    @FXML private Button btnEliminarMar;      
    @FXML private Button btnLimpiarCli;          
    @FXML private Button btnProductos;    
    @FXML private Button btnIngresarCat;   
    @FXML private TextArea txtReferenciaCli;  
    @FXML private TextField txtStockProd;
    @FXML private TextField txtNombreCli;
    @FXML private TextField txtAMaternoEmp;
    @FXML private TextField txtTelefonoEmp;
    @FXML private TextField txtNumeroCli;
    @FXML private TextField txtAMaternoCli;
    @FXML private TextField txtUsuarioEmp;
    @FXML private TextField txtAPaternoPro;
    @FXML private TextField txtCallePro;    
    @FXML private TextField txtNombreMar;    
    @FXML private TextField txtAPaternoEmp;  
    @FXML private TextField txtMunicipioCli;      
    @FXML private TextField txtPrecio2Prod;    
    @FXML private TextField txtNombreEmp;  
    @FXML private TextField txtColoniaEmp;   
    @FXML private TextField txtStockMaxProd;    
    @FXML private TextField txtNombreCat;  
    @FXML private TextField txtStockMinProd;    
    @FXML private TextField txtColoniaPro;   
    @FXML private TextField txtCalleEmp;     
    @FXML private TextField txtNombrePro;   
    @FXML private TextField txtAvenidaPro; 
    @FXML private TextField txtAPaternoCli; 
    @FXML private TextField txtBuscar;      
    @FXML private TextField txtPrecio1Prod; 
    @FXML private TextField txtCalleCli;  
    @FXML private TextField txtAMaternoPro;    
    @FXML private TextField txtMunicipioEmp;  
    @FXML private TextField txtNumeroEmp;   
    @FXML private TextField txtNumeroPro;   
    @FXML private TextField txtColoniaCli;    
    @FXML private TextField txtMunicipioPro;    
    @FXML private TextField txtPasswordEmp;    
    @FXML private TextField txtAvenidaEmp;
    @FXML private TextField txtAvenidaCli;  
    @FXML private TextField txtTelefonoPro;
    @FXML private TextField txtDescripcionProd;
    @FXML private TextField txtUsuarioP;
    @FXML private PasswordField pfConfContrasenaP, pfNewContrasenaP, pfContrasenaP;    
    @FXML private ComboBox<String> cbTipo;    
    @FXML private ComboBox<MarcaVO> cbMarca;   
    @FXML private ComboBox<CategoriaVO> cbCategoria;   
    @FXML private TableView<ProductoVO> tvProductos; 
    @FXML private TableColumn<ProductoVO, Double> tcPrecio2Prod;
    @FXML private TableColumn<ProductoVO, String> tcCategoriaProd; 
    @FXML private TableColumn<ProductoVO, String> tcTipoProd;   
    @FXML private TableColumn<ProductoVO, Integer> tcStockMaxProd;    
    @FXML private TableColumn<ProductoVO, Integer> tcStockMinProd;
    @FXML private TableColumn<ProductoVO, Double> tcPrecio1Prod;  
    @FXML private TableColumn<ProductoVO, String> tcDescripcionProd; 
    @FXML private TableColumn<ProductoVO, Integer> tcStockProd;      
    @FXML private TableColumn<ProductoVO, String> tcMarcaProd;    
    @FXML private TableView<CategoriaVO> tvCategoria;  
    @FXML private TableColumn<CategoriaVO, Integer> tcIdCat;
    @FXML private TableColumn<CategoriaVO, String> tcNombreCat; 
    @FXML private TableView<MarcaVO> tvMarca; 
    @FXML private TableColumn<MarcaVO, Integer> tcIdMar;  
    @FXML private TableColumn<MarcaVO, String> tcNombreMar;   
    @FXML private TableView<EmpleadoVO> tvEmpleados;
    @FXML private TableColumn<EmpleadoVO, String> tcColoniaEmp;
    @FXML private TableColumn<EmpleadoVO, String> tcUsuarioEmp;
    @FXML private TableColumn<EmpleadoVO, Integer> tcCalleEmp; 
    @FXML private TableColumn<EmpleadoVO, String> tcTelefonoEmp;    
    @FXML private TableColumn<EmpleadoVO, String> tcAMaternoEmp;  
    @FXML private TableColumn<EmpleadoVO, String> tcAPaternoEmp;    
    @FXML private TableColumn<EmpleadoVO, String> tcNombreEmp;  
    @FXML private TableColumn<EmpleadoVO, Integer> tcAvenidaEmp;
    @FXML private TableColumn<EmpleadoVO, String> tcMunicipioEmp;
    @FXML private TableColumn<EmpleadoVO, Integer> tcNumeroEmp; 
    @FXML private TableColumn<EmpleadoVO, String> tcContrasenaEmp;
    @FXML private TableView<ProveedorVO> tvProveedor; 
    @FXML private TableColumn<ProveedorVO, String> tcTelefonoPro; 
    @FXML private TableColumn<ProveedorVO, String> tcAMaternoPro; 
    @FXML private TableColumn<ProveedorVO, String> tcAPaternoPro;
    @FXML private TableColumn<ProveedorVO, String> tcNombrePro;    
    @FXML private TableColumn<ProveedorVO, Integer> tcCallePro; 
    @FXML private TableColumn<ProveedorVO, String> tcMunicipioPro;  
    @FXML private TableColumn<ProveedorVO, Integer> tcAvenidaPro;
    @FXML private TableColumn<ProveedorVO, Integer> tcNumeroPro; 
    @FXML private TableColumn<ProveedorVO, String> tcColoniaPro;
    @FXML private TableView<ClienteVO> tvCliente; 
    @FXML private TableColumn<ClienteVO, Integer> tcCalleCli;  
    @FXML private TableColumn<ClienteVO, Integer> tcAvenidaCli;
    @FXML private TableColumn<ClienteVO, String> tcMunicipioCli;
    @FXML private TableColumn<ClienteVO, String> tcAPaternoCli;     
    @FXML private TableColumn<ClienteVO, String> tcNombreCli;   
    @FXML private TableColumn<ClienteVO, String> tcAMaternoCli; 
    @FXML private TableColumn<ClienteVO, String> tcReferenciaCli; 
    @FXML private TableColumn<ClienteVO, String> tcColoniaCli; 
    @FXML private TableColumn<ClienteVO, Integer> tcNumeroCli;     
    @FXML private TableView<CompraVO> tvCompra;    
    @FXML private TableColumn<CompraVO, Float> tcTotalCom;    
    @FXML private TableColumn<CompraVO, String> tcEmpresaCom; 
    @FXML private TableColumn<CompraVO, Timestamp> tcFechaRecepcionCom; 
    @FXML private TableColumn<CompraVO, Date> tcFechaPedidoCom; 
    @FXML private TableView<DetalleCompraVO> tvDetalleCompra;
    @FXML private TableColumn<DetalleCompraVO, String> tcProductoDCom;
    @FXML private TableColumn<DetalleCompraVO, Float> tcPrecioCompraDCom;
    @FXML private TableColumn<DetalleCompraVO, Float> tcTotalDCom;  
    @FXML private TableColumn<DetalleCompraVO, Float> tcPrecioVenta1DCom; 
    @FXML private TableColumn<DetalleCompraVO, Integer> tcCantidadDCom; 
    @FXML private TableColumn<DetalleCompraVO, Float> tcPrecioVenta2DCom;   
    @FXML private TableView<VentaVO> tvVenta;  
    @FXML private TableColumn<VentaVO, String> tcVendedorVen;
    @FXML private TableColumn<VentaVO, String> tcClienteVen; 
    @FXML private TableColumn<VentaVO, Float> tcTotalVen; 
    @FXML private TableColumn<VentaVO, Timestamp> tcFechaHoraVen;    
    @FXML private TableView<DetalleVentaVO> tvDetalleVenta; 
    @FXML private TableColumn<DetalleVentaVO, String> tcProductoDVen;
    @FXML private TableColumn<DetalleVentaVO, Float> tcPrecioDVen;
    @FXML private TableColumn<DetalleVentaVO, Integer> tcCantidadDVen;
    @FXML private TableColumn<DetalleVentaVO, Float> tcTotalDVen;
    /*
    **metodos Controlador
    */

    public void guardarProd(ActionEvent event) {
    	System.out.println(cbCategoria.getValue());
    	if(txtDescripcionProd.getText().trim().equals("")|| txtPrecio1Prod.getText().trim().equals("")||
    			txtPrecio2Prod.getText().trim().equals("")|| txtStockProd.getText().trim().equals("") ||
    			txtStockMaxProd.getText().trim().equals("")|| txtStockMinProd.getText().trim().equals("")||
    			cbCategoria.getValue() == null || cbMarca.getValue() == null || cbCategoria.getValue() == null){
			alert(AlertType.ERROR, "Completa los campos");
		}
		else{
			int i = 0;
			if(validar.cadena(txtDescripcionProd.getText())){txtDescripcionProd.setStyle("-fx-background-color: white"); }else{i++;txtDescripcionProd.setStyle("-fx-background-color: red");}
			if(validar.precio(txtPrecio1Prod.getText())){txtPrecio1Prod.setStyle("-fx-background-color: white");}else{i++; txtPrecio1Prod.setStyle("-fx-background-color: red");}
			if(validar.precio(txtPrecio2Prod.getText())){txtPrecio2Prod.setStyle("-fx-background-color: white");}else{i++; txtPrecio2Prod.setStyle("-fx-background-color: red");}
			if(validar.entero(txtStockProd.getText())){txtStockProd.setStyle("-fx-background-color: white");}else{i++; txtStockProd.setStyle("-fx-background-color: red");}
			if(validar.entero(txtStockMaxProd.getText())){txtStockMaxProd.setStyle("-fx-background-color: white");}else{i++; txtStockMaxProd.setStyle("-fx-background-color: red");}
			if(validar.entero(txtStockMinProd.getText())){txtStockMinProd.setStyle("-fx-background-color: white");}else{i++; txtStockMinProd.setStyle("-fx-background-color: red");}
			if(i == 0){
				int stock=Integer.parseInt(txtStockProd.getText());
				int stockMax=Integer.parseInt(txtStockMaxProd.getText());
				int stockMin=Integer.parseInt(txtStockMinProd.getText());
				double precio1= Double.valueOf(txtPrecio1Prod.getText());
				double precio2= Double.valueOf(txtPrecio2Prod.getText());
				if(precio1 > 0 && precio2 > 0 && precio1 > precio2 && stock > 0
						&& stockMax > 0 && stockMin > 0 && stockMax > stock && stockMax > stockMin 
						&& stockMin < stock && stock > stockMin){
					CategoriaVO catVO = cbCategoria.getValue(); 
					MarcaVO marVO = cbMarca.getValue();
					String tipo = (String) cbTipo.getValue();
					ProductoVO productoVO = new ProductoVO(0, catVO, txtDescripcionProd.getText().trim(), marVO, precio1, precio2, stock, stockMax, stockMin, tipo);
					ProductoDAO productoDAO = new ProductoDAO();
					if(productoDAO.registrar(productoVO)){
						alert(AlertType.INFORMATION, "Producto Registrado");
						productos.add(productoDAO.lastInsert());
					}
					else{
						alert(AlertType.ERROR, "Fallo Registro");
					}
				}
				else{
					alert(AlertType.INFORMATION, "Precios y Stock deben ser diferentes de 0, \n"
							+ " Precio1 debe ser mayor que precio2,"
							+ "Stock max debe ser mayor que Stock min y Stock");
				}
			}
			else{
				alert(AlertType.ERROR, "Verifica Los Campos");
			}
		}

    }

	public void limpiarProd(ActionEvent event) {
		txtDescripcionProd.setText(null);
		txtPrecio1Prod.setText(null);
		txtPrecio2Prod.setText(null);
		txtStockProd.setText(null);
		txtStockMaxProd.setText(null);
		txtStockMinProd.setText(null);
		cbCategoria.setValue(null);
		cbMarca.setValue(null);
		cbTipo.setValue(null);
    }

   
    public void modificarProd(ActionEvent event) {

    }

   
    public void eliminarProd(ActionEvent event) {

    }

   
    public void buscarProd(ActionEvent event) {

    }

   
    public void mostrarProductos(ActionEvent event) {

    }

   
    public void ingresarCat(ActionEvent event) {
    	if(txtNombreCat.getText().isEmpty()){
    		alert(AlertType.ERROR,"Falta dato nombre.");
    	}
    	else{
    		catVO = new CategoriaVO();
    		catVO.setNombre(txtNombreCat.getText().trim());
    		if(catVO.ingresarCat()){
    			alert(AlertType.INFORMATION, "Categoria Agregada");
    			txtNombreCat.setText(null);
    		}
    		else{
    			alert(AlertType.ERROR, "Fallo Ingreso");
    			
    		}
    	}

    }

   
    public void modificarCat(ActionEvent event) {

    }

   
    public void eliminarCat(ActionEvent event) {

    }

   
    public void eliminarMar(ActionEvent event) {

    }

   
    public void modificarMar(ActionEvent event) {

    }

   
    public void ingresarMar(ActionEvent event) {

    }

   
    public void guardarEmp(ActionEvent event) {

    }

   
    public void limpiarEmp(ActionEvent event) {

    }

   
    public void modificarEmp(ActionEvent event) {

    }

   
    public void eliminarEmp(ActionEvent event) {

    }

   
    public void guardarPro(ActionEvent event) {

    }

   
    public void limpiarPro(ActionEvent event) {

    }

   
    public void modificarPro(ActionEvent event) {

    }

   
    public void eliminarPro(ActionEvent event) {

    }

   
    public void guardarCli(ActionEvent event) {

    }

   
    public void limpiarCli(ActionEvent event) {

    }

   
    public void modificarCli(ActionEvent event) {

    }

   
    public void eliminarCli(ActionEvent event) {

    }

   
    public void actualizarRecepcionCom(ActionEvent event) {

    }

   
    public void cambiarContrasena(ActionEvent event) {

    }
   
    public void cancelarCambiarContrasena(ActionEvent event) {

    }

   
    public void cerrarSesion(ActionEvent event) {

    }
    
    /*
     * Metodos Externos
    */
    /*
     * metodo que crea mensajes de alerta
     * */
    public void alert(AlertType tipo, String mensaje) {
    	Alert alert = new Alert(tipo);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
    /*
     * Metodo para llenar ComboBox categoria
     * */
    public void fillCategoria(){
    	ObservableList<CategoriaVO> categorias = FXCollections.observableArrayList(catVO.listarCategoria());
    	cbCategoria.getItems().addAll(categorias);
    }
    /*
     * Metodo para llenar ComboBox marca
     * */
    public void fillMarca(){
    	ObservableList<MarcaVO> marcas = FXCollections.observableArrayList(marVO.listarMarca());
    	cbMarca.getItems().addAll(marcas);
    }
    
    /*
     * Metodo para inicilaizar tabla prodcutos
     * */
    public void tableProducto(){
    	tcCategoriaProd.setCellValueFactory(new PropertyValueFactory<ProductoVO, String>("categoria"));
    	tcDescripcionProd.setCellValueFactory(new PropertyValueFactory<ProductoVO, String >("descripcion"));
    	tcMarcaProd.setCellValueFactory(new PropertyValueFactory<ProductoVO, String>("marca"));
    	tcPrecio1Prod.setCellValueFactory(new PropertyValueFactory<ProductoVO, Double>("precio1"));
    	tcPrecio2Prod.setCellValueFactory(new PropertyValueFactory<ProductoVO, Double>("precio2"));
    	tcStockProd.setCellValueFactory(new PropertyValueFactory<ProductoVO, Integer>("stock"));
    	tcStockMaxProd.setCellValueFactory(new PropertyValueFactory<ProductoVO, Integer>("stockMax"));
    	tcStockMinProd.setCellValueFactory(new PropertyValueFactory<ProductoVO, Integer>("stockMin"));
    	tcTipoProd.setCellValueFactory(new PropertyValueFactory<ProductoVO, String>("tipo"));
    }
    /*
     * Metodo para llenar tabla Producto
     * */
    public void fillTableProducto(){
    	productos = FXCollections.observableArrayList(productoDAO.getDatos());
    	tvProductos.getItems().addAll(productos);
    }
    /*
    
     * Metodo para obtener los datos del producto seccionado en la tabla producto
     * */
    public void selectedTableProducto(){
    	if(!(tvProductos.getSelectionModel().getSelectedItem() == null)){
    		disableFieldsProducto();
    		productoVO = tvProductos.getSelectionModel().getSelectedItem();
    		cbCategoria.getSelectionModel().select(productoVO.getCategoria());
    		txtDescripcionProd.setText(productoVO.getDescripcion());
    		cbMarca.getSelectionModel().select(productoVO.getMarca());
    		String precio1 = String.valueOf(productoVO.getPrecio1());
    		txtPrecio1Prod.setText(precio1);
    		String precio2 = String.valueOf(productoVO.getPrecio2());
    		txtPrecio2Prod.setText(precio2);
    		String stock = String.valueOf(productoVO.getStock());
    		txtStockProd.setText(stock);
    		String stockMax = String.valueOf(productoVO.getStockMax());
    		txtStockMaxProd.setText(stockMax);
    		String stockMin = String.valueOf(productoVO.getStockMin());
    		txtStockMinProd.setText(stockMin);
    		cbTipo.getSelectionModel().select(productoVO.getTipo());
    		
    	}
    	
    }

    /*
     * Metodo para delarar  de la tabla categoria
     * */
    public void tableCategoria(){
    	tcIdCat.setCellValueFactory(new PropertyValueFactory<CategoriaVO, Integer>("id"));
    	tcNombreCat.setCellValueFactory(new PropertyValueFactory<CategoriaVO, String>("nombre"));
    }
    /*
     * Metodo para llenar tabla categoria
     * */
    public void fillTableCategoria(){
    	categorias = FXCollections.observableArrayList(catVO.listarCategoria());
    	tvCategoria.getItems().addAll(categorias);
    }
/*
    
     * Metodo para obtener los datos de la Categoria seleccionada en la tabla Categoria
     * */
    public void selectedTableCategoria(){
    	if(tvCategoria.getSelectionModel().getSelectedItem() != null){
    		catVO = tvCategoria.getSelectionModel().getSelectedItem();
    		txtNombreCat.setText(catVO.getNombre());
    		disableFieldsCategoria();
    	}
    }

    /*
     * Metodo para delarar  de la tabla marca
     * */
    public void tableMarca(){
    	tcIdMar.setCellValueFactory(new PropertyValueFactory<MarcaVO, Integer>("id"));
    	tcNombreMar.setCellValueFactory(new PropertyValueFactory<MarcaVO, String>("nombre"));
    }
    /*
     * Metodo para llenar tabla marca
     * */
    public void fillTableMarca(){
    	marcas = FXCollections.observableArrayList(marVO.listarMarca());
    	tvMarca.getItems().addAll(marcas);
    }
/*
    
     * Metodo para obtener los datos de la Marca seleccionada en la tabla marca
     * */
    public void selectedTableMarca(){
    	if(tvMarca.getSelectionModel().getSelectedItem() != null){
    		marVO = tvMarca.getSelectionModel().getSelectedItem();
    		txtNombreMar.setText(marVO.getNombre());
    		disableFieldsMarca();
    	}
    }

    /*
     * Metodo para declarar atributos de la tabla empleado
     * */
    public void tableEmpleado(){
    	tcNombreEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("nombre"));
    	tcAPaternoEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("apPaterno"));
    	tcAMaternoEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("apMaterno"));
    	tcCalleEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, Integer>("calle"));
    	tcAvenidaEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, Integer>("avenida"));
    	tcNumeroEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, Integer>("numero"));
    	tcColoniaEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("colonia"));
    	tcMunicipioEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("municipio"));
    	tcTelefonoEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("telefono"));
    	tcUsuarioEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("usuario"));
    	tcContrasenaEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("contrasena"));
    }
    /*
     * Metodo para llenar tabla empleado
     * */
    public void fillTableEmpleado(){
    	empleados = FXCollections.observableArrayList(empleadoDAO.getDatos());
    	tvEmpleados.getItems().addAll(empleados);
    }
    /*
     * Metodo para obtener el objeo selecioado de la tabla empleado
     * */
    public void selectedTableEmpleado(){
    	if(tvEmpleados.getSelectionModel().getSelectedItem() != null){
    		disableFieldsEmpleado();
    		empleadoVO = tvEmpleados.getSelectionModel().getSelectedItem();
    		txtNombreEmp.setText(empleadoVO.getNombre());
    		txtAPaternoEmp.setText(empleadoVO.getApPaterno());
    		txtAMaternoEmp.setText(empleadoVO.getApMaterno());
    		String calle = String.valueOf(empleadoVO.getCalle());
    		txtCalleEmp.setText(calle);
    		String avenida = String.valueOf(empleadoVO.getAvenida());
    		txtAvenidaEmp.setText(avenida);
    		String numero = String.valueOf(empleadoVO.getNumero());
    		txtNumeroEmp.setText(numero);
    		txtColoniaEmp.setText(empleadoVO.getColonia());
    		txtMunicipioEmp.setText(empleadoVO.getMunicipio());
    		txtTelefonoEmp.setText(empleadoVO.getTelefono());
    		txtUsuarioEmp.setText(empleadoVO.getUsuario());
    		txtPasswordEmp.setText(empleadoVO.getPassword());
    	}
    }
    /*
     * Metodo para declarar atributos de la tabla proveedor
     * */
    public void tableProveedor(){
    	tcNombrePro.setCellValueFactory(new PropertyValueFactory<ProveedorVO, String>("nombre"));
    	tcAPaternoPro.setCellValueFactory(new PropertyValueFactory<ProveedorVO, String>("apPaterno"));
    	tcAMaternoPro.setCellValueFactory(new PropertyValueFactory<ProveedorVO, String>("apMaterno"));
    	tcCallePro.setCellValueFactory(new PropertyValueFactory<ProveedorVO, Integer>("calle"));
    	tcAvenidaPro.setCellValueFactory(new PropertyValueFactory<ProveedorVO, Integer>("avenida"));
    	tcNumeroPro.setCellValueFactory(new PropertyValueFactory<ProveedorVO, Integer>("numero"));
    	tcColoniaPro.setCellValueFactory(new PropertyValueFactory<ProveedorVO, String>("colonia"));
    	tcMunicipioPro.setCellValueFactory(new PropertyValueFactory<ProveedorVO, String>("municipio"));
    	tcTelefonoPro.setCellValueFactory(new PropertyValueFactory<ProveedorVO, String>("telefono"));
    	
    }
    /*
     * Metodo para llenar tabla proveedor
     * */
    public void fillTableProveedor(){
    	proveedores = FXCollections.observableArrayList(proveedorDAO.getDatos());
    	tvProveedor.getItems().addAll(proveedores);
    }
    /*
     * Metodo para obtener el objeo selecioado de la tabla proveedor
     * */
    public void selectedTableProveedor(){
    	if(tvProveedor.getSelectionModel().getSelectedItem() != null){
    		disableFieldsProveedor();
    		proveedorVO = tvProveedor.getSelectionModel().getSelectedItem();
    		txtNombrePro.setText(proveedorVO.getNombre());
    		txtAPaternoPro.setText(proveedorVO.getApPaterno());
    		txtAMaternoPro.setText(proveedorVO.getApMaterno());
    		String calle = String.valueOf(proveedorVO.getCalle());
    		txtCallePro.setText(calle);
    		String avenida = String.valueOf(proveedorVO.getAvenida());
    		txtAvenidaPro.setText(avenida);
    		String numero = String.valueOf(proveedorVO.getNumero());
    		txtNumeroPro.setText(numero);
    		txtColoniaPro.setText(proveedorVO.getColonia());
    		txtMunicipioPro.setText(proveedorVO.getMunicipio());
    		txtTelefonoPro.setText(proveedorVO.getTelefono());
    	}
    }
    /*
     * Metodo para declarar atributos de la tabla cliente
     * */
    public void tableCliente(){
    	tcNombreCli.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>("nombre"));
    	tcAPaternoCli.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>("apPaterno"));
    	tcAMaternoCli.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>("apMaterno"));
    	tcCalleCli.setCellValueFactory(new PropertyValueFactory<ClienteVO, Integer>("calle"));
    	tcAvenidaCli.setCellValueFactory(new PropertyValueFactory<ClienteVO, Integer>("avenida"));
    	tcNumeroCli.setCellValueFactory(new PropertyValueFactory<ClienteVO, Integer>("numero"));
    	tcColoniaCli.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>("colonia"));
    	tcMunicipioCli.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>("municipio"));
    	tcReferenciaCli.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>("referencia"));
    }
    /*
     * Metodo para llenar tabla cliente
     * */
    public void fillTableCliente(){
    	clientes = FXCollections.observableArrayList(clienteDAO.getDatos());
    	tvCliente.getItems().addAll(clientes);
    }
    /*
     * Metodo para obtener el objeo selecioado de la tabla cliente
     * */
    public void selectedTableCliente(){
    	if(tvCliente.getSelectionModel().getSelectedItem() != null){
    		disableFieldsCliente();
    		clienteVO = tvCliente.getSelectionModel().getSelectedItem();
    		txtNombreCli.setText(clienteVO.getNombre());
    		txtAPaternoCli.setText(clienteVO.getApPaterno());
    		txtAMaternoCli.setText(clienteVO.getApMaterno());
    		String calle = String.valueOf(clienteVO.getCalle());
    		txtCalleCli.setText(calle);
    		String avenida = String.valueOf(clienteVO.getAvenida());
    		txtAvenidaCli.setText(avenida);
    		String numero = String.valueOf(clienteVO.getNumero());
    		txtNumeroCli.setText(numero);
    		txtColoniaCli.setText(clienteVO.getColonia());
    		txtMunicipioCli.setText(clienteVO.getMunicipio());
    		txtReferenciaCli.setText(clienteVO.getReferencia());
    	}
    }
    /*
     * Metodo para declarar atributos de la tabla compra
     * */
    public void tableCompra(){
    	tcEmpresaCom.setCellValueFactory(new PropertyValueFactory<CompraVO, String>("empresa"));
    	tcTotalCom.setCellValueFactory(new PropertyValueFactory<CompraVO, Float>("total"));
    	tcFechaPedidoCom.setCellValueFactory(new PropertyValueFactory<CompraVO, Date>("fechaPedido"));
    	tcFechaRecepcionCom.setCellValueFactory(new PropertyValueFactory<CompraVO, Timestamp>("fechaRecepcion"));
    }
    /*
     * Metodo para llenar tabla compra
     * */
    public void fillTableCompra(){
    	compras = FXCollections.observableArrayList(compraDAO.getDatos());
    	tvCompra.getItems().addAll(compras);
    }
    /*
     * Metodo para obtener el objeto selecioado de la tabla compra
     * */
    public void selectedTableCompra(){
    	if(tvCompra.getSelectionModel().getSelectedItem() != null){
    		
    	}
    }

    /*
     * Metodo para declarar atributos de la tabla detallecompra
     * */
    public void tableDetalleCompra(){
    	tcProductoDCom.setCellValueFactory(new PropertyValueFactory<DetalleCompraVO, String>("producto"));
    	tcCantidadDCom.setCellValueFactory(new PropertyValueFactory<DetalleCompraVO, Integer>("cantidad"));
    	tcPrecioCompraDCom.setCellValueFactory(new PropertyValueFactory<DetalleCompraVO, Float>("precioCompra"));
    	tcTotalDCom.setCellValueFactory(new PropertyValueFactory<DetalleCompraVO, Float>("total"));
    	tcPrecioVenta1DCom.setCellValueFactory(new PropertyValueFactory<DetalleCompraVO, Float>("precioVenta1"));
    	tcPrecioVenta2DCom.setCellValueFactory(new PropertyValueFactory<DetalleCompraVO, Float>("precioVenta2"));
    	
    }
    /*
     * Metodo para llenar tabla detallecompra
     * */
    public void fillTableDetalleCompra(){
    	detalleCompras = FXCollections.observableArrayList(detalleCompraDAO.getDatos());
    	tvDetalleCompra.getItems().addAll(detalleCompras);
    }
    /*
     * Metodo para obtener el objeto selecioado de la tabla compra
     * */
    public void selectedTableDetalleCompra(){
    	if(tvDetalleCompra.getSelectionModel().getSelectedItem() != null){
    		
    	}
    }

    /*
     * Metodo para declarar atributos de la tabla venta
     * */
    public void tableVenta(){
    	tcVendedorVen.setCellValueFactory(new PropertyValueFactory<VentaVO, String>("vendedor"));
    	tcClienteVen.setCellValueFactory(new PropertyValueFactory<VentaVO, String>("cliente"));
    	tcTotalVen.setCellValueFactory(new PropertyValueFactory<VentaVO, Float>("total"));
    	tcFechaHoraVen.setCellValueFactory(new PropertyValueFactory<VentaVO, Timestamp>("fechaHora"));
    	
    }
    /*
     * Metodo para llenar tabla venta
     * */
    public void fillTableVenta(){
    	ventas = FXCollections.observableArrayList(ventaDAO.getDatos());
    	tvVenta.getItems().addAll(ventas);
    }
    /*
     * Metodo para obtener el objeto selecioado de la tabla venta
     * */
    public void selectedTableVenta(){
    	if(tvVenta.getSelectionModel().getSelectedItem() != null){
    		
    	}
    }

    /*
     * Metodo para declarar atributos de la tabla detalleventa
     * */
    public void tableDetalleVenta(){
    	tcProductoDVen.setCellValueFactory(new PropertyValueFactory<DetalleVentaVO, String>("producto"));
    	tcPrecioDVen.setCellValueFactory(new PropertyValueFactory<DetalleVentaVO, Float>("precio"));
    	tcCantidadDVen.setCellValueFactory(new PropertyValueFactory<DetalleVentaVO, Integer>("cantidad"));
    	tcTotalDVen.setCellValueFactory(new PropertyValueFactory<DetalleVentaVO, Float>("total"));
    	
    }
    /*
     * Metodo para llenar tabla detalleventa
     * */
    public void fillTableDetalleVenta(){
    	detalleVentas = FXCollections.observableArrayList(detalleVentaDAO.getDatos());
    	tvDetalleVenta.getItems().addAll(detalleVentas);
    }
    /*
     * Metodo para obtener el objeto selecioado de la tabla venta
     * */
    public void selectedTableDetalleVenta(){
    	if(tvVenta.getSelectionModel().getSelectedItem() != null){
    		
    	}
    }
    /*
     * Metodo para desabilitar campos de producto
     * */
    public void disableFieldsProducto(){
    	cbCategoria.setDisable(true);
    	txtDescripcionProd.setDisable(true);
    	cbMarca.setDisable(true);
    	txtPrecio1Prod.setDisable(true);
    	txtPrecio2Prod.setDisable(true);
    	txtStockProd.setDisable(true);
    	txtStockMaxProd.setDisable(true);
    	txtStockMinProd.setDisable(true);
    	cbTipo.setDisable(true);
    	btnGuardarProd.setDisable(true);
    	btnCancelarP.setDisable(true);
    }

    /*
     * Metodo para habilitar campos de producto
     * */
    public void enableFieldsProducto(){
    	cbCategoria.setDisable(false);
    	txtDescripcionProd.setDisable(false);
    	cbMarca.setDisable(false);
    	txtPrecio1Prod.setDisable(false);
    	txtPrecio2Prod.setDisable(false);
    	txtStockProd.setDisable(false);
    	txtStockMaxProd.setDisable(false);
    	txtStockMinProd.setDisable(false);
    	cbTipo.setDisable(false);
    	btnGuardarProd.setDisable(false);
    	btnCancelarP.setDisable(false);
    }
    /*
    
     * Metodo para desabilitar campos de categoria
     * */
    public void disableFieldsCategoria(){
    	txtNombreCat.setDisable(true);
    	btnGuardarCli.setDisable(true);
    }
/*
    
     * Metodo para habilitar campos de categoria
     * */
    public void enableFieldsCategoria(){
    	txtNombreCat.setDisable(false);
    	btnIngresarCat.setDisable(false);
    }
    /*
    
     * Metodo para desabilitar campos de marca
     * */
    public void disableFieldsMarca(){
    	txtNombreMar.setDisable(true);
    	btnIngresarMar.setDisable(true);
    }
/*
    
     * Metodo para habilitar campos de marca
     * */
    public void enableFieldsMarca(){
    	txtNombreCat.setDisable(false);
    	btnIngresarMar.setDisable(false);
    }
    /*
    
     * Metodo para desabilitar campos de empleado
     * */
    public void disableFieldsEmpleado(){
		txtNombreEmp.setDisable(true);
		txtAPaternoEmp.setDisable(true);
		txtAMaternoEmp.setDisable(true);
		txtCalleEmp.setDisable(true);
		txtAvenidaEmp.setDisable(true);
		txtNumeroEmp.setDisable(true);
		txtColoniaEmp.setDisable(true);
		txtMunicipioEmp.setDisable(true);
		txtTelefonoEmp.setDisable(true);
		txtUsuarioEmp.setDisable(true);
		txtPasswordEmp.setDisable(true);
		btnGuardarEmp.setDisable(true);
		btnLimpiarEmp.setDefaultButton(true);
    }
/*
    
     * Metodo para habilitar campos de empleado
     * */
    public void enableFieldsEmpleado(){
    	txtNombreEmp.setDisable(false);
		txtAPaternoEmp.setDisable(false);
		txtAMaternoEmp.setDisable(false);
		txtCalleEmp.setDisable(false);
		txtAvenidaEmp.setDisable(false);
		txtNumeroEmp.setDisable(false);
		txtColoniaEmp.setDisable(false);
		txtMunicipioEmp.setDisable(false);
		txtTelefonoEmp.setDisable(false);
		txtUsuarioEmp.setDisable(false);
		txtPasswordEmp.setDisable(false);
		btnGuardarEmp.setDisable(false);
		btnLimpiarEmp.setDefaultButton(false);
    }
/*
    
     * Metodo para desabilitar campos de proveedor
     * */
    public void disableFieldsProveedor(){
		txtNombrePro.setDisable(true);
		txtAPaternoPro.setDisable(true);
		txtAMaternoPro.setDisable(true);
		txtCallePro.setDisable(true);
		txtAvenidaPro.setDisable(true);
		txtNumeroPro.setDisable(true);
		txtColoniaPro.setDisable(true);
		txtMunicipioPro.setDisable(true);
		txtTelefonoPro.setDisable(true);
		btnGuardarPro.setDisable(true);
		btnLimpiarPro.setDefaultButton(true);
    }
/*
    
     * Metodo para habilitar campos de proveedor
     * */
    public void enableFieldsProveedor(){
    	txtNombrePro.setDisable(false);
		txtAPaternoPro.setDisable(false);
		txtAMaternoPro.setDisable(false);
		txtCallePro.setDisable(false);
		txtAvenidaPro.setDisable(false);
		txtNumeroPro.setDisable(false);
		txtColoniaPro.setDisable(false);
		txtMunicipioPro.setDisable(false);
		txtTelefonoPro.setDisable(false);
		btnGuardarPro.setDisable(false);
		btnLimpiarPro.setDefaultButton(false);
    }

/*
    
     * Metodo para desabilitar campos de cliente
     * */
    public void disableFieldsCliente(){
		txtNombreCli.setDisable(true);
		txtAPaternoCli.setDisable(true);
		txtAMaternoCli.setDisable(true);
		txtCalleCli.setDisable(true);
		txtAvenidaCli.setDisable(true);
		txtNumeroCli.setDisable(true);
		txtColoniaCli.setDisable(true);
		txtMunicipioCli.setDisable(true);
		txtReferenciaCli.setDisable(true);
		btnGuardarCli.setDisable(true);
		btnLimpiarCli.setDefaultButton(true);
    }
/*
    
     * Metodo para habilitar campos de cliente
     * */
    public void enableFieldsClienter(){
    	txtNombreCli.setDisable(false);
		txtAPaternoCli.setDisable(false);
		txtAMaternoCli.setDisable(false);
		txtCalleCli.setDisable(false);
		txtAvenidaCli.setDisable(false);
		txtNumeroCli.setDisable(false);
		txtColoniaCli.setDisable(false);
		txtMunicipioCli.setDisable(false);
		txtReferenciaCli.setDisable(false);
		btnGuardarCli.setDisable(false);
		btnLimpiarCli.setDefaultButton(false);
    }
    /*
    /*
     
     * Metodo para
     * */
	public void setMain1(view.Main1 main1) {
		this.main1 = main1;
	}
}
