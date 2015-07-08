package modelo;

public class ProveedorVO extends Person {
	
	private String empresa, nombreProveedor ;
	
	public ProveedorVO(int id , String nombre, String apPaterno, String apMaterno, String empresa,
			int calle,int avenida, int numero, String colonia, String municipio,
			String telefono){
		this.id = id;
		this.nombre = nombre;
		this.apPaterno = apPaterno;
		this.apMaterno = apMaterno;
		this.nombreProveedor = nombre + " " + apPaterno + " " + apMaterno;
		this.empresa = empresa;
		this.calle = calle;
		this.avenida = avenida;
		this.numero = numero;
		this.colonia = colonia;
		this.municipio = municipio;
		this.telefono = telefono;
	}

	public ProveedorVO() {
		
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

}
