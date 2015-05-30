package modelo;

public class UsuarioVO {
	protected String usuario;
	protected String password;
	
	public UsuarioVO(){		
	}
	public UsuarioVO(String usuario, String password){
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
