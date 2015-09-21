package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoriaVO {
	protected int id;
	protected String nombre;
	protected Conexion conex = Conexion.getInstance();
	protected Logger log;
	protected String message;
	
	public CategoriaVO(){
		log = new Logger();
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public CategoriaVO(int id, String nombre){
		this.id = id;
		this.nombre = nombre;
	}
	public ObservableList<CategoriaVO> listarCategoria(boolean d){
		ObservableList<CategoriaVO> lista = FXCollections.observableArrayList();
		try {
			conex.conectar();
			PreparedStatement consulta;
			if(d){
				consulta= conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionarcategoria()");
			}
			else{
				consulta= conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionareliminadocategoria()");
			}
			ResultSet res = consulta.executeQuery();
			while(res.next()){
				String nombre =res.getString("fnombre");
				int id = res.getInt("fid");
				CategoriaVO categoriaVO = new CategoriaVO();
				categoriaVO.setId(id);
				categoriaVO.setNombre(nombre);
				lista.add(categoriaVO);
			}
			res.close();
		} catch (SQLException e) {
			message = e.getSQLState();
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
	
	public boolean ingresarCat(){
		try {
			if(conex.conectado()){
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("select fn_agregarcategoria(?)");
				consulta.setString(1, this.getNombre());
				boolean res = consulta.execute();
				if(res){
					return true;
				}
				consulta.close();
			}
			
		} catch (SQLException e) {
			message = e.getMessage().intern();
			System.out.println(e.getMessage().intern());
			log.printLog(e.getMessage(), this.getClass().toString());
		}
		finally{
			conex.desconectar();
		}
		return false;
	}
	public boolean eliminarCat(){
		try {
			if(conex.conectado()){
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("select fn_eliminarcategoria(?)");
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
	public boolean modificarCat(){
		try {
			if(conex.conectado()){
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("select fn_modificarcategoria(?,?)");
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
				PreparedStatement consulta = conex.getConnection().prepareStatement("select fn_modificareliminadocategoria(?)");
				consulta.setInt(1, this.id);
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
	public String getMesssage(){
		return this.message;
	}
}
