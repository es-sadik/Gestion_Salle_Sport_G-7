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
import javafx.scene.control.Button;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SampleController implements Initializable {
	public static Stage stage;
	
	static String userName;
	static String Password;

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
			msgError.setFill(Color.RED);
			msgError.setText("Error Username Or Password");
		} else {
			try {	
				stage.close();
				stage = new Stage();
				FXMLLoader fxmlLoader= new FXMLLoader();
				BorderPane root =(BorderPane)fxmlLoader.load(getClass().getResource("../fxmls/ConfLogin.fxml"));
				Scene scene=new Scene(root);
				stage.setTitle("Cofirme Login");
				//stage.setMaximized(true);
				//stage.setResizable(false);
				stage.setScene(scene);
				stage.showAndWait();
				
			}catch (Exception e) {
				e.printStackTrace();	
				}	
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
			stage.setTitle("Login");
			stage.setScene(scene);
			stage.show();	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	
}














