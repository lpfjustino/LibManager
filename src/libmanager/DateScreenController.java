package libmanager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class DateScreenController implements Initializable {
	Stage primaryStage;
	Date date;

	@FXML
	private DatePicker datePicker;

	@FXML
	private void datePickerAction(ActionEvent event) {
		
		primaryStage = (Stage) ((DatePicker) event.getSource()).getScene().getWindow();
		Platform.runLater(
				() -> {
					try {
						String pattern = "dd-MM-yyyy";
						datePicker.setPromptText(pattern.toLowerCase());
						LocalDate localDate = datePicker.getValue();
						date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
						FXMLLoader homeScreenLoader = new FXMLLoader(getClass().getResource("/libmanager/HomeScreen.fxml"));
						Parent loans = homeScreenLoader.load();
						Scene scene = new Scene(loans);
						primaryStage.setScene(scene);
						primaryStage.show();
						
				        HomeScreenController hsc = homeScreenLoader.getController();
				        hsc.initializeLibrary(date);
					} catch (IOException ex) { 
						ex.printStackTrace();
					}
				});

	}
//	
	@FXML
	private void confirmButtonAction(ActionEvent event) {
//		
//		primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//		Platform.runLater(
//				() -> {
//					try {
//						Parent loans = FXMLLoader.load(getClass().getResource("/libmanager/HomeScreen.fxml"));
//						Scene scene = new Scene(loans);
//						primaryStage.setScene(scene);
//						primaryStage.show();
//						
//						FXMLLoader homeScreenLoader = new FXMLLoader(getClass().getResource("dateScreen.fxml"));
//				        HomeScreenController hsc = homeScreenLoader.getController();
//				        hsc.initializeLibrary(date);
//					} catch (IOException ex) { }
//				});
//
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
