package modelo;

import java.sql.Timestamp;
import java.util.Date;

public class CompraDetVO {
	private int id, cantidad;
	private String empresa, empleado, producto;
	private Float precio, total;
	private Date fechaPedido;
	private Timestamp fechaRecepcion;
	
	public CompraDetVO(int id, String empresa, String empleado, String producto, int cantidad, float precio, float total, Date fechaPedido, Timestamp fechaRecepcion){
		// TODO Auto-generated constructor stub
		this.id = id;
		this.empresa = empresa;
		this.empleado = empleado;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.total = total;
		this.fechaPedido = fechaPedido;
		this.fechaRecepcion = fechaRecepcion;
	}

	public CompraDetVO() {
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
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the empleado
	 */
	public String getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
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
	 * @return the precio
	 */
	public Float getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	/**
	 * @return the total
	 */
	public Float getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Float total) {
		this.total = total;
	}

	/**
	 * @return the fechaPedido
	 */
	public Date getFechaPedido() {
		return fechaPedido;
	}

	/**
	 * @param fechaPedido the fechaPedido to set
	 */
	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	/**
	 * @return the fechaRecepcion
	 */
	public Timestamp getFechaRecepcion() {
		return fechaRecepcion;
	}

	/**
	 * @param fechaRecepcion the fechaRecepcion to set
	 */
	public void setFechaRecepcion(Timestamp fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
}
