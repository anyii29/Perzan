package modelo;

public class InventarioVO {
	int idEmpleado, idProducto, existencia, nuevaExistencia;
	String causa;
	
	public InventarioVO(){
		
	}
	public InventarioVO(int idProducto, int existencia, int nuevaExistencia, int idEmpleado, String causa){
		this.idProducto = idProducto;
		this.existencia = existencia;
		this.nuevaExistencia = nuevaExistencia;
		this.idEmpleado = idEmpleado;
		this.causa = causa;
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
	 * @return the existencia
	 */
	public int getExistencia() {
		return existencia;
	}
	/**
	 * @param existencia the existencia to set
	 */
	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}
	/**
	 * @return the nuevaExistencia
	 */
	public int getNuevaExistencia() {
		return nuevaExistencia;
	}
	/**
	 * @param nuevaExistencia the nuevaExistencia to set
	 */
	public void setNuevaExistencia(int nuevaExistencia) {
		this.nuevaExistencia = nuevaExistencia;
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

}
