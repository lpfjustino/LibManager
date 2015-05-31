package libmanager;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import management.Library;
import users.Community;
import users.Student;
import users.Teacher;
import users.User;
import users.UserType;

public class LibManager extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        launch(args);
        
        String string = "January 2, 2010";
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        try {
            Date date = format.parse(string);
            Library library = new Library(date);
            
            /////////////////// NEW USERS ///////////////////
//            Student istudanty = new Student(1, "Esteiquerino");
//            library.registerUser(istudanty, UserType.STUDENT);
//            Teacher titxer = new Teacher(2, "Fodaserino");
//            library.registerUser(titxer, UserType.TEACHER);
//            Community com = new Community(3, "Commu");
//            library.registerUser(com, UserType.COMMUNITY);
//            library.listUsers();
            
            /////////////////// NEW BOOKS //////////////////
//            Book hp = new Book(1);
//            hp.setTitle("Harry Potter");
//            hp.setAuthor("JK Rowling");
//            hp.setType(BookType.TEXT);
//            
//            Book hp2 = new Book(2);
//            hp2.setTitle("Harry Potter 2");
//            hp2.setAuthor("JK Rowling 2");
//            hp2.setType(BookType.GENERAL);
//            
//            library.addToCollection(hp, 5);
//            library.addToCollection(hp2, 2);
//            library.listCollection();
//            
//            // Substituição de hp2
//            Book hp3 = new Book(2);
//            hp2.setTitle("Harry Potter 2000");
//            hp2.setAuthor("JK Rowling 20000");
//            hp2.setType(BookType.TEXT);
//            library.addToCollection(hp3, 200);
//            library.listCollection();
            
            /////////////////// SUSPENSION //////////////////
//            String suspensionString = "January 4, 2010";
//            Date suspension = format.parse(suspensionString);
//            User suspendable = library.getUser(2);
//            library.suspendUser(suspendable, suspension);
            
        } catch (IOException | ParseException ex) {}
    }
    
}
