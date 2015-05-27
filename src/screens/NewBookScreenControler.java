package screens;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import books.Book;
import books.BookType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;

public class NewBookScreenControler implements Initializable {
	
	@FXML
	TextField idField = new TextField();
	TextField titleField = new TextField();
	TextField authorField = new TextField();
	TextField publisherField = new TextField();
	TextField yearField = new TextField();
	ChoiceBox<BookType> bookType = new ChoiceBox<BookType>();
	String id, title, author, publisher, year;
	
	@FXML
    private void confirmButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        
        id = idField.getText();
        Book book = new Book(Integer.parseInt(id));
        book.setTitle(titleField.getText());
        book.setAuthor(authorField.getText());
        book.setPublisher(publisherField.getText());
        book.setYear(Integer.parseInt(yearField.getText()));

        
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
