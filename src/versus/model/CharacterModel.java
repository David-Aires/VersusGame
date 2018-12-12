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
	private boolean moving= true;

	
	
	
	
	
	
	public void mouvement(int x,int y,boolean moving) {
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
	public boolean isMoving() {
		return moving;
	}
	/**
	 * @param movingCollision the movingCollision to set
	 */
	public void setMovingCollision(boolean movingCollision) {
		this.moving = movingCollision;
	}

	public void setMoving(boolean moving){
		this.moving= moving;
	}
	
	
	public String toString() {
		String str="";
		str+= "\nCoordonnée X: "+(this.x+1);
		str+= "\nCoordonnée Y: "+(this.y+1);
		str+= "\nEtat de déplacement: "+this.moving;
		return str;
	}
	
	
	
}
