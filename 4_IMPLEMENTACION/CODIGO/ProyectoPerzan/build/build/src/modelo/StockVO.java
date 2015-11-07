package modelo;

import java.sql.Timestamp;

public class StockVO {
	private int idProducto, stock;
	private String usuario;
	private String causa;
	private Timestamp fechaHora;
	
	public StockVO(int idProducto, String causa, String usuario, int stock, Timestamp fechaHora) {
		// TODO Auto-generated constructor stub
		this.idProducto = idProducto;
		this.usuario = usuario;
		this.causa = causa;
		this.stock = stock;
		this.fechaHora = fechaHora;
	}
	
	
	public StockVO() {
		// TODO Auto-generated constructor stub
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
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the causa
	 */
	public String getCausa() {
		return causa;
	}
	/**
	 * @param causa the causa to set
	 */
	public void setCausa(String causa) {
		this.causa = causa;
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


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}

}
