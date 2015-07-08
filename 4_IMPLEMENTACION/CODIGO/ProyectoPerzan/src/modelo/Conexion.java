package modelo;

import java.sql.*;

public class Conexion {
	private String bd;
	private String usuario;
	private String contrasena;
	private String servidor;
	private String ip;
	private int puerto;
	private static Conexion instancia;
	private Connection con;
	private boolean date = false;
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	
	private Conexion(){
		setBd("perzan");
		usuario = "postgres";
		contrasena = "1234";
		ip = "localhost";
		puerto = 5432;
		servidor = "jdbc:postgresql://" + ip +":"+ puerto + "/"+bd;
	}
	
	private Conexion(String bd, String usuario, String contrasena, String ip, int puerto){
		this.servidor = "jdbc:postgresql://" + ip +":"+ puerto + "/"+bd;
		this.bd = bd;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.ip = ip;
		this.puerto = puerto;
		con = null;
	}
	
	public static Conexion getInstance(){
		if(instancia == null){
			instancia = new Conexion();
		}
		return instancia;
			
	}
	
	public String conectar(){
		try{
			this.servidor = "jdbc:postgresql://" + ip +":"+ puerto + "/"+bd;
			//verifica que el driver este presente en el proyecto
			Class.forName("org.postgresql.Driver");
			//establecer conexion
			con = DriverManager.getConnection(servidor, usuario, contrasena);
			System.out.println("Conexión éxitosa");
			date = true;
			return "Conexión éxitosa";
		}catch(Exception e){
			e .printStackTrace();
			return "No se establecio la conexion. Consulte a su administrador";
		}
	}
	/*
	 * metodo para desconectar.
	 */
	public String desconectar(){
		try{
			con.close();
			System.out.println("Se ha desconectado del servidor");
			date = false;
			return "Se ha desconectado del servidor";
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("La conexion esta siendo ocupada, no se puede desconectar");
			return "La conexion esta siendo ocupada, no se puede desconectar";
		}
	}

	public Connection getConnection(){
		return con;
	}
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getBd() {
		return bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
	}

	public boolean conectado() {
		return date;
	}
}
