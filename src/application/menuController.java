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

public class menuController implements Initializable {
	@FXML
	private Button btn_add, btn_clients, btn_coachs, btn_gym, btn_static;
	@FXML
	private BorderPane center;

	// ids fxml :PageAoute:
	@FXML
	BorderPane pageAjoute;
	Button btn_ajouteClient, btn_ajouteCoach;

	Text text = new Text();

	public void ClikBtnAdd() {
		ClearStyle();
		btn_add.getStylesheets().add(getClass().getResource("../styles/StyleBtn.css").toExternalForm());
		center.getChildren().clear();

		try {
			BorderPane pageAjoute = (BorderPane) FXMLLoader.load(getClass().getResource("../fxmls/PageAjoute.fxml"));
			center.setCenter(pageAjoute);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//
	public void ClickBtnClients() {
		ClearStyle();
		btn_clients.getStylesheets().add(getClass().getResource("../styles/StyleBtn.css").toExternalForm());
		center.getChildren().clear();
		text.setText(btn_clients.getText());
		text.setStyle("-fx-font: 24 arial");
		center.setCenter(text);
		center.setStyle("-fx-background-color : #eee000");
	}

	//
	public void ClickBtnCoachs() {
		ClearStyle();
		btn_coachs.getStylesheets().add(getClass().getResource("../styles/StyleBtn.css").toExternalForm());
		center.getChildren().clear();
		text.setText("Coachs");
		text.setStyle("-fx-font: 24 arial");
		center.setCenter(text);
		center.setStyle("-fx-background-color : #ad0");
	}

	//
	public void ClickBtnGym() {
		ClearStyle();
		btn_gym.getStylesheets().add(getClass().getResource("../styles/StyleBtn.css").toExternalForm());
		center.getChildren().clear();
		try {
			BorderPane gym = (BorderPane) FXMLLoader.load(getClass().getResource("../fxmls/gym.fxml"));
			center.setCenter(gym);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//
	public void ClickBtnStatic() {
		ClearStyle();
		btn_static.getStylesheets().add(getClass().getResource("../styles/StyleBtn.css").toExternalForm());
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

	//
	public void Click_btn_ajouteClient() {
		try {
			pageAjoute.getChildren().clear();
			BorderPane AjouteClient = (BorderPane) FXMLLoader.load(getClass().getResource("../fxmls/AjouterClient.fxml"));
			pageAjoute.setCenter(AjouteClient);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void Click_btn_ajouteCoach() {
		try {
			pageAjoute.getChildren().clear();
			BorderPane AjouteClient = (BorderPane) FXMLLoader.load(getClass().getResource("../fxmls/AjouterCoach.fxml"));
			pageAjoute.setCenter(AjouteClient);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ClearStyle() {
		btn_add.getStylesheets().clear();
		btn_clients.getStylesheets().clear();
		btn_coachs.getStylesheets().clear();
		btn_gym.getStylesheets().clear();
		btn_static.getStylesheets().clear();
	}

}
