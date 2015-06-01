package screens;

import books.NoSuchBookException;
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
import libmanager.HomeScreenController;
import management.Library;
import users.NoSuchUserException;

public class BookReturnScreenController implements Initializable {
    Stage primaryStage;
    
    @FXML private TextField bookTitleField = new TextField();
    @FXML private TextField userIdField = new TextField();
    @FXML private TextField bookIdField = new TextField();
	
    @FXML
    private void confirmButtonAction(ActionEvent event) {
        String bookTitle = bookTitleField.getText();
        int userId = Integer.parseInt(userIdField.getText());
        int bookId = Integer.parseInt(bookIdField.getText());
        
        try {
            HomeScreenController.library.getBook(bookId);
            HomeScreenController.library.getUser(userId);
            HomeScreenController.library.returnBook(null, null);
        } catch(NumberFormatException ex) {
            HomeScreenController.library.showDialog("Error", "Invalid input.",
                                        "Please fill in the text fields.");
        } catch (NoSuchBookException nsb) {
            HomeScreenController.library.showDialog("Error", "Invalid input.", 
                                        "Book not found");
        } catch (NoSuchUserException nsu) {
            HomeScreenController.library.showDialog("Error", "Invalid input.",
                                        "User not found");
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
}
