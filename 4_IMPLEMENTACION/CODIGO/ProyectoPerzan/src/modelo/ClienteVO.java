package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClienteVO extends Person {
	private String referencia, nombreCliente;
	private Logger log;
	
	
	
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
		log = new Logger();
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
	
	public ObservableList<ClienteVO> getDatos(){
		ObservableList<ClienteVO> clientes = FXCollections.observableArrayList();
		ClienteVO clienteVO;
		Conexion conex = Conexion.getInstance();
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionarclientes()");
				ResultSet res = consulta.executeQuery();
				while(res.next()){
					int id = res.getInt("fid");
					String nombre = res.getString("fnombre");
					String apellidoPaterno = res.getString("fapellido_paterno");
					String apellidoMaterno = res.getString("fapellido_materno");
					int calle = res.getInt("fcalle");
					int avenida = res.getInt("favenida");
					int numero = res.getInt("fnumero");
					String colonia = res.getString("fcolonia");
					String municipio = res.getString("fmunicipio");
					String referencia = res.getString("freferencia");
					clienteVO = new ClienteVO(id, nombre, apellidoPaterno, apellidoMaterno, calle, avenida,
							numero, colonia, municipio, referencia);
					clientes.add(clienteVO);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}			
		}
		return clientes;
		
	}
	
	public String toString(){
		return nombreCliente;
	}
	
}
