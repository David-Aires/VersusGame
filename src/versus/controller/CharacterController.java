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
	

	// for example 9,6 IS EQUAL TO 10,7 in X,Y  
		//the part[0][x] is for the position in x of the traps
		//the part[1][y] is for the position in y of the traps
		private int [][]trap={{9,10,8,2},{6,5,3,2}};
		
		private int [][] backInX={{2,3,3},{5,3,7}};
		
		private int [][] backInY={{8,5,9},{7,8,7}};
	  
	  public void goBackX(){
			for(int i=0;i<2;i++) {
				if(player.getLX()==backInX[0][i] && player.getLY()==backInX[1][i]){
					mouvementLocal(player.getLX()-1,player.getLY(),false);
					vue.affiche("Un changement de direction en X a été activé(!Retenez son emplacement!)");

				}
			}
		}
		// when the player goes on it he
	  public void goBackY(){
			for(int i=0;i<2;i++) {
				if(player.getLX()==backInY[0][i] && player.getLY()==backInY[1][i]){
					mouvementLocal(player.getLX(),player.getLY()+1,false);
					vue.affiche("Un changement de direction en Y a été activé(!Retenez son emplacement!)");

				}
			}
		}
		//when the player is trapped he goes to his "camp"
	  public void isTrapped(){
			
				for(int i=0;i<2;i++) {
					if(player.getLX()==trap[0][i] && player.getLY()==trap[1][i]){
						mouvementLocal(0,5,false);
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
			
			
		} else if(x==player.getLX() && y==player.getLY() && player.getLMoving()){
			vue.affiche("Collision détectée!");
		
		} 
		
		
	}
	

}
