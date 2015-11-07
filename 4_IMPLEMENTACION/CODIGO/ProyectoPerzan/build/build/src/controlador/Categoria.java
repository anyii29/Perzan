package controlador;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import modelo.CategoriaVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Categoria implements Initializable{
	private CategoriaVO catVO;
	@SuppressWarnings("unused")
	private view.Main1 main1;
	private Stage dialogStage;
	private Validar validar;
	
	public Categoria(){
		catVO = new CategoriaVO();
		validar = new Validar();
	}
    @FXML private TextField txtNombreCat;
    @FXML private Button btnEliminarCat;
    @FXML private Button btnIngresarCat;
    @FXML private Button btnModificarCat;
    @FXML private Button btnCancelar;
    @FXML private Button btnNuevo;
    @FXML private Button btnResEliminados;
    @FXML private CheckBox ckbEliminados;
    @FXML private TableView<CategoriaVO> tvCategoria;  
    @FXML private TableColumn<CategoriaVO, String> tcNombreCat; 

	private ObservableList<CategoriaVO> categorias;
	private ObservableList<CategoriaVO> categoriasDel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.tableCategoria();
		this.fillTableCategoria();
		this.disableFieldsCategoria();
		btnResEliminados.setVisible(false);
	}

    public void eliminarCat(ActionEvent event) {
    	if( catVO != null){
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Confirmación");
    		//alert.setHeaderText("Look, a Confirmation Dialog");
    		alert.setContentText("¿Desea eliminar el registro? ");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    			if(catVO.eliminarCat()){
	    		    //tvCategoria.getItems().removeAll(categorias);
	    		    //fillTableCategoria();
	    			categorias.remove(catVO);
	    			categoriasDel.add
	    			(catVO);
    				txtNombreCat.setText("");
	    			disableFieldsCategoria();
    			}
    			else{
    				alert(AlertType.ERROR, "Falló eliminación.");
    			}
    		}
    		}
    	else{
    		alert(AlertType.ERROR,"No hay producto seleccionado.");
    	}
    }

    public void modificarCat(ActionEvent event) {
    	enableFieldsCategoria();
    	btnModificarCat.setDisable(true);
    	btnIngresarCat.setDisable(false);
    	txtNombreCat.setDisable(false);
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
        			if(catVO == null){
        				CategoriaVO catVO = new CategoriaVO();
    	        		catVO.setNombre(txtNombreCat.getText().trim());
    	        		if(catVO.ingresarCat()){
    	        			alert(AlertType.INFORMATION, "Categoria agregada.");
    	        			txtNombreCat.setText(null);
    	        		    tvCategoria.getItems().removeAll(categorias);
    	        		    fillTableCategoria();
    	        		}
    	        		else{
    	        			alert(AlertType.ERROR, "No se agrego el registro.");
    	        			//alert(AlertType.INFORMATION, catVO.getMesssage());
    	        		}
        			}
        			else{
        				catVO.setNombre(txtNombreCat.getText().trim());
        				if(catVO.modificarCat()){
    	        			alert(AlertType.INFORMATION, "Categoria actualizada.");
    	        			txtNombreCat.setText(null);
    	        		    tvCategoria.getItems().removeAll(categorias);
    	        		    fillTableCategoria();
    	        		    disableFieldsCategoria();
    	        		}
    	        		else{
    	        			alert(AlertType.ERROR, "Falló actalización.");
    	        			
    	        		}
        			}
	        		
        		}
        		else{
        			 txtNombreCat.setStyle("-fx-background-color: red");
        			 alert(AlertType.ERROR, "Datos no validos");
        		}
        	}

    	}
    	
    }
    public void eliminados(ActionEvent event){
    	if(ckbEliminados.isSelected()){
    		btnEliminarCat.setVisible(false);
    		btnIngresarCat.setVisible(false);
    		btnModificarCat.setVisible(false);
    		btnNuevo.setVisible(false);
    		btnResEliminados.setVisible(true);
        	tvCategoria.setItems(categoriasDel);
    	}
    	else{
    		btnEliminarCat.setVisible(true);
    		btnIngresarCat.setVisible(true);
    		btnModificarCat.setVisible(true);
    		btnNuevo.setVisible(true);
    		btnResEliminados.setVisible(false);
    		tvCategoria.setItems(categorias);
    	}
    }
    public void resEliminado(ActionEvent event){
    	if(catVO != null){
    		if(catVO.modificarEliminado()){
    			alert(AlertType.INFORMATION, "Registro restaurado.");
    			categoriasDel.remove(catVO);
    			categorias.add(catVO);
    			catVO = null;
    		}
    		else{
    			alert(AlertType.INFORMATION, "Registro no restaurado.");
    		}
    	}
    	else{
    		alert(AlertType.INFORMATION, "Seleccione un registro.");
    	}
    }
    
    /*
     * metodo para habiliar campo para ingresar un nuevo producto
    */
    public void nuevo(ActionEvent event){
    	txtNombreCat.setText("");
    	txtNombreCat.setDisable(false);
    	btnIngresarCat.setDisable(false);
    	catVO = null;
    }
	
/*
    
     * Metodo para desabilitar campos de categoria
     * */
    public void disableFieldsCategoria(){
    	txtNombreCat.setDisable(true);
		btnEliminarCat.setDisable(true);
		btnModificarCat.setDisable(true);
    	btnIngresarCat.setDisable(true);
    }

    /*
     * Metodo para habilitar campos de categoria
     *
    */
    public void enableFieldsCategoria(){
    	//txtNombreCat.setDisable(false);
		btnEliminarCat.setDisable(false);
		btnModificarCat.setDisable(false);
    }
   

    /*
     * Metodo para declarar  de la tabla categoria
     * */
    public void tableCategoria(){
    	tcNombreCat.setCellValueFactory(new PropertyValueFactory<CategoriaVO, String>("nombre"));
    }
    /*
     * Metodo para llenar tabla categoria
     * */
    public void fillTableCategoria(){
    	catVO = new CategoriaVO();
    	categorias = FXCollections.observableArrayList(catVO.listarCategoria(true));
    	categoriasDel = FXCollections.observableArrayList(catVO.listarCategoria(false));
    	tvCategoria.setItems(categorias);
    }
/*
    
     * Metodo para obtener los datos de la Categoria seleccionada en la tabla Categoria
     * */
    public void selectedTableCategoria(){
    	if(tvCategoria.getSelectionModel().getSelectedItem() != null){
    		catVO = tvCategoria.getSelectionModel().getSelectedItem();
    		txtNombreCat.setText(catVO.getNombre());
    		enableFieldsCategoria();
    		btnIngresarCat.setDisable(true);
    		txtNombreCat.setDisable(true);
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
