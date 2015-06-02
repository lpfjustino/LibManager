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
//        String dateString = "January 2, 2010";
//        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
//        Date date = format.parse(dateString);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DateScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
            
            /////////////////// SUSPENSION //////////////////
//            String suspensionString = "January 4, 2010";
//            Date suspension = format.parse(suspensionString);
//            User suspendable = library.getUser(2);
//            library.suspendUser(suspendable, suspension);
    }
    
}
