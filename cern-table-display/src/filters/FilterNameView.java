package filters;

import application.Main;
import javafx.scene.control.TextField;

public class FilterNameView implements FilterView {

	@Override
	public void initialize() {

	}

	@Override
	public Object getValueFromView() {
		TextField tfDevice = (TextField) Main.scene.lookup("#filterName");
		String valSearch = tfDevice.getText().toLowerCase();
		return valSearch;
	}


}
