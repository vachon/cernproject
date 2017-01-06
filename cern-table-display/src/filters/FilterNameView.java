package filters;

import application.Main;
import javafx.scene.control.TextField;

public class FilterNameView implements FilterView {
	
	private static TextField field;
	
	@Override
	public void initialize() {
		field = (TextField) Main.scene.lookup("#filterName");
	}

	@Override
	public Object getValueFromView() {
		String valSearch = field.getText().toLowerCase();
		return valSearch;
	}


}
