package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Conexion;

public class AdminDAO {
	boolean result;
	private Conexion conex;
	public boolean user(String pass) {
		boolean yes = false;
		conex = Conexion.getInstance();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT password from administrador as pass where password = ?");
				consulta.setString(1, pass);
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					String password = res.getString("password");
					yes = true;
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
		return yes;
	}
	public boolean actualizar(String usuario, String pass){
		conex = Conexion.getInstance();
		if(conex.conectado()){
			if(!(usuario.equals(""))){
				try{
					conex.conectar();
					PreparedStatement consulta = conex.getConnection().prepareStatement("UPDATE administrador SET administrador = ?, password = MD5(?) WHERE Id = 1");
					consulta.setString(1, usuario);
					consulta.setString(2,	pass);
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
			else{
				try{
					conex.conectar();
					PreparedStatement consulta = conex.getConnection().prepareStatement("UPDATE administrador (pasword) SET password = MD5(?) WHERE Id = 1");
					consulta.setString(1, pass);
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
		}
		if(result){
			return true;
		}
		else{
			return false;
		}
	}
}
