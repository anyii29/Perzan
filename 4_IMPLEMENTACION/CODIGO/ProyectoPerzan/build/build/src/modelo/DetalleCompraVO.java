package modelo;

public class DetalleCompraVO {
	private int id, cantidad, idProducto, idCompra;
	private String producto, nombre;
	private float precioCompra,total, precioVenta1, precioVenta2;
	
	public DetalleCompraVO(int id, String producto, int cantidad,
			float precioCompra, float total, float precioVenta1 , float precioVenta2){
		this.id = id;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioCompra = precioCompra;
		this.total = total;
		this.precioVenta1 = precioVenta1;
		this.precioVenta2 = precioVenta2;
	}
	public DetalleCompraVO(int id, int idProducto, int idCompra,
			float precioCompra, float total, float precioVenta1 , float precioVenta2){
		this.id = id;
		this.idProducto = idProducto;
		this.idCompra = idCompra;
		this.precioCompra = precioCompra;
		this.total = total;
		this.precioVenta1 = precioVenta1;
		this.precioVenta2 = precioVenta2;
	}

	public DetalleCompraVO() {
		// TODO Auto-generated constructor stub
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
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the idProducto
	 */
	public int getIdProducto() {
		return idProducto;
	}

	/**
	 * @param idProducto the idProducto to set
	 */
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	/**
	 * @return the idCompra
	 */
	public int getIdCompra() {
		return idCompra;
	}

	/**
	 * @param idCompra the idCompra to set
	 */
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	/**
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * @return the precioCompra
	 */
	public float getPrecioCompra() {
		return precioCompra;
	}

	/**
	 * @param precioCompra the precioCompra to set
	 */
	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}

	/**
	 * @return the total
	 */
	public float getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(float total) {
		this.total = total;
	}

	/**
	 * @return the precioVenta1
	 */
	public float getPrecioVenta1() {
		return precioVenta1;
	}

	/**
	 * @param precioVenta1 the precioVenta1 to set
	 */
	public void setPrecioVenta1(float precioVenta1) {
		this.precioVenta1 = precioVenta1;
	}

	/**
	 * @return the precioVenta2
	 */
	public float getPrecioVenta2() {
		return precioVenta2;
	}

	/**
	 * @param precioVenta2 the precioVenta2 to set
	 */
	public void setPrecioVenta2(float precioVenta2) {
		this.precioVenta2 = precioVenta2;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}