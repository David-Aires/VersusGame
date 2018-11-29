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
 * @author Aires David
 *
 */
public class GameViewConsole extends GameView {
	
	protected Scanner sc;
	private  String [][] board= new String[15][15];
	private int [] piège;
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
		board[player.getLY()][player.getLX()]="[ + ]";
		board[player.getRY()][player.getRX()]="[ + ]";
		printHelp();
		
		
		
		
	
	
	}
	

	
	private void printHelp(){
		printBoard();
		affiche("Pour se téléporter : tp + coordX + coordY");
		affiche("Pour se déplacer : ");
		affiche("H : haut");
		affiche("B: bas");
		affiche("A : avant");
		affiche("R: arrière");
		
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
						
						
						
						
						
						
						switch(c){
							case "tp" :
								int i = sc.nextInt();
								int a= sc.nextInt();
								if(i<0 || i> 15 || a<0 || a>15){
									affiche("Emplacement incorrect");
									printHelp(); 
									break;
								}else {
									
								reponse= controller.mouvementLocal(i, a);;
								break;
								}
							
							case "A" : 
								reponse= controller.mouvementLocal((player.getLX()+1<board.length?player.getLX()+1:player.getLX()),player.getLY());
								break;
							case "R" : 
								reponse= controller.mouvementLocal((player.getLX()-1<0?player.getLX():player.getLX()-1),player.getLY());
								break;
								
							case "B" : 
								reponse= controller.mouvementLocal(player.getLX(),(player.getLY()+1>board.length-1?player.getLY():player.getLY()+1));
								break;
								
							case "H" : 
								reponse= controller.mouvementLocal(player.getLX(),(player.getLY()-1<0?player.getLY():player.getLY()-1));
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

