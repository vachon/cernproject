package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import cern.mpe.systems.core.domain.SystemInformation;
import cern.mpe.systems.core.domain.SystemUnderTest;
import cern.mpe.systems.core.domain.relation.SystemRelation;
import cern.mpe.systems.core.domain.relation.SystemRelations;
import cern.mpe.systems.core.service.control.SystemsControllerImpl;
import cern.mpe.systems.core.service.manage.SystemAttributesManagerImpl;
import cern.mpe.systems.core.service.manage.SystemsManagerImpl;
import cern.mpe.systems.core.service.provider.SystemsProvider;
import filters.Filter;
import filters.FilterInterface;
import filters.FilterName;
import filters.FilterTest;
import filters.FilterType;
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
import json.JSONWriter;
import mpe.commons.util.checks.Checks;

public class DataLayer {
	
	private static DataLayer instance;
	private SystemsManagerImpl systemsManager;
	private Collection<SystemsProvider> systemProviders;
	private RandomGenRelations randRelation;
	//private Scene scene;
	private boolean isFirstLaunch;
	private Collection<SystemRelation> rel;
	private Collection<Filter> filters;
	
	
	
	public SystemsManagerImpl getSystemsManager() {
		return systemsManager;
	}

	public Collection<SystemsProvider> getSystemProviders() {
		return systemProviders;
	}

	public void setSystemsManager(SystemsManagerImpl systemsManager) {
		this.systemsManager = systemsManager;
	}

	public void setSystemProviders(Collection<SystemsProvider> systemProviders) {
		this.systemProviders = systemProviders;
	}

	public Collection<SystemRelation> getRel() {
		return rel;
	}

	public void setRel(Collection<SystemRelation> rel) {
		this.rel = rel;
	}

	public RandomGenRelations getRandRelation() {
		return randRelation;
	}

	public void setRandRelation(RandomGenRelations randRelation) {
		this.randRelation = randRelation;
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
	private void setUpSystemsControl()
	{
		systemsManager = new SystemsManagerImpl();
		systemProviders = new ArrayList<SystemsProvider>();
		randRelation = new RandomGenRelations();
		RandomGenSystems test = new RandomGenSystems();
		
		systemProviders.add(test);

		Checks.notNull(systemProviders, "systemProviders");
        Checks.notNull(systemsManager, "systemsManager");
        
		systemsManager.setSystemsProviders(systemProviders);
        SystemAttributesManagerImpl systemAttributesManager = new SystemAttributesManagerImpl();
        SystemsControllerImpl systemsController = new SystemsControllerImpl();
        systemsController.setSystemsManager(systemsManager);
        systemsController.setSystemAttributesManager(systemAttributesManager);
        
        systemsManager.init();
	}
	
	public void setUpFilters(){
		filters = new HashSet<>();
		filters.add(new FilterName());
		filters.add(new FilterType());
		filters.add(new FilterTest());
		for(Filter filter : filters){
			filter.callInitialize();
		}
	}

	
	//return systems to an observable list
	//There are call filter function and setUpFunctions
	public ObservableList<TableItem> getData() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, IOException
	{
		if(isFirstLaunch){
			setUpSystemsControl();
			setUpFilters();
		}
		Collection<SystemUnderTest> listUnderTest = systemsManager.getAllSystemsUnderTest();
        
        randRelation = new RandomGenRelations();
        rel = new HashSet<>();
        rel = randRelation.genAllRelations(systemsManager);
        JSONWriter.genJSON(rel);
        System.out.println(rel.size());
		for(Filter filter : filters){
			listUnderTest = ((FilterInterface)filter).filter(listUnderTest);
		} 
		
		this.isFirstLaunch = false;
		return convertToObservableList(listUnderTest);
	}
	
	//edit table "props" to change displayed items
	private ObservableList<TableItem> convertToObservableList(Collection<SystemUnderTest> listUnderTest) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
		ObservableList<TableItem> data = FXCollections.observableArrayList();
		
		int index = 0;
		for(SystemUnderTest item : listUnderTest)
		{
			String source = "";
			String target = "";
			for(SystemRelation relation : rel)
			{
				if(item.equals(relation.getTarget()))
					source += relation.getSource().getName() + ", ";
				if(item.equals(relation.getSource()))
					target += relation.getTarget().getName() + ", ";
			}
			String props[] = {Integer.toString(index),item.getName(),item.getKey().toDbString(),source,target};
			index++;
			data.add(new TableItem(props));
		}
		return data;
	}

}
