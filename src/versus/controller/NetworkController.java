package versus.controller;

import java.io.DataOutputStream;
import java.io.IOException;

import versus.model.PlayerModel;
import versus.view.GameView;

public class NetworkController {

	PlayerModel player;
	GameView vue;
	
	
	public NetworkController(PlayerModel player){
		this.player=player;
		
	}
	
	
	/** Send the move to other player */
    private void sendMove(DataOutputStream out, int x, int y)
        throws IOException {
      out.writeInt(x); // Send x index
      out.writeInt(y); // Send y index
    }
    
    

       
}


