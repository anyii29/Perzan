package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import modelo.ClienteDAO;
import modelo.ClienteVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NuevoCliente implements Initializable{
	
	private Validar validar;
	private ClienteVO clienteVO;
	private ClienteDAO clienteDAO;
	private Stage dialogStage;
	
	public NuevoCliente() {
		validar = new Validar();
		clienteVO = new ClienteVO();
		clienteDAO = new ClienteDAO();
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
    @FXML
    private TextField txtMunicipioCli;

    @FXML
    private Button btnSalir;

    @FXML
    private TextField txtAPaternoCli;

    @FXML
    private TextField txtNumeroCli;

    @FXML
    private TextField txtAMaternoCli;

    @FXML
    private TextArea txtReferenciaCli;

    @FXML
    private TextField txtNombreCli;

    @FXML
    private Button btnGuardarCli;

    @FXML
    private TextField txtAvenidaCli;

    @FXML
    private TextField txtCalleCli;

    @FXML
    private TextField txtColoniaCli;

    public void guardarCli(ActionEvent event) {
    	if (txtNombreCli.getText().isEmpty()
				|| txtAMaternoCli.getText().isEmpty()
				|| txtReferenciaCli.getText().isEmpty()
				|| txtCalleCli.getText().isEmpty()
				|| txtAvenidaCli.getText().isEmpty()
				|| txtNumeroCli.getText().isEmpty()
				|| txtColoniaCli.getText().isEmpty()
				|| txtMunicipioCli.getText().isEmpty()) {
			alert(AlertType.ERROR, "Completa los campos.");
		} else {
			int i = 0;
			if (validar.nombres(txtNombreCli.getText().trim())) {
				txtNombreCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtNombreCli.setStyle("-fx-background-color: red");
			}
			if (validar.nombres(txtAMaternoCli.getText().trim())) {
				txtAMaternoCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtAMaternoCli.setStyle("-fx-background-color: red");
			}
			if (validar.nombres(txtAPaternoCli.getText().trim())) {
				txtAPaternoCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtAPaternoCli.setStyle("-fx-background-color: red");
			}
			if (validar.cadena(txtReferenciaCli.getText().trim())) {
				txtReferenciaCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtReferenciaCli.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtCalleCli.getText().trim())) {
				txtCalleCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtCalleCli.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtAvenidaCli.getText().trim())) {
				txtAvenidaCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtAvenidaCli.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtNumeroCli.getText().trim())) {
				txtNumeroCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtNumeroCli.setStyle("-fx-background-color: red");
			}
			if (validar.cadena(txtColoniaCli.getText().trim())) {
				txtColoniaCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtColoniaCli.setStyle("-fx-background-color: red");
			}
			if (validar.cadena(txtMunicipioCli.getText().trim())) {
				txtMunicipioCli.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtMunicipioCli.setStyle("-fx-background-color: red");
			}
			if (i == 0) {
				String nombre = txtNombreCli.getText().trim();
				String aPaterno = txtAPaternoCli.getText().trim();
				String aMaterno = txtAMaternoCli.getText().trim();
				int calle = Integer.parseInt(txtCalleCli.getText());
				int avenida = Integer.parseInt(txtAvenidaCli.getText());
				int numero = Integer.parseInt(txtNumeroCli.getText());
				String colonia = txtColoniaCli.getText().trim();
				String municipio = txtMunicipioCli.getText();
				String referencia = txtReferenciaCli.getText().trim();
				ClienteVO clienteVO = new ClienteVO(0, nombre, aPaterno,
						aMaterno, calle, avenida, numero, colonia, municipio,
						referencia);
					if (clienteDAO.registrar(clienteVO)) {
						alert(AlertType.INFORMATION, "Cliente registrado.");
						txtNombreCli.setText("");
						txtAMaternoCli.setText("");
						txtAPaternoCli.setText("");
						txtReferenciaCli.setText("");
						txtCalleCli.setText("");
						txtAvenidaCli.setText("");
						txtNumeroCli.setText("");
						txtColoniaCli.setText("");
						txtMunicipioCli.setText("");
					} else {
						alert(AlertType.ERROR, "Falló registro.");
					}
			} else {
				alert(AlertType.ERROR, "Verifica datos no validos.");
			}
		}

    }

    public void salir(ActionEvent event) {
    	dialogStage.close();
    }
    
    /*
	 * metodo que crea mensajes de alerta
	 */
	public void alert(AlertType tipo, String mensaje) {
		Alert alert = new Alert(tipo);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}
