/**
 * (?).
 */
package versus.model;

/**
 * This class represents the Character Model.
 * @author Aires David
 */
public class CharacterModel {
	/**
	 * The character X coordinate
	 */
	private int x= 300;
	/**
	 * The character Y coordinate
	 */
	private int y= 300;
	/**
	 * The character moving ability
	 */
	private int moving= 1;
	/**
	 * The character extra-life
	 */
	private int life= 0;

	/**
	 * This method (?).
	 * @param x (?)
	 * @param y (?)
	 * @param moving (?)
	 */
	public void mouvement(int x,int y,int moving) {
		this.x=x;
		this.y=y;
		this.moving=moving;
	}
	
	
	
	
	/**
	 * This getter allow the user to get the X coordinate.
	 * @return x the X coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * This setter allow the user to set the X coordinate.
	 * @param x The X coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * This getter allow the user to get the Y coordinate.
	 * @param y The Y coordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * This setter allow the user to set the Y coordinate.
	 * @param y The Y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * This method return the "moving" .
	 * @return moving 
	 */
	public int getMoving() {
		return moving;
	}
	
	/**
	 * This setter allow the user to set the movingCollision.
	 * @param moving 
	 */
	public void setMoving(int moving){
		this.moving= moving;
	}
	
	/**
	 * This getter allow the user to get the number of Extra-life of the player.
	 * @return life The number of Extra-life of the player
	 */
	public int getLife() {
		return this.life;
	}
	
	/**
	 * This setter allow the user to set the number of Extra-life of the player.
	 * @param life The number of Extra-life of the player
	 */
	public void setLife(int life) {
		this.life= life;
	}
	
	/**
	 * This method gives a textual representation of the state of the player.
	 * @return str The string of the state of the player
	 */
	public String toString() {
		String str="";
		str+= "\nCoordonnée X: "+(this.x+1);
		str+= "\nCoordonnée Y: "+(this.y+1);
		str+= "\nEtat de déplacement: "+this.moving;
		return str;
	}
}