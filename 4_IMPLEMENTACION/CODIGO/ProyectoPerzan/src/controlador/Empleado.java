package controlador;


import java.net.URL;
import java.util.ResourceBundle;

import modelo.ClienteDAO;
import modelo.ClienteVO;
import modelo.CompraDAO;
import modelo.CompraVO;
import modelo.DetalleCompraVO;
import modelo.DetalleVentaVO;
import modelo.LoginEmpVO;
import modelo.ProductoDAO;
import modelo.ProductoVO;
import modelo.ProveedorDAO;
import modelo.ProveedorVO;
import modelo.VentaDAO;
import modelo.VentaVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Empleado implements Initializable {
	
	private view.Main1 main1;
	private ObservableList<ProductoVO> productos;
	private ObservableList<ClienteVO> clientes;
	private ObservableList<ProveedorVO> proveedores;
	private ObservableList<DetalleVentaVO> ventas;
	private ObservableList<DetalleCompraVO> compras;
	private FilteredList<ProductoVO> bcProductos;
	private FilteredList<ProductoVO> bvProductos;
	private FilteredList<ClienteVO> bClientes;
	private FilteredList<ProveedorVO> bProveedores; 
	private LoginEmpVO usuario;
	private ProductoDAO productoDAO;
	private ProductoVO productoVO;
	private ClienteDAO clienteDAO;
	private ClienteVO clienteVO;
	private ProveedorDAO proveedorDAO;
	private ProveedorVO proveedorVO;
	private DetalleCompraVO dCompraVO, dListCompraVO;
	private DetalleVentaVO dVentaVO, dListVentaVO;
	//private VentaDetVO ventadVO, ventaListVO;
	private Validar validar;
	private VentaVO ventaVO;
	private VentaDAO ventaDAO;
	private CompraVO compraVO;
	private CompraDAO compraDAO;
	private float total;
	
	public Empleado(){
		productos = FXCollections.observableArrayList();
		clientes = FXCollections.observableArrayList();
		proveedores = FXCollections.observableArrayList();
		ventas = FXCollections.observableArrayList();
		compras = FXCollections.observableArrayList();
		productoDAO = new ProductoDAO();
		productoVO = new ProductoVO();
		clienteVO = new ClienteVO();
		clienteDAO = new ClienteDAO();
		proveedorDAO = new ProveedorDAO();
		proveedorVO = new ProveedorVO();
		validar = new Validar();
		dCompraVO = new DetalleCompraVO();
		dVentaVO = new DetalleVentaVO();
		ventaDAO = new VentaDAO();
		compraDAO = new CompraDAO();
		total = 0f;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.fillClientes();
		this.fillProducto();
		this.fillProveedores();
		this.initTables();
		this.disableFields();
		bcProductos = new FilteredList<ProductoVO>(productos);
		bvProductos = new FilteredList<ProductoVO>(productos);
		bClientes = new FilteredList<ClienteVO>(clientes);
		bProveedores = new FilteredList<ProveedorVO>(proveedores);
		getTotalCom();
		getTotalVen();
		
	}	

    @FXML private TextField txtNombreProd;
    @FXML private TextField txtBuscarProdCom;
    @FXML private TextField txtBuscarProCom;
    @FXML private TextField txtPrecioProd;
    @FXML private TextField txtNewPrecioProdCom1;
    @FXML private TextField txtNewPrecioProdCom2;
    @FXML private TextField txtCantidadProd;
    @FXML private TextField txtBuscarClientesVen;
    @FXML private TextField txtCantidadProdCom;
    @FXML private TextField txtNombreProdCom;
    @FXML private TextField txtNombreCli;
    @FXML private TextField txtTotalProd;
    @FXML private TextField txtTotalCom;
    @FXML private TextField txtEmpresaPro;
    @FXML private TextField txtPrecioProdCom1;
    @FXML private TextField txtPrecioProdCom2;
    @FXML private TextField txtBuscarProductoVen;
    @FXML private TextField txtPrecioCompra;
    @FXML private TextField txtStock;
    @FXML private Button btnNuevoCli;
    @FXML private Button btnNuevoProdCom;
    @FXML private Button btnVenta;
    @FXML private Button btnCompra;
    @FXML private Button btnDelItemProd;
    @FXML private Button btnAgregarProd;
    @FXML private Button btnAgregarProdCom;
    @FXML private Button btnNuevoProCom;
    @FXML private Button btnCerrarSesion;
    @FXML private Button btnDelItemCom;
    @FXML private Button btnCancelarVenta;
    @FXML private Button btnCancelarCom;
    @FXML private TableView<DetalleCompraVO> tvCompras;
    @FXML private TableColumn<DetalleCompraVO, Float> tcPrecioCom;
    @FXML private TableColumn<DetalleCompraVO, Float> tcImporteCom;
    @FXML private TableColumn<DetalleCompraVO, String> tcNombreCom;
    @FXML private TableColumn<DetalleCompraVO, Integer> tcCantidadCom;
    @FXML private TableView<DetalleVentaVO> tvVentas;
    @FXML private TableColumn<DetalleVentaVO, Float> tcImporteVen;
    @FXML private TableColumn<DetalleVentaVO, String> tcNombreVen;
    @FXML private TableColumn<DetalleVentaVO, Integer> tcCantidadVen;
    @FXML private TableColumn<DetalleVentaVO, Float> tcPrecioVen;
    @FXML private ListView<ProductoVO> lvProductoCom;
    @FXML private ListView<ProveedorVO> lvProveedoresCom;
    @FXML private ListView<ProductoVO> lvProductosVen;
    @FXML private ListView<ClienteVO> lvClientesVen;
    @FXML private Label lblEmpleado;
    @FXML private Label lblCProductos;
    @FXML private Label lblVProductos;
    @FXML private Label lblClientes;
    @FXML private Label lblProveedores;
    @FXML private Label lblTCompras;
    @FXML private Label lblTVentas;
    @FXML private CheckBox cbDescuento;
    @FXML private Tab tVentas;
    @FXML private Tab tCompras;
    
    @FXML public void buscarProdVen() {
    	if (txtBuscarProductoVen.getText().isEmpty()) {
			lvProductosVen.setItems(productos);
			registros(lblVProductos, productos.size());
		} else {
			bvProductos.setPredicate(ProductoVO -> ProductoVO
					.getNombreProducto().toLowerCase()
					.contains(txtBuscarProductoVen.getText().trim().toLowerCase()));
			lvProductosVen.setItems(bvProductos);
			registros(lblVProductos, bvProductos.size());
		}
    }

    
    public void nuevoCliente(ActionEvent event) {
    	main1.nuevoCliente();
    	fillClientes();
    }

    
    public void agregarProd(ActionEvent event) {
    	if(txtCantidadProd.getText().isEmpty()){
    		alert(AlertType.ERROR, "Ingresa una cantidad.");
    	}
    	else{
    		if(validar.entero(txtCantidadProd.getText().trim()) && !txtCantidadProd.getText().equals("0")){
    			txtCantidadProd.setStyle("-fx-background-color: white");
    			dVentaVO = new DetalleVentaVO();
    			dVentaVO.setIdProducto(productoVO.getId());
    			dVentaVO.setNombre(productoVO.getNombreProducto());
    			dVentaVO.setPrecio(Float.valueOf(txtPrecioProd.getText().trim()));
    			dVentaVO.setCantidad(Integer.parseInt(txtCantidadProd.getText().trim()));
    			dVentaVO.setTotal(dVentaVO.getCantidad()*dVentaVO.getPrecio());
    			if(ventas.size() > 0){
    				boolean d = false;
    				for(int i = 0; i < ventas.size(); i++){
    					if(ventas.get(i).getId() == dVentaVO.getIdProducto()){
    						dListVentaVO = ventas.get(i);
    						dListVentaVO.setCantidad(dListVentaVO.getCantidad() + dVentaVO.getCantidad());
    						dListVentaVO.setTotal(dListVentaVO.getCantidad() * dVentaVO.getPrecio());
    						if(productoVO.getStock()-dListVentaVO.getCantidad() >= 0){
        						ventas.set(i, dListVentaVO);
    						}
    						else{
    							alert(AlertType.ERROR, "Producto insuficiente. \n Stock: "+productoVO.getStock());
    						}
    						d = true;
    						//break;
    					}
    				}
    				if(d == false){
    					if(productoVO.getStock()-dVentaVO.getCantidad() >= 0){
    						ventas.add(dVentaVO);
						}
						else{
							alert(AlertType.ERROR, "Producto insuficiente. \n Stock: "+productoVO.getStock());
						}
    				}  
					getTotalVen();
    			}
    			else{
    				if(productoVO.getStock()-dVentaVO.getCantidad() >= 0){
						ventas.add(dVentaVO);
					}
					else{
						alert(AlertType.ERROR, "Producto insuficiente. \n Stock: "+productoVO.getStock());
					}
    				getTotalVen();    				
    			}
    			registros(lblTVentas, ventas.size());				
				txtCantidadProd.setText("0");
    		}
    		else{
    			txtCantidadProd.setStyle("-fx-background-color: red");
    			alert(AlertType.ERROR, "El campo cantidad solo permite \n valores numericos.");
    		}	
    	}
    }

    
    @FXML public void buscarCliVen() {
    	if (txtBuscarClientesVen.getText().isEmpty()) {
			lvClientesVen.setItems(clientes);
			registros(lblClientes, clientes.size());
		} else {
			bClientes.setPredicate(ClienteVO -> ClienteVO
					.getNombreCliente().toLowerCase()
					.contains(txtBuscarClientesVen.getText().trim().toLowerCase()));
			lvClientesVen.setItems(bClientes);
			registros(lblClientes, bClientes.size());
		}
    }

    
    public void eliminarItemProd(ActionEvent event) {
    	if(tvVentas.getSelectionModel().getSelectedItem() != null){
    		ventas.remove(tvVentas.getSelectionModel().getSelectedItem());
    		getTotalVen();
    		registros(lblTVentas, ventas.size());
    	}
    	else{
    		alert(AlertType.ERROR, "No se ha seleccionado un registro.");
    	}
    }

    
    public void venta(ActionEvent event) {
    	int i = 0;
    	/*if(txtNombreCli.getText().isEmpty()){
    		i++; alert(AlertType.ERROR, "Seleciona un cliente.");
    	}*/
    	if(ventas.size() == 0){
    		i++; alert(AlertType.ERROR, "No se han agregado productos.");
    	}
    	if(i == 0){
    		ventaVO = new VentaVO();
    		ventaVO.setIdVendedor(usuario.getId());
    		if(txtNombreCli.getText().isEmpty()){
        		ventaVO.setIdCliente(1);    			
    		}
    		else{
        		ventaVO.setIdCliente(clienteVO.getId());    			
    		}
    		//ventaVO.setIdCliente(clienteVO.getId());
    		ventaVO.setTotal(total);
    		if(main1.showTotal(total)){
    			if(ventaDAO.registrar(ventaVO, ventas)){
        			alert(AlertType.INFORMATION, "Venta realizada.");
        			cancelarVenta();
        			registros(lblTVentas, ventas.size());
        			fillProducto();
        		}
        		else{
        			alert(AlertType.ERROR, "No se realizo la venta.");
        		}
    		}
    	}
    }

    
    @FXML public void cancelarVenta() {
    	productoVO = new ProductoVO();
    	clienteVO = new ClienteVO();
    	txtNombreCli.setText("");
    	txtNombreProd.setText("");
    	txtPrecioProd.setText("");
    	txtCantidadProd.setText("");
    	cbDescuento.setSelected(false);
    	ventas.removeAll(ventas);
    	getTotalVen();
    }

    
    @FXML public void buscarProdCom() {
    	if (txtBuscarProductoVen.getText().isEmpty()) {
			lvProductoCom.setItems(productos);
			registros(lblCProductos, productos.size());
		} else {
			bcProductos.setPredicate(ProductoVO -> ProductoVO
					.getNombreProducto().toLowerCase()
					.contains(txtBuscarProdCom.getText().trim().toLowerCase()));
			lvProductoCom.setItems(bcProductos);
			registros(lblCProductos, bcProductos.size());
		}
    }

    
    @FXML public void buscarProCom() {
    	if (txtBuscarProCom.getText().isEmpty()) {
			lvProveedoresCom.setItems(proveedores);
			registros(lblProveedores, proveedores.size());
		} else {
			bProveedores.setPredicate(ProveedorVO -> ProveedorVO
					.getNombreProveedor().toLowerCase()
					.contains(txtBuscarProCom.getText().trim().toLowerCase()));
			lvProveedoresCom.setItems(bProveedores);
			registros(lblProveedores, bProveedores.size());
		}
    }

    
    public void agregarProdCom(ActionEvent event) {
    	int e = 0;
    	if(txtCantidadProdCom.getText().isEmpty()||
    			txtPrecioProdCom1.getText().isEmpty()||
    			txtPrecioProdCom2.getText().isEmpty()||
    			txtPrecioCompra.getText().isEmpty()){
    		alert(AlertType.ERROR, "Completa los campos de producto.");
    	}
    	else{
    		if(Float.valueOf(txtNewPrecioProdCom1.getText().trim()) < Float.valueOf(txtNewPrecioProdCom2.getText().trim())){
    			alert(AlertType.ERROR, "Precio 1 debe ser mayor que precio 2.");
    			e++;
    		}
    		if(Float.valueOf(txtNewPrecioProdCom2.getText().trim()) < Float.valueOf(txtPrecioCompra.getText().trim())){
    			alert(AlertType.ERROR, "Precio 1 debe ser mayor que precio 2.");
    			e++;
    		}
    		if(validar.entero(txtCantidadProdCom.getText().trim()) && !txtCantidadProdCom.getText().equals("0")){
    			txtCantidadProdCom.setStyle("-fx-background-color: white");
    		}
    		else{
    			e++;
    			txtCantidadProdCom.setStyle("-fx-background-color: red");
    				}
    		if(validar.precio(txtNewPrecioProdCom1.getText().trim()) && !txtNewPrecioProdCom1.getText().equals("0")){
    			txtNewPrecioProdCom1.setStyle("-fx-background-color: white");
    		}
    		else{
    			e++;
    			txtNewPrecioProdCom1.setStyle("-fx-background-color: red");
    		}
    		if(validar.precio(txtNewPrecioProdCom2.getText().trim()) && !txtNewPrecioProdCom2.getText().equals("0")){
    			txtNewPrecioProdCom2.setStyle("-fx-background-color: white");
    		}
    		else{
    			e++;
    			txtNewPrecioProdCom2.setStyle("-fx-background-color: red");
    		}
    		if(validar.precio(txtPrecioCompra.getText().trim()) && !txtPrecioCompra.getText().equals("0")){
    			txtPrecioCompra.setStyle("-fx-background-color: white");
    		}
    		else{
    			e++;
    			txtPrecioCompra.setStyle("-fx-background-color: red");
    		}
    		if(e == 0){
    			dCompraVO = new DetalleCompraVO();
    			dCompraVO.setIdProducto(productoVO.getId());
    			dCompraVO.setNombre(productoVO.getNombreProducto());
    			dCompraVO.setPrecioCompra(Float.valueOf(txtPrecioCompra.getText().trim()));
    			dCompraVO.setCantidad(Integer.parseInt(txtCantidadProdCom.getText().trim()));
    			dCompraVO.setTotal(dCompraVO.getCantidad()*dCompraVO.getPrecioCompra());
    			dCompraVO.setPrecioVenta1(Float.valueOf(txtNewPrecioProdCom1.getText().trim()));
    			dCompraVO.setPrecioVenta2(Float.valueOf(txtNewPrecioProdCom2.getText().trim()));
    			if(compras.size() > 0){
    				boolean d = false;
    				for(int i = 0; i < compras.size(); i++){
    					if(compras.get(i).getId() == dCompraVO.getId()){
    						dListCompraVO = compras.get(i);
    						dListCompraVO.setCantidad(dCompraVO.getCantidad() + dListCompraVO.getCantidad());
    						dListCompraVO.setTotal(dListCompraVO.getCantidad() * dCompraVO.getPrecioCompra());
    						if(dListCompraVO.getCantidad()+productoVO.getStock() <= productoVO.getStockMax()){
        						compras.set(i, dListCompraVO);
    						}
    						else{
    							alert(AlertType.ERROR, "Stock maximo alcanzado \n limite: "+ productoVO.getStockMax());
    						}
    						txtCantidadProd.setText("0");
    						d = true;
    						//break;
    					}
    				}
    				if(d == false){
    					if(dCompraVO.getCantidad()+productoVO.getStock() <= productoVO.getStockMax()){
						compras.add(dCompraVO);
    					}
    					else{
    						alert(AlertType.ERROR,  "Stock maximo alcanzado \n limite: "+ productoVO.getStockMax());
    					}
    				}  
					getTotalCom();
    			}
    			else{
    				if(dCompraVO.getCantidad()+productoVO.getStock() <= productoVO.getStockMax()){
						compras.add(dCompraVO);
    					}
    				else{
    					alert(AlertType.ERROR,  "Stock maximo alcanzado \n limite: "+ productoVO.getStockMax());
    				}  				
    			}
    			registros(lblTCompras, compras.size());
    			getTotalCom();  
    		}
    		else{
    			alert(AlertType.ERROR, "Revisa los datos.");
    		}
    	}

    }

    
    public void nuevoProdCompra(ActionEvent event) {
    	main1.nuevoProducto();
    	fillProducto();
    }

    
    public void nuevoPro(ActionEvent event) {
    	main1.nuevoProveedor();
    	fillProveedores();
    }

    
    @FXML public void cancelarCom() {
    	txtEmpresaPro.setText("");
    	txtNombreProdCom.setText("");
    	txtPrecioProdCom1.setText("");
    	txtPrecioProdCom2.setText("");
    	txtCantidadProdCom.setText("");
    	txtNewPrecioProdCom1.setText("");
    	txtNewPrecioProdCom2.setText("");
    	txtPrecioCompra.setText("");
    	txtStock.setText("");
    	proveedorVO = new ProveedorVO();
    	productoVO = new ProductoVO();
    	compras.removeAll(compras);
    	getTotalCom();
    }

    
    public void compra(ActionEvent event) {
    	int i = 0;
    	if(txtEmpresaPro.getText().isEmpty()){
    		i++; alert(AlertType.ERROR, "Seleccione un proveedor.");
    	}
    	if(compras.size() == 0){
    		i++; alert(AlertType.ERROR, "No se han agregado productos.");
    	}
    	if(i == 0){
    		compraVO = new CompraVO();
    		compraVO.setIdEmpleado(usuario.getId());
    		compraVO.setIdProveedor(proveedorVO.getId());
    		compraVO.setTotal(total);
    		if(compraDAO.insertar(compraVO, compras)){
    			alert(AlertType.INFORMATION, "Compra realizada.");
    			cancelarCom();
    			registros(lblTCompras, compras.size());
    			fillProducto();
    		}
    		else{
    			alert(AlertType.ERROR, "Compra no realizada.");
    		}
    		
    	}
    }

    
    public void eliminarItemCom(ActionEvent event) {
    	if(tvCompras.getSelectionModel().getSelectedItem() != null){
    		compras.remove(tvCompras.getSelectionModel().getSelectedItem());
    		getTotalCom();
    		registros(lblTCompras, compras.size());
    	}
    }
    
    public void fillProducto(){
    	productos = FXCollections.observableArrayList(productoVO.getDatos());
    	lvProductoCom.setItems(productos);
    	lvProductosVen.setItems(productos);
    	registros(lblCProductos, productos.size());
    	registros(lblVProductos, productos.size());
    }
    public void fillClientes(){
    	clientes = FXCollections.observableArrayList(clienteVO.getDatos());
    	lvClientesVen.setItems(clientes);
    	registros(lblClientes, clientes.size());
    }
    public void fillProveedores(){
    	proveedores = FXCollections.observableArrayList(proveedorVO.getDatos());
    	lvProveedoresCom.setItems(proveedores);
    	registros(lblProveedores, proveedores.size());
    }
    
    public void initTables(){
    	tcCantidadCom.setCellValueFactory(new PropertyValueFactory<DetalleCompraVO, Integer>("cantidad"));
    	tcImporteCom.setCellValueFactory(new PropertyValueFactory<DetalleCompraVO, Float>("total"));
    	tcNombreCom.setCellValueFactory(new PropertyValueFactory<DetalleCompraVO, String>("nombre"));
    	tcPrecioCom.setCellValueFactory(new PropertyValueFactory<DetalleCompraVO, Float>("precioCompra"));
    	tcCantidadVen.setCellValueFactory(new PropertyValueFactory<DetalleVentaVO, Integer>("cantidad"));
    	tcImporteVen.setCellValueFactory(new PropertyValueFactory<DetalleVentaVO, Float>("total"));
    	tcNombreVen.setCellValueFactory(new PropertyValueFactory<DetalleVentaVO, String>("nombre"));
    	tcPrecioVen.setCellValueFactory(new PropertyValueFactory<DetalleVentaVO, Float>("precio"));
    	tvCompras.setItems(compras);
    	tvVentas.setItems(ventas);
    	registros(lblTCompras, compras.size());
    	registros(lblTVentas, ventas.size());
    }
    
    public void disableFields(){
    	txtNombreCli.setEditable(false);
    	txtNombreProd.setEditable(false);
    	txtPrecioProd.setEditable(false);
    	txtTotalProd.setEditable(false);
    	txtEmpresaPro.setEditable(false);
    	txtNombreProdCom.setEditable(false);
    	txtPrecioProdCom1.setEditable(false);
    	txtPrecioProdCom2.setEditable(false);
    	txtTotalCom.setEditable(false);
    	txtStock.setEditable(false);
    }
    
    @FXML public void selectedProdVen(){
    	if(lvProductosVen.getSelectionModel().getSelectedItem() != null){
    		productoVO = lvProductosVen.getSelectionModel().getSelectedItem();
    		txtNombreProd.setText(productoVO.getNombreProducto());
    		if(cbDescuento.isSelected()){
        		txtPrecioProd.setText(String.valueOf(productoVO.getPrecio2()));
        	}
        	else{
        		txtPrecioProd.setText(String.valueOf(productoVO.getPrecio1()));
        	}
    	}
    }
    
    @FXML public void descuento(ActionEvent event){
    	if(cbDescuento.isSelected()){
    		if(!txtPrecioProd.getText().isEmpty()){
        		txtPrecioProd.setText(String.valueOf(productoVO.getPrecio2()));
        	}
    	}
    	else{
    		if(!txtPrecioProd.getText().isEmpty()){
        		txtPrecioProd.setText(String.valueOf(productoVO.getPrecio1()));
        	}
    	}
    	
    }
    
    @FXML public void selectedCliVen(){
    	if(lvClientesVen.getSelectionModel().getSelectedItem() != null){
    		clienteVO = lvClientesVen.getSelectionModel().getSelectedItem();
    		txtNombreCli.setText(clienteVO.getNombreCliente());
    	}
    }
    
    @FXML public void selectedProCom(){
    	if(lvProveedoresCom.getSelectionModel().getSelectedItem() != null){
    		proveedorVO = lvProveedoresCom.getSelectionModel().getSelectedItem();
    		txtEmpresaPro.setText(proveedorVO.getEmpresa());
    	}
    }
    
    @FXML public void selectedProdCom(){
    	if(lvProductoCom.getSelectionModel().getSelectedItem() != null){
    		productoVO = lvProductoCom.getSelectionModel().getSelectedItem();
    		txtNombreProdCom.setText(productoVO.getNombreProducto());
    		txtPrecioProdCom1.setText(String.valueOf(productoVO.getPrecio1()));
    		txtPrecioProdCom2.setText(String.valueOf(productoVO.getPrecio2()));
    		txtStock.setText(String.valueOf(productoVO.getStock()));
    	}
    }
    
    public void getTotalVen(){
    	total = 0;
    	for(int i = 0; i < ventas.size(); i++){
    		total += ventas.get(i).getTotal();
    	}
    	txtTotalProd.setText(String.valueOf(total));
    }
    public void getTotalCom(){
    	total = 0;
    	for(int i = 0; i < compras.size(); i++){
    		total += compras.get(i).getTotal();
    	}
    	txtTotalCom.setText(String.valueOf(total));
    }
    
    public void registros(Label lab, int i) {
		if (i > 1) {
			lab.setText(i + " registros.");
		} else {
			if (i == 1) {
				lab.setText("1 registro.");
			} else {
				lab.setText("No hay registros.");
			}
		}
	}
    
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
	public void cerrarSesion(ActionEvent event){
		Principal.loginEmp = false;
		main1.showLoginEmp();
	}
	public LoginEmpVO getUsuario() {
		return usuario;
	}

	public void setUsuario(LoginEmpVO usuario) {
		this.usuario = usuario;
		lblEmpleado.setText(usuario.getNombre());
	}

	public void setMain1(view.Main1 main1) {
		this.main1 = main1;
	}

}
