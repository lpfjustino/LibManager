package screens;

import books.NoSuchBookException;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import libmanager.HomeScreenController;
import management.CSVManager;
import management.Library;
import management.Loan;
import users.NoSuchUserException;

public class NewLoanScreenController implements Initializable{
	Library library;
	Stage primaryStage;
	@FXML private TextField userIdField;
	@FXML private TextField bookIdField;
	
    @FXML
    private void confirmButtonAction(ActionEvent event) throws IOException {
        try {
            int bookId = Integer.valueOf(bookIdField.getText());
            int userId = Integer.valueOf(userIdField.getText());
            
            Loan loan = new Loan();
            loan.setBook(HomeScreenController.library.getBook(bookId));
            loan.setBorrower(HomeScreenController.library.getUser(userId));
            loan.setExpirationDate(HomeScreenController.library.sumDays(
                    HomeScreenController.library.getDate(),
                    loan.getBorrower().LOAN_TERM));
            
            CSVManager.includeLoan(loan);
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
        
        HomeScreenController.library.showDialog("Success", "Loan registered.",
                                        "The loan has been made successfully.");
        
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
		// Returns to main screen
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
