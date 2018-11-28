/**
 * 
 */
package versus.view;

import java.util.Observer;

import versus.controller.CharacterController;
import versus.model.PlayerModel;

/**
 * @author Ajaxo
 *
 */
public abstract class GameView implements Observer {
	protected PlayerModel player;
	protected CharacterController controller;
	
	GameView(PlayerModel player,CharacterController controller){
		this.player= player;
		this.controller=controller;
		player.addObserver(this);
	}
	
	public abstract void affiche(String string) ;
}
