package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class VentaDAO {
	private boolean result;
	private int contt;
	private int idVenta;
	private Conexion conex = Conexion.getInstance();
	private ObservableList<VentaVO> ventas;
	private ObservableList<VentaDetVO> ventasDetalle;
	private Logger log;
	private DetalleVentaDAO dVentaDAO;
	private DetalleVentaVO dVO;
	private VentaDetVO ventaDetVO;
	
	public VentaDAO(){
		log = new Logger();
		dVentaDAO = new DetalleVentaDAO();
	}
	public boolean registrar(VentaVO ventaVO, ObservableList<DetalleVentaVO> dv) {
		boolean result = false;
		if(conex.conectado()){
			try{
				conex.conectar();
				conex.getConnection().setAutoCommit(false);				
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_agregarventa(?,?,?)");
				consulta.setInt(1, ventaVO.getIdVendedor());
				consulta.setInt(2, ventaVO.getIdCliente());
				consulta.setFloat(3, ventaVO.getTotal());
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					idVenta = res.getInt("fn_agregarventa");
					result = true;
				}
				for(int i = 0; i < dv.size(); i++ ){
					dVO = dv.get(i);
					dVO.setIdVenta(idVenta);
					if(!dVentaDAO.insertar(dVO)){
						result = false;
					}
				}
				conex.getConnection().commit();
				conex.getConnection().setAutoCommit(true);
				consulta.close();
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
					log.printLog(e.getMessage(), this.getClass().toString());
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
				log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}
		}
		return null;
	}
	public ObservableList<VentaDetVO> getDatosDetalle() {
		// TODO Auto-generated method stubventas = FXCollections.observableArrayList();
		ventasDetalle = FXCollections.observableArrayList();
		if(conex.conectado()){
			try{
				conex.conectar();
				//PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT id, id_vendedor, id_cliente, total, fecha_hora FROM venta");
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionardetventas()");
				ResultSet res = consulta.executeQuery();
					while(res.next()){
						int id= res.getInt("id");
						String empleado = res.getString("empleado");
						String cliente = res.getString("cliente");
						String producto = res.getString("producto");
						int cantidad = res.getInt("cantidad");
						float precio = res.getFloat("precio");
						float total = res.getFloat("total");
						Timestamp fechaHora = res.getTimestamp("fecha_hora");
						ventaDetVO = new VentaDetVO(id, empleado, cliente, producto, cantidad, precio, total, fechaHora);
						ventasDetalle.add(ventaDetVO);
					}
					consulta.close();
				}
			catch(SQLException e){
					log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}
		}
		return ventasDetalle;
	}
}
