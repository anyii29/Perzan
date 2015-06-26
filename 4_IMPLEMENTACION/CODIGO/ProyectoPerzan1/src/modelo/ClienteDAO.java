package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClienteDAO{
	Conexion conex = Conexion.getInstance();
	ObservableList<ClienteVO> clientes;

	public boolean registrar(Object obj) {
		ClienteVO clienteVO = new ClienteVO();
		clienteVO = (ClienteVO) obj;
		boolean result = false;
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("Insert into cliente"
						+ "(id, nombre, apellido_paterno, apellido_materno, calle, avenida, numero, colonia"
						+ "municipio, referencia) VALUES (default,?,?,?,?,?,?,?,?,?)");
				consulta.setString(1, clienteVO.getNombre());
				consulta.setString(2, clienteVO.getApPaterno());
				consulta.setString(3, clienteVO.getApMaterno());
				consulta.setInt(4, clienteVO.getCalle());
				consulta.setInt(5, clienteVO.getAvenida());
				consulta.setInt(6, clienteVO.getNumero());
				consulta.setString(7, clienteVO.getColonia());
				consulta.setString(8, clienteVO.getMunicipio());
				consulta.setString(9, clienteVO.getReferencia());
				int res = consulta.executeUpdate();
				if(res > 0){
					result = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
				PreparedStatement consulta = conex.getConnection().prepareStatement("UPDATE compra set id = ?, nombre = ?, apellido_paterno = ?, apellido_materno = ?, calle = ?, avenida = ?, numero = ?, colonia = ?"
						+ "municipio = ?, referencia = ? WHERE id = ?");
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
				consulta.setInt(11, clienteVO.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{	
				conex.desconectar();
			}
		}
		return result;
	}
	
	public ObservableList<ClienteVO> getDatos(){
		clientes = FXCollections.observableArrayList();
		ClienteVO clienteVO;
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT "
						+ "id, nombre, apellido_paterno, apellido_materno, calle, avenida, numero, colonia"
							+ "municipio, referencia FROM cliente");
				ResultSet res = consulta.executeQuery();
				while(res.next()){
					int id = res.getInt("id");
					String nombre = res.getString("nombre");
					String apellidoPaterno = res.getString("apellido_paterno");
					String apellidoMaterno = res.getString("apellido_materno");
					int calle = res.getInt("calle");
					int avenida = res.getInt("avenida");
					int numero = res.getInt("numero");
					String colonia = res.getString("colonia");
					String municipio = res.getString("municipio");
					String referencia = res.getString("referencia");
					clienteVO = new ClienteVO(id, nombre, apellidoPaterno, apellidoMaterno, calle, avenida,
							numero, colonia, municipio, referencia);
					clientes.add(clienteVO);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return clientes;
		
	}

}
