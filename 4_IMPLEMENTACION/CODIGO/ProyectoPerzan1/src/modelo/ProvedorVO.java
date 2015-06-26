package modelo;

public class ProvedorVO extends Person {
	
	private String empresa ;
	
	public ProvedorVO(int id , String nombre, String apPaterno, String apMaterno, String empresa,
			int calle,int avenida, int numero, String colonia, String municipio,
			String telefono, String usuario, String password, String tipo){
		this.id = id;
		this.nombre = nombre;
		this.apPaterno = apPaterno;
		this.apMaterno = apMaterno;
		this.empresa = empresa;
		this.calle = calle;
		this.avenida = avenida;
		this.numero = numero;
		this.colonia = colonia;
		this.municipio = municipio;
		this.telefono = telefono;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

}
