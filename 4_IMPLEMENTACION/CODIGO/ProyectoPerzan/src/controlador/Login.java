package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import modelo.UsuarioDAO;
import modelo.UsuarioVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login implements Initializable {
	public Login(){
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
	boolean result;
	private Stage dialogStage;
	private String usuario;
	public void login(ActionEvent event){
		if(txtUsuario.getText().equals("")||txtPassword.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Complementa Los Campos");
		}
		else{	
			if(!(txtUsuario.getText().equals(""))&&!(txtPassword.getText().equals(""))){
				UsuarioVO usuarioVO = new UsuarioVO(txtUsuario.getText(),txtPassword.getText());
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				if(usuarioDAO.iniciar(usuarioVO)){
					setUsuario(usuarioVO.getUsuario());					
					dialogStage.close();
					result=true;
					Principal.loginAdm = true;	
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
	public void setMain1(application.Main1 main1) {
		this.main1=main1;
	}
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
