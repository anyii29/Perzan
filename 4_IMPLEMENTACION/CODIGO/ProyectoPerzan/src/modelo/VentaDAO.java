package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentaDAO {
	boolean result;
	private int contt;
	private int dato;
	public boolean registrar(VentaVO ventaVO) {
		Conexion conex = new Conexion();
		if(conex.conectado()){
			try{
			PreparedStatement consulta = conex.getConnection().prepareStatement("INSERT INTO ventas VALUES (?,?,?,?,?)");
			consulta.setNull(1,0);
			consulta.setString(2, ventaVO.getCliente());
			consulta.setDouble(3, ventaVO.getImporte());
			consulta.setString(4, ventaVO.getVendedor());
			consulta.setString(5, ventaVO.getFecha());
				
			int res = consulta.executeUpdate();
			if(res > 0){
				result= true;	
			}
			else{
				result = false;
			}PreparedStatement last = conex.getConnection().prepareStatement("SELECT LAST_INSERT_ID()");
			ResultSet lastIn = last.executeQuery();
			if(lastIn.next()){
				dato = lastIn.getInt(1);
				System.out.println(dato);
			}
			last.close();
			consulta.close();
			conex.desconectar();
			}		
			catch(SQLException e){
				System.out.println(e);
			}
		}
		if(result){
			return true;
		}
		else{
			return false;
		}		
	}

	public VentaVO[] getDatos(){
		int contador;
		VentaVO[] datos;
		Conexion conex = new Conexion();
		if(conex.conectado()){
			try{
				PreparedStatement count = conex.getConnection().prepareStatement("SELECT COUNT(DISTINCT id) FROM ventas");
				ResultSet cont = count.executeQuery();
				if(cont.next()){
					contt = cont.getInt(1);
				}
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM ventas");
				ResultSet res = consulta.executeQuery();
				datos = new VentaVO[contt];
					contador = 0;
					while(res.next()){
						int id= res.getInt("id");
						String cliente = res.getString("cliente");
						double importe = res.getDouble("importe");
						String vendedor = res.getString("vendedor");
						String fecha = res.getString("fecha");
						VentaVO ventaVO = new VentaVO(id, cliente, importe, vendedor, fecha);
						datos[contador] = ventaVO;
						contador++;
					}
					return datos;
				}		
			catch(SQLException e){
					System.out.println(e);
			}
		}
		return null;
	}
	public VentaVO lastInsert(){		
		Conexion conex = new Conexion();
		if(conex.conectado()){
			try{
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM ventas WHERE `id` = ?");
			consulta.setInt(1, dato);
			ResultSet res = consulta.executeQuery();
			if(res.next()){
				int id= res.getInt("id");
				String cliente = res.getString("cliente");
				double importe = res.getDouble("importe");
				String vendedor = res.getString("vendedor");
				String fecha = res.getString("fecha");
				VentaVO ventaVO = new VentaVO(id, cliente, importe, vendedor, fecha);
				return ventaVO;
			}
			consulta.close();
			conex.desconectar();
			}		
		catch(SQLException e){
				System.out.println(e);
			}
		}
		return null;
	}

}
