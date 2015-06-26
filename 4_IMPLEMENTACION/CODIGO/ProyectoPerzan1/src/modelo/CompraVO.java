package modelo;
import java.sql.Timestamp;
import java.util.Date;

public class CompraVO {
	
	private int id, idProvedor;
	private float total;
	private Date fechaPedido;
	private Timestamp fechaRecepcion;
	
	public CompraVO(int id, int idProvedor, float total, Date fechaPedido, Timestamp fechaRecepcion){
		this.id = id;
		this.idProvedor = idProvedor;
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
		return idProvedor;
	}

	public void setIdProvedor(int idProvedor) {
		this.idProvedor = idProvedor;
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
