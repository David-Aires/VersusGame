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
 * @author Quentin Lebrun
 */
public class NetworkController  {
	/**
	 * (?)
	 */
	PlayerModel player;
	/**
	 * (?)
	 */
	GameView vue;
	/**
	 * (?)
	 */
	GameViewMap vueCarte;
	/**
	 * (?)
	 */
	CharacterController controller;
	/**
	 * (?)
	 */
	private InetAddress inet = null;
	/**
	 * (?)
	 */
	private Thread checkStatus = new Thread(new CheckNetwork());
	/**
	 * (?)
	 */
	private Thread receiveMove = new Thread(new receiveMove());
	/**
	 * (?)
	 */
	private boolean isServer;
	/**
	 * (?)
	 */
	ServerSocket socketserver  ;
	/**
	 * (?)
	 */
    Socket socketduserver ;
    /**
	 * (?)
	 */
    Socket socketclient;
    /**
	 * (?)
	 */
    BufferedReader in;
    /**
	 * (?)
	 */
    PrintWriter out;
    
    public void checkBonus() {
    	for(int i=0;i<player.getBonus().get(0).size();i++) {
			if(player.getRX()== player.getBonus().get(0).get(i) && player.getRY()==player.getBonus().get(1).get(i)) {
				player.getBonus().get(0).remove(i);
				player.getBonus().get(1).remove(i);
			}
    	}
    }
    
    public void sendResign() {
    	out.println(changeLocalXForNetwork() +"/"+player.getLY()+"/"+player.getLMoving()+"/"+1);
		out.flush();
		player.setHaveLose(true);
    }
    
    public void isLose() {
    	if(player.getRX()==0) {
    		player.setHaveLose(true);
    	}
    }
    
    /**
	 * This method (?).
	 * @param player (?)
	 * @param isServer (?)
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
	 * This method (?).
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
	 * This method return the good X coordinates for the other player in the network.
	 * @return 14-player.getLX()
	 */ 
	public int changeLocalXForNetwork(){
		return 14-player.getLX();
	}
	
	/**
	 * This method send the appropriate X and Y coordinates to the other player.
	 */ 
	public void sendMove() {
		//si tu passes en local 
		//de 1, 6 à 2, 6 ( x=1,y=5)
		//pour l'autre joueur tu passes de
		//11, 6 à 10, 6
		out.println(changeLocalXForNetwork() +"/"+player.getLY()+"/"+player.getLMoving()+"/"+0);
		out.flush();	
	}
			
	/**
	 * This method send pings to the network player to check if the link is up.
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