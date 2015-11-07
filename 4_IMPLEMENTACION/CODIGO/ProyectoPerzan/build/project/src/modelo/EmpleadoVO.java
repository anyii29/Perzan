package modelo;

public class EmpleadoVO extends Person {
	private String usuario, password, tipo,nombreEmpleado;
	
	public EmpleadoVO(){
		
	}
	public EmpleadoVO(int id , String nombre, String apPaterno, String apMaterno, int calle,
			int avenida, int numero, String colonia, String municipio,
			String telefono, String usuario, String password, String tipo){
		this.id = id;
		this.nombre = nombre;
		this.apPaterno = apPaterno;
		this.apMaterno = apMaterno;
		this.nombreEmpleado = nombre + " " + apPaterno + " " + apMaterno;
		this.calle = calle;
		this.avenida = avenida;
		this.numero = numero;
		this.colonia = colonia;
		this.municipio = municipio;
		this.telefono = telefono;
		this.usuario = usuario;
		this.password = password;
		this.tipo = tipo;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
		
}
