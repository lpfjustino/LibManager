package screens;

import books.Book;
import books.BookType;
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
    private void confirmButtonAction(ActionEvent event) throws IOException {
        int quantity = Integer.parseInt(quantityField.getText());
        int id = Integer.parseInt(idField.getText());
        String title = titleField.getText();
        String author = authorField.getText();
        String type = ((RadioButton) bookType.getSelectedToggle()).getText();
        BookType bookType = BookType.getTypeFromText(type);
        
        Book newBook = new Book(id);
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setType(BookType.GENERAL);
        library.addToCollection(newBook, quantity);
        
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
    
    public void initializeLibrary(Library library) {
        this.library = library;
    }

}
