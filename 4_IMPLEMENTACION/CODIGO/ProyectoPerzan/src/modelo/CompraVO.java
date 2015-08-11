package modelo;
import java.sql.Timestamp;
import java.util.Date;

public class CompraVO {
	
	private int id, idProveedor, idEmpleado;
	private String empresa, empleado;
	private float total;
	private Date fechaPedido;
	private Timestamp fechaRecepcion;
	
	public CompraVO(int id, int idProveedor, float total, Date fechaPedido, Timestamp fechaRecepcion){
		this.id = id;
		this.idProveedor = idProveedor;
		this.total = total;
		this.fechaPedido = fechaPedido;
		this.fechaRecepcion = fechaRecepcion;
	}
	public CompraVO(int id, String empleado, String empresa, float total, Date fechaPedido, Timestamp fechaRecepcion){
		this.id = id;
		this.empleado = empleado;
		this.empresa = empresa;
		this.total = total;
		this.fechaPedido = fechaPedido;
		this.fechaRecepcion = fechaRecepcion;
	}

	public CompraVO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Timestamp getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Timestamp fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	/**
	 * @return the idProveedor
	 */
	public int getIdProveedor() {
		return idProveedor;
	}
	/**
	 * @param idProveedor the idProveedor to set
	 */
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	/**
	 * @return the idEmpleado
	 */
	public int getIdEmpleado() {
		return idEmpleado;
	}
	/**
	 * @param idEmpleado the idEmpleado to set
	 */
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
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
	
}
