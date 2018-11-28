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
	CharacterModel playerModel;
	
	public PlayerModel() {
		playerModel= new CharacterModel();
		playerModel.setX(300);
		playerModel.setY(0);
	}
	
	public void mouvements(int x,int y,boolean moving) {
		if(x!=playerModel.getX() || y!=playerModel.getY()) {
			playerModel.mouvement(x, y,moving);
			setChanged();
			notifyObservers();
		}
		
		
	
	}
	public String toString() {
		return playerModel.toString();
		
	}
	
	public int getX() {
		return playerModel.getX();
	}
	
	public int getY() {
		return playerModel.getY();
	}
	
	public boolean getMoving() {
		return playerModel.isMoving();
	}

}
