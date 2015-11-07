package modelo;

public class LoginEmpVO {
	private String usuario;
	private String password;
	private String tipo, nombre;
	private int id;

	public LoginEmpVO(String usuario, String password, String tipo) {
		this.usuario=usuario;
		this.password=password;
		this.tipo = tipo;
	}
	public LoginEmpVO(String usuario, String password, String tipo, String nombre, int id) {
		this.usuario=usuario;
		this.password=password;
		this.tipo = tipo;
		this.nombre = nombre;
		this.id = id;
	}
	public LoginEmpVO(){
		
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
}
