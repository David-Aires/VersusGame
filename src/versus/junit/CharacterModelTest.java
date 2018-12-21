package versus.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import versus.model.CharacterModel;

public class CharacterModelTest {

	CharacterModel obj;
	
	@Before
	public void setUp() throws Exception {
		
		obj = new CharacterModel();
		obj.mouvement(10, 2,1);
	}

	
	@Test
	public void test() {
		assertEquals(10,obj.getX());
		assertEquals(2,obj.getY());

		assertEquals(1,obj.getMoving());

		//11 in x and 3 in y because our table is surrounded 
		//by a box for displaying the box numbers in x and y		
		assertEquals("\nCoordonnée X: 11\nCoordonnée Y: 3\nEtat de déplacement: 1", obj.toString());
	}

}
