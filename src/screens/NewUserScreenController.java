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
import management.Library;
import users.Community;
import users.Student;
import users.Teacher;
import users.UserType;

public class NewUserScreenController implements Initializable {
	private Stage primaryStage;
        private Library library;
	
	@FXML private TextField idField = new TextField();
	@FXML private TextField nameField = new TextField();
	@FXML private RadioButton userStudent = new RadioButton("Student");
	@FXML private RadioButton userTeacher = new RadioButton("Teacher");
	@FXML private RadioButton userCommunity = new RadioButton("Community");
	@FXML private ToggleGroup userType = new ToggleGroup();
	
    @FXML
    private void confirmButtonAction(ActionEvent event) throws IOException {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String type = ((RadioButton) userType.getSelectedToggle()).getText();
        UserType userType = UserType.getTypeFromText(type);
        
        switch(userType) {
            case STUDENT:
                Student newStudent = new Student(id, name);
                library.registerUser(newStudent, userType);
                break;
            case TEACHER:
                Teacher newTeacher = new Teacher(id, name);
                library.registerUser(newTeacher, userType);
                break;
                
            case COMMUNITY:
                Community newCommunity = new Community(id, name);
                library.registerUser(newCommunity, userType);
                break;
        }
        
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
		// Retorna para a tela principal
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
        
        public void initializeLibrary(Library library) {
            this.library = library;
        }
}
