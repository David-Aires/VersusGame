/**
 * (?)
 */
package versus.controller;

/**
 * This imported class is used to generate a random numbers.
 */
import java.util.Random;

import versus.model.PlayerModel;
import versus.view.GameView;
import versus.view.GameViewMap;

/**
 * This class represents the Character Controller.
 * @author Aires David
 */
public class CharacterController {
	/**
	 * (?)
	 */
	PlayerModel player;
	/**
	 * (?)
	 */
	GameView vue;
	/**
	 * (?)
	 */
	GameViewMap vueGUI;
	/**
	 * (?)
	 */
	NetworkController controllerNetwork;
	/**
	 * Creation of the first random number using the random class.
	 */
	Random random1 = new Random();
	/**
	 * Creation of the second random number using the random class.
	 */
	Random random2 = new Random();

	/**
	 * This method is a trap.
	 * If the player has no extra life, he's teleported 2 frames to the "Left/Right/Top/Bottom" (?).
	 * In the opposite case, he loses a life.
	 */
	public void goBackX(){
		for(int i=0;i<player.getBackX()[0].length;i++) {
			if(player.getLX()==player.getBackX()[0][i] && player.getLY()==player.getBackX()[1][i] && player.getLlife()==0){
				mouvementLocal(player.getLX()-2,player.getLY(),0);
				vue.affiche("Un changement de direction en X a été activé(!Retenez son emplacement!)");
			}
			
			else if(player.getLX()==player.getBackX()[0][i] && player.getLY()==player.getBackX()[1][i] && player.getLlife()>0) {
				vue.affiche("Tu es chanceux! (une vie en moins) ");
				player.setLlife(player.getLlife()-1);
				
			}
		}
	}
		
	/**
	 * This method is a trap.
	 * If the player has no extra life, he's teleported 2 frames to the "Left/Right/Top/Bottom" (?).
	 * In the opposite case, he loses a life.
	 */
	public void goBackY(){
		for(int i=0;i<player.getBackY()[0].length;i++) {
			if(player.getLX()==player.getBackY()[0][i] && player.getLY()==player.getBackY()[1][i] && player.getLlife()==0){
				mouvementLocal(player.getLX(),player.getLY()+2,0);
				vue.affiche("Un changement de direction en Y a été activé(!Retenez son emplacement!)");
			}
			
			else if(player.getLX()==player.getBackY()[0][i] && player.getLY()==player.getBackY()[1][i] && player.getLlife()>0) {
				vue.affiche("Tu es chanceux! (une vie en moins) ");
				player.setLlife(player.getLlife()-1);
				
			}
		}
	}
		
	/**
	 * This method is a trap.
	 * If the player has no extra life, he's teleported 2 frames to the "Left/Right/Top/Bottom" (?).
	 * In the opposite case, he loses a life.
	 */
	public void isTrapped(){
		for(int i=0;i<player.getTrap()[0].length;i++) {
			if(player.getLX()==player.getTrap()[0][i] && player.getLY()==player.getTrap()[1][i] && player.getLlife()==0){
				mouvementLocal(0,7,0);
				vue.affiche("Vous êtes tombés dans un piège(!Retenez son emplacement!)");
			}
			
			else if(player.getLX()==player.getTrap()[0][i] && player.getLY()==player.getTrap()[1][i] && player.getLlife()>0) {
				vue.affiche("Tu es chanceux! (une vie en moins) ");
				player.setLlife(player.getLlife()-1);
				
			}
		}
	}
	
	/**
	 * This method is a bonus.
	 * The first random number generated define the type of Bonus.
	 * The second random number generated define the number of displacement bonus if the bonus is this type.
	 */
	public void isBonus() {
		for(int i=0;i<player.getBonus().get(0).size();i++) {
			if(player.getLX()== player.getBonus().get(0).get(i) && player.getLY()==player.getBonus().get(1).get(i)) {
				int rand = random1.nextInt(1-0 +1);
				switch(rand) {
				case 0:
					player.setLlife(player.getLlife()+1);
					vue.affiche("Bonus de vie"+"\nVous avez gagné une vie!");
					
					break;
					
				case 1:
					int depl = random2.nextInt(3-1 +1);
					vue.affiche("Bonus de déplacement: "+depl);
					player.setLMoving(player.getLMoving()+depl);
					
					break;
				}
				player.getBonus().get(0).remove(i);
				player.getBonus().get(1).remove(i);
			}
		}
	}

	/**
	 * This method "call all check trap" (?).
	 */
	public void checkTrap() {
		goBackX();
		goBackY();
		isTrapped();
	}

	/**
	 * This constructor creates the Character controller using player & networkController.
	 * @param player (?)
	 * @param controllerNetwork (?)
	 */
	public CharacterController(PlayerModel player, NetworkController networkController) {
		this.player=player;
		this.controllerNetwork= networkController;
	}
	
	/**
	 * This method (?)
	 * @param vue (?)
	 */
	public void addview(GameView vue) {
		this.vue=vue;
	}
	
	/**
	 * This method allow the player to move if it's his turn and return a message if a collision occurs.
	 * @param x X coordinates where the player wants to move
	 * @param y Y coordinates where the player wants to move
	 * @param move (?)
	 */
	public void mouvementLocal(int x,int y,int move) {
		if(player.getLMoving()==0) {
			vue.affiche("Ce n'est pas votre tour");
		} 
		if(player.getLMoving()>0 && (x!=player.getLX() || y!=player.getLY())) {
			player.mouvementsLocal(x, y, move);
			checkTrap();
			isBonus();
			if(player.getLX()==x && player.getLY()==y && player.getLMoving()!=0) {
				player.setLMoving(player.getLMoving()-1);
			}
			controllerNetwork.sendMove();
		} 
		else if(x==player.getLX() && y==player.getLY() && player.getLMoving()==0){
			vue.affiche("Collision détectée!");
		} 
	}
}