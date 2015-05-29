package application;
	
import java.io.IOException;

import modelo.EmpleadoVO;
import modelo.ProductoVO;
import modelo.VentaDetVO;
import controlador.Administrador;
import controlador.Empleado;
import controlador.Login;
import controlador.LoginEmp;
import controlador.ModEmpleado;
import controlador.ModProducto;
import controlador.ModVenta;
import controlador.Principal;
import controlador.Splash;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Main1 extends Application {
	
    private Stage primaryStage;
    private BorderPane rootLayout;
    private Principal controlleroot;
	private String usuario;
	@SuppressWarnings("unused")
	private controlador.Principal principal;
    public Main1() {
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PERZAN");
        RootLayout();
        empty();
        splash();
    }
    public void RootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main1.class.getResource("Principal.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image("images/perzanIco.png"));
            primaryStage.setResizable(false);
            //primaryStage.show();
            controlleroot = loader.getController();
			controlleroot.setMain1(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	public void showLoginAdm() {
		try {
			empty();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main1.class.getResource("Login.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Login Administrador");
			dialogStage.getIcons().add(new Image("images/perzanIco.png"));
			dialogStage.initStyle(StageStyle.TRANSPARENT); 
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialogStage.setScene(scene);
			Login controller = loader.getController();
			controller.setDialogStage(dialogStage);
			primaryStage.opacityProperty().set(0.7);
			dialogStage.showAndWait();
			primaryStage.opacityProperty().set(1.0);
			usuario = controller.getUsuario();
			if(usuario != null){
				viewAdministrador();
			}
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	public void showLoginEmp() {
		try {
			empty();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main1.class.getResource("LoginEmp.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Login Empleado");
			dialogStage.getIcons().add(new Image("images/perzanIco.png"));
			dialogStage.initStyle(StageStyle.TRANSPARENT); 
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialogStage.setScene(scene);
			LoginEmp controller = loader.getController();
			controller.setDialogStage(dialogStage);
			primaryStage.opacityProperty().set(0.7);
			dialogStage.showAndWait();
			primaryStage.opacityProperty().set(1.0);
			usuario = controller.getUsuario();
			if(usuario != null){
				viewEmpleado(usuario);
			}
			
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	public void showModProducto(ProductoVO productoVO) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main1.class.getResource("ModProducto.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Modificar Producto");
			dialogStage.getIcons().add(new Image("images/perzanIco.png"));
			dialogStage.initStyle(StageStyle.TRANSPARENT); 
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialogStage.setScene(scene);
			ModProducto controller = loader.getController();
			primaryStage.opacityProperty().set(0.7);
			controller.setDialogStage(dialogStage);
			controller.setProductoVO(productoVO);
			dialogStage.showAndWait();
			primaryStage.opacityProperty().set(1.0);
			
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	public void showModEmpleado(EmpleadoVO empleadoVO) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main1.class.getResource("ModEmpleado.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Modificar Empleado");
			dialogStage.getIcons().add(new Image("images/perzanIco.png"));
			dialogStage.initStyle(StageStyle.TRANSPARENT); 
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialogStage.setScene(scene);
			ModEmpleado controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setEmpleadoVO(empleadoVO);
			primaryStage.opacityProperty().set(0.7);
			dialogStage.showAndWait();
			primaryStage.opacityProperty().set(1.0);
			
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	public void showModVenta(VentaDetVO ven) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main1.class.getResource("ModVenta.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Modificar Empleado");
			dialogStage.getIcons().add(new Image("images/perzanIco.png"));
			dialogStage.initStyle(StageStyle.TRANSPARENT); 
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialogStage.setScene(scene);
			ModVenta controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setVentaDetVO(ven);
			primaryStage.opacityProperty().set(0.7);
			dialogStage.showAndWait();
			primaryStage.opacityProperty().set(1.0);
			
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	public void viewAdministrador() {
		try {
			FXMLLoader loader = new FXMLLoader(Main1.class.getResource("Administrador.fxml"));
			AnchorPane overviewPage = (AnchorPane) loader.load();
			rootLayout.setCenter(overviewPage);
			rootLayout.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Administrador controller = loader.getController();
			controller.setMain1(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    public void viewEmpleado(String usuario) {
        try {
        	FXMLLoader loader = new FXMLLoader(Main1.class.getResource("Empleado.fxml"));
			AnchorPane overviewPage = (AnchorPane) loader.load();
			rootLayout.setCenter(overviewPage);
			rootLayout.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Empleado controller = loader.getController();
			controller.setMain1(this);
			controller.setUsuario(usuario);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void empty() {
        try {
        	FXMLLoader loader = new FXMLLoader(Main1.class.getResource("Empty.fxml"));
			AnchorPane overviewPage = (AnchorPane) loader.load();
			rootLayout.setCenter(overviewPage); 
			rootLayout.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Stage getPrimaryStage() {
    	return primaryStage;
    }
    public void splash(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main1.class.getResource("Splash.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image("images/perzanIco.png"));
			dialogStage.initStyle(StageStyle.TRANSPARENT); 
			dialogStage.initModality(Modality.NONE);
			dialogStage.setResizable(false);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialogStage.setScene(scene);
			Splash controller = loader.getController();
			controller.setDialogStage(dialogStage);
			dialogStage.showAndWait();
			primaryStage.show();
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);	
	}
}
