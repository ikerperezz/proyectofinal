package test;

import static org.junit.Assert.*;

import org.junit.Test;

import clases.Usuario;
import clases.UsuarioPublico;

public class UsuarioPublicoTest {

	@Test
	public void testGetIdLiga() {
		UsuarioPublico u = new UsuarioPublico("jigoa", "123j", 001, 1,1000000, 0);
		assertEquals("a1", u.getIdLiga());
	}
	@Test
	public void testGetDineroDisponible() {
		UsuarioPublico u = new UsuarioPublico("jigoa", "123j", 001, 1,1000000,0 );
		assertEquals(1000000, u.getDineroDisponible());
	}
	@Test
	public void testSetIdLiga() {
		UsuarioPublico u = new UsuarioPublico("jigoa", "123j", 001, 1,1000000, 0);
		u.setIdLiga(2);
		assertEquals(2, u.getIdLiga());
	}
	@Test
	public void testSetDineroDisponible() {
		UsuarioPublico u = new UsuarioPublico("jigoa", "123j", 001, 1,1000000, 0);
		u.setDineroDisponible(99000000);
		assertEquals(99000000, u.getDineroDisponible());
	}
	@Test
	public void testGetIdUsuario() {
		UsuarioPublico u = new UsuarioPublico("jigoa", "123j", 001, 1,1000000, 0);
		assertEquals("001", u.getIdUsuarioPublico());
	}
	@Test
	public void testSetIdUsuario() {
		UsuarioPublico u = new UsuarioPublico("jigoa", "123j", 001, 1,1000000, 0);
		u.setIdUsuarioPublico(002);
		assertEquals(002, u.getDineroDisponible());
	}
}
