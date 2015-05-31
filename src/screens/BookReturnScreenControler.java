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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookReturnScreenControler implements Initializable {
	
	@FXML
	private TextField bookTitleField = new TextField();
	@FXML
	private TextField userIdField = new TextField();
	@FXML
	private TextField bookIdField = new TextField();
	String bookTitle;
	int userId, bookId;
	
	Stage primaryStage;
	
	@FXML
    private void confirmButtonAction(ActionEvent event) {
        bookTitle = bookTitleField.getText();
        userId = Integer.parseInt(userIdField.getText());
        bookId = Integer.parseInt(bookIdField.getText());
        
        System.out.println("BookId:" + bookId);
        System.out.println("userId:" + userId);
        System.out.println("Title:" + bookTitle);
        
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
		// TODO Auto-generated method stub
		
	}
}
