/**
 * 
 */
package jUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import main.ModelWindow;

/**
 * @author Ajaxo
 *
 */
public class ModelWindowTest {

	private String map = "map/exemple.map";
	private String music = "sound/lost-in-the-meadows.ogg";
	ModelWindow model = new ModelWindow(map,music); 
	
	@Test
	public void testgetScreenHeight() {
		assertNotNull(model.getScreenHeight());
		assertEquals(1080, model.getScreenHeight());
	}
	
	@Test
	public void testgetScreenWidth() {
		assertNotNull(model.getScreenWidth());
		assertEquals(1920, model.getScreenWidth());
	}
	
	@Test
	public void testgetdimScreen() {
		assertNotNull(model.getDimScreen());
	}
	
	@Test
	public void testgetTiledMap() {
		assertNotNull(model.getTiledMap());
		assertEquals(map, model.getTiledMap());
	}
	
	@Test
	public void testgetMusic() {
		assertNotNull(model.getMusic());
		assertEquals(music, model.getMusic());
	}
	
}