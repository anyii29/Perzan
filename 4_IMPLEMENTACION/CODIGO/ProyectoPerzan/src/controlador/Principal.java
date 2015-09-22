package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import modelo.LoginEmpVO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class Principal implements Initializable{
	static public boolean loginAdm;
	static public boolean loginEmp;
	//static String empleado;
	private view.Main1 main1;
	private static LoginEmpVO empleado;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnEmpleado.setOnAction(new showLoginEmp());
		btnAdmin.setOnAction(new showLogin());
		ttAdmin.setText("Iniciar Sesión");
		ttEmp.setText("Iniciar Sesión");
	}
	public Principal(){	
		//loginAdm = false;
		//loginEmp = false;
	}
	@FXML
	private Button btnAdmin;
	@FXML
	private Button btnEmpleado;
	@FXML
	private Button btnSalir;
	@FXML
	private Tooltip ttAdmin;
	@FXML
	private Tooltip ttEmp;
	
	private class showLogin implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			if(loginAdm){				
				main1.viewAdministrador();
				ttAdmin.setText("Cambiar a administrador.");
			}
			else{
				LoginEmp.tipo = "admin";
				main1.showLoginEmp();
				ttAdmin.setText("Iniciar sesión.");
			}
		}
	}
	private class showLoginEmp implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			if(loginEmp){
				main1.viewEmpleado(empleado);
				ttEmp.setText("Cambiar a empleado.");
			}
			else{
				LoginEmp.tipo = "empleado";
				main1.showLoginEmp();
				ttEmp.setText("Iniciar sesión.");
			}
		}
	}
	public void salir(ActionEvent event){
		System.exit(0);
	}
	
	public static void setEmpleado(LoginEmpVO emp){
		empleado = emp;
	}
	
	public void setMain1(view.Main1 main1) {
		this.main1=main1;
	}
}
