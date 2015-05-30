package modelo;

public class AdminVO {
	protected String usuario;
	protected String password;
	
	public AdminVO(){		
	}
	public AdminVO(String usuario, String password){
		this.usuario=usuario;
		this.password=password;
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
}
