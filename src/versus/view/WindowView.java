package versus.view;

import java.util.Observer;

import versus.controller.WindowController;
import versus.model.WindowModel;



public abstract class WindowView implements Observer{

	
	 protected WindowModel model;
	protected WindowController controller;
	
	WindowView(WindowModel model,
			WindowController controller) {
		this.model = model;
		this.controller = controller;
		model.addObserver(this); // Connection between the view and the model
	}

	
	
}
