package controlador;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import modelo.ProductoDAO;
import modelo.ProductoVO;
import modelo.VentaDAO;
import modelo.VentaDetVO;
import modelo.VentaVO;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;

public class Empleado implements Initializable{
	private String usuario;
	private double total;
	static VentaDetVO ventaDetVO;
	static boolean actualizado = false;
	public Empleado(){
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.inicializarTabla();
		final ObservableList<ProductoVO> tablaPersonaSeleccionada = tvProductos.getSelectionModel().getSelectedItems();
		tablaPersonaSeleccionada.addListener(indiceTabla);	
		//tabla ventas
		this.inicializarTablaVen();
		final ObservableList<VentaDetVO> tablaVentaSeleccionada = tvVentas.getSelectionModel().getSelectedItems();
		tablaVentaSeleccionada.addListener(indiceTablaVen);	
		
	}
	
	private application.Main1 main1;
	@FXML
	private TextField txtIdVentas;
	@FXML
	private TextField txtClienteVentas;
	@FXML
	private TextField txtCantidadVentas;
	@FXML
	private TextField txtIdB;
	@FXML
	private TextField txtNombreB;
	@FXML
	private TextField txtPrecioB;
	@FXML
	private Tooltip ttId;
	@FXML
	private Tooltip ttCantidad;
	@FXML
	private Tooltip ttCliente;
	@FXML
	private TableView<VentaDetVO> tvVentas;
	@FXML
	private TableColumn<VentaDetVO, String> tcIdVen;
	@FXML
	private TableColumn<VentaDetVO, String> tcNombreVen;
	@FXML
	private TableColumn<VentaDetVO, String> tcPrecioVen;
	@FXML
	private TableColumn<VentaDetVO, String> tcCantidadVen;
	@FXML
	private TableColumn<VentaDetVO, String> tcImporteVen;
	@FXML
	private Button btnInsertar;
	@FXML
	private Button btnVenta;
	@FXML
	private Button btnCancelar;
	@FXML
	private Button btnModItem;
	@FXML
	private Button btnDelItem;
	@FXML
	private Button btnBuscar;
	@FXML
	private Button btnAgregar;
	@FXML
	private Button btnMostrarT;
	@FXML
	private Label lblTotal;
	@FXML
	private Label lblEmpleado;
	@FXML
	private TableView<ProductoVO> tvProductos;
	@FXML
	private TableColumn<ProductoVO, String> tcIdProd;
	@FXML
	private TableColumn<ProductoVO, String> tcNombreProd;
	@FXML
	private TableColumn<ProductoVO, String> tcPrecioProd;
	@FXML
	private TableColumn<ProductoVO, String> tcCantidadProd;
	@FXML
	private Button btnCerrarSesion;
	ObservableList<ProductoVO> productos;
	ObservableList<VentaDetVO> ventas;
	private int posicion;
	
	public void insertar(ActionEvent event){
		if(txtIdVentas.getText().equals("") || txtCantidadVentas.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Completa los datos");
		}
		else{
			if(txtCantidadVentas.getText().equals("0")){
				JOptionPane.showMessageDialog(null, "Error \"0\" no valido");
			}
			else{
				boolean reg = false;
				Validar validar = new Validar();
				int id = 0;
				if(validar.entero(txtIdVentas.getText())){
					System.out.println("ëntrar");
					int tam = ventas.size();
					int a = 0;
					id= Integer.parseInt(txtIdVentas.getText());
					while(a<tam && !(reg)){
						VentaDetVO venta = ventas.get(a);
						a++;
						System.out.println(a);
						if(venta.getId()==id){
							reg = true;
						}
					}
				}
				else{
					ttId.setText("Campo Erroneo Ej: \"1\"");
					}
				if(!(reg)){
					System.out.println("ëntrar1");
					int i = 0;
					if(validar.entero(txtIdVentas.getText())){ttId.setText(null);}else{ i++; ttId.setText("Campo Erroneo Ej: \"1\"");}
					if(validar.entero(txtCantidadVentas.getText())){ttCantidad.setText(null);}else{ i++; ttCantidad.setText("Campo Erroneo Ej: \"1\"");}
					if(i == 0){
						System.out.println("ëntrar2");
						ProductoDAO productoDAO= new ProductoDAO();
						ProductoVO productoVO = productoDAO.getProducto(id);
						if(productoDAO.got()){
							System.out.println("ëntrar3");
							int cant = productoVO.getCantidad();
							int cantidad = Integer.parseInt(txtCantidadVentas.getText());
							if((cant-cantidad) >= 0 ){
								System.out.println("ëntrar4");
								//productoVO.setCantidad(cant - cantidad);
								//productosVenta.add(productoVO);
								double importe = productoVO.getPrecio()*cantidad;
								//total=total+importe;
								VentaDetVO ventaDetVO = new VentaDetVO(productoVO.getId(), productoVO.getNombre(), productoVO.getPrecio(), cantidad, importe);
								ventas.add(ventaDetVO);
								totales();
								String tot = String.valueOf(total);
								lblTotal.setText(tot);
								txtIdVentas.setText(null);
								txtCantidadVentas.setText(null);
							}
							else{
								JOptionPane.showMessageDialog(null, "No hay producto suficiente");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Id No Encontrado");
							txtIdVentas.setText(null);
							txtCantidadVentas.setText(null);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Los campos se llenan con datos de tipo entero.");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Id repetido! \n Seleccione el producto en la tabla y\n presione el boton \"Mod Item\"");
					txtIdVentas.setText(null);
					txtCantidadVentas.setText(null);
				}
			}
		}
	}
	public void venta(ActionEvent event){
		if(txtClienteVentas.getText().equals("") || lblTotal.getText().equals(0)){
			JOptionPane.showMessageDialog(null, "Agrega Productos y nombre de Cliente.");
		}
		else{
			int i = 0;
			Validar validar = new Validar();
			if(validar.nombres(txtClienteVentas.getText())){ttCantidad.setText(null);}else{i++; ttCantidad.setText("Campo Erroneo Ej: \"Nombre Cliente\"");}
			if(i == 0){
				Calendar date = Calendar.getInstance();
				Date time = date.getTime();
				String fecha = String.valueOf(time);
				VentaVO ventaVO = new VentaVO(0, txtClienteVentas.getText(), total, lblEmpleado.getText(),fecha);
				VentaDAO ventaDAO = new VentaDAO();
				//int o = productosVenta.size();
				int o = ventas.size();
				//o--;
				System.out.println("size" + o);
				ProductoDAO productoDAO = new ProductoDAO();
				int error = 0;
				for(int a = 0;a < o; a++){
					//if(!(productoDAO.modificar(productosVenta.get(a)))){
					VentaDetVO venta = ventas.get(a);
					int id = venta.getId();
					int cantidad = venta.getCantidad();
					if(!(productoDAO.venta(id, cantidad))){
						JOptionPane.showMessageDialog(null, "Error de Actualización");
						error++;
					}
				}
				if(error == 0){
					if(ventaDAO.registrar(ventaVO)){
						JOptionPane.showMessageDialog(null, "Venta Registrada");
						total = 0;
						lblTotal.setText(null);
						txtClienteVentas.setText(null);
						ventas.removeAll(ventas);
						productos.removeAll(productos);
						listaProductos();
					}
					else{
						if(txtClienteVentas.getText().equals(null)){
							JOptionPane.showMessageDialog(null, "Escribe el nombre del cliente.");
						}
						else{
							JOptionPane.showMessageDialog(null, "Venta No Realizada");
						}	
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Verifica Los Campos");
			}
		}
	}
	public void modificarItem(ActionEvent event){
		final VentaDetVO ven = getTablaVen();
		posicion = ventas.indexOf(ven);
		
		if (ven != null) {
		 	main1.showModVenta(ven);
		 	if(actualizado){
		 		
		 		/*total=total-ven.getImporte();
		 		total=total+ventaDetVO.getImporte();*/
		 		ventas.add(posicion, ventaDetVO);
		 		ventas.remove(posicion);
		 		totales();
		 		String tot = String.valueOf(total);
				lblTotal.setText(tot);
		 		System.out.println(posicion);
			 	actualizado = false;
		 	}
		}
	}
	public void eliminarItem(ActionEvent event){
		final VentaDetVO ven = getTablaVen();
		posicion = ventas.indexOf(ven);
		
		if (ven != null) {
			int o = JOptionPane.showConfirmDialog(null, "Confirmar eliminacion.");
        	if(o == 0){
	        	ventas.remove(posicion);
	        	totales();
        	}
		}
	}
	public void cancelar(ActionEvent event){
		txtIdVentas.setText(null);
		txtCantidadVentas.setText(null);
		lblTotal.setText("0");
		total = 0;
		productos.removeAll(productos);
		
	}
	public void cerrarSesion(ActionEvent event){
		Principal.loginEmp = false;
		main1.showLoginEmp();
	}
	public void setMain1(application.Main1 main1) {
		this.main1 = main1;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
		lblEmpleado.setText(usuario);
	}
	public void listaProductos(){
		ProductoDAO productoDAO =new ProductoDAO();
		 ProductoVO[] products = productoDAO.getDatos();
		 int t = products.length;
		 int p = 0;
		 while(t > 0){
			 ProductoVO productVO = products[p];
			 productos.add(productVO);
			 t--;
			 p++;
		 }
	}
	public void buscar(ActionEvent event){
		Validar validar = new Validar();
		ProductoDAO productoDAO = new ProductoDAO();
		int id = 0;
		double precio = 0;
		int i = 0;
		if(!(txtIdB.getText().equals(""))){
			if(validar.entero(txtIdB.getText())){
				id = Integer.parseInt(txtIdB.getText());
				i++;
			}
			else{
				JOptionPane.showMessageDialog(null, "Id debe ser un numero Entero");
			}
		}
		if(!(txtNombreB.getText().equals(""))){
			if(validar.cadena(txtNombreB.getText())){
				i++;
			}
			else{
				JOptionPane.showMessageDialog(null, "Nombre debe ser:\n Ejemplo \"Nombre\"");
			}
		}
		if(!(txtPrecioB.getText().equals(""))){
			if(validar.precio(txtPrecioB.getText())){
				precio = Double.valueOf(txtPrecioB.getText());
				i++;
			}
			else{
				JOptionPane.showMessageDialog(null, "Precio debe ser:\n Ejemplo \"1.0 o 1.00\"");
			}
		}
		if(i != 0){
			ObservableList<ProductoVO> productosGet = productoDAO.getBuscado(id,txtNombreB.getText() , precio);
			if(productoDAO.got()){
				productos.removeAll(productos);
				productos.addAll(productosGet);
				txtIdB.setText("");
				txtNombreB.setText("");
				txtPrecioB.setText("");
			}
			else{
				JOptionPane.showMessageDialog(null, "Producto no encontrado.");
				txtIdB.setText("");
				txtNombreB.setText("");
				txtPrecioB.setText("");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Al menos un campo debe contener\n un dato para buscar.");
		}
	}
	public void agregar(ActionEvent event){
		final ProductoVO prod = getTabla();
		posicion = productos.indexOf(prod);
		int tam = ventas.size();
		int a = 0;
		boolean reg = false;
		int id= prod.getId();
		while(a<tam && !(reg)){
			VentaDetVO venta = ventas.get(a);
			if(venta.getId()==id){
				reg = true;
			}
			a++;
		}
		if(!(reg)){
			if (prod != null) {	
				
				VentaDetVO ven = new VentaDetVO(prod.getId(),prod.getNombre(),prod.getPrecio(), 0, 0.0);
				main1.showModVenta(ven);
			 	if(actualizado){
			 		//total=total+ventaDetVO.getImporte();
			 		//System.out.println(ventaDetVO.getImporte());
			 		ventas.add(ventaDetVO);
			 		totales();
			 		String tot = String.valueOf(total);
					lblTotal.setText(tot);
				 	actualizado = false;
			 	}
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Id repetido! \n Seleccione el producto en la tabla y\n presione el boton \"Mod Item\"");
		}	
	}
	public void mostrar(ActionEvent event){
		productos.removeAll(productos);
		listaProductos();
	}
	public void totales(){
		int num = ventas.size();
		int i = 0;
		total = 0;
		while(i != num){
			VentaDetVO v =  ventas.get(i);
			total = total + v.getImporte();
			i++;
		}
		String tot = String.valueOf(total);
		lblTotal.setText(tot);
	}
	//metodo externo producto
	private final ListChangeListener<ProductoVO> indiceTabla =
		new ListChangeListener<ProductoVO>() {
		@Override
		public void onChanged(ListChangeListener.Change<? extends ProductoVO> c) {
			getAdministrador();
		}
	};
	private void getAdministrador() {
		final ProductoVO prod = getTabla();
		posicion = productos.indexOf(prod);
		
		if (prod != null) {	
		}	
	}
	public ProductoVO getTabla() {
		if (tvProductos != null) {
			List<ProductoVO> tabla = tvProductos.getSelectionModel().getSelectedItems();
			if (tabla.size() == 1) {
				final ProductoVO seleccionada = tabla.get(0);
				return seleccionada;
			}
		}
		return null;
	}
	 private void inicializarTabla(){
		 tcIdProd.setCellValueFactory(new PropertyValueFactory<ProductoVO, String>("id"));
		 tcNombreProd.setCellValueFactory(new PropertyValueFactory<ProductoVO, String>("nombre"));
		 tcPrecioProd.setCellValueFactory(new PropertyValueFactory<ProductoVO, String>("precio"));
		 tcCantidadProd.setCellValueFactory(new PropertyValueFactory<ProductoVO, String>("cantidad"));
		 
		 productos = FXCollections.observableArrayList();
		 tvProductos.setItems(productos);
			
		 listaProductos();	
	 }
	 //Terminan metodos producto
	//metodo externo ventas
		private final ListChangeListener<VentaDetVO> indiceTablaVen =
			new ListChangeListener<VentaDetVO>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends VentaDetVO> c) {
				getVenta();
			}
		};
		private void getVenta() {
			final VentaDetVO ven = getTablaVen();
			posicion = ventas.indexOf(ven);
			
			if (ven != null) {
			}
		}
		public VentaDetVO getTablaVen() {
			if (tvVentas != null) {
				List<VentaDetVO> tabla = tvVentas.getSelectionModel().getSelectedItems();
				if (tabla.size() == 1) {
					final VentaDetVO seleccionada = tabla.get(0);
					return seleccionada;
				}
			}
			return null;
		}
		 private void inicializarTablaVen(){
			 tcIdVen.setCellValueFactory(new PropertyValueFactory<VentaDetVO, String>("id"));
			 tcNombreVen.setCellValueFactory(new PropertyValueFactory<VentaDetVO, String>("nombre"));
			 tcPrecioVen.setCellValueFactory(new PropertyValueFactory<VentaDetVO, String>("precio"));
			 tcCantidadVen.setCellValueFactory(new PropertyValueFactory<VentaDetVO, String>("cantidad"));
			 tcImporteVen.setCellValueFactory(new PropertyValueFactory<VentaDetVO, String>("importe"));
			 
			 ventas = FXCollections.observableArrayList();
			 tvVentas.setItems(ventas);
				
		 }
		 //Terminan metodos ventas
}
