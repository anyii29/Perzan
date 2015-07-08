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

public class Categoria implements Initializable{
	private CategoriaVO catVO;
	private view.Main1 main1;
	private Stage dialogStage;
	private Validar validar;
	
	public Categoria(){
		catVO = new CategoriaVO();
		validar = new Validar();
	}
    @FXML
    private TextField txtNombreCat;

    @FXML
    private Button btnEliminarCat;

    @FXML
    private Button btnIngresarCat;

    @FXML
    private Button btnModificarCat;
    
    @FXML
    private Button btnCancelar;
    @FXML private TableView<CategoriaVO> tvCategoria;  
    @FXML private TableColumn<CategoriaVO, Integer> tcIdCat;
    @FXML private TableColumn<CategoriaVO, String> tcNombreCat; 

	private ObservableList<CategoriaVO> categorias;

    public void eliminarCat(ActionEvent event) {
    	if( catVO != null){
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Confirmación");
    		//alert.setHeaderText("Look, a Confirmation Dialog");
    		alert.setContentText("¿Desea Eliminar Este Producto? ");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    		    catVO.eliminarCat();
    		    tvCategoria.getItems().removeAll(categorias);
    		    fillTableCategoria();
    			txtNombreCat.setText("");
    		}
    		}
    	else{
    		alert(AlertType.ERROR,"No hay producto seleccionado.");
    	}
    }

    public void modificarCat(ActionEvent event) {
    	if(txtNombreCat.isDisable()){
    		txtNombreCat.setDisable(false);
    	}
    	else{
    		if(txtNombreCat.getText().isEmpty()){
        		alert(AlertType.ERROR,"Falta dato nombre.");
        	}
        	else{
        		if(validar.cadena(txtNombreCat.getText().trim())){
        			txtNombreCat.setStyle("-fx-background-color: white");
        			catVO.setNombre(txtNombreCat.getText().trim());
            		if(catVO.modificarCat()){
            			alert(AlertType.INFORMATION, "Categoria Actualizada");
            			//categorias.removeAll(categorias);
            		    tvCategoria.getItems().removeAll(categorias);
            			fillTableCategoria();
            			txtNombreCat.setText("");
            			disableFieldsCategoria();
            		}
            		else{
            			alert(AlertType.ERROR, "Fallo Ingreso");
            			
            		}
            		
        		}
        		else{
        			 txtNombreCat.setStyle("-fx-background-color: red");
        			 alert(AlertType.ERROR, "Datos no validos");
        		}
        		
        	}
    	}
    	
    }

    public void ingresarCat(ActionEvent event) {
    	if(txtNombreCat.isDisable()){
    		txtNombreCat.setDisable(false);
    		txtNombreCat.setText("");
    	}
    	else{
    		if(txtNombreCat.getText().isEmpty()){
        		alert(AlertType.ERROR,"Falta dato nombre.");
        	}
        	else{
        		if(validar.cadena(txtNombreCat.getText().trim())){
        			txtNombreCat.setStyle("-fx-background-color: white");
        			catVO.setNombre(txtNombreCat.getText().trim());
	        		CategoriaVO catVO = new CategoriaVO();
	        		catVO.setNombre(txtNombreCat.getText().trim());
	        		if(catVO.ingresarCat()){
	        			alert(AlertType.INFORMATION, "Categoria Agregada");
	        			txtNombreCat.setText(null);
	        		    tvCategoria.getItems().removeAll(categorias);
	        		    fillTableCategoria();
	        		}
	        		else{
	        			alert(AlertType.ERROR, "Fallo Ingreso");
	        			
	        		}
        		}
        		else{
        			 txtNombreCat.setStyle("-fx-background-color: red");
        			 alert(AlertType.ERROR, "Datos no validos");
        		}
        	}

    	}
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.tableCategoria();
		this.fillTableCategoria();
	}
	
/*
    
     * Metodo para desabilitar campos de categoria
     * */
    public void disableFieldsCategoria(){
    	txtNombreCat.setDisable(true);
    	//btnIngresarCat.setDisable(true);
    }

    /*
     * Metodo para habilitar campos de categoria
     * 
    public void enableFieldsCategoria(){
    	//txtNombreCat.setDisable(false);
    	btnIngresarCat.setDisable(false);
    }
    */

    /*
     * Metodo para delarar  de la tabla categoria
     * */
    public void tableCategoria(){
    	tcIdCat.setCellValueFactory(new PropertyValueFactory<CategoriaVO, Integer>("id"));
    	tcNombreCat.setCellValueFactory(new PropertyValueFactory<CategoriaVO, String>("nombre"));
    }
    /*
     * Metodo para llenar tabla categoria
     * */
    public void fillTableCategoria(){
    	categorias = FXCollections.observableArrayList(catVO.listarCategoria());
    	tvCategoria.getItems().addAll(categorias);
    }
/*
    
     * Metodo para obtener los datos de la Categoria seleccionada en la tabla Categoria
     * */
    public void selectedTableCategoria(){
    	if(tvCategoria.getSelectionModel().getSelectedItem() != null){
    		catVO = tvCategoria.getSelectionModel().getSelectedItem();
    		txtNombreCat.setText(catVO.getNombre());
    		disableFieldsCategoria();
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
