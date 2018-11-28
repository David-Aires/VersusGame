/**
 * 
 */
package versus.testGame;



import versus.controller.CharacterController;
import versus.model.PlayerModel;
import versus.view.GameViewConsole;
import versus.view.GameViewMap;

/**
 * @author Aires David
 *
 */
public class GameTest {

	
	public GameTest() {

		PlayerModel player = new PlayerModel();
	
		
		CharacterController GamecontrolConsole = new CharacterController(player);
		CharacterController GamecontrolMap = new CharacterController(player);

		
		GameViewMap map = new GameViewMap(player, GamecontrolMap);
		GameViewConsole console = new GameViewConsole(player, GamecontrolConsole);
		
		GamecontrolMap.addview(map);
		GamecontrolConsole.addview(console);
		
		
	}
	
	
	public static void main(String args[]) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GameTest();
			}
		});
				
				
		
	
	}

	
}


