package application;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import io.github.gleidson28.GNAvatarView;
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
	String sql;
	Stage stage;
	@FXML
	private TextField first_name, last_name, email, adress, phone, salary;
	@FXML
	private DatePicker date_entry;

	@FXML
	private Button btnUploadImg, btn_save;
	@FXML
	GNAvatarView img_profil;

	@FXML
	private ComboBox<String> type_sport;

	static File file;
	private boolean update;
	String EMAIL;
	
	public void setTextField(String firstname ,String lastname, String Email ,String Phone,String Address,Date dateentry,ImageView img,String typesport,float Salary) {
		EMAIL = Email;
		
		first_name.setText(firstname);
		last_name.setText(lastname);
		email.setText(Email);
		phone.setText(Phone);
		adress.setText(Address);
		LocalDate localDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(dateentry));
		date_entry.setValue(localDate);
		img_profil.setImage(img.getImage());
		type_sport.setValue(typesport);
		String salary_=Float.toString(Salary);
		salary.setText(salary_);
	}

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
			img_profil.setImage(img);	
		}
	}

	@FXML
	void Click_btn_save() {
		CoachsController coachsController = new CoachsController();
		
		String First_name = first_name.getText().toString();
		String Last_name = last_name.getText().toString();
		String Email = email.getText().toString();
		String Phone = phone.getText().toString();
		String Address = adress.getText().toString();
		Float Salary = Float.valueOf(salary.getText().toString());
		String Type_sport = type_sport.getSelectionModel().getSelectedItem();
		String Srcimage;
		if(!update && file  == null) {
			Srcimage = "file:/C:/Users/Sys/workspace/Salle%20sports/src/imgs/DefaultProfile.jpg";
		}
		else if(update && file==null) {
			Srcimage =img_profil.getImage().impl_getUrl();
		}
		else {
			Srcimage=file.toURI().toString();
		}
		
		
		// get Date of entry :
		LocalDate Date_entry = date_entry.getValue();

		// query :
        if(update == false) {
        	 sql = "insert into coachs values( ? , ? , ? , ? , ? , ? , ? , ? , ? ) ";
        }else {

 		   sql ="UPDATE coachs set first_name = ? , last_name = ? , email = ? , phone = ? , address = ? , date_entry = ? , salary = ? , type_sport = ? , image = ? WHERE email ='"+EMAIL+"'";
 		}
		
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
			preparedStatement.setString(9, Srcimage);
			preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Clear();
		if(update) {
			coachsController.stage.close();
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> list = FXCollections.observableArrayList("Box", "Karate", "Musculation");
		type_sport.setItems(list);

	}
    // Clear TextFields :
	void Clear() {//
		first_name.clear();
		last_name.clear();
		email.clear();
		adress.clear();
		phone.clear();
		salary.clear();
		date_entry.setValue(null);
		
		
	}
	 void setUpdate(boolean b) {
	        this.update = b;

	    }

}
