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
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_agregarventa(?,?,?)");
			consulta.setInt(1, ventaVO.getIdVendedor());
			consulta.setInt(2, ventaVO.getIdCliente());
			consulta.setFloat(3, ventaVO.getTotal());
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
				//PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT id, id_vendedor, id_cliente, total, fecha_hora FROM venta");
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionarventas()");
				ResultSet res = consulta.executeQuery();
					while(res.next()){
						int id= res.getInt("fid");
						String vendedor = res.getString("fvendedor");
						String cliente = res.getString("fcliente");
						float total = res.getFloat("ftotal");
						Timestamp fechaHora = res.getTimestamp("fecha_hora");
						VentaVO ventaVO = new VentaVO(id, vendedor, cliente, total, fechaHora);
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
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionarultimaventa();");
			ResultSet res = consulta.executeQuery();
			if(res.next()){
				int id= res.getInt("fid");
				String vendedor = res.getString("fvendedor");
				String cliente = res.getString("fcliente");
				float total = res.getFloat("ftotal");
				Timestamp fechaHora = res.getTimestamp("fecha_hora");
				VentaVO ventaVO = new VentaVO(id, vendedor, cliente, total, fechaHora);
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
