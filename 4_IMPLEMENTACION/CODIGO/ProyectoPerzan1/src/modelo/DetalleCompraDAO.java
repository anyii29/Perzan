package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DetalleCompraDAO {
	Conexion conex = Conexion.getInstance();
	ObservableList<DetalleCompraVO> detalleCompras;
	
	public boolean insertar(DetalleCompraVO dCompraVO){
		boolean result = false;
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("INSERT INTO detallecompra"
						+ "(id, id_producto, id_compra, cantidad, precio_compra, precio_venta1, precio_venta2)"
						+ "VALUES (default,?,?,?,?,?,?");
				consulta.setInt(1, dCompraVO.getIdProducto());
				consulta.setInt(2, dCompraVO.getIdCompra());
				consulta.setInt(3, dCompraVO.getCantidad());
				consulta.setFloat(4, dCompraVO.getPrecioCompra());
				consulta.setFloat(5, dCompraVO.getPrecioVenta1());
				consulta.setFloat(6, dCompraVO.getPrecioVenta2());
				int res = consulta.executeUpdate();
				if(res > 0 ){
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
	
	public ObservableList<DetalleCompraVO> getDatos(){
		detalleCompras = FXCollections.observableArrayList();
		if(conex.conectado()){
			/*conex.conectar();
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT "
					+ "id, id_producto, id_compra, cantidad, precio_compra, precio_venta1, precio_venta2"
					+ "FROM detallecompra");*/
		}
		return detalleCompras;
	}
	
}