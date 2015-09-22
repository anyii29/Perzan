package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import view.Main1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Total implements Initializable{
	private float total;
	private boolean venta;
	private Stage dialogStage;
	private Main1 main1;
	private Validar validar;
	
	public Total() {
		// TODO Auto-generated constructor stub
		validar = new Validar();
		total = 0;
		venta = false;;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtCambio.setEditable(false);
		txtTotal.setEditable(false);
		txtCambio.setText("0");
	}


    @FXML
    private TextField txtEfectivo;

    @FXML
    private TextField txtCambio;

    @FXML
    private Button btnRegistrar;

    @FXML
    private TextField txtTotal;

    @FXML
    public void registrar(ActionEvent event) {
    	venta = true;
    	dialogStage.close();
    }

    @FXML
    public void salir(ActionEvent event) {
    	dialogStage.close();
    	
    }
    public void calcular(){
    	if(validar.entero(txtEfectivo.getText().trim())){
    		float val = Integer.valueOf(txtEfectivo.getText())-total;
    		if(val < 0){
    			txtCambio.setText("0");
    		}
    		else {
        		txtCambio.setText(String.valueOf(val));
			}
    	}
    	else{
    		if(txtCambio.getText().isEmpty()){
    			txtCambio.setText("0");
    		}
    		else{

        		txtCambio.setText("Error");
    		}    			
    	}
    }

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
		txtTotal.setText(String.valueOf(total));
	}

	public boolean getVenta() {
		return venta;
	}

	public void setVenta(boolean venta) {
		this.venta = venta;
	}

	public void setDialogStage(Stage dialogStage) {
		// TODO Auto-generated method stub
		this.dialogStage = dialogStage;
	}
	

}
