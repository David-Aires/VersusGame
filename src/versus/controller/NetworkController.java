package versus.controller;

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
 * @author Quentin Lebrun
 *
 */
public class NetworkController  {
	PlayerModel player;
	GameView vue;
	GameViewMap vueCarte;
	CharacterController controller;
	private InetAddress inet = null;
	private Thread checkStatus = new Thread(new CheckNetwork());
	private Thread receiveMove = new Thread(new receiveMove());
	private boolean isServer;
	ServerSocket socketserver  ;
    Socket socketduserver ;
    Socket socketclient;
    BufferedReader in;
    PrintWriter out;
	
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
				System.out.println("je suis un serveur");
					socketserver = new ServerSocket(2009);
					socketduserver = socketserver.accept();
					in = new BufferedReader (new InputStreamReader (socketduserver.getInputStream()));
					out = new PrintWriter( new BufferedWriter(new OutputStreamWriter(socketduserver.getOutputStream())),true);
			} 
			else {
				System.out.println("je suis un client");
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
	
	public void closeNetwork() throws IOException {
		socketclient.close();
		socketserver.close();
		socketduserver.close();
		in.close();
		out.close();
	}
	
	public class receiveMove implements Runnable{
		@Override
		public void run() {
			int[] position = new int[3];
			String temp1= "";
			while(true) {
				try {
					temp1 = in.readLine();
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(temp1=="fin") {
					try {
						closeNetwork();
					} 
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				else if(temp1 !=""){
					String[] temp = temp1.split("/");
					position[0] =(Integer.parseInt(temp[0]));   // position X of the player
					position[1] =(Integer.parseInt(temp[1]));	// position Y of the player
					position[2] = (Integer.parseInt(temp[2]));	// number of move
					player.mouvementsEnnemy(position[0], position[1], position[2]);
					temp1="";
				}
			}	
		}
	}
		
	// return the good x for the other player in the network
		public int changeLocalXForNetwork(){
			return 10-player.getLX();
		}
	
		
		
		public void sendMove() {
			//si tu passes en local 
			//de 1, 6 à 2, 6 ( x=1,y=5)
			//pour l'autre joueur tu passes de
			//11, 6 à 10, 6
		
				out.println(changeLocalXForNetwork() +"/"+player.getLY()+"/"+player.getLMoving());
				out.flush();
				
			}
			
	// thread pings the network player to check if he is here
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