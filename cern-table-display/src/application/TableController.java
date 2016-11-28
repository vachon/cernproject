package application;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableController {
	
	private TableView<TableItem> table;
	
	

	public TableView<TableItem> getTable() {
		return table;
	}



	public void setTable(TableView<TableItem> table) {
		this.table = table;
	}

	

	public TableController(TableView<TableItem> table) {
		super();
		this.table = table;
	}



	@FXML
    protected void initialize(ObservableList<TableItem> tableItems) {

		for(int i = 1;i<=tableItems.get(0).nbProps;i++)
		{
			PropertyValueFactory<TableItem, String> columnProperty = new PropertyValueFactory<TableItem, String>("prop"+i);
			TableColumn<TableItem, String> column = new TableColumn<TableItem, String>("C"+i);
			table.getColumns().add(column);
			column.setCellValueFactory( columnProperty );
		}
		table.setItems( tableItems );
    }
}
