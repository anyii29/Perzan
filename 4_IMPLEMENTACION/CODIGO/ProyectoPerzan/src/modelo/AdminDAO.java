package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Conexion;

public class AdminDAO {
	boolean result;
	private Conexion conex;
	private Logger log;
	public AdminDAO(){
		log = new Logger();
	}
	public boolean user(String pass) {
		boolean yes = false;
		conex = Conexion.getInstance();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_adminpassword(?)");
				consulta.setString(1, pass);
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					String password = res.getString(0);
					
					yes = true;
				}
				res.close();
				}		
				catch(SQLException e){
					log.printLog(e.getMessage(), this.getClass().toString());
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
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_modificarpassword(?)");
				consulta.setString(1,	pass);
				boolean res = consulta.execute();
				if(res){
					result= true;	
				}
				else{
					result = false;
				}
				consulta.close();
				}		
			catch(SQLException e){
					log.printLog(e.getMessage(), this.getClass().toString());
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
