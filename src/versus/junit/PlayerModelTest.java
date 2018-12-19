package versus.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import versus.model.PlayerModel;

public class PlayerModelTest {

	PlayerModel obj;
	
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

}
