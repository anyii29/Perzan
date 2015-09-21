package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Conexion;

public class LoginEmpDAO {
	private Conexion conex;
	private LoginEmpVO loginE;
	private Logger log;
	
	public LoginEmpDAO(){
		log = new Logger();
		loginE = new LoginEmpVO();
		conex = Conexion.getInstance();
	}

	public boolean iniciar(LoginEmpVO usuarioEmpVO) {
		boolean result = false;
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
					loginE.setNombre(res.getString("fnombre"));
					loginE.setId(res.getInt("fid"));
					result = true;
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
		return result;
	}
	public boolean fechaHora(){
		boolean res = false;
		try {
			conex.conectar();
			PreparedStatement consulta = conex.getConnection().prepareStatement("select fn_fechahorasistema()");
			ResultSet r = consulta.executeQuery();
			if(r.next()){
				res = r.getBoolean("fn_fechahorasistema");
			}
			consulta.close();
		} catch (Exception e) {
			log.printLog(e.getMessage(), this.getClass().toString());
			// TODO: handle exception
		}
		finally{
			conex.desconectar();
		}
		return res;
	}
	public LoginEmpVO obj() {
			return loginE;	
	}
}

