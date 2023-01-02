package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clases.Jugador;
import clases.Liga;

public class JugadorTest {

	private Jugador j1;
	
	@Before
	public void setUp() {
		j1= new Jugador(1, "Guardiola", 50000000, "Medio", "Barcelona", 0, false);
	}
	
	
	@Test
	public void testGetSetIdJugador() {
		assertEquals(1, j1.getIdJugador());
		j1.setIdJugador(3);
		assertEquals(3, j1.getIdJugador());
		
		
	}
	
	@Test
	public void testGetSetNombreJugador() {
		assertEquals("Guardiola", j1.getNombreJugador());
		j1.setNombreJugador("Patxi Puñal");
		assertEquals("Patxi Puñal", j1.getNombreJugador());
		
		
	}

	@Test
	public void testGetSetValor() {
		assertEquals(50000000, j1.getValor());
		j1.setValor(45000000);
		assertEquals(45000000, j1.getValor());
		
		
	}
	
	@Test
	public void testGetSetPosicionJugador() {
		assertEquals("Medio", j1.getPosicion());
		j1.setPosicion("Defensa");
		assertEquals("Defensa", j1.getPosicion());
		
		
	}
	
	@Test
	public void testGetSetEquipo() {
		assertEquals("Barcelona", j1.getEquipo());
		j1.setEquipo("Osasuna");
		assertEquals("Osasuna", j1.getEquipo());
		
		
	}
	
	@Test
	public void testGetSetPuntos() {
		assertEquals(0, j1.getPuntos());
		j1.setPuntos(13);
		assertEquals(13, j1.getPuntos());
		
		
	}
}
