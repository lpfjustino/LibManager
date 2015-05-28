package screens;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class NewUserScreenControler implements Initializable {
	
	Stage primaryStage;
	TextField idField = new TextField();
	TextField nameField = new TextField();
//	ChoiceBox<BookType> userType = new ChoiceBox<BookType>();
	RadioButton userStudent = new RadioButton("Student");
	RadioButton userTeacher = new RadioButton("Teacher");
	RadioButton userCommunity = new RadioButton("Community");
	final ToggleGroup group = new ToggleGroup();
	String name;
	int id;

	@FXML
    private void confirmButtonAction(ActionEvent event) {
		id = Integer.parseInt(idField.getText());
        name = nameField.getText();
        //TODO: CHAMAR A FUNCAO COM OS VALORES DO NEW USER
        
		primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	Platform.runLater(
            () -> {
                try {
                    Parent loans = FXMLLoader.load(getClass().getResource("/libmanager/HomeScreen.fxml"));
                    Scene scene = new Scene(loans);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) { }
            });
    }
	@FXML
    private void cancelButtonAction(ActionEvent event) {
		// returns to main screen
		primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		Platform.runLater(
				() -> {
					try {
						Parent loans = FXMLLoader.load(getClass().getResource("/libmanager/HomeScreen.fxml"));
						Scene scene = new Scene(loans);
						primaryStage.setScene(scene);
						primaryStage.show();
					} catch (IOException ex) { }
				});
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
