/**
 * (?).
 */
package versus.view;

/**
 * This imported class is used to informed changes in observable objects.
 */
import java.util.Observer;

import versus.controller.CharacterController;
import versus.controller.NetworkController;
import versus.model.PlayerModel;

/**
 * This class represents the Game View.
 * @author Aires David
 */
public abstract class GameView implements Observer {
	/**
	 * (?)
	 */
	protected PlayerModel player;
	/**
	 * (?)
	 */
	protected CharacterController controller;
	/**
	 * (?)
	 */
	protected NetworkController networkController;
	
	/**
	 * This constructor creates the Game View.
	 * @param player (?)
	 * @param controller (?)
	 * @param networkController (?)
	 */
	GameView(PlayerModel player,CharacterController controller, NetworkController networkController){
		this.player= player;
		this.controller=controller;
		this.networkController= networkController;
		player.addObserver(this);
	}
	/**
	 * This method gives a textual representation of (?).
	 * @param string (?)
	 */
	public abstract void affiche(String string) ;
}