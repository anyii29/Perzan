package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import modelo.LoginEmpDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class Principal implements Initializable{
	static boolean loginAdm;
	static boolean loginEmp;
	static String empleado;
	private application.Main1 main1;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnEmpleado.setOnAction(new showLoginEmp());
		btnAdmin.setOnAction(new showLogin());
		ttAdmin.setText("Iniciar Sesion");
		ttEmp.setText("Iniciar Sesion");
	}
	public Principal(){	
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
				ttAdmin.setText("Cambiar A Administrador");
			}
			else{
				LoginEmp.tipo = "admin";
				main1.showLoginEmp();
				ttAdmin.setText("Iniciar Sesion");
			}
		}
	}
	private class showLoginEmp implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			if(loginEmp){
				main1.viewEmpleado(empleado);
				ttEmp.setText("Cambiar A Empleado");
			}
			else{
				LoginEmp.tipo = "empleado";
				main1.showLoginEmp();
				ttEmp.setText("Iniciar Sesion");
			}
		}
	}
	public void salir(ActionEvent event){
		System.exit(0);
	}
	
	public void setMain1(application.Main1 main1) {
		this.main1=main1;
	}
}
