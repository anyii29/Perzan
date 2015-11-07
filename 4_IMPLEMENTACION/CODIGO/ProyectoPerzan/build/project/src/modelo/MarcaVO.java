package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MarcaVO extends CategoriaVO{
	
	public MarcaVO(int id, String nombre) {
		super(id, nombre);
		this.id = id;
		this.nombre = nombre;
	}

	public MarcaVO() {
		id= 0;
		nombre = "";
	}
	
	public ObservableList<MarcaVO> listarMarca(boolean b){
		conex = Conexion.getInstance();
		ObservableList<MarcaVO> lista = FXCollections.observableArrayList();
		try {
			conex.conectar();
			PreparedStatement consulta;
			if(b){
				consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionarmarca()"); 
			}
			else{
				consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionareliminadomarca()"); 
			}			
			ResultSet res = consulta.executeQuery();
			while(res.next()){
				String nombre =res.getString("fnombre");
				int id = res.getInt("fid");
				MarcaVO marcaVO= new MarcaVO();
				marcaVO.setId(id);
				marcaVO.setNombre(nombre);
				lista.add(marcaVO);
			}
			res.close();
		} catch (SQLException e) {
			log.printLog(e.getMessage(), this.getClass().toString());
		}
		finally{
			conex.desconectar();
		}
		return lista;
	}
	
	public String toString(){
		return nombre;
	}
	
	public boolean ingresarMar(){
		try {
			if(conex.conectado()){
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("select fn_agregarmarca(?)");
				consulta.setString(1, this.getNombre());
				boolean res = consulta.execute();
				if(res){
					return true;
				}
				consulta.close();
			}
			
		} catch (Exception e) {
			message = e.getMessage();
			log.printLog(e.getMessage(), this.getClass().toString());
		}
		finally{
			conex.desconectar();
		}
		return false;
	}
	public boolean eliminarMar(){
		try {
			if(conex.conectado()){
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("select fn_eliminarmarca(?)");
				consulta.setInt(1, this.getId());
				boolean res = consulta.execute();
				if(res){
					return true;
				}
				consulta.close();
			}
			
		} catch (Exception e) {
			message = e.getMessage();
			log.printLog(e.getMessage(), this.getClass().toString());
		}
		finally{
			conex.desconectar();
		}
		return false;
	}
	public boolean modificarMar(){
		try {
			if(conex.conectado()){
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("select fn_modificarmarca(?,?)");
				consulta.setInt(1, this.getId());
				consulta.setString(2, this.getNombre());
				boolean res = consulta.execute();
				if(res){
					return true;
				}
				consulta.close();
			}
			
		} catch (Exception e) {
			message = e.getMessage();
			log.printLog(e.getMessage(), this.getClass().toString());
		}
		finally{
			conex.desconectar();
		}
		return false;
	}
	public boolean modificarEliminado(){
		try {
			if(conex.conectado()){
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("select fn_modificareliminadomarca(?)");
				consulta.setInt(1, this.id);
				boolean res = consulta.execute();
				if(res){
					return true;
				}
				consulta.close();
			}
			
		} catch (Exception e) {
			log.printLog(e.getMessage(), this.getClass().toString());
		}
		finally{
			conex.desconectar();
		}
		return false;
	}
}
