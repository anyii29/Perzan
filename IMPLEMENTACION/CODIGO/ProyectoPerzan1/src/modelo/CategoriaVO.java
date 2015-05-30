package modelo;

public class CategoriaVO {
	protected int id;
	protected String nombre;
	
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
}
