package view;

import java.io.IOException;

import modelo.CategoriaVO;
import modelo.EmpleadoVO;
import modelo.Logger;
import modelo.LoginEmpVO;
import modelo.ProductoVO;
import modelo.VentaDetVO;
import controlador.Administrador;
import controlador.Categoria;
import controlador.Empleado;
import controlador.LoginEmp;
import controlador.Marca;
import controlador.NuevoCliente;
import controlador.NuevoProducto;
import controlador.NuevoProveedor;
import controlador.Principal;
import controlador.Splash;
import controlador.Total;
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
	private AnchorPane overviewPage;
	private FXMLLoader loader;
	private Principal controlleroot;
	private LoginEmpVO usuario;
	public Stage dStage;
	private Logger log;
	@SuppressWarnings("unused")
	private controlador.Principal principal;
	public Main1() {
		log = new Logger();
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("PERZAN");
		//splash();
		RootLayout();
		empty();
		loader = new FXMLLoader(Main1.class.getResource("fxml/Empleado.fxml"));
		try {
			overviewPage = (AnchorPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//splash();

	}
	public void RootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main1.class.getResource("fxml/Principal.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("view/images/perzanIco.png"));
			primaryStage.setResizable(false);
			primaryStage.show();
			//dStage.close();
			controlleroot = loader.getController();
			controlleroot.setMain1(this);
		} catch (IOException e) {
			log.printLog(e.getMessage(), this.getClass().toString());
		}
	}
	public void showLoginEmp() {
		try {
			empty();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main1.class.getResource("fxml/LoginEmp.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Login Empleado");
			dialogStage.getIcons().add(new Image("view/images/perzanIco.png"));
			dialogStage.initStyle(StageStyle.TRANSPARENT); 
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialogStage.setScene(scene);
			LoginEmp controller = loader.getController();
			controller.setDialogStage(dialogStage);
			primaryStage.opacityProperty().set(0.7);
			dialogStage.showAndWait();
			primaryStage.opacityProperty().set(1.0);
			usuario = controller.getUsuario();
			if(LoginEmp.getTipo().equals("admin") && controller.getUsuario() != null){
				viewAdministrador();
				Principal.loginAdm = true;
			}
			else{
				if(usuario != null && (usuario.getTipo().equals("admin")
						|| usuario.getTipo().equals("empleado"))){
					viewEmpleado(usuario);
					Principal.loginEmp = true;
				}
			}
		}
		catch (IOException e) {	
			log.printLog(e.getMessage(), this.getClass().toString());
		}
	}
	public void nuevoCliente() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main1.class.getResource("fxml/NuevoCliente.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Nuevo Cliente");
			dialogStage.getIcons().add(new Image("view/images/perzanIco.png"));
			dialogStage.initStyle(StageStyle.TRANSPARENT); 
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialogStage.setScene(scene);
			NuevoCliente controller = loader.getController();
			controller.setDialogStage(dialogStage);
			primaryStage.opacityProperty().set(0.7);
			dialogStage.showAndWait();
			primaryStage.opacityProperty().set(1.0);

		} catch (IOException e) {	
			log.printLog(e.getMessage(), this.getClass().toString());
		}
	}public void nuevoProducto() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main1.class.getResource("fxml/NuevoProducto.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Nuevo Cliente");
			dialogStage.getIcons().add(new Image("view/images/perzanIco.png"));
			dialogStage.initStyle(StageStyle.TRANSPARENT); 
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialogStage.setScene(scene);
			NuevoProducto controller = loader.getController();
			controller.setDialogStage(dialogStage);
			primaryStage.opacityProperty().set(0.7);
			dialogStage.showAndWait();
			primaryStage.opacityProperty().set(1.0);

		} catch (IOException e) {	
			log.printLog(e.getMessage(), this.getClass().toString());
		}
	}
	public void nuevoProveedor() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main1.class.getResource("fxml/NuevoProveedor.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Nuevo Cliente");
			dialogStage.getIcons().add(new Image("view/images/perzanIco.png"));
			dialogStage.initStyle(StageStyle.TRANSPARENT); 
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialogStage.setScene(scene);
			NuevoProveedor controller = loader.getController();
			controller.setDialogStage(dialogStage);
			primaryStage.opacityProperty().set(0.7);
			dialogStage.showAndWait();
			primaryStage.opacityProperty().set(1.0);

		} catch (IOException e) {	
			log.printLog(e.getMessage(), this.getClass().toString());
		}
	}
	public void viewAdministrador() {
		try {
			FXMLLoader loader = new FXMLLoader(Main1.class.getResource("fxml/Administrador.fxml"));
			AnchorPane overviewPage = (AnchorPane) loader.load();
			rootLayout.setCenter(overviewPage);
			//rootLayout.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Administrador controller = loader.getController();
			controller.setMain1(this);

		} catch (IOException e) {
			log.printLog(e.getMessage(), this.getClass().toString());
		}
	}
	public void viewEmpleado(LoginEmpVO usuario) {
		rootLayout.setCenter(overviewPage);
		//rootLayout.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Empleado controller = loader.getController();
		controller.setMain1(this);
		controller.setUsuario(usuario);
	}
	public void empty() {
		try {
			FXMLLoader loader = new FXMLLoader(Main1.class.getResource("fxml/Empty.fxml"));
			AnchorPane overviewPage = (AnchorPane) loader.load();
			rootLayout.setCenter(overviewPage); 
			//rootLayout.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		} catch (IOException e) {
			log.printLog(e.getMessage(), this.getClass().toString());
		}
	}
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public void splash(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main1.class.getResource("fxml/Splash.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			dStage = new Stage();
			dStage.getIcons().add(new Image("view/images/perzanIco.png"));
			dStage.initStyle(StageStyle.TRANSPARENT); 
			//dialogStage.initModality(Modality.WINDOW_MODAL);
			dStage.setResizable(false);
			dStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dStage.setScene(scene);
			Splash controller = loader.getController();
			controller.setDialogStage(dStage);
			//System.out.println("show and wait");
			dStage.showAndWait();
			//controller.initJoin();
			//System.out.println("wait 4000 milis");
			//Thread.sleep(4000);
			//System.out.println("close");
			//dialogStage.close();
			//primaryStage.show();
		} catch (IOException e) {	
			log.printLog(e.getMessage(), this.getClass().toString());
		}
	}
	public void showCategoria() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main1.class.getResource("fxml/Categoria.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Categoria");
			dialogStage.getIcons().add(new Image("view/images/perzanIco.png"));
			dialogStage.initStyle(StageStyle.TRANSPARENT); 
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialogStage.setScene(scene);
			Categoria controller = loader.getController();
			controller.setDialogStage(dialogStage);
			primaryStage.opacityProperty().set(0.7);
			dialogStage.showAndWait();
			primaryStage.opacityProperty().set(1.0);

		} catch (IOException e) {	
			log.printLog(e.getMessage(), this.getClass().toString());
		}
	}
	public void showMarca() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main1.class.getResource("fxml/Marca.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Marca");
			dialogStage.getIcons().add(new Image("view/images/perzanIco.png"));
			dialogStage.initStyle(StageStyle.TRANSPARENT); 
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialogStage.setScene(scene);
			Marca controller = loader.getController();
			controller.setDialogStage(dialogStage);
			primaryStage.opacityProperty().set(0.7);
			dialogStage.showAndWait();
			primaryStage.opacityProperty().set(1.0);

		} catch (IOException e) {	
			log.printLog(e.getMessage(), this.getClass().toString());
		}
	}
	public boolean showTotal(Float total) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main1.class.getResource("fxml/Total.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Marca");
			dialogStage.getIcons().add(new Image("view/images/perzanIco.png"));
			dialogStage.initStyle(StageStyle.TRANSPARENT); 
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialogStage.setScene(scene);
			Total controller = loader.getController();
			controller.setTotal(total);
			controller.setDialogStage(dialogStage);
			primaryStage.opacityProperty().set(0.7);
			dialogStage.showAndWait();
			primaryStage.opacityProperty().set(1.0);
			return controller.getVenta();
		} catch (IOException e) {	
			log.printLog(e.getMessage(), this.getClass().toString());
			return false;
		}
	}
	public static void main(String[] args) {
		launch(args);	
	}
}
