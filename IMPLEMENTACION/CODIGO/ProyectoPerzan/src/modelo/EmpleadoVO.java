package modelo;

public class EmpleadoVO {
	private int id;
	private String nombre;
	private String apPaterno;
	private String apMaterno;
	private String direccion;
	private String telefono;
	private String usuario;
	private String password;
	
	
	public EmpleadoVO(){
		
	}
	public EmpleadoVO(int id, String nombre, String apPaterno, String apMaterno, String direccion, String telefono, String usuario,String password){
		this.setId(id);
		this.nombre= nombre;
		this.apPaterno= apPaterno;
		this.apMaterno= apMaterno;
		this.direccion= direccion;
		this.telefono= telefono;
		this.usuario= usuario;
		this.password= password;
		
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApPaterno() {
		return apPaterno;
	}


	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}


	public String getApMaterno() {
		return apMaterno;
	}


	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
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


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

}
