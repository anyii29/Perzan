package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	private FileReader file;
	private FileWriter fWrite;
	private BufferedReader brFile;
	private BufferedWriter wrWrite;
	private Logger log;
	private Encrypt encrypt;
	
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
		encrypt = new Encrypt();
		try {
			File f = new File("BaseDatos\\conexion.txt");
			if(!f.exists()){
				fWrite = new FileWriter(f);
				ip = "";
				puerto = 0;
				usuario = "";
				contrasena = "";
				bd = "";
			}
			else{
				try {
					f = encrypt.decryptConexion(f);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					log.printLog(e.getMessage(), this.getClass().toString());
				}
				file = new FileReader(f);
				brFile = new BufferedReader(file);
				ip = brFile.readLine();
				String p = brFile.readLine();
				if(p == null){
					puerto = 0;
				}
				else{
					puerto = Integer.parseInt(p);
				}
				usuario = brFile.readLine();
				contrasena = brFile.readLine();
				bd = brFile.readLine();
				brFile.close();
				f.delete();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.printLog(e.getMessage(), this.getClass().toString());
		}
		
		servidor = "jdbc:postgresql://" + ip +":"+ puerto + "/"+bd;
		log = new Logger();
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
			log.printLog(e.getMessage(), this.getClass().toString());
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
			log.printLog(e.getMessage(), this.getClass().toString());
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
		return true;
	}
	public boolean result(){
		return date;
	}
	public void actualizar(){
		try {
			File f = new File("BaseDatos\\conexion1.txt");
			fWrite = new FileWriter(f, false);
			wrWrite = new BufferedWriter(fWrite);
			wrWrite.write(this.ip);//ip
			wrWrite.newLine();
			wrWrite.write(String.valueOf(this.puerto));//puerto
			wrWrite.newLine();
			wrWrite.write(this.usuario);//usuario
			wrWrite.newLine();
			wrWrite.write(this.contrasena);//contrasena
			wrWrite.newLine();
			wrWrite.write(this.bd);//bd
			wrWrite.close();
			encrypt.encryptConexion(f);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			log.printLog(e.getMessage(), this.getClass().toString());
		}
	}
}
