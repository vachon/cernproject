package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import cern.mpe.systems.core.domain.SystemUnderTest;
import cern.mpe.systems.core.service.control.SystemsControllerImpl;
import cern.mpe.systems.core.service.manage.SystemAttributesManagerImpl;
import cern.mpe.systems.core.service.manage.SystemsManagerImpl;
import cern.mpe.systems.core.service.provider.SystemsProvider;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mpe.commons.util.checks.Checks;


public class DataLayer {
	
	private static DataLayer instance;
	private Collection<SystemUnderTest> listUnderTest;
	private SystemsManagerImpl systemsManager;
	private Collection<SystemsProvider> systemProviders;
	private Scene scene;
	private boolean isFirstLaunch;
	
	public Collection<SystemUnderTest> getListUnderTest() {
		return listUnderTest;
	}

	public Scene getScene() {
		return scene;
	}
	
	public void setListUnderTest(Collection<SystemUnderTest> listUnderTest) {
		this.listUnderTest = listUnderTest;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public boolean isFirstLaunch() {
		return isFirstLaunch;
	}

	public void setFirstLaunch(boolean isFirstLaunch) {
		this.isFirstLaunch = isFirstLaunch;
	}

	private DataLayer() {
		super();
	}
	
	public final static DataLayer getInstance() {

        if (DataLayer.instance == null) {
           synchronized(DataLayer.class) {
             if (DataLayer.instance == null) {
               DataLayer.instance = new DataLayer();
               DataLayer.getInstance().isFirstLaunch = true;
             }
           }
        }
        return DataLayer.instance;
    }
	
	//setup of systemsManager, systems providers, etc
	private void stepUpSystemsControl()
	{
		systemsManager = new SystemsManagerImpl();
		systemProviders = new ArrayList<SystemsProvider>();
		
		RandomGen test = new RandomGen();
		RandomGen test2 = new RandomGen();
		
		systemProviders.add(test);
		systemProviders.add(test2);

		Checks.notNull(systemProviders, "systemProviders");
        Checks.notNull(systemsManager, "systemsManager");
        
		systemsManager.setSystemsProviders(systemProviders);
        SystemAttributesManagerImpl systemAttributesManager = new SystemAttributesManagerImpl();
        SystemsControllerImpl systemsController = new SystemsControllerImpl();
        systemsController.setSystemsManager(systemsManager);
        systemsController.setSystemAttributesManager(systemAttributesManager);
        systemsManager.init();
	}
	
	public ObservableList<TableItem> getData() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
		if(isFirstLaunch)
			stepUpSystemsControl();
        
        listUnderTest = systemsManager.getAllSystemsUnderTest();
		this.filterName();
		this.filterType();
		this.filterRelation();
		
		this.isFirstLaunch = false;
		return convertToObservableList();
	}
	
	//edit table "props" to change displayed items
	private ObservableList<TableItem> convertToObservableList() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
		ObservableList<TableItem> data = FXCollections.observableArrayList();
		for(SystemUnderTest item : listUnderTest)
		{
			String props[] = {item.getName(),item.getKey().toString(),item.getSystemAttributes().toString(),"test","test2"};
			data.add(new TableItem(props));
		}
		return data;
	}
	
	private void filterName()
	{
		Collection<SystemUnderTest> list = new HashSet<>();
		
		TextField tfDevice = (TextField) scene.lookup("#filterName");
		String valSearch = tfDevice.getText().toLowerCase();
		for(SystemUnderTest item : listUnderTest)
		{
			String oneItem = item.getName().toLowerCase();
			if(oneItem.indexOf(valSearch) != -1)
				list.add(item);
		}
		listUnderTest = list;
	}
	
	private void filterType()
	{
		Collection<SystemUnderTest> list = new HashSet<>();
		ComboBox cbType = (ComboBox) scene.lookup("#cbType");
		if(isFirstLaunch)
		{
			cbType.getItems().add("-");
			cbType.getItems().addAll(systemsManager.getAllSystemTypes());
			cbType.getSelectionModel().selectFirst();
		}
		
		
		for(SystemUnderTest item : listUnderTest)
		{
			String itemstr = item.getClass().getSimpleName();
			String cbstr = cbType.getValue().toString();
			if(itemstr.equals(cbstr) || cbstr.equals("-"))
			{
				System.out.println("ok");
				list.add(item);
			}
		}
		listUnderTest = list;
	}
	
	private void filterRelation()
	{
		
	}

}
