package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductoDAO implements iOp{
	int dato;
	boolean result;
	static int contt;
	boolean enviado;
	ProductoVO[] products;
	public ProductoVO[] getDatos(){
		int contador;
		ProductoVO[] datos;
		Conexion conex = new Conexion();
		if(conex.conectado()){
			try{
				PreparedStatement count = conex.getConnection().prepareStatement("SELECT COUNT(id) FROM producto");
				ResultSet cont = count.executeQuery();
				if(cont.next()){
					contt = cont.getInt(1);
				}
				count.close();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM producto");
				ResultSet res = consulta.executeQuery();
				datos = new ProductoVO[contt];
				contador = 0;
				while(res.next()){
					int id= res.getInt("id");
					String nombre = res.getString("nombre");
					double precio = res.getDouble("precio");
					int cantidad = res.getInt("cantidad");
					ProductoVO productoVO = new ProductoVO(id, nombre, precio, cantidad);
					datos[contador] = productoVO;
					contador++;
				}
				consulta.close();
				conex.desconectar();
				return datos;
				}		
			catch(SQLException e){
					System.out.println(e);
			}
		}
		return null;
	}
	public ProductoVO lastInsert(){		
		Conexion conex = new Conexion();
		if(conex.conectado()){
			try{
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM producto WHERE `id` = ?");
				consulta.setInt(1, dato);
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					int id= res.getInt("id");
					String nombre = res.getString("nombre");
					double precio = res.getDouble("precio");
					int cantidad = res.getInt("cantidad");
					ProductoVO productoVO = new ProductoVO(id, nombre, precio, cantidad);
					return productoVO;
				}
				//}
				consulta.close();
				conex.desconectar();
				}		
			catch(SQLException e){
					System.out.println(e);
			}
		}
		return null;
		}
	public ProductoVO getProducto(int idP){		
		Conexion conex = new Conexion();
		if(conex.conectado()){
			try{
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM producto WHERE `id` = ?");
				consulta.setInt(1, idP);
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					int id= res.getInt("id");
					String nombre = res.getString("nombre");
					double precio = res.getDouble("precio");
					int cantidad = res.getInt("cantidad");
					ProductoVO productoVO = new ProductoVO(id, nombre, precio, cantidad);
					enviado = true;
					return productoVO;
					
				}
				else{
					enviado = false;
				}
				//}
				consulta.close();
				conex.desconectar();
				}		
			catch(SQLException e){
					System.out.println(e);
			}
		}
		return null;
	}
	public boolean eliminar(int id) {
		boolean result = false;
		Conexion conex = new Conexion();
		if(conex.conectado()){
			try{
				PreparedStatement consulta = conex.getConnection().prepareStatement("DELETE FROM producto WHERE id = ?");
				consulta.setInt(1, id);
				int res = consulta.executeUpdate();
				if(res > 0){
					result = true;
				}
				//}
				consulta.close();
				conex.desconectar();
				}		
			catch(SQLException e){
					System.out.println(e);
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
		Conexion conex = new Conexion();
		if(conex.conectado()){
			try{
				PreparedStatement consulta = conex.getConnection().prepareStatement("INSERT INTO producto VALUES (?,?,?,?)");
				consulta.setNull(1,0);
				consulta.setString(2,productoVO.getNombre());
				consulta.setDouble(3,productoVO.getPrecio());
				consulta.setInt(4, productoVO.getCantidad());
				int res = consulta.executeUpdate();
				if(res > 0){
					result= true;	
				}
				else{
					result = false;
				}
				consulta.close();
				PreparedStatement last = conex.getConnection().prepareStatement("SELECT LAST_INSERT_ID()");
				ResultSet lastIn = last.executeQuery();
				if(lastIn.next()){
					dato = lastIn.getInt(1);
				}
				last.close();
				conex.desconectar();
				}		
				catch(SQLException e){
					System.out.println(e);}
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
		Conexion conex = new Conexion();
		if(conex.conectado()){
			try{
				PreparedStatement consulta = conex.getConnection().prepareStatement("UPDATE producto SET id= ?, nombre = ?,precio = ?, cantidad = ? WHERE id=?");
				consulta.setInt(1,productoVO.getId());
				consulta.setString(2,productoVO.getNombre());
				consulta.setDouble(3,productoVO.getPrecio());
				consulta.setInt(4, productoVO.getCantidad());
				consulta.setInt(5, productoVO.getId());			
				int res = consulta.executeUpdate();
				if(res > 0){
					result= true;	
				}
				else{
					result = false;
				}
				consulta.close();
				conex.desconectar();
				}		
				catch(SQLException e){
					System.out.println(e);}
		}
		if(result){
			return true;
		}
		else{
			return false;
		}		
	}
	public boolean venta(int id, int cantidad) {
		Conexion conex = new Conexion();
		if(conex.conectado()){
			try{
				PreparedStatement consulta = conex.getConnection().prepareStatement("UPDATE producto SET cantidad=(cantidad - ?) WHERE  id=?;");
				consulta.setInt(1, cantidad);
				consulta.setInt(2, id);			
				int res = consulta.executeUpdate();
				if(res > 0){
					result= true;	
				}
				else{
					result = false;
				}
				consulta.close();
				conex.desconectar();
				}		
				catch(SQLException e){
					System.out.println(e);}
		}
		if(result){
			return true;
		}
		else{
			return false;
		}		
	}
	public ObservableList<ProductoVO> getBuscado(int idP, String nom, double prec){		
		Conexion conex = new Conexion();
		ObservableList<ProductoVO> productosVO = FXCollections.observableArrayList();
		String nom1 = "%" + nom + "%";
		if(conex.conectado()){
			if(nom.equals("")){
				try{
					PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM producto WHERE `id` = ? OR `precio` = ? ");
					consulta.setInt(1, idP);
					consulta.setDouble(2, prec);
					ResultSet res = consulta.executeQuery();
					int i = 0;
					while(res.next()){
						int id= res.getInt("id");
						String nombre = res.getString("nombre");
						System.out.println(nombre);
						double precio = res.getDouble("precio");
						int cantidad = res.getInt("cantidad");
						ProductoVO productoVO = new ProductoVO(id, nombre, precio, cantidad);
						productosVO.add(productoVO);
						i++;
					}
					if(i != 0){
						enviado=true;
					}
					consulta.close();
					conex.desconectar();
					}		
				catch(SQLException e){
						System.out.println(e);
				}
			}
			else{
				try{
					PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM producto WHERE `id` = ? OR `nombre` LIKE ? OR `precio` = ? ");
					consulta.setInt(1, idP);
					consulta.setString(2, nom1);
					consulta.setDouble(3, prec);
					ResultSet res = consulta.executeQuery();
					int i = 0;
					while(res.next()){
						int id= res.getInt("id");
						String nombre = res.getString("nombre");
						System.out.println(nombre);
						double precio = res.getDouble("precio");
						int cantidad = res.getInt("cantidad");
						ProductoVO productoVO = new ProductoVO(id, nombre, precio, cantidad);
						productosVO.add(productoVO);
						i++;
					}
					if(i != 0){
						enviado=true;
					}
					consulta.close();
					conex.desconectar();
					}		
				catch(SQLException e){
						System.out.println(e);
				}
			}
		}
		return productosVO;
	}
}