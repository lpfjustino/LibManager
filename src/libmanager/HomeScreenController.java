package libmanager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import management.CSVManager;
import management.Library;

public class HomeScreenController implements Initializable {
    Stage primaryStage;
    public static Library library;

    @FXML private Label systemDate;
    @FXML private Button newUserButton;
    @FXML private Button newBookButton;
    @FXML private Button newLoanButton;
    @FXML private Button bookReturnButton;
    
    @FXML
    private void newUserButtonAction(ActionEvent event) {
    	primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	Platform.runLater(
            () -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/NewUserScreen.fxml"));
                    Parent newUser = loader.load();
                    Scene scene = new Scene(newUser);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) { }
            });
    }
    
    @FXML
    private void newBookButtonAction(ActionEvent event) {
    	primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	Platform.runLater(
            () -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/NewBookScreen.fxml"));
                    Parent newBook = loader.load();
                    Scene scene = new Scene(newBook);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) { }
            });
    }
    @FXML
    private void newLoanButtonAction(ActionEvent event) {
        primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	Platform.runLater(
            () -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/NewLoanScreen.fxml"));
                    Parent newLoan = loader.load();
                    Scene scene = new Scene(newLoan);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
    }
    @FXML
    private void databaseButtonAction(ActionEvent event) {
        primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	Platform.runLater(
            () -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/DatabaseScreen.fxml"));
                    Parent database = loader.load();
                    Scene scene = new Scene(database);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    
                } catch (IOException ex) { }
            });
    }

    @FXML
    private void bookReturnButtonAction(ActionEvent event) {
    	primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	System.out.println("BOOK RETURN");
    	Platform.runLater(
            () -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/BookReturnScreen.fxml"));
                    Parent bookReturn = loader.load();
                    Scene scene = new Scene(bookReturn);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) { }
            });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(
            () -> {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = formatter.format(library.getDate());
                String labelText = systemDate.getText() + dateString;
                systemDate.setText(labelText);
                
            });
        }
    
    public void initializeLibrary(Date date) {
    	try {
            library = new Library (date);
            Date lastAccess = CSVManager.lastAccessFromCSV();
            // Estamos no passado
            if(date.before(lastAccess)) {
                newUserButton.setDisable(true);
                newBookButton.setDisable(true);
                newLoanButton.setDisable(true);
                bookReturnButton.setDisable(true);
                systemDate.setText("You're in the past. How did you get here? D: - ");
            } else {
                CSVManager.registerLastAccess(date);
            }
        } catch (IOException e) { }

    }
}
