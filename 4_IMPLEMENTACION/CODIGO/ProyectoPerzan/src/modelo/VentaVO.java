package modelo;

import java.sql.Timestamp;


public class VentaVO {
	private int id, idVendedor,idCliente;
	private String vendedor, cliente;
	private float total;
	private Timestamp fechaHora;
	
	public VentaVO(){	
	}
	public VentaVO(int id, int idVendedor, int idCliente, float total, Timestamp fechaHora){
		this.id = id;
		this.idVendedor = idVendedor;
		this.idCliente = idCliente;
		this.fechaHora = fechaHora;
		this.setTotal(total);
	}
	public VentaVO(int id, String vendedor, String cliente, float total, Timestamp fechaHora){
		this.id = id;
		this.vendedor = vendedor;
		this.cliente = cliente;
		this.fechaHora = fechaHora;
		this.setTotal(total);
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
	 * @return the idVendedor
	 */
	public int getIdVendedor() {
		return idVendedor;
	}
	/**
	 * @param idVendedor the idVendedor to set
	 */
	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}
	/**
	 * @return the idCliente
	 */
	public int getIdCliente() {
		return idCliente;
	}
	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	/**
	 * @return the vendedor
	 */
	public String getVendedor() {
		return vendedor;
	}
	/**
	 * @param vendedor the vendedor to set
	 */
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	/**
	 * @return the fechaHora
	 */
	public Timestamp getFechaHora() {
		return fechaHora;
	}
	/**
	 * @param fechaHora the fechaHora to set
	 */
	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
	}
	
}
