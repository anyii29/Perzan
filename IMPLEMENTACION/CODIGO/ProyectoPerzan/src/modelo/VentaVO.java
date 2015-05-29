package modelo;

public class VentaVO {
	private int id;
	private String cliente;
	private double importe;
	private String vendedor;
	private String fecha;
	
	public VentaVO(){	
	}
	public VentaVO(int id, String cliente, double importe, String vendedor, String fecha){
		this.id = id;
		this.cliente = cliente;
		this.importe = importe;
		this.vendedor = vendedor;
		this.fecha = fecha;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
