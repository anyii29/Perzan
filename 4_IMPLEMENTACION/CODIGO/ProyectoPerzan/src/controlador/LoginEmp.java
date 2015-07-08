package controlador;

import java.net.URL;
import java.text.BreakIterator;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import modelo.Conexion;
import modelo.Encrypt;
import modelo.LoginEmpDAO;
import modelo.LoginEmpVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
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
			JOptionPane.showMessageDialog(null, "Complementa Los Campos");
		}
		else{
			conex.conectar();
			if(tipo == "admin" && conex.conectado() == false){
				tpConexion.setVisible(true);
				val = false;
				JOptionPane.showMessageDialog(null, "Configure su conexión.");
				
			}
			else{
				String password = encrypt.encryptText(txtPassword.getText());
				LoginEmpVO usuarioEmpVO = new LoginEmpVO(txtUsuario.getText().trim(),
						password, tipo);
				LoginEmpDAO usuarioEmpDAO = new LoginEmpDAO();
				if(usuarioEmpDAO.iniciar(usuarioEmpVO)  && val){
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
					usuario = null;
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
		JOptionPane.showMessageDialog(null, text);
		if(conex.conectado()){
			tpConexion.setVisible(false);
			val = true;	
		}
		
	}
	public void cancelarCon(){
		txtIp.setText("");
		txtPuerto.setText("");
		txtBaseDatos.setText("");
		txtUser.setText("");
		txtPass.setText("");
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
