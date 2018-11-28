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
		initBoard(player.getX(),player.getY());
		
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
 
	
	private  void initBoard(int x,int y) {
		
		for(int j=0;j<board.length;j++) {
			for(int i=0;i<board.length;i++) {
				board[j][i]= "[   ]";
			}
		}
		miseEchelle(x, y);
		miseEchelle(300, 600);
		printHelp();
		
		
		
		
	
	
	}
	
	public void miseEchelle(int x,int y) {
		board[(x-1)/40][(y-1)/40]="[ + ]";
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
								if(i<0 || i> 600 || a<0 || a>600){
									affiche("Emplacement incorrect");
									printHelp(); 
									break;
								}else {
									
								reponse= controller.mouvement(i, a);;
								break;
								}
							
							case "A" : 
								reponse= controller.mouvement(player.getX(), player.getY()+41);
								break;
							case "R" : 
								reponse= controller.mouvement(player.getX(), player.getY()-41);
								break;
								
							case "B" : 
								reponse= controller.mouvement(player.getX()+41, player.getY());
								break;
								
							case "H" : 
								reponse= controller.mouvement(player.getX()-41, player.getY());
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

