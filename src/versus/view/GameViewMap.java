/**
 * 
 */
package versus.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import versus.controller.CharacterController;
import versus.model.PlayerModel;



/**
 * @author Aires David
 *
 */
public class GameViewMap extends GameView  implements Observer {
	private JFrame MapJFrame;
	

	public GameViewMap(PlayerModel player,CharacterController controller) {
		super(player,controller);
		MapJFrame = new JFrame("Versus");	
		MapJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MapJFrame.setSize(500, 400);
		MapJFrame.setLocation(300, 400);
		MapJFrame.setVisible(true);
	}

	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void affiche(String string) {
		// TODO Auto-generated method stub
		
	}

}



