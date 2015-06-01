package libmanager;

import java.io.IOException;
import java.net.URL;
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
import management.Library;
import screens.BookReturnScreenController;
import screens.NewBookScreenController;
import screens.NewLoanScreenController;
import screens.NewUserScreenController;

public class HomeScreenController implements Initializable {
    Stage primaryStage;
    public static Library library;

    @FXML
    private Label systemDate;
    
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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/NewBookScreen.fxml"));
                    Parent newBook = loader.load();
                    Scene scene = new Scene(newBook);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    
                    NewBookScreenController nbsc = loader.getController();
                    nbsc.initializeLibrary(library);
                } catch (IOException ex) { }
            });
    }
    @FXML
    private void newLoanButtonAction(ActionEvent event) {
        primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	System.out.println("NEW LOAN");
    	Platform.runLater(
            () -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/NewLoanScreen.fxml"));
                    Parent newLoan = loader.load();
                    Scene scene = new Scene(newLoan);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    
                    NewLoanScreenController nlsc = loader.getController();
                    nlsc.initializeLibrary(library);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
    }
    @FXML
    private void databaseButtonAction(ActionEvent event) {
        primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	System.out.println("DATABASE");
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
                    
                    BookReturnScreenController brsc = loader.getController();
                    brsc.initializeLibrary(library);
                } catch (IOException ex) { }
            });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//    	systemDate.setText(library.getDate().toString());
    }    
    
    public void initializeLibrary(Date date) {
    	try {
			library = new Library (date);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
