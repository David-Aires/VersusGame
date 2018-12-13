package versus.controller;

import java.io.DataInputStream;



import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import versus.model.PlayerModel;
import versus.view.GameView;
import versus.view.GameViewMap;


/**
 * @author Quentin Lebrun
 *
 */
public class NetworkController  {
	private int sessionNo = 1; // Number a session
	
	PlayerModel player;
	GameView vue;
	GameViewMap vueCarte;
	
	public static int PLAYER1 = 1; // Indicate player 1
	public static int PLAYER2 = 2; // Indicate player 2
	  
	public int p1_won=1;
	public int p2_won=2;

	
	  public void start(Stage primaryStage) {

		  new Thread( () -> {
		      try {
		        // Create a server socket
		        ServerSocket serverSocket = new ServerSocket(6666);
		    
		        // Ready to create a session for every two players
		        while (true) {
		      
		          // Connect to player 1
		          Socket player1 = serverSocket.accept();
		          // Notify that the player is Player 1
		          new DataOutputStream(player1.getOutputStream()).writeInt(PLAYER1);
		  
		          
		          
		          // Connect to player 2
		          Socket player2 = serverSocket.accept();
		          // Notify that the player is Player 2
		          new DataOutputStream(player2.getOutputStream()).writeInt(PLAYER2);
		    
		          
		          
		          // Launch a new thread for this session of two players
		          new Thread(new HandleASession(player1, player2)).start();
		        }
		      }
		      catch(IOException ex) {
		        ex.printStackTrace();
		      }
		    }).start();
		  }
	
	
	
	public NetworkController(PlayerModel player){
		this.player=player;
		
		/*Thread send= new Thread("send");
		 System.out.println("phase thread send");
		 
		Thread receive= new Thread("receive");
		 System.out.println("phase receive send");*/

		
	}
	
	
	
	/*
    private void sendMove(DataOutputStream out, int x, int y)
        throws IOException {
      out.writeInt(x); // Send x index
      out.writeInt(y); // Send y index
    }*/
    
    
    
    
    // Define the thread class for handling a new session for two players
    class HandleASession implements Runnable {
      private Socket player1;
      private Socket player2;
    
      // Create and initialize cells
      private char[][] cell =  new char[11][11];
    
      private DataInputStream fromPlayer1;
      private DataOutputStream toPlayer1;
      private DataInputStream fromPlayer2;
      private DataOutputStream toPlayer2;
    
      // Continue to play
      private boolean continueToPlay = true;
    
     /** Construct a thread */
      public HandleASession(Socket player1, Socket player2) {
        this.player1 = player1;
        this.player2 = player2;
        
       initBoard();
        
      }
      
        // Initialize the board for the network
        private  void initBoard() {
    		 String [][] board= new String[11][11];
    		 
    		for(int j=0;j<board.length;j++) {
    			for(int i=0;i<board.length;i++) {
    				board[j][i]= " ";
    			}
    		}
    		board[player.getLY()][player.getLX()]="§";
    		board[player.getRY()][player.getRX()]="£";
    	
    	}
        
        
      /** Implement the run() method for the thread */
      public void run() {
        try {
          // Create data input and output streams
          DataInputStream fromPlayer1 = new DataInputStream(
            player1.getInputStream());
          DataOutputStream toPlayer1 = new DataOutputStream(
            player1.getOutputStream());
          DataInputStream fromPlayer2 = new DataInputStream(
            player2.getInputStream());
          DataOutputStream toPlayer2 = new DataOutputStream(
            player2.getOutputStream());
    
          // Write anything to notify player 1 to start
          //TODO
    
          // Continuously serve the players and determine and report
          // the game status to the players
          while (true) {
           
        	  // Receive a move from player 1
        	int x=player.getLX();
        	x = fromPlayer1.readInt();
            int y =player.getLY();
            y=fromPlayer1.readInt();
            
            
            cell[x][y] = '§';
    
            // Check if Player 1 wins
            if (isWon(1)) {
              //TODO
   //ajouter une fenêtre qui pop pour dire que le joueur 1 a gagné
            	
              break; // Break the loop
            }
            else {
              // Notify player 2 to take the turn
              //TODO
              // Send player 1's selected x and y to player 2
              sendMove(toPlayer2, x, y);
            }
    
            // Receive a move from Player 2
            x = fromPlayer2.readInt();
            y = fromPlayer2.readInt();
            cell[x][y] = '£';
    
            // Check if Player 2 wins
            if (isWon(2)) {
            	 //TODO
      //ajouter une fenêtre qui pop pour dire que le joueur 2 a gagné

            	vueCarte.update(null,null);
                break; // Break the loop
            }
            else {
              // Notify player 1 to take the turn
            	 //TODO
              // Send player 2's selected x and y to player 1
              sendMove(toPlayer1, x, y);
            }
          }
        }
        catch(IOException ex) {
          ex.printStackTrace();
        }
      }
    
      /** Send the move to other player */
      private void sendMove(DataOutputStream out, int x, int y)
          throws IOException {
    	    out.writeInt(x); // Send x index
    	      out.writeInt(y); // Send y index
      }
   
     
      /** Determine if the player local or network wins */
      private boolean isWon(int currentPlayer) {
        // Check all rows
        if(currentPlayer==1 && player.getLX()==10){
        	
        	return true;
        }
        else if(currentPlayer==2 && player.getRX()==0){
        	
        	return true;
        }
        /** no winner */
        else{
        	return false;	
        }
        
      }
    }

       
}


