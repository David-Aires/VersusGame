package versus.testGame;

import org.newdawn.slick.AppGameContainer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import main.MainGame;
import main.MainMap;
import main.MainWindow;
import main.ModelWindow;
import versus.controller.WindowController;
import versus.model.WindowModel;
import versus.view.WindowViewConsole;
import versus.view.WindowViewGUI;

public class WindowVersus {

	//A IMPLEMENTER
	
	public WindowVersus() {
		//Création du modèle
		WindowModel windmod = new WindowModel();
		//Création des contrôleurs : Un pour chaque vue
		//Chaque contrôleur doit avoir une référence vers le modèle pour pouvoir le commander
		WindowController windcontrolConsole = new WindowController(windmod);
		WindowController windcontrolGUI = new WindowController(windmod);

		//Création des vues.
		//Chaque vue doit connaître son contrôleur et avoir une référence vers le modèle pour pouvoir l'observer
		WindowViewGUI gui = new WindowViewGUI(windmod, windcontrolGUI);
		WindowViewConsole console = new WindowViewConsole(windmod, windcontrolConsole);
		//On donne la référence à  la vue pour chaque contrôleur
		windcontrolGUI.addView(gui);
			
		windcontrolConsole.addView(console);
		
	}
	
	public static void main(String args[]) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new WindowVersus();
			}
		});
	}
	
	
	
	
	
	/*public static void main(String[] args) throws SlickException {
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

*/
}
