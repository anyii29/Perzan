package controlador;

import java.time.format.DateTimeFormatter;

import modelo.Reportes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ReporteProcesos {
	private String ruta, nombreReporte;
	private Stage dialogStage;
	private Reportes reporte;
	
	public ReporteProcesos() {
		// TODO Auto-generated constructor stub
		ruta = "";
		reporte = new Reportes();
	}
	@FXML
	private Label lblNombreReporte;
	
    @FXML
    private DatePicker dpInicio;

    @FXML
    private DatePicker dpFinal;

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    public void aceptar(ActionEvent event) {
    	if(dpInicio.getValue() != null && dpFinal.getValue() != null){
    		String pattern = "yyyy-MM-dd";
    		DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
    		String inicio = df.format(dpInicio.getValue());
    		String fin = df.format(dpFinal.getValue());
    		dialogStage.close();
    		reporte.loadReportCV(ruta, inicio, fin);
    		reporte.showReport();
    	}
    	else{
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Information Dialog");
    		alert.setHeaderText(null);
    		alert.setContentText("Seleccione las fecha de inicio \n y fecha final.");
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

	public void setRuta(String ruta, String nombreReporte) {  
		// TODO Auto-generated method stub
		this.ruta = ruta;
		this.nombreReporte = nombreReporte;
		lblNombreReporte.setText(this.nombreReporte);
	}
}
