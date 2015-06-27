package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CompraDAO {
	private Conexion conex = Conexion.getInstance();
	ObservableList<CompraVO> compras;	
		
	public boolean insertar(Object obj){
		CompraVO compraVO = new CompraVO();
		compraVO = (CompraVO) obj;
		boolean result = false;
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_agregarcompra(?,?)");
				consulta.setInt(1, compraVO.getIdProvedor());
				consulta.setFloat(2, compraVO.getTotal());
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
	public boolean actualizar(int id){
		boolean result = false;
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta =conex.getConnection().prepareStatement("SELECT fn_modificarcompra(?)");
				consulta.setInt(1, id);
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
	// pendiente
	// ***************************************************************************************************
	public ObservableList<CompraVO> getDatos(){
		CompraVO compraVO;
		compras = FXCollections.observableArrayList();
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * from fn_seleccionarcompras()");
				ResultSet res = consulta.executeQuery();
				while(res.next()){
					int id = res.getInt("id");
					String empresa = res.getString("empresa");
					//int idProvedor = res.getInt("id_proveedor");
					float total = res.getFloat("total");
					Date fechaPedido = res.getDate("fecha_pedido");
					Timestamp fechaRecepcion = res.getTimestamp("fecha_recepcion");
					compraVO = new CompraVO(id, empresa, total, fechaPedido, fechaRecepcion);
					compras.add(compraVO);
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
		return compras;
	}
	public CompraVO getLast(){
		CompraVO compraVO;
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_selecionarultimacompra()");
				ResultSet res = consulta.executeQuery();
				while(res.next()){
					int id = res.getInt("id");
					int idProvedor = res.getInt("id_proveedor");
					float total = res.getFloat("total");
					Date fechaPedido = res.getDate("fecha_pedido");
					Timestamp fechaRecepcion = res.getTimestamp("fecha_recepcion");
					compraVO = new CompraVO(id, idProvedor, total, fechaPedido, fechaRecepcion);
					return compraVO;
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
		return null;
	}

}
