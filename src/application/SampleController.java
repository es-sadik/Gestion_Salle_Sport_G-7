package application;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SampleController implements Initializable {
	public static Stage stage;

	private static String userName;
	private static String Password;

	@FXML
	public TextField username;
	@FXML
	public PasswordField password;

	@FXML
	private Button btnLogin;
	@FXML
	private Text msgError;
	@FXML
	private BorderPane rootBorder;
	
	
	
	public void clicK()  {

		if (!logIn()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Incorrect Username or Password");
            alert.showAndWait();
			//msgError.setText("Incorrect Username or Password.");
		} else {
			
				stage.close();
				Goto();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public SampleController() {
		conn = Connection_DB.ConDB();
		
	}
	
	public SampleController(Stage stage) {
		this.stage=stage;
	}

	//
	Connection conn = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	private boolean logIn() {
		boolean login = true;
		userName = username.getText().toString();
		Password = password.getText().toString();
		
		// query :
		String sql = "SELECT * from login where username = ? and password = ?";

		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, Password);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				login = false;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return login;
	}

	// get && set :
	public String getUsername() {
		return userName;
		
	}
	public String getPassword() {
		return Password;
	}

	public void show() {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../fxmls/Login.fxml"));
			Scene scene = new Scene(root);
			//stage.setMaximized(true);
			//stage.setResizable(false);
			stage.getIcons().add(new Image(getClass().getResourceAsStream("../imgs/logoApp.jpeg")));
			stage.setTitle("Login");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Goto() {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader();
			BorderPane root = (BorderPane) fxmlLoader.load(getClass().getResource("../fxmls/Menu.fxml"));
			Scene scene = new Scene(root,Screen.getPrimary().getBounds().getWidth(),Screen.getPrimary().getBounds().getHeight()-95);
			
			//primaryStage.setMaximized(true);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Menu");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../imgs/logoApp.jpeg")));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	
}














