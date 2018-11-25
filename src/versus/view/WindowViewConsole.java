package versus.view;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;


import versus.controller.WindowController;
import versus.model.WindowModel;


public class WindowViewConsole extends WindowView{

	protected Scanner sc;
	public  char[][] board=  new char[20][20];
	
	public WindowViewConsole(WindowModel model, WindowController controller) {
		super(model, controller);
		initBoard();
		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();
	}
	
	private  void initBoard() {
		
		for(int j=0;j<board.length;j++) {
			for(int i=0;i<board.length;i++) {
				board[j][i]= '-';
			}
		}
	}
	
	
	 public  void printBoard() {
		 
	        
		 
	        
			System.out.println();
	        for (int i = 0; i < board.length; i++) {

	            System.out.print("| ");

	            for (int j = 0; j < board.length; j++) {

	                System.out.print(board[i][j] + " | ");

	            }

	            System.out.println();
	            System.out.println();

	           

	        }

	    }
		
	@Override
	public void update(Observable arg0, Object arg1) {
		printBoard();
	}

	@Override
	public void affiche(String string) {
		System.out.println(string);
		
	}
	
	private void printHelp(){
		affiche("Pour se déplacer : M + coordX + coordY");
	}
	
	 private class ReadInput implements Runnable{
			public void run() {
				while(true){
					try{
						
						String c = sc.next();
						if(c.length()!=1){
							affiche("Format d'input incorrect");
							printHelp();
						}
							
						int x = sc.nextInt();
						int y= sc.nextInt();
						
						if(x<0 || x> 500 || y<0 || y>500){
							affiche("Emplacement incorrect");
							printHelp(); 
						}
						switch(c){
							case "M" :
								controller.mouvement(x,y);
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
