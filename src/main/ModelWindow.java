/**
 * 
 */
package main;

//Suite à un problème technique, nous avons dû refaire un repository
//https://github.com/David-Aires/VersusGame.git

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * @author Aires David
 * 
 */

public class ModelWindow {


	
	
	/**
	 * The TiledMap
	 */
	private String tiledMap;
	/**
	 * The Music
	 */
	private String music;
	/**
	 * the width of the screen
	 */
	private  int screenWidth;
	/**
	 * the height of the screen
	 */
	private  int screenHeight = 0;
	/**
	 * The dimension of the screen
	 */
	private Dimension dimScreen;
	/**
	 * Tools of toolkit library
	 */
	private Toolkit tk;
	
	
	/**
	 * This constructor create a map using the tiledmap and the music
	 * @param tiledmap
	 * @param music
	 */
	public ModelWindow(String tiledmap, String music ) {
		this.tiledMap = tiledmap;
		this.music = music;
	}
	

	public ModelWindow() {
		getscreenSize();
	}


	/**
	 * @return the tiledmap
	 */
	public String getTiledMap() {
		return tiledMap;
	}

	/**
	 * @return the music
	 */
	public String getMusic() {
		return music;
	}
	
	/**
	 * @return the width and the height of the system screen
	 */
	public void getscreenSize() {
		tk = Toolkit.getDefaultToolkit();
		dimScreen = tk.getScreenSize();
		this.screenWidth = dimScreen.width;
		this.screenHeight = dimScreen.height;
		
		
	}

	/**
	 * @return the screenWidth
	 */
	public int getScreenWidth() {
		return screenWidth;
	}

	/**
	 * @return the screenHeight
	 */
	public int getScreenHeight() {
		return screenHeight;
	}

	/**
	 * @return the dimScreen
	 */
	public Dimension getDimScreen() {
		return dimScreen;
	}
	
	

		
		
		
		
		
		
		

		

	}


