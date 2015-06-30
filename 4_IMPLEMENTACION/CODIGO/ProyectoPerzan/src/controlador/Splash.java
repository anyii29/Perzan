package controlador;
import java.net.URL;
import java.util.ResourceBundle;

import view.Main1;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Splash implements Initializable{
	
	public Splash(){	
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		iniciar();
		//run();
	}
	@FXML
	private ProgressBar pbBarra;
	@FXML
	private ImageView ivImage;
	@FXML
	private Label lblProgress;
	private Stage dialogStage;
	Task<Void> task;
	boolean hecho = false;
	Timeline timeline;
	Thread th;
	view.Main1 main1;
	private Status dat;
	public void run(){
		  task = new Task<Void>() {
		      @Override public Void call() {
		        for (int i = 1; i < 100; i++) {
		          try {
		            Thread.sleep(30);
		          } catch (InterruptedException e) {
		            e.printStackTrace();
		          }
		          updateProgress(i, 100);
		          }
		     
		        //cerrar();
		       // main1.dStage.close();
		        return null;
		      }
		    };
		    pbBarra.progressProperty().bind(task.progressProperty());
		    th = new Thread(task);
		    th.setDaemon(true);
		    th.start();
		    System.out.println(th.getPriority());
		    
				timeline.statusProperty();{
			    	cerrar();
			    }
			
			
	}
	public void iniciar(){
		timeline = new Timeline(new KeyFrame(
		        Duration.millis(3000),
		        ae -> run()));
		//timeline.setCycleCount(Animation.Status);
		timeline.play();
		timeline.getOnFinished();{
			System.out.println("Entro");
			//dialogStage.close();
			//main1.dStage.close();
			
		}
		/*while(timeline.getCurrentRate() >= 0.0){
			System.out.println(timeline.getCurrentRate());
		}
		try{
			dialogStage.close();
			System.out.println("Hecho");
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("error");
		}*/
	}
	public void cerrar(){
		//th.interrupt();
		System.out.println("Cerrar");
			try{
				//th.interrupt();
				//task.cancel();
				//th.destroy();
				//th.resume();
				//th.setPriority(1);
				//th.suspend();
				th.checkAccess();
				//timeline.stop();
				dialogStage.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("Cerrado");			
		}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;		
		System.out.println("Stage establecido");
		//cerrar();
	}
}
