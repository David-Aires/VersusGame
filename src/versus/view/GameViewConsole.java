/**
 * 
 */
package versus.view;

import java.util.InputMismatchException;

import java.util.Observable;
import java.util.Scanner;

import versus.controller.CharacterController;
import versus.model.PlayerModel;



/**
 * @author Aires David, Quentin Lebrun
 *
 */
public class GameViewConsole extends GameView {
	
	protected Scanner sc;
	private  String [][] board= new String[11][11];
	
	private String reponse="Bienvenue";
	


	@Override
	public void update(Observable o, Object arg) {
		
		System.out.println(player);
		initBoard();
		
	}
	
	
	public void affiche(String string) {
		System.out.println(string);
	}
		
	public GameViewConsole(PlayerModel player,CharacterController controller) {
		
		super(player,controller);
		update(null,null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();
	}
 
	
	private  void initBoard() {
		
		for(int j=0;j<board.length;j++) {
			for(int i=0;i<board.length;i++) {
				board[j][i]= "[   ]";
			}
		}
		board[player.getLY()][player.getLX()]="[ § ]";
		board[player.getRY()][player.getRX()]="[ £ ]";
		printHelp();
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
// it's won when the player arrives a the last y which is 11 (10 in the array)
	private void isWon(){
		if(player.getLX()==10){
			System.out.println("Vous avez gagné");
		}
	}
	// Looks at what can happen with the move of one player
		private void reviewWhatHappens(){
			controller.isTrapped();
			controller.goBackX();
			controller.goBackY();
			isWon();
		}
	
	private void printHelp(){
		printBoard();
		affiche("Pour se téléporter : tp + coordX + coordY");
		affiche("Pour se déplacer : ");
		affiche("N : Nord");
		affiche("E : Est");
		affiche("S: Sud");
		affiche("O : Ouest");
		
	}
	
	
	
	 public  void printBoard() {
		 
		 	        
		 
		 	        
		 			System.out.println();
		 	        for (int i = 0; i < board.length; i++) {
		 
		 	           
		 	        	System.out.println();
		 	            for (int j = 0; j < board.length; j++) {
		 
		 	                System.out.print(board[i][j] + " ");
		 
		 	            }
		 
		 	            System.out.println();
		 	            
		 
		 	           
		 
		 	        }
		 
		 	    }
	 
	 private class ReadInput implements Runnable{
			public void run() {
				while(true){
					try{
						
						String c = sc.next();
						if(c.length()<1){
							affiche("Format d'input incorrect");
							printHelp();
						}
						
						
						
						
						
						//console's commands to move the players
						switch(c){
							case "tp" :
								int i = sc.nextInt();
								int a= sc.nextInt();
								if(i<0 || i> 11 || a<0 || a>11){
									affiche("Emplacement incorrect");
									printHelp(); 
									break;
								}else {
									
								reponse= controller.mouvementLocal(i, a);;
								break;
								}
							
							case "E" : 
								reponse= controller.mouvementLocal((player.getLX()+1<board.length?player.getLX()+1:player.getLX()),player.getLY());
								
								reviewWhatHappens();
								break;
							case "O" : 
								reponse= controller.mouvementLocal((player.getLX()-1<0?player.getLX():player.getLX()-1),player.getLY());
								reviewWhatHappens();


								break;
								
							case "S" : 
								reponse= controller.mouvementLocal(player.getLX(),(player.getLY()+1>board.length-1?player.getLY():player.getLY()+1));
								reviewWhatHappens();


								break;
								
							case "N" : 
								reponse= controller.mouvementLocal(player.getLX(),(player.getLY()-1<0?player.getLY():player.getLY()-1));
								reviewWhatHappens();


								break;
							default : 
								affiche("Opération incorrecte");
								printHelp();
						}
						affiche(reponse);
					}
					catch(InputMismatchException e){
						affiche("Format d'input incorrect");
						printHelp();
					}
				}
			}
		}

	
	
}

