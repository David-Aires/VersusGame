package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MainGame extends StateBasedGame implements CONSTANTS {
	
	
	
	
	
	public static void main(String[] args) throws SlickException {
		ModelWindow start = new ModelWindow();
		new AppGameContainer(new MainGame(), start.getScreenWidth(), start.getScreenHeight(), false).start();
		
	}

	public MainGame() {
		super("Lesson 1 :: WindowGame");
	}
	
	 public void initStatesList(GameContainer container) throws SlickException {
		    addState(new MainWindow());
		    addState(new MainMap());
		  }



}


