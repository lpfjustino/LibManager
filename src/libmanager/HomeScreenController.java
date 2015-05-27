package libmanager;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class HomeScreenController implements Initializable {
	
    @FXML
    private Button button;
    
    @FXML
    private void newUserButtonAction(ActionEvent event) {
    	
    	Platform.runLater(
    			() -> {
    				try {
    					Parent newUser = FXMLLoader.load(getClass().getResource("NewUserScreen.fxml"));
    					Scene scene = new Scene(newUser);
    					libManager.programStage.setScene(scene);
    					libManager.programStage.show();
    				} catch (IOException e) { }

    			} );
    }
    
    @FXML
    private void newBookButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    @FXML
    private void newLoanButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    @FXML
    private void usersButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    @FXML
    private void loansButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    @FXML
    private void bookReturnButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    @FXML
    private void collectionButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
