package modelo;

public class ProductoVO {
	private int id;
	private String categoria;
	private String marca;
	private int idCategoria;
	private int idMarca;
	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	private String nombre;
	private double precio;
	private double precio2;
	private int stock;
	private int stockMax;
	private int stockMin;
	private String tipo;
	
	
	public ProductoVO(){
	}
	
	public ProductoVO(int id, String nombre, String categoria, String marca,
			double precio, double precio2, int stock, int stockMax, int stockMin, String tipo){
		this.id= id;
		this.nombre= nombre;
		this.categoria= categoria;
		this.marca= marca;
		this.precio= precio;
		this.precio= precio2;
		this.stock = stock;
		this.stockMax = stockMax;
		this.stockMin = stockMin;
		this.tipo = tipo;
	}
	public ProductoVO(int id, String nombre, int  idCategoria, int idMarca,
			double precio, double precio2, int stock, int stockMax, int stockMin, String tipo){
		this.id= id;
		this.nombre= nombre;
		this.idCategoria= idCategoria;
		this.idMarca= idMarca;
		this.precio= precio;
		this.precio2= precio2;
		this.stock = stock;
		this.stockMax = stockMax;
		this.stockMin = stockMin;
		this.tipo = tipo;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
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

	public double getPrecio2() {
		return precio2;
	}

	public void setPrecio2(double precio2) {
		this.precio2 = precio2;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStockMax() {
		return stockMax;
	}

	public void setStockMax(int stockMax) {
		this.stockMax = stockMax;
	}

	public int getStockMin() {
		return stockMin;
	}

	public void setStockMin(int stockMin) {
		this.stockMin = stockMin;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
