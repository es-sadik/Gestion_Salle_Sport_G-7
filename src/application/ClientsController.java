package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import models.Client;


public class ClientsController implements Initializable {
	public static Stage stage ;
	
    @FXML
    Button Btn_Search;
    @FXML
    TextField SearchClient;
	@FXML
    TableView<Client> ClientsTable;
	@FXML
    private TableColumn<Client, String> imageCol;
	@FXML
    private TableColumn<Client, String> firstNameCol;
	@FXML
    private TableColumn<Client, String> lastNameCol;
	@FXML
    private TableColumn<Client, String> emailCol;
	@FXML
    private TableColumn<Client, String> phoneCol;
	@FXML
    private TableColumn<Client, String > addressCol;
	@FXML
    private TableColumn<Client, String> dateEntryCol;
	@FXML
    private TableColumn<Client, Float> paymentCol;
	@FXML
    private TableColumn<Client, String> typeSportCol;
	@FXML
    private TableColumn<Client, String> actionCol;
	
	 ImageView profilClient;
	
	 FontAwesomeIconView deleteIcon;
	 FontAwesomeIconView editIcon ;
	 
	 Button BtnDelete = new Button();
	 Button BtnEdit = new Button();
	 
	String query = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Client client = null;
	
	public ClientsController() {
		connection = Connection_DB.ConDB();
	}

	static ObservableList<Client> ClientList = FXCollections.observableArrayList();
	
	
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
		paymentCol.setCellValueFactory(new PropertyValueFactory<>("payment"));	
		typeSportCol.setCellValueFactory(new PropertyValueFactory<>("type_sport"));
		
		//add cell of button edit 
        Callback<TableColumn<Client, String>, TableCell<Client, String>> cellFoctory = (TableColumn<Client, String> param) -> {
           // make cell containing buttons
           final TableCell<Client, String> cell = new TableCell<Client, String>() {
               @Override
               public void updateItem(String item, boolean empty) {
                   super.updateItem(item, empty);
                   //that cell created only on non-empty rows
                   if (empty) {
                       setGraphic(null);
                       setText(null);

                   } else {

                	   deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                	   editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

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
                        	   client = ClientsTable.getSelectionModel().getSelectedItem();
                               query = "DELETE FROM clients WHERE email ='"+client.getEmail()+"'";
                              
                               preparedStatement = connection.prepareStatement(query);
                               preparedStatement.execute();
                               ClickBtnSearch();
                               
                           } catch (SQLException ex) {
                               ex.printStackTrace();
                           }
                        
                       });
                       
                       
                       
                       editIcon.setOnMouseClicked((MouseEvent event) -> {
                    	   client = ClientsTable.getSelectionModel().getSelectedItem();
                           FXMLLoader loader = new FXMLLoader ();
                           loader.setLocation(getClass().getResource("../fxmls/AjouterClient.fxml"));
                           try {
                               loader.load();
                           } catch (IOException ex) {
                        	   ex.printStackTrace();
                           }
                           
                           AjClientController ajClientController = loader.getController();
                           ajClientController.setUpdate(true);
                           ajClientController.setTextField(client.getFirst_name(),client.getLast_name(),client.getEmail(),client.getPhone(),client.getAddress(),client.getDate_entry(),client.getImage(),client.getType_sport(),client.getPayment());
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
        ClientsTable.setItems(ClientList);
	}     
	
                   
	public void ClickBtnSearch() {
		ClientList.clear();
		String nameSearch=SearchClient.getText().toString();
		if(nameSearch.isEmpty()) {
			query = "SELECT * from clients";
		}
		else {
			query = "SELECT * from clients where first_name like'"+nameSearch+"%' OR last_name like'"+nameSearch+"%'";
		}
		
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Image img = new Image(resultSet.getString("image").toString());
				ImageView ImgV = new ImageView(img);
				ImgV.setFitWidth(150);
				ImgV.setFitHeight(150);
				ClientList.add(new Client(
											resultSet.getString("first_name"),
											resultSet.getString("last_name"),
											resultSet.getString("email"), 
											resultSet.getString("phone"),
											resultSet.getString("address"),
											resultSet.getDate("date_entry"),
											ImgV,
											resultSet.getString("type_sport"),
											resultSet.getFloat("payment")
						                 )
						      );
				ClientsTable.setItems(ClientList);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	

    
	
	
	
	
	
	

}
