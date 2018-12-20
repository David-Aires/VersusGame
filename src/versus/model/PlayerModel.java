/**
 * 
 */
package versus.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.List;

/**
 * @author Aires David, Quentin Lebrun
 *
 */
public class PlayerModel extends Observable {
	CharacterModel plocalModel;
	CharacterModel enemyModel;
	String ipAddress = "127.0.0.1";
	boolean isConnected = false;
	
	
	
	//for example 9,6 IS EQUAL TO 10,7 in X,Y  
	//the part[0][x] is for the position in x of the traps
	//the part[1][y] is for the position in y of the traps
	private int [][]trap={{9,10,8,2},{6,5,3,2}};
	private int [][] backInX={{3,3},{3,7}};
	private int [][] backInY={{5,9},{8,7}};
	
	
	//ArrayList with the position of a bonus on the board
	List<ArrayList<Integer>> bonus = new ArrayList<>();
	ArrayList<Integer> bonusX = new ArrayList<Integer>(Arrays.asList(1,5,2));
	ArrayList<Integer> bonusY = new ArrayList<Integer>(Arrays.asList(2,10,9));
	
	
	
	
	
	public PlayerModel() {
		// init the local player and the position
		plocalModel= new CharacterModel();
		plocalModel.setX(0);
		plocalModel.setY(5);
		
		//init the network player and the position
		enemyModel= new CharacterModel();
		enemyModel.setX(10);
		enemyModel.setY(5);
		
		//init arrayList of bonus
		this.bonus.add(bonusX);
		this.bonus.add(bonusY);
		
	}
	
	// change the position in x,y and the boolean moving of the local player
	public void mouvementsLocal(int x,int y,int moving) {
		if(x!=plocalModel.getX() || y!=plocalModel.getY()) {
			plocalModel.mouvement(x, y,moving);
			setChanged();
			notifyObservers();
		}
	}
		
	// change the position in x,y and the boolean moving of the enemy
	public void mouvementsEnnemy(int x,int y,int Rmoving) {
		if(x!=enemyModel.getX() || y!=enemyModel.getY()) {
			enemyModel.mouvement(x, y,Rmoving);
			if(Rmoving==0) {
				plocalModel.setMoving(1);
			}
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
	public int getLMoving() {
		return plocalModel.isMoving();
	}

	public void setLMoving(int moving){
		this.plocalModel.setMoving(moving);
		setChanged();
		notifyObservers();
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
	public int getRMoving() {
		return enemyModel.isMoving();
	}
	
	public void setRMoving(int moving){
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
	
	public int[][] getTrap(){
		return this.trap;
	}
	
	public int[][] getBackX(){
		return this.backInX;
	}
	
	public int[][] getBackY(){
		return this.backInY;
	}
	
	public List<ArrayList<Integer>> getBonus(){ 
		return this.bonus;
	}
	
	public int getLlife() {
		return plocalModel.getLife();
	}
	
	public int getRlife() {
		return enemyModel.getLife();
	}
	
	public void setLlife(int life) {
		plocalModel.setLife(life);
		setChanged();
		notifyObservers();
	}
}