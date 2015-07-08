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
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_agregarproveedor(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
				boolean res = consulta.execute();
				if(res){
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
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_modificaproveedor(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
				boolean res = consulta.execute();
				if(res){
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
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_eliminaproveedor(?)");
				consulta.setInt(1, i);
				boolean res = consulta.execute();
				if(res){
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
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionarproveedor()");
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					int id = res.getInt("fid");
					String nombre = res.getString("fnombre");
					String apPaterno = res.getString("fapellido_paterno");
					String apMaterno = res.getString("fapellido_materno");
					String empresa = res.getString("fempresa");
					int calle = res.getInt("fcalle");
					int avenida = res.getInt("favenida");
					int numero = res.getInt("fnumero");
					String colonia = res.getString("fcolonia");
					String municipio = res.getString("fmunicipio");
					String telefono = res.getString("ftelefono");
					ProveedorVO proveedorVO = new ProveedorVO(id, nombre, apPaterno, apMaterno,
							empresa, calle, avenida, numero, colonia, municipio, telefono);
					return proveedorVO;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				conex.desconectar();
			}
		}
		return null;
	}
	public ObservableList<ProveedorVO> getDatos(){
		proveedores = FXCollections.observableArrayList();
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionarproveedores()");
				ResultSet res = consulta.executeQuery();
				while(res.next()){
					int id = res.getInt("fid");
					String nombre = res.getString("fnombre");
					String apPaterno = res.getString("fapellido_paterno");
					String apMaterno = res.getString("fapellido_materno");
					String empresa = res.getString("fempresa");
					int calle = res.getInt("fcalle");
					int avenida = res.getInt("favenida");
					int numero = res.getInt("fnumero");
					String colonia = res.getString("fcolonia");
					String municipio = res.getString("fmunicipio");
					String telefono = res.getString("ftelefono");
					ProveedorVO proveedorVO = new ProveedorVO(id, nombre, apPaterno, apMaterno,
							empresa, calle, avenida, numero, colonia, municipio, telefono);
					proveedores.add(proveedorVO);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				conex.desconectar();
			}
		}
		return proveedores;
	}
}
