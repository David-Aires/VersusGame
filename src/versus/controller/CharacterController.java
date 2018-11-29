/**
 * 
 */
package versus.controller;

import versus.model.PlayerModel;
import versus.view.GameView;

/**
 * @author Aires David
 *
 */
public class CharacterController {
	PlayerModel player;
	GameView vue;
	
	public CharacterController(PlayerModel player) {
		this.player=player;
	}
	
	public void addview(GameView vue) {
		this.vue=vue;
		
	}
	
	public String mouvementLocal(int x,int y) {
		//if(player.getLMoving()) {
			player.mouvementsLocal(x, y, false);
			return "Mouvement effectué";
		//} else {
		//	return "Ce n'est pas votre tour";
		//}
	}

}
