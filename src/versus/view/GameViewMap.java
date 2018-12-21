/**
 * (?).
 */
package versus.view;

/**
 * This imported class is used to lays out a container, arranging and resizing its components to fit in five regions: north, south, east, west, and center.
 */
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import javafx.scene.control.ToolBar;
import versus.controller.CharacterController;
import versus.controller.NetworkController;
import versus.model.PlayerModel;

/**
 * This class represents the Game View Map.
 *@author Aires David, Quentin Lebrun
 */
public class GameViewMap extends GameView  implements ActionListener, MouseListener {
	/**
	 * (?)
	 */
	NetworkController networkController;	
	/**
	 * (?)
	 */
	private JFrame f;
	/**
	 * (?)
	 */
    private final JPanel gui = new JPanel(new BorderLayout(1, 1));
    /**
	 * (?)
	 */
    private JButton[][] BoardSquares = new JButton[15][15];
    /**
	 * (?)
	 */
    private JPanel Board;
    /**
	 * (?)
	 */
    private JButton checkNetwork = new JButton();
    /**
	 * (?)
	 */
    private JButton life = new JButton();
    /**
	 * (?)
	 */
    private JLabel lifeLabel = new JLabel(": "+Integer.toString(player.getLlife()));
    /**
	 * (?)
	 */
    private JButton moveB = new JButton();
    /**
	 * (?)
	 */
    private JLabel moveLabel = new JLabel(": "+Integer.toString(player.getLMoving()));
    /**
	 * (?)
	 */
    private final JLabel message = new JLabel("Connexion State");
    /**
	 * (?)
	 */
    JToolBar tools= new JToolBar();
    /**
	 * (?)
	 */
    private JButton reset;
    /**
	 * (?)
	 */
    private Thread MessageBox;
    /**
	 * (?)
	 */
    private String Message;
    /**
	 * (?)
	 */
    private int lastLocalX= 0;
    /**
	 * (?)
	 */
    private int lastLocalY= 0;
    /**
	 * (?)
	 */
    private int lastNetworkX= 0;
    /**
	 * (?)
	 */
    private int lastNetworkY= 0;
    /**
	 * (?)
	 */
    JOptionPane jop = new JOptionPane();
    /**
	 * (?)
	 */
    ImageIcon playerLocalImage= new ImageIcon("resource/Player1.png");
    /**
	 * (?)
	 */
    ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
    /**
	 * (?)
	 */
    ImageIcon playerNetworkImage= new ImageIcon("resource/Player2.png");
    /**
	 * (?)
	 */
    ImageIcon bonusImage= new ImageIcon("resource/Bonus.png");
    
    /**
	 * This method update the number of life.
	 */
    public void updateLife() {
        lifeLabel.setText(": "+Integer.toString(player.getLlife()));
	}
	
    /**
	 * This method update the number of move.
	 */
	public void updateMove() {
		moveLabel.setText(": "+Integer.toString(player.getLMoving()));
	}
	
	/**
	 * This method (?).
	 * @param e (?)
	 */
    public void actionPerformed(ActionEvent e){
    	if(e.getSource()== reset){
    		f.dispose();
    		controller.mouvementLocal(0, 7,1);
    		new GameViewMap(player,controller,networkController);
    	}
    }
    /**
	 * This constructor creates the Game view Map.
	 * @param player (?)
	 * @param controller (?)
	 * @param networkController (?)
	 */
	public GameViewMap(PlayerModel player,CharacterController controller, NetworkController networkController) {
		super(player,controller,networkController);
		gui.setBorder(new EmptyBorder(1, 1, 1, 1));
		tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        reset = new JButton("Reset");
        tools.add(reset);
        tools.add(new JButton("Resign"));
        checkNetwork.setText("");
        checkNetwork.setForeground(Color.WHITE);
        checkNetwork.setBorderPainted(false);
        reset.addActionListener(this);
        tools.addSeparator();
        tools.addSeparator();
        tools.add(message);
        tools.add(checkNetwork);
        checkNetwork.setEnabled(false);
        tools.add(new JLabel("                                                "));
        life.setBorderPainted(false);
        life.setOpaque( false );
        life.setContentAreaFilled(false);
        life.setEnabled(false);
        life.setIcon(new ImageIcon("resource/Life.png"));
        tools.add(life);
        tools.add(lifeLabel);
        tools.add(new JLabel("         "));
        moveB.setBorderPainted(false);
        moveB.setOpaque( false );
        moveB.setContentAreaFilled(false);
        moveB.setEnabled(false);
        moveB.setIcon(new ImageIcon("resource/move.png"));
        tools.add(moveB);
        tools.add(moveLabel);
        gui.add(new JLabel(""), BorderLayout.LINE_START);
        Board = new JPanel(new GridLayout(0, 16));
        Board.setBorder(new LineBorder(Color.BLACK));
        gui.add(Board);
        //create all the buttons
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int ii = 0; ii < BoardSquares.length; ii++) {
            for (int jj = 0; jj < BoardSquares[ii].length; jj++) {
            	BoardSquares[jj][ii] = new JButton();
            	BoardSquares[jj][ii].setMargin(buttonMargin);
            	BoardSquares[jj][ii].setName(jj+"/"+ii);
            	for(int i=0;i<player.getBonus().get(0).size();i++) {
        			if(jj== player.getBonus().get(0).get(i) && ii==player.getBonus().get(1).get(i)) {
        				BoardSquares[jj][ii].setIcon(bonusImage);
        			} 
            	}
            	BoardSquares[jj][ii].addMouseListener(this);
            	if(jj==0 || jj==14) {
            		BoardSquares[jj][ii].setBackground(Color.RED);
            	} else {
            		BoardSquares[jj][ii].setBackground(Color.WHITE);
            	}
            }
        }
        update(null,null);
        Board.add(new JLabel(""));
        for (int ii = 0; ii < BoardSquares[0].length; ii++) {
            Board.add(new JLabel("" + (ii + 1),
            SwingConstants.CENTER));
        }
        for (int ii = 0; ii < BoardSquares[0].length; ii++) {
        	for (int jj = 0; jj < BoardSquares[0].length; jj++) {
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

	/**
	 * This method (?).
	 * @param o (?)
	 * @param arg (?)
	 */
	@Override
	public void update(Observable o, Object arg) {
		//display the local player icon
		BoardSquares[lastLocalX][lastLocalY].setIcon(icon);
		lastLocalX=player.getLX();
		lastLocalY= player.getLY();
    	BoardSquares[lastLocalX][lastLocalY].setIcon(playerLocalImage);
		//display the network player icon
        BoardSquares[lastNetworkX][lastNetworkY].setIcon(icon);
		lastNetworkX=player.getRX();
		lastNetworkY= player.getRY();
        BoardSquares[lastNetworkX][lastNetworkY].setIcon(playerNetworkImage);
        //update button status connection
        if(player.getIsConnected()) {
        	 checkNetwork.setText("UP");
             checkNetwork.setBackground(Color.GREEN);      
        } 
        else{
        	checkNetwork.setText("DOWN");
            checkNetwork.setBackground(Color.RED); 
        }
        updateLife();
        updateMove();
	}

	/**
	 * This method (?).
	 * @param string (?)
	 */
	@Override
	public void affiche(String string) {
		Message = string;
		MessageBox = new Thread(new MessageBox());
		MessageBox.start();
	}

	/**
	 * This method (?).
	 * @param e (?)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		//get the X and Y position of the button with the name
		String[] temp = (((Component) e.getSource()).getName()).split("/");
		int tempX =Integer.parseInt(temp[0]);
		int tempY =Integer.parseInt(temp[1]);
		//check if the X move is +1 or -1 if it's move the local player to X click position
		if((tempX==(player.getLX()+1) || (tempX==player.getLX()-1)) && tempY==player.getLY() ) {
			controller.mouvementLocal(tempX, player.getLY(),1);
		}
		//check if the Y move is +1 or -1 if it's move the local player to Y click position
		if((tempY==(player.getLY()+1) || (tempY==player.getLY()-1)) && tempX==player.getLX() ) {
			controller.mouvementLocal(player.getLX(),tempY,1);
		}
	}
	
	/**
	 * This method (?).
	 * @param e (?)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * This method (?).
	 * @param e (?)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * This method (?).
	 * @param e (?)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub	
	}

	/**
	 * This method (?).
	 * @param e (?)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}	
	
	/**
	 * This (?).
	 */
	private class MessageBox implements Runnable{
		@Override
		public void run() {
			JOptionPane.showMessageDialog(null, Message);
		}
	}
}