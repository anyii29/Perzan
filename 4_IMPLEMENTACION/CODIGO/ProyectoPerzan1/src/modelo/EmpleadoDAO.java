package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmpleadoDAO implements iOp{
	boolean result;
	ObservableList<EmpleadoVO> empleados;
	Conexion conex;
	
	public ObservableList<EmpleadoVO> getDatos(){
		empleados = FXCollections.observableArrayList();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT id, nombre,"
						+ " apellido_paterno, apellido_materno, calle, avenida, numero, colonia, municipio,"
						+ " telefono, usuario, password, tipo FROM empleado WHERE activo = 's'");
				ResultSet res = consulta.executeQuery();
					while(res.next()){
						int id= res.getInt("id");
						String nombre = res.getString("nombre");
						String apPaterno = res.getString("apellido_paterno");
						String apMaterno = res.getString("apellido_materno");
						int calle = res.getInt("calle");
						int avenida = res.getInt("avenida");
						int numero = res.getInt("numero");
						String colonia = res.getString("colonia");
						String municipio = res.getString("municipio");
						String telefono = res.getString("telefono");
						String usuario = res.getString("usuario");
						String password = res.getString("password");
						String tipo = res.getString("tipo");
						EmpleadoVO empleadoVO = new EmpleadoVO(id, nombre, apPaterno, apMaterno, calle,avenida,
								numero, colonia, municipio, telefono, usuario, password, tipo);
						empleados.add(empleadoVO);
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
		return empleados;
	}
	public EmpleadoVO lastInsert(){		
		if(conex.conectado()){
			try{
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT id, nombre,"
						+ " apellido_paterno, apellido_materno, calle, avenida, numero, colonia, municipio,"
						+ " telefono, usuario, password, tipo FROM empleado WHERE activo = 's' order by id desc limit  1");
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					int id= res.getInt("id");
					String nombre = res.getString("nombre");
					String apPaterno = res.getString("apellido_paterno");
					String apMaterno = res.getString("apellido_materno");
					int calle = res.getInt("calle");
					int avenida = res.getInt("avenida");
					int numero = res.getInt("numero");
					String colonia = res.getString("colonia");
					String municipio = res.getString("municipio");
					String telefono = res.getString("telefono");
					String usuario = res.getString("usuario");
					String password = res.getString("password");
					String tipo = res.getString("tipo");
					EmpleadoVO empleadoVO = new EmpleadoVO(id, nombre, apPaterno, apMaterno, calle,avenida,
							numero, colonia, municipio, telefono, usuario, password, tipo);
					return empleadoVO;
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
	public boolean eliminar(int id) {
		boolean result = false;
		
		if(conex.conectado()){
			try{
				PreparedStatement consulta = conex.getConnection().prepareStatement("UPDATE empleado set activo = 'n' WHERE id = ?");
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
	@Override
	public boolean registrar(Object obj) {
		EmpleadoVO empleadoVO = new EmpleadoVO();
		empleadoVO = (EmpleadoVO) obj;
		
		if(conex.conectado()){
			try{
				PreparedStatement consulta = conex.getConnection().prepareStatement("INSERT INTO empleado(id, nombre,"
						+ " apellido_paterno, apellido_materno, calle, avenida, numero, colonia, municipio,"
						+ " telefono, usuario, password, tipo, activo ) VALUES (default,?,?,?,?,?,?,?,?,?,?,?,?,default)");
				consulta.setString(1,empleadoVO.getNombre());
				consulta.setString(2,empleadoVO.getApPaterno());
				consulta.setString(3,empleadoVO.getApMaterno());
				consulta.setInt(4, empleadoVO.getCalle());
				consulta.setInt(5, empleadoVO.getAvenida());
				consulta.setInt(6, empleadoVO.getNumero());
				consulta.setString(7, empleadoVO.getColonia());
				consulta.setString(8, empleadoVO.getMunicipio());
				consulta.setString(9, empleadoVO.getTelefono());
				consulta.setString(10, empleadoVO.getUsuario());
				consulta.setString(11, empleadoVO.getPassword());
				consulta.setString(12, empleadoVO.getTipo());
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
	@Override
	public boolean modificar(Object obj) {
		EmpleadoVO empleadoVO = new EmpleadoVO();
		empleadoVO = (EmpleadoVO) obj;
		
		if(conex.conectado()){
			try{
				PreparedStatement consulta = conex.getConnection().prepareStatement("UPDATE empleado SET id = ?, nombre = ?,"
						+ " apellido_paterno = ?, apellido_materno = ?, calle = ?, avenida = ?, numero = ?, colonia = ?, municipio = ?,"
						+ " telefono = ?, usuario = ?, password = ?, tipo = ? WHERE id = ? ");
				consulta.setInt(1, empleadoVO.getId());
				consulta.setString(2,empleadoVO.getNombre());
				consulta.setString(3,empleadoVO.getApPaterno());
				consulta.setString(4,empleadoVO.getApMaterno());
				consulta.setInt(5, empleadoVO.getCalle());
				consulta.setInt(6, empleadoVO.getAvenida());
				consulta.setInt(7, empleadoVO.getNumero());
				consulta.setString(8, empleadoVO.getColonia());
				consulta.setString(9, empleadoVO.getMunicipio());
				consulta.setString(10, empleadoVO.getTelefono());
				consulta.setString(11, empleadoVO.getUsuario());
				consulta.setString(12, empleadoVO.getPassword());
				consulta.setString(13, empleadoVO.getTipo());
				consulta.setInt(14, empleadoVO.getId());
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