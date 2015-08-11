package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClienteDAO{
	private Conexion conex;
	private ObservableList<ClienteVO> clientes;
	private Logger log;
	
	public ClienteDAO(){
		log = new Logger();
		conex = Conexion.getInstance();
	}

	public boolean registrar(Object obj) {
		ClienteVO clienteVO = new ClienteVO();
		clienteVO = (ClienteVO) obj;
		boolean result = false;
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_agregarcliente(?,?,?,?,?,?,?,?,?)");
				consulta.setString(1, clienteVO.getNombre());
				consulta.setString(2, clienteVO.getApPaterno());
				consulta.setString(3, clienteVO.getApMaterno());
				consulta.setInt(4, clienteVO.getCalle());
				consulta.setInt(5, clienteVO.getAvenida());
				consulta.setInt(6, clienteVO.getNumero());
				consulta.setString(7, clienteVO.getColonia());
				consulta.setString(8, clienteVO.getMunicipio());
				consulta.setString(9, clienteVO.getReferencia());
				result = consulta.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}	
		}
		return result;
	}

	public boolean modificar(Object obj) {
		ClienteVO clienteVO = new ClienteVO();
		clienteVO = (ClienteVO) obj;
		boolean result = false;
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_modificarcliente(?,?,?,?,?,?,?,?,?,?)");
				consulta.setInt(1, clienteVO.getId());
				consulta.setString(2, clienteVO.getNombre());
				consulta.setString(3, clienteVO.getApPaterno());
				consulta.setString(4, clienteVO.getApMaterno());
				consulta.setInt(5, clienteVO.getCalle());
				consulta.setInt(6, clienteVO.getAvenida());
				consulta.setInt(7, clienteVO.getNumero());
				consulta.setString(8, clienteVO.getColonia());
				consulta.setString(9, clienteVO.getMunicipio());
				consulta.setString(10, clienteVO.getReferencia());
				if(consulta.execute()){
					result = true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{	
				conex.desconectar();
			}
		}
		return result;
	}
	
	public boolean eliminar(int id){
		boolean result = false;
		conex.conectar();
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("Select fn_eliminarcliente(?)");
			consulta.setInt(1, id);
			if(consulta.execute()){
				result = true;
			}
		} catch (Exception e) {
			log.printLog(e.getMessage(), this.getClass().toString());
			// TODO: handle exception
		}
		return result;
	}
	
	public ObservableList<ClienteVO> getDatos(boolean d){
		clientes = FXCollections.observableArrayList();
		ClienteVO clienteVO;
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta;
				if(d){
					consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionarclientes()");
				}
				else{
					consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionareliminadocliente()");
				}
				ResultSet res = consulta.executeQuery();
				while(res.next()){
					int id = res.getInt("fid");
					String nombre = res.getString("fnombre");
					String apellidoPaterno = res.getString("fapellido_paterno");
					String apellidoMaterno = res.getString("fapellido_materno");
					int calle = res.getInt("fcalle");
					int avenida = res.getInt("favenida");
					int numero = res.getInt("fnumero");
					String colonia = res.getString("fcolonia");
					String municipio = res.getString("fmunicipio");
					String referencia = res.getString("freferencia");
					clienteVO = new ClienteVO(id, nombre, apellidoPaterno, apellidoMaterno, calle, avenida,
							numero, colonia, municipio, referencia);
					clientes.add(clienteVO);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}			
		}
		return clientes;
		
	}
	
	public ClienteVO lastInsert(){
		ClienteVO clienteVO = null;
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionarultimocliente()");
				ResultSet res = consulta.executeQuery();
				while(res.next()){
					int id = res.getInt("fid");
					String nombre = res.getString("fnombre");
					String apellidoPaterno = res.getString("fapellido_paterno");
					String apellidoMaterno = res.getString("fapellido_materno");
					int calle = res.getInt("fcalle");
					int avenida = res.getInt("favenida");
					int numero = res.getInt("fnumero");
					String colonia = res.getString("fcolonia");
					String municipio = res.getString("fmunicipio");
					String referencia = res.getString("freferencia");
					clienteVO = new ClienteVO(id, nombre, apellidoPaterno, apellidoMaterno, calle, avenida,
							numero, colonia, municipio, referencia);
					return clienteVO;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}			
		}
		return clienteVO;
		
	}
	public boolean modificarEliminado(int id){
		try {
			if(conex.conectado()){
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("select fn_modificareliminadocliente(?)");
				consulta.setInt(1, id);
				boolean res = consulta.execute();
				if(res){
					return true;
				}
				consulta.close();
			}
			
		} catch (Exception e) {
			log.printLog(e.getMessage(), this.getClass().toString());
		}
		finally{
			conex.desconectar();
		}
		return false;
	}

}
