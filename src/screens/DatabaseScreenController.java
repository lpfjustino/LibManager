package screens;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import books.Book;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import libmanager.HomeScreenController;
import management.Library;
import management.Loan;
import users.User;

public class DatabaseScreenController implements Initializable {
    Stage primaryStage;

    @FXML private Label dbTypeLabel;
    
    @FXML private RadioButton rbCollection;
    @FXML private RadioButton rbLoans;
    @FXML private RadioButton rbUsers;
    
    @FXML private TableView collectionTable;
    @FXML private TableView loansTable;
    @FXML private TableView usersTable;
    // Users table
    @FXML private TableColumn<User, Integer> userIdCollumn;
    @FXML private TableColumn<User, String> nameCollumn;
    @FXML private TableColumn<User, String> userTypeCollumn;
    // Loans table
    @FXML private TableColumn<Loan, String> bookColumn;
    @FXML private TableColumn<Loan, String> borrowerColumn;
    @FXML private TableColumn<Loan, Date> expirationColumn;
    // Collection table
    @FXML private TableColumn<Book, Integer> bookIdColumn;
    @FXML private TableColumn<Book, String> titleColumn;
    @FXML private TableColumn<Book, String> authorColumn;
    @FXML private TableColumn<Book, Integer> quantityColumn;
    
    @FXML
    private void rbCollectionClick(ActionEvent event) {
        // Alterna entre as tabelas dependendo do que o usuário deseja listar
        collectionTable.setVisible(true);
        loansTable.setVisible(false);
        usersTable.setVisible(false);
        
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        
        bookIdColumn.setCellValueFactory(cell -> {
        	int bookId = ((Book) cell.getValue()).getId();
        	return new ReadOnlyObjectWrapper<>(bookId);
        });
        
//        quantityColumn.setCellValueFactory(cell -> {
//        	int bookQty = ((Book) cell.getValue()).;
//        	return new ReadOnlyObjectWrapper<>(bookId);
//        });
        
        ObservableList<Book> data = FXCollections.observableArrayList();
        HomeScreenController.library.getCollection()
        		.keySet()
                .forEach( book -> {
                    data.add(book);
                });
        collectionTable.setItems(data);
    }
    
    @FXML
    private void rbLoansClick(ActionEvent event) {
        // Alterna entre as tabelas dependendo do que o usuário deseja listar
        collectionTable.setVisible(false);
        loansTable.setVisible(true);
        usersTable.setVisible(false);
        
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("book"));
        borrowerColumn.setCellValueFactory(new PropertyValueFactory<>("borrower"));
        
        expirationColumn.setCellValueFactory(cell -> {
            Date expDate = ((Loan) cell.getValue()).getExpirationDate();
            return new ReadOnlyObjectWrapper<>(expDate);
        });
        
        ObservableList<Loan> loanData = FXCollections.observableArrayList();
        HomeScreenController.library.getLoans()
                .stream()
                .forEach( loan -> {
                    loanData.add(loan);
                });
        loansTable.setItems(loanData);
    }
    
    @FXML
    private void rbUsersClick(ActionEvent event) {
        // Alterna entre as tabelas dependendo do que o usuário deseja listar
        collectionTable.setVisible(false);
        loansTable.setVisible(false);
        usersTable.setVisible(true);
        
        // Insere os valores na tabela
        userIdCollumn.setCellValueFactory(cell -> {
            int userID = ((User) cell.getValue()).getID();
            return new ReadOnlyObjectWrapper<>(userID);
        });
        nameCollumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        ObservableList<User> data = FXCollections.observableArrayList();
        HomeScreenController.library.getUsers()
                .stream()
                .forEach( user -> {
                    data.add(user);
                });
        usersTable.setItems(data);
    }
        
    @FXML
    private void homeButtonAction(ActionEvent event) {
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
    }
        
//    public void initializeLibrary(Library library) {
//        this.library = library;
//    }

}
