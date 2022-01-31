package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.Coach;
import models.Coach;

public class CoachsController implements Initializable {
	public static Stage stage ;
	
    @FXML
    Button Btn_Search;
    @FXML
    TextField SearchCoach;
	@FXML
    TableView<Coach> CoachsTable;
	@FXML
    private TableColumn<Coach, String> imageCol;
	@FXML
    private TableColumn<Coach, String> firstNameCol;
	@FXML
    private TableColumn<Coach, String> lastNameCol;
	@FXML
    private TableColumn<Coach, String> emailCol;
	@FXML
    private TableColumn<Coach, String> phoneCol;
	@FXML
    private TableColumn<Coach, String > addressCol;
	@FXML
    private TableColumn<Coach, String> dateEntryCol;
	@FXML
    private TableColumn<Coach, Float> SalaryCol;
	@FXML
    private TableColumn<Coach, String> typeSportCol;
	@FXML
    private TableColumn<Coach, String> actionCol;
	
	ImageView profilCoach;
	 
	String query = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Coach Coach = null;
	
	public CoachsController() {
		connection = Connection_DB.ConDB();
	}

	static ObservableList<Coach> CoachList = FXCollections.observableArrayList();
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadDate();
		
	}
	
	public void loadDate() {
	    
		ClickBtnSearch();
		
		imageCol.setPrefWidth(150);
		imageCol.setCellValueFactory(new PropertyValueFactory<>("image"));
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("first_name"));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("last_name"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
		addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
		dateEntryCol.setCellValueFactory(new PropertyValueFactory<>("date_entry"));
		SalaryCol.setCellValueFactory(new PropertyValueFactory<>("payment"));	
		typeSportCol.setCellValueFactory(new PropertyValueFactory<>("type_sport"));

		//add cell of button edit 
        Callback<TableColumn<Coach, String>, TableCell<Coach, String>> cellFoctory = (TableColumn<Coach, String> param) -> {
           // make cell containing buttons
           final TableCell<Coach, String> cell = new TableCell<Coach, String>() {
               @Override
               public void updateItem(String item, boolean empty) {
                   super.updateItem(item, empty);
                   //that cell created only on non-empty rows
                   if (empty) {
                       setGraphic(null);
                       setText(null);

                   } else {

                       FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                       FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                       deleteIcon.setStyle(
                               " -fx-cursor: hand ;"
                               + "-glyph-size:35px;"
                               + "-fx-fill:#ff1744;"
                       );
                       editIcon.setStyle(
                               " -fx-cursor: hand ;"
                               + "-glyph-size:35px;"
                               + "-fx-fill:#00E676;"
                       );
                       
                       deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                           try {
                        	   Coach = CoachsTable.getSelectionModel().getSelectedItem();
                               query = "DELETE FROM Coachs WHERE email ='"+Coach.getEmail()+"'";
                              
                               preparedStatement = connection.prepareStatement(query);
                               preparedStatement.execute();
                               ClickBtnSearch();
                               
                           } catch (SQLException ex) {
                               ex.printStackTrace();
                           }
                        
                       });
                       
                       editIcon.setOnMouseClicked((MouseEvent event) -> {
                    	   Coach = CoachsTable.getSelectionModel().getSelectedItem();
                           FXMLLoader loader = new FXMLLoader ();
                           loader.setLocation(getClass().getResource("../fxmls/AjouterCoach.fxml"));
                           try {
                               loader.load();
                           } catch (IOException ex) {
                        	   ex.printStackTrace();
                           }
                           
                           AjCoachController ajCoachController = loader.getController();
                           ajCoachController.setUpdate(true);
                           ajCoachController.setTextField(Coach.getFirst_name(),Coach.getLast_name(),Coach.getEmail(),Coach.getPhone(),Coach.getAddress(),Coach.getDate_entry(),Coach.getImage(),Coach.getType_sport(),Coach.getPayment());
                           Parent parent = loader.getRoot();
                           stage = new Stage();
                           stage.setScene(new Scene(parent));
                           stage.initStyle(StageStyle.UTILITY);
                           stage.show();
                           
                           
                           
                         });
                       
                       HBox managebtn = new HBox(editIcon, deleteIcon);
                       managebtn.setStyle("-fx-alignment:center");
                       HBox.setMargin(deleteIcon, new Insets(2, 7, 0, 3));
                       HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                       setGraphic(managebtn);

                       setText(null);

                   }
               }
           };
           return cell;
       };
        actionCol.setCellFactory(cellFoctory);
        CoachsTable.setItems(CoachList);
	}   

	public void ClickBtnSearch() {
		CoachList.clear();
		String nameSearch=SearchCoach.getText().toString();
		if(nameSearch.isEmpty()) {
			query = "SELECT * from Coachs";
		}
		else {
			query = "SELECT * from Coachs where first_name like'"+nameSearch+"%' OR last_name like'"+nameSearch+"%'";
		}
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Image img = new Image(resultSet.getString("image").toString());
				ImageView ImgV = new ImageView(img);
				ImgV.setFitWidth(150);
				ImgV.setFitHeight(150);
				CoachList.add(new Coach(
											resultSet.getString("first_name"),
											resultSet.getString("last_name"),
											resultSet.getString("email"), 
											resultSet.getString("phone"),
											resultSet.getString("address"),
											resultSet.getDate("date_entry"),
											ImgV,
											resultSet.getString("type_sport"),
											resultSet.getFloat("salary")
						                 )
						      );
				CoachsTable.setItems(CoachList);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
	
	
	
	
	
	
	
	

}
