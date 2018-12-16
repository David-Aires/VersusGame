/**
 * 
 */
package versus.view;

import java.util.Observer;

import versus.controller.CharacterController;
import versus.controller.NetworkController;
import versus.model.PlayerModel;

/**
 * @author Aires David
 *
 */
public abstract class GameView implements Observer {
	protected PlayerModel player;
	protected CharacterController controller;
	protected NetworkController networkController;
	
	GameView(PlayerModel player,CharacterController controller, NetworkController networkController){
		this.player= player;
		this.controller=controller;
		this.networkController= networkController;
		player.addObserver(this);
	}
	
	public abstract void affiche(String string) ;
	
}


	