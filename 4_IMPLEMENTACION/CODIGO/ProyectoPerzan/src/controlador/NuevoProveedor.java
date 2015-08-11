package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import modelo.ProveedorDAO;
import modelo.ProveedorVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NuevoProveedor implements Initializable {
	
	private Stage dialogStage;
	private ProveedorDAO proveedorDAO;
	private Validar validar;
	
	public NuevoProveedor() {
		// TODO Auto-generated constructor stub
		proveedorDAO = new ProveedorDAO();
		validar = new Validar();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@FXML
    private TextField txtAvenidaPro;

    @FXML
    private TextField txtColoniaPro;

    @FXML
    private TextField txtCallePro;

    @FXML
    private Button btnSalir;

    @FXML
    private TextField txtMunicipioPro;

    @FXML
    private TextField txtTelefonoPro;

    @FXML
    private TextField txtAMaternoPro;

    @FXML
    private TextField txtAPaternoPro;

    @FXML
    private TextField txtNumeroPro;

    @FXML
    private Button btnGuardarPro;

    @FXML
    private TextField txtNombrePro;

    @FXML
    private TextField txtEmpresaPro;

    @FXML
    public void guardarPro(ActionEvent event) {
    	if (txtNombrePro.getText().isEmpty()
				|| txtAMaternoPro.getText().isEmpty()
				|| txtAPaternoPro.getText().isEmpty()
				|| txtEmpresaPro.getText().isEmpty()
				|| txtTelefonoPro.getText().isEmpty()
				|| txtCallePro.getText().isEmpty()
				|| txtAvenidaPro.getText().isEmpty()
				|| txtNumeroPro.getText().isEmpty()
				|| txtColoniaPro.getText().isEmpty()
				|| txtMunicipioPro.getText().isEmpty()) {
			alert(AlertType.ERROR, "Completa los campos.");
		} else {
			int i = 0;
			if (validar.nombres(txtNombrePro.getText().trim())) {
				txtNombrePro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtNombrePro.setStyle("-fx-background-color: red");
			}
			if (validar.nombres(txtAMaternoPro.getText().trim())) {
				txtAMaternoPro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtAMaternoPro.setStyle("-fx-background-color: red");
			}
			if (validar.nombres(txtAPaternoPro.getText().trim())) {
				txtAPaternoPro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtAPaternoPro.setStyle("-fx-background-color: red");
			}
			if (validar.telefono(txtTelefonoPro.getText().trim())) {
				txtTelefonoPro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtTelefonoPro.setStyle("-fx-background-color: red");
			}
			if (validar.cadena(txtEmpresaPro.getText().trim())) {
				txtEmpresaPro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtEmpresaPro.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtCallePro.getText().trim())) {
				txtCallePro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtCallePro.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtAvenidaPro.getText().trim())) {
				txtAvenidaPro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtAvenidaPro.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtNumeroPro.getText().trim())) {
				txtNumeroPro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtNumeroPro.setStyle("-fx-background-color: red");
			}
			if (validar.cadena(txtColoniaPro.getText().trim())) {
				txtColoniaPro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtColoniaPro.setStyle("-fx-background-color: red");
			}
			if (validar.cadena(txtMunicipioPro.getText().trim())) {
				txtMunicipioPro.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtMunicipioPro.setStyle("-fx-background-color: red");
			}
			if (i == 0) {
				String nombre = txtNombrePro.getText().trim();
				String aPaterno = txtAPaternoPro.getText().trim();
				String aMaterno = txtAMaternoPro.getText().trim();
				int calle = Integer.parseInt(txtCallePro.getText());
				String empresa = txtEmpresaPro.getText().trim();
				int avenida = Integer.parseInt(txtAvenidaPro.getText());
				int numero = Integer.parseInt(txtNumeroPro.getText());
				String colonia = txtColoniaPro.getText().trim();
				String municipio = txtMunicipioPro.getText();
				String telefono = txtTelefonoPro.getText().trim();
				ProveedorVO proveedorVO = new ProveedorVO(0, nombre, aPaterno,
						aMaterno, empresa, calle, avenida, numero, colonia,
						municipio, telefono);
					if (proveedorDAO.registrar(proveedorVO)) {
						alert(AlertType.INFORMATION, "Proveedor registrado.");
						txtNombrePro.setText("");
						txtAMaternoPro.setText("");
						txtAPaternoPro.setText("");
						txtTelefonoPro.setText("");
						txtEmpresaPro.setText("");
						txtCallePro.setText("");
						txtAvenidaPro.setText("");
						txtNumeroPro.setText("");
						txtColoniaPro.setText("");
						txtMunicipioPro.setText("");
					} else {
						alert(AlertType.ERROR, "Falló registro.");
					}
			} else {
				alert(AlertType.ERROR, "Verifica datos no validos.");
			}
		}

	}

    @FXML
    public void salir(ActionEvent event) {
    	dialogStage.close();
    }
    
    public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
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
	

}
