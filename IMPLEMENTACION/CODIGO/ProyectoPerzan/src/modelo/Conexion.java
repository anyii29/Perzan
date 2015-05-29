package modelo;

import java.sql.*;

import javax.swing.JOptionPane;

public class Conexion {
	static String bd = "Perzan";
	static String login = "root";
	static String password = "12345";
	static String url = "jdbc:mysql://localhost/"+bd;
	boolean conexion = false;
	
	Connection conn = null;
	public Conexion(){
		conectar();
	}
	
	public void conectar(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, login, password);
			if(conn!=null){
				conexion = true;
			}
		}
		catch(SQLException e){
			System.out.println(e);
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public boolean conectado(){
		boolean dat = false;
		if(conexion){
			System.out.println("Conexion a BD: "+bd+ " Establecida");
			dat = true;
		}
		else{
			JOptionPane.showMessageDialog(null, "Fallo conexion a Base de Datos!\n Verificar Conexion.");
		}
		if(dat){
			return true;
		}
		else{
			return false;
		}
	}
	public Connection getConnection(){
		return conn;
	}
	public void desconectar(){
		conn = null;
	}
	public static void main(String [] args){
		Conexion cn = new Conexion();
		cn.getConnection();
	}

}
