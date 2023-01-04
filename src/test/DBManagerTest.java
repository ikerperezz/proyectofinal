package test;

import static org.junit.Assert.*;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import baseDatos.DBManager;
import clases.Usuario;
import clases.UsuarioPublico;

public class DBManagerTest {

	private Connection conexionPruebas;
	private DBManager gestor;
	private ResultSet rs;
	private DBManager usuario;
	

	@Before
	public void setUp() throws Exception {
		gestor.conectar();
	}

	@After
	public void tearDown() throws Exception {
		gestor.disconnect();
	}

	@Test // para asegurarnos de que no da error al crear lista
	public void testCrearLista() {

		
		try {
			
			conexionPruebas=gestor.getConn();
			
			List<UsuarioPublico> usu=gestor.crearLista();
			
			try(PreparedStatement ps=conexionPruebas.prepareStatement("Select * from Usuario")) {
				
			rs=ps.executeQuery();	
				
			UsuarioPublico up;
			
			while(rs.next()) {
				
		up=new UsuarioPublico(rs.getString("usuario"), rs.getString("contraseña"), 
rs.getInt("idUsuario"), rs.getInt("idLiga"), rs.getInt("dineroDisponible"), rs.getInt("puntos"));
				
			for (int i = 0; i < usu.size(); i++) {
				
				if(usu.get(i).getIdUsuarioPublico()==up.getIdUsuarioPublico()) {
					
					assertTrue(up.equals(usu.get(i)));
					
				}
				
			}
				
				
			}
				
			} catch (Exception e) {
				// TODO: handle exception
			}

		} catch (Exception e) {


			fail("Error a la hora de crear lista");

		}

	}

	@Test // para ver que cuando añadimos un usuario, el tamaño del array incrementa
	public void testActualizarUsuarios() {

		List<UsuarioPublico> up = new ArrayList<>();

		up = usuario.crearLista();
		int largoLista = up.size();

		usuario.actualizarUsuarios(null);

		assertEquals(largoLista + 1, usuario.crearLista().size());

	}

	@Test // para ver que cuando eliminamos un usuario, el tamaño del array disminuye
	public void testEliminarUsuario() {

		List<UsuarioPublico> up = new ArrayList<>();

		up = usuario.crearLista();
		int largoLista = up.size();

		usuario.eliminarUsuario(null);

		assertEquals(largoLista - 1, usuario.crearLista().size());

	}
	
	

	
}