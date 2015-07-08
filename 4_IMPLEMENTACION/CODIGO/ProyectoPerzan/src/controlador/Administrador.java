package controlador;

import java.io.ObjectStreamField;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

















import javax.swing.JOptionPane;

import view.Main1;
import modelo.AdminDAO;
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
import modelo.InventarioVO;
import modelo.MarcaVO;
import modelo.ProductoDAO;
import modelo.ProductoVO;
import modelo.ProveedorDAO;
import modelo.ProveedorVO;
import modelo.VentaDAO;
import modelo.VentaVO;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableObjectValue;
import javafx.beans.value.WritableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.FocusModel;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

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
	private ObservableList<EmpleadoVO> empleados;
	private ObservableList<ProveedorVO> proveedores;
	private ObservableList<ClienteVO> clientes;
	private ObservableList<CompraVO> compras;
	private ObservableList<DetalleCompraVO> detalleCompras;
	private ObservableList<VentaVO> ventas;
	private ObservableList<DetalleVentaVO> detalleVentas;
	private ObservableList<CategoriaVO> categorias;
	private ObservableList<MarcaVO> marcas;
	private FilteredList<ProductoVO> productosFound;
	
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
		btnModificar.setDisable(true);
		btnModificarCli.setDisable(true);
		btnModificarPro.setDisable(true);
		btnModificarEmp.setDisable(true);
		btnEliminar.setDisable(true);
		btnEliminarEmp.setDisable(true);
		btnEliminarPro.setDisable(true);
		btnGuardarPro.setDisable(true);
		btnGuardarCli.setDisable(true);
		btnGuardarEmp.setDisable(true);
		btnGuardarProd.setDisable(true);
		this.disableFieldsProducto();
		this.disableFieldsCliente();
		this.disableFieldsEmpleado();
		this.disableFieldsProveedor();
		productosFound = new FilteredList<>(productos);
		tcLista.setCellValueFactory(new PropertyValueFactory<ProductoVO, String>("nombreProducto"));
		tvListProducts.setItems(productos);
		txtIdP.setDisable(true);
		txtDescripcionP.setDisable(true);
		txtExistenciaP.setDisable(true);
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
    //@FXML private Button btnEliminarCli;
    @FXML private Button btnModificarPro;
    @FXML private Button btnGuardarPro;
    @FXML private Button btnModificarEmp;
    @FXML private Button btnLimpiarPro;
    @FXML private Button btnLimpiarProd;
    @FXML private Button btnGuardarEmp;
    //@FXML private Button btnModificarMar; 
    @FXML private Button btnEliminar;     
    //@FXML private Button btnIngresarMar;     
    @FXML private Button btnCancelarP;     
    //@FXML private Button btnEliminarEmp1;
    @FXML private Button btnModificarCli;     
    @FXML private Button btnGuardarCli; 
    @FXML private Button btnEliminarEmp;   
    @FXML private Button btnModificar;    
    @FXML private Button btnGuardarProd;    
    //@FXML private Button btnBuscar;    
    @FXML private Button btnAceptarP;  
    @FXML private Button btnActualizarRecepcion;    
    //@FXML private Button btnEliminarCat;     
    @FXML private Button btnCerrarSesion;   
    //@FXML private Button btnModificarCat;    
    //@FXML private Button btnEliminarMar;      
    @FXML private Button btnLimpiarCli;          
    @FXML private Button btnProductos;    
    //@FXML private Button btnIngresarCat; 
    @FXML private Button btnEliminarPro;
    @FXML private Button btnGuardarInv;
    @FXML private Button btnCancelarInv;
    @FXML private TextArea txtReferenciaCli;  
    //@FXML private TextField txtStockProd;
    @FXML private TextField txtNombreCli;
    @FXML private TextField txtAMaternoEmp;
    @FXML private TextField txtTelefonoEmp;
    @FXML private TextField txtNumeroCli;
    @FXML private TextField txtAMaternoCli;
    @FXML private TextField txtUsuarioEmp;
    @FXML private TextField txtAPaternoPro;
    @FXML private TextField txtCallePro;    
    //@FXML private TextField txtNombreMar;    
    @FXML private TextField txtAPaternoEmp;  
    @FXML private TextField txtMunicipioCli;      
    @FXML private TextField txtPrecio2Prod;    
    @FXML private TextField txtNombreEmp;  
    @FXML private TextField txtColoniaEmp;   
    @FXML private TextField txtStockMaxProd;    
    //@FXML private TextField txtNombreCat;  
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
    @FXML private TextField txtEmpresaPro;
    @FXML private TextField txtIdP;
    @FXML private TextField txtDescripcionP;
    @FXML private TextField txtExistenciaP;
    @FXML private TextField txtNuevaExistenciaP;
    @FXML private TextArea txtCausaP;
    //@FXML private TextField txtUsuarioP;
    @FXML private PasswordField pfConfContrasenaP, pfNewContrasenaP, pfContrasenaP;    
    @FXML private ComboBox<String> cbTipo;    
    @FXML private ComboBox<MarcaVO> cbMarca;   
    @FXML private ComboBox<CategoriaVO> cbCategoria;   
    @FXML private TableView<ProductoVO> tvProductos; 
    @FXML private TableColumn<ProductoVO, Double> tcPrecio2Prod;
    //@FXML private TableColumn<ProductoVO, String> tcCategoriaProd;
    @FXML private TableColumn<ProductoVO, String> tcProductoProd; 
    @FXML private TableColumn<ProductoVO, String> tcTipoProd;   
    //@FXML private TableColumn<ProductoVO, Integer> tcStockMaxProd;    
    @FXML private TableColumn<ProductoVO, Integer> tcStockMinProd;
    @FXML private TableColumn<ProductoVO, Double> tcPrecio1Prod;  
   // @FXML private TableColumn<ProductoVO, String> tcDescripcionProd; 
    //@FXML private TableColumn<ProductoVO, Integer> tcStockProd;      
    @FXML private TableColumn<ProductoVO, String> tcMarcaProd;    
   /* @FXML private TableView<CategoriaVO> tvCategoria;  
    @FXML private TableColumn<CategoriaVO, Integer> tcIdCat;
    @FXML private TableColumn<CategoriaVO, String> tcNombreCat; */
    /*@FXML private TableView<MarcaVO> tvMarca; 
    @FXML private TableColumn<MarcaVO, Integer> tcIdMar;  
    @FXML private TableColumn<MarcaVO, String> tcNombreMar;*/   
    @FXML private TableView<EmpleadoVO> tvEmpleados;
    //@FXML private TableColumn<EmpleadoVO, String> tcColoniaEmp;
    @FXML private TableColumn<EmpleadoVO, String> tcUsuarioEmp;
    //@FXML private TableColumn<EmpleadoVO, Integer> tcCalleEmp; 
    @FXML private TableColumn<EmpleadoVO, String> tcTelefonoEmp;    
    //@FXML private TableColumn<EmpleadoVO, String> tcAMaternoEmp;  
    //@FXML private TableColumn<EmpleadoVO, String> tcAPaternoEmp;    
    //@FXML private TableColumn<EmpleadoVO, String> tcNombreEmp;
    @FXML private TableColumn<EmpleadoVO, String> tcEmpleadoEmp;
    //@FXML private TableColumn<EmpleadoVO, Integer> tcAvenidaEmp;
    @FXML private TableColumn<EmpleadoVO, String> tcMunicipioEmp;
    //@FXML private TableColumn<EmpleadoVO, Integer> tcNumeroEmp; 
    @FXML private TableColumn<EmpleadoVO, String> tcContrasenaEmp;
    @FXML private TableView<ProveedorVO> tvProveedor; 
    @FXML private TableColumn<ProveedorVO, String> tcEmpresaPro; 
    @FXML private TableColumn<ProveedorVO, String> tcTelefonoPro; 
    //@FXML private TableColumn<ProveedorVO, String> tcAMaternoPro; 
   // @FXML private TableColumn<ProveedorVO, String> tcAPaternoPro;
    //@FXML private TableColumn<ProveedorVO, String> tcNombrePro;   
    @FXML private TableColumn<ProveedorVO, String> tcProveedorPro;
    //@FXML private TableColumn<ProveedorVO, Integer> tcCallePro; 
    @FXML private TableColumn<ProveedorVO, String> tcMunicipioPro;  
    //@FXML private TableColumn<ProveedorVO, Integer> tcAvenidaPro;
    //@FXML private TableColumn<ProveedorVO, Integer> tcNumeroPro; 
    //@FXML private TableColumn<ProveedorVO, String> tcColoniaPro;
    @FXML private TableView<ClienteVO> tvCliente; 
    //@FXML private TableColumn<ClienteVO, Integer> tcCalleCli;  
    //@FXML private TableColumn<ClienteVO, Integer> tcAvenidaCli;
    @FXML private TableColumn<ClienteVO, String> tcMunicipioCli;
    @FXML private TableColumn<ClienteVO, String> tcClienteCli; 
    //@FXML private TableColumn<ClienteVO, String> tcAPaternoCli;     
    //@FXML private TableColumn<ClienteVO, String> tcNombreCli;   
    //@FXML private TableColumn<ClienteVO, String> tcAMaternoCli; 
    @FXML private TableColumn<ClienteVO, String> tcReferenciaCli; 
    //@FXML private TableColumn<ClienteVO, String> tcColoniaCli; 
    //@FXML private TableColumn<ClienteVO, Integer> tcNumeroCli;     
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
    @FXML private TableView<ProductoVO> tvListProducts;
    @FXML private TableColumn<ProductoVO, String> tcLista; 
    @FXML private RadioButton rbtnCategoria;
    @FXML private RadioButton rbtnMarca;
    /*
    **metodos Controlador
    */

    public void guardarProd(ActionEvent event) {
    	if(txtDescripcionProd.getText().trim().equals("")|| txtPrecio1Prod.getText().trim().equals("")||
    			txtPrecio2Prod.getText().trim().equals("")||
    			txtStockMaxProd.getText().trim().equals("")|| txtStockMinProd.getText().trim().equals("")||
    			cbCategoria.getValue() == null || cbMarca.getValue() == null || cbCategoria.getValue() == null){
			alert(AlertType.ERROR, "Completa los campos");
		}
		else{			
			int i = 0;
			if(validar.cadena(txtDescripcionProd.getText())){txtDescripcionProd.setStyle("-fx-background-color: white"); }else{i++;txtDescripcionProd.setStyle("-fx-background-color: red");}
			if(validar.precio(txtPrecio1Prod.getText())){txtPrecio1Prod.setStyle("-fx-background-color: white");}else{i++; txtPrecio1Prod.setStyle("-fx-background-color: red");}
			if(validar.precio(txtPrecio2Prod.getText())){txtPrecio2Prod.setStyle("-fx-background-color: white");}else{i++; txtPrecio2Prod.setStyle("-fx-background-color: red");}
			if(validar.entero(txtStockMaxProd.getText())){txtStockMaxProd.setStyle("-fx-background-color: white");}else{i++; txtStockMaxProd.setStyle("-fx-background-color: red");}
			if(validar.entero(txtStockMinProd.getText())){txtStockMinProd.setStyle("-fx-background-color: white");}else{i++; txtStockMinProd.setStyle("-fx-background-color: red");}
			if(i == 0){
				int stockMax=Integer.parseInt(txtStockMaxProd.getText());
				int stockMin=Integer.parseInt(txtStockMinProd.getText());
				float precio1= Float.valueOf(txtPrecio1Prod.getText());
				float precio2= Float.valueOf(txtPrecio2Prod.getText());
				if(precio1 > 0 && precio2 > 0 && precio1 > precio2 
						&& stockMax > 0 && stockMin > 0  && stockMax > stockMin){
					CategoriaVO catVO = cbCategoria.getValue(); 
					MarcaVO marVO = cbMarca.getValue();
					String tipo = (String) cbTipo.getValue();
					ProductoVO productoVO = new ProductoVO(0, catVO, txtDescripcionProd.getText().trim(), marVO, precio1, precio2, 0 , stockMax, stockMin, tipo);
					if(this.productoVO == null){
						if(productoDAO.registrar(productoVO)){
							alert(AlertType.INFORMATION, "Producto Registrado");
							productos.add(productoDAO.lastInsert());
							limpiarProd(null);
						}
						else{
							alert(AlertType.ERROR, "Fallo Registro");
						}
					}
					else{
						productoVO.setId(this.productoVO.getId());
						if(productoDAO.modificar(productoVO)){
							productos.removeAll(productos);
							fillTableProducto();
							limpiarProd(null);
							disableFieldsProducto();
							
							alert(AlertType.INFORMATION, "Producto Actualizado");
						}
						else{
							alert(AlertType.ERROR, "Fallo Registro");
						}
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
		txtDescripcionProd.setText("");
		txtPrecio1Prod.setText("");
		txtPrecio2Prod.setText("");
		txtStockMaxProd.setText("");
		txtStockMinProd.setText("");
		cbCategoria.setValue(null);
		cbMarca.setValue(null);
		cbTipo.setValue(null);
		productoVO = null;
		enableFieldsProducto();
		btnModificar.setDisable(true);
		btnEliminar.setDisable(true);
		btnGuardarPro.setDisable(true);
    }

   
    public void modificarProd(ActionEvent event) {
    	if( productoVO != null){
        	enableFieldsProducto();
        	btnModificar.setDisable(true);
    	}
    	else{
    		alert(AlertType.ERROR,"No hay producto seleccionado.");
    	}

    }

   
    public void eliminarProd(ActionEvent event) {
    	if( productoVO != null){
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Confirmación");
    		//alert.setHeaderText("Look, a Confirmation Dialog");
    		alert.setContentText("¿Desea Eliminar Este Producto? ");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    		    productoDAO.eliminar(productoVO.getId());
    		    productos.removeAll(productos);
    		    fillTableProducto();
    		}
    		}
    	else{
    		alert(AlertType.ERROR,"No hay producto seleccionado.");
    	}

    }

   
    @FXML public void buscarProd() {
    	if(txtBuscar.getText().isEmpty()){
    		tvProductos.setItems(productos);
    	}
    	else{
    		productosFound.setPredicate(ProductoVO -> ProductoVO.getNombreProducto().toLowerCase().
					contains(txtBuscar.getText().trim().toLowerCase()));
    		tvProductos.setItems(productosFound);
    	}
    }

   
    public void mostrarProductos(ActionEvent event) {
    	tvProductos.setItems(productos);
    }
    
    public void guardarInv(ActionEvent event){
    	if(txtIdP.getText().isEmpty() || txtDescripcionP.getText().isEmpty() ||
    			txtExistenciaP.getText().isEmpty() || txtNuevaExistenciaP.getText().isEmpty() ||
    			txtCausaP.getText().isEmpty()){
    		alert(AlertType.ERROR, "Complete los campos.");
    	}
    	else{
    		int i = 0;
    		if(validar.cadena(txtDescripcionP.getText())){txtDescripcionP.setStyle("-fx-background-color: white"); }else{i++;txtDescripcionP.setStyle("-fx-background-color: red");}
			if(validar.entero(txtIdP.getText())){txtIdP.setStyle("-fx-background-color: white");}else{i++; txtIdP.setStyle("-fx-background-color: red");}
			if(validar.entero(txtExistenciaP.getText())){txtExistenciaP.setStyle("-fx-background-color: white");}else{i++; txtExistenciaP.setStyle("-fx-background-color: red");}
			if(validar.entero(txtNuevaExistenciaP.getText())){txtNuevaExistenciaP.setStyle("-fx-background-color: white");}else{i++; txtNuevaExistenciaP.setStyle("-fx-background-color: red");}
			if(validar.cadena(txtCausaP.getText())){txtCausaP.setStyle("-fx-background-color: white");}else{i++; txtCausaP.setStyle("-fx-background-color: red");}
			if(i == 0){
				int idProducto = Integer.parseInt(txtIdP.getText().trim());
				String causa = txtCausaP.getText().trim();
				int existencia = Integer.parseInt(txtExistenciaP.getText().trim());
				int nuevaExistencia = Integer.parseInt(txtNuevaExistenciaP.getText().trim());
				//int idEmpleado
				InventarioVO inventarioVO = new InventarioVO(idProducto, existencia, nuevaExistencia, 3, causa);
				if(productoDAO.inventario(inventarioVO)){
					alert(AlertType.INFORMATION,"Actualización de inventario correcta.");
					fillTableProducto();
					txtIdP.setText("");
					txtDescripcionP.setText("");
					txtExistenciaP.setText("");
					txtNuevaExistenciaP.setText("");					
	    			txtCausaP.setText("");
				}
				else{
					alert(AlertType.ERROR, "Falló actualización.");
				}
			}
    	}
    }
	public void cancelarInv(ActionEvent event){
		txtIdP.setText("");
		txtDescripcionP.setText("");
		txtExistenciaP.setText("");
		txtNuevaExistenciaP.setText("");					
		txtCausaP.setText("");
	}
   @FXML public void listSelected(){
	   if(tvListProducts.getSelectionModel().getSelectedItem() != null){
		   productoVO = tvListProducts.getSelectionModel().getSelectedItem();
		   txtIdP.setText(String.valueOf(productoVO.getId()));
		   txtDescripcionP.setText(productoVO.getNombreProducto());
		   txtExistenciaP.setText(String.valueOf(productoVO.getStock()));
	   }
	   else{
		   alert(AlertType.ERROR, "No hay producto seleccionado.");
	   }
   }
   
    public void guardarEmp(ActionEvent event) {
    	if(txtNombreEmp.getText().isEmpty() || txtAMaternoEmp.getText().isEmpty() ||
    			txtAPaternoEmp.getText().isEmpty() || txtTelefonoEmp.getText().isEmpty() ||
    			txtUsuarioEmp.getText().isEmpty() || txtPasswordEmp.getText().isEmpty() ||
    			txtCalleEmp.getText().isEmpty() || txtAvenidaEmp.getText().isEmpty() ||
    			txtNumeroEmp.getText().isEmpty() || txtColoniaEmp.getText().isEmpty() ||
    			txtMunicipioEmp.getText().isEmpty()){
    		alert(AlertType.ERROR, "Completa los campos.");
    	}
    	else{
    		int i = 0;
    		if(validar.nombres(txtNombreEmp.getText().trim())){
    			txtNombreEmp.setStyle("-fx-background-color: white");
    			}else{i++; txtNombreEmp.setStyle("-fx-background-color: red");}
    		if(validar.nombres(txtAMaternoEmp.getText().trim())){
    			txtAMaternoEmp.setStyle("-fx-background-color: white");
    		}else{i++; txtAMaternoEmp.setStyle("-fx-background-color: red");}
    		if(validar.nombres(txtAPaternoEmp.getText().trim())){
    			txtAPaternoEmp.setStyle("-fx-background-color: white");
    		}else{i++; txtAPaternoEmp.setStyle("-fx-background-color: red");}
    		if(validar.telefono(txtTelefonoEmp.getText().trim())){
    			txtTelefonoEmp.setStyle("-fx-background-color: white");
    		}else{i++; txtTelefonoEmp.setStyle("-fx-background-color: red");}
    		if(validar.usuario(txtUsuarioEmp.getText().trim())){
    			txtUsuarioEmp.setStyle("-fx-background-color: white");
    		}else{i++; txtUsuarioEmp.setStyle("-fx-background-color: red");}
	    	if(txtPasswordEmp.getText().length() != 32){
	    		if(validar.contrasena(txtPasswordEmp.getText().trim())){
	    			txtPasswordEmp.setStyle("-fx-background-color: white");
	    		}else{i++; txtPasswordEmp.setStyle("-fx-background-color: red");}
    		}
    		if(validar.entero(txtCalleEmp.getText().trim())){
    			txtCalleEmp.setStyle("-fx-background-color: white");
    		}else{i++; txtCalleEmp.setStyle("-fx-background-color: red");}
    		if(validar.entero(txtAvenidaEmp.getText().trim())){
    			txtAvenidaEmp.setStyle("-fx-background-color: white");
    		}else{i++; txtAvenidaEmp.setStyle("-fx-background-color: red");}
    		if(validar.entero(txtNumeroEmp.getText().trim())){
    			txtNumeroEmp.setStyle("-fx-background-color: white");
    		}else{i++; txtNumeroEmp.setStyle("-fx-background-color: red");}
    		if(validar.cadena(txtColoniaEmp.getText().trim())){
    			txtColoniaEmp.setStyle("-fx-background-color: white");
    		}else{i++; txtColoniaEmp.setStyle("-fx-background-color: red");}
    		if(validar.cadena(txtMunicipioEmp.getText().trim())){
    			txtMunicipioEmp.setStyle("-fx-background-color: white");
    		}else{i++; txtMunicipioEmp.setStyle("-fx-background-color: red");}
    		if(i == 0){
    			String nombre = txtNombreEmp.getText().trim();
    			String aPaterno = txtAPaternoEmp.getText().trim();
    			String aMaterno = txtAMaternoEmp.getText().trim();
    			int calle = Integer.parseInt(txtCalleEmp.getText());
    			int avenida = Integer.parseInt(txtAvenidaEmp.getText());
    			int numero = Integer.parseInt(txtNumeroEmp.getText());
    			String colonia = txtColoniaEmp.getText().trim();
    			String municipio = txtMunicipioEmp.getText();
    			String telefono= txtTelefonoEmp.getText().trim();
    			String usuario = txtUsuarioEmp.getText().trim();
    			String password = txtPasswordEmp.getText();
    			EmpleadoVO empleadoVO = new EmpleadoVO(0, nombre, aPaterno, aMaterno, calle, avenida, numero, colonia, municipio, telefono, usuario, password, "empleado");
    			if(this.empleadoVO == null){
    				if(empleadoDAO.registrar(empleadoVO)){
    					alert(AlertType.INFORMATION,"Empleado Registrado.");
    					empleados.add(empleadoDAO.lastInsert());
    					limpiarEmp(null);
    					disableFieldsEmpleado();
    				}
    				else{
    					alert(AlertType.ERROR, "Fallo registro.");
    				}
    			}
    			else{
    				empleadoVO.setId(this.empleadoVO.getId());
    				if(empleadoDAO.modificar(empleadoVO)){
    					alert(AlertType.INFORMATION,"Empleado Actualizado.");
    					empleados.removeAll(empleados);
    					fillTableEmpleado();
    					disableFieldsEmpleado();
    					limpiarEmp(null);
    				}
    				else{
    					alert(AlertType.ERROR, "Falló actualización.");
    				}
    			}
    		}
    		else{
    			alert(AlertType.ERROR, "Verifica datos no validos.");
    		}
    	}
    }

    public void limpiarEmp(ActionEvent event) {
    	txtNombreEmp.setText("");
    	txtAMaternoEmp.setText("");
    	txtAPaternoEmp.setText("");
    	txtTelefonoEmp.setText("");
    	txtUsuarioEmp.setText("");
    	txtPasswordEmp.setText("");
		txtCalleEmp.setText("");
		txtAvenidaEmp.setText("");
		txtNumeroEmp.setText("");
		txtColoniaEmp.setText("");
		txtMunicipioEmp.setText("");
		empleadoVO = null;
		enableFieldsEmpleado();
		btnModificarEmp.setDisable(true);
		btnEliminarEmp.setDisable(true);
    }

   
    public void modificarEmp(ActionEvent event) {
    	if( empleadoVO != null){
    		enableFieldsEmpleado();
        	btnModificarEmp.setDisable(true);
    	}
    	else{
    		alert(AlertType.ERROR,"No hay empleado seleccionado.");
    	}

    }

   
    public void eliminarEmp(ActionEvent event) {
    	if( empleadoVO != null){
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Confirmación");
    		//alert.setHeaderText("Look, a Confirmation Dialog");
    		alert.setContentText("¿Desea Eliminar Este Empleado? ");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    		    empleadoDAO.eliminar(empleadoVO.getId());
    		    empleados.removeAll(empleados);
    		    fillTableEmpleado();
    		}
    		}
    	else{
    		alert(AlertType.ERROR,"No hay producto seleccionado.");
    	}

    }

   
    public void guardarPro(ActionEvent event) {
    	if(txtNombrePro.getText().isEmpty() || txtAMaternoPro.getText().isEmpty() ||
    			txtAPaternoPro.getText().isEmpty() || txtEmpresaPro.getText().isEmpty()||
    			txtTelefonoPro.getText().isEmpty() ||
    			txtCallePro.getText().isEmpty() || txtAvenidaPro.getText().isEmpty() ||
    			txtNumeroPro.getText().isEmpty() || txtColoniaPro.getText().isEmpty() ||
    			txtMunicipioPro.getText().isEmpty()){
    		alert(AlertType.ERROR, "Completa los campos.");
    	}
    	else{
    		int i = 0;
    		if(validar.nombres(txtNombrePro.getText().trim())){
    			txtNombrePro.setStyle("-fx-background-color: white");
    			}else{i++; txtNombrePro.setStyle("-fx-background-color: red");}
    		if(validar.nombres(txtAMaternoPro.getText().trim())){
    			txtAMaternoPro.setStyle("-fx-background-color: white");
    		}else{i++; txtAMaternoPro.setStyle("-fx-background-color: red");}
    		if(validar.nombres(txtAPaternoPro.getText().trim())){
    			txtAPaternoPro.setStyle("-fx-background-color: white");
    		}else{i++; txtAPaternoPro.setStyle("-fx-background-color: red");}
    		if(validar.telefono(txtTelefonoPro.getText().trim())){
    			txtTelefonoPro.setStyle("-fx-background-color: white");
    		}else{i++; txtTelefonoPro.setStyle("-fx-background-color: red");}
    		if(validar.cadena(txtEmpresaPro.getText().trim())){
    			txtEmpresaPro.setStyle("-fx-background-color: white");
    		}else{i++; txtEmpresaPro.setStyle("-fx-background-color: red");}
    		if(validar.entero(txtCallePro.getText().trim())){
    			txtCallePro.setStyle("-fx-background-color: white");
    		}else{i++; txtCallePro.setStyle("-fx-background-color: red");}
    		if(validar.entero(txtAvenidaPro.getText().trim())){
    			txtAvenidaPro.setStyle("-fx-background-color: white");
    		}else{i++; txtAvenidaPro.setStyle("-fx-background-color: red");}
    		if(validar.entero(txtNumeroPro.getText().trim())){
    			txtNumeroPro.setStyle("-fx-background-color: white");
    		}else{i++; txtNumeroPro.setStyle("-fx-background-color: red");}
    		if(validar.cadena(txtColoniaPro.getText().trim())){
    			txtColoniaPro.setStyle("-fx-background-color: white");
    		}else{i++; txtColoniaPro.setStyle("-fx-background-color: red");}
    		if(validar.cadena(txtMunicipioPro.getText().trim())){
    			txtMunicipioPro.setStyle("-fx-background-color: white");
    		}else{i++; txtMunicipioPro.setStyle("-fx-background-color: red");}
    		if(i == 0){
    			String nombre = txtNombrePro.getText().trim();
    			String aPaterno = txtAPaternoPro.getText().trim();
    			String aMaterno = txtAMaternoPro.getText().trim();
    			int calle = Integer.parseInt(txtCallePro.getText());
    			String empresa = txtEmpresaPro.getText().trim();
    			int avenida = Integer.parseInt(txtAvenidaPro.getText());
    			int numero = Integer.parseInt(txtNumeroPro.getText());
    			String colonia = txtColoniaPro.getText().trim();
    			String municipio = txtMunicipioPro.getText();
    			String telefono= txtTelefonoPro.getText().trim();
    			ProveedorVO proveedorVO = new ProveedorVO(0, nombre, aPaterno, aMaterno, empresa, calle, avenida, numero, colonia, municipio, telefono);
    			if(this.proveedorVO == null){
    				if(proveedorDAO.registrar(proveedorVO)){
    					alert(AlertType.INFORMATION,"Proveedor Registrado.");
    					proveedores.add(proveedorDAO.getLast());
    					limpiarPro(null);
    					disableFieldsProveedor();
    				}
    				else{
    					alert(AlertType.ERROR, "Fallo registro.");
    				}
    			}
    			else{
    				proveedorVO.setId(this.proveedorVO.getId());
    				if(proveedorDAO.modificar(proveedorVO)){
    					alert(AlertType.INFORMATION,"Proveedor Actualizado.");
    					proveedores.removeAll(proveedores);
    					fillTableProveedor();
    					disableFieldsProveedor();
    					limpiarPro(null);
    				}
    				else{
    					alert(AlertType.ERROR, "Falló actualización.");
    				}
    			}
    		}
    		else{
    			alert(AlertType.ERROR, "Verifica datos no validos.");
    		}
    	}

    }

   
    public void limpiarPro(ActionEvent event) {
    	txtNombrePro.setText("");
    	txtAMaternoPro.setText("");
    	txtAPaternoPro.setText("");
    	txtTelefonoPro.setText("");
    	txtEmpresaPro.setText("");
		txtCallePro.setText("");
		txtAvenidaPro.setText("");
		txtNumeroPro.setText("");
		txtColoniaPro.setText("");
		txtMunicipioPro.setText("");
		proveedorVO = null;
		btnModificarPro.setDisable(true);
		btnEliminarPro.setDisable(true);
		btnGuardarPro.setDisable(false);
		enableFieldsProveedor();
    }

   
    public void modificarPro(ActionEvent event) {
    	if( proveedorVO != null){
    		enableFieldsProveedor();
        	btnModificarPro.setDisable(true);
    	}
    	else{
    		alert(AlertType.ERROR,"No hay proveedor seleccionado.");
    	}

    }

   
    public void eliminarPro(ActionEvent event) {
    	if( proveedorVO != null){
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Confirmación");
    		//alert.setHeaderText("Look, a Confirmation Dialog");
    		alert.setContentText("¿Desea Eliminar Este Proveedor? ");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    		    proveedorDAO.eliminar(proveedorVO.getId());
    		    proveedores.removeAll(proveedores);
    		    fillTableProveedor();
    		}
    		}
    	else{
    		alert(AlertType.ERROR,"No hay proveedor seleccionado.");
    	}

    }/*
    metodo para mostrar ventana categoria
    */
    public void mostrarCategoria(ActionEvent event){
    	main1.showCategoria();
    	rbtnCategoria.setSelected(false);
    	categorias.removeAll(categorias);
    	fillCategoria();
    }
    /*
    metodo para mostrar ventana marca
    */
    public void mostrarMarca(ActionEvent event){
    	main1.showMarca();
    	rbtnMarca.setSelected(false);
    	marcas.removeAll(marcas);
    	fillMarca();
    	
    }
   
    public void guardarCli(ActionEvent event) {
    	if(txtNombreCli.getText().isEmpty() || txtAMaternoCli.getText().isEmpty() ||
    			txtReferenciaCli.getText().isEmpty() ||
    			txtCalleCli.getText().isEmpty() || txtAvenidaCli.getText().isEmpty() ||
    			txtNumeroCli.getText().isEmpty() || txtColoniaCli.getText().isEmpty() ||
    			txtMunicipioCli.getText().isEmpty()){
    		alert(AlertType.ERROR, "Completa los campos.");
    	}
    	else{
    		int i = 0;
    		if(validar.nombres(txtNombreCli.getText().trim())){
    			txtNombreCli.setStyle("-fx-background-color: white");
    			}else{i++; txtNombreCli.setStyle("-fx-background-color: red");}
    		if(validar.nombres(txtAMaternoCli.getText().trim())){
    			txtAMaternoCli.setStyle("-fx-background-color: white");
    		}else{i++; txtAMaternoCli.setStyle("-fx-background-color: red");}
    		if(validar.nombres(txtAPaternoCli.getText().trim())){
    			txtAPaternoCli.setStyle("-fx-background-color: white");
    		}else{i++; txtAPaternoCli.setStyle("-fx-background-color: red");}
    		if(validar.cadena(txtReferenciaCli.getText().trim())){
    			txtReferenciaCli.setStyle("-fx-background-color: white");
    		}else{i++; txtReferenciaCli.setStyle("-fx-background-color: red");}
    		if(validar.entero(txtCalleCli.getText().trim())){
    			txtCalleCli.setStyle("-fx-background-color: white");
    		}else{i++; txtCalleCli.setStyle("-fx-background-color: red");}
    		if(validar.entero(txtAvenidaCli.getText().trim())){
    			txtAvenidaCli.setStyle("-fx-background-color: white");
    		}else{i++; txtAvenidaCli.setStyle("-fx-background-color: red");}
    		if(validar.entero(txtNumeroCli.getText().trim())){
    			txtNumeroCli.setStyle("-fx-background-color: white");
    		}else{i++; txtNumeroCli.setStyle("-fx-background-color: red");}
    		if(validar.cadena(txtColoniaCli.getText().trim())){
    			txtColoniaCli.setStyle("-fx-background-color: white");
    		}else{i++; txtColoniaCli.setStyle("-fx-background-color: red");}
    		if(validar.cadena(txtMunicipioCli.getText().trim())){
    			txtMunicipioCli.setStyle("-fx-background-color: white");
    		}else{i++; txtMunicipioCli.setStyle("-fx-background-color: red");}
    		if(i == 0){
    			String nombre = txtNombreCli.getText().trim();
    			String aPaterno = txtAPaternoCli.getText().trim();
    			String aMaterno = txtAMaternoCli.getText().trim();
    			int calle = Integer.parseInt(txtCalleCli.getText());
    			int avenida = Integer.parseInt(txtAvenidaCli.getText());
    			int numero = Integer.parseInt(txtNumeroCli.getText());
    			String colonia = txtColoniaCli.getText().trim();
    			String municipio = txtMunicipioCli.getText();
    			String referencia = txtReferenciaCli.getText().trim();
    			ClienteVO clienteVO = new ClienteVO(0, nombre, aPaterno, aMaterno, calle, avenida, numero, colonia, municipio, referencia);
    			if(this.clienteVO == null){
    				if(clienteDAO.registrar(clienteVO)){
    					alert(AlertType.INFORMATION,"Cliente Registrado.");
    					clientes.add(clienteDAO.lastInsert());
    					limpiarCli(null);
    					disableFieldsCliente();
    				}
    				else{
    					alert(AlertType.ERROR, "Fallo registro.");
    				}
    			}
    			else{
    				clienteVO.setId(this.clienteVO.getId());
    				if(clienteDAO.modificar(clienteVO)){
    					alert(AlertType.INFORMATION,"Cliente Actualizado.");
    					clientes.removeAll(clientes);
    					fillTableCliente();
    				}
    				else{
    					alert(AlertType.ERROR, "Falló actualización.");
    				}
    			}
    		}
    		else{
    			alert(AlertType.ERROR, "Verifica datos no validos.");
    		}
    	}

    }

   
    public void limpiarCli(ActionEvent event) {
    	txtNombreCli.setText("");
    	txtAMaternoCli.setText("");
    	txtAPaternoCli.setText("");
    	txtReferenciaCli.setText("");
		txtCalleCli.setText("");
		txtAvenidaCli.setText("");
		txtNumeroCli.setText("");
		txtColoniaCli.setText("");
		txtMunicipioCli.setText("");
		clienteVO = null;
		btnModificarCli.setDisable(true);
		btnGuardarCli.setDisable(false);
		enableFieldsCliente();
    }

   
    public void modificarCli(ActionEvent event) {
    	if( clienteVO != null){
    		enableFieldsCliente();;
        	btnModificarCli.setDisable(true);
    	}
    	else{
    		alert(AlertType.ERROR,"No hay cliente seleccionado.");
    	}

    }   
   
    public void actualizarRecepcionCom(ActionEvent event) {
    	// checar 3er parcial
    }

   
    public void cambiarContrasena(ActionEvent event) {
    	if(pfContrasenaP.getText().equals("") && pfNewContrasenaP.getText().equals("") && pfConfContrasenaP.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Completa los campos.");
		}
		else{
			int i = 0;
			if(validar.contrasena(pfContrasenaP.getText())){ttContrasenaP.setText(null);}else{i++; ttContrasenaP.setText("Campo Erroneo Ej: \"User4321\"");}
			if(validar.contrasena(pfNewContrasenaP.getText())){ttNewContrasenaP.setText(null);}else{i++; ttNewContrasenaP.setText("Campo Erroneo Ej: \"User4321\"");}
			if(validar.contrasena(pfConfContrasenaP.getText())){ttConfContrasenaP.setText(null);}else{i++; ttConfContrasenaP.setText("Campo Erroneo Ej: \"User4321\"");}
			if(i == 0){
				AdminDAO adminDAO = new AdminDAO();
				String pass = encrypt.encryptText(pfContrasenaP.getText());
				if(adminDAO.user(pass)){
					if(pfNewContrasenaP.getText().equals(pfConfContrasenaP.getText())){
						if(adminDAO.actualizar("",pfNewContrasenaP.getText())){
							JOptionPane.showMessageDialog(null, "Informacion Actualizada.");
							
							pfContrasenaP.setText(null);
							pfNewContrasenaP.setText(null);
							pfConfContrasenaP.setText(null);
						}
						else{
							JOptionPane.showMessageDialog(null, "Falló Actualización.");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Contraseña Actual Invalida.");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Verifica los campos.");
			}
		}
    }
   
    public void cancelarCambiarContrasena(ActionEvent event) {
		pfContrasenaP.setText(null);
		pfNewContrasenaP.setText(null);
		pfConfContrasenaP.setText(null);
    }

   
    public void cerrarSesion(ActionEvent event) {
    	Principal.loginAdm = false;
		main1.showLoginEmp();
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
    	categorias = FXCollections.observableArrayList(catVO.listarCategoria());
    	cbCategoria.setItems(categorias);
    }
    /*
     * Metodo para llenar ComboBox marca
     * */
    public void fillMarca(){
    	marcas = FXCollections.observableArrayList(marVO.listarMarca());
    	cbMarca.setItems(marcas);
    }
    
    /*
     * Metodo para inicilaizar tabla prodcutos
     * */
    public void tableProducto(){
    	tcProductoProd.setCellValueFactory(new PropertyValueFactory<ProductoVO, String>("nombreProducto"));
    	tcMarcaProd.setCellValueFactory(new PropertyValueFactory<ProductoVO, String>("marca"));
    	tcPrecio1Prod.setCellValueFactory(new PropertyValueFactory<ProductoVO, Double>("precio1"));
    	tcPrecio2Prod.setCellValueFactory(new PropertyValueFactory<ProductoVO, Double>("precio2"));
    	tcStockMinProd.setCellValueFactory(new PropertyValueFactory<ProductoVO, Integer>("stockMin"));
    	tcTipoProd.setCellValueFactory(new PropertyValueFactory<ProductoVO, String>("tipo"));
    }
    /*
     * Metodo para llenar tabla Producto
     * */
    public void fillTableProducto(){
    	productos = FXCollections.observableArrayList(productoDAO.getDatos());
    	//tvProductos.getItems().addAll(productos);
    	tvProductos.setItems(productos);
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
    		//String stock = String.valueOf(productoVO.getStock());
    		//txtStockProd.setText(stock);
    		String stockMax = String.valueOf(productoVO.getStockMax());
    		txtStockMaxProd.setText(stockMax);
    		String stockMin = String.valueOf(productoVO.getStockMin());
    		txtStockMinProd.setText(stockMin);
    		cbTipo.getSelectionModel().select(productoVO.getTipo());
    		btnModificar.setDisable(false);
    		btnEliminar.setDisable(false);    		
    	}
    	
    }


    

    /*
     * Metodo para declarar atributos de la tabla empleado
     * */
    public void tableEmpleado(){
    	tcEmpleadoEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("nombreEmpleado"));
    	tcMunicipioEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("municipio"));
    	tcTelefonoEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("telefono"));
    	tcUsuarioEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("usuario"));
    	tcContrasenaEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("password"));
    }
    /*
     * Metodo para llenar tabla empleado
     * */
    public void fillTableEmpleado(){
    	empleados = FXCollections.observableArrayList(empleadoDAO.getDatos());
    	//tvEmpleados.getItems().addAll(empleados);
    	tvEmpleados.setItems(empleados);
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
    		btnModificarEmp.setDisable(false);
    		btnEliminarEmp.setDisable(false); 
    	}
    }
    /*
     * Metodo para declarar atributos de la tabla proveedor
     * */
    public void tableProveedor(){
    	tcProveedorPro.setCellValueFactory(new PropertyValueFactory<ProveedorVO, String>("nombreProveedor"));
    	tcEmpresaPro.setCellValueFactory(new PropertyValueFactory<ProveedorVO, String>("empresa"));
    	tcMunicipioPro.setCellValueFactory(new PropertyValueFactory<ProveedorVO, String>("municipio"));
    	tcTelefonoPro.setCellValueFactory(new PropertyValueFactory<ProveedorVO, String>("telefono"));
    	
    }
    /*
     * Metodo para llenar tabla proveedor
     * */
    public void fillTableProveedor(){
    	proveedores = FXCollections.observableArrayList(proveedorDAO.getDatos());
    	//tvProveedor.getItems().addAll(proveedores);
    	tvProveedor.setItems(proveedores);
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
    		txtEmpresaPro.setText(proveedorVO.getEmpresa());
    		String calle = String.valueOf(proveedorVO.getCalle());
    		txtCallePro.setText(calle);
    		String avenida = String.valueOf(proveedorVO.getAvenida());
    		txtAvenidaPro.setText(avenida);
    		String numero = String.valueOf(proveedorVO.getNumero());
    		txtNumeroPro.setText(numero);
    		txtColoniaPro.setText(proveedorVO.getColonia());
    		txtMunicipioPro.setText(proveedorVO.getMunicipio());
    		txtTelefonoPro.setText(proveedorVO.getTelefono());
    		btnModificarPro.setDisable(false);
    		btnEliminarPro.setDisable(false);
    	}
    }
    /*
     * Metodo para declarar atributos de la tabla cliente
     * */
    public void tableCliente(){
    	tcClienteCli.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>("nombreCliente"));
    	tcMunicipioCli.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>("municipio"));
    	tcReferenciaCli.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>("referencia"));
    }
    /*
     * Metodo para llenar tabla cliente
     * */
    public void fillTableCliente(){
    	clientes = FXCollections.observableArrayList(clienteDAO.getDatos());
    	//tvCliente.getItems().addAll(clientes);
    	tvCliente.setItems(clientes);
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
    		btnModificarCli.setDisable(false);
    		btnModificar.setDisable(false);
    		btnEliminar.setDisable(false); 
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
    	//txtStockProd.setDisable(true);
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
    	//txtStockProd.setDisable(false);
    	txtStockMaxProd.setDisable(false);
    	txtStockMinProd.setDisable(false);
    	cbTipo.setDisable(false);
    	btnGuardarProd.setDisable(false);
    	btnCancelarP.setDisable(false);
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
		//btnLimpiarEmp.setDisable(true);
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
		//btnLimpiarEmp.setDefaultButton(false);
    }
/*
    
     * Metodo para desabilitar campos de proveedor
     * */
    public void disableFieldsProveedor(){
		txtNombrePro.setDisable(true);
		txtAPaternoPro.setDisable(true);
		txtAMaternoPro.setDisable(true);
		txtEmpresaPro.setDisable(true);
		txtCallePro.setDisable(true);
		txtAvenidaPro.setDisable(true);
		txtNumeroPro.setDisable(true);
		txtColoniaPro.setDisable(true);
		txtMunicipioPro.setDisable(true);
		txtTelefonoPro.setDisable(true);
		btnGuardarPro.setDisable(true);
		//btnLimpiarPro.setDisable(true);
    }
/*
    
     * Metodo para habilitar campos de proveedor
     * */
    public void enableFieldsProveedor(){
    	txtNombrePro.setDisable(false);
		txtAPaternoPro.setDisable(false);
		txtAMaternoPro.setDisable(false);
		txtEmpresaPro.setDisable(false);
		txtCallePro.setDisable(false);
		txtAvenidaPro.setDisable(false);
		txtNumeroPro.setDisable(false);
		txtColoniaPro.setDisable(false);
		txtMunicipioPro.setDisable(false);
		txtTelefonoPro.setDisable(false);
		btnGuardarPro.setDisable(false);
		//btnLimpiarPro.setDisable(false);
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
		//btnLimpiarCli.setDisable(true);
    }
/*
    
     * Metodo para habilitar campos de cliente
     * */
    public void enableFieldsCliente(){
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
		//btnLimpiarCli.setDisable(false);
    }
    
    /*
    /*
     
     * Metodo para
     * */
	public void setMain1(view.Main1 main1) {
		this.main1 = main1;
	}
}
