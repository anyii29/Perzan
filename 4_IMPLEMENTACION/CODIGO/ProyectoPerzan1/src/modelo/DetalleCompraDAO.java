package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	public ObservableList<DetalleCompraVO> getDatos(int idCompra){
		detalleCompras = FXCollections.observableArrayList();
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT "
						+ " detallecompra.id, concat(categoria.nombre,' ', producto.descripcion)"
						+ " as producto,"
						+ " detallecompra.cantidad, detallecompra.precio_compra,"
						+ " detallecompra.cantidad * detallecompra.precio_compra AS total,"
						+ " detallecompra.precio_venta1, detallecompra.precio_venta2"
						+ " FROM detallecompra"
						+ " inner JOIN producto ON producto.id = detallecompra.id_producto"
						+ " inner JOIN categoria ON categoria.id = producto.id_categoria"
						+ "	WHERE detallecompra.id_compra = ?");
				consulta.setInt(1, idCompra);
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
				e.printStackTrace();
			}
		}
		return detalleCompras;
	}
	
}