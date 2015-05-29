package controlador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Splash implements Initializable{
	
	public Splash(){	
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		run();
	}
	@FXML
	private ProgressBar pbBarra;
	@FXML
	private ImageView ivImage;
	@FXML
	private Label lblProgress;
	private Stage dialogStage;
	Thread thread = new Thread();
	public void run(){
		new Thread(new Runnable() {
		    @Override public void run() {
		        for (int i=1; i<=10000; i++) {
		            final int counter = i;
		            Platform.runLater(new Runnable() {
		                @Override public void run() {
		                    pbBarra.setProgress(counter/10000.0);
		                    if(counter == 10000){
		                    	dialogStage.close();
		                    }
		                }
		            });
		        }
		    }
		}).start();
	}
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;		
	}
}
