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

public class NewLoanScreenControler implements Initializable{
	
	Stage primaryStage;
	@FXML
	private TextField bookIdField;
	@FXML
	private TextField usernameField;
	@FXML
	private TextField titleField;
	String username, title;
	int bookId;
	
	@FXML
    private void confirmButtonAction(ActionEvent event) {
		
//		bookId = Integer.valueOf(bookIdField.getText());
        title = titleField.getText().toString();
        username = usernameField.getText();
        
        System.out.println("Id:" + bookId);
        System.out.println("username:" + username);
        System.out.println("Title:" + title);

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
