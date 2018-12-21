/**
 * (?)
 */
package versus.controller;

/**
 * This imported class is used to create object having a graphical representation that can be displayed on the screen and that can interact with the user.
 */
import java.awt.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;



import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import versus.model.PlayerModel;
import versus.view.GameView;
import versus.view.GameViewMap;

/**
 * This class represents the Character Controller.
 * @author Aires David, Quentin Lebrun
 */
public class NetworkController  {
	/**
	 * PlayerModel contains all the methods and variables which
	 * are linked to the player in the game
	 */
	PlayerModel player;
	/**
	 * The GameView is the abstract class 
	 * of GameViewMap and GameViewConsole
	 */
	GameView vue;
	/**
	 * GameViewMap shows the GUI
	 */
	GameViewMap vueCarte;
	/**
	 * CharacterController makes the moves and the actions
	 * in function of what happens in the game 
	 */
	CharacterController controller;
	/**
	 * It will contain the address of the users
	 */
	private InetAddress inet = null;
	/**
	 * This thread will tell if one user is disconnected
	 */
	private Thread checkStatus = new Thread(new CheckNetwork());
	/**
	 *This thread will receive the moves from the other player 
	 */
	private Thread receiveMove = new Thread(new receiveMove());
	/**
	 * it will be used to know if 
	 * the user is the future server or the future client
	 */
	private boolean isServer;
	/**
	 * it's the socket for the server(which is also a client)
	 */
	ServerSocket socketserver  ;
	/**
	 * it's the future accepted socket for the server(which is also a client)
	 */
    Socket socketduserver ;
    /**
	 * it's the socket for the client
	 */
    Socket socketclient;
    /**
	 * to see what data will be received
	 */
    BufferedReader in;
    /**
	 * to send data in the network
	 */
    PrintWriter out;
    /*
     * this method checks the bonus when it's taken by one player
     */
    public void checkBonus() {
    	for(int i=0;i<player.getBonus().get(0).size();i++) {
			if(player.getRX()== player.getBonus().get(0).get(i) && player.getRY()==player.getBonus().get(1).get(i)) {
				player.getBonus().get(0).remove(i);
				player.getBonus().get(1).remove(i);
			}
    	}
    }
    
    /*
     * method to reset the game, it's like one player gave up.
     */
    public void sendResign() {
    	out.println(changeLocalXForNetwork() +"/"+player.getLY()+"/"+player.getLMoving()+"/"+1);
		out.flush();
		player.setHaveLose(true);
    }
    
    /*
     * method to know when the player lose
     * */
    public void isLose() {
    	if(player.getRX()==0) {
    		player.setHaveLose(true);
    	}
    }
    
    /**
	 * This constructor creates a network using the model of the player and 
	 * the boolean isServer (to know if it is the client or the server who wants to connect)
	 * @param player the model of the player
	 * @param isServer the boolean to know if it is the client or the server who wants to connect
	 */
	public NetworkController(PlayerModel player, boolean isServer) {
		this.player=player;
		this.isServer=isServer;
		try {
			inet = InetAddress.getByName(player.getIpAddress());
		} 
		catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		checkStatus.start();
		try {
			if(isServer) {
					socketserver = new ServerSocket(2009);
					socketduserver = socketserver.accept();
					in = new BufferedReader (new InputStreamReader (socketduserver.getInputStream()));
					out = new PrintWriter( new BufferedWriter(new OutputStreamWriter(socketduserver.getOutputStream())),true);
			} 
			else {
				socketclient = new Socket(InetAddress.getLocalHost(),2009);
				 in = new BufferedReader (new InputStreamReader (socketclient.getInputStream()));
				 out = new PrintWriter( new BufferedWriter(new OutputStreamWriter(socketclient.getOutputStream())),true);
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		receiveMove.start();
	}	
	

	
	/**
	 * This thread is used to receive the moves of the opponents.
	 */
	public class receiveMove implements Runnable{
		GameView vue;
		@Override
		public void run() {
			int[] position = new int[4];
			String temp1= "";
			while(true) {
				try {
					temp1 = in.readLine();
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(temp1 !=""){
					String[] temp = temp1.split("/");
					position[0] =(Integer.parseInt(temp[0]));   // position X of the player
					position[1] =(Integer.parseInt(temp[1]));	// position Y of the player
					position[2] = (Integer.parseInt(temp[2]));	// number of move
					position[3] = (Integer.parseInt(temp[3]));  // 0: nothing  1:Win
					if(position[3]==1) {
						player.setHaveWin(true);;
					}
					player.mouvementsEnnemy(position[0], position[1], position[2]);
					isLose();
					checkBonus();
					temp1="";
				}
			}	
		}
	}
		
	/**
	 * This method return the good X
	 *  knowing that both players consider themselves 
	 *  as local players starting in x=0 and y=7
	 * @return the good position for the opponent
	 * 
	 */ 
	public int changeLocalXForNetwork(){
		return 14-player.getLX();
	}
	
	/**
	 * This method send the appropriate X and Y coordinates to the other player.
	 */ 
	public void sendMove() {
		//for example
		//if you move in local 
				//from 1, 6 to 2, 6 ( x=1,y=5)
				//For the other player you go from
				//11, 6 to 10, 6
		out.println(changeLocalXForNetwork() +"/"+player.getLY()+"/"+player.getLMoving()+"/"+0);
		out.flush();	
	}
			
	/**
	 * This thread checks if the 2 players are well connected
	 */ 
	private class CheckNetwork implements Runnable {
		@Override
		public void run() {
			while(true) {
				try {
					if (inet.isReachable(5000)){
						player.setIsConnected(true);
					}
					else {
				        player.setIsConnected(false);
				    }
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(1000);
				} 
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}