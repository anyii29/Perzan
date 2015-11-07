package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import modelo.CategoriaVO;
import modelo.MarcaVO;
import modelo.ProductoDAO;
import modelo.ProductoVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NuevoProducto implements Initializable{
	
	private CategoriaVO catVO;
	private MarcaVO marVO;
	private Stage dialogStage;
	private ProductoDAO productoDAO;
	private Validar validar;
	
	public NuevoProducto() {
		// TODO Auto-generated constructor stub
		catVO = new CategoriaVO();
		marVO = new MarcaVO();
		productoDAO = new ProductoDAO();
		validar = new Validar();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		cbTipo.getItems().addAll("interior", "exterior");
		this.fillCategoria();
		this.fillMarca();
		
	}
	@FXML
	private ComboBox<String> cbTipo;
	@FXML
	private ComboBox<MarcaVO> cbMarca;
	@FXML
	private ComboBox<CategoriaVO> cbCategoria;
    @FXML
    private Button btnSalir;

    @FXML
    private TextField txtDescripcionProd;

    @FXML
    private TextField txtPrecio1Prod;

    @FXML
    private TextField txtStockMinProd;

    @FXML
    private TextField txtStockMaxProd;

    @FXML
    private TextField txtPrecio2Prod;

    @FXML
    private Button btnGuardarProd;


    @FXML
    public void salir(ActionEvent event) {
    	dialogStage.close();
    }

    @FXML
    public void guardarProd(ActionEvent event) {
    	if (txtDescripcionProd.getText().trim().equals("")
				|| txtPrecio1Prod.getText().trim().equals("")
				|| txtPrecio2Prod.getText().trim().equals("")
				|| txtStockMaxProd.getText().trim().equals("")
				|| txtStockMinProd.getText().trim().equals("")
				|| cbCategoria.getValue() == null || cbMarca.getValue() == null
				|| cbCategoria.getValue() == null) {
			alert(AlertType.ERROR, "Completa los campos.");
		} else {
			int i = 0;
			if (validar.cadena(txtDescripcionProd.getText())) {
				txtDescripcionProd.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtDescripcionProd.setStyle("-fx-background-color: red");
			}
			if (validar.precio(txtPrecio1Prod.getText())) {
				txtPrecio1Prod.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtPrecio1Prod.setStyle("-fx-background-color: red");
			}
			if (validar.precio(txtPrecio2Prod.getText())) {
				txtPrecio2Prod.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtPrecio2Prod.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtStockMaxProd.getText())) {
				txtStockMaxProd.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtStockMaxProd.setStyle("-fx-background-color: red");
			}
			if (validar.entero(txtStockMinProd.getText())) {
				txtStockMinProd.setStyle("-fx-background-color: white");
			} else {
				i++;
				txtStockMinProd.setStyle("-fx-background-color: red");
			}
			if (i == 0) {
				int stockMax = Integer.parseInt(txtStockMaxProd.getText());
				int stockMin = Integer.parseInt(txtStockMinProd.getText());
				float precio1 = Float.valueOf(txtPrecio1Prod.getText());
				float precio2 = Float.valueOf(txtPrecio2Prod.getText());
				if (precio1 > 0 && precio2 > 0 && precio1 > precio2
						&& stockMax > 0 && stockMin > 0 && stockMax > stockMin) {
					CategoriaVO catVO = cbCategoria.getValue();
					MarcaVO marVO = cbMarca.getValue();
					String tipo = (String) cbTipo.getValue();
					ProductoVO productoVO = new ProductoVO(0, catVO,
							txtDescripcionProd.getText().trim(), marVO,
							precio1, precio2, 0, stockMax, stockMin, tipo);
						if (productoDAO.registrar(productoVO)) {
							alert(AlertType.INFORMATION, "Producto registrado.");
							txtDescripcionProd.setText("");
							txtPrecio1Prod.setText("");
							txtPrecio2Prod.setText("");
							txtStockMaxProd.setText("");
							txtStockMinProd.setText("");
							cbCategoria.setValue(null);
							cbMarca.setValue(null);
							cbTipo.setValue(null);
						} else {
							alert(AlertType.ERROR, "Falló registro.");
						}
				} else {
					alert(AlertType.INFORMATION,
							"Precios y Stock deben ser diferentes de 0, \n"
									+ " Precio1 debe ser mayor que precio2,"
									+ "Stock max debe ser mayor que Stock min.");
				}
			} else {
				alert(AlertType.ERROR, "Verifica los campos.");
			}
		}
    }
    
    /*
	 * Metodo para llenar ComboBox categoria
	 */
	public void fillCategoria() {
		ObservableList<CategoriaVO> categorias = FXCollections.observableArrayList(catVO.listarCategoria(true));
		cbCategoria.setItems(categorias);
	}

	/*
	 * Metodo para llenar ComboBox marca
	 */
	public void fillMarca() {
		ObservableList<MarcaVO> marcas = FXCollections.observableArrayList(marVO.listarMarca(true));
		cbMarca.setItems(marcas);
	}
	
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
