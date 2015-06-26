package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VentaDAO {
	boolean result;
	private int contt;
	private int dato;
	private Conexion conex = Conexion.getInstance();
	ObservableList<VentaVO> ventas;
	public boolean registrar(VentaVO ventaVO) {
		boolean result = false;
		if(conex.conectado()){
			try{
				conex.conectar();
			PreparedStatement consulta = conex.getConnection().prepareStatement("INSERT INTO venta (id, id_vendedor, id_cliente, fecha_hora) VALUES (default,?,?,default)");
			consulta.setInt(1, ventaVO.getIdVendedor());
			consulta.setInt(1, ventaVO.getIdCliente());
			int res = consulta.executeUpdate();
			if(res > 0){
				result= true;	
			}
			consulta.close();
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

	public ObservableList<VentaVO> getDatos(){
		ventas = FXCollections.observableArrayList();
		if(conex.conectado()){
			try{
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT id, id_vendedor, id_cliente, fecha_hora FROM venta");
				ResultSet res = consulta.executeQuery();
					while(res.next()){
						int id= res.getInt("id");
						int idVendedor = res.getInt("id_vendedor");
						int idCliente = res.getInt("id_cliente");
						Timestamp fechaHora = res.getTimestamp("fecha_hora");
						VentaVO ventaVO = new VentaVO(id, idVendedor, idCliente, fechaHora);
						ventas.add(ventaVO);
					}
					consulta.close();
				}
			catch(SQLException e){
					e.printStackTrace();
			}
			finally{
				conex.desconectar();
			}
		}
		return ventas;
	}
	public VentaVO lastInsert(){		
		if(conex.conectado()){
			try{
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT id, id_vendedor, id_cliente, fecha_hora FROM venta ORDER BY id DESC LIMIT 1");
			ResultSet res = consulta.executeQuery();
			if(res.next()){
				int id= res.getInt("id");
				int idVendedor = res.getInt("id_vendedor");
				int idCliente = res.getInt("id_cliente");
				Timestamp fechaHora = res.getTimestamp("fecha_hora");
				VentaVO ventaVO = new VentaVO(id, idVendedor, idCliente, fechaHora);
				return ventaVO;
			}
			consulta.close();
			}		
		catch(SQLException e){
				e.printStackTrace();
			}
			finally{
				conex.desconectar();
			}
		}
		return null;
	}

}
