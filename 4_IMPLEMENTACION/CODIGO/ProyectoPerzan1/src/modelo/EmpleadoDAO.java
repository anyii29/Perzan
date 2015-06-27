package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmpleadoDAO implements iOp{
	boolean result;
	ObservableList<EmpleadoVO> empleados;
	Conexion conex = Conexion.getInstance();
	
	public ObservableList<EmpleadoVO> getDatos(){
		empleados = FXCollections.observableArrayList();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * fn_seleccionarempleados()");
				ResultSet res = consulta.executeQuery();
					while(res.next()){
						int id= res.getInt("fid");
						String nombre = res.getString("fnombre");
						String apPaterno = res.getString("fapellido_paterno");
						String apMaterno = res.getString("fapellido_materno");
						int calle = res.getInt("fcalle");
						int avenida = res.getInt("favenida");
						int numero = res.getInt("fnumero");
						String colonia = res.getString("fcolonia");
						String municipio = res.getString("fmunicipio");
						String telefono = res.getString("ftelefono");
						String usuario = res.getString("fusuario");
						String password = res.getString("fpassword");
						String tipo = res.getString("ftipo");
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
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionarultimoempleado()");
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					int id= res.getInt("fid");
					String nombre = res.getString("fnombre");
					String apPaterno = res.getString("fapellido_paterno");
					String apMaterno = res.getString("fapellido_materno");
					int calle = res.getInt("fcalle");
					int avenida = res.getInt("favenida");
					int numero = res.getInt("fnumero");
					String colonia = res.getString("fcolonia");
					String municipio = res.getString("fmunicipio");
					String telefono = res.getString("ftelefono");
					String usuario = res.getString("fusuario");
					String password = res.getString("fpassword");
					String tipo = res.getString("ftipo");
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
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_eliminarempleado(?)");
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
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_agregarempleado(?,?,?,?,?,?,?,?,?,?,?,?)");
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
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_modificarempleado(?,?,?,?,?,?,?,?,?,?,?,?)");
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