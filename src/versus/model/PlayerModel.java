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
	String ipAddress = "127.0.0.1";
	boolean isConnected = false;
	
	
	public PlayerModel() {
		plocalModel= new CharacterModel();
		plocalModel.setX(0);
		plocalModel.setY(5);
		
		enemyModel= new CharacterModel();
		enemyModel.setX(10);
		enemyModel.setY(5);
	}
	
	// change the position in x,y and the boolean moving of the local player
	public void mouvementsLocal(int x,int y,boolean moving) {
		if(x!=plocalModel.getX() || y!=plocalModel.getY()) {
			plocalModel.mouvement(x, y,moving);
			
			setChanged();
			notifyObservers();
		}
	}
		// change the position in x,y and the boolean moving of the enemy
	public void mouvementsEnnemy(int x,int y,boolean moving) {
		if(x!=enemyModel.getX() || y!=enemyModel.getY()) {
			enemyModel.mouvement(x, y,moving);
			
			setLMoving(true);
			
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

	public void setLMoving(boolean moving){
		this.plocalModel.setMoving(moving);
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
	
	public void setRMoving(boolean moving){
		this.enemyModel.setMoving(moving);
	}
	
	public String getIpAddress() {
		return this.ipAddress;
	}
	
	public boolean getIsConnected() {
		return this.isConnected;
	}
	
	public void setIsConnected(boolean isConnected) {
		if(this.isConnected != isConnected) {
			this.isConnected= isConnected;
			setChanged();
			notifyObservers();
		}
		
	}
}