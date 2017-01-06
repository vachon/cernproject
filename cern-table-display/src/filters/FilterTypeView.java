package filters;

import application.DataLayer;
import application.Main;
import javafx.scene.control.ComboBox;

public class FilterTypeView implements FilterView {

	@Override
	public void initialize() {
		
		ComboBox cbType = (ComboBox) Main.scene.lookup("#cbType");
		cbType.getItems().add("-");
		cbType.getItems().addAll(DataLayer.getInstance().getSystemsManager().getAllSystemTypes());
		cbType.getSelectionModel().selectFirst();
	}

	@Override
	public Object getValueFromView() {
		ComboBox cbType = (ComboBox) Main.scene.lookup("#cbType");
		String value = cbType.getValue().toString();
		return value;
	}

}
