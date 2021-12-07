package application;


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
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;

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
				
				Stage stage = new Stage();
				FXMLLoader fxmlLoader= new FXMLLoader();
				BorderPane root =(BorderPane)fxmlLoader.load(getClass().getResource("ConfLogin.fxml"));
				Scene scene=new Scene(root,1165,688);
				
				stage.setTitle("Cofirme Login");
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

	/// ---
	Connection conn = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	private boolean logIn() {
		boolean login = true;
		String userName = username.getText().toString();
		String Password = password.getText().toString();
		// query :
		String sql = "SELECT * from login where username = ? and password =?";

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
}
