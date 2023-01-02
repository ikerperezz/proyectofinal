package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import clases.Jugador;
import clases.Liga;
import clases.Usuario;
import clases.UsuarioPublico;

public class DBManager {

	private Connection conn = null;

	public void conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
		}

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:src/baseDatos/baseDatosProyecto.db");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se ha podido establecer la conexión a la base de datos");
		}

	}

	public void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.format("Error cerrando la conexión con la BD", e);
		}
	}

	public List<UsuarioPublico> crearLista() {
		List<UsuarioPublico> up = new ArrayList<UsuarioPublico>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"SELECT idUsuario, nombreDeUsuario, contraseña, dineroDisponible, idLiga, puntos FROM usuario");

			while (rs.next()) {
				int idUsuario = rs.getInt("idUsuario");
				String usuario = rs.getString("nombreDeUsuario");
				String contraseina = rs.getString("contraseña");
				int idLIga = rs.getInt("IdLIga");
				int dineroDisponible = rs.getInt("dineroDisponible");
				int puntos= rs.getInt("puntos");
				UsuarioPublico us = new UsuarioPublico(usuario, contraseina, idUsuario, idLIga, dineroDisponible,puntos);
				up.add(us);
			}
			return up;
		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
		}
		return null;
	}

	public void actualizarUsuarios(UsuarioPublico usuarioP) {
		try (PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO USUARIO ( nombreDeUsuario, contraseña, IdLiga, dineroDisponible, puntos) VALUES (?, ?,?,?,?)");
				Statement stmtForId = conn.createStatement()) {
			ResultSet rs = stmtForId.executeQuery("SELECT last_insert_rowid() AS id FROM USUARIO");
			
			stmt.setString(1, usuarioP.getUsuario());
			stmt.setString(2, usuarioP.getContraseina());
			stmt.setInt(3, usuarioP.getIdLiga());
			stmt.setInt(4, usuarioP.getDineroDisponible());
			stmt.setInt(5, usuarioP.getPuntos());

			stmt.executeUpdate();
			if (rs.next()) {
				int newId = rs.getInt("id");
				usuarioP.setIdUsuarioPublico(newId);
			}
		} catch (SQLException e) {
			System.out.format("Error cargando usuario", e);
			e.printStackTrace();
		}
	}
	
	public void eliminarUsuario(String nombreDeUsuario) {
		try (Statement stmt = conn.createStatement()) {
		stmt.executeUpdate("DELETE FROM usuario where nombreDeUsuario = '" + nombreDeUsuario + "'");
		stmt.executeUpdate("DELETE FROM jugadorenliga where nombreUsuario = '" + nombreDeUsuario + "'");
		Logger logger = Logger.getLogger( "Borrar usuario");
		logger.info("Usuario borrado");
	}catch (SQLException e) {
		System.out.format("Error eliminando usuario", e);
		e.printStackTrace();
	}
}
	
	
	public void update(UsuarioPublico usuarioPublico, String nombreDeUsuario) {
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE usuario SET nombreDeUsuario=?, contraseña=? WHERE nombreDeUsuario = '"+ nombreDeUsuario +"'")) {
			stmt.setString(1, usuarioPublico.getUsuario());
			stmt.setString(2, usuarioPublico.getContraseina());	
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.format("No se pudo guardar el usuario en la BD", e);
		}
	}
	public void updateLigaEnUsuario(UsuarioPublico usuarioPublico, String nombreDeUsuario) {
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE usuario SET idLiga=? WHERE nombreDeUsuario = '"+ nombreDeUsuario +"'")) {
			stmt.setInt(1, usuarioPublico.getIdLiga());	
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.format("No se pudo guardar el usuario en la BD", e);
		}
	}
	
	
	public int actualizarLigas(Liga liga) {
		int newId = 0;
		try (PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO LIGA (nombreLiga) VALUES (?)");
				Statement stmtForId = conn.createStatement()) {
			stmt.setString(1, liga.getNombreLiga());
			stmt.executeUpdate();
			
			ResultSet rs = stmtForId.executeQuery("SELECT last_insert_rowid() AS idLiga FROM LIGA");
			if (rs.next()) {
				newId = rs.getInt("idLiga");
				liga.setIdLiga(newId);
				
			}
			
			
			
			return newId ;
		} catch (SQLException e) {
			System.out.format("Error actualizando liga", e);
			e.printStackTrace();
			return 0;
		}
		
		
	
	}	


	public List<Liga> crearListaLiga(){
		
		List<Liga> liga = new ArrayList<Liga>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"SELECT idLiga, nombreLiga FROM Liga");

			while (rs.next()) {
				int idLiga = rs.getInt("idLiga");
				String nombre = rs.getString("nombreLiga");
				Liga lig = new Liga(idLiga, nombre);
				liga.add(lig);
			}
			return liga;
		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
		}
		return null;
	}
	
	
	
	public List<UsuarioPublico> crearListaDeMismaLiga(UsuarioPublico usP){
		List<UsuarioPublico> up = new ArrayList<UsuarioPublico>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"SELECT idUsuario, nombreDeUsuario, contraseña, dineroDisponible, idLiga, puntos FROM usuario WHERE idLiga = '"+usP.getIdLiga()+"'");

			while (rs.next()) {
				int idUsuario = rs.getInt("idUsuario");
				String usuario = rs.getString("nombreDeUsuario");
				String contraseina = rs.getString("contraseña");
				int idLIga = rs.getInt("IdLIga");
				int dineroDisponible = rs.getInt("dineroDisponible");
				int puntos= rs.getInt("puntos");
				UsuarioPublico us = new UsuarioPublico(usuario, contraseina, idUsuario, idLIga, dineroDisponible,puntos);
				up.add(us);
			}
			return up;
		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
		}
		return null;
	}
	public List<Jugador> crearListaJugadores(){
		List<Jugador> jug = new ArrayList<Jugador>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
	"SELECT idJugador, nombreJugador, valor, posicion, equipo, puntos FROM Jugadores");

			while (rs.next()) {
				int idJugador = rs.getInt("idJugador");
				String nombreJugador = rs.getString("nombreJugador");
				int valor = rs.getInt("valor");
				String posicion = rs.getString("posicion");
				String equipo = rs.getString("equipo");
				int puntos= rs.getInt("puntos");
	
				Jugador jugador = new Jugador(idJugador, nombreJugador, valor, posicion, equipo, puntos, false);
				jug.add(jugador);
				
			}
			return jug;
		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
		}
		return null;
	}	
	
	
	public List<Jugador> eliminarJugadoresDeLiga(int idLiga){
		List<Jugador> jug = crearListaJugadores();
		List<Integer> id = new ArrayList<>();

		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT idJugador FROM jugadorenliga where idLiga = '" + idLiga +"'");

			while (rs.next()) {
				int idJug = rs.getInt("idJugador");
				id.add(idJug);
			}	
		
		for (int i = 0; i < jug.size(); i++) {
			for (int j = 0; j < id.size(); j++) {
				if(id.contains(jug.get(i).getIdJugador())) {
					jug.remove(i);
				}
			}
		}
		
		return jug;
	
		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
		}
		return null;
		
}
	
	public List<Jugador> crearListaPorteros(int idLiga){
		
		List<Jugador> jug = eliminarJugadoresDeLiga(idLiga);
		
		List<Jugador> por= new ArrayList<>();
		for (int i = 0; i < jug.size(); i++) {
			if(jug.get(i).getPosicion().equals("Por")) {
				por.add(jug.get(i));
			}
		}
		
		return por;
	}
	public List<Jugador> crearListaDefensas(int idLiga){
		List<Jugador> jug = eliminarJugadoresDeLiga(idLiga);
		List<Jugador> def= new ArrayList<>();
		for (int i = 0; i < jug.size(); i++) {
			if(jug.get(i).getPosicion().equals("Def")) {
				def.add(jug.get(i));
			}
		}
		return def;
	}
	public List<Jugador> crearListaMedios(int idLiga){
		List<Jugador> jug = eliminarJugadoresDeLiga(idLiga);
		List<Jugador> med= new ArrayList<>();
		for (int i = 0; i < jug.size(); i++) {
			if(jug.get(i).getPosicion().equals("Med")) {
				med.add(jug.get(i));
			}
		}
		return med;
	}
	public List<Jugador> crearListaDelanteros(int idLiga){
		List<Jugador> jug = eliminarJugadoresDeLiga(idLiga);
		List<Jugador> del= new ArrayList<>();
		for (int i = 0; i < jug.size(); i++) {
			if(jug.get(i).getPosicion().equals("Del")) {
				del.add(jug.get(i));
			}
		}
		return del;
	}
	
	public List<Jugador> crearListaMercado(int idLiga){
		List<Jugador> por = crearListaPorteros(idLiga);
		List<Jugador> def = crearListaDefensas(idLiga);
		List<Jugador> med = crearListaMedios(idLiga);
		List<Jugador> del = crearListaDelanteros(idLiga);
		List<Jugador> jug = new ArrayList<>();
		Random r = new Random();
		int aleatorio = r.nextInt(por.size());
		jug.add(por.get(aleatorio));
		por.remove(aleatorio);
		aleatorio = r.nextInt(por.size());
		jug.add(por.get(aleatorio));
		por.remove(aleatorio);
		
		aleatorio = r.nextInt(def.size());
		jug.add(def.get(aleatorio));
		def.remove(aleatorio);
		aleatorio = r.nextInt(def.size());
		jug.add(def.get(aleatorio));
		def.remove(aleatorio);
		aleatorio = r.nextInt(def.size());
		jug.add(def.get(aleatorio));
		def.remove(aleatorio);
		
		aleatorio = r.nextInt(med.size());
		jug.add(med.get(aleatorio));
		med.remove(aleatorio);
		aleatorio = r.nextInt(med.size());
		jug.add(med.get(aleatorio));
		med.remove(aleatorio);
		aleatorio = r.nextInt(med.size());
		jug.add(med.get(aleatorio));
		med.remove(aleatorio);
		
		aleatorio = r.nextInt(del.size());
		jug.add(del.get(aleatorio));
		del.remove(aleatorio);
		aleatorio = r.nextInt(del.size());
		jug.add(del.get(aleatorio));
		del.remove(aleatorio);	
		aleatorio = r.nextInt(del.size());
		jug.add(del.get(aleatorio));
		del.remove(aleatorio);
		return jug;
	}
	
	
	
	
	
	
	
	public void crearPlantilla(int idLiga, String nombreUsuario) {
		List<Jugador> por = crearListaPorteros(idLiga);
		List<Jugador> def = crearListaDefensas(idLiga);
		List<Jugador> med = crearListaMedios(idLiga);
		List<Jugador> del = crearListaDelanteros(idLiga);
		List<Jugador> jug = new ArrayList<>();
		Random r = new Random();
		int aleatorio = r.nextInt(por.size());
		jug.add(por.get(aleatorio));
		por.remove(aleatorio);
		aleatorio = r.nextInt(por.size());
		jug.add(por.get(aleatorio));
		por.remove(aleatorio);
		
		aleatorio = r.nextInt(def.size());
		jug.add(def.get(aleatorio));
		def.remove(aleatorio);
		aleatorio = r.nextInt(def.size());
		jug.add(def.get(aleatorio));
		def.remove(aleatorio);
		aleatorio = r.nextInt(def.size());
		jug.add(def.get(aleatorio));
		def.remove(aleatorio);
		aleatorio = r.nextInt(def.size());
		jug.add(def.get(aleatorio));
		def.remove(aleatorio);
		aleatorio = r.nextInt(def.size());
		jug.add(def.get(aleatorio));
		def.remove(aleatorio);
		
		aleatorio = r.nextInt(med.size());
		jug.add(med.get(aleatorio));
		med.remove(aleatorio);
		aleatorio = r.nextInt(med.size());
		jug.add(med.get(aleatorio));
		med.remove(aleatorio);
		aleatorio = r.nextInt(med.size());
		jug.add(med.get(aleatorio));
		med.remove(aleatorio);
		aleatorio = r.nextInt(med.size());
		jug.add(med.get(aleatorio));
		med.remove(aleatorio);
		
		aleatorio = r.nextInt(del.size());
		jug.add(del.get(aleatorio));
		del.remove(aleatorio);
		aleatorio = r.nextInt(del.size());
		jug.add(del.get(aleatorio));
		del.remove(aleatorio);	
		aleatorio = r.nextInt(del.size());
		jug.add(del.get(aleatorio));
		del.remove(aleatorio);
		
		
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO jugadorenliga (nombreUsuario, idJugador, idLiga, titular) VALUES (?,?,?,?)")) {
			
			int contadorTitularPor=0;
			int contadorTitularDef = 0;
			int contadorTitularMed = 0;
			int contadorTitularDel = 0;
			for (int i = 0; i < jug.size(); i++) {
			stmt.setString(1, nombreUsuario);
			stmt.setInt(2, jug.get(i).getIdJugador());	
			stmt.setInt(3, idLiga);	
			
			String posicion= jug.get(i).getPosicion();
			switch(posicion) {
			case "Por":
			if (contadorTitularPor<1) {
				stmt.setBoolean(4, true);
			contadorTitularPor=contadorTitularPor+1;
			break;
			}else {
				stmt.setBoolean(4, false);
				break;
			}
			case "Def":
			if (contadorTitularDef<4) {
				stmt.setBoolean(4, true);
				contadorTitularDef=contadorTitularDef+1;
				break;
				}else {
					stmt.setBoolean(4, false);
					break;
				}
			case "Med":
			if (contadorTitularMed<4) {
				stmt.setBoolean(4, true);
				contadorTitularMed=contadorTitularMed+1;
				break;
				}else {
					stmt.setBoolean(4, false);
					break;
				}
			case "Del":
			if (contadorTitularDel<3) {
				stmt.setBoolean(4, true);
				contadorTitularMed=contadorTitularMed+1;
				break;
				}else {
					stmt.setBoolean(4, false);
					break;
				}
			}
			stmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			System.out.format("No se pudo guardar el usuario en la BD", e);
			e.printStackTrace();
		}
	}
	
	
	
	public List<Jugador> crearListaPlantilla(UsuarioPublico usP){

		List<Jugador> jug = new ArrayList<Jugador>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"SELECT Jugadores.idJugador, nombreJugador, valor, posicion, equipo, puntos, nombreUsuario, titular FROM Jugadores JOIN jugadorenliga ON Jugadores.idJugador = jugadorenliga.idJugador where nombreUsuario = '" +usP.getUsuario()+ "'");

			while (rs.next()) {
				int idJugador = rs.getInt("idJugador");
				String nombreJugador = rs.getString("nombreJugador");
				int valor = rs.getInt("valor");
				String posicion = rs.getString("posicion");
				String equipo = rs.getString("equipo");
				int puntos= rs.getInt("puntos");
				boolean titular = rs.getBoolean("titular");
				
				
				Jugador jugador = new Jugador(idJugador, nombreJugador, valor, posicion, equipo, puntos, titular);
				jug.add(jugador);
			}
			return jug;
		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
			return null;
		}
		
	}
	
	public void updateJugadorEnLiga(UsuarioPublico usP, Jugador jug) {
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE jugadorenliga SET titular=? WHERE nombreUsuario = '"+ usP.getUsuario() +"' AND idJugador ='" +jug.getIdJugador() +"'")) {
		
				stmt.setBoolean(1, jug.isTitular());
				stmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			System.out.format("Error actualizando jugador", e);
			e.printStackTrace();
		}
	}
	
	
	
	public Connection getConn() {
		return conn;
	}
	
	
	
}
