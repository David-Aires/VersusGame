/**
 * 
 */
package versus.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import versus.controller.CharacterController;
import versus.model.PlayerModel;



/**
 *@author Aires David, Quentin Lebrun
 *
 */
public class GameViewMap extends GameView  implements Observer {
	private JFrame f;
    private final JPanel gui = new JPanel(new BorderLayout(1, 1));
    private JButton[][] BoardSquares = new JButton[11][11];
    private JPanel Board;
    private final JLabel message = new JLabel(
            "Connexion State");
    ImageIcon playerImage= new ImageIcon("resource/d�monLITTLE.png");
    private int lastX= 0;
    private int lastY= 0;
    ImageIcon icon = new ImageIcon(
            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	

	public GameViewMap(PlayerModel player,CharacterController controller) {
		super(player,controller);
		
		gui.setBorder(new EmptyBorder(1, 1, 1, 1));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        tools.add(new JButton("Reset"));
        
        
        // TODO - add functionality!
        
        
        
        tools.addSeparator();
        tools.add(new JButton("Resign")); 
        // TODO - add functionality!
        
        
        tools.addSeparator();
        tools.add(message);
        
        //To check the network, if the other player is connected
        JButton checkNetwork= new JButton("DOWN");
        tools.add(checkNetwork);
        checkNetwork.setBackground(Color.RED); 
        checkNetwork.setForeground(Color.WHITE);
        checkNetwork.setEnabled(false);
       

        gui.add(new JLabel(""), BorderLayout.LINE_START);

        Board = new JPanel(new GridLayout(0, 12));
        Board.setBorder(new LineBorder(Color.BLACK));
        gui.add(Board);

        //create all the buttons
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int ii = 0; ii < BoardSquares.length; ii++) {
            for (int jj = 0; jj < BoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                
                
                b.setIcon(icon);
                    b.setBackground(Color.WHITE);
                BoardSquares[jj][ii] = b;
            }
        }
        
       update(null,null);

        
        Board.add(new JLabel(""));
        
        for (int ii = 0; ii < 11; ii++) {
        	
            
                Board.add(new JLabel("" + (ii + 1),
                        SwingConstants.CENTER));
           
        }
        
      
        for (int ii = 0; ii < 11; ii++) {
            for (int jj = 0; jj < 11; jj++) {
                switch (jj) {
                    case 0:
                        Board.add(new JLabel("" + (ii + 1),
                                SwingConstants.CENTER));
                    default:
                        Board.add(BoardSquares[jj][ii]);
                }
            }
        }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	    f = new JFrame("Versus");
        f.add(gui);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.pack();
        f.setMaximumSize(f.getSize());
        f.setVisible(true);
	}

	

	@Override
	public void update(Observable o, Object arg) {
		BoardSquares[lastX][lastY].setIcon(icon);
		lastX=player.getLX();
		lastY= player.getLY();
        BoardSquares[lastX][lastY].setIcon(playerImage);
	}





	@Override
	public void affiche(String string) {
		// TODO Auto-generated method stub
		
	}

}