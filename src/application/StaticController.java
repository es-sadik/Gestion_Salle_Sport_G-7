package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import models.Coach;

public class StaticController implements Initializable  {

	@FXML
	Text NbrClient,NbrCoach,TotalIncome,TotalExpenditure;
	
	String query = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	public StaticController() {
		connection = Connection_DB.ConDB();
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getTotalExpenditure();
		getTotalIncme();
		getNbrClient();
		getNbrCoach();
	}
	
	void getTotalIncme() {

		float Sum=0;
		query = "select payment from clients";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Sum+=resultSet.getFloat("payment");
			}
			
			TotalIncome.setText(String.valueOf(Sum)+" DH ");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	void getTotalExpenditure() {

		float Sum=0;
		query = "select salary from coachs";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Sum+=resultSet.getFloat("salary");
			}
			
			TotalExpenditure.setText(String.valueOf(Sum)+" DH");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	void getNbrClient() {
		float Sum=0;
		query = "select * from clients";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Sum+=1;
			}
			NbrClient.setText(String.valueOf(Sum));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void getNbrCoach() {
		float Sum=0;
		query = "select * from coachs";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Sum+=1;
			}
			NbrCoach.setText(String.valueOf(Sum));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
