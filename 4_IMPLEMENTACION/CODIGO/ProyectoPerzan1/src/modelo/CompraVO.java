package modelo;
import java.sql.Timestamp;
import java.util.Date;

public class CompraVO {
	
	private int id, idProveedor;
	private String empresa;
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
	public CompraVO(int id, String empresa, float total, Date fechaPedido, Timestamp fechaRecepcion){
		this.id = id;
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

	public int getIdProvedor() {
		return idProveedor;
	}

	public void setIdProvedor(int idProvedor) {
		this.idProveedor = idProvedor;
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
	
}
