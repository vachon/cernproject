package application;
	
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cern.mpe.systems.core.domain.SystemUnderTest;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			StackPane page = (StackPane) FXMLLoader.load(Main.class.getResource("cernView.fxml"));
			Scene scene = new Scene(page);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			RandomGen test = new RandomGen();
			TableView tab = (TableView)scene.lookup("#tab");
			
			TableController controller = new TableController(tab);
			
			DataLayer.getInstance().setScene(scene);
			controller.initialize(DataLayer.getInstance().getData());
			
			Button btnLaunchFilters = (Button)scene.lookup("#btnLaunchFilters");
			btnLaunchFilters.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent event) {
					tab.getItems().clear();
					try {
						tab.setItems(DataLayer.getInstance().getData());
					} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
							| SecurityException e) {
						e.printStackTrace();
					}
				}
				
			});
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
