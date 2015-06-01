package screens;

import books.Book;
import books.BookType;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import management.Library;

public class NewBookScreenController implements Initializable {
    Library library;
    
    @FXML private TextField idField = new TextField();
    @FXML private TextField titleField = new TextField();
    @FXML private TextField authorField = new TextField();
    @FXML private TextField quantityField = new TextField();
    @FXML private RadioButton bookText = new RadioButton();
    @FXML private RadioButton bookGeneral = new RadioButton();
    @FXML private ToggleGroup bookType = new ToggleGroup();

    Stage primaryStage;
	
    @FXML
    private void confirmButtonAction(ActionEvent event) {
        int quantity = Integer.parseInt(quantityField.getText());
        int id = Integer.parseInt(idField.getText());
        String title = titleField.getText();
        String author = authorField.getText();
        String type = ((RadioButton) bookType.getSelectedToggle()).getText();
        BookType newBookType = BookType.getTypeFromText(type);
        
        // Cria uma instância do livro
        Book newBook = new Book(id);
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setType(newBookType);
        
        // Tenta adicioná-lo ao arquivo
        try {
            HomeScreenController.library.addToCollection(newBook, quantity);
        } catch (IOException ex) {
            HomeScreenController.library.showDialog("File error", "File output failed", "Error occurred on insertion");
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
