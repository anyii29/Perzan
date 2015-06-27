package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import modelo.Encrypt;
import modelo.LoginEmpDAO;
import modelo.LoginEmpVO;
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
	private LoginEmpVO usuario;
	boolean result;
	private Encrypt encrypt = new Encrypt();
	static String tipo;
	
	public void login(ActionEvent event){
		if(txtUsuario.getText().equals("")||txtPassword.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Complementa Los Campos");
		}
		else{
			String password = encrypt.encryptText(txtPassword.getText());
			LoginEmpVO usuarioEmpVO = new LoginEmpVO(txtUsuario.getText().trim(),
					password, tipo);
			System.out.println(tipo);
			LoginEmpDAO usuarioEmpDAO = new LoginEmpDAO();
			if(usuarioEmpDAO.iniciar(usuarioEmpVO)){
				usuario = usuarioEmpDAO.obj();
				if(usuario.getTipo().equals("admin")){
					Principal.empleado = usuario.getUsuario();
				}
				else{
					Principal.empleado = usuario.getUsuario();
				}
				dialogStage.close();
			}
			else{
				JOptionPane.showMessageDialog(null, "Error Usuario o Contraseña Incorrectos!");
				txtPassword.setText("");
			}
		}
	}
	/**
	 * @return the tipo
	 */
	public static String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public static void setTipo(String tipo) {
		LoginEmp.tipo = tipo;
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
	public LoginEmpVO getUsuario() {
		return usuario;
	}
	public void setUsuario(LoginEmpVO usuario) {
		this.usuario = usuario;
	}
	
}
