package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Conexion;

public class LoginEmpDAO {
	boolean result;
	Conexion conex;

	public boolean iniciar(LoginEmpVO usuarioEmpVO) {
		conex = Conexion.getInstance();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * from empleado where usuario = ? and pass = ?");
				consulta.setString(1,usuarioEmpVO.getUsuario());
				consulta.setString(2,usuarioEmpVO.getPassword());
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					if(res.getString("usuario").equals(usuarioEmpVO.getUsuario())&&res.getString("pass").equals(usuarioEmpVO.getPassword())){
						result = true;
					}	
				}
				else{
					result = false;
				}
				res.close();
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
	public String nombre(String nombre) {
		conex = Conexion.getInstance();
		boolean dato = false;
		String nom = null;
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("select CONCAT(nombre, \" \", apPaterno) as nombre from empleado where usuario = ?");
				consulta.setString(1, nombre);
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					nom = res.getString("nombre");
					dato = true;
				}
			}		
			catch(SQLException e){
				e.printStackTrace();
			}
			finally{
				conex.desconectar();
			}
		}
		
		if(dato){
			return nom;
		}
		else{
			return null;
		}
		
	}
}

