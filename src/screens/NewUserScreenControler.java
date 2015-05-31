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
	
	@FXML private TextField idField = new TextField();
	@FXML private TextField nameField = new TextField();
	@FXML private RadioButton userStudent = new RadioButton("Student");
	@FXML private RadioButton userTeacher = new RadioButton("Teacher");
	@FXML private RadioButton userCommunity = new RadioButton("Community");
	@FXML private ToggleGroup userType = new ToggleGroup();
	
	String name;
	int id;
	String type;

	@FXML
    private void confirmButtonAction(ActionEvent event) {
		id = Integer.parseInt(idField.getText());
        name = nameField.getText();
        type = ((RadioButton) userType.getSelectedToggle()).getText();
        
        System.out.println("Id:" + id);
        System.out.println("name:" + name);
        System.out.println("Type:" + type);

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
