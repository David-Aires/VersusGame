package versus.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import versus.model.CharacterModel;
import versus.model.PlayerModel;

public class PlayerModelTest {

	PlayerModel obj;
	CharacterModel plocalModel;
	CharacterModel enemyModel;
	
	@Before
	public void setUp() throws Exception {
		
		obj= new PlayerModel();
		
	}

	@Test
	public void testFirstLocation() {
		
		//test to see if local player begins at the good place
		assertEquals(0, obj.getLX());
		assertEquals(7, obj.getLY());
		
		
		//test to see if network player begins at the good place
		assertEquals(14, obj.getRX());
		assertEquals(7, obj.getRY());

	}
	
	
	@Test 
	public void testConnection(){
		assertEquals("Joueur local\nCoordonn�e X: 1\nCoordonn�e Y: 8\nEtat de d�placement: true\n\n\nJoueur r�seau\nCoordonn�e X: 15\nCoordonn�e Y: 8\nEtat de d�placement: true", obj.toString());
		
		
		assertEquals("127.0.0.1", obj.getIpAddress());
		assertEquals(false, obj.getIsConnected()); 
	}

}
