package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class ProductoDAO implements iOp{
	private int dato;
	private boolean result;
	private static int contt;
	private boolean enviado;
	private CategoriaVO catVO;
	private MarcaVO marVO;
	private Conexion conex;
	private ObservableList<ProductoVO> productos;
	private Logger log;
	private ProductoVO productoVO;
	private String nombreProducto;
	
	public ProductoDAO(){
		log = new Logger();
		conex = Conexion.getInstance();
		productoVO = new ProductoVO();
	}
	// revisar
	public ObservableList<ProductoVO> getDatos(boolean d){
		productos = FXCollections.observableArrayList();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta;
				if(d){
					consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionarproductos()");
				}
				else{
					consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionareliminadoproducto()");
				}
				ResultSet res = consulta.executeQuery();
				while(res.next()){
					int id= res.getInt("fid");
					int idCat = res.getInt("fid_categoria");
					String nombreCat = res.getString("fcategoria");
					catVO = new CategoriaVO(idCat, nombreCat);
					String descripcion = res.getString("fdescripcion");
					int idMar = res.getInt("fid_marca");
					String nombreMar = res.getString("fmarca");
					marVO = new MarcaVO(idMar, nombreMar);
					float precio1 = res.getFloat("fprecio1");
					float precio2 = res.getFloat("fprecio2");
					int stock = res.getInt("fstock");
					int stockMax = res.getInt("fstock_max");
					int stockMin= res.getInt("fstock_min");
					String tipo = res.getString("ftipo");
					productoVO = new ProductoVO(id, catVO, descripcion, marVO, precio1, precio2, stock, stockMax, stockMin, tipo);
					productos.add(productoVO);
				}
				consulta.close();
				}		
			catch(SQLException e){
					log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}
		}
		return productos;
	}
	public ProductoVO lastInsert(){		
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionarultimoproducto()");
				//consulta.setInt(1, dato);
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					int id= res.getInt("fid");
					int idCat = res.getInt("fid_categoria");
					String nombreCat = res.getString("fcategoria");
					catVO = new CategoriaVO(idCat, nombreCat);
					String descripcion = res.getString("fdescripcion");
					int idMar = res.getInt("fid_marca");
					String nombreMar = res.getString("fmarca");
					marVO = new MarcaVO(idMar, nombreMar);
					float precio1 = res.getFloat("fprecio1");
					float precio2 = res.getFloat("fprecio2");
					int stock = res.getInt("fstock");
					int stockMax = res.getInt("fstock_max");
					int stockMin= res.getInt("fstock_min");
					String tipo = res.getString("ftipo");
					ProductoVO productoVO = new ProductoVO(id, catVO, descripcion, marVO, precio1, precio2, stock, stockMax, stockMin, tipo);
					return productoVO;
				}
				consulta.close();
				}		
			catch(SQLException e){
					log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}
		}
		return null;
		}
	public ProductoVO getProducto(int idP){		
		conex = Conexion.getInstance();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionarproducto(?)");
			consulta.setInt(1, idP);
			ResultSet res = consulta.executeQuery();
			if(res.next()){
				int id= res.getInt("fid");
				int idCat = res.getInt("fid_categoria");
				String nombreCat = res.getString("fcategoria");
				catVO = new CategoriaVO(idCat, nombreCat);
				String descripcion = res.getString("fdescripcion");
				int idMar = res.getInt("fid_marca");
				String nombreMar = res.getString("fmarca");
				marVO = new MarcaVO(idMar, nombreMar);
				float precio1 = res.getFloat("fprecio1");
				float precio2 = res.getFloat("fprecio2");
				int stock = res.getInt("fstock");
				int stockMax = res.getInt("fstock_max");
				int stockMin= res.getInt("fstock_min");
				String tipo = res.getString("ftipo");
				ProductoVO productoVO = new ProductoVO(id, catVO, descripcion, marVO, precio1, precio2, stock, stockMax, stockMin, tipo);
				return productoVO;
				}
				else{
					enviado = false;
				}
				//}
				consulta.close();
				
				}		
			catch(SQLException e){
					log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}
		}
		return null;
	}
	public boolean eliminar(int id) {
		boolean result = false;
		conex = Conexion.getInstance();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_eliminaproducto(?)");
				consulta.setInt(1, id);
				boolean res = consulta.execute();
				if(res){
					result = true;
				}
				//}
				consulta.close();
				
				}		
			catch(SQLException e){
					log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}
		}
		if(result){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean got() {
		if(enviado){
			return true;
		}
		else{
			return false;
		}
	}
	@Override
	public boolean registrar(Object obj) {
		ProductoVO productoVO = new ProductoVO();
		productoVO = (ProductoVO) obj;
		conex = Conexion.getInstance();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_agregarproducto(?,?,?,?,?,?,?,?,?)");
				consulta.setInt(1, productoVO.getCategoria().getId());
				consulta.setString(2, productoVO.getDescripcion());
				consulta.setInt(3, productoVO.getMarca().getId());
				consulta.setFloat(4, productoVO.getPrecio1());
				consulta.setFloat(5, productoVO.getPrecio2());
				consulta.setInt(6, productoVO.getStock());
				consulta.setInt(7, productoVO.getStockMax());
				consulta.setInt(8, productoVO.getStockMin());
				consulta.setString(9, productoVO.getTipo());
				result = consulta.execute();
				consulta.close();
				/*PreparedStatement last = conex.getConnection().prepareStatement("SELECT LAST_INSERT_ID()");
				ResultSet lastIn = last.executeQuery();
				if(lastIn.next()){
					dato = lastIn.getInt(1);
				}
				last.close();*/
				
				}		
			catch(SQLException e){
				log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}
		}
		
		if(result){
			return true;
		}
		else{
			return false;
		}		
	}
	@Override
	public boolean modificar(Object obj) {
		ProductoVO productoVO = new ProductoVO();
		productoVO = (ProductoVO) obj;
		boolean result = false;
		conex = Conexion.getInstance();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_modificaproducto(?,?,?,?,?,?,?,?,?,?)");
				consulta.setInt(1,productoVO.getId());
				consulta.setInt(2, productoVO.getCategoria().getId());
				consulta.setString(3,productoVO.getDescripcion());
				consulta.setInt(4, productoVO.getMarca().getId());
				consulta.setFloat(5,productoVO.getPrecio1());
				consulta.setFloat(6,productoVO.getPrecio2());
				consulta.setInt(7, productoVO.getStock());
				consulta.setInt(8, productoVO.getStockMax());
				consulta.setInt(9, productoVO.getStockMin());
				consulta.setString(10, productoVO.getTipo());			
				boolean res = consulta.execute();
				if(res){
					result= true;	
				}
				consulta.close();
				
				}		
			catch(SQLException e){
				log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}
		}
		return result;		
	}
	public boolean venta(int id, int stock) {
		conex = Conexion.getInstance();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_agregarventaproducto(?,?)");
				consulta.setInt(1, stock);
				consulta.setInt(2, id);			
				boolean res = consulta.execute();
				if(res){
					result= true;	
				}
				else{
					result = false;
				}
				consulta.close();
				
				}		
			catch(SQLException e){
				log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}
		}
		if(result){
			return true;
		}
		else{
			return false;
		}		
	}	
	public boolean inventario(InventarioVO inventarioVO) {
		boolean result = false;
		conex = Conexion.getInstance();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_ajusteinventario(?,?,?,?,?)");
				consulta.setInt(1,inventarioVO.getIdProducto());
				consulta.setString(2, inventarioVO.getCausa());
				consulta.setInt(3,inventarioVO.getExistencia());
				consulta.setInt(4, inventarioVO.getNuevaExistencia());
				consulta.setInt(5,inventarioVO.getIdEmpleado());			
				boolean res = consulta.execute();
				if(res){
					result= true;	
				}
				consulta.close();
				
				}		
			catch(SQLException e){
				log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}
		}
		return result;		
	}
	public boolean modificarEliminado(int id){
		try {
			if(conex.conectado()){
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("select fn_modificareliminadoproducto(?)");
				consulta.setInt(1, id);
				boolean res = consulta.execute();
				if(res){
					return true;
				}
				consulta.close();
			}
			
		} catch (Exception e) {
			log.printLog(e.getMessage(), this.getClass().toString());
		}
		finally{
			conex.desconectar();
		}
		return false;
	}
	public ObservableList<StockVO> getStock() {
		StockVO stockVO;
		ObservableList<StockVO> stock = FXCollections.observableArrayList();
		try {
			conex.conectar();
			PreparedStatement consulta = conex.getConnection().prepareStatement("select * from fn_seleccionarhistorialstock()");
			ResultSet res = consulta.executeQuery();
			while(res.next()){
				stockVO = new StockVO();
				stockVO.setIdProducto(res.getInt("id_producto"));
				stockVO.setCausa(res.getString("causa"));
				stockVO.setUsuario(res.getString("usuario"));
				stockVO.setStock(res.getInt("stock"));
				stockVO.setFechaHora(res.getTimestamp("fecha_hora"));
				stock.add(stockVO);
			}
		} catch (Exception e) {
			log.printLog(e.getMessage(), this.getClass().toString());
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return stock;
	}
}