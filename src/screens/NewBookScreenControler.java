package screens;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import books.BookType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class NewBookScreenControler implements Initializable {
	
	@FXML
	TextField idField = new TextField();
	TextField titleField = new TextField();
	TextField authorField = new TextField();
	RadioButton bookText = new RadioButton();
	RadioButton bookGeneral = new RadioButton();
	final ToggleGroup group = new ToggleGroup();
	String title, author;
	int id;
	BookType type;
	
	Stage primaryStage;
	
	@FXML
    private void confirmButtonAction(ActionEvent event) {        
        id = Integer.parseInt(idField.getText());
        title = titleField.getText();
        author = authorField.getText();
     
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
