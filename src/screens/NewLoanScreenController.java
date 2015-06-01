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
import management.Library;

public class NewLoanScreenController implements Initializable{
	Library library;
	Stage primaryStage;
	@FXML private TextField userIdField;
	@FXML private TextField bookIdField;
	
    @FXML
    private void confirmButtonAction(ActionEvent event) {
		
        int bookId = Integer.valueOf(bookIdField.getText());
        int userId = Integer.valueOf(userIdField.getText());
        
        System.out.println("Id:" + bookId);
        System.out.println("username:" + userId);

        //TODO: CHAMAR AS FUNCAO DE NEW LOAN
		
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

        public void initializeLibrary(Library library) {
            this.library = library;
        }
}
