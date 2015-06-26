package modelo;

import java.sql.PreparedStatement;
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
	
	public ObservableList<DetalleVentaVO> getDatos(){
		detalleVentas = FXCollections.observableArrayList();
		
		return detalleVentas;
	}

}
