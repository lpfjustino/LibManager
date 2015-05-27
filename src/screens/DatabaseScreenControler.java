package screens;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class DatabaseScreenControler implements Initializable {
	
	@FXML
	Label label;
	
	@FXML
    private void homeButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String databaseType = null;	//parametro da lista
		label.setText(databaseType);
		
	}

}
