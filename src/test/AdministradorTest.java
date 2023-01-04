package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clases.Administrador;
import clases.Jugador;

public class AdministradorTest {

	private Administrador admin1;
	
	@Before
	public void setUp() {
		admin1= new Administrador("Joxow", "Pilota");
	}
		
	@Test
	public void testGetSetNombreUsuario() {
		
		assertEquals("Joxow", admin1.getNombreUsuario());
		admin1.setNombreUsuario("Pedri");
		assertEquals("Pedri", admin1.getNombreUsuario());
		
		
	}
	
	@Test
	public void testGetSetContraseina() {
		assertEquals("Pilota", admin1.getContraseina());
		admin1.setContraseina("Futbola");
		assertEquals("Futbola", admin1.getContraseina());
				
	}
	
	@Test
	public void testToString() {
		
		assertEquals("Administrador [nombreUsuario=" + admin1.getNombreUsuario() + ", contraseina=" + admin1.getContraseina() + "]", admin1.toString());
	
	}
}



