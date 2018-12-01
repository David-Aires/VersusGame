/**
 * 
 */
package versus.model;

import java.util.Observable;



/**
 * @author Aires David, Quentin Lebrun
 *
 */
public class PlayerModel extends Observable {
	CharacterModel plocalModel;
	CharacterModel enemyModel;
	
	public PlayerModel() {
		plocalModel= new CharacterModel();
		plocalModel.setX(0);
		plocalModel.setY(5);
		
		enemyModel= new CharacterModel();
		enemyModel.setX(10);
		enemyModel.setY(5);
	}
	
	// change the position in x,y and the boolean moving
	public void mouvementsLocal(int x,int y,boolean moving) {
		if(x!=plocalModel.getX() || y!=plocalModel.getY()) {
			plocalModel.mouvement(x, y,moving);
			setChanged();
			notifyObservers();
		}
		
		
	
	}
	public String toString() {
		String str="Joueur local";
		str+=plocalModel.toString();
		str+="\n\n\nJoueur réseau";
		str+=enemyModel.toString();
		return str;
		
	}
	// get position x for player local Model
	public int getLX() {
		return plocalModel.getX();
	}
	// get position y for player local Model
	public int getLY() {
		return plocalModel.getY();
	}
	
	// get the state of moving of  player local Model
	public boolean getLMoving() {
		return plocalModel.isMoving();
	}

	
	// get position x for enemy Model
	public int getRX() {
		return enemyModel.getX();
	}
	
	// get position y for enemy Model
	public int getRY() {
		return enemyModel.getY();
	}
	
	// get the state of moving of  enemy Model
	public boolean getRMoving() {
		return enemyModel.isMoving();
	}
}