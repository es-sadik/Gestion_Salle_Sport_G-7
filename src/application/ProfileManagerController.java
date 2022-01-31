package application;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import io.github.gleidson28.GNAvatarView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ProfileManagerController implements Initializable {
	Stage stage;
	@FXML
	Button btnUploadImg, btn_save;
	@FXML
	GNAvatarView img_profil;
	@FXML
	TextField full_name, username;
	@FXML
	PasswordField perviousPass, newPass;

	Connection conn = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	public ProfileManagerController(){
		conn = Connection_DB.ConDB();
	}
	
    
	SampleController sampleController = new SampleController();
	
	static File file;
	
	public void Click_btnUploadImg() {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(" Select image ");
		// Set extension filter
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");

		FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");

		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");

		FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");

		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
		// Show open file dialog
		file = fileChooser.showOpenDialog(stage);

		if (file != null) {
			Image img = new Image(file.toURI().toString());
			img_profil.setImage(img);
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	

	public void Click_btn_save() {
		String Username = username.getText().toString();
		String Password = perviousPass.getText().toString();
		String NewPassword = newPass.getText().toString();
		String Srcimage;
		if (file == null) {
			Srcimage = "file:/C:/Users/Sys/workspace/Salle%20sports/src/imgs/DefaultProfile.jpg";
		} else {
			Srcimage = file.toURI().toString();
		}

		if(!Username.isEmpty() && !Password.isEmpty() && !NewPassword.isEmpty()) {
			if(Password.equals(NewPassword)) {
				
				try {
					String sql = "update login set username = ? , password =? , image = ? where username=? ";
					preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, Username);
					preparedStatement.setString(2, NewPassword);
					preparedStatement.setString(3,Srcimage);
					preparedStatement.setString(4, sampleController.getUsername());
					preparedStatement.executeUpdate();
					preparedStatement.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}
}
