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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConfLoginController implements Initializable {
	public static Stage primarystage;
	
	@FXML
	BorderPane root;
	@FXML
	TextField newUsername, newPassword, confPassword;
	@FXML
	private Text msgError;

	static String NewUsername,NewPassword,ConfPassword;
	
	public ConfLoginController() {
		conn = Connection_DB.ConDB();

	}

	Connection conn = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	SampleController sampleController = new SampleController();

	public void Confirme() {
		 NewUsername = newUsername.getText().toString();
		 NewPassword = newPassword.getText().toString();
		 ConfPassword = confPassword.getText().toString();
		msgError.setFill(Color.RED);
		if (!NewUsername.isEmpty() && !NewPassword.isEmpty() && !ConfPassword.isEmpty()) {
			if (NewPassword.equals(ConfPassword)) {
				try {
					String sql = "update login set username = ? ,password =? where username=? ";
					preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, NewUsername);
					preparedStatement.setString(2, NewPassword);
					preparedStatement.setString(3, sampleController.getUsername());
					preparedStatement.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				Goto();
				
			}

			else {	
				msgError.setText("NewPassword Machi hwa ConfPassword 3awd A Lhmar");
			}
		}
		else {
			msgError.setText("Chi case khawya Aras Taro");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		newUsername.setText(sampleController.getUsername());
		newPassword.setText(sampleController.getPassword());
		confPassword.setText(sampleController.getPassword());

	}

	public void Goto() {
		try {
			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader();
			BorderPane root = (BorderPane) fxmlLoader.load(getClass().getResource("../fxmls/Menu.fxml"));
			Scene scene = new Scene(root);
			primarystage=stage;
			stage.setMaximized(true);
			stage.setResizable(false);
			stage.setTitle("Menu");
			stage.setScene(scene);
			stage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
