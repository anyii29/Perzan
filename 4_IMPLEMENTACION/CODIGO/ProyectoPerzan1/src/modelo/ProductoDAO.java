package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductoDAO implements iOp{
	int dato;
	boolean result;
	static int contt;
	boolean enviado;
	ProductoVO[] products;
	Conexion conex;
	// revisar
	public ProductoVO[] getDatos(){
		int contador;
		ProductoVO[] datos;
		conex = Conexion.getInstance();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement count = conex.getConnection().prepareStatement("SELECT COUNT(id) FROM producto WHERE activo = 's'");
				ResultSet cont = count.executeQuery();
				if(cont.next()){
					contt = cont.getInt(1);
				}
				count.close();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT id, nombre, id_categoria, id_marca, precio, precio2, stock, stock_max, stock_min, tipos FROM producto WHERE activo = 's'");
				ResultSet res = consulta.executeQuery();
				datos = new ProductoVO[contt];
				contador = 0;
				while(res.next()){
					int id= res.getInt("id");
					String nombre = res.getString("nombre");
					
					double precio = res.getDouble("precio");
					double precio2 = res.getDouble("precio2");
					int stock = res.getInt("stock");
					int stockMax = res.getInt("stock_max");
					int stockMin= res.getInt("stockMin");
					String tipo = res.getString("tipos");
					ProductoVO productoVO = new ProductoVO(id, nombre, "categoria", "marca", precio, precio2, stock, stockMax, stockMin, tipo);
					datos[contador] = productoVO;
					contador++;
				}
				consulta.close();
				return datos;
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
	public ProductoVO lastInsert(){		
		conex = Conexion.getInstance();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT id, nombre, id_categoria, id_marca, precio, precio2, stock, stock_max, stock_min, tipos FROM producto WHERE id = ? and activo = 's'");
				consulta.setInt(1, dato);
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					int id= res.getInt("id");
					String nombre = res.getString("nombre");
					
					double precio = res.getDouble("precio");
					double precio2 = res.getDouble("precio2");
					int stock = res.getInt("stock");
					int stockMax = res.getInt("stock_max");
					int stockMin= res.getInt("stockMin");
					String tipo = res.getString("tipos");
					ProductoVO productoVO = new ProductoVO(id, nombre, "categoria", "marca", precio, precio2, stock, stockMax, stockMin, tipo);
					return productoVO;
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
	public ProductoVO getProducto(int idP){		
		conex = Conexion.getInstance();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT id, nombre, id_categoria, id_marca, precio, precio2, stock, stock_max, stock_min, tipos FROM producto WHERE id = ? and activo = 's'");
				consulta.setInt(1, idP);
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					int id= res.getInt("id");
					String nombre = res.getString("nombre");
					
					double precio = res.getDouble("precio");
					double precio2 = res.getDouble("precio2");
					int stock = res.getInt("stock");
					int stockMax = res.getInt("stock_max");
					int stockMin= res.getInt("stockMin");
					String tipo = res.getString("tipos");
					ProductoVO productoVO = new ProductoVO(id, nombre, "categoria", "marca", precio, precio2, stock, stockMax, stockMin, tipo);
					enviado = true;
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
				PreparedStatement consulta = conex.getConnection().prepareStatement("INSERT INTO producto  VALUES (default,?,?,?,?,?,?,?,?,?,default)");
				consulta.setString(1,productoVO.getNombre());
				consulta.setInt(2, productoVO.getIdCategoria());
				consulta.setInt(3, productoVO.getIdMarca());
				consulta.setDouble(4, productoVO.getPrecio());
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
		conex = Conexion.getInstance();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("UPDATE producto SET id= ?, nombre = ?, id_categoria = ?, id _marca = ?, precio = ?, precio2 = ?, stock = ?, stock_max = ?, stock_min = ?, tipos = ? WHERE id=?");
				consulta.setInt(1,productoVO.getId());
				consulta.setString(2,productoVO.getNombre());
				consulta.setInt(3, productoVO.getIdCategoria());
				consulta.setInt(4, productoVO.getIdMarca());
				consulta.setDouble(5,productoVO.getPrecio());
				consulta.setDouble(6,productoVO.getPrecio2());
				consulta.setInt(7, productoVO.getStock());
				consulta.setInt(8, productoVO.getStockMax());
				consulta.setInt(9, productoVO.getStockMin());
				consulta.setString(10, productoVO.getTipo());			
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
		String nom1 = "%" + nom + "%";
		if(conex.conectado()){
			if(nom.equals("")){
				try{
					conex.conectar();
					PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT id, nombre, id_categoria, id_marca, precio, precio2, stock, stock_max, stock_min, tipos FROM producto WHERE `id` = ? OR `precio` = ? ");
					consulta.setInt(1, idP);
					consulta.setDouble(2, prec);
					ResultSet res = consulta.executeQuery();
					int i = 0;
					while(res.next()){
						int id= res.getInt("id");
						String nombre = res.getString("nombre");
						
						double precio = res.getDouble("precio");
						double precio2 = res.getDouble("precio2");
						int stock = res.getInt("stock");
						int stockMax = res.getInt("stock_max");
						int stockMin= res.getInt("stockMin");
						String tipo = res.getString("tipos");
						ProductoVO productoVO = new ProductoVO(id, nombre, "categoria", "marca", precio, precio2, stock, stockMax, stockMin, tipo);
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
					PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT id, nombre, id_categoria, id_marca, precio, precio2, stock, stock_max, stock_min, tipos FROM producto WHERE `id` = ? OR `nombre` LIKE ? OR `precio` = ? ");
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
						int stockMin= res.getInt("stockMin");
						String tipo = res.getString("tipos");
						ProductoVO productoVO = new ProductoVO(id, nombre, "categoria", "marca", precio, precio2, stock, stockMax, stockMin, tipo);
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
		}
		return productosVO;
	}
	public List<Object> listarCategoria(){
		conex = Conexion.getInstance();
		List<Object> lista = new ArrayList<Object>();
		try {
			conex.conectar();
			PreparedStatement consulta= conex.getConnection().prepareStatement("SELECT id, nombre FROM categoria");
			ResultSet res = consulta.executeQuery();
			while(res.next()){
				String nombre =res.getString("nombre");
				int id = res.getInt("id");
				CategoriaVO categoriaVO = new CategoriaVO(id, nombre);
				lista.add(categoriaVO);
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			conex.desconectar();
		}
		return lista;
	}
	public List<Object> listarMarca(){
		conex = Conexion.getInstance();
		List<Object> lista = new ArrayList<Object>();
		try {
			conex.conectar();
			PreparedStatement consulta= conex.getConnection().prepareStatement("SELECT id, nombre FROM marca");
			ResultSet res = consulta.executeQuery();
			while(res.next()){
				String nombre =res.getString("nombre");
				int id = res.getInt("id");
				MarcaVO marcaVO= new MarcaVO(id, nombre);
				lista.add(marcaVO);
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			conex.desconectar();
		}
		return lista;
	}
}