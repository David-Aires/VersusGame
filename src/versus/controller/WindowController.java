package versus.controller;


import versus.model.WindowModel;
import versus.view.WindowView;

public class WindowController {

	private WindowModel model;
	private WindowView view = null;
	
	public WindowController(WindowModel m){
		model = m;

	}
	
	/*
	 * 
	 * add all methods 
	 * with control(); at the end
	 * 
	 * 
	 * */
	
	
	
	public void addView(WindowView view) {
		this.view = view;
	}
}
