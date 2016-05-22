package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import utils.LoadConfig;

public class Seafile_controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
         LoadConfig.loadSettings();
		
		
	}

}
