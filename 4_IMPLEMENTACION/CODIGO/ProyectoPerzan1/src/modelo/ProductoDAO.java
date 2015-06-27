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
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT producto.id,"
							+ " categoria.id as id_categoria, categoria.nombre AS categoria,"
							+ " producto.descripcion, marca.id as id_marca, marca.nombre AS marca,"
							+ " producto.precio1, producto.precio2, producto.stock, producto.stock_max,"
							+ " producto.stock_min, producto.tipo FROM producto"
							+ " inner JOIN categoria ON categoria.id = producto.id_categoria"
							+ " inner JOIN marca ON marca.id = producto.id_marca where activo = 's'");
				ResultSet res = consulta.executeQuery();
				while(res.next()){
					int id= res.getInt("id");
					int idCat = res.getInt("id_categoria");
					String nombreCat = res.getString("categoria");
					catVO = new CategoriaVO(idCat, nombreCat);
					String descripcion = res.getString("descripcion");
					int idMar = res.getInt("id_marca");
					String nombreMar = res.getString("marca");
					marVO = new MarcaVO(idMar, nombreMar);
					double precio1 = res.getDouble("precio1");
					double precio2 = res.getDouble("precio2");
					int stock = res.getInt("stock");
					int stockMax = res.getInt("stock_max");
					int stockMin= res.getInt("stock_min");
					String tipo = res.getString("tipo");
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
				PreparedStatement consulta = conex.getConnection().prepareStatement(
						"SELECT producto.id,"
							+ " categoria.id as id_categoria, categoria.nombre AS categoria,"
							+ " producto.descripcion, marca.id as id_marca, marca.nombre AS marca,"
							+ " producto.precio1, producto.precio2, producto.stock, producto.stock_max,"
							+ " producto.stock_min, producto.tipo FROM producto"
							+ " inner JOIN categoria ON categoria.id = producto.id_categoria"
							+ " inner JOIN marca ON marca.id = producto.id_marca WHERE activo = 's' ORDER BY id DESC LIMIT 1");
				//consulta.setInt(1, dato);
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					int id= res.getInt("id");
					int idCat = res.getInt("id_categoria");
					String nombreCat = res.getString("categoria");
					catVO = new CategoriaVO(idCat, nombreCat);
					String descripcion = res.getString("descripcion");
					int idMar = res.getInt("id_marca");
					String nombreMar = res.getString("marca");
					marVO = new MarcaVO(idMar, nombreMar);
					double precio1 = res.getDouble("precio1");
					double precio2 = res.getDouble("precio2");
					int stock = res.getInt("stock");
					int stockMax = res.getInt("stock_max");
					int stockMin= res.getInt("stock_min");
					String tipo = res.getString("tipo");
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
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT producto.id,"
						+ " categoria.id as id_categoria, categoria.nombre AS categoria,"
						+ " producto.descripcion, marca.id as id_marca, marca.nombre AS marca,"
						+ " producto.precio1, producto.precio2, producto.stock, producto.stock_max,"
						+ " producto.stock_min, producto.tipo FROM producto"
						+ " inner JOIN categoria ON categoria.id = producto.id_categoria"
						+ " inner JOIN marca ON marca.id = producto.id_marca WHERE id = ? ORDER BY id DESC LIMIT 1");
			consulta.setInt(1, idP);
			ResultSet res = consulta.executeQuery();
			if(res.next()){
				int id= res.getInt("id");
				int idCat = res.getInt("id_categoria");
				String nombreCat = res.getString("categoria");
				catVO = new CategoriaVO(idCat, nombreCat);
				String descripcion = res.getString("descripcion");
				int idMar = res.getInt("id_marca");
				String nombreMar = res.getString("marca");
				marVO = new MarcaVO(idMar, nombreMar);
				double precio1 = res.getDouble("precio1");
				double precio2 = res.getDouble("precio2");
				int stock = res.getInt("stock");
				int stockMax = res.getInt("stock_max");
				int stockMin= res.getInt("stock_min");
				String tipo = res.getString("tipo");
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
				PreparedStatement consulta = conex.getConnection().prepareStatement("UPDATE producto SET activo = 'n' WHERE id = ?");
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
				PreparedStatement consulta = conex.getConnection().prepareStatement("INSERT INTO producto"
						+ " (id, id_categoria, descripcion, id_marca, precio1, precio2, stock, stock_max,"
						+ " stock_min, tipo, activo)  VALUES (default,?,?,?,?,?,?,?,?,?,default)");
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
				PreparedStatement consulta = conex.getConnection().prepareStatement("UPDATE producto"
						+ " SET id= ?,  id_categoria = ?, descripcion = ?, id _marca = ?, precio1 = ?,"
						+ " precio2 = ?, stock = ?, stock_max = ?, stock_min = ?, tipo = ? WHERE id=?");
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
				PreparedStatement consulta = conex.getConnection().prepareStatement("UPDATE producto SET stock=(stock - ?) WHERE  id=?;");
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
	public ObservableList<ProductoVO> getBuscado(int idP, String nom, double prec){		
		conex = Conexion.getInstance();
		ObservableList<ProductoVO> productosVO = FXCollections.observableArrayList();
		CategoriaVO catVO;
		MarcaVO marVO;
		String nom1 = "%" + nom + "%";
		if(conex.conectado()){
			if(nom.equals("")){
				try{
					conex.conectar();
					PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT producto.id,"
							+ " categoria.id as id_categoria, categoria.nombre AS categoria,"
							+ " producto.descripcion, marca.id as id_marca, marca.nombre AS marca,"
							+ " producto.precio1, producto.precio2, producto.stock, producto.stock_max,"
							+ " producto.stock_min, producto.tipo FROM producto"
							+ " inner JOIN categoria ON categoria.id = producto.id_categoria"
							+ " inner JOIN marca ON marca.id = producto.id_marca "
							+ " WHERE `id` = ? OR `precio1` = ? OR `precio2`");
					consulta.setInt(1, idP);
					consulta.setDouble(2, prec);
					ResultSet res = consulta.executeQuery();
					int i = 0;
					while(res.next()){
						int id= res.getInt("id");
						int idCat = res.getInt("id_categoria");
						String nombreCat = res.getString("categoria");
						catVO = new CategoriaVO(idCat, nombreCat);
						String descripcion = res.getString("descripcion");
						int idMar = res.getInt("id_marca");
						String nombreMar = res.getString("marca");
						marVO = new MarcaVO(idMar, nombreMar);
						double precio1 = res.getDouble("precio1");
						double precio2 = res.getDouble("precio2");
						int stock = res.getInt("stock");
						int stockMax = res.getInt("stock_max");
						int stockMin= res.getInt("stock_min");
						String tipo = res.getString("tipo");
						ProductoVO productoVO = new ProductoVO(id, catVO, descripcion, marVO, precio1, precio2, stock, stockMax, stockMin, tipo);
						productosVO.add(productoVO);
						i++;
					}
					if(i != 0){
						enviado=true;
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
			else{
				try{
					PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT id, nombre, id_categoria, id_marca, precio, precio2, stock, stock_max, stock_min, tipo FROM producto WHERE `id` = ? OR `descripcion` LIKE ? OR `precio` = ? ");
					consulta.setInt(1, idP);
					consulta.setString(2, nom1);
					consulta.setDouble(3, prec);
					ResultSet res = consulta.executeQuery();
					int i = 0;
					while(res.next()){
						int id= res.getInt("id");
						String nombre = res.getString("nombre");
						
						double precio = res.getDouble("precio");
						double precio2 = res.getDouble("precio2");
						int stock = res.getInt("stock");
						int stockMax = res.getInt("stock_max");
						int stockMin= res.getInt("stock_min");
						String tipo = res.getString("tipo");
						//ProductoVO productoVO = new ProductoVO(id, nombre, "categoria", "marca", precio, precio2, stock, stockMax, stockMin, tipo);
						//productosVO.add(productoVO);
						i++;
					}
					if(i != 0){
						enviado=true;
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
		}
		return productosVO;
	}
	
}