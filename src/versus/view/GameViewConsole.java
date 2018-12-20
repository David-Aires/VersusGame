/**
 * 
 */
package versus.view;

import java.util.InputMismatchException;

import java.util.Observable;
import java.util.Scanner;

import versus.controller.CharacterController;
import versus.controller.NetworkController;
import versus.model.PlayerModel;

/**
 * @author Aires David, Quentin Lebrun
 *
 */
public class GameViewConsole extends GameView {
	protected Scanner sc;
	private  String [][] board= new String[15][15];
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(player);
		initBoard();
	}
	
	public void affiche(String string) {
		System.out.println(string);
	}
		
	public GameViewConsole(PlayerModel player,CharacterController controller, NetworkController networkController) {
		super(player,controller,networkController);
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
		for(int i=0;i<player.getBonus().get(0).size();i++) {
			board[player.getBonus().get(1).get(i)][player.getBonus().get(0).get(i)]= "[ B ]";
		}
		printHelp();
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
						if(i<0 || i> 15 || a<0 || a>15){
							affiche("Emplacement incorrect");
							printHelp(); 
							break;
						}
						else{	
							controller.mouvementLocal(i, a,1);;
							break;
						}
						
						case "E" : 
						controller.mouvementLocal((player.getLX()+1<board.length?player.getLX()+1:player.getLX()),player.getLY(),0);
						break;
						
						case "O" : 
						controller.mouvementLocal((player.getLX()-1<0?player.getLX():player.getLX()-1),player.getLY(),0);
						break;
						
						case "S" : 
						controller.mouvementLocal(player.getLX(),(player.getLY()+1>board.length-1?player.getLY():player.getLY()+1),0);
						break;
								
						case "N" : 
						controller.mouvementLocal(player.getLX(),(player.getLY()-1<0?player.getLY():player.getLY()-1),0);
						break;
							
						default : 
						affiche("Opération incorrecte");
						printHelp();
					}	
				}
				catch(InputMismatchException e){
					affiche("Format d'input incorrect");
					printHelp();
				}
			}
		}
	}
}