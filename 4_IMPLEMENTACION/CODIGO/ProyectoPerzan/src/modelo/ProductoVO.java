package modelo;

public class ProductoVO {
	private int id;
	private String nombre;
	private double precio;
	private int cantidad;
	
	public ProductoVO(){
	}
	
	public ProductoVO(int id, String nombre, double precio, int cantidad){
		this.id= id;
		this.nombre= nombre;
		this.precio= precio;
		this.cantidad= cantidad;		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
