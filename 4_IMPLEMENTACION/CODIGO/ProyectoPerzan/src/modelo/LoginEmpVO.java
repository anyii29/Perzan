package modelo;

public class LoginEmpVO {
	private String usuario;
	private String password;
	private String tipo;

	public LoginEmpVO(String usuario, String password, String tipo) {
		this.usuario=usuario;
		this.password=password;
		this.tipo = tipo;
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
}
