package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import modelo.UsuarioEmpDAO;
import modelo.UsuarioEmpVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginEmp implements Initializable {
	public LoginEmp(){
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	@FXML
	private TextField txtUsuario;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnCancelar;
	@SuppressWarnings("unused")
	private application.Main1 main1;
	private Stage dialogStage;
	private String usuario;
	boolean result;
	
	public void login(ActionEvent event){
		if(txtUsuario.getText().equals("")||txtPassword.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Complementa Los Campos");
		}
		else{
			
			if(!(txtUsuario.getText().equals(""))&&!(txtPassword.getText().equals(""))){
				UsuarioEmpVO usuarioEmpVO = new UsuarioEmpVO(txtUsuario.getText(),txtPassword.getText());
				UsuarioEmpDAO usuarioEmpDAO = new UsuarioEmpDAO();
				System.out.println(usuarioEmpVO.getUsuario() + usuarioEmpVO.getPassword());
				if(usuarioEmpDAO.iniciar(usuarioEmpVO)){
					dialogStage.close();
					String nombre = usuarioEmpDAO.nombre(txtUsuario.getText());
					setUsuario(nombre);	
					//setUsuario(usuarioEmpVO.getUsuario());	
					Principal.loginEmp = true;
					Principal.empleado = nombre;
				}
				else{
					JOptionPane.showMessageDialog(null, "Error Usuario o Contraseña Incorrectos!");
					txtPassword.setText("");
				}
			}
		}
	}

	public void cancelar(ActionEvent event){
		dialogStage.close();
	}
	public void setDialogStage(Stage dialogStage) {
		 this.dialogStage = dialogStage;
	}
	public void setMain1(application.Main1 main1) {
		this.main1= main1;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario){
		this.usuario= usuario;
	}
}
