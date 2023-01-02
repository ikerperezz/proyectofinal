package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clases.Liga;

public class LigaTest {

	private Liga liga1;
	
	@Before
	public void setUp() {
		liga1= new Liga(1, "Joxow");
	}
	
	@Test
	public void testGetIdLiga() {
		assertEquals(1, liga1.getIdLiga());
				
	}
	
	@Test
	public void testSetIdLiga() {
		
		liga1.setIdLiga(5);
		assertEquals(5, liga1.getIdLiga());
				
	}
	
	@Test
	public void testGetNombreLiga() {
		assertEquals("Joxow", liga1.getNombreLiga());
				
	}
	
	@Test
	public void testSetNombreLiga() {
		
		liga1.setNombreLiga("Ikertxow");
		assertEquals("Ikertxow", liga1.getNombreLiga());
				
	}

}
