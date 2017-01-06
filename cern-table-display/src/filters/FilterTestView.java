package filters;

import application.Main;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FilterTestView implements FilterView {
	
	private static TextField field;
	
	@Override
	public void initialize() {
		AnchorPane anchor = (AnchorPane)Main.scene.lookup("#customFilterAnchorPane");
		field = new TextField();
		anchor.getChildren().add(field);

	}

	@Override
	public Object getValueFromView() {
		String val = field.getText().toLowerCase();
		return val;
	}

}
