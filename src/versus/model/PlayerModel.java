/**
 * 
 */
package versus.model;

import java.util.Observable;



/**
 * @author Aires David
 *
 */
public class PlayerModel extends Observable {
	CharacterModel plocalModel;
	CharacterModel enemyModel;
	
	public PlayerModel() {
		plocalModel= new CharacterModel();
		plocalModel.setX(0);
		plocalModel.setY(6);
		
		enemyModel= new CharacterModel();
		enemyModel.setX(14);
		enemyModel.setY(6);
	}
	
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
	
	public int getLX() {
		return plocalModel.getX();
	}
	
	public int getLY() {
		return plocalModel.getY();
	}
	
	public boolean getLMoving() {
		return plocalModel.isMoving();
	}

	
	
	public int getRX() {
		return enemyModel.getX();
	}
	
	public int getRY() {
		return enemyModel.getY();
	}
	
	public boolean getRMoving() {
		return enemyModel.isMoving();
	}
}
