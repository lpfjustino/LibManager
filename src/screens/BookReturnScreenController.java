package screens;

import books.Book;
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
import users.NoSuchUserException;
import users.User;

public class BookReturnScreenController implements Initializable {
    Stage primaryStage;
    
    @FXML private TextField bookTitleField = new TextField();
    @FXML private TextField userIdField = new TextField();
    @FXML private TextField bookIdField = new TextField();
	
    @FXML
    private void confirmButtonAction(ActionEvent event) throws IOException {
        try {
            String bookTitle = bookTitleField.getText();
            int userId = Integer.parseInt(userIdField.getText());
            int bookId = Integer.parseInt(bookIdField.getText());
            
            Book book = HomeScreenController.library.getBook(bookId);
            User user = HomeScreenController.library.getUser(userId);
            boolean success = HomeScreenController.library.returnBook(book, user);
            
            if(success) HomeScreenController.library.showDialog("Success",
                                    "Return registered.",
                                    "The return has been made successfully.");
            else HomeScreenController.library.showDialog("Failed",
                                    "Return not registered.",
                                    "Couldn't complete the return.");
            
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
