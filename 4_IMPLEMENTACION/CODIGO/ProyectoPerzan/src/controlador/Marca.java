package controlador;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import modelo.CategoriaVO;
import modelo.MarcaVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Marca implements Initializable{

	private MarcaVO marVO;
	private view.Main1 main1;
	private Stage dialogStage;
	private Validar validar;
	
	public Marca(){
		marVO = new MarcaVO();
		validar = new Validar();
	}
    @FXML
    private Button btnModificarMar;

    @FXML
    private TextField txtNombreMar;

    @FXML
    private Button btnIngresarMar;

    @FXML
    private Button btnEliminarMar;
    @FXML
    private Button btnCancelar;
    @FXML private TableView<MarcaVO> tvMarca; 
    @FXML private TableColumn<MarcaVO, Integer> tcIdMar;  
    @FXML private TableColumn<MarcaVO, String> tcNombreMar;

	private ObservableList<MarcaVO> marcas;


	public void eliminarMar(ActionEvent event) {
    	if( marVO != null){
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Confirmación");
    		//alert.setHeaderText("Look, a Confirmation Dialog");
    		alert.setContentText("¿Desea Eliminar Esta Marca? ");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    		    marVO.eliminarMar();
    		    tvMarca.getItems().removeAll(marcas);
    		    fillTableMarca();
    			txtNombreMar.setText("");
    		}
    		}
    	else{
    		alert(AlertType.ERROR,"No hay producto seleccionado.");
    	}
    }

    public void modificarMar(ActionEvent event) {
    	if(txtNombreMar.isDisable()){
    		txtNombreMar.setDisable(false);
    	}
    	else{
    		if(txtNombreMar.getText().isEmpty()){
        		alert(AlertType.ERROR,"Falta dato nombre.");
        	}
        	else{

    			if(validar.cadena(txtNombreMar.getText().trim())){
        			txtNombreMar.setStyle("-fx-background-color: white");
        		marVO.setNombre(txtNombreMar.getText().trim());
        		if(marVO.modificarMar()){
        			alert(AlertType.INFORMATION, "Marca Actualizada");
        			//categorias.removeAll(categorias);
        		    tvMarca.getItems().removeAll(marcas);
        			fillTableMarca();
        			txtNombreMar.setText("");
        			disableFieldsMarca();
        		}
        		else{
        			alert(AlertType.ERROR, "Fallo Ingreso");
        			
        		}
    		}
    		else{
       			 txtNombreMar.setStyle("-fx-background-color: red");
       			 alert(AlertType.ERROR, "Datos no validos");
       		}
    			
        	}
    	}
    	
    }

    public void ingresarMar(ActionEvent event) {
    	if(txtNombreMar.isDisable()){
    		txtNombreMar.setDisable(false);
    		txtNombreMar.setText("");
    	}
    	else{
    		if(txtNombreMar.getText().isEmpty()){
        		alert(AlertType.ERROR,"Falta dato nombre.");
    		}
    		else{
    			if(validar.cadena(txtNombreMar.getText().trim())){
        			txtNombreMar.setStyle("-fx-background-color: white");
            		MarcaVO MarVO = new MarcaVO();
            		MarVO.setNombre(txtNombreMar.getText().trim());
            		if(MarVO.ingresarMar()){
            			alert(AlertType.INFORMATION, "Marca Agregada");
            			txtNombreMar.setText(null);
            		    tvMarca.getItems().removeAll(marcas);
            		    fillTableMarca();
            		}
            		else{
            			alert(AlertType.ERROR, "Fallo Ingreso");
            			
            		}
            	}
    		else{
    			 txtNombreMar.setStyle("-fx-background-color: red");
    			 alert(AlertType.ERROR, "Datos no validos");
    		}
    		
    		}
    	}
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		this.tableMarca();
		this.fillTableMarca();
	}
	
/*
    
     * Metodo para desabilitar campos de marca
     * */
    public void disableFieldsMarca(){
    	txtNombreMar.setDisable(true);
    	//btnIngresarMar.setDisable(true);
    }
/*
    
     * Metodo para habilitar campos de marca
     * */
    /*public void enableFieldsMarca(){
    	txtNombreMar.setDisable(false);
    	btnIngresarMar.setDisable(false);
    }*/
    
    /*
     * Metodo para delarar  de la tabla marca
     * */
    public void tableMarca(){
    	tcIdMar.setCellValueFactory(new PropertyValueFactory<MarcaVO, Integer>("id"));
    	tcNombreMar.setCellValueFactory(new PropertyValueFactory<MarcaVO, String>("nombre"));
    }
    /*
     * Metodo para llenar tabla marca
     * */
    public void fillTableMarca(){
    	marcas = FXCollections.observableArrayList(marVO.listarMarca());
    	tvMarca.getItems().addAll(marcas);
    }
/*
    
     * Metodo para obtener los datos de la Marca seleccionada en la tabla marca
     * */
    public void selectedTableMarca(){
    	if(tvMarca.getSelectionModel().getSelectedItem() != null){
    		marVO = tvMarca.getSelectionModel().getSelectedItem();
    		txtNombreMar.setText(marVO.getNombre());
    		disableFieldsMarca();
    	}
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
    

    public void setDialogStage(Stage dialogStage) {
		 this.dialogStage = dialogStage;
	}

    public void cancelar(ActionEvent event){
    	dialogStage.close();
    }
	public void setMain1(view.Main1 main1) {
		this.main1= main1;
	}

}
