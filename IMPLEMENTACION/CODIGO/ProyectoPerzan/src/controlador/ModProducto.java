package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import modelo.ProductoDAO;
import modelo.ProductoVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class ModProducto implements Initializable {
	private ProductoVO productoVO;
	public ModProducto(){
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCantidad;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtPrecio;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Tooltip ttNombre;
    @FXML
    private Tooltip ttPrecio;
    @FXML
    private Tooltip ttCantidad;
	private Stage dialogStage;

   public void registrar(ActionEvent event) {
    	if(txtNombre.getText().equals("")|| txtPrecio.getText().equals("")|| txtCantidad.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Rellena todos los campos");
		}
		else{
			int i = 0;
			Validar validar = new Validar();
			if(!(validar.cadena(txtNombre.getText()))){i++; ttNombre.setText("Campo Erroneo");}else{ttNombre.setText(null);}
			if(!(validar.precio(txtPrecio.getText()))){i++; ttPrecio.setText("Campo Erroneo  Ej: \"10.00\"");}else{ttPrecio.setText(null);}
			if(!(validar.entero(txtCantidad.getText()))){i++; ttCantidad.setText("Campo Erroneo Ej: \"10\"");}else{ttCantidad.setText(null);}
			if(i == 0){
				int id = productoVO.getId();
				double precio= Double.valueOf(txtPrecio.getText());
				int cantidad=Integer.parseInt(txtCantidad.getText());
				ProductoVO productoVO = new ProductoVO(id , txtNombre.getText(), precio, cantidad);
				ProductoDAO productoDAO = new ProductoDAO();
				if(productoDAO.modificar(productoVO)){
					JOptionPane.showMessageDialog(null, "Producto Actualizado");
					txtNombre.setText(null);
					txtPrecio.setText(null);
					txtCantidad.setText(null);
					dialogStage.close();
				}
				else{
					JOptionPane.showMessageDialog(null, "Fallo Registro");
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
	public ProductoVO getProductoVO() {
		return productoVO;
	}
	public void setProductoVO(ProductoVO productoVO) {
		this.productoVO = productoVO;
		
		String precio = String.valueOf(productoVO.getPrecio());
		String cantidad = String.valueOf(productoVO.getCantidad());
		
		txtNombre.setText(productoVO.getNombre());
		txtPrecio.setText(precio);
		txtCantidad.setText(cantidad);
	}


}
