package modelo;

public class ClienteVO extends Person {
	String referencia, nombreCliente;
	
	
	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public ClienteVO(int id , String nombre, String apPaterno, String apMaterno, int calle,
			int avenida, int numero, String colonia, String municipio,
			String referencia){
		this.id = id;
		this.nombre = nombre;
		this.apPaterno = apPaterno;
		this.apMaterno = apMaterno;
		this.nombreCliente = nombre + " " + apPaterno + " " + apMaterno;
		this.calle = calle;
		this.avenida = avenida;
		this.numero = numero;
		this.colonia = colonia;
		this.municipio = municipio;
		this.referencia = referencia;
	}

	public ClienteVO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * @param referencia the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	
}
