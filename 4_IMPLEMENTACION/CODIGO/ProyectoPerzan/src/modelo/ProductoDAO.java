package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductoDAO implements iOp{
	private int dato;
	private boolean result;
	private static int contt;
	private boolean enviado;
	private CategoriaVO catVO;
	private MarcaVO marVO;
	private Conexion conex = Conexion.getInstance();
	ObservableList<ProductoVO> productos;
	// revisar
	public ObservableList<ProductoVO> getDatos(){
		productos = FXCollections.observableArrayList();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT *FROM fn_seleccionarproductos()");
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
					double precio1 = res.getDouble("fprecio1");
					double precio2 = res.getDouble("fprecio2");
					int stock = res.getInt("fstock");
					int stockMax = res.getInt("fstock_max");
					int stockMin= res.getInt("fstock_min");
					String tipo = res.getString("ftipo");
					ProductoVO productoVO = new ProductoVO(id, catVO, descripcion, marVO, precio1, precio2, stock, stockMax, stockMin, tipo);
					productos.add(productoVO);
				}
				consulta.close();
				}		
			catch(SQLException e){
					e.printStackTrace();
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
					double precio1 = res.getDouble("fprecio1");
					double precio2 = res.getDouble("fprecio2");
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
					e.printStackTrace();
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
				double precio1 = res.getDouble("fprecio1");
				double precio2 = res.getDouble("fprecio2");
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
					e.printStackTrace();
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
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_eliminarproducto(?)");
				consulta.setInt(1, id);
				int res = consulta.executeUpdate();
				if(res > 0){
					result = true;
				}
				//}
				consulta.close();
				
				}		
			catch(SQLException e){
					e.printStackTrace();
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
				consulta.setDouble(4, productoVO.getPrecio1());
				consulta.setDouble(5, productoVO.getPrecio2());
				consulta.setInt(6, productoVO.getStock());
				consulta.setInt(7, productoVO.getStockMax());
				consulta.setInt(8, productoVO.getStockMin());
				consulta.setString(9, productoVO.getTipo());
				
				int res = consulta.executeUpdate();
				if(res > 0){
					result= true;	
				}
				else{
					result = false;
				}
				consulta.close();
				/*PreparedStatement last = conex.getConnection().prepareStatement("SELECT LAST_INSERT_ID()");
				ResultSet lastIn = last.executeQuery();
				if(lastIn.next()){
					dato = lastIn.getInt(1);
				}
				last.close();*/
				
				}		
			catch(SQLException e){
				e.printStackTrace();
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
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_modificarproducto(?,?,?,?,?,?,?,?,?)");
				consulta.setInt(1,productoVO.getId());
				consulta.setInt(2, productoVO.getCategoria().getId());
				consulta.setString(3,productoVO.getDescripcion());
				consulta.setInt(4, productoVO.getMarca().getId());
				consulta.setDouble(5,productoVO.getPrecio1());
				consulta.setDouble(6,productoVO.getPrecio2());
				consulta.setInt(7, productoVO.getStock());
				consulta.setInt(8, productoVO.getStockMax());
				consulta.setInt(9, productoVO.getStockMin());
				consulta.setString(10, productoVO.getTipo());			
				int res = consulta.executeUpdate();
				if(res > 0){
					result= true;	
				}
				consulta.close();
				
				}		
			catch(SQLException e){
				e.printStackTrace();
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
				int res = consulta.executeUpdate();
				if(res > 0){
					result= true;	
				}
				else{
					result = false;
				}
				consulta.close();
				
				}		
			catch(SQLException e){
				e.printStackTrace();
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
}