package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProveedorVO extends Person {
	
	private String empresa, nombreProveedor ;
	private Logger log;
	
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
		log = new Logger();
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
	
	public ObservableList<ProveedorVO> getDatos(){
		ObservableList<ProveedorVO> proveedores = FXCollections.observableArrayList();
		Conexion conex = Conexion.getInstance();
		if(conex.conectado()){
			try {
				conex.conectar();
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM fn_seleccionarproveedores()");
				ResultSet res = consulta.executeQuery();
				while(res.next()){
					int id = res.getInt("fid");
					String nombre = res.getString("fnombre");
					String apPaterno = res.getString("fapellido_paterno");
					String apMaterno = res.getString("fapellido_materno");
					String empresa = res.getString("fempresa");
					int calle = res.getInt("fcalle");
					int avenida = res.getInt("favenida");
					int numero = res.getInt("fnumero");
					String colonia = res.getString("fcolonia");
					String municipio = res.getString("fmunicipio");
					String telefono = res.getString("ftelefono");
					ProveedorVO proveedorVO = new ProveedorVO(id, nombre, apPaterno, apMaterno,
							empresa, calle, avenida, numero, colonia, municipio, telefono);
					proveedores.add(proveedorVO);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				conex.desconectar();
			}
		}
		return proveedores;
	}
	
	public String toString(){
		return empresa;
	}
}
