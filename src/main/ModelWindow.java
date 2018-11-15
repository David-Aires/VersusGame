/**
 * 
 */
package main;



import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.Music;
import org.newdawn.slick.tiled.TiledMap;

/**
 * @author Aires David
 *
 */

public class ModelWindow {


	
		
		private TiledMap tiledMap;
		private Music music;
		private int screenWidth;
		private int screenHeight;
		private Dimension dimScreen;
		private Toolkit tk;
		
		
		public ModelWindow(TiledMap tiledMap, Music music ) {
			this.tiledMap = tiledMap;
			this.music = music;
		}

		public TiledMap getTiledMap() {
			return tiledMap;
		}

		
		public Music getMusic() {
			return music;
		}

		public int[] getscreenSize() {
			tk = Toolkit.getDefaultToolkit();
			dimScreen = tk.getScreenSize();
			screenWidth = dimScreen.width;
			screenHeight = dimScreen.height;
			int[] sizeScreen = {screenWidth,screenHeight};
			return sizeScreen;
			
		}
		
		public int getscreenWidth() {
			return this.screenWidth;
		}
		
		
		
		
		

		

	}


