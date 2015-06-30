package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Conexion;

public class LoginEmpDAO {
	Conexion conex;
	LoginEmpVO loginE = new LoginEmpVO();

	public boolean iniciar(LoginEmpVO usuarioEmpVO) {
		boolean result = false;
		conex = Conexion.getInstance();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_login(?,?,?)");
				consulta.setString(1,usuarioEmpVO.getUsuario());
				consulta.setString(2,usuarioEmpVO.getPassword());
				consulta.setString(3,usuarioEmpVO.getTipo());
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					loginE.setUsuario(res.getString("fusuario"));
					loginE.setPassword(res.getString("fpassword"));
					loginE.setTipo(res.getString("ftipo"));
					result = true;
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
		return result;
	}
	
	public LoginEmpVO obj() {
			return loginE;	
	}
}

