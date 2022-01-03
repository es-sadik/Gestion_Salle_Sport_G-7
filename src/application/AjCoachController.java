package application;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AjCoachController implements Initializable{
	
	public AjCoachController() {
		conn = Connection_DB.ConDB();
	}

	Connection conn = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	Stage stage;
	@FXML
	private TextField first_name, last_name, email, adress, phone, salary;
	@FXML
	private DatePicker date_entry;

	@FXML
	private Button btnUploadImg, btn_save;
	@FXML
	ImageView img_profil;

	@FXML
	private ComboBox<String> type_sport;

	static File file;

	@FXML
	void Click_btnUploadImg() {
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
			Image img = new Image(file.toURI().toString(), 83, 92, true, true);
			img_profil = new ImageView(img);
			img_profil.setFitWidth(150);
			img_profil.setFitHeight(150);
			img_profil.setPreserveRatio(true);
			btnUploadImg.setGraphic(img_profil);
		}
	}

	@FXML
	void Click_btn_save() {
		
		String First_name = first_name.getText().toString();
		String Last_name = last_name.getText().toString();
		String Email = email.getText().toString();
		String Phone = phone.getText().toString();
		String Address = adress.getText().toString();
		Float Salary = Float.valueOf(salary.getText().toString());
		String Type_sport = type_sport.getSelectionModel().getSelectedItem();
		String image = file.toURI().toString();
		// get Date of entry :
		LocalDate Date_entry = date_entry.getValue();
		
		System.out.println(Date_entry+"\n"+Salary);
		// query :

		String sql = "insert into coachs values( ? , ? , ? , ? , ? , ? , ? , ? , ? ) ";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, First_name);
			preparedStatement.setString(2, Last_name);
			preparedStatement.setString(3, Email);
			preparedStatement.setString(4, Phone);
			preparedStatement.setString(5, Address);
			preparedStatement.setDate(6,java.sql.Date.valueOf(Date_entry));
			preparedStatement.setFloat(7, Salary);
			preparedStatement.setString(8, Type_sport);
			preparedStatement.setString(9, image);
			preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> list = FXCollections.observableArrayList("Box", "Karate", "Musculation");
		type_sport.setItems(list);

	}

}
