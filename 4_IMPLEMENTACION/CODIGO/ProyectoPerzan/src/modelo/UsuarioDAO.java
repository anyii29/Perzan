package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Conexion;

public class UsuarioDAO {
	boolean result;

	public boolean iniciar(UsuarioVO usuarioVO) {
		Conexion conex = new Conexion();
		if(conex.conectado()){
			try{
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * from usuarios where usuario = ? and pass = ?");
				consulta.setString(1,usuarioVO.getUsuario());
				consulta.setString(2,usuarioVO.getPassword());
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					if(res.getString("usuario").equals(usuarioVO.getUsuario())&&res.getString("pass").equals(usuarioVO.getPassword())){
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
	public String user(String pass) {
		String password = "";
		Conexion conex = new Conexion();
		if(conex.conectado()){
			try{
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * from usuarios as pass where pass = ?");
				consulta.setString(1, pass);
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					password = res.getString("pass");
				}
				else{
				}
				res.close();
				conex.desconectar();
				}		
				catch(SQLException e){
					System.out.println(e);
				}
		}
		return password;
	}
	public boolean actualizar(String usuario, String pass){
		Conexion conex = new Conexion();
		if(conex.conectado()){
			if(!(usuario.equals(""))){
				try{
					PreparedStatement consulta = conex.getConnection().prepareStatement("UPDATE usuarios SET usuario = ?, pass = ? WHERE Id = 1");
					consulta.setString(1, usuario);
					consulta.setString(2,pass);
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
						System.out.println(e);
				}
			}
			else{
				try{
					PreparedStatement consulta = conex.getConnection().prepareStatement("UPDATE usuarios SET pass = ? WHERE Id = 1");
					consulta.setString(1, pass);
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
						System.out.println(e);
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
