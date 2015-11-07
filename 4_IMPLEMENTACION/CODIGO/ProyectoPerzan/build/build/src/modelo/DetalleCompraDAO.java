package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DetalleCompraDAO {
	private Conexion conex = Conexion.getInstance();
	private ObservableList<DetalleCompraVO> detalleCompras;
	private Logger log;
	
	public DetalleCompraDAO(){
		log = new Logger();
	}
	public boolean insertar(DetalleCompraVO dCompraVO){
		boolean result = false;
		if(conex.conectado()){
			try {
				//conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fn_agregardetallecompra(?,?,?,?,?,?)");
				consulta.setInt(1, dCompraVO.getIdProducto());
				consulta.setInt(2, dCompraVO.getIdCompra());
				consulta.setInt(3, dCompraVO.getCantidad());
				consulta.setFloat(4, dCompraVO.getPrecioCompra());
				consulta.setFloat(5, dCompraVO.getPrecioVenta1());
				consulta.setFloat(6, dCompraVO.getPrecioVenta2());
				result = consulta.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				//conex.desconectar();
			}
		}
		return result;
	}
	
	public ObservableList<DetalleCompraVO> getDatos(){
		detalleCompras = FXCollections.observableArrayList();
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionardetallecompras()");
				//consulta.setInt(1, idCompra);
				ResultSet res = consulta.executeQuery();
				while(res.next()){
					int id = res.getInt("id");
					String producto = res.getString("producto");
					int cantidad = res.getInt("cantidad");
					float precioCompra = res.getFloat("precio_compra");
					float total = res.getFloat("total");
					float precioVenta1	= res.getFloat("precio_venta1");
					float precioVenta2 = res.getFloat("precio_venta2");
					DetalleCompraVO detComVO = new DetalleCompraVO(id, producto, cantidad,
							precioCompra, total, precioVenta1, precioVenta2);
					detalleCompras.add(detComVO);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}
		}
		return detalleCompras;
	}
	
}