/**
 * 
 */
package versus.testGame;



import versus.controller.CharacterController;
import versus.model.PlayerModel;
import versus.view.GameViewConsole;
import versus.view.GameViewMap;

/**
 * @author Aires David, Quentin Lebrun
 * 
 *
 */
public class GameTest {

	
	public GameTest() {
		//Creation of model 
		PlayerModel player = new PlayerModel();
		//Creation of controllers : one for each view	
		//Each controller must have a reference to the model in order to be able to order it
		CharacterController GamecontrolConsole = new CharacterController(player);
		CharacterController GamecontrolMap = new CharacterController(player);

		//Creation of views
		//Each view must know its controller and have a reference to the model to be able to observe it
		GameViewMap map = new GameViewMap(player, GamecontrolMap);
		GameViewConsole console = new GameViewConsole(player, GamecontrolConsole);
		
		//The reference to the view is given for each controller
		GamecontrolMap.addview(map);
		GamecontrolConsole.addview(console);
		
		
	}
	
	
	public static void main(String args[]) {
		//Schedule a job for the event-dispatching thread:
		//Creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GameTest();
			}
		});
				
				
		
	
	}

	
}


