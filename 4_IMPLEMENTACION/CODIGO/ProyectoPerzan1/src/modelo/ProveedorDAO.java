package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProveedorDAO implements iOp {
	Conexion conex = Conexion.getInstance();
	ObservableList<ProveedorVO> proveedores;
	@Override
	public boolean registrar(Object obj) {
		ProveedorVO  proveedorVO= new ProveedorVO();
		proveedorVO = (ProveedorVO) obj;
		boolean result = false;
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("INSERT INTO"
						+ "(id, nombre, apellido_paterno, apellido_materno, empresa, calle,"
						+ "avenida, numero, colonia, municipio, telefono, activo) VALUES"
						+ "(default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, default)");
				consulta.setString(1, proveedorVO.getNombre());
				consulta.setString(2, proveedorVO.getApPaterno());
				consulta.setString(3, proveedorVO.getApMaterno());
				consulta.setString(4, proveedorVO.getEmpresa());
				consulta.setInt(5, proveedorVO.getCalle());
				consulta.setInt(6, proveedorVO.getAvenida());
				consulta.setInt(7, proveedorVO.getNumero());
				consulta.setString(8, proveedorVO.getColonia());
				consulta.setString(9, proveedorVO.getMunicipio());
				consulta.setString(10, proveedorVO.getTelefono());
				int res = consulta.executeUpdate();
				if(res > 0){
					result = true;
				}
				consulta.close();
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
	@Override
	public boolean modificar(Object obj) {
		ProveedorVO  proveedorVO= new ProveedorVO();
		proveedorVO = (ProveedorVO) obj;
		boolean result = false;
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("UPDATE proveedor"
						+ "id = ?, nombre = ?, apellido_paterno = ?, apellido_materno = ?, empresa = ?, calle = ?,"
						+ "avenida = ?, numero = ?, colonia = ?, municipio = ?, telefono = ?"
						+ "WHERE id = ?");
				consulta.setInt(1, proveedorVO.getId());
				consulta.setString(2, proveedorVO.getNombre());
				consulta.setString(3, proveedorVO.getApPaterno());
				consulta.setString(4, proveedorVO.getApMaterno());
				consulta.setString(5, proveedorVO.getEmpresa());
				consulta.setInt(6, proveedorVO.getCalle());
				consulta.setInt(7, proveedorVO.getAvenida());
				consulta.setInt(8, proveedorVO.getNumero());
				consulta.setString(9, proveedorVO.getColonia());
				consulta.setString(10, proveedorVO.getMunicipio());
				consulta.setString(11, proveedorVO.getTelefono());
				consulta.setInt(12, proveedorVO.getId());
				int res = consulta.executeUpdate();
				if(res > 0){
					result = true;
				}
				consulta.close();
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
	@Override
	public boolean eliminar(int i) {
		boolean result = false;
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("UPDATE proveedor"
						+ "activo = 'n' WHERE id = ?");
				consulta.setInt(1, i);
				int res = consulta.executeUpdate();
				if(res > 0){
					result = true;
				}
				consulta.close();
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
	
	public ProveedorVO getLast(){
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT"
							+ "id, nombre, apellido_paterno, apellido_materno, empresa, calle,"
							+ "avenida, numero, colonia, municipio, telefono FROM proveedor"
							+ "where activo = 's' ORDER BY id LIMIT 1");
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					int id = res.getInt("id");
					String nombre = res.getString("nombre");
					String apPaterno = res.getString("apellido_paterno");
					String apMaterno = res.getString("apellido_materno");
					String empresa = res.getString("empresa");
					int calle = res.getInt("calle");
					int avenida = res.getInt("avenida");
					int numero = res.getInt("numero");
					String colonia = res.getString("colonia");
					String municipio = res.getString("municipio");
					String telefono = res.getString("telefono");
					ProveedorVO proveedorVO = new ProveedorVO(id, nombre, apPaterno, apMaterno,
							empresa, calle, avenida, numero, colonia, municipio, telefono);
					return proveedorVO;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public ObservableList<ProveedorVO> getDatos(){
		proveedores = FXCollections.observableArrayList();
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT"
							+ "id, nombre, apellido_paterno, apellido_materno, empresa, calle,"
							+ "avenida, numero, colonia, municipio, telefono FROM proveedor"
							+ "where activo = 's'");
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					int id = res.getInt("id");
					String nombre = res.getString("nombre");
					String apPaterno = res.getString("apellido_paterno");
					String apMaterno = res.getString("apellido_materno");
					String empresa = res.getString("empresa");
					int calle = res.getInt("calle");
					int avenida = res.getInt("avenida");
					int numero = res.getInt("numero");
					String colonia = res.getString("colonia");
					String municipio = res.getString("municipio");
					String telefono = res.getString("telefono");
					ProveedorVO proveedorVO = new ProveedorVO(id, nombre, apPaterno, apMaterno,
							empresa, calle, avenida, numero, colonia, municipio, telefono);
					proveedores.add(proveedorVO);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return proveedores;
	}
}
