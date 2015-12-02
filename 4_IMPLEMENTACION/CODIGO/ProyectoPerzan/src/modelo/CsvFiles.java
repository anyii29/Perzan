package modelo;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringBufferInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import controlador.Administrador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CsvFiles {
	private String comma = ",";
	private String line = "\n";
	private String header;
	private FileWriter fw;
	private ProductoVO productoVO;
	private ProductoDAO pDAO;
	private EmpleadoVO empleadoVO;
	private EmpleadoDAO eDAO;
	private ClienteVO clienteVO;
	private ClienteDAO cDAO;
	private ProveedorVO proveedorVO;
	private ProveedorDAO proDAO;
	private CompraVO compraVO;
	private VentaVO ventaVO;
	private Administrador admin;
	private ObservableList<ProductoVO> productos;
	private ObservableList<EmpleadoVO> empleados;
	private ObservableList<ClienteVO> clientes;
	private ObservableList<ProveedorVO> proveedores;
	private BufferedReader br;
	private Logger log;
	public CsvFiles(){
		productos = FXCollections.observableArrayList();
		empleados = FXCollections.observableArrayList();
		clientes = FXCollections.observableArrayList();
		proveedores = FXCollections.observableArrayList();
		pDAO = new ProductoDAO();
		eDAO = new EmpleadoDAO();
		cDAO = new ClienteDAO();
		proDAO = new ProveedorDAO();
		log = new Logger();
	}
	
	public boolean writeCsvProducto(File file, ObservableList<ProductoVO> productos){
		header = "IdCategoria,Categoria,Descripción,IdMarca,Marca,Precio1,Precio2,Stock,StockMax,StockMin,Tipo";
		try {
			fw = new FileWriter(file);
			fw.append("Productos");
			fw.append(line);
			fw.append(header);
			fw.append(line);
			for(ProductoVO productoVO : productos){
				fw.append(String.valueOf(productoVO.getCategoria().getId()));
				fw.append(comma);
				fw.append(productoVO.getCategoria().getNombre());
				fw.append(comma);
				fw.append(productoVO.getDescripcion());
				fw.append(comma);
				fw.append(String.valueOf(productoVO.getMarca().getId()));
				fw.append(comma);
				fw.append(productoVO.getMarca().getNombre());
				fw.append(comma);
				fw.append(String.valueOf(productoVO.getPrecio1()));
				fw.append(comma);
				fw.append(String.valueOf(productoVO.getPrecio2()));
				fw.append(comma);
				fw.append(String.valueOf(productoVO.getStock()));
				fw.append(comma);
				fw.append(String.valueOf(productoVO.getStockMax()));
				fw.append(comma);
				fw.append(String.valueOf(productoVO.getStockMin()));
				fw.append(comma);
				fw.append(productoVO.getTipo());
				fw.append(line);
			}
			System.out.println("CSV created.");	
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			log.printLog(e.getMessage(), this.getClass().toString());
			return false;
		}
		finally{
			try {
				fw.flush();
				fw.close();				
			} catch (Exception e2) {
				// TODO: handle exception
				log.printLog(e2.getMessage(), this.getClass().toString());
			}
		}
	}
	public String readCSVProducto(File file){
		String line = "";
		int a = 0;
		int n = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			br.readLine();
			br.readLine();
			while((line = br.readLine()) != null){
				String[] token = line.split(comma);
				if(token.length > 0){
					productoVO = new ProductoVO();
					productoVO.getCategoria().setId(Integer.valueOf(token[0]));
					productoVO.getCategoria().setNombre(token[1]);
					productoVO.setDescripcion(token[2]);
					productoVO.getMarca().setId(Integer.valueOf(token[3]));
					productoVO.getMarca().setNombre(token[4]);
					productoVO.setPrecio1(Float.valueOf(token[5]));
					productoVO.setPrecio2(Float.valueOf(token[6]));
					productoVO.setStock(Integer.valueOf(token[7]));
					productoVO.setStockMax(Integer.valueOf(token[8]));
					productoVO.setStockMin(Integer.valueOf(token[9]));
					productoVO.setTipo(token[10]);
					productos.add(productoVO);
				}
			}
			for(ProductoVO p: productos){
				if(pDAO.registrar(p)){
					a++;
				}
				else{
					n++;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.printLog(e.getMessage(), this.getClass().toString());
		}
		finally{
			try {
				br.close();
				productos.removeAll(productos);
			} catch (Exception e) {
				log.printLog(e.getMessage(), this.getClass().toString());
				// TODO: handle exception
			}
		}
		return "Productos agregados: " + a +"\n Productos incorrectos: " + n;
	}
	public boolean writeCsvEmpleado(File file, ObservableList<EmpleadoVO> empleados){
		header = "Nombre,ApPaterno,ApMaterno,Calle,Avenida,Numero,Colonia,Municipio,Telefono,Usuario,Password,Tipo";
		try {
			fw = new FileWriter(file);
			fw.append("Empleados");
			fw.append(line);
			fw.append(header);
			fw.append(line);
			for(EmpleadoVO empleadoVO : empleados){
				fw.append(empleadoVO.getNombre());
				fw.append(comma);
				fw.append(empleadoVO.getApPaterno());
				fw.append(comma);
				fw.append(empleadoVO.getApMaterno());
				fw.append(comma);
				fw.append(String.valueOf(empleadoVO.getCalle()));
				fw.append(comma);
				fw.append(String.valueOf(empleadoVO.getAvenida()));
				fw.append(comma);
				fw.append(String.valueOf(empleadoVO.getNumero()));
				fw.append(comma);
				fw.append(empleadoVO.getColonia());
				fw.append(comma);
				fw.append(empleadoVO.getMunicipio());
				fw.append(comma);
				fw.append(empleadoVO.getTelefono());
				fw.append(comma);
				fw.append(empleadoVO.getUsuario());
				fw.append(comma);
				fw.append(empleadoVO.getPassword());
				fw.append(comma);
				fw.append(empleadoVO.getTipo());
				fw.append(line);		
				
			}
			System.out.println("CSV created.");	
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			log.printLog(e.getMessage(), this.getClass().toString());
			return false;
		}
		finally{
			try {
				fw.flush();
				fw.close();				
			} catch (Exception e2) {
				// TODO: handle exception
				log.printLog(e2.getMessage(), this.getClass().toString());
			}
		}
	}
	public String readCSVEmpleado(File file){
		String line = "";
		int a = 0;
		int n = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			br.readLine();
			br.readLine();
			while((line = br.readLine()) != null){
				String[] token = line.split(comma);
				if(token.length > 0){
					empleadoVO = new EmpleadoVO();
					empleadoVO.setNombre(token[0]);
					empleadoVO.setApPaterno(token[1]);
					empleadoVO.setApMaterno(token[2]);
					empleadoVO.setCalle(Integer.valueOf(token[3]));
					empleadoVO.setAvenida(Integer.valueOf(token[4]));
					empleadoVO.setNumero(Integer.valueOf(token[5]));
					empleadoVO.setColonia(token[6]);
					empleadoVO.setMunicipio(token[7]);
					empleadoVO.setTelefono(token[8]);
					empleadoVO.setUsuario(token[9]);
					empleadoVO.setPassword(token[10]);
					empleadoVO.setTipo(token[11]);
					empleados.add(empleadoVO);
				}
			}
			for(EmpleadoVO e: empleados){
				if(eDAO.registrar(e)){
					a++;
				}
				else{
					n++;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.printLog(e.getMessage(), this.getClass().toString());
		}
		finally{
			try {
				br.close();
				empleados.removeAll(empleados);
			} catch (Exception e) {
				log.printLog(e.getMessage(), this.getClass().toString());
				// TODO: handle exception
			}
		}
		return "Empleados agregados: " + a +"\n Empleados incorrectos: " + n;
	}
	public boolean writeCsvCliente(File file, ObservableList<ClienteVO> clientes){
		header = "Nombre,ApPaterno,ApMaterno,Calle,Avenida,Numero,Colonia,Municipio,Referencia";
		try {
			fw = new FileWriter(file);
			fw.append("Clientes");
			fw.append(line);
			fw.append(header);
			fw.append(line);
			for(ClienteVO clienteVO : clientes){
				fw.append(clienteVO.getNombre());
				fw.append(comma);
				fw.append(clienteVO.getApPaterno());
				fw.append(comma);
				fw.append(clienteVO.getApMaterno());
				fw.append(comma);
				fw.append(String.valueOf(clienteVO.getCalle()));
				fw.append(comma);
				fw.append(String.valueOf(clienteVO.getAvenida()));
				fw.append(comma);
				fw.append(String.valueOf(clienteVO.getNumero()));
				fw.append(comma);
				fw.append(clienteVO.getColonia());
				fw.append(comma);
				fw.append(clienteVO.getMunicipio());
				fw.append(comma);
				fw.append(clienteVO.getReferencia());
				fw.append(line);		
			}
			System.out.println("CSV created.");	
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			log.printLog(e.getMessage(), this.getClass().toString());
			return false;
		}
		finally{
			try {
				fw.flush();
				fw.close();				
			} catch (Exception e2) {
				// TODO: handle exception
				log.printLog(e2.getMessage(), this.getClass().toString());
			}
		}
	}
	public String readCSVCliente(File file){
		String line = "";
		int a = 0;
		int n = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			br.readLine();
			br.readLine();
			while((line = br.readLine()) != null){
				String[] token = line.split(comma);
				if(token.length > 0){
					clienteVO = new ClienteVO();
					clienteVO.setNombre(token[0]);
					clienteVO.setApPaterno(token[1]);
					clienteVO.setApMaterno(token[2]);
					clienteVO.setCalle(Integer.valueOf(token[3]));
					clienteVO.setAvenida(Integer.valueOf(token[4]));
					clienteVO.setNumero(Integer.valueOf(token[5]));
					clienteVO.setColonia(token[6]);
					clienteVO.setMunicipio(token[7]);
					clienteVO.setReferencia(token[8]);
					clientes.add(clienteVO);
				}
			}
			for(ClienteVO c: clientes){
				if(cDAO.registrar(c)){
					a++;
				}
				else{
					n++;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.printLog(e.getMessage(), this.getClass().toString());
		}
		finally{
			try {
				br.close();
				clientes.removeAll(clientes);
			} catch (Exception e) {
				log.printLog(e.getMessage(), this.getClass().toString());
				// TODO: handle exception
			}
		}
		return "Clientes agregados: " + a +"\n Clientes incorrectos: " + n;
	}
	public boolean writeCsvProveedor(File file, ObservableList<ProveedorVO> proveedores){
		header = "Nombre,ApPaterno,ApMaterno,Empresa,Calle,Avenida,Numero,Colonia,Municipio,Teléfono";
		try {
			fw = new FileWriter(file);
			fw.append("Proveedores");
			fw.append(line);
			fw.append(header);
			fw.append(line);
			for(ProveedorVO proveedorVO : proveedores){
				fw.append(proveedorVO.getNombre());
				fw.append(comma);
				fw.append(proveedorVO.getApPaterno());
				fw.append(comma);
				fw.append(proveedorVO.getApMaterno());
				fw.append(comma);
				fw.append(proveedorVO.getEmpresa());
				fw.append(comma);
				fw.append(String.valueOf(proveedorVO.getCalle()));
				fw.append(comma);
				fw.append(String.valueOf(proveedorVO.getAvenida()));
				fw.append(comma);
				fw.append(String.valueOf(proveedorVO.getNumero()));
				fw.append(comma);
				fw.append(proveedorVO.getColonia());
				fw.append(comma);
				fw.append(proveedorVO.getMunicipio());
				fw.append(comma);
				fw.append(proveedorVO.getTelefono());
				fw.append(line);		
			}
			System.out.println("CSV created.");	
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			log.printLog(e.getMessage(), this.getClass().toString());
			return false;
		}
		finally{
			try {
				fw.flush();
				fw.close();				
			} catch (Exception e2) {
				// TODO: handle exception
				log.printLog(e2.getMessage(), this.getClass().toString());
			}
		}
	}
	public String readCSVProveedor(File file){
		String line = "";
		int a = 0;
		int n = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			br.readLine();
			br.readLine();
			while((line = br.readLine()) != null){
				String[] token = line.split(comma);
				if(token.length > 0){
					proveedorVO = new ProveedorVO();
					proveedorVO.setNombre(token[0]);
					proveedorVO.setApPaterno(token[1]);
					proveedorVO.setApMaterno(token[2]);
					proveedorVO.setEmpresa(token[3]);
					proveedorVO.setCalle(Integer.valueOf(token[4]));
					proveedorVO.setAvenida(Integer.valueOf(token[5]));
					proveedorVO.setNumero(Integer.valueOf(token[6]));
					proveedorVO.setColonia(token[7]);
					proveedorVO.setMunicipio(token[8]);
					proveedorVO.setTelefono(token[9]);
					proveedores.add(proveedorVO);
				}
			}
			for(ProveedorVO pro: proveedores){
				if(proDAO.registrar(pro)){
					a++;
				}
				else{
					n++;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.printLog(e.getMessage(), this.getClass().toString());
		}
		finally{
			try {
				br.close();
				clientes.removeAll(clientes);
			} catch (Exception e) {
				log.printLog(e.getMessage(), this.getClass().toString());
				// TODO: handle exception
			}
		}
		return "Proveedores agregados: " + a +"\n Proveedores incorrectos: " + n;
	}
	public boolean writeCsvCompra(File file, ObservableList<CompraDetVO> compras){
		header = "Empresa,Empleado,Producto,Cantidad,Precio,Total,Fecha Pedido,Fecha Recepción";
		try {
			fw = new FileWriter(file);
			fw.append("Compras");
			fw.append(line);
			fw.append(header);
			fw.append(line);
			for(CompraDetVO compraDetVO : compras){
				fw.append(compraDetVO.getEmpresa());
				fw.append(comma);
				fw.append(compraDetVO.getEmpleado());
				fw.append(comma);
				fw.append(compraDetVO.getProducto());
				fw.append(comma);
				fw.append(String.valueOf(compraDetVO.getCantidad()));
				fw.append(comma);
				fw.append(String.valueOf(compraDetVO.getPrecio()));
				fw.append(comma);
				fw.append(String.valueOf(compraDetVO.getTotal()));
				fw.append(comma);
				fw.append(String.valueOf(compraDetVO.getFechaPedido()));
				fw.append(comma);
				fw.append(String.valueOf(compraDetVO.getFechaRecepcion()));
				fw.append(line);		
			}
			System.out.println("CSV created.");	
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			log.printLog(e.getMessage(), this.getClass().toString());
			return false;
		}
		finally{
			try {
				fw.flush();
				fw.close();				
			} catch (Exception e2) {
				// TODO: handle exception
				log.printLog(e2.getMessage(), this.getClass().toString());
			}
		}
	}
	public boolean writeCsvVenta(File file, ObservableList<VentaDetVO> ventas){
		header = "Empleado,Cliente,Producto,Cantidad,Precio,Total,Fecha Hora";
		try {
			fw = new FileWriter(file);
			fw.append("Ventas");
			fw.append(line);
			fw.append(header);
			fw.append(line);
			for(VentaDetVO ventaDetVO : ventas){
				fw.append(ventaDetVO.getEmpleado());
				fw.append(comma);
				fw.append(ventaDetVO.getCliente());
				fw.append(comma);
				fw.append(ventaDetVO.getProducto());
				fw.append(comma);
				fw.append(String.valueOf(ventaDetVO.getCantidad()));
				fw.append(comma);
				fw.append(String.valueOf(ventaDetVO.getPrecio()));
				fw.append(comma);
				fw.append(String.valueOf(ventaDetVO.getTotal()));
				fw.append(comma);
				fw.append(String.valueOf(ventaDetVO.getFechaHora()));
				fw.append(line);		
			}
			System.out.println("CSV created.");	
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			log.printLog(e.getMessage(), this.getClass().toString());
			return false;
		}
		finally{
			try {
				fw.flush();
				fw.close();				
			} catch (Exception e2) {
				// TODO: handle exception
				log.printLog(e2.getMessage(), this.getClass().toString());
			}
		}
	}
	public String readCSVFile(File file){
		String msj = "";
		try {
			br = new BufferedReader(new FileReader(file));
			String obj = br.readLine();
			System.out.println(obj);
			br.close();
			switch (obj) {
			case "Productos":
				msj = readCSVProducto(file);
				admin.fillTableProducto();
				break;
			case "Empleados":
				msj = readCSVEmpleado(file);
				admin.fillTableEmpleado();
				break;
			case "Clientes":
				msj = readCSVCliente(file);
				admin.fillTableCliente();
				break;
			case "Proveedores":
				msj = readCSVProveedor(file);
				admin.fillTableProveedor();
				break;

			default:
				msj = "Falló lectura de archivo CSV. \n Estructura de datos no valida.";
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.printLog(e.getMessage(), this.getClass().toString());
		}
		return msj;
	}
	
	public void setAdmin(Administrador admin){
		this.admin = admin;
	}
}