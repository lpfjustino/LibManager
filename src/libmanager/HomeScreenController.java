package libmanager;

import static com.sun.glass.ui.android.SoftwareKeyboard.show;
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
import javafx.stage.Stage;

public class HomeScreenController implements Initializable {
    Stage primaryStage;
    
    @FXML
    private Button newUserButton;
    
    @FXML
    private void newUserButtonAction(ActionEvent event) {
    	primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	Platform.runLater(
            () -> {
                try {
                    Parent newUser = FXMLLoader.load(getClass().getResource("/screens/NewUserScreen.fxml"));
                    Scene scene = new Scene(newUser);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) { }
            });
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
