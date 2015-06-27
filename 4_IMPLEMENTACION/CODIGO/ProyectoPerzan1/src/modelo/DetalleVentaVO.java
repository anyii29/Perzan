package modelo;

public class DetalleVentaVO {
	private int id, idProducto, cantidad, idVenta;
	private String producto;
	private float precio, total;
	
	public DetalleVentaVO(int id, int idProducto, int cantidad, int idVenta, float precio){
		this.id = id;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.idVenta = idVenta;
		this.precio = precio;
	}
	public DetalleVentaVO(int id, String producto, float precio,int cantidad, float total){
		this.id = id;
		this.producto = producto;
		this.precio = precio;
		this.cantidad = cantidad;
		this.total = total;
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
	 * @return the idVenta
	 */
	public int getIdVenta() {
		return idVenta;
	}

	/**
	 * @param idVenta the idVenta to set
	 */
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	/**
	 * @return the precio
	 */
	public float getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	

}
