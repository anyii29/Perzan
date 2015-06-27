package modelo;

public class ProductoVO {
	private MarcaVO marca;
	private CategoriaVO categoria;
	private int id;
	private String descripcion;
	private double precio1;
	private double precio2;
	private int stock;
	private int stockMax;
	private int stockMin;
	private String tipo;
	
	public ProductoVO(){
	}
	
	public ProductoVO(int id, CategoriaVO categoria, String descripcion, MarcaVO marca,
			double precio1, double precio2, int stock, int stockMax, int stockMin, String tipo){
		this.id= id;
		this.descripcion = descripcion;
		this.categoria= categoria;
		this.marca= marca;
		this.precio1= precio1;
		this.precio2= precio2;
		this.stock = stock;
		this.stockMax = stockMax;
		this.stockMin = stockMin;
		this.tipo = tipo;
	}
	/*public ProductoVO(int id, String nombre, int  idCategoria, int idMarca,
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
	}*/

	/**
	 * @return the marca
	 */
	public MarcaVO getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(MarcaVO marca) {
		this.marca = marca;
	}

	/**
	 * @return the categoria
	 */
	public CategoriaVO getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(CategoriaVO categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the precio1
	 */
	public double getPrecio1() {
		return precio1;
	}

	/**
	 * @param precio1 the precio1 to set
	 */
	public void setPrecio1(double precio1) {
		this.precio1 = precio1;
	}

	/**
	 * @return the precio2
	 */
	public double getPrecio2() {
		return precio2;
	}

	/**
	 * @param precio2 the precio2 to set
	 */
	public void setPrecio2(double precio2) {
		this.precio2 = precio2;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return the stockMax
	 */
	public int getStockMax() {
		return stockMax;
	}

	/**
	 * @param stockMax the stockMax to set
	 */
	public void setStockMax(int stockMax) {
		this.stockMax = stockMax;
	}

	/**
	 * @return the stockMin
	 */
	public int getStockMin() {
		return stockMin;
	}

	/**
	 * @param stockMin the stockMin to set
	 */
	public void setStockMin(int stockMin) {
		this.stockMin = stockMin;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}
