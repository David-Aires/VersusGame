/**
 * (?).
 */
package versus.model;

/**
 * This imported class is used to implements all optional list operations, and permits all elements.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.List;

/**
 * This class represents the Player Model.
 * @author Aires David, Quentin Lebrun
 */
public class PlayerModel extends Observable {
	/**
	 * (?)
	 */
	CharacterModel plocalModel;
	/**
	 * (?)
	 */
	CharacterModel enemyModel;
	/**
	 * (?)
	 */
	String ipAddress = "127.0.0.1";
	/**
	 * (?)
	 */
	boolean isConnected = false;

	//for example 9,6 IS EQUAL TO 10,7 in X,Y  
	//the part[0][x] is for the position in x of the traps
	//the part[1][y] is for the position in y of the traps
	//yellow
	/**
	 * (?)
	 */
	private int [][]trap={{0,0,1,2,6,8,9,9,10},{0,14,9,2,14,3,6,10,5}};
	//purple
	/**
	 * (?)
	 */
	private int [][] backInX={{2,2,3,3,4,4,6,6,8,9,9,11,12,12,13,13,13,13,13},{5,10,3,7,13,11,3,6,1,14,7,12,3,8,11,13,14,0,4}};
	//magenta
	/**
	 * (?)
	 */
	private int [][] backInY={{0,1,2,3,4,4,5,6,6,6,8,8,13},{6,6,12,0,5,10,8,2,5,11,7,11,7}};
	
	//ArrayList with the position of a bonus on the board
	/**
	 * (?)
	 */
	List<ArrayList<Integer>> bonus = new ArrayList<>();
	/**
	 * (?)
	 */
	ArrayList<Integer> bonusX = new ArrayList<Integer>(Arrays.asList(1,5,2));
	/**
	 * (?)
	 */
	ArrayList<Integer> bonusY = new ArrayList<Integer>(Arrays.asList(2,10,9));
	boolean haveWin= false;
	boolean haveLose= false;
	int count=1;
	
	public void init() {
		
		//init position of the local player
		plocalModel.setX(0);
		plocalModel.setY(7);
		
		//init position of the network player
		enemyModel.setX(14);
		enemyModel.setY(7);
		
		//ArrayList with the position of a bonus on the board
		bonus = new ArrayList<>();
		ArrayList<Integer> bonusX = new ArrayList<Integer>(Arrays.asList(1,5,2));
		ArrayList<Integer> bonusY = new ArrayList<Integer>(Arrays.asList(2,10,9));
		
		//init arrayList of bonus
		this.bonus.add(bonusX);
		this.bonus.add(bonusY);
		
		
		
		
		
		//force update viewConsole if the player restart the game
		if(count==0) {
			this.haveLose= false;
			this.haveWin= false;
			plocalModel.setLife(0);
			count++;
		}
		count--;
	}
	
	public void updateAll() {
		setChanged();
		notifyObservers();
	}
	
	/**
	 * This constructor creates the Player Model.
	 */
	public PlayerModel() {
		// init the local player and the position
		plocalModel= new CharacterModel();
		
		//init the network player and the position
		enemyModel= new CharacterModel();
		
		
		init();
		
	}
	
	/**
	 * This method (?).
	 * @param x The X coordinate
	 * @param y The Y coordinate
	 * @param moving (?)
	 */
	public void mouvementsLocal(int x,int y,int moving) {
		if(x!=plocalModel.getX() || y!=plocalModel.getY()) {
			plocalModel.mouvement(x, y,moving);
			updateAll();
		}
	}

	// change the position in x,y and the boolean moving of the enemy
	/**
	 * This method change the X/Y coordinates and the boolean moving of the enemy.
	 * @param x The X coordinate
	 * @param y The Y coordinate
	 * @param Rmoving The boolean moving of the enemy
	 */
	public void mouvementsEnnemy(int x,int y,int Rmoving) {
		if(x!=enemyModel.getX() || y!=enemyModel.getY()) {
			enemyModel.mouvement(x, y,Rmoving);
			if(Rmoving==0) {
				plocalModel.setMoving(1);
			}
			updateAll();
		}
	}
	
	/**
	 * This method gives a textual representation of (?).
	 * @return str The string of (?)
	 */
	public String toString() {
		String str="Joueur local";
		str+=plocalModel.toString();
		str+="\n\n\nJoueur réseau";
		str+=enemyModel.toString();
		return str;
	}
	
	/**
	 * This getter allow the user to get the X coordinate of the player local Model.
	 * @return plocalModel.getX() The X coordinate
	 */
	public int getLX() {
		return plocalModel.getX();
	}
	
	/**
	 * This getter allow the user to get the Y coordinate of the player local Model.
	 * @return plocalModel.getY() The Y coordinate
	 */
	public int getLY() {
		return plocalModel.getY();
	}
	
	/**
	 * This getter allow the user to get the state of moving of the player local Model.
	 * @return plocalModel.isMoving() The state of moving
	 */
	public int getLMoving() {
		return plocalModel.isMoving();
	}

	/**
	 * This setter allow the user to set the state of moving of the player local Model.
	 * @param moving The state of moving
	 */
	public void setLMoving(int moving){
		this.plocalModel.setMoving(moving);
		updateAll();
	}
	
	/**
	 * This getter allow the user to get the X coordinate of the enemy Model.
	 * @return enemyModel.getX() The X coordinate
	 */
	public int getRX() {
		return enemyModel.getX();
	}
	
	/**
	 * This getter allow the user to get the Y coordinate of the enemy Model.
	 * @return enemyModel.getY() The Y coordinate
	 */
	public int getRY() {
		return enemyModel.getY();
	}
	
	/**
	 * This getter allow the user to get the state of moving of the enemy Model.
	 * @return enemyModel.isMoving() The state of moving
	 */
	public int getRMoving() {
		return enemyModel.isMoving();
	}
	
	/**
	 * This setter allow the user to set the state of moving of the enemy Model.
	 * @param moving The state of moving
	 */
	public void setRMoving(int moving){
		this.enemyModel.setMoving(moving);
	}
	
	/**
	 * This getter allow the user to get the IP address of (?).
	 * @return this.ipAddress The IP address of (?)
	 */
	public String getIpAddress() {
		return this.ipAddress;
	}
	
	/**
	 * This getter allow the user to get the state of the connection.
	 * @return this.isConnected The state of the connection
	 */
	public boolean getIsConnected() {
		return this.isConnected;
	}
	
	/**
	 * This setter allow the user to set the state of the connection.
	 * @param isConnected The state of the connection
	 */
	public void setIsConnected(boolean isConnected) {
		if(this.isConnected != isConnected) {
			this.isConnected= isConnected;
			updateAll();
		}
	}
	
	/**
	 * This getter allow the user to get the trap.
	 * @return this.trap The trap
	 */
	public int[][] getTrap(){
		return this.trap;
	}
	
	/**
	 * This getter allow the user to get the (?).
	 * @return this.backInX The (?)
	 */
	public int[][] getBackX(){
		return this.backInX;
	}
	
	/**
	 * This getter allow the user to get the (?).
	 * @return this.backInY The (?)
	 */
	public int[][] getBackY(){
		return this.backInY;
	}
	
	/**
	 * This getter allow the user to get the Bonus.
	 * @return this.bonus The Bonus
	 */
	public List<ArrayList<Integer>> getBonus(){ 
		return this.bonus;
	}
	
	/**
	 * This getter allow the user to get the (?) Extra-life.
	 * @return plocalModel.getLife() The (?) Bonus
	 */
	public int getLlife() {
		return plocalModel.getLife();
	}
	
	/**
	 * This getter allow the user to get the (?) Extra-life.
	 * @return plocalModel.getLife() The (?) Bonus
	 */
	public int getRlife() {
		return enemyModel.getLife();
	}
	
	/**
	 * This setter allow the user to set the (?) Extra-life.
	 * @return life The (?) Bonus
	 */
	public void setLlife(int life) {
		plocalModel.setLife(life);
		updateAll();
	}
	
	public void setHaveWin(boolean haveWin) {
		this.haveWin= haveWin;
		updateAll();
	}
	
	public boolean getHaveWin() {
		return this.haveWin;
	}
	
	public boolean getHaveLose() {
		return this.haveLose;
	}
	
	public void setHaveLose(boolean haveLose) {
		this.haveLose=haveLose;
		updateAll();
	}
}