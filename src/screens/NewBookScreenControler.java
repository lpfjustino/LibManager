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

public class NewBookScreenControler implements Initializable {
	
	@FXML private TextField idField = new TextField();
	@FXML private TextField titleField = new TextField();
	@FXML private TextField authorField = new TextField();
	@FXML private RadioButton bookText = new RadioButton();
	@FXML private RadioButton bookGeneral = new RadioButton();
	@FXML private ToggleGroup bookType = new ToggleGroup();
	String title, author;
	int id;
	String type;
	
	Stage primaryStage;
	
	@FXML
    private void confirmButtonAction(ActionEvent event) {        
        id = Integer.parseInt(idField.getText());
        title = titleField.getText();
        author = authorField.getText();
        type = ((RadioButton) bookType.getSelectedToggle()).getText();
        
        System.out.println("Id:" + id);
        System.out.println("author:" + author);
        System.out.println("Title:" + title);
        System.out.println("Type:" + type);
     
        //TODO: CHAMAR A FUNCAO COM OS RESULTADOS DO NEW BOOK
        
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
		//returns to main screen
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
