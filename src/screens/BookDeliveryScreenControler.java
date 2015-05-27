package screens;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class BookDeliveryScreenControler implements Initializable {
	
	TextField bookTitleField = new TextField();
	TextField userIdField = new TextField();
	String bookTitle;
	String userId; 
	
	@FXML
    private void confirmButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        bookTitle = bookTitleField.getText();
        userId = userIdField.getText();
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
