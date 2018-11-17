/**
 * 
 */
package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 * @author Aires David
 *
 */
public class MainMap extends BasicGameState implements CONSTANTS {
	private TiledMap tiledmap;
	private GameContainer container;
	public static final int ID = 2;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.container = container;
		ModelWindow jeu = new ModelWindow(map,music);
		this.tiledmap = new TiledMap(jeu.getTiledMap());
		
	}

	

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		this.tiledmap.render(0,0);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			this.container.exit();
		}
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
