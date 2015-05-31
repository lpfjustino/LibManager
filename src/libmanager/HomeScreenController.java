package libmanager;

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
import javafx.stage.Stage;
import javafx.scene.control.Button;
import management.Library;
import screens.NewUserScreenController;

public class HomeScreenController implements Initializable {
    Stage primaryStage;
    Library library;
    
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
                    
                    NewUserScreenController nusc = loader.getController();
                    nusc.initializeLibrary(library);
                } catch (IOException ex) { }
            });
    }
    
    @FXML
    private void newBookButtonAction(ActionEvent event) {
    	primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	Platform.runLater(
            () -> {
                try {
                    Parent newBook = FXMLLoader.load(getClass().getResource("/screens/NewBookScreen.fxml"));
                    Scene scene = new Scene(newBook);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) { }
            });
    }
    @FXML
    private void newLoanButtonAction(ActionEvent event) {
    	System.out.println("NEW LOAN");
    	primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	Platform.runLater(
            () -> {
                try {
                    Parent newLoan = FXMLLoader.load(getClass().getResource("/screens/NewLoanScreen.fxml"));
                    Scene scene = new Scene(newLoan);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) { }
            });
    }
    @FXML
    private void usersButtonAction(ActionEvent event) {
    	System.out.println("USERS");
    	primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	Platform.runLater(
            () -> {
                try {
                    Parent users = FXMLLoader.load(getClass().getResource("/screens/DatabaseScreen.fxml"));
                    Scene scene = new Scene(users);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) { }
            });
    }
    @FXML
    private void loansButtonAction(ActionEvent event) {
    	System.out.println("LOANS");
    	primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	Platform.runLater(
            () -> {
                try {
                    Parent loans = FXMLLoader.load(getClass().getResource("/screens/DatabaseScreen.fxml"));
                    Scene scene = new Scene(loans);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) { }
            });
    }
    @FXML
    private void bookReturnButtonAction(ActionEvent event) {
    	System.out.println("BOOK RETURN");
    	primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	Platform.runLater(
            () -> {
                try {
                    Parent bookReturn = FXMLLoader.load(getClass().getResource("/screens/BookReturnScreen.fxml"));
                    Scene scene = new Scene(bookReturn);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) { }
            });
    }
    @FXML
    private void collectionButtonAction(ActionEvent event) {
    	System.out.println("COLLECTION");
    	primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	Platform.runLater(
            () -> {
                try {
                    Parent collection = FXMLLoader.load(getClass().getResource("/screens/DatabaseScreen.fxml"));
                    Scene scene = new Scene(collection);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) { }
            });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initializeLibrary(Library library) {
        this.library = library;
    }
}
