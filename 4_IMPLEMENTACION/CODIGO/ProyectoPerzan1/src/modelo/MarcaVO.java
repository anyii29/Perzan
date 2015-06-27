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
	}
	
	public ObservableList<Object> listarMarca(){
		conex = Conexion.getInstance();
		ObservableList<Object> lista = FXCollections.observableArrayList();
		try {
			conex.conectar();
			PreparedStatement consulta= conex.getConnection().prepareStatement("SELECT id, nombre FROM marca");
			ResultSet res = consulta.executeQuery();
			while(res.next()){
				String nombre =res.getString("nombre");
				int id = res.getInt("id");
				MarcaVO marcaVO= new MarcaVO();
				marcaVO.setId(id);
				marcaVO.setNombre(nombre);
				lista.add(marcaVO);
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
	

}
