package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Conexion;

public class UsuarioEmpDAO {
	boolean result;

	public boolean iniciar(UsuarioEmpVO usuarioEmpVO) {
		Conexion conex = new Conexion();
		if(conex.conectado()){
			try{
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
	public String nombre(String nombre) {
		Conexion conex = new Conexion();
		boolean dato = false;
		String nom = null;
		if(conex.conectado()){
			try{
				PreparedStatement consulta = conex.getConnection().prepareStatement("select CONCAT(nombre, \" \", apPaterno) as nombre from empleado where usuario = ?");
				consulta.setString(1, nombre);
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					nom = res.getString("nombre");
					dato = true;
				}
			}		
			catch(SQLException e){
				System.out.println(e);
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

