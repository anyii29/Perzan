package controlador;

import modelo.Reportes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ReporteActivo {
	
	String ruta;
	Stage dialogStage;
	Reportes reporte;
	
	public ReporteActivo() {
		// TODO Auto-generated constructor stub
		ruta = "";
		reporte = new Reportes();
	}

    @FXML
    private CheckBox cbActivo;

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private CheckBox cbInactivo;

    @FXML
    public void cbActivoChange(ActionEvent event) {
    	cbInactivo.setSelected(false);
    }

    @FXML
    public void cbInactivoChange(ActionEvent event) {
    	cbActivo.setSelected(false);
    }
    
    @FXML
    public void aceptar(ActionEvent event) {
    	if(cbActivo.isSelected() || cbInactivo.isSelected()){
    		if(cbActivo.isSelected()){
    			reporte.loadReport(ruta,true);
    		}
    		else{
    			reporte.loadReport(ruta,false);
    		}
    		dialogStage.close();
			reporte.showReport();
    	}
    	else{
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Information Dialog");
    		alert.setHeaderText(null);
    		alert.setContentText("Selecciona un tipo.");
    		alert.showAndWait();
    	}

    }

    @FXML
    public void cancelar(ActionEvent event) {
    	dialogStage.close();
    }

	public void setDialogStage(Stage dialogStage) {
		// TODO Auto-generated method stub
		this.dialogStage = dialogStage;
		
	}

	public void setRuta(String ruta) {
		// TODO Auto-generated method stub
		this.ruta = ruta;
		
	}

}
