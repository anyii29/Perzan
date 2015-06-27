package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DetalleVentaDAO {
	Conexion conex = Conexion.getInstance();
	ObservableList<DetalleVentaVO> detalleVentas;
	
	public boolean insertar(DetalleVentaVO dVentaVO){
		boolean result = false;
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("INSERT INTO detalleventa"
						+ "(id, id_producto, precio, cantidad, id_venta) VALUES (default, ?, ?, ?, ?)");
				consulta.setInt(1, dVentaVO.getIdProducto());
				consulta.setFloat(2, dVentaVO.getPrecio());
				consulta.setInt(3, dVentaVO.getCantidad());
				consulta.setInt(4, dVentaVO.getIdVenta());
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
	
	public ObservableList<DetalleVentaVO> getDatos(int idVenta){
		detalleVentas = FXCollections.observableArrayList();
		DetalleVentaVO detVenVO;
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT detalleventa.id,"
						+ " concat(categoria.nombre, ' ', producto.descripcion) as producto,"
						+ " detalleventa.precio,    detalleventa.cantidad,"
						+ " detalleventa.precio * detalleventa.cantidad AS total FROM detalleventa"
						+ " JOIN producto ON producto.id = detalleventa.id_producto"
						+ " JOIN categoria ON categoria.id = producto.id_categoria "
						+ " where detalleventa.id_venta = ?");
				consulta.setInt(1, idVenta);
				ResultSet res = consulta.executeQuery();
				while(res.next()){
					int id = res.getInt("id");
					String producto = res.getString("producto");
					float precio = res.getFloat("precio");
					int cantidad = res.getInt("cantidad");
					float total = res.getFloat("total");
					detVenVO = new DetalleVentaVO(id, producto, precio, cantidad, total);
					detalleVentas.add(detVenVO);
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
		return detalleVentas;
	}

}
