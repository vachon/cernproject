package filters;

import java.lang.reflect.InvocationTargetException;

public abstract class Filter {
	
	public FilterView getView() {
		String className = this.getClass().getName() + "View";
		FilterView instance = null;
		try {
			instance = (FilterView) Class.forName(className).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public void callInitialize() {
		this.getView().initialize();
	}
	
	public Object callGetValueFromView() {
		return this.getView().getValueFromView();
	}
}
