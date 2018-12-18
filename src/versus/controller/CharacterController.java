/**
 * 
 */
package versus.controller;

import java.io.IOException;

import versus.controller.NetworkController.receiveMove;
import versus.model.PlayerModel;
import versus.view.GameView;
import versus.view.GameViewMap;

/**
 * @author Aires David
 *
 */
public class CharacterController {
	PlayerModel player;
	GameView vue;
	GameViewMap vueGUI;
	NetworkController controllerNetwork;
	//for example 9,6 IS EQUAL TO 10,7 in X,Y  
	//the part[0][x] is for the position in x of the traps
	//the part[1][y] is for the position in y of the traps
	
												//yellow
	private int [][]trap={{0,0,1,2,6,8,9,9,10},{0,14,9,2,14,3,6,10,5}};
												//purple
	private int [][] backInX={{2,2,3,3,4,4,6,6,8,9,9,11,12,12,13,13,13,13,13},{5,10,3,7,13,11,3,6,1,14,7,12,3,8,11,13,14,0,4}};
												//magenta
	private int [][] backInY={{0,1,2,3,4,4,5,6,6,6,8,8,13},{6,6,12,0,5,10,8,2,5,11,7,11,7}};
	  
	public void goBackX(){
		for(int i=0;i<backInX[0].length;i++) {
			if(player.getLX()==backInX[0][i] && player.getLY()==backInX[1][i]){
				mouvementLocal(player.getLX()-2,player.getLY(),false);
				vue.affiche("Un changement de direction en X a été activé(!Retenez son emplacement!)");
			}
		}
	}
	
	// when the player goes on it he
	public void goBackY(){
		for(int i=0;i<backInY[0].length;i++) {
			if(player.getLX()==backInY[0][i] && player.getLY()==backInY[1][i]){
				mouvementLocal(player.getLX(),player.getLY()+2,false);
				vue.affiche("Un changement de direction en Y a été activé(!Retenez son emplacement!)");
			}
		}
	}
		
	//when the player is trapped he goes to his "camp"
	public void isTrapped(){
		for(int i=0;i<trap[0].length;i++) {
			if(player.getLX()==trap[0][i] && player.getLY()==trap[1][i]){
				mouvementLocal(0,7,false);
				vue.affiche("Vous êtes tombés dans un piège(!Retenez son emplacement!)");
			}
		}
	}

	// call all check trap
	public void checkTrap() {
		goBackX();
		goBackY();
		isTrapped();
	}
	
	public CharacterController(PlayerModel player, NetworkController networkController) {
		this.player=player;
		this.controllerNetwork= networkController;
	}
	
	public void addview(GameView vue) {
		this.vue=vue;
	}
	
	public void mouvementLocal(int x,int y,boolean move) {
		if(!(player.getLMoving())) {
			vue.affiche("Ce n'est pas votre tour");
		} 
		if(player.getLMoving() && (x!=player.getLX() || y!=player.getLY())) {
			player.mouvementsLocal(x, y, move);
			checkTrap();
			if(player.getLX()==x && player.getLY()==y) {
				player.setLMoving(false);
			}
			controllerNetwork.sendMove();
		} 
		else if(x==player.getLX() && y==player.getLY() && player.getLMoving()){
			vue.affiche("Collision détectée!");
		} 
	}
}