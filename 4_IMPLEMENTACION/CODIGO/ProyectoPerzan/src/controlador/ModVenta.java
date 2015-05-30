package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import modelo.ProductoDAO;
import modelo.ProductoVO;
import modelo.VentaDetVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModVenta implements Initializable{
	
	private VentaDetVO ventaDetVO = new VentaDetVO();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
    @FXML
    private TextField txtNombre;
    @FXML
    private Button btnAceptar;
    @FXML
    private TextField txtCantidad;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtPrecio;
	private Stage dialogStage;

    public void aceptar(ActionEvent event) {
    	if(txtNombre.getText().equals("")||txtCantidad.getText().equals("")||txtPrecio.getText().equals("")){
    		JOptionPane.showMessageDialog(null, "Completa Los Campos");
    	}
    	else{
			ProductoDAO productoDAO= new ProductoDAO();
			ProductoVO productoVO = productoDAO.getProducto(ventaDetVO.getId());
			if(productoDAO.got()){
				int i = 0;
				Validar validar = new Validar();
				if(!(validar.entero(txtCantidad.getText())) && !(txtCantidad.getText().equals("0"))){i++; JOptionPane.showMessageDialog(null, "La cantidad debe ser \n un dato entero diferente \n de 0");}
				if (i == 0){
					int cant = productoVO.getCantidad();
					int cantidad = Integer.parseInt(txtCantidad.getText());
					if((cant-cantidad) >= 0 ){
			    	    double precio = Double.valueOf(txtPrecio.getText());
			    	    double importe = precio*cantidad;
			    		VentaDetVO venta = new VentaDetVO(ventaDetVO.getId(),txtNombre.getText(),precio,cantidad,importe);
			    		Empleado.ventaDetVO = venta;
			    		Empleado.actualizado = true;
			    		JOptionPane.showMessageDialog(null, "Venta Actualizada");
			    		dialogStage.close();
					}
					else{
						JOptionPane.showMessageDialog(null, "No hay producto suficiente");
					}
				}
	    	}
		}		
    }
    public void cancelar(ActionEvent event) {
    	dialogStage.close();
    }

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public VentaDetVO getVentaDetVO() {
		return ventaDetVO;
	}

	public void setVentaDetVO(VentaDetVO ventaDetVO) {
		this.ventaDetVO = ventaDetVO;
		txtNombre.setText(ventaDetVO.getNombre());
		String nombre = String.valueOf(ventaDetVO.getCantidad());
	    txtCantidad.setText(nombre);
	    String precio = String.valueOf(ventaDetVO.getPrecio());
	    txtPrecio.setText(precio);
	}
}
