package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		SampleController sc = new SampleController(primaryStage);
		sc.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
