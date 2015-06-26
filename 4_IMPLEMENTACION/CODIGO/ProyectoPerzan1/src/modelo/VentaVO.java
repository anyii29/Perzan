package modelo;

import java.security.Timestamp;

public class VentaVO {
	private int id, idVendedor,idCliente;
	private Timestamp fechaHora;
	
	public VentaVO(){	
	}
	public VentaVO(int id, int idVendedor, int idCliente, Timestamp fechaHora){
		this.id = id;
		this.idVendedor = idVendedor;
		this.idCliente = idCliente;
		this.fechaHora = fechaHora;
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
