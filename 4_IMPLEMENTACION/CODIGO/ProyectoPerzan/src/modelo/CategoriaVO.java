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
	public CategoriaVO() {
		
	}
	public ObservableList<CategoriaVO> listarCategoria(){
		ObservableList<CategoriaVO> lista = FXCollections.observableArrayList();
		try {
			conex.conectar();
			PreparedStatement consulta= conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionarcategoria()");
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
			e.printStackTrace();
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			conex.desconectar();
		}
		return false;
	}
}
