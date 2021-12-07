package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class menuController implements Initializable{
	@FXML
	private Button btn_add,btn_clients,btn_coachs,btn_gym,btn_static;
	@FXML
	 private BorderPane center;
	
	Text text = new Text();
	
	public void ClikBtnAdd() {
	   center.getChildren().clear();
	    
		try {
			BorderPane pageAjoute = (BorderPane)FXMLLoader.load(getClass().getResource("PageAjoute.fxml"));
			center.getChildren().add(pageAjoute);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//
	public void ClickBtnClients() {
		center.getChildren().clear();
		text.setText(btn_clients.getText());
		text.setStyle("-fx-font: 24 arial");
		center.setCenter(text);
		center.setStyle("-fx-background-color : #eee000");	
	}
	//
	public void ClickBtnCoachs() {
		center.getChildren().clear();
		text.setText("Coachs");
		text.setStyle("-fx-font: 24 arial");
		center.setCenter(text);
		center.setStyle("-fx-background-color : #ad0");	
	}
	//
	public void ClickBtnGym() {
		center.getChildren().clear();
		text.setText("GYM");
		text.setStyle("-fx-font: 24 arial");
		center.setCenter(text);
		center.setStyle("-fx-background-color : #eee");	
	}
	//
	public void ClickBtnStatic() {
		center.getChildren().clear();
		text.setText("Static");
		text.setStyle("-fx-font: 24 arial");
		center.setCenter(text);
		center.setStyle("-fx-background-color : #d9267d");	
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
