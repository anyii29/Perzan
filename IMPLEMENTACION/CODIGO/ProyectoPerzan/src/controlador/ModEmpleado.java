package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import modelo.EmpleadoDAO;
import modelo.EmpleadoVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class ModEmpleado implements Initializable{
	
	public ModEmpleado(){
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	@FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtContrasena;
    @FXML
    private TextField txtTelefono;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtApPaterno;
    @FXML
    private TextField txtApMaterno;
    @FXML
    private Button btnRegistrar;
    @FXML
    private TextField txtUsuario;
    @FXML
    private Tooltip ttNombre;
    @FXML
    private Tooltip ttApPaterno;
    @FXML
    private Tooltip ttApMaterno;
    @FXML
    private Tooltip ttDireccion;
    @FXML
    private Tooltip ttTelefono;
    @FXML
    private Tooltip ttUsuario;
    @FXML
    private Tooltip ttContrasena;
    
	private Stage dialogStage;
	private EmpleadoVO empleadoVO;
    
    public void registrar(ActionEvent event) {
    	if(txtNombre.getText().equals("")||txtApPaterno.getText().equals("") || txtApMaterno.getText().equals("")|| txtDireccion.getText().equals("")||txtTelefono.getText().equals("")||txtUsuario.getText().equals("")||txtContrasena.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Rellena Todos Los Campos");		
		}
		else{
			int i = 0;
			int id = empleadoVO.getId();
			Validar validar = new Validar();
			if(!(validar.nombres(txtNombre.getText()))){ i++; ttNombre.setText("Campo Erroneo Ej: \"Nombre\"");}else{ttNombre.setText(null);}
			if(!(validar.nombres(txtApPaterno.getText()))){ i++; ttApPaterno.setText("Campo Erroneo Ej: \"Paterno\"");}else{ttApPaterno.setText(null);}
			if(!(validar.nombres(txtApMaterno.getText()))){ i++; ttApMaterno.setText("Campo Erroneo Ej: \"Paterno\"");}else{ttApMaterno.setText(null);}
			if(!(validar.direccion(txtDireccion.getText()))){ i++; ttDireccion.setText("Campo Erroneo ");}else{ttDireccion.setText(null);}
			if(!(validar.telefono(txtTelefono.getText()))){ i++; ttTelefono.setText("Campo Erroneo Ej: \"555-555-55-55\"");}else{ttTelefono.setText(null);}
			if(!(validar.usuario(txtUsuario.getText()))){ i++; ttUsuario.setText("Campo Erroneo Ej: \"usuario1\"");}else{ttUsuario.setText(null);}
			if(!(validar.contrasena(txtContrasena.getText()))){ i++; ttContrasena.setText("Campo Erroneo  Ej: \"usuario1\"");}else{ttContrasena.setText(null);}
			if(i == 0){
				EmpleadoVO empleadoVO = new EmpleadoVO(id, txtNombre.getText(), txtApPaterno.getText(), txtApMaterno.getText(), txtDireccion.getText(),txtTelefono.getText(), txtUsuario.getText(), txtContrasena.getText());
				EmpleadoDAO empleadoDAO = new EmpleadoDAO();
				if(empleadoDAO.modificar(empleadoVO)){
					JOptionPane.showMessageDialog(null, "Empleado Actualizado!");
					dialogStage.close();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Verifica Los Campos");
			}
		}

    }
    public void cancelar(ActionEvent event) {
    	dialogStage.close();
    }
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setEmpleadoVO(EmpleadoVO empleadoVO) {
		this.empleadoVO = empleadoVO;
		
		txtNombre.setText(empleadoVO.getNombre());
		txtApPaterno.setText(empleadoVO.getApPaterno());
		txtApMaterno.setText(empleadoVO.getApMaterno());
		txtDireccion.setText(empleadoVO.getDireccion());
		txtTelefono.setText(empleadoVO.getTelefono());
		txtUsuario.setText(empleadoVO.getUsuario());
		txtContrasena.setText(empleadoVO.getPassword());
	}

}
