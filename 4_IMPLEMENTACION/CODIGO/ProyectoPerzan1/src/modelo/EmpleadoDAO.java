package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;

public class EmpleadoDAO implements iOp{
	boolean result;
	private int contt;
	private int dato;
	ObservableList<EmpleadoVO> empleados;
	Conexion conex;
	
	public ObservableList<EmpleadoVO> getDatos(){
		if(Conexion.getInstance() != null){
			try{
				conex.conectar();
				PreparedStatement count = conex.getConnection().prepareStatement("SELECT COUNT(activo) FROM empleado WHERE activo = true");
				ResultSet cont = count.executeQuery();
				if(cont.next()){
					contt = cont.getInt(1);
				}
				count.close();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT id, nombre,"
						+ " apellido_paterno, apellido_materno, calle, avenida, numero, colonia, municipio,"
						+ " telefono, usuario, password, tipo FROM empleado WHERE activo = 's'");
				ResultSet res = consulta.executeQuery();
				datos = new EmpleadoVO[contt];
					contador = 0;
					while(res.next()){
						int id= res.getInt("id");
						String nombre = res.getString("nombre");
						String apPaterno = res.getString("apellidoPaterno");
						String apMaterno = res.getString("apellidoMaterno");
						String direccion = res.getString("direccion");
						String telefono = res.getString("telefono");
						String usuario = res.getString("usuario");
						String password = res.getString("pass");
						EmpleadoVO empleadoVO = new EmpleadoVO(id, nombre, apPaterno, apMaterno, direccion, telefono, usuario, password);
						datos[contador] = empleadoVO;
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
	public EmpleadoVO lastInsert(){		
		if(conex.conectado()){
			try{
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT id, nombre, apellidoPaterno, apellidoMaterno, direccion, telefono, usuario, pass FROM empleado WHERE `id` = ?");
				consulta.setInt(1, dato);
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					int id= res.getInt("id");
					String nombre = res.getString("nombre");
					String apPaterno = res.getString("apellidoPaterno");
					String apMaterno = res.getString("apellidoMaterno");
					String direccion = res.getString("direccion");
					String telefono = res.getString("telefono");
					String usuario = res.getString("usuario");
					String password = res.getString("pass");
					EmpleadoVO empleadoVO = new EmpleadoVO(id, nombre, apPaterno, apMaterno, direccion, telefono, usuario, password);
					return empleadoVO;
				}
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
		
		if(conex.conectado()){
			try{
				PreparedStatement consulta = conex.getConnection().prepareStatement("UPDATE empleado set activo = 'false' WHERE id = ?");
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
	@Override
	public boolean registrar(Object obj) {
		EmpleadoVO empleadoVO = new EmpleadoVO();
		empleadoVO = (EmpleadoVO) obj;
		
		if(conex.conectado()){
			try{
				PreparedStatement consulta = conex.getConnection().prepareStatement("INSERT INTO empleado VALUES (?,?,?,?,?,?,?,?,default)");
				consulta.setNull(1,0);
				consulta.setString(2,empleadoVO.getNombre());
				consulta.setString(3,empleadoVO.getApPaterno());
				consulta.setString(4, empleadoVO.getApMaterno());
				consulta.setString(5, empleadoVO.getDireccion());
				consulta.setString(6, empleadoVO.getTelefono());
				consulta.setString(7, empleadoVO.getUsuario());
				consulta.setString(8, empleadoVO.getPassword());
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
					System.out.println(dato);
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
		EmpleadoVO empleadoVO = new EmpleadoVO();
		empleadoVO = (EmpleadoVO) obj;
		
		if(conex.conectado()){
			try{
				PreparedStatement consulta = conex.getConnection().prepareStatement("UPDATE empleado SET id = ?, nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, direccion = ?, telefono = ?, usuario = ?, pass = ? WHERE Id= ?");
				consulta.setInt(1, empleadoVO.getId());
				consulta.setString(2,empleadoVO.getNombre());
				consulta.setString(3,empleadoVO.getApPaterno());
				consulta.setString(4, empleadoVO.getApMaterno());
				consulta.setString(5, empleadoVO.getDireccion());
				consulta.setString(6, empleadoVO.getTelefono());
				consulta.setString(7, empleadoVO.getUsuario());
				consulta.setString(8, empleadoVO.getPassword());
				consulta.setInt(9, empleadoVO.getId());
				
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
}