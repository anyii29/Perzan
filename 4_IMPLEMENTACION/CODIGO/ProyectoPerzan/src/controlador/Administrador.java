package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import view.Main1;
import modelo.AdminDAO;
import modelo.BaseDatos;
import modelo.CategoriaVO;
import modelo.ClienteDAO;
import modelo.ClienteVO;
import modelo.CompraDAO;
import modelo.CompraDetVO;
import modelo.CompraVO;
import modelo.CsvFiles;
import modelo.DetalleCompraDAO;
import modelo.DetalleCompraVO;
import modelo.DetalleVentaDAO;
import modelo.DetalleVentaVO;
import modelo.EmpleadoDAO;
import modelo.EmpleadoVO;
import modelo.Encrypt;
import modelo.InventarioVO;
import modelo.Logger;
import modelo.MarcaVO;
import modelo.ProductoDAO;
import modelo.ProductoVO;
import modelo.ProveedorDAO;
import modelo.ProveedorVO;
import modelo.Reportes;
import modelo.StockVO;
import modelo.VentaDAO;
import modelo.VentaDetVO;
import modelo.VentaVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

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
	private BaseDatos baseDatos;
	private CsvFiles csv;
	private CompraDetVO compraDetVO;
	private Logger log;
	private Reportes reporte;
	private ObservableList<ProductoVO> productos;
	private ObservableList<EmpleadoVO> empleados;
	private ObservableList<ProveedorVO> proveedores;
	private ObservableList<ClienteVO> clientes;
	private ObservableList<ProductoVO> delProductos;
	private ObservableList<EmpleadoVO> delEmpleados;
	private ObservableList<ProveedorVO> delProveedores;
	private ObservableList<ClienteVO> delClientes;
	private ObservableList<CompraDetVO> comprasCSV;
	private ObservableList<VentaDetVO> ventasCSV;
	private ObservableList<CompraVO> compras;
	private ObservableList<DetalleCompraVO> detalleCompras;
	private ObservableList<VentaVO> ventas;
	private ObservableList<DetalleVentaVO> detalleVentas;               
	private ObservableList<CategoriaVO> categorias;
	private ObservableList<MarcaVO> marcas;
	private ObservableList<StockVO> stock;
	private FilteredList<StockVO> historialStock;
	private FilteredList<ProductoVO> productosFound;
	private FilteredList<EmpleadoVO> empleadosFound;
	private FilteredList<ClienteVO> clientesFound;
	private FilteredList<ProveedorVO> proveedoresFound;
	private FilteredList<DetalleCompraVO> detalleCompraFiltro;
	private FilteredList<DetalleVentaVO> detalleVentaFiltro;
	
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
		baseDatos = new BaseDatos();
		csv = new CsvFiles();
		log = new Logger();
		reporte = new Reportes();
		stock = FXCollections.observableArrayList();
		//compraDetVO = new CompraDetVO();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		cbTipo.getItems().addAll("interior", "exterior");
		cbCSV.getItems().addAll("Producto","Empleado","Cliente","Proveedor","Compra","Venta");
		cbReportes.getItems().addAll("Producto","Empleado","Cliente","Proveedor","Compra","Venta");
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
		this.tableStock();
		this.fillTableStock();
		detalleCompraFiltro = new FilteredList<DetalleCompraVO>(detalleCompras);
		detalleVentaFiltro = new FilteredList<DetalleVentaVO>(detalleVentas);
		historialStock = new FilteredList<StockVO>(stock);
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
		tcLista.setCellValueFactory(new PropertyValueFactory<ProductoVO, String>(
				"nombreProducto"));
		txtIdP.setDisable(true);
		txtDescripcionP.setDisable(true);
		txtExistenciaP.setDisable(true);
		btnResProd.setVisible(false);
		btnResEmp.setVisible(false);
		btnResPro.setVisible(false);
		btnResCli.setVisible(false);
		registros(lblTDetalleCompras, 0);
		registros(lblTDetalleVentas, 0);
	}

	@FXML
	private Button btnModificarPro;
	@FXML
	private Button btnLimpiarEmp;
	@FXML
	private Button btnGuardarPro;
	@FXML
	private Button btnModificarEmp;
	@FXML
	private Button btnLimpiarPro;
	@FXML
	private Button btnLimpiarProd;
	@FXML
	private Button btnGuardarEmp;
	@FXML
	private Button btnEliminar;
	@FXML
	private Button btnCancelarP;
	@FXML
	private Button btnModificarCli;
	@FXML
	private Button btnGuardarCli;
	@FXML
	private Button btnEliminarEmp;
	@FXML
	private Button btnModificar;
	@FXML
	private Button btnGuardarProd;
	@FXML
	private Button btnAceptarP;
	@FXML
	private Button btnActualizarRecepcion;
	@FXML
	private Button btnCerrarSesion;
	@FXML
	private Button btnLimpiarCli;
	@FXML
	private Button btnEliminarPro;
	@FXML
	private Button btnGuardarInv;
	@FXML
	private Button btnCancelarInv;
	@FXML
	private Button btnRespaldar;
	@FXML
	private Button btnRestaurar;
	@FXML
	private Button btnEliminarCli;
	@FXML
	private Button btnCrearReporte;
	@FXML
	private Button btnCrearCSV;
	@FXML
	private Button btnLeerCSV;
	@FXML
	private Button btnLog;
	@FXML
	private Button btnResProd;
	@FXML
	private CheckBox ckbDelProd;
	@FXML
	private Button btnResPro;
	@FXML
	private CheckBox ckbDelPro;
	@FXML
	private Button btnResEmp;
	@FXML
	private CheckBox ckbDelEmp;
	@FXML
	private Button btnResCli;
	@FXML
	private CheckBox ckbDelCli;
	@FXML
	private TextArea txtReferenciaCli;
	@FXML
	private TextField txtNombreCli;
	@FXML
	private TextField txtAMaternoEmp;
	@FXML
	private TextField txtTelefonoEmp;
	@FXML
	private TextField txtNumeroCli;
	@FXML
	private TextField txtAMaternoCli;
	@FXML
	private TextField txtUsuarioEmp;
	@FXML
	private TextField txtAPaternoPro;
	@FXML
	private TextField txtCallePro;
	@FXML
	private TextField txtAPaternoEmp;
	@FXML
	private TextField txtMunicipioCli;
	@FXML
	private TextField txtPrecio2Prod;
	@FXML
	private TextField txtNombreEmp;
	@FXML
	private TextField txtColoniaEmp;
	@FXML
	private TextField txtStockMaxProd;
	@FXML
	private TextField txtStockMinProd;
	@FXML
	private TextField txtColoniaPro;
	@FXML
	private TextField txtCalleEmp;
	@FXML
	private TextField txtNombrePro;
	@FXML
	private TextField txtAvenidaPro;
	@FXML
	private TextField txtAPaternoCli;
	@FXML
	private TextField txtBuscar;
	@FXML
	private TextField txtPrecio1Prod;
	@FXML
	private TextField txtCalleCli;
	@FXML
	private TextField txtAMaternoPro;
	@FXML
	private TextField txtMunicipioEmp;
	@FXML
	private TextField txtNumeroEmp;
	@FXML
	private TextField txtNumeroPro;
	@FXML
	private TextField txtColoniaCli;
	@FXML
	private TextField txtMunicipioPro;
	@FXML
	private PasswordField txtPasswordEmp;
	@FXML
	private PasswordField txtPasswordConfEmp;
	@FXML
	private TextField txtAvenidaEmp;
	@FXML
	private TextField txtAvenidaCli;
	@FXML
	private TextField txtTelefonoPro;
	@FXML
	private TextField txtDescripcionProd;
	@FXML
	private TextField txtEmpresaPro;
	@FXML
	private TextField txtIdP;
	@FXML
	private TextField txtDescripcionP;
	@FXML
	private TextField txtExistenciaP;
	@FXML
	private TextField txtNuevaExistenciaP;
	@FXML
	private TextField txtBuscarCli;
	@FXML
	private TextField txtBuscarPro;
	@FXML
	private TextField txtBuscarEmp;
	@FXML
	private TextArea txtCausaP;
	@FXML
	private Label lblProductos;
	@FXML
	private Label lblProductos1;
	@FXML
	private Label lblEmpleados;
	@FXML
	private Label lblClientes;
	@FXML
	private Label lblProveedores;
	@FXML
	private Label lblTCompras;
	@FXML
	private Label lblTDetalleCompras;
	@FXML
	private Label lblTVentas;
	@FXML
	private Label lblTDetalleVentas;
	@FXML
	private Label lblTStock;
	@FXML
	private PasswordField pfConfContrasenaP, pfNewContrasenaP, pfContrasenaP;
	@FXML
	private ComboBox<String> cbTipo;
	@FXML
	private ComboBox<MarcaVO> cbMarca;
	@FXML
	private ComboBox<CategoriaVO> cbCategoria;
	@FXML
	private ComboBox<String> cbCSV;
	@FXML
	private ComboBox<String> cbReportes;
	@FXML
	private TableView<ProductoVO> tvProductos;
	@FXML
	private TableColumn<ProductoVO, Double> tcPrecio2Prod;
	@FXML
	private TableColumn<ProductoVO, String> tcProductoProd;
	@FXML
	private TableColumn<ProductoVO, String> tcTipoProd;
	@FXML
	private TableColumn<ProductoVO, Integer> tcStockProd;
	@FXML
	private TableColumn<ProductoVO, Double> tcPrecio1Prod;
	@FXML
	private TableColumn<ProductoVO, String> tcMarcaProd;
	@FXML
	private TableView<EmpleadoVO> tvEmpleados;
	@FXML
	private TableColumn<EmpleadoVO, String> tcUsuarioEmp;
	@FXML
	private TableColumn<EmpleadoVO, String> tcTelefonoEmp;
	@FXML
	private TableColumn<EmpleadoVO, String> tcEmpleadoEmp;
	@FXML
	private TableColumn<EmpleadoVO, String> tcMunicipioEmp;
	@FXML
	private TableView<ProveedorVO> tvProveedor;
	@FXML
	private TableColumn<ProveedorVO, String> tcEmpresaPro;
	@FXML
	private TableColumn<ProveedorVO, String> tcTelefonoPro;
	@FXML
	private TableColumn<ProveedorVO, String> tcProveedorPro;
	@FXML
	private TableColumn<ProveedorVO, String> tcMunicipioPro;
	@FXML
	private TableView<ClienteVO> tvCliente;
	@FXML
	private TableColumn<ClienteVO, String> tcMunicipioCli;
	@FXML
	private TableColumn<ClienteVO, String> tcClienteCli;
	@FXML
	private TableColumn<ClienteVO, String> tcReferenciaCli;
	@FXML
	private TableView<StockVO> tvNuevoStock;
	@FXML
	private TableColumn<StockVO, String> tcCausaStock;
	@FXML
	private TableColumn<StockVO, Integer> tcNuevoStock;
	@FXML
	private TableColumn<StockVO, Timestamp> tcFechaHoraStock;
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
    @FXML
	private TableView<ProductoVO> tvListProducts;
	@FXML
	private TableColumn<ProductoVO, String> tcLista;
	@FXML
	private ListView<String> lvRespaldos;
	@FXML
	private RadioButton rbtnCategoria;
	@FXML
	private RadioButton rbtnMarca;

	/*
	 * *metodos Controlador
	 */

	public void guardarProd(ActionEvent event) {
		if (txtDescripcionProd.getText().trim().equals("")
				|| txtPrecio1Prod.getText().trim().equals("")
				|| txtPrecio2Prod.getText().trim().equals("")
				|| txtStockMaxProd.getText().trim().equals("")
				|| txtStockMinProd.getText().trim().equals("")
				|| cbCategoria.getValue() == null || cbMarca.getValue() == null
				|| cbCategoria.getValue() == null) {
			alert(AlertType.ERROR, "Completa los campos.");
		} else {
			int i = 0;
			if (validar.cadena(txtDescripcionProd.getText())) {
				txtDescripcionProd.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtDescripcionProd.setStyle("-fx-background-color: red");
			}
			if (validar.precio(txtPrecio1Prod.getText())) {
				txtPrecio1Prod.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtPrecio1Prod.setStyle("-fx-background-color: red");
			}
			if (validar.precio(txtPrecio2Prod.getText())) {
				txtPrecio2Prod.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtPrecio2Prod.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtStockMaxProd.getText())) {
				txtStockMaxProd.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtStockMaxProd.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtStockMinProd.getText())) {
				txtStockMinProd.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtStockMinProd.setStyle("-fx-background-color: red");
			}
			if (i == 0) {
				int stockMax = Integer.parseInt(txtStockMaxProd.getText());
				int stockMin = Integer.parseInt(txtStockMinProd.getText());
				float precio1 = Float.valueOf(txtPrecio1Prod.getText());
				float precio2 = Float.valueOf(txtPrecio2Prod.getText());
				if (precio1 > 0 && precio2 > 0 && precio1 > precio2
						&& stockMax > 0 && stockMin > 0 && stockMax > stockMin) {
					CategoriaVO catVO = cbCategoria.getValue();
					MarcaVO marVO = cbMarca.getValue();
					String tipo = (String) cbTipo.getValue();
					ProductoVO productoVO = new ProductoVO(0, catVO,
							txtDescripcionProd.getText().trim(), marVO,
							precio1, precio2, 0, stockMax, stockMin, tipo);
					if (this.productoVO == null) {
						if (productoDAO.registrar(productoVO)) {
							alert(AlertType.INFORMATION, "Producto registrado.");
							productos.add(productoDAO.lastInsert());
							limpiarProd(null);
							disableFieldsProducto();
							fillTableStock();
						} else {
							alert(AlertType.ERROR, "Falló registro.");
						}
					} else {
						productoVO.setId(this.productoVO.getId());
						if (productoDAO.modificar(productoVO)) {
							productos.removeAll(productos);
							fillTableProducto();
							limpiarProd(null);
							disableFieldsProducto();
							fillTableStock();
							alert(AlertType.INFORMATION, "Producto actualizado.");
						} else {
							alert(AlertType.ERROR, "Falló registro.");
						}
					}
				} else {
					alert(AlertType.INFORMATION,
							"Precios y Stock deben ser diferentes de 0, \n"
									+ " Precio1 debe ser mayor que precio2,"
									+ "Stock max debe ser mayor que Stock min.");
				}
			} else {
				alert(AlertType.ERROR, "Verifica los campos");
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
		if (productoVO != null) {
			enableFieldsProducto();
			btnModificar.setDisable(true);
		} else {
			alert(AlertType.ERROR, "No hay producto seleccionado.");
		}

	}

	public void eliminarProd(ActionEvent event) {
		if (productoVO != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmación");
			// alert.setHeaderText("Look, a Confirmation Dialog");
			alert.setContentText("¿Desea Eliminar Este Producto? ");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				if(productoDAO.eliminar(productoVO.getId())){
					//productos.removeAll(productos);
					//fillTableProducto();
					productos.remove(productoVO);
					delProductos.add(productoVO);
					disableFieldsProducto();
				}
				else{
					alert(AlertType.ERROR, "No se elimino el producto.");
				}
			}
		} else {
			alert(AlertType.ERROR, "No hay producto seleccionado.");
		}

	}

	@FXML
	public void buscarProd() {
		if(!ckbDelProd.isSelected()){
			productosFound = new FilteredList<>(productos);
			if (txtBuscar.getText().isEmpty()) {
				tvProductos.setItems(productos);
				registros(lblProductos, productos.size());
			} else {
				productosFound.setPredicate(ProductoVO -> ProductoVO
						.getNombreProducto().toLowerCase()
						.contains(txtBuscar.getText().trim().toLowerCase()));
				tvProductos.setItems(productosFound);
				registros(lblProductos, productosFound.size());
			}
		}
		else{
			productosFound = new FilteredList<>(delProductos);
			if (txtBuscar.getText().isEmpty()) {
				tvProductos.setItems(delProductos);
				registros(lblProductos, delProductos.size());
			} else {
				productosFound.setPredicate(ProductoVO -> ProductoVO
						.getNombreProducto().toLowerCase()
						.contains(txtBuscar.getText().trim().toLowerCase()));
				tvProductos.setItems(productosFound);
				registros(lblProductos, productosFound.size());
			}
		}
		
	}
	@FXML
	public void buscarEmp() {
		if(!ckbDelEmp.isSelected()){
			empleadosFound = new FilteredList<EmpleadoVO>(empleados);
			if (txtBuscarEmp.getText().isEmpty()) {
				tvEmpleados.setItems(empleados);
				registros(lblEmpleados, empleados.size());
			} else {
				empleadosFound.setPredicate(EmpleadoVO -> EmpleadoVO
						.getNombreEmpleado().toLowerCase()
						.contains(txtBuscarEmp.getText().trim().toLowerCase()));
				tvEmpleados.setItems(empleadosFound);
				registros(lblEmpleados, empleadosFound.size());
			}
		}
		else{
			empleadosFound = new FilteredList<EmpleadoVO>(delEmpleados);
			if (txtBuscarEmp.getText().isEmpty()) {
				tvEmpleados.setItems(delEmpleados);
				registros(lblEmpleados, delEmpleados.size());
			} else {
				empleadosFound.setPredicate(EmpleadoVO -> EmpleadoVO
						.getNombreEmpleado().toLowerCase()
						.contains(txtBuscarEmp.getText().trim().toLowerCase()));
				tvEmpleados.setItems(empleadosFound);
				registros(lblEmpleados, empleadosFound.size());
			}
		}
	}
	@FXML
	public void buscarCli() {
		if(!ckbDelCli.isSelected()){
			clientesFound = new FilteredList<ClienteVO>(clientes);
			if (txtBuscarCli.getText().isEmpty()) {
				tvCliente.setItems(clientes);
				registros(lblClientes, clientes.size());
			} else {
				clientesFound.setPredicate(ClienteVO -> ClienteVO
						.getNombreCliente().toLowerCase()
						.contains(txtBuscarCli.getText().trim().toLowerCase()));
				tvCliente.setItems(clientesFound);
				registros(lblClientes, clientesFound.size());
			}
		}
		else{
			clientesFound = new FilteredList<ClienteVO>(delClientes);
			if (txtBuscarCli.getText().isEmpty()) {
				tvCliente.setItems(delClientes);
				registros(lblClientes, delClientes.size());
			} else {
				clientesFound.setPredicate(ClienteVO -> ClienteVO
						.getNombreCliente().toLowerCase()
						.contains(txtBuscarCli.getText().trim().toLowerCase()));
				tvCliente.setItems(clientesFound);
				registros(lblClientes, clientesFound.size());
			}
		}
	}
	@FXML
	public void buscarPro() {
		if(!ckbDelPro.isSelected()){
			proveedoresFound = new FilteredList<ProveedorVO>(proveedores);
			if (txtBuscarPro.getText().isEmpty()) {
				tvProveedor.setItems(proveedores);
				registros(lblProveedores, proveedores.size());
			} else {
				proveedoresFound.setPredicate(ProveedorVO -> ProveedorVO
						.getNombreProveedor().toLowerCase()
						.contains(txtBuscarPro.getText().trim().toLowerCase()));
				tvProveedor.setItems(proveedoresFound);
				registros(lblProveedores, proveedoresFound.size());
			}
		}
		else{
			proveedoresFound = new FilteredList<ProveedorVO>(delProveedores);
			if (txtBuscarPro.getText().isEmpty()) {
				tvProveedor.setItems(delProveedores);
				registros(lblProveedores, delProveedores.size());
			} else {
				proveedoresFound.setPredicate(ProveedorVO -> ProveedorVO
						.getNombreProveedor().toLowerCase()
						.contains(txtBuscarPro.getText().trim().toLowerCase()));
				tvProveedor.setItems(proveedoresFound);
				registros(lblProveedores, proveedoresFound.size());
			}
		}
	}


	public void guardarInv(ActionEvent event) {
		if (txtIdP.getText().isEmpty() || txtDescripcionP.getText().isEmpty()
				|| txtExistenciaP.getText().isEmpty()
				|| txtNuevaExistenciaP.getText().isEmpty()
				|| txtCausaP.getText().isEmpty()) {
			alert(AlertType.ERROR, "Complete los campos.");
		} else {
			int i = 0;
			if (validar.cadena(txtDescripcionP.getText())) {
				txtDescripcionP.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtDescripcionP.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtIdP.getText())) {
				txtIdP.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtIdP.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtExistenciaP.getText())) {
				txtExistenciaP.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtExistenciaP.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtNuevaExistenciaP.getText())) {
				txtNuevaExistenciaP.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtNuevaExistenciaP.setStyle("-fx-background-color: red");
			}
			if (validar.cadena(txtCausaP.getText())) {
				txtCausaP.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtCausaP.setStyle("-fx-background-color: red");
			}
			if (i == 0) {
				int idProducto = Integer.parseInt(txtIdP.getText().trim());
				String causa = txtCausaP.getText().trim();
				int existencia = Integer.parseInt(txtExistenciaP.getText()
						.trim());
				int nuevaExistencia = Integer.parseInt(txtNuevaExistenciaP
						.getText().trim());
				// int idEmpleado
				if(nuevaExistencia > existencia){
					alert(AlertType.ERROR, "Stock maximo alcanzado.");
					txtNuevaExistenciaP.requestFocus();
				}
				else{
					InventarioVO inventarioVO = new InventarioVO(idProducto,
							existencia, nuevaExistencia, 4, causa);
					if (productoDAO.inventario(inventarioVO)) {
						alert(AlertType.INFORMATION,
								"Actualización de inventario correcta.");
						fillTableProducto();
						txtIdP.setText("");
						txtDescripcionP.setText("");
						txtExistenciaP.setText("");
						txtNuevaExistenciaP.setText("");
						txtCausaP.setText("");
						fillTableStock();
					} else {
						alert(AlertType.ERROR, "Falló actualización.");
					}
				}
			}
		}
	}

	public void cancelarInv(ActionEvent event) {
		txtIdP.setText("");
		txtDescripcionP.setText("");
		txtExistenciaP.setText("");
		txtNuevaExistenciaP.setText("");
		txtCausaP.setText("");
	}

	@FXML
	public void listSelected() {
		if (tvListProducts.getSelectionModel().getSelectedItem() != null) {
			productoVO = tvListProducts.getSelectionModel().getSelectedItem();
			txtIdP.setText(String.valueOf(productoVO.getId()));
			txtDescripcionP.setText(productoVO.getNombreProducto());
			txtExistenciaP.setText(String.valueOf(productoVO.getStock()));
			selectedStock();
		} else {
			alert(AlertType.ERROR, "No hay producto seleccionado.");
		}
	}

	public void guardarEmp(ActionEvent event) {
		if (txtNombreEmp.getText().isEmpty()
				|| txtAMaternoEmp.getText().isEmpty()
				|| txtAPaternoEmp.getText().isEmpty()
				|| txtTelefonoEmp.getText().isEmpty()
				|| txtUsuarioEmp.getText().isEmpty()
				|| txtPasswordEmp.getText().isEmpty()
				|| txtPasswordConfEmp.getText().isEmpty()
				|| txtCalleEmp.getText().isEmpty()
				|| txtAvenidaEmp.getText().isEmpty()
				|| txtNumeroEmp.getText().isEmpty()
				|| txtColoniaEmp.getText().isEmpty()
				|| txtMunicipioEmp.getText().isEmpty()) {
			alert(AlertType.ERROR, "Completa los campos.");
		} else {
			int i = 0;
			if (validar.nombres(txtNombreEmp.getText().trim())) {
				txtNombreEmp.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtNombreEmp.setStyle("-fx-background-color: red");
			}
			if (validar.nombres(txtAMaternoEmp.getText().trim())) {
				txtAMaternoEmp.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtAMaternoEmp.setStyle("-fx-background-color: red");
			}
			if (validar.nombres(txtAPaternoEmp.getText().trim())) {
				txtAPaternoEmp.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtAPaternoEmp.setStyle("-fx-background-color: red");
			}
			if (validar.telefono(txtTelefonoEmp.getText().trim())) {
				txtTelefonoEmp.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtTelefonoEmp.setStyle("-fx-background-color: red");
			}
			if (validar.usuario(txtUsuarioEmp.getText().trim())) {
				txtUsuarioEmp.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtUsuarioEmp.setStyle("-fx-background-color: red");
			}
			if (txtPasswordEmp.getText().length() != 32) {
				if (validar.contrasena(txtPasswordEmp.getText().trim())) {
					txtPasswordEmp.setStyle("-fx-background-color: white");
				} else {
					i++;
					txtPasswordEmp.setStyle("-fx-background-color: red");
				}
			}
			if(!txtPasswordEmp.getText().trim().equals(txtPasswordConfEmp.getText().trim())){
				i++;
				alert(AlertType.ERROR, "Las contraseñas no coinciden.");
			}
			if (validar.entero(txtCalleEmp.getText().trim())) {
				txtCalleEmp.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtCalleEmp.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtAvenidaEmp.getText().trim())) {
				txtAvenidaEmp.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtAvenidaEmp.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtNumeroEmp.getText().trim())) {
				txtNumeroEmp.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtNumeroEmp.setStyle("-fx-background-color: red");
			}
			if (validar.cadena(txtColoniaEmp.getText().trim())) {
				txtColoniaEmp.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtColoniaEmp.setStyle("-fx-background-color: red");
			}
			if (validar.cadena(txtMunicipioEmp.getText().trim())) {
				txtMunicipioEmp.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtMunicipioEmp.setStyle("-fx-background-color: red");
			}
			if (i == 0) {
				String nombre = txtNombreEmp.getText().trim();
				String aPaterno = txtAPaternoEmp.getText().trim();
				String aMaterno = txtAMaternoEmp.getText().trim();
				int calle = Integer.parseInt(txtCalleEmp.getText());
				int avenida = Integer.parseInt(txtAvenidaEmp.getText());
				int numero = Integer.parseInt(txtNumeroEmp.getText());
				String colonia = txtColoniaEmp.getText().trim();
				String municipio = txtMunicipioEmp.getText();
				String telefono = txtTelefonoEmp.getText().trim();
				String usuario = txtUsuarioEmp.getText().trim();
				String password = txtPasswordEmp.getText();
				EmpleadoVO empleadoVO = new EmpleadoVO(0, nombre, aPaterno,
						aMaterno, calle, avenida, numero, colonia, municipio,
						telefono, usuario, password, "empleado");
				if (this.empleadoVO == null) {
					if (empleadoDAO.registrar(empleadoVO)) {
						alert(AlertType.INFORMATION, "Empleado registrado.");
						empleados.add(empleadoDAO.lastInsert());
						limpiarEmp(null);
						disableFieldsEmpleado();
					} else {
						alert(AlertType.ERROR, "Falló registro.");
					}
				} else {
					empleadoVO.setId(this.empleadoVO.getId());
					if (empleadoDAO.modificar(empleadoVO)) {
						alert(AlertType.INFORMATION, "Empleado actualizado.");
						empleados.removeAll(empleados);
						fillTableEmpleado();
						disableFieldsEmpleado();
						limpiarEmp(null);
					} else {
						alert(AlertType.ERROR, "Falló actualización.");
					}
				}
			} else {
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
		txtPasswordConfEmp.setText("");
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
		if (empleadoVO != null) {
			enableFieldsEmpleado();
			btnModificarEmp.setDisable(true);
		} else {
			alert(AlertType.ERROR, "No hay empleado seleccionado.");
		}

	}

	public void eliminarEmp(ActionEvent event) {
		if (empleadoVO != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmación");
			// alert.setHeaderText("Look, a Confirmation Dialog");
			alert.setContentText("¿Desea Eliminar Este Empleado? ");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				if(empleadoDAO.eliminar(empleadoVO.getId())){
					//empleados.removeAll(empleados);
					//fillTableEmpleado();
					empleados.remove(empleadoVO);
					delEmpleados.add(empleadoVO);
					disableFieldsEmpleado();
				}
				else{
					alert(AlertType.ERROR, "No se elimino el proveedor.");
				}
			}
		} else {
			alert(AlertType.ERROR, "No hay producto seleccionado.");
		}

	}

	public void guardarPro(ActionEvent event) {
		if (txtNombrePro.getText().isEmpty()
				|| txtAMaternoPro.getText().isEmpty()
				|| txtAPaternoPro.getText().isEmpty()
				|| txtEmpresaPro.getText().isEmpty()
				|| txtTelefonoPro.getText().isEmpty()
				|| txtCallePro.getText().isEmpty()
				|| txtAvenidaPro.getText().isEmpty()
				|| txtNumeroPro.getText().isEmpty()
				|| txtColoniaPro.getText().isEmpty()
				|| txtMunicipioPro.getText().isEmpty()) {
			alert(AlertType.ERROR, "Completa los campos.");
		} else {
			int i = 0;
			if (validar.nombres(txtNombrePro.getText().trim())) {
				txtNombrePro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtNombrePro.setStyle("-fx-background-color: red");
			}
			if (validar.nombres(txtAMaternoPro.getText().trim())) {
				txtAMaternoPro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtAMaternoPro.setStyle("-fx-background-color: red");
			}
			if (validar.nombres(txtAPaternoPro.getText().trim())) {
				txtAPaternoPro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtAPaternoPro.setStyle("-fx-background-color: red");
			}
			if (validar.telefono(txtTelefonoPro.getText().trim())) {
				txtTelefonoPro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtTelefonoPro.setStyle("-fx-background-color: red");
			}
			if (validar.cadena(txtEmpresaPro.getText().trim())) {
				txtEmpresaPro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtEmpresaPro.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtCallePro.getText().trim())) {
				txtCallePro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtCallePro.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtAvenidaPro.getText().trim())) {
				txtAvenidaPro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtAvenidaPro.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtNumeroPro.getText().trim())) {
				txtNumeroPro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtNumeroPro.setStyle("-fx-background-color: red");
			}
			if (validar.cadena(txtColoniaPro.getText().trim())) {
				txtColoniaPro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtColoniaPro.setStyle("-fx-background-color: red");
			}
			if (validar.cadena(txtMunicipioPro.getText().trim())) {
				txtMunicipioPro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtMunicipioPro.setStyle("-fx-background-color: red");
			}
			if (i == 0) {
				String nombre = txtNombrePro.getText().trim();
				String aPaterno = txtAPaternoPro.getText().trim();
				String aMaterno = txtAMaternoPro.getText().trim();
				int calle = Integer.parseInt(txtCallePro.getText());
				String empresa = txtEmpresaPro.getText().trim();
				int avenida = Integer.parseInt(txtAvenidaPro.getText());
				int numero = Integer.parseInt(txtNumeroPro.getText());
				String colonia = txtColoniaPro.getText().trim();
				String municipio = txtMunicipioPro.getText();
				String telefono = txtTelefonoPro.getText().trim();
				ProveedorVO proveedorVO = new ProveedorVO(0, nombre, aPaterno,
						aMaterno, empresa, calle, avenida, numero, colonia,
						municipio, telefono);
				if (this.proveedorVO == null) {
					if (proveedorDAO.registrar(proveedorVO)) {
						alert(AlertType.INFORMATION, "Proveedor registrado.");
						proveedores.add(proveedorDAO.getLast());
						limpiarPro(null);
						disableFieldsProveedor();
					} else {
						alert(AlertType.ERROR, "Falló registro.");
					}
				} else {
					proveedorVO.setId(this.proveedorVO.getId());
					if (proveedorDAO.modificar(proveedorVO)) {
						alert(AlertType.INFORMATION, "Proveedor actualizado.");
						proveedores.removeAll(proveedores);
						fillTableProveedor();
						disableFieldsProveedor();
						limpiarPro(null);
					} else {
						alert(AlertType.ERROR, "Falló actualización.");
					}
				}
			} else {
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
		if (proveedorVO != null) {
			enableFieldsProveedor();
			btnModificarPro.setDisable(true);
		} else {
			alert(AlertType.ERROR, "No hay proveedor seleccionado.");
		}

	}

	public void eliminarPro(ActionEvent event) {
		if (proveedorVO != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmación");
			// alert.setHeaderText("Look, a Confirmation Dialog");
			alert.setContentText("¿Desea Eliminar Este Proveedor? ");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				if(proveedorDAO.eliminar(proveedorVO.getId())){
					//proveedores.removeAll(proveedores);
					//fillTableProveedor();
					proveedores.remove(proveedorVO);
					delProveedores.add(proveedorVO);
					disableFieldsProveedor();
				}
				else{
					alert(AlertType.ERROR, "Proveedor no eliminado.");
				}
				
			}
		} else {
			alert(AlertType.ERROR, "No hay proveedor seleccionado.");
		}

	}/*
	 * metodo para mostrar ventana categoria
	 */

	public void mostrarCategoria(ActionEvent event) {
		main1.showCategoria();
		rbtnCategoria.setSelected(false);
		categorias.removeAll(categorias);
		fillCategoria();
	}

	/*
	 * metodo para mostrar ventana marca
	 */
	public void mostrarMarca(ActionEvent event) {
		main1.showMarca();
		rbtnMarca.setSelected(false);
		marcas.removeAll(marcas);
		fillMarca();

	}

	public void guardarCli(ActionEvent event) {
		if (txtNombreCli.getText().isEmpty()
				|| txtAMaternoCli.getText().isEmpty()
				|| txtReferenciaCli.getText().isEmpty()
				|| txtCalleCli.getText().isEmpty()
				|| txtAvenidaCli.getText().isEmpty()
				|| txtNumeroCli.getText().isEmpty()
				|| txtColoniaCli.getText().isEmpty()
				|| txtMunicipioCli.getText().isEmpty()) {
			alert(AlertType.ERROR, "Completa los campos.");
		} else {
			int i = 0;
			if (validar.nombres(txtNombreCli.getText().trim())) {
				txtNombreCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtNombreCli.setStyle("-fx-background-color: red");
			}
			if (validar.nombres(txtAMaternoCli.getText().trim())) {
				txtAMaternoCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtAMaternoCli.setStyle("-fx-background-color: red");
			}
			if (validar.nombres(txtAPaternoCli.getText().trim())) {
				txtAPaternoCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtAPaternoCli.setStyle("-fx-background-color: red");
			}
			if (validar.cadena(txtReferenciaCli.getText().trim())) {
				txtReferenciaCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtReferenciaCli.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtCalleCli.getText().trim())) {
				txtCalleCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtCalleCli.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtAvenidaCli.getText().trim())) {
				txtAvenidaCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtAvenidaCli.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtNumeroCli.getText().trim())) {
				txtNumeroCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtNumeroCli.setStyle("-fx-background-color: red");
			}
			if (validar.cadena(txtColoniaCli.getText().trim())) {
				txtColoniaCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtColoniaCli.setStyle("-fx-background-color: red");
			}
			if (validar.cadena(txtMunicipioCli.getText().trim())) {
				txtMunicipioCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtMunicipioCli.setStyle("-fx-background-color: red");
			}
			if (i == 0) {
				String nombre = txtNombreCli.getText().trim();
				String aPaterno = txtAPaternoCli.getText().trim();
				String aMaterno = txtAMaternoCli.getText().trim();
				int calle = Integer.parseInt(txtCalleCli.getText());
				int avenida = Integer.parseInt(txtAvenidaCli.getText());
				int numero = Integer.parseInt(txtNumeroCli.getText());
				String colonia = txtColoniaCli.getText().trim();
				String municipio = txtMunicipioCli.getText();
				String referencia = txtReferenciaCli.getText().trim();
				ClienteVO clienteVO = new ClienteVO(0, nombre, aPaterno,
						aMaterno, calle, avenida, numero, colonia, municipio,
						referencia);
				if (this.clienteVO == null) {
					if (clienteDAO.registrar(clienteVO)) {
						alert(AlertType.INFORMATION, "Cliente registrado.");
						clientes.add(clienteDAO.lastInsert());
						limpiarCli(null);
						disableFieldsCliente();
					} else {
						alert(AlertType.ERROR, "Falló registro.");
					}
				} else {
					clienteVO.setId(this.clienteVO.getId());
					if (clienteDAO.modificar(clienteVO)) {
						alert(AlertType.INFORMATION, "Cliente Actualizado.");
						clientes.removeAll(clientes);
						fillTableCliente();
					} else {
						alert(AlertType.ERROR, "Falló actualización.");
					}
				}
			} else {
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
		btnEliminarCli.setDisable(true);
		enableFieldsCliente();
	}

	public void modificarCli(ActionEvent event) {
		if (clienteVO != null) {
			enableFieldsCliente();
			;
			btnModificarCli.setDisable(true);
		} else {
			alert(AlertType.ERROR, "No hay cliente seleccionado.");
		}

	}

	public void eliminarCli(ActionEvent event) {
		if (!(clienteVO == null)) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmación");
			// alert.setHeaderText("Look, a Confirmation Dialog");
			alert.setContentText("¿Desea eliminar este cliente? ");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				if (clienteDAO.eliminar(clienteVO.getId())) {
					//clientes.removeAll(clientes);
					//fillTableCliente();
					clientes.remove(clienteVO);
					delClientes.add(clienteVO);
					limpiarCli(null);
					disableFieldsCliente();
				} else {
					alert(AlertType.ERROR,
							"No se elimino el registro seleccionado.");
				}
			}
		} else {
			alert(AlertType.ERROR, "No hay cliente seleccionado.");
		}
	}

	public void actualizarRecepcionCom(ActionEvent event) {
		// checar 3er parcial
		if(compraVO != null){
			if(compraDAO.actualizar(compraVO.getId())){
				alert(AlertType.INFORMATION, "Fecha de compra actualizada.");
				compras.removeAll(compras);
				compraVO = null;
				fillTableCompra();
			}
			else{
				alert(AlertType.ERROR, "Fallo actualización.");
			}
		}
		else{
			alert(AlertType.ERROR, "Seleccione una compra.");
		}
	}

	public void cambiarContrasena(ActionEvent event) {
		if (pfContrasenaP.getText().equals("")
				&& pfNewContrasenaP.getText().equals("")
				&& pfConfContrasenaP.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Completa los campos.");
		} else {
			int i = 0;
			if (validar.contrasena(pfContrasenaP.getText())) {
				pfConfContrasenaP.setStyle("-fx-background-color: white");
			} else {
				i++;
				pfConfContrasenaP.setStyle("-fx-background-color: red");
			}
			if (validar.contrasena(pfNewContrasenaP.getText())) {
				pfNewContrasenaP.setStyle("-fx-background-color: white");
			} else {
				i++;
				pfNewContrasenaP.setStyle("-fx-background-color: red");
			}
			if (validar.contrasena(pfConfContrasenaP.getText())) {
				pfConfContrasenaP.setStyle("-fx-background-color: true");
			} else {
				i++;
				pfConfContrasenaP.setStyle("-fx-background-color: red");
			}
			if (i == 0) {
				AdminDAO adminDAO = new AdminDAO();
				String pass = encrypt.encryptText(pfContrasenaP.getText());
				if (adminDAO.user(pass)) {
					if (pfNewContrasenaP.getText().equals(
							pfConfContrasenaP.getText())) {
						if (adminDAO.actualizar("", pfNewContrasenaP.getText())) {
							JOptionPane.showMessageDialog(null,
									"Informacion Actualizada.");

							pfContrasenaP.setText(null);
							pfNewContrasenaP.setText(null);
							pfConfContrasenaP.setText(null);
						} else {
							JOptionPane.showMessageDialog(null,
									"Falló Actualización.");
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Las contraseñas no coinciden.");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Contraseña Actual Invalida.");
				}
			} else {
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
	 */
	public void alert(AlertType tipo, String mensaje) {
		Alert alert = new Alert(tipo);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	/*
	 * Metodo para llenar ComboBox categoria
	 */
	public void fillCategoria() {
		categorias = FXCollections.observableArrayList(catVO.listarCategoria(true));
		cbCategoria.setItems(categorias);
	}

	/*
	 * Metodo para llenar ComboBox marca
	 */
	public void fillMarca() {
		marcas = FXCollections.observableArrayList(marVO.listarMarca(true));
		cbMarca.setItems(marcas);
	}

	/*
	 * Metodo para inicilaizar tabla prodcutos
	 */
	public void tableProducto() {
		tcProductoProd
				.setCellValueFactory(new PropertyValueFactory<ProductoVO, String>(
						"nombreProducto"));
		tcMarcaProd
				.setCellValueFactory(new PropertyValueFactory<ProductoVO, String>(
						"marca"));
		tcPrecio1Prod
				.setCellValueFactory(new PropertyValueFactory<ProductoVO, Double>(
						"precio1"));
		tcPrecio2Prod
				.setCellValueFactory(new PropertyValueFactory<ProductoVO, Double>(
						"precio2"));
		tcStockProd
				.setCellValueFactory(new PropertyValueFactory<ProductoVO, Integer>(
						"stock"));
		tcTipoProd
				.setCellValueFactory(new PropertyValueFactory<ProductoVO, String>(
						"tipo"));
	}

	/*
	 * Metodo para llenar tabla Producto
	 */
	public void fillTableProducto() {
		productos = FXCollections.observableArrayList(productoDAO.getDatos(true));
		delProductos = FXCollections.observableArrayList(productoDAO.getDatos(false));
		// tvProductos.getItems().addAll(productos);
		tvProductos.setItems(productos);
		tvListProducts.setItems(productos);
		registros(lblProductos, productos.size());
		registros(lblProductos1, productos.size());
	}

	/*
	 * 
	 * Metodo para obtener los datos del producto seccionado en la tabla
	 * producto
	 */
	public void selectedTableProducto() {
		if (!(tvProductos.getSelectionModel().getSelectedItem() == null)) {
			disableFieldsProducto();
			productoVO = tvProductos.getSelectionModel().getSelectedItem();
			cbCategoria.getSelectionModel().select(productoVO.getCategoria());
			txtDescripcionProd.setText(productoVO.getDescripcion());
			cbMarca.getSelectionModel().select(productoVO.getMarca());
			String precio1 = String.valueOf(productoVO.getPrecio1());
			txtPrecio1Prod.setText(precio1);
			String precio2 = String.valueOf(productoVO.getPrecio2());
			txtPrecio2Prod.setText(precio2);
			// String stock = String.valueOf(productoVO.getStock());
			// txtStockProd.setText(stock);
			String stockMax = String.valueOf(productoVO.getStockMax());
			txtStockMaxProd.setText(stockMax);
			String stockMin = String.valueOf(productoVO.getStockMin());
			txtStockMinProd.setText(stockMin);
			cbTipo.getSelectionModel().select(productoVO.getTipo());
			btnModificar.setDisable(false);
			btnEliminar.setDisable(false);			
			txtDescripcionProd.setStyle("-fx-background-color: white");
			txtPrecio1Prod.setStyle("-fx-background-color: white");
			txtPrecio2Prod.setStyle("-fx-background-color: white");
			txtStockMaxProd.setStyle("-fx-background-color: white");
			txtStockMinProd.setStyle("-fx-background-color: white");
		
		}

	}

	/*
	 * Metodo para declarar atributos de la tabla empleado
	 */
	public void tableEmpleado() {
		tcEmpleadoEmp
				.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>(
						"nombreEmpleado"));
		tcMunicipioEmp
				.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>(
						"municipio"));
		tcTelefonoEmp
				.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>(
						"telefono"));
		tcUsuarioEmp
				.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>(
						"usuario"));
	}

	/*
	 * Metodo para llenar tabla empleado
	 */
	public void fillTableEmpleado() {
		empleados = FXCollections.observableArrayList(empleadoDAO.getDatos(true));
		delEmpleados = FXCollections.observableArrayList(empleadoDAO.getDatos(false));
		// tvEmpleados.getItems().addAll(empleados);
		tvEmpleados.setItems(empleados);
		registros(lblEmpleados, empleados.size());
	}

	/*
	 * Metodo para obtener el objeo selecioado de la tabla empleado
	 */
	public void selectedTableEmpleado() {
		if (tvEmpleados.getSelectionModel().getSelectedItem() != null) {
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
			txtPasswordConfEmp.setText(empleadoVO.getPassword());
			btnModificarEmp.setDisable(false);
			btnEliminarEmp.setDisable(false);

			txtNombreEmp.setStyle("-fx-background-color: white");
			txtAMaternoEmp.setStyle("-fx-background-color: white");
			txtAPaternoEmp.setStyle("-fx-background-color: white");
			txtTelefonoEmp.setStyle("-fx-background-color: white");
			txtUsuarioEmp.setStyle("-fx-background-color: white");
			txtPasswordEmp.setStyle("-fx-background-color: white");
			txtCalleEmp.setStyle("-fx-background-color: white");
			txtAvenidaEmp.setStyle("-fx-background-color: white");
			txtNumeroEmp.setStyle("-fx-background-color: white");
			txtColoniaEmp.setStyle("-fx-background-color: white");
			txtMunicipioEmp.setStyle("-fx-background-color: white");
		}
	}

	/*
	 * Metodo para declarar atributos de la tabla proveedor
	 */
	public void tableProveedor() {
		tcProveedorPro
				.setCellValueFactory(new PropertyValueFactory<ProveedorVO, String>(
						"nombreProveedor"));
		tcEmpresaPro
				.setCellValueFactory(new PropertyValueFactory<ProveedorVO, String>(
						"empresa"));
		tcMunicipioPro
				.setCellValueFactory(new PropertyValueFactory<ProveedorVO, String>(
						"municipio"));
		tcTelefonoPro
				.setCellValueFactory(new PropertyValueFactory<ProveedorVO, String>(
						"telefono"));

	}

	/*
	 * Metodo para llenar tabla proveedor
	 */
	public void fillTableProveedor() {
		proveedores = FXCollections
				.observableArrayList(proveedorDAO.getDatos(true));
		delProveedores = FXCollections.observableArrayList(proveedorDAO.getDatos(false));
		// tvProveedor.getItems().addAll(proveedores);
		tvProveedor.setItems(proveedores);
		registros(lblProveedores, proveedores.size());
	}

	/*
	 * Metodo para obtener el objeo selecioado de la tabla proveedor
	 */
	public void selectedTableProveedor() {
		if (tvProveedor.getSelectionModel().getSelectedItem() != null) {
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
			txtNombrePro.setStyle("-fx-background-color: white");
			txtAMaternoPro.setStyle("-fx-background-color: white");
			txtAPaternoPro.setStyle("-fx-background-color: white");
			txtTelefonoPro.setStyle("-fx-background-color: white");
			txtEmpresaPro.setStyle("-fx-background-color: white");
			txtCallePro.setStyle("-fx-background-color: white");
			txtAvenidaPro.setStyle("-fx-background-color: white");
			txtNumeroPro.setStyle("-fx-background-color: white");
			txtColoniaPro.setStyle("-fx-background-color: white");
			txtMunicipioPro.setStyle("-fx-background-color: white");
		}
	}

	/*
	 * Metodo para declarar atributos de la tabla cliente
	 */
	public void tableCliente() {
		tcClienteCli
				.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>(
						"nombreCliente"));
		tcMunicipioCli
				.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>(
						"municipio"));
		tcReferenciaCli
				.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>(
						"referencia"));
	}

	/*
	 * Metodo para llenar tabla cliente
	 */
	public void fillTableStock() {
		stock = FXCollections.observableArrayList(productoDAO.getStock());
		// tvCliente.getItems().addAll(clientes);
		//tvNuevoStock.setItems(stock);
		//registros(lblTStock, stock.size());
	}
	
	/*
	 * Metodo para declarar atributos de la tabla cliente
	 */
	public void tableStock() {
		tcCausaStock
				.setCellValueFactory(new PropertyValueFactory<StockVO, String>(
						"causa"));
		tcNuevoStock
				.setCellValueFactory(new PropertyValueFactory<StockVO, Integer>(
						"stock"));
		tcFechaHoraStock
				.setCellValueFactory(new PropertyValueFactory<StockVO, Timestamp>("fechaHora"));
	}
	
	public void selectedStock(){
    		historialStock.setPredicate(StockVO -> StockVO
					.getIdProducto() ==  productoVO.getId());
			tvNuevoStock.setItems(historialStock);
			registros(lblTStock, historialStock.size());
	}

	/*
	 * Metodo para llenar tabla cliente
	 */
	public void fillTableCliente() {
		clientes = FXCollections.observableArrayList(clienteDAO.getDatos(true));
		delClientes = FXCollections.observableArrayList(clienteDAO.getDatos(false));
		// tvCliente.getItems().addAll(clientes);
		tvCliente.setItems(clientes);
		registros(lblClientes, clientes.size());
	}

	/*
	 * Metodo para obtener el objeo selecioado de la tabla cliente
	 */
	public void selectedTableCliente() {
		if (tvCliente.getSelectionModel().getSelectedItem() != null) {
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
			btnEliminarCli.setDisable(false);
			txtNombreCli.setStyle("-fx-background-color: white");
			txtAMaternoCli.setStyle("-fx-background-color: white");
			txtAPaternoCli.setStyle("-fx-background-color: white");
			txtReferenciaCli.setStyle("-fx-background-color: white");
			txtCalleCli.setStyle("-fx-background-color: white");
			txtAvenidaCli.setStyle("-fx-background-color: white");
			txtNumeroCli.setStyle("-fx-background-color: white");
			txtColoniaCli.setStyle("-fx-background-color: white");
			txtMunicipioCli.setStyle("-fx-background-color: white");
		}
	}

	/*
	 * Metodo para declarar atributos de la tabla compra
	 */
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
    	tvCompra.setItems(compras);
    	registros(lblTCompras, compras.size());
    }
    /*
     * Metodo para obtener el objeto selecioado de la tabla compra
     * */
    public void selectedTableCompra(){
    	if(tvCompra.getSelectionModel().getSelectedItem() != null){
    		compraVO = tvCompra.getSelectionModel().getSelectedItem();
    		detalleCompraFiltro.setPredicate(DetalleCompraVO -> DetalleCompraVO
					.getId() == compraVO.getId());
			tvDetalleCompra.setItems(detalleCompraFiltro);
			registros(lblTDetalleCompras, detalleCompraFiltro.size());
    		
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
    	//tvDetalleCompra.getItems().addAll(detalleCompras);
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
    	registros(lblTVentas, ventas.size());
    }
    /*
     * Metodo para obtener el objeto selecioado de la tabla venta
     * */
    public void selectedTableVenta(){
    	if(tvVenta.getSelectionModel().getSelectedItem() != null){
    		ventaVO = tvVenta.getSelectionModel().getSelectedItem();
    		detalleVentaFiltro.setPredicate(DetalleVentaVO -> DetalleVentaVO
					.getId() ==  ventaVO.getId());
			tvDetalleVenta.setItems(detalleVentaFiltro);
			registros(lblTDetalleVentas, detalleVentaFiltro.size());
    		
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
    	//tvDetalleVenta.getItems().addAll(detalleVentas);
    }
	/*
	 * Metodo para desabilitar campos de producto
	 */
	public void disableFieldsProducto() {
		cbCategoria.setDisable(true);
		txtDescripcionProd.setDisable(true);
		cbMarca.setDisable(true);
		txtPrecio1Prod.setDisable(true);
		txtPrecio2Prod.setDisable(true);
		// txtStockProd.setDisable(true);
		txtStockMaxProd.setDisable(true);
		txtStockMinProd.setDisable(true);
		cbTipo.setDisable(true);
		btnGuardarProd.setDisable(true);
	}

	/*
	 * Metodo para habilitar campos de producto
	 */
	public void enableFieldsProducto() {
		cbCategoria.setDisable(false);
		txtDescripcionProd.setDisable(false);
		cbMarca.setDisable(false);
		txtPrecio1Prod.setDisable(false);
		txtPrecio2Prod.setDisable(false);
		// txtStockProd.setDisable(false);
		txtStockMaxProd.setDisable(false);
		txtStockMinProd.setDisable(false);
		cbTipo.setDisable(false);
		btnGuardarProd.setDisable(false);
	}

	public void registros(Label lab, int i) {
		if (i > 1) {
			lab.setText(i + " registros encontrados.");
		} else {
			if (i == 1) {
				lab.setText("1 registro encontrado.");
			} else {
				lab.setText("Ningun registro encontrado.");
			}
		}
	}

	/*
	 * 
	 * Metodo para desabilitar campos de empleado
	 */
	public void disableFieldsEmpleado() {
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
		txtPasswordConfEmp.setDisable(true);
		btnGuardarEmp.setDisable(true);
		// btnLimpiarEmp.setDisable(true);
	}

	/*
	 * 
	 * Metodo para habilitar campos de empleado
	 */
	public void enableFieldsEmpleado() {
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
		txtPasswordConfEmp.setDisable(false);
		btnGuardarEmp.setDisable(false);
		// btnLimpiarEmp.setDefaultButton(false);
	}

	/*
	 * 
	 * Metodo para desabilitar campos de proveedor
	 */
	public void disableFieldsProveedor() {
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
		// btnLimpiarPro.setDisable(true);
	}

	/*
	 * 
	 * Metodo para habilitar campos de proveedor
	 */
	public void enableFieldsProveedor() {
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
		// btnLimpiarPro.setDisable(false);
	}

	/*
	 * 
	 * Metodo para desabilitar campos de cliente
	 */
	public void disableFieldsCliente() {
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
		btnEliminarCli.setDisable(true);
		btnModificarCli.setDisable(true);
		// btnLimpiarCli.setDisable(true);
	}

	/*
	 * 
	 * Metodo para habilitar campos de cliente
	 */
	public void enableFieldsCliente() {
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
		// btnLimpiarCli.setDisable(false);
	}

	/*
	 * metodo para respaldar la base de datos
	 */
	public void respaldar(ActionEvent event) {
		/*FileChooser f = new FileChooser();
		ExtensionFilter per = new ExtensionFilter("Perzan Backup (*.per)", "*.per");
		f.getExtensionFilters().add(per);
		f.showSaveDialog(null);*/
		if (baseDatos.respaldo()) {
			alert(AlertType.INFORMATION, "Respaldo creado.");
		} else {
			alert(AlertType.ERROR, "Respaldo no creado.");
		}
	}

	/*
	 * metodo para restaurar la base de datos
	 */
	public void restaurar(ActionEvent event) {
		ExtensionFilter per = new ExtensionFilter("Perzan Backup (*.per)", "*.per");
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Buscar Respaldo");
		chooser.getExtensionFilters().add(per);
		chooser.setInitialDirectory(
	            new File("basedatos\\.."));
		chooser.setInitialFileName("perzan");

		File f = chooser.showOpenDialog(main1.getPrimaryStage());
		System.out.println(f);
		if (f != null) {
			f.getPath();
			if(baseDatos.restaurar(f)){
				alert(AlertType.INFORMATION, "Base de datos restaurada.");
			}
			else{
				alert(AlertType.ERROR, "No se restauro la base de datos.");
			}
		}
	}
	public void crearCSV(ActionEvent event){
		FileChooser f = new FileChooser();
		ExtensionFilter cs = new ExtensionFilter("CSV File (*.csv)", "*.csv");
		f.getExtensionFilters().add(cs);
		f.setInitialDirectory(
	            new File("CSVFiles\\.."));
		if(cbCSV.getValue() != null){
			File file = f.showSaveDialog(main1.getPrimaryStage());
			System.out.println(file.getAbsolutePath());
			String csvNombre = (String) cbCSV.getValue();
			switch (csvNombre){
			case "Producto":
				if(csv.writeCsvProducto(file, productos)) {
					alert(AlertType.INFORMATION, "Reporte creado.");
				} else {
					alert(AlertType.ERROR, "Falló creación de reporte.");
				}
				break;
			case "Empleado":
				if(csv.writeCsvEmpleado(file, empleados)) {
					alert(AlertType.INFORMATION, "Reporte creado.");
				} else {
					alert(AlertType.ERROR, "Falló creación de reporte.");
				}
				break;
			case "Cliente":
				if(csv.writeCsvCliente(file, clientes)) {
					alert(AlertType.INFORMATION, "Reporte creado.");
				} else {
					alert(AlertType.ERROR, "Falló creación de reporte.");
				}
				break;
			case "Proveedor":
				if(csv.writeCsvProveedor(file, proveedores)) {
					alert(AlertType.INFORMATION, "Reporte creado.");
				} else {
					alert(AlertType.ERROR, "Falló creación de reporte.");
				}
				break;
			case "Compra":
				comprasCSV = FXCollections.observableArrayList(compraDAO.getDatosDetalle());
				if(csv.writeCsvCompra(file, comprasCSV)) {
					alert(AlertType.INFORMATION, "Reporte creado.");
				} else {
					alert(AlertType.ERROR, "Falló creación de reporte.");
				}
				break;
			case "Venta":
				ventasCSV = FXCollections.observableArrayList(ventaDAO.getDatosDetalle());
				if(csv.writeCsvVenta(file, ventasCSV)) {
					alert(AlertType.INFORMATION, "Reporte creado.");
				} else {
					alert(AlertType.ERROR, "Falló creación de reporte.");
				}
				break;
			default:
				alert(AlertType.ERROR, "Falló creación de reporte.");
				break;
			}
			cbCSV.setValue(null);
		}
		
		else{
			alert(AlertType.ERROR, "Seleccione un valor.");
		}
	}
	public void leerCSV(ActionEvent event){
		ExtensionFilter cs = new ExtensionFilter("CSV File (*.csv)", "*.csv");
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Buscar CSV File");
		chooser.getExtensionFilters().add(cs);
		chooser.setInitialDirectory(
	            new File("CSVFiles\\.."));
		//chooser.setInitialFileName("perzan");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			csv.setAdmin(this);
			alert(AlertType.INFORMATION, csv.readCSVFile(f));
		}
	}
	
	public void crearReporte(ActionEvent event){
		if(cbReportes.getValue() != null){
			String rep = cbReportes.getSelectionModel().getSelectedItem();
			switch (rep) {
			case "Cliente":
				main1.showReporteActivo("src\\view\\reporte\\cliente.jrxml", rep);
				break;
			case "Compra":
				main1.showReporteProceso("src\\view\\reporte\\compra.jrxml", rep);
				break;
			case "Empleado":
				main1.showReporteActivo("src\\view\\reporte\\empleado.jrxml", rep);
				break;
			case "Producto":
				main1.showReporteActivo("src\\view\\reporte\\producto.jrxml", rep);
				break;
			case "Proveedor":
				main1.showReporteActivo("src\\view\\reporte\\proveedor.jrxml", rep);
				break;
			case "Venta":
				main1.showReporteProceso("src\\view\\reporte\\ventas.jrxml", rep);
				break;
			default:
				alert(AlertType.ERROR, "Falló creación de reporte.");
				break;
			}
			cbReportes.setValue(null);
		}
		else{
			alert(AlertType.ERROR, "Seleccione una categoria.");
		}
	}
	
	public void delProd(ActionEvent event){
		if(ckbDelProd.isSelected()){
			btnGuardarProd.setVisible(false);
			btnEliminar.setVisible(false);
			btnModificar.setVisible(false);
			btnLimpiarProd.setVisible(false);
			btnResProd.setVisible(true);
			tvProductos.setItems(delProductos);
			registros(lblProductos, delProductos.size());
		}
		else{
			btnGuardarProd.setVisible(true);
			btnEliminar.setVisible(true);
			btnModificar.setVisible(true);
			btnLimpiarProd.setVisible(true);
			btnResProd.setVisible(false);
			tvProductos.setItems(productos);
			registros(lblProductos, productos.size());
			registros(lblProductos1, productos.size());
		}
	}
	public void resProd(ActionEvent event){
		if(productoVO != null){
			if(productoDAO.modificarEliminado(productoVO.getId())){
				alert(AlertType.INFORMATION, "Registro restaurado.");
				delProductos.remove(productoVO);
				productos.add(productoVO);
				productoVO = null;
				registros(lblProductos, delProductos.size());
				registros(lblProductos1, productos.size());
				
			}
			else{
				alert(AlertType.ERROR, "Falló registro.");
			}
		}
		
	}
	
	public void delPro(ActionEvent event){
		if(ckbDelPro.isSelected()){
			btnGuardarPro.setVisible(false);
			btnEliminarPro.setVisible(false);
			btnModificarPro.setVisible(false);
			btnLimpiarPro.setVisible(false);
			btnResPro.setVisible(true);
			tvProveedor.setItems(delProveedores);
			registros(lblProveedores, delProveedores.size());
		}
		else{
			btnGuardarPro.setVisible(true);
			btnEliminarPro.setVisible(true);
			btnModificarPro.setVisible(true);
			btnLimpiarPro.setVisible(true);
			btnResPro.setVisible(false);
			tvProveedor.setItems(proveedores);
			registros(lblProveedores, proveedores.size());
		}
	}
	public void resPro(ActionEvent event){
		if(proveedorVO != null){
			if(proveedorDAO.modificarEliminado(proveedorVO.getId())){
				alert(AlertType.INFORMATION, "Registro restaurado.");
				delProveedores.remove(proveedorVO);
				proveedores.add(proveedorVO);
				proveedorVO = null;
				registros(lblProveedores, delProveedores.size());
				
			}
			else{
				alert(AlertType.ERROR, "Falló registro.");
			}
		}
		
	}
	public void delCli(ActionEvent event){
		if(ckbDelCli.isSelected()){
			btnGuardarCli.setVisible(false);
			btnEliminarCli.setVisible(false);
			btnModificarCli.setVisible(false);
			btnLimpiarCli.setVisible(false);
			btnResCli.setVisible(true);
			tvCliente.setItems(delClientes);
			registros(lblClientes, delClientes.size());
		}
		else{
			btnGuardarCli.setVisible(true);
			btnEliminarCli.setVisible(true);
			btnModificarCli.setVisible(true);
			btnLimpiarCli.setVisible(true);
			btnResCli.setVisible(false);
			tvCliente.setItems(clientes);
			registros(lblClientes, clientes.size());
		}
	}
	public void resCli(ActionEvent event){
		if(clienteVO != null){
			if(clienteDAO.modificarEliminado(clienteVO.getId())){
				alert(AlertType.INFORMATION, "Registro restaurado.");
				delClientes.remove(clienteVO);
				clientes.add(clienteVO);
				clienteVO = null;
				registros(lblClientes, delClientes.size());
				
			}
			else{
				alert(AlertType.ERROR, "Falló registro.");
			}
		}
		
	}
	public void delEmp(ActionEvent event){
		if(ckbDelEmp.isSelected()){
			btnGuardarEmp.setVisible(false);
			btnEliminarEmp.setVisible(false);
			btnModificarEmp.setVisible(false);
			btnLimpiarEmp.setVisible(false);
			btnResEmp.setVisible(true);
			tvEmpleados.setItems(delEmpleados);
			registros(lblEmpleados, delEmpleados.size());
		}
		else{
			btnGuardarEmp.setVisible(true);
			btnEliminarEmp.setVisible(true);
			btnModificarEmp.setVisible(true);
			btnLimpiarEmp.setVisible(true);
			btnResEmp.setVisible(false);
			tvEmpleados.setItems(empleados);
			registros(lblEmpleados, empleados.size());
		}
	}
	public void resEmp(ActionEvent event){
		if(empleadoVO != null){
			if(empleadoDAO.modificarEliminado(empleadoVO.getId())){
				alert(AlertType.INFORMATION, "Registro restaurado.");
				delEmpleados.remove(empleadoVO);
				empleados.add(empleadoVO);
				empleadoVO = null;
				registros(lblEmpleados, delEmpleados.size());
				
			}
			else{
				alert(AlertType.ERROR, "Falló registro.");
			}
		}
		
	}
public void showLog(ActionEvent event){
	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("Logs");
	alert.setHeaderText("Bitacora de errores.");

	File f = new File("Logs\\log.txt");
	try {
		FileWriter fw = new FileWriter(f,true);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		log.printLog(e1.getMessage(), this.getClass().toString());
	}
	int n = 0;
	String t = "";
	String message = "";
	FileReader fr;
	try {
		fr = new FileReader(f);
	
	BufferedReader br = new BufferedReader(fr);
	while(n == 0){
		t = br.readLine();
		if(t != null){
			message += t + "\n";
		}
		else{
			n++;
		}
	}
	br.close();
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		log.printLog(e.getMessage(), this.getClass().toString());
	}
	// Create expandable Exception.

	TextArea textArea = new TextArea(message);
	textArea.setEditable(false);
	textArea.setWrapText(true);

	textArea.setMaxWidth(Double.MAX_VALUE);
	textArea.setMaxHeight(Double.MAX_VALUE);
	GridPane.setVgrow(textArea, Priority.ALWAYS);
	GridPane.setHgrow(textArea, Priority.ALWAYS);

	GridPane expContent = new GridPane();
	expContent.setMaxWidth(Double.MAX_VALUE);
	expContent.add(textArea, 0, 0);

	// Set expandable Exception into the dialog pane.
	alert.getDialogPane().setExpandableContent(expContent);

	alert.showAndWait();

}

	/*
	 * /*
	 * 
	 * Metodo para
	 */
	public void setMain1(view.Main1 main1) {
		this.main1 = main1;
	}
}
