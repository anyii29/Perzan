package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Conexion;

public class LoginEmpDAO {
	boolean result;
	Conexion conex;
	LoginEmpVO loginE = new LoginEmpVO();

	public boolean iniciar(LoginEmpVO usuarioEmpVO) {
		conex = Conexion.getInstance();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT usuario,password,tipo from empleado where usuario = ? and password = ? and tipo = ? and activo = 's'");
				consulta.setString(1,usuarioEmpVO.getUsuario());
				consulta.setString(2,usuarioEmpVO.getPassword());
				consulta.setString(3,usuarioEmpVO.getTipo());
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					loginE.setUsuario(res.getString("usuario"));
					loginE.setPassword(res.getString("password"));
					loginE.setTipo(res.getString("tipo"));
					result = true;
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
	
	public LoginEmpVO obj() {
			return loginE;	
	}
}

