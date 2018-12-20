/**
 * 
 */
package versus.model;

/**
 * @author Aires David
 *
 */
public class CharacterModel {
	private int x= 300;
	private int y= 300;
	private int moving= 1;
	private int life= 0;

	public void mouvement(int x,int y,int moving) {
		this.x=x;
		this.y=y;
		this.moving=moving;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the movingCollision
	 */
	public int isMoving() {
		return moving;
	}
	
	/**
	 * @param movingCollision the movingCollision to set
	 */
	public void setMoving(int moving){
		this.moving= moving;
	}
	
	public int getLife() {
		return this.life;
	}
	
	public void setLife(int life) {
		this.life= life;
	}
	
	public String toString() {
		String str="";
		str+= "\nCoordonnée X: "+(this.x+1);
		str+= "\nCoordonnée Y: "+(this.y+1);
		str+= "\nEtat de déplacement: "+this.moving;
		str+= "\nNombre de Vie: "+this.life;
		return str;
	}
}