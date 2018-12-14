/**
 * 
 */
package versus.controller;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import versus.model.PlayerModel;
import versus.view.GameView;
import versus.view.GameViewMap;

/**
 * @author Aires David
 *
 */
public class CharacterController {
	PlayerModel player;
	GameView vue;
	GameViewMap vueGUI;
	NetworkController controllerNetwork;
	
	//the number for the local player, see lines 147 to 187
	public int playerLocal=1;
	//the number for the network player, see lines 147 to 187
	public int playerNetwork=2;

	// Indicate selected row and column by the current move
	  private int xSelected;
	  private int ySelected;
	
	// Continue to play?
	  private boolean continueToPlay = true;
	  
	// for example 9,6 IS EQUAL TO 10,7 in X,Y  
		//the part[0][x] is for the position in x of the traps
		//the part[1][y] is for the position in y of the traps
		private int [][]trap={{9,10,8,2},{6,5,3,2}};
		
		private int [][] backInX={{2,3,3},{5,3,7}};
		
		private int [][] backInY={{8,5,9},{7,8,7}};
	  
	  public void goBackX(){
			for(int i=0;i<2;i++) {
				if(player.getLX()==backInX[0][i] && player.getLY()==backInX[1][i]){
					mouvementLocal(player.getLX()-1,player.getLY());
					System.out.println("Un changement de direction en X a été activé(!Retenez son emplacement!)");

				}
			}
		}
		// when the player goes on it he
	  public void goBackY(){
			for(int i=0;i<2;i++) {
				if(player.getLX()==backInY[0][i] && player.getLY()==backInY[1][i]){
					mouvementLocal(player.getLX(),player.getLY()+1);
					System.out.println("Un changement de direction en Y a été activé(!Retenez son emplacement!)");

				}
			}
		}
		//when the player is trapped he goes to his "camp"
	  public void isTrapped(){
			
				for(int i=0;i<2;i++) {
					if(player.getLX()==trap[0][i] && player.getLY()==trap[1][i]){
						mouvementLocal(0,5);
						System.out.println("Vous êtes tombés dans un piège(!Retenez son emplacement!)");

					}
				}
		}
	  
	  
	  
	  
	  
	  
	  
	public CharacterController(PlayerModel player) {
		this.player=player;
	}
	
	public void addview(GameView vue) {
		this.vue=vue;
		
	}
	
	public String mouvementLocal(int x,int y) {
		
		//if(player.getLMoving()) {
			int xChosen = x;
			int yChosen=y;
			player.mouvementsLocal(x, y, false);
			return "Commande effectuée";
		//} else {
		//	return "Ce n'est pas votre tour";
		//}
	}
	
	
	
	
	
	
	
	
	
/*     				PARTIE CLIENT A DEPLACE          */ 	
	
	
	
	
	
	// Input and output streams from/to server
	  private DataInputStream fromServer;
	  private DataOutputStream toServer;
	
	// Wait for the player to move
	  private boolean waiting = true;

	  // Host name or ip
	  private String host = "localhost";
	  
	  
	  public void start(Stage primaryStage) {
		   

		      

		    // Connect to the server
		    connectToServer();
		  }
	  
	  
	  
	  private void connectToServer() {
		    try {
		      // Create a socket to connect to the server
		      Socket socket = new Socket(host, 6666);

		      // Create an input stream to receive data from the server
		      fromServer = new DataInputStream(socket.getInputStream());

		      // Create an output stream to send data to the server
		      toServer = new DataOutputStream(socket.getOutputStream());
		    }
		    catch (Exception ex) {
		      ex.printStackTrace();
		    }

		    // Control the game on a separate thread
		    new Thread(() -> {
		      try {
		        // Get notification from the server
		        int player = fromServer.readInt();
		  
		        // Am I player 1 or 2?
		        if (player == playerLocal) {
		          // Receive startup notification from the server
		          fromServer.readInt(); // Whatever read is ignored
		  
		          // The other player has joined
		          //TODO
		          
		          // Notify the turn 
		          //TODO
		        }
		        else if (player == playerNetwork) {
		          // notifier au joueur 2 que le joueur local à la main
		        }
		  
		        // Continue to play
		        while (continueToPlay) {      
		          if (player == playerLocal) {
		            sendMove(); // Send the move to the server
		            receiveInfoFromServer(); // Receive info from the server
		          }
		          else if (player == playerNetwork) {
		            receiveInfoFromServer(); // Receive info from the server
		            sendMove(); // Send player 2's move to the server
		          }
		        }
		      }
		      catch (Exception ex) {
		        ex.printStackTrace();
		      }
		    }).start();
		  }
	  
	  
	  /** Send this player's move to the server */
	  private void sendMove() throws IOException {
	    toServer.writeInt(xSelected); // Send the selected row
	    toServer.writeInt(ySelected); // Send the selected column
	  }
	    
	  
	  
	  
	  /** Receive info from the server */
	  private void receiveInfoFromServer() throws IOException {
	    // Receive game status
	    int status = fromServer.readInt();

	    //si le status est égal à 1 le joueur local a gagné
	    if (status == controllerNetwork.p1_won) {
	      // Player 1 won, stop playing
	      continueToPlay = false;
	     
	   // notifier au joueur local qu'il a gagné
	      //TODO
	      // notifier au joueur network que le joueur local a gagné
	      //avec le receive move  dans l'exécution
	      //TODO
	      
	    }
	    
	    //si le status est égal à 2 le joueur local a gagné, 

	    else if (status == controllerNetwork.p2_won) {
	      // Player 2 won, stop playing
	      continueToPlay = false;
	      // notifier au joueur network qu'il a gagn2
	      //TODO
	      // notifier au joueur local que le joueur network a gagné
	      //avec le receive move  dans l'exécution
	      //TODO
	    }
	    
	    else {
	    	//recois les infos de déplacement du serveur
	      receiveMove();
	      // notifier de qui à le tour 
	      //"myTurn = true", it's my turn
	    }
	  }

	  private void receiveMove() throws IOException {
	    // Get the other player's move
	    int x = fromServer.readInt();
	    int y = fromServer.readInt();
	    //Get the getter to change the position of the opponent in local
	    player.mouvementsEnnemi(x, y, true);
	    
	    
	   
	  }
}
