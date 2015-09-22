package controlador;

import java.net.URL;
import java.util.ResourceBundle;


import modelo.Conexion;
import modelo.Encrypt;
import modelo.LoginEmpDAO;
import modelo.LoginEmpVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginEmp implements Initializable {
	public LoginEmp(){
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tpConexion.setVisible(false);
	}
	@FXML
	private TextField txtUsuario, txtIp, txtPuerto, txtBaseDatos,txtUser;
	@FXML
	private PasswordField txtPassword, txtPass;
	@FXML
	private Button btnLogin, btnCancelar, btnConectar, btnCancelarCon;
	@FXML private TitledPane tpConexion;
	@SuppressWarnings("unused")
	private view.Main1 main1;
	private Stage dialogStage;
	private LoginEmpVO usuario;
	boolean result;
	private Encrypt encrypt = new Encrypt();
	static String tipo;
	private Conexion conex = Conexion.getInstance();
	private Boolean val;
	
	public void login(ActionEvent event){
		val = true;
		if(txtUsuario.getText().equals("")||txtPassword.getText().equals("")){
			alert(AlertType.WARNING, "Complementa los campos.");
		}
		else{
			conex.conectar();
			if(tipo == "admin" && conex.result() == false){
				tpConexion.setVisible(true);
				val = false;
				txtUsuario.setDisable(true);
				txtPassword.setDisable(true);
				btnLogin.setDisable(true);
				btnCancelar.setDisable(true);
				alert(AlertType.ERROR, "Configure su conexión.");
				
			}
			else{
				String password = encrypt.encryptText(txtPassword.getText());
				LoginEmpVO usuarioEmpVO = new LoginEmpVO(txtUsuario.getText().trim(),
						password, tipo);
				LoginEmpDAO usuarioEmpDAO = new LoginEmpDAO();
				if(usuarioEmpDAO.fechaHora()){
					if(usuarioEmpDAO.iniciar(usuarioEmpVO)  && val){
						usuario = usuarioEmpDAO.obj();
						Principal.setEmpleado(usuario);
						dialogStage.close();
					}
					else{
						alert(AlertType.ERROR, "Error Usuario o Contraseña Incorrectos!");
						txtPassword.setText("");
						txtPassword.requestFocus();;
						usuario = null;
					}
				}
				else{
					alert(AlertType.ERROR, "Fecha y hora incorrectas, \n Actualice la fecha y hora de su sistema operativo.");
				}
			}
		}
	}
	
	public void conectar(){
		conex.setIp(txtIp.getText().trim());
		conex.setBd(txtBaseDatos.getText().trim());
		conex.setPuerto(Integer.parseInt(txtPuerto.getText().trim()));
		conex.setUsuario(txtUser.getText().trim());
		conex.setContrasena(txtPass.getText().trim());
		String text = conex.conectar();
		alert(AlertType.INFORMATION, text);
		if(conex.result()){
			login(null);
			tpConexion.setVisible(false);
			txtUsuario.setDisable(false);
			txtPassword.setDisable(false);
			btnCancelar.setDisable(false);
			btnLogin.setDisable(false);
			val = true;	
			conex.actualizar();
		}
		
	}
	public void cancelarCon(){
		txtIp.setText("");
		txtPuerto.setText("");
		txtBaseDatos.setText("");
		txtUser.setText("");
		txtPass.setText("");
		tpConexion.setVisible(false);
		txtUsuario.setDisable(false);
		txtPassword.setDisable(false);
		btnCancelar.setDisable(false);
		btnLogin.setDisable(false);
	}
	
	/*
     * metodo que crea mensajes de alerta
     * */
    public void alert(AlertType tipo, String mensaje) {
    	Alert alert = new Alert(tipo);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
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
		usuario = null;
	}
	public void setDialogStage(Stage dialogStage) {
		 this.dialogStage = dialogStage;
	}
	public void setMain1(view.Main1 main1) {
		this.main1= main1;
	}
	public LoginEmpVO getUsuario() {
		return usuario;
	}
	public void setUsuario(LoginEmpVO usuario) {
		this.usuario = usuario;
	}
	
}
