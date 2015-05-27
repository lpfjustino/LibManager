package screens;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class NewLoanScreenControler implements Initializable{
	
	@FXML
    private void confirmButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
	@FXML
    private void cancelButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
