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
import cern.mpe.systems.core.service.control.SystemRelationController;
import cern.mpe.systems.core.service.control.SystemRelationControllerImpl;
import cern.mpe.systems.core.service.control.SystemsControllerImpl;
import cern.mpe.systems.core.service.manage.SynchronizedMultiMapSystemRelationEngine;
import cern.mpe.systems.core.service.manage.SystemAttributesManagerImpl;
import cern.mpe.systems.core.service.manage.SystemRelationEngine;
import cern.mpe.systems.core.service.manage.SystemRelationManagerImpl;
import cern.mpe.systems.core.service.manage.SystemsManagerImpl;
import cern.mpe.systems.core.service.provider.SystemRelationProvider;
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

public abstract class AbstractDataLayer {
	
	protected boolean isFirstLaunch;
    protected SystemsControllerImpl systemController;
    protected SystemRelationControllerImpl relationController;
    protected SystemRelationEngine relationEngine;
	protected SystemsManagerImpl systemsManager;
	protected SystemRelationManagerImpl relationManager;
	protected Collection<SystemsProvider> systemProviders;
	protected List<SystemRelationProvider> relationProviders;
	protected Collection<SystemUnderTest> systemsUnderTest;
	protected Collection<SystemRelation> relationsUnderTest;
	protected Collection<Filter> filters;
	
	public boolean isFirstLaunch() {
		return isFirstLaunch;
	}

	public SystemsControllerImpl getSystemController() {
		return systemController;
	}

	public SystemRelationControllerImpl getRelationController() {
		return relationController;
	}

	public SystemsManagerImpl getSystemsManager() {
		return systemsManager;
	}

	public SystemRelationManagerImpl getRelationManager() {
		return relationManager;
	}

	public Collection<SystemsProvider> getSystemProviders() {
		return systemProviders;
	}

	public Collection<SystemRelationProvider> getRelationProviders() {
		return relationProviders;
	}

	public Collection<SystemUnderTest> getSystemsUnderTest() {
		return systemsUnderTest;
	}

	public Collection<SystemRelation> getRelationsUnderTest() {
		return relationsUnderTest;
	}

	public Collection<Filter> getFilters() {
		return filters;
	}

	public void setFirstLaunch(boolean isFirstLaunch) {
		this.isFirstLaunch = isFirstLaunch;
	}

	public void setSystemController(SystemsControllerImpl systemController) {
		this.systemController = systemController;
	}

	public void setRelationController(SystemRelationControllerImpl relationController) {
		this.relationController = relationController;
	}

	public void setSystemsManager(SystemsManagerImpl systemsManager) {
		this.systemsManager = systemsManager;
	}

	public void setRelationManager(SystemRelationManagerImpl relationManager) {
		this.relationManager = relationManager;
	}

	public void setSystemProviders(Collection<SystemsProvider> systemProviders) {
		this.systemProviders = systemProviders;
	}

	public void setRelationProviders(List<SystemRelationProvider> relationProviders) {
		this.relationProviders = relationProviders;
	}

	public void setSystemsUnderTest(Collection<SystemUnderTest> systemsUnderTest) {
		this.systemsUnderTest = systemsUnderTest;
	}

	public void setRelationsUnderTest(Collection<SystemRelation> relationsUnderTest) {
		this.relationsUnderTest = relationsUnderTest;
	}

	public void setFilters(Collection<Filter> filters) {
		this.filters = filters;
	}
	
	//setup of systemsManager, systems providers, etc
	protected abstract void setUpSystems();

	protected abstract void setUpSystemRelations();
	
	protected abstract void setUpFilters();

	//return systems to an observable list
	//There are call filter function and setUpFunctions
	public ObservableList<TableItem> getData() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, IOException
	{
		if(isFirstLaunch){
			this.setUpSystems();
			this.setUpSystemRelations();
			this.setUpFilters();
		}
		systemsUnderTest = systemController.getAllSystemsUnderTest();
        //JSONWriter.genJSON();
		for(Filter filter : filters){
			systemsUnderTest = ((FilterInterface)filter).filter(systemsUnderTest);
		} 
		this.isFirstLaunch = false;
		return this.convertToObservableList(systemsUnderTest);
	}
	
	//edit table "props" to change displayed items
	protected abstract ObservableList<TableItem> convertToObservableList(Collection<SystemUnderTest> listUnderTest) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException;

}
