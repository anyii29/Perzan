package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class CompraDAO {
	private Conexion conex = Conexion.getInstance();
	private ObservableList<CompraVO> compras;	
	private ObservableList<CompraDetVO> comprasDetalle;
	private Logger log;
	private int idCompra;
	private DetalleCompraDAO dCompraDAO;
	private DetalleCompraVO dCompraVO;
	
	public CompraDAO(){
		log = new Logger();
		dCompraDAO = new DetalleCompraDAO();
	}
	public boolean insertar(Object obj, ObservableList<DetalleCompraVO> dc){
		CompraVO compraVO = new CompraVO();
		compraVO = (CompraVO) obj;
		boolean result = false;
		if(conex.conectado()){
			try {
				conex.conectar();
				conex.getConnection().setAutoCommit(false);
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_agregarcompra(?,?,?)");
				consulta.setInt(1, compraVO.getIdEmpleado());
				consulta.setInt(2, compraVO.getIdProveedor());
				consulta.setFloat(3, compraVO.getTotal());
				ResultSet res = consulta.executeQuery();
				if(res.next()){
					idCompra = res.getInt("fn_agregarcompra");
					result = true;
				}
				for(int i = 0; i < dc.size();i++){
					dCompraVO = dc.get(i);
					dCompraVO.setIdCompra(idCompra);
					if(!dCompraDAO.insertar(dCompraVO)){
						result = false;
					}
				}
				conex.getConnection().commit();
				conex.getConnection().setAutoCommit(true);
				consulta.close();
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
	public boolean actualizar(int id){
		boolean result = false;
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta =conex.getConnection().prepareStatement("SELECT fn_modificarcompra(?)");
				consulta.setInt(1, id);
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
					String empleado = res.getString("empleado");
					//int idProvedor = res.getInt("id_proveedor");
					float total = res.getFloat("total");
					Date fechaPedido = res.getDate("fecha_pedido");
					Timestamp fechaRecepcion = res.getTimestamp("fecha_recepcion");
					compraVO = new CompraVO(id, empleado, empresa, total, fechaPedido, fechaRecepcion);
					compras.add(compraVO);
				}
				consulta.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.printLog(e.getMessage(), this.getClass().toString());
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
				log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}
		}
		return null;
	}
	public ObservableList<CompraDetVO> getDatosDetalle() {
		// TODO Auto-generated method stub
		CompraDetVO compraDetVO;
		comprasDetalle = FXCollections.observableArrayList();
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * from fn_seleccionardetcompras()");
				ResultSet res = consulta.executeQuery();
				while(res.next()){
					int id = res.getInt("id");
					String empresa = res.getString("empresa");
					String empleado = res.getString("empleado");
					String producto = res.getString("producto");
					int cantidad = res.getInt("cantidad");
					float precio = res.getFloat("precio");
					//int idProvedor = res.getInt("id_proveedor");
					float total = res.getFloat("total");
					Date fechaPedido = res.getDate("fecha_pedido");
					Timestamp fechaRecepcion = res.getTimestamp("fecha_recepcion");
					compraDetVO = new CompraDetVO(id, empresa, empleado, producto, cantidad, precio, total, fechaPedido, fechaRecepcion);
					comprasDetalle.add(compraDetVO);
				}
				consulta.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}
		}
		return comprasDetalle;
	}

}
