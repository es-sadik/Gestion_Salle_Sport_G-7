package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ConfLoginController implements Initializable {
	@FXML
	BorderPane root;
	public void Confirme() {
		try {
			
			Stage stage = new Stage();
			FXMLLoader fxmlLoader= new FXMLLoader();
			BorderPane root =(BorderPane)fxmlLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene=new Scene(root,1165,688);
			stage.setTitle("Menu");
			stage.setScene(scene);
			stage.showAndWait();
		}catch (Exception e) {
			e.printStackTrace();	
			}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

}
