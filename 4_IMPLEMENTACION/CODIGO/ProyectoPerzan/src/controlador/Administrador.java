package controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import modelo.EmpleadoDAO;
import modelo.EmpleadoVO;
import modelo.ProductoDAO;
import modelo.ProductoVO;
import modelo.UsuarioDAO;
import modelo.VentaDAO;
import modelo.VentaVO;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;

public class Administrador implements Initializable{
	
	private application.Main1 main1;
	Validar validar = new Validar();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.inicializarTabla();
		final ObservableList<ProductoVO> tablaPersonaSeleccionada = tvProductos.getSelectionModel().getSelectedItems();
		tablaPersonaSeleccionada.addListener(indiceTabla);		
		//empleado
		this.inicializarTablaEmp();
		final ObservableList<EmpleadoVO> tablaEmpleadoSeleccionada = tvEmpleados.getSelectionModel().getSelectedItems();
		tablaEmpleadoSeleccionada.addListener(indiceTablaEmp);	
		//ventas
		this.inicializarTablaVen();
		final ObservableList<VentaVO> tablaVentaSeleccionada = tvVentas.getSelectionModel().getSelectedItems();
		tablaVentaSeleccionada.addListener(indiceTablaVen);
	}

	@FXML
	private TextField txtNombreRegistrar;
	@FXML
	private TextField txtPrecioRegistrar;
	@FXML
	private TextField txtCantidadRegistrar;
	@FXML
	private TextField txtBuscar;
	@FXML
	private Button btnGuardarRegistrar;
	@FXML
	private Button btnLimpiarRegistrar;
	@FXML
	private Button btnModificar;
	@FXML
	private Button btnEliminar;
	@FXML
	private Button btnBuscar;
	@FXML
	private Button btnProductos;
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
	private TextField txtNombreRegEmp;
	@FXML
	private TextField txtAPaternoRegEmp;
	@FXML
	private TextField txtAMaternoRegEmp;
	@FXML
	private TextField txtDireccionRegEmp;
	@FXML
	private TextField txtTelefonoRegEmp;
	@FXML
	private TextField txtUsuarioRegEmp;
	@FXML
	private TextField txtPasswordRegEmp;
	@FXML
	private Button btnGuardarRegEmp;
	@FXML
	private Button btnLimpiarRegEmp;
	@FXML
	private Button btnModificarEmp;
	@FXML
	private Button btnEliminarEmp;
	@FXML
	private Button btnCerrarSesion;
	@FXML
	private TableView<EmpleadoVO> tvEmpleados;
	@FXML
	private TableColumn<EmpleadoVO, String> tcIdEmp;
	@FXML
	private TableColumn<EmpleadoVO, String> tcNombreEmp;
	@FXML
	private TableColumn<EmpleadoVO, String> tcAPaternoEmp;
	@FXML
	private TableColumn<EmpleadoVO, String> tcAMaternoEmp;
	@FXML
	private TableColumn<EmpleadoVO, String> tcDireccionEmp;
	@FXML
	private TableColumn<EmpleadoVO, String> tcTelefonoEmp;
	@FXML
	private TableColumn<EmpleadoVO, String> tcUsuarioEmp;
	@FXML
	private TableColumn<EmpleadoVO, String> tcContrasenaEmp;
	@FXML
	private TableView<VentaVO> tvVentas;
	@FXML
	private TableColumn<VentaVO, String> tcIdVen;
	@FXML
	private TableColumn<VentaVO, String> tcClienteVen;
	@FXML
	private TableColumn<VentaVO, String> tcImporteVen;
	@FXML
	private TableColumn<VentaVO, String> tcVendedorVen;
	@FXML
	private TableColumn<VentaVO, String> tcFechaVen;
    @FXML
    private Tooltip ttPrecioProd;
    @FXML
    private Tooltip ttNombreProd;
    @FXML
    private Tooltip ttCantidadProd;
    @FXML
    private Tooltip ttNombreEmp;
    @FXML
    private Tooltip ttPassword;
    @FXML
    private Tooltip ttTelefono;
    @FXML
    private Tooltip ttApMaterno;
    @FXML
    private Tooltip ttApPaterno;
    @FXML
    private Tooltip ttUsuario;
    @FXML
    private Tooltip ttDireccion;
    @FXML
    private TextField txtUsuarioP;
    @FXML
    private PasswordField pfContrasenaP;
    @FXML
    private PasswordField pfNewContrasenaP;
    @FXML
    private PasswordField pfConfContrasenaP;
    @FXML
    private Tooltip ttUsuarioP;
    @FXML
    private Tooltip ttContrasenaP;
    @FXML
    private Tooltip ttNewContrasenaP;
    @FXML
    private Tooltip ttConfContrasenaP;
    @FXML
    private Button btnAceptarP;
    @FXML
    private Button btnCancelarP;
    
	ObservableList<ProductoVO> productos;
	ObservableList<EmpleadoVO> empleados;
	ObservableList<VentaVO>	ventas;
	
	private int posicion;
	
	public void guardarRegistrar(ActionEvent event){
		if(txtNombreRegistrar.getText().equals("")|| txtPrecioRegistrar.getText().equals("")|| txtCantidadRegistrar.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Completa todos los campos");
		}
		else{
			int i = 0;
			if(validar.cadena(txtNombreRegistrar.getText())){ttNombreProd.setText(null);}else{i++; ttNombreProd.setText("Campo Erroneo");}
			if(validar.entero(txtCantidadRegistrar.getText())){ttCantidadProd.setText(null);}else{i++; ttCantidadProd.setText("Campo Erroneo Ej: \"10\"");}
			if(validar.precio(txtPrecioRegistrar.getText())){ttPrecioProd.setText(null);}else{i++; ttPrecioProd.setText("Campo Erroneo Ej: \"10.00\"");}
			if(i == 0){
				if(!(txtPrecioRegistrar.getText().equals("0")) && !(txtCantidadRegistrar.getText().equals("0"))){
					double precio= Double.valueOf(txtPrecioRegistrar.getText());
					int cantidad=Integer.parseInt(txtCantidadRegistrar.getText());
					ProductoVO productoVO = new ProductoVO(0 , txtNombreRegistrar.getText(), precio, cantidad);
					ProductoDAO productoDAO = new ProductoDAO();
					if(productoDAO.registrar(productoVO)){
						JOptionPane.showMessageDialog(null, "Producto Registrado");
						txtNombreRegistrar.setText(null);
						txtPrecioRegistrar.setText(null);
						txtCantidadRegistrar.setText(null);
						productos.add(productoDAO.lastInsert());
					}
					else{
						JOptionPane.showMessageDialog(null, "Fallo Registro");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Precio y Cantidad deben ser diferentes de 0 ");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Verifica Los Campos");
			}
		}
	}
	public void limpiarRegistrar(ActionEvent event){
		txtNombreRegistrar.setText(null);
		txtPrecioRegistrar.setText(null);
		txtCantidadRegistrar.setText(null);
	}
	public void modificar(ActionEvent event){
		 final ProductoVO prod = getTabla();
	        posicion = productos.indexOf(prod);

	        if (prod != null) {
	        	main1.showModProducto(prod);
	        	productos.removeAll(productos);
	        	productoTable();
	        }
	}
	public void eliminar(ActionEvent event){
		final ProductoVO prod = getTabla();
        posicion = productos.indexOf(prod);

        if (prod != null) {
        	int o = JOptionPane.showConfirmDialog(null, "Confirmar eliminacion.");
        	if(o == 0){
        		ProductoDAO productoDAO = new ProductoDAO();
        		productoDAO.eliminar(prod.getId());
        		productos.remove(posicion);
        	}
        }
	}
	public void buscar(ActionEvent event){
		Validar validar = new Validar();
		ProductoDAO productoDAO = new ProductoDAO();
		int id = 0;
		double precio = 0;
		int i = 0;
		if(!(txtBuscar.getText().equals(""))){
			if(validar.cadena(txtBuscar.getText())){
				i++;
			}
			else{
				JOptionPane.showMessageDialog(null, "Precio debe ser:\n Ejemplo \"1.0 o 1.00\"");
			}
		}
		if(i != 0){
			ObservableList<ProductoVO> productosGet = productoDAO.getBuscado(id,txtBuscar.getText() , precio);
			if(productoDAO.got()){
				productos.removeAll(productos);
				productos.addAll(productosGet);
				txtBuscar.setText("");
				}
			else{
				JOptionPane.showMessageDialog(null, "Producto no encontrado.");
				txtBuscar.setText("");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Escribe el nombre del producto.");
		}
	}
	public void mostrarProductos(ActionEvent event){
		productos.removeAll(productos);
		productoTable();
	}
			
	public void productoTable(){
		ProductoDAO productoDAO =new ProductoDAO();
		ProductoVO[] products = productoDAO.getDatos();
		int t = products.length;
		int p = 0;
		if(t > 0){
			while(t > 0){
				ProductoVO productVO = products[p];
				productos.add(productVO);
				t--;
				p++;
			}
		}
	}
	public void empleadoTable(){
		EmpleadoDAO empleadoDAO =new EmpleadoDAO();
		EmpleadoVO[] empleado = empleadoDAO.getDatos();
		int t = empleado.length;
		int p = 0;
		if(t > 0){
			while(t > 0){
				EmpleadoVO empleadoVO = empleado[p];
				empleados.add(empleadoVO);
				t--;
				p++;
			}
		}
	}
	public void ventaTable(){
		VentaDAO ventaDAO =new VentaDAO();
		VentaVO[] venta = ventaDAO.getDatos();
		int t = venta.length;
		int p = 0;
		if(t > 0){
			while(t > 0){
				VentaVO ventaVO = venta[p];
				ventas.add(ventaVO);
				t--;
				p++;
			}
		}
	}
	//metodo externo
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
	        	/*String id = String.valueOf(prod.getId());
	        	String precio = String.valueOf(prod.getPrecio());
	        	String cantidad = String.valueOf(prod.getCantidad());
	        	txtIdModificar.setText(id);
	        	txtNombreModificar.setText(prod.getNombre());	
	        	txtPrecioModificar.setText(precio);
	        	txtCantidadModificar.setText(cantidad);*/
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
		tvProductos.setEditable(true);
		productoTable();
	
	 }
	 //Terminan metodos
	//metodo externo empleado
	private final ListChangeListener<EmpleadoVO> indiceTablaEmp =
		new ListChangeListener<EmpleadoVO>() {
		@Override
		public void onChanged(ListChangeListener.Change<? extends EmpleadoVO> c) {
			getEmpleado();
		}
	};
	private void getEmpleado() {
		final EmpleadoVO emp = getTablaEmp();
		posicion = empleados.indexOf(emp);
		if (emp != null) {
		}	
	}
	public EmpleadoVO getTablaEmp() {
		if (tvEmpleados != null) {
			List<EmpleadoVO> tabla = tvEmpleados.getSelectionModel().getSelectedItems();
			if (tabla.size() == 1) {
				final EmpleadoVO seleccionada = tabla.get(0);
				return seleccionada;
	  		}
		}
		return null;
	}
	private void inicializarTablaEmp(){
			tcIdEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("id"));
			tcNombreEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("nombre"));
			tcAPaternoEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("apPaterno"));
			tcAMaternoEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("apMaterno"));
			tcDireccionEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("direccion"));
			tcTelefonoEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("telefono"));
			tcUsuarioEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("usuario"));
			tcContrasenaEmp.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("password"));
			
			empleados = FXCollections.observableArrayList();
			tvEmpleados.setItems(empleados);
			
			empleadoTable();
		 }
		 //Terminan metodos empleado
	//metodo externo ventas
	private final ListChangeListener<VentaVO> indiceTablaVen =
		new ListChangeListener<VentaVO>() {
		@Override
		public void onChanged(ListChangeListener.Change<? extends VentaVO> c) {
			getVenta();
		}
	};
	private void getVenta() {
		final VentaVO ven = getTablaVen();
		posicion = ventas.indexOf(ven);
			if (ven != null) {
			}	
		}
		public VentaVO getTablaVen() {
			if (tvVentas != null) {
				List<VentaVO> tabla = tvVentas.getSelectionModel().getSelectedItems();
				if (tabla.size() == 1) {
					final VentaVO seleccionada = tabla.get(0);
					return seleccionada;
		  		}
			}
			return null;
		}
		private void inicializarTablaVen(){
				tcIdVen.setCellValueFactory(new PropertyValueFactory<VentaVO, String>("id"));
				tcClienteVen.setCellValueFactory(new PropertyValueFactory<VentaVO, String>("cliente"));
				tcImporteVen.setCellValueFactory(new PropertyValueFactory<VentaVO, String>("importe"));
				tcVendedorVen.setCellValueFactory(new PropertyValueFactory<VentaVO, String>("vendedor"));
				tcFechaVen.setCellValueFactory(new PropertyValueFactory<VentaVO, String>("fecha"));
				
				ventas = FXCollections.observableArrayList();
				tvVentas.setItems(ventas);
				
				ventaTable();
			 }
			 //Terminan metodos empleado
	public void limpiarEliminar(ActionEvent event){
		
	}
	
	public void guardarRegEmp(ActionEvent event){
		if(txtNombreRegEmp.getText().equals("")||txtAPaternoRegEmp.getText().equals("") || txtAMaternoRegEmp.getText().equals("")|| txtDireccionRegEmp.getText().equals("")|| txtTelefonoRegEmp.getText().equals("")||txtUsuarioRegEmp.getText().equals("")||txtPasswordRegEmp.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Rellena Todos Los Campos");		
		}
		else{
			int i = 0;
			if(validar.nombres(txtNombreRegEmp.getText())){ttNombreEmp.setText(null);}else{i++; ttNombreEmp.setText("Campo Erroneo Ej: \"Nombre\"");}
			if(validar.nombres(txtAPaternoRegEmp.getText())){ttApPaterno.setText(null);}else{i++; ttApPaterno.setText("Campo Erroneo Ej: \"Paterno\"");}
			if(validar.nombres(txtAMaternoRegEmp.getText())){ttApMaterno.setText(null);}else{i++; ttApMaterno.setText("Campo Erroneo Ej: \"Materno\"");}
			if(validar.direccion(txtDireccionRegEmp.getText())){ttDireccion.setText(null);}else{i++; ttDireccion.setText("Campo Erroneo");}
			if(validar.telefono(txtTelefonoRegEmp.getText())){ttTelefono.setText(null);}else{i++; ttTelefono.setText("Campo Erroneo Ej: \"555-555-55-55\"");}
			if(validar.usuario(txtUsuarioRegEmp.getText())){ttUsuario.setText(null);}else{i++; ttUsuario.setText("Campo Erroneo Ej: \"usuario10\"");}
			if(validar.contrasena(txtPasswordRegEmp.getText())){ttPassword.setText(null);}else{i++; ttPassword.setText("Campo Erroneo Ej: \"Usuario10\"");}
			if(i == 0){
				EmpleadoVO empleadoVO = new EmpleadoVO(0, txtNombreRegEmp.getText(), txtAPaternoRegEmp.getText(), txtAMaternoRegEmp.getText(), txtDireccionRegEmp.getText(), txtTelefonoRegEmp.getText(), txtUsuarioRegEmp.getText(), txtPasswordRegEmp.getText());
				EmpleadoDAO empleadoDAO = new EmpleadoDAO();
				if(empleadoDAO.registrar(empleadoVO)){
					JOptionPane.showMessageDialog(null, "Empleado Registrado!");
					txtNombreRegEmp.setText(null);
					txtAPaternoRegEmp.setText(null);
					txtAMaternoRegEmp.setText(null);
					txtDireccionRegEmp.setText(null);
					txtTelefonoRegEmp.setText(null);
					txtUsuarioRegEmp.setText(null);
					txtPasswordRegEmp.setText(null);
					empleados.add(empleadoDAO.lastInsert());
					
				}
				else{
					JOptionPane.showMessageDialog(null, "Fallo Registro!");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Verifica Los Campos");
			}
		}
	}
	public void limpiarRegEmp(ActionEvent event){
		txtNombreRegEmp.setText(null);
		txtAPaternoRegEmp.setText(null);
		txtAMaternoRegEmp.setText(null);
		txtDireccionRegEmp.setText(null);
		txtTelefonoRegEmp.setText(null);
		txtUsuarioRegEmp.setText(null);
		txtPasswordRegEmp.setText(null);
	}
	public void modificarEmp(ActionEvent event){
		 final EmpleadoVO emp = getTablaEmp();
	        posicion = empleados.indexOf(emp);

	        if (emp != null) {
	        	main1.showModEmpleado(emp);
	        	empleados.removeAll(empleados);
	        	empleadoTable();
	        }
	}
	public void eliminarModEmp(ActionEvent event){
		final EmpleadoVO emp = getTablaEmp();
        posicion = empleados.indexOf(emp);

        if (emp != null) {
        	int o = JOptionPane.showConfirmDialog(null, "Confirmar eliminacion.");
        	if(o == 0){
	        	EmpleadoDAO empleadoDAO = new EmpleadoDAO();
	        	empleadoDAO.eliminar(emp.getId());
	        	empleados.remove(posicion);
        	}
        }
	}
	public void limpiarEliModEmp(ActionEvent event){
	
	}
	public void cambiarContrasena(ActionEvent event){
		if(pfContrasenaP.getText().equals("") && pfNewContrasenaP.getText().equals("") && pfConfContrasenaP.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Completa los campos.");
		}
		else{
			int i = 0;
			if(!(txtUsuarioP.getText().equals(""))){
				if(validar.usuario(txtUsuarioP.getText())){ttUsuarioP.setText(null);}else{i++; ttUsuarioP.setText("Campo Erroneo Ej: \"user1234\"");}
			}
			if(validar.contrasena(pfContrasenaP.getText())){ttContrasenaP.setText(null);}else{i++; ttContrasenaP.setText("Campo Erroneo Ej: \"User4321\"");}
			if(validar.contrasena(pfNewContrasenaP.getText())){ttNewContrasenaP.setText(null);}else{i++; ttNewContrasenaP.setText("Campo Erroneo Ej: \"User4321\"");}
			if(validar.contrasena(pfConfContrasenaP.getText())){ttConfContrasenaP.setText(null);}else{i++; ttConfContrasenaP.setText("Campo Erroneo Ej: \"User4321\"");}
			if(i == 0){
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				if(usuarioDAO.user(pfContrasenaP.getText()).equals(pfContrasenaP.getText())){
					if(pfNewContrasenaP.getText().equals(pfConfContrasenaP.getText())){
						if(usuarioDAO.actualizar(txtUsuarioP.getText(),pfNewContrasenaP.getText())){
							JOptionPane.showMessageDialog(null, "Informacion Actualizada.");
							txtUsuarioP.setText(null);
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
	public void cancelarP(ActionEvent event){
		txtUsuarioP.setText(null);
		pfContrasenaP.setText(null);
		pfNewContrasenaP.setText(null);
		pfConfContrasenaP.setText(null);
	}
	public void cerrarSesion(ActionEvent event){
		Principal.loginAdm = false;
		main1.showLoginAdm();
	}
	public void setMain1(application.Main1 main1) {
		this.main1 = main1;
	}
}