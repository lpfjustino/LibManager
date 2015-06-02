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
import libmanager.HomeScreenController;
import users.Community;
import users.Student;
import users.Teacher;
import users.User;
import users.UserType;
import static users.UserType.COMMUNITY;
import static users.UserType.STUDENT;
import static users.UserType.TEACHER;

public class NewUserScreenController implements Initializable {
	private Stage primaryStage;
	
	@FXML private TextField idField = new TextField();
	@FXML private TextField nameField = new TextField();
	@FXML private RadioButton userStudent = new RadioButton("Student");
	@FXML private RadioButton userTeacher = new RadioButton("Teacher");
	@FXML private RadioButton userCommunity = new RadioButton("Community");
	@FXML private ToggleGroup userType = new ToggleGroup();
	
    @FXML
    private void confirmButtonAction(ActionEvent event) {
        try {
            // Recupera os campos da GUI
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String type = ((RadioButton) userType.getSelectedToggle()).getText();
            UserType userType = UserType.getTypeFromText(type);
            
            // Verifica se a inserção foi bem sucedida
            boolean success = HomeScreenController.library.registerUser(
                    buildUser(id, name, userType),
                    userType
            );
            
            // Mostra uma mensagem de alerta apropriada
            if(success)
                HomeScreenController.library.showDialog("Success",
                                        "Registration success.",
                                        "The registration has been made successfully.");
            else HomeScreenController.library.showDialog("Failed",
                                        "Registration failed.",
                                        "Couldn't complete user registration.");
            
        } catch(IOException|NumberFormatException ex) {
            HomeScreenController.library.showDialog("Failed",
                                        "Registration failed.",
                                        "Couldn't complete user registration.");
        }
        
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
        
    // Função auxiliar para retornar um Usuário do tipo certo
    public User buildUser(int id, String name, UserType type) {
        switch(type) {
            case STUDENT:
                return new Student(id, name);
            
            case TEACHER:
                return new Teacher(id, name);
            
            case COMMUNITY:
                return new Community(id, name);
                
            default:
                return null;
        }
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
}
