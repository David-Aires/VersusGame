package main;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainWindow extends BasicGameState implements CONSTANTS {
	  public static final int ID = 1;
	  private StateBasedGame game;
	  private Image backgroundMap;
	  private Music backgroudMusic;
	  private int playersChoice = 0;
	  private GameContainer container;
	    private static final int NOCHOICES = 3;
	    private static final int START = 0;
	    private static final int INFO = 1;
	    private static final int QUIT = 2;
	    private String[] playersOptions = new String[NOCHOICES];
	    private boolean exit = false;
	    private Font font;
	    private TrueTypeFont playersOptionsTTF, foo;
	    private Color notChosen = new Color(153, 204, 255);

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.container = container;
		ModelWindow menu = new ModelWindow(menuImage,musicMenu);
		this.game = game;
		this.backgroundMap = new Image(menu.getTiledMap());
		this.backgroudMusic = new Music(menu.getMusic());
		font = new Font("Verdana", Font.BOLD, 40);
        playersOptionsTTF = new TrueTypeFont(font, true);
        font = new Font ("Verdana", Font.PLAIN, 20);
        foo = new TrueTypeFont(font, true);
        playersOptions[0] = "Start";
        playersOptions[1] = "Info";
        playersOptions[2] = "Quit";
        backgroudMusic.loop();
       
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		backgroundMap.draw(0, 0, container.getWidth(), container.getHeight());
		 renderPlayersOptions();
	        if (exit) {
	            container.exit();
	}
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		container.setFullscreen(fullscreen);
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            if (playersChoice == (NOCHOICES - 1)) {
                playersChoice = 0;
            } else {
                playersChoice++;
            }
        }
        if (input.isKeyPressed(Input.KEY_UP)) {
            if (playersChoice == 0) {
                playersChoice = NOCHOICES - 1;
            } else {
                playersChoice--;
            }
        }
        if (input.isKeyPressed(Input.KEY_ENTER)) {
            switch (playersChoice) {
                case QUIT:
                    exit = true;
                    break;
                
                case START:
                	backgroudMusic.stop();
                	 game.enterState(MainMap.ID);
                	 break;
            }
}
		
	}
	
	private void renderPlayersOptions() {
        for (int i = 0; i < NOCHOICES; i++) {
            if (playersChoice == i) {
                playersOptionsTTF.drawString(container.getWidth()/2f - 5/2f,
                        i*50+(container.getHeight()/2f - 5/2f), playersOptions[i]);
            } else {
                playersOptionsTTF.drawString(container.getWidth()/2f - 5/2f,
                       i*50+(container.getHeight()/2f - 5/2f), playersOptions[i], notChosen);
            }
        }
    }

	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
}
