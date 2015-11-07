package controlador;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Splash extends Application{
	
	public Splash(){	
	}
	@FXML
	private ProgressBar pbBarra;
	@FXML
	private ImageView ivImage;
	@FXML
	private Label lblProgress;
	private Stage dialogStage;
	 public static final String APPLICATION_ICON =
	            "view/images/Perzan1.png";
	    public static final String SPLASH_IMAGE =
	            "view/images/Perzan1.png";
	 
	    private Pane splashLayout;
	    private ProgressBar loadProgress;
	    private Label progressText;
	    private Stage mainStage;
	    private static final int SPLASH_WIDTH = 676;
	    private static final int SPLASH_HEIGHT = 227;
	 
	    public static void main(String[] args) throws Exception {
	        launch(args);
	    }
	 
	    @Override
	    public void init() {
	        ImageView splash = new ImageView(new Image(
	                SPLASH_IMAGE
	        ));
	        loadProgress = new ProgressBar();
	        loadProgress.setPrefWidth(32);
	        progressText = new Label("Will find friends for peanuts . . .");
	        splashLayout = new VBox();
	        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
	        progressText.setAlignment(Pos.CENTER);
	        splashLayout.setStyle(
	                "-fx-padding: 5; " +
	                "-fx-background-color: cornsilk; " +
	                "-fx-border-width:5; " +
	                "-fx-border-color: " +
	                    "linear-gradient(" +
	                        "to bottom, " +
	                        "chocolate, " +
	                        "derive(chocolate, 50%)" +
	                    ");"
	        );
	        splashLayout.setEffect(new DropShadow());
	    }
	 
	    @Override
	    public void start(final Stage initStage) throws Exception {
	        final Task<ObservableList<String>> friendTask = new Task<ObservableList<String>>() {
	            @Override
	            protected ObservableList<String> call() throws InterruptedException {
	                ObservableList<String> foundFriends =
	                        FXCollections.<String>observableArrayList();
	                ObservableList<String> availableFriends =
	                        FXCollections.observableArrayList(
	                                "Fili", "Kili", "Oin", "Gloin", "Thorin",
	                                "Dwalin", "Balin", "Bifur", "Bofur",
	                                "Bombur", "Dori", "Nori", "Ori"
	                        );
	 
	                updateMessage("Finding friends . . .");
	                for (int i = 0; i < availableFriends.size(); i++) {
	                    Thread.sleep(400);
	                    updateProgress(i + 1, availableFriends.size());
	                    String nextFriend = availableFriends.get(i);
	                    foundFriends.add(nextFriend);
	                    updateMessage("Finding friends . . . found " + nextFriend);
	                }
	                Thread.sleep(400);
	                updateMessage("All friends found.");
	 
	                return foundFriends;
	            }
	        };
	 
	        showSplash(
	                initStage,
	                friendTask,
	                () -> showMainStage(friendTask.valueProperty())
	        );
	        new Thread(friendTask).start();
	    }
	 
	    private void showMainStage(
	            ReadOnlyObjectProperty<ObservableList<String>> friends
	    ) {
	        mainStage = new Stage(StageStyle.DECORATED);
	        mainStage.setTitle("My Friends");
	        mainStage.getIcons().add(new Image(
	                APPLICATION_ICON
	        ));
	 
	        final ListView<String> peopleView = new ListView<>();
	        peopleView.itemsProperty().bind(friends);
	 
	        mainStage.setScene(new Scene(peopleView));
	        mainStage.show();
	    }
	 
	    private void showSplash(
	            final Stage initStage,
	            Task<?> task,
	            InitCompletionHandler initCompletionHandler
	    ) {
	        progressText.textProperty().bind(task.messageProperty());
	        loadProgress.progressProperty().bind(task.progressProperty());
	        task.stateProperty().addListener((observableValue, oldState, newState) -> {
	            if (newState == Worker.State.SUCCEEDED) {
	                loadProgress.progressProperty().unbind();
	                loadProgress.setProgress(1);
	                initStage.toFront();
	                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
	                fadeSplash.setFromValue(1.0);
	                fadeSplash.setToValue(0.0);
	                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
	                fadeSplash.play();
	 
	                initCompletionHandler.complete();
	            } // todo add code to gracefully handle other task states.
	        });
	 
	        Scene splashScene = new Scene(splashLayout);
	        initStage.initStyle(StageStyle.UNDECORATED);
	        final Rectangle2D bounds = Screen.getPrimary().getBounds();
	        initStage.setScene(splashScene);
	        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
	        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
	        initStage.show();
	    }
	    public interface InitCompletionHandler {
	        public void complete();
	    }
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;		
	}
}
