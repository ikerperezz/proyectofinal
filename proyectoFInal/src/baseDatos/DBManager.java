package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import clases.Jugador;
import clases.Liga;
import clases.Oferta;
import clases.UsuarioPublico;
import interfaces.IManejoDeDatos;

public class DBManager implements IManejoDeDatos {

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
				int puntos = rs.getInt("puntos");
				UsuarioPublico us = new UsuarioPublico(usuario, contraseina, idUsuario, idLIga, dineroDisponible,
						puntos);
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

			stmt.setString(1, usuarioP.getUsuario());
			stmt.setString(2, usuarioP.getContraseina());
			stmt.setInt(3, usuarioP.getIdLiga());
			stmt.setInt(4, usuarioP.getDineroDisponible());
			stmt.setInt(5, usuarioP.getPuntos());

			stmt.executeUpdate();
			ResultSet rs = stmtForId.executeQuery("SELECT last_insert_rowid() AS id FROM USUARIO");
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
			Logger logger = Logger.getLogger("Borrar usuario");
			logger.info("Usuario borrado");
		} catch (SQLException e) {
			System.out.format("Error eliminando usuario", e);
			e.printStackTrace();
		}
	}

	public void update(UsuarioPublico usuarioPublico, String nombreDeUsuario) {
		try (PreparedStatement stmt = conn
				.prepareStatement("UPDATE usuario SET nombreDeUsuario=?, contraseña=? WHERE nombreDeUsuario = '"
						+ nombreDeUsuario + "'")) {
			stmt.setString(1, usuarioPublico.getUsuario());
			stmt.setString(2, usuarioPublico.getContraseina());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.format("No se pudo guardar el usuario en la BD", e);
		}
	}

	public void updateLigaEnUsuario(UsuarioPublico usuarioPublico, String nombreDeUsuario) {
		try (PreparedStatement stmt = conn
				.prepareStatement("UPDATE usuario SET idLiga=? WHERE nombreDeUsuario = '" + nombreDeUsuario + "'")) {
			stmt.setInt(1, usuarioPublico.getIdLiga());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.format("No se pudo guardar el usuario en la BD", e);
		}
	}

	public int actualizarLigas(Liga liga) {
		int newId = 0;
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO LIGA (nombreLiga) VALUES (?)");
				Statement stmtForId = conn.createStatement()) {
			stmt.setString(1, liga.getNombreLiga());
			stmt.executeUpdate();

			ResultSet rs = stmtForId.executeQuery("SELECT last_insert_rowid() AS idLiga FROM LIGA");
			if (rs.next()) {
				newId = rs.getInt("idLiga");
				liga.setIdLiga(newId);

			}

			return newId;
		} catch (SQLException e) {
			System.out.format("Error actualizando liga", e);
			e.printStackTrace();
			return 0;
		}

	}

	public List<Liga> crearListaLiga() {

		List<Liga> liga = new ArrayList<Liga>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT idLiga, nombreLiga FROM Liga");

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

	public List<UsuarioPublico> crearListaDeMismaLiga(UsuarioPublico usP) {
		List<UsuarioPublico> up = new ArrayList<UsuarioPublico>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"SELECT idUsuario, nombreDeUsuario, contraseña, dineroDisponible, idLiga, puntos FROM usuario WHERE idLiga = '"
							+ usP.getIdLiga() + "'");

			while (rs.next()) {
				int idUsuario = rs.getInt("idUsuario");
				String usuario = rs.getString("nombreDeUsuario");
				String contraseina = rs.getString("contraseña");
				int idLIga = rs.getInt("IdLIga");
				int dineroDisponible = rs.getInt("dineroDisponible");
				int puntos = rs.getInt("puntos");
				UsuarioPublico us = new UsuarioPublico(usuario, contraseina, idUsuario, idLIga, dineroDisponible,
						puntos);
				up.add(us);
			}
			return up;
		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
		}
		return null;
	}

	public List<Jugador> crearListaJugadores() {
		List<Jugador> jug = new ArrayList<Jugador>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt
					.executeQuery("SELECT idJugador, nombreJugador, valor, posicion, equipo, puntos FROM Jugadores");

			while (rs.next()) {
				int idJugador = rs.getInt("idJugador");
				String nombreJugador = rs.getString("nombreJugador");
				int valor = rs.getInt("valor");
				String posicion = rs.getString("posicion");
				String equipo = rs.getString("equipo");
				int puntos = rs.getInt("puntos");

				Jugador jugador = new Jugador(idJugador, nombreJugador, valor, posicion, equipo, puntos, false);
				jug.add(jugador);

			}
			return jug;
		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
		}
		return null;
	}

	public List<Jugador> eliminarJugadoresDeLiga(int idLiga) {
		List<Jugador> jug = crearListaJugadores();
		List<Integer> id = new ArrayList<>();

		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT idJugador FROM jugadorenliga where idLiga = '" + idLiga + "'");

			while (rs.next()) {
				int idJug = rs.getInt("idJugador");
				id.add(idJug);
			}

			for (int i = 0; i < jug.size(); i++) {
				for (int j = 0; j < id.size(); j++) {
					if (id.contains(jug.get(i).getIdJugador())) {
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

	public List<Jugador> crearListaPorteros(int idLiga) {

		List<Jugador> jug = eliminarJugadoresDeLiga(idLiga);

		List<Jugador> por = new ArrayList<>();
		for (int i = 0; i < jug.size(); i++) {
			if (jug.get(i).getPosicion().equals("Por")) {
				por.add(jug.get(i));
			}
		}

		return por;
	}

	public List<Jugador> crearListaDefensas(int idLiga) {
		List<Jugador> jug = eliminarJugadoresDeLiga(idLiga);
		List<Jugador> def = new ArrayList<>();
		for (int i = 0; i < jug.size(); i++) {
			if (jug.get(i).getPosicion().equals("Def")) {
				def.add(jug.get(i));
			}
		}
		return def;
	}

	public List<Jugador> crearListaMedios(int idLiga) {
		List<Jugador> jug = eliminarJugadoresDeLiga(idLiga);
		List<Jugador> med = new ArrayList<>();
		for (int i = 0; i < jug.size(); i++) {
			if (jug.get(i).getPosicion().equals("Med")) {
				med.add(jug.get(i));
			}
		}
		return med;
	}

	public List<Jugador> crearListaDelanteros(int idLiga) {
		List<Jugador> jug = eliminarJugadoresDeLiga(idLiga);
		List<Jugador> del = new ArrayList<>();
		for (int i = 0; i < jug.size(); i++) {
			if (jug.get(i).getPosicion().equals("Del")) {
				del.add(jug.get(i));
			}
		}
		return del;
	}

	public void eliminarMercado() {
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate("DELETE FROM mercado");
		} catch (SQLException e) {
			System.out.format("Error eliminando mercado", e);
			e.printStackTrace();
		}
	}

	public void crearMercado(int idLiga) {
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

		try (PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO mercado (idLiga, idJugador, ofertaminima) VALUES (?,?,?)")) {
			for (int i = 0; i < jug.size(); i++) {
				stmt.setInt(1, idLiga);
				stmt.setInt(2, jug.get(i).getIdJugador());
				stmt.setInt(3, jug.get(i).getValor());
				stmt.executeUpdate();

			}

		} catch (SQLException e) {
			System.out.format("Error creando mercado", e);
			e.printStackTrace();
		}

	}

	public List<Jugador> crearListaMercado(int idLiga) {

		List<Jugador> jug = new ArrayList<Jugador>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"SELECT Jugadores.idJugador, nombreJugador, valor, posicion, equipo, puntos FROM Jugadores JOIN mercado ON Jugadores.idJugador = mercado.idJugador where mercado.idLiga = '"
							+ idLiga + "'");

			while (rs.next()) {
				int idJugador = rs.getInt("idJugador");
				String nombreJugador = rs.getString("nombreJugador");
				int valor = rs.getInt("valor");
				String posicion = rs.getString("posicion");
				String equipo = rs.getString("equipo");
				int puntos = rs.getInt("puntos");

				Jugador jugador = new Jugador(idJugador, nombreJugador, valor, posicion, equipo, puntos, false);
				jug.add(jugador);
			}
			return jug;
		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
			e.printStackTrace();
			return null;
		}

	}

	public void meterJugadoresMercado(int idJugador, int valor, UsuarioPublico usP) {
		try (PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO mercado (idLiga, idJugador, ofertaminima, idUsuarioVenta) VALUES (?,?,?,?)")) {

			stmt.setInt(1, usP.getIdLiga());
			stmt.setInt(2, idJugador);
			stmt.setInt(3, valor);
			stmt.setString(4, usP.getUsuario());
			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.format("Error añadiendo jugador a mercado", e);
			e.printStackTrace();
		}

	}

	public void eliminarJugadoresDePlantilla(int id) {
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate("DELETE FROM jugadorenliga where idJugador = " + id);
		} catch (SQLException e) {
			System.out.format("Error eliminando jugador", e);
			e.printStackTrace();
		}
	}

	public void devolverJugadoresDeUsuario() {
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"SELECT idjugador, idLiga, idUsuarioVenta FROM mercado where idUsuarioVenta is not null and ofertaMasAlta is null");

			while (rs.next()) {

				int idJugador = rs.getInt("idJugador");
				String nombreUsuario = rs.getString("idUsuarioVenta");
				int idLiga = rs.getInt("idLiga");

				try (PreparedStatement stmt1 = conn.prepareStatement(
						"INSERT INTO jugadorenliga (idJugador,nombreUsuario, idLiga, titular) VALUES (?,?,?,?)")) {

					stmt1.setInt(1, idJugador);
					stmt1.setString(2, nombreUsuario);
					stmt1.setInt(3, idLiga);
					stmt1.setBoolean(4, false);
					stmt1.executeUpdate();
				} catch (SQLException e) {
					System.out.format("Error añadiendo jugador a liga", e);
					e.printStackTrace();
				}

			}

		} catch (SQLException e) {
			System.out.format("Error seleccionandojugadores", e);
			e.printStackTrace();
		}
	}

	public void cambiarJugadoresDeUsuario() {
		List<Oferta> ofertas = new ArrayList<Oferta>();
		List<UsuarioPublico> dinero = new ArrayList<UsuarioPublico>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"SELECT idjugador, idLiga, idUsuario, ofertaMasAlta, idUsuarioVenta FROM mercado where idUsuarioVenta is not null and ofertaMasAlta is not null");

			while (rs.next()) {

				int idjug = rs.getInt("idJugador");
				String us = rs.getString("idUsuario");
				int idLiga = rs.getInt("idLiga");
				int oferta = rs.getInt("ofertaMasAlta");
				String us2 = rs.getString("idUsuarioVenta");
				Oferta ofer = new Oferta(us, us2, idjug, oferta, idLiga);
				ofertas.add(ofer);

				try (PreparedStatement stmt1 = conn.prepareStatement(
						"INSERT INTO jugadorenliga (idJugador,nombreUsuario, idLiga, titular) VALUES (?,?,?,?)")) {

					stmt1.setInt(1, idjug);
					stmt1.setString(2, us);
					stmt1.setInt(3, idLiga);
					stmt1.setBoolean(4, false);
					stmt1.executeUpdate();
				} catch (SQLException e) {
					System.out.format("Error añadiendo jugador a liga", e);
					e.printStackTrace();
				}

			}

		} catch (SQLException e) {
			System.out.format("Error seleccionandojugadores", e);
			e.printStackTrace();
		}

		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT nombreDeUsuario, dineroDisponible FROM usuario");

			while (rs.next()) {
				int dineroDis = rs.getInt("dineroDisponible");
				String nombre = rs.getString("nombreDeUsuario");
				UsuarioPublico us = new UsuarioPublico(nombre, "", 0, 0, dineroDis, 0);
				dinero.add(us);
			}
		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
			e.printStackTrace();
		}

		for (int i = 0; i < dinero.size(); i++) {

			for (int j = 0; j < ofertas.size(); j++) {
				if (dinero.get(i).getUsuario().equals(ofertas.get(j).getNombreUsuario())) {
					int valor = dinero.get(i).getDineroDisponible() - ofertas.get(j).getOferta();
					dinero.get(i).setDineroDisponible(valor);
				}
				if (dinero.get(i).getUsuario().equals(ofertas.get(j).getNombreUsuario2())) {
					int valor = dinero.get(i).getDineroDisponible() + ofertas.get(j).getOferta();
					dinero.get(i).setDineroDisponible(valor);
				}
			}
		}
		for (int i = 0; i < dinero.size(); i++) {
			try (PreparedStatement stmt = conn
					.prepareStatement("UPDATE usuario SET dineroDisponible=? WHERE nombreDeUsuario = '"
							+ dinero.get(i).getUsuario() + "'")) {
				stmt.setInt(1, dinero.get(i).getDineroDisponible());
				stmt.executeUpdate();
			} catch (SQLException e) {
				System.out.format("Error actualizando oferta", e);
				e.printStackTrace();
			}
		}

	}

	public void eliminarJugadoresCambiados() {
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate("DELETE FROM mercado where idUsuarioVenta is not null");
		} catch (SQLException e) {
			System.out.format("Error eliminando jugador", e);
			e.printStackTrace();
		}

	}

	public List<Integer> crearListaIdLigas() {

		List<Integer> up = new ArrayList<Integer>();
		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery("SELECT idLiga FROM liga");

			while (rs.next()) {
				up.add(rs.getInt("idLiga"));
			}
			return up;

		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
		}
		return null;
	}

	public int conseguirIdJugador(String nombre) {
		int id = 0;
		List<Jugador> jug = crearListaJugadores();
		for (int i = 0; i < jug.size(); i++) {
			if (nombre.contains(jug.get(i).getNombreJugador())) {
				id = jug.get(i).getIdJugador();
				break;
			}
		}
		return id;
	}

	public int conseguirIdUsuario(String nombre) {
		int id = 0;
		List<UsuarioPublico> usu = crearLista();

		for (int i = 0; i < usu.size(); i++) {
			if (nombre.contains(usu.get(i).getUsuario())) {
				id = usu.get(i).getIdUsuarioPublico();
				break;
			}
		}
		return id;
	}

	public int conseguirPuntosUsuario(String nombre) {
		int puntos = 0;
		List<UsuarioPublico> usu = crearLista();

		for (int i = 0; i < usu.size(); i++) {
			if (nombre.contains(usu.get(i).getUsuario())) {
				puntos = usu.get(i).getPuntos();
				break;
			}
		}
		return puntos;
	}

	public int conseguirDineroUsuario(String nombre) {
		int dinero = 0;
		List<UsuarioPublico> usu = crearLista();

		for (int i = 0; i < usu.size(); i++) {
			if (nombre.contains(usu.get(i).getUsuario())) {
				dinero = usu.get(i).getDineroDisponible();
				break;
			}
		}
		return dinero;
	}

	public int conseguirMayorOferta(int id) {
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT ofertaMasAlta FROM Mercado where idJugador = '" + id + "'");

			return rs.getInt("ofertaMasAlta");
		} catch (SQLException e) {
			System.out.format("Error consiguiendo", e);
			return 0;
		}

	}

	public int conseguirValor(int id) {
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT valor FROM jugadores where idJugador = '" + id + "'");

			return rs.getInt("valor");
		} catch (SQLException e) {
			System.out.format("Error consiguiendo valor", e);
			e.printStackTrace();
			return 0;
		}

	}

	public void updateMayoroferta(int oferta, String nombre, UsuarioPublico usP) {
		int id = conseguirIdJugador(nombre);
		int ofertaMasAlta = conseguirMayorOferta(id);

		if (oferta > ofertaMasAlta) {
			try (PreparedStatement stmt = conn
					.prepareStatement("UPDATE mercado SET ofertaMasAlta=?, idUsuario=? WHERE idJugador = '" + id
							+ "' AND idLiga = " + usP.getIdLiga())) {
				stmt.setInt(1, oferta);
				stmt.setString(2, usP.getUsuario());
				stmt.executeUpdate();
			} catch (SQLException e) {
				System.out.format("Error actualizando oferta", e);
				e.printStackTrace();
			}
		}
	}

	public void updateJugador() {
		List<Oferta> ofertas = new ArrayList<Oferta>();
		List<UsuarioPublico> dinero = new ArrayList<UsuarioPublico>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"SELECT idLiga, idJugador, ofertaMasAlta, idUsuario FROM Mercado where ofertaMasAlta is not null");

			while (rs.next()) {
				int idLiga = rs.getInt("idLiga");
				int idjug = rs.getInt("idJugador");
				int oferta = rs.getInt("ofertaMasAlta");
				String us = rs.getString("idUsuario");
				Oferta ofer = new Oferta(us, "", idjug, oferta, idLiga);
				ofertas.add(ofer);

			}

		} catch (SQLException e) {
			System.out.format("Error actualizando compra de jugadores", e);
			e.printStackTrace();
		}

		try (PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO jugadorenliga (nombreUsuario, idJugador, idLiga, titular) VALUES (?,?,?,?)")) {
			for (int i = 0; i < ofertas.size(); i++) {
				stmt.setString(1, ofertas.get(i).getNombreUsuario());
				stmt.setInt(2, ofertas.get(i).getIdJugador());
				stmt.setInt(3, ofertas.get(i).getIdLiga());
				stmt.setBoolean(4, false);
				stmt.executeUpdate();

			}

		} catch (SQLException e) {
			System.out.format("Error creando mercado", e);
			e.printStackTrace();
		}

		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT nombreDeUsuario, dineroDisponible FROM usuario");

			while (rs.next()) {
				int dineroDis = rs.getInt("dineroDisponible");
				String nombre = rs.getString("nombreDeUsuario");
				UsuarioPublico us = new UsuarioPublico(nombre, "", 0, 0, dineroDis, 0);
				dinero.add(us);
			}
		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
			e.printStackTrace();
		}

		for (int i = 0; i < dinero.size(); i++) {

			for (int j = 0; j < ofertas.size(); j++) {
				if (dinero.get(i).getUsuario().equals(ofertas.get(j).getNombreUsuario())) {
					int valor = dinero.get(i).getDineroDisponible() - ofertas.get(j).getOferta();
					dinero.get(i).setDineroDisponible(valor);
				}
			}
		}
		for (int i = 0; i < dinero.size(); i++) {
			try (PreparedStatement stmt = conn
					.prepareStatement("UPDATE usuario SET dineroDisponible=? WHERE nombreDeUsuario = '"
							+ dinero.get(i).getUsuario() + "'")) {
				stmt.setInt(1, dinero.get(i).getDineroDisponible());
				stmt.executeUpdate();
			} catch (SQLException e) {
				System.out.format("Error actualizando oferta", e);
				e.printStackTrace();
			}
		}

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

		try (PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO jugadorenliga (nombreUsuario, idJugador, idLiga, titular) VALUES (?,?,?,?)")) {

			int contadorTitularPor = 0;
			int contadorTitularDef = 0;
			int contadorTitularMed = 0;
			int contadorTitularDel = 0;
			for (int i = 0; i < jug.size(); i++) {
				stmt.setString(1, nombreUsuario);
				stmt.setInt(2, jug.get(i).getIdJugador());
				stmt.setInt(3, idLiga);

				String posicion = jug.get(i).getPosicion();
				switch (posicion) {
				case "Por":
					if (contadorTitularPor < 1) {
						stmt.setBoolean(4, true);
						contadorTitularPor = contadorTitularPor + 1;
						break;
					} else {
						stmt.setBoolean(4, false);
						break;
					}
				case "Def":
					if (contadorTitularDef < 4) {
						stmt.setBoolean(4, true);
						contadorTitularDef = contadorTitularDef + 1;
						break;
					} else {
						stmt.setBoolean(4, false);
						break;
					}
				case "Med":
					if (contadorTitularMed < 4) {
						stmt.setBoolean(4, true);
						contadorTitularMed = contadorTitularMed + 1;
						break;
					} else {
						stmt.setBoolean(4, false);
						break;
					}
				case "Del":
					if (contadorTitularDel < 3) {
						stmt.setBoolean(4, true);
						contadorTitularMed = contadorTitularMed + 1;
						break;
					} else {
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

	public List<Jugador> crearListaPlantilla(UsuarioPublico usP) {

		List<Jugador> jug = new ArrayList<Jugador>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"SELECT Jugadores.idJugador, nombreJugador, valor, posicion, equipo, puntos, nombreUsuario, titular FROM Jugadores JOIN jugadorenliga ON Jugadores.idJugador = jugadorenliga.idJugador where nombreUsuario = '"
							+ usP.getUsuario() + "'");

			while (rs.next()) {
				int idJugador = rs.getInt("idJugador");
				String nombreJugador = rs.getString("nombreJugador");
				int valor = rs.getInt("valor");
				String posicion = rs.getString("posicion");
				String equipo = rs.getString("equipo");
				int puntos = rs.getInt("puntos");
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
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE jugadorenliga SET titular=? WHERE nombreUsuario = '"
				+ usP.getUsuario() + "' AND idJugador ='" + jug.getIdJugador() + "'")) {

			stmt.setBoolean(1, jug.isTitular());
			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.format("Error actualizando jugador", e);
			e.printStackTrace();
		}
	}

	public Map<String, Integer> crearListaJugadoresValor() {
		Map<String, Integer> jug = new HashMap<String, Integer>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT nombreJugador, valor FROM Jugadores");

			while (rs.next()) {
				String nombreJugador = rs.getString("nombreJugador");
				int valor = rs.getInt("valor");
				jug.put(nombreJugador, valor);

			}
			return jug;
		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, Integer> crearListaUsuariosPuntos() {
		Map<String, Integer> usu = new HashMap<String, Integer>();

		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT nombreDeUsuario, puntos FROM usuario");

			while (rs.next()) {
				String nombreUsuario = rs.getString("nombreDeUsuario");
				int puntos = rs.getInt("puntos");
				usu.put(nombreUsuario, puntos);

			}
			return usu;
		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, Integer> crearListaUsuariosDinero() {
		Map<String, Integer> usu = new HashMap<String, Integer>();

		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT nombreDeUsuario, dineroDisponible FROM usuario");

			while (rs.next()) {
				String nombreUsuario = rs.getString("nombreDeUsuario");
				int dinero = rs.getInt("dineroDisponible");
				usu.put(nombreUsuario, dinero);

			}
			return usu;
		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
			e.printStackTrace();
		}
		return null;
	}

	public List<String> crearListaJugadoresNombre() {
		List<String> jug = new ArrayList<String>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT nombreJugador FROM Jugadores");

			while (rs.next()) {
				String nombreJugador = rs.getString("nombreJugador");
				jug.add(nombreJugador);

			}
			return jug;
		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
		}
		return null;
	}

	public List<String> crearListaUsuariosNombre() {
		List<String> usu = new ArrayList<String>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT nombreDeUsuario FROM usuario");

			while (rs.next()) {
				String nombreUsuario = rs.getString("nombreDeUsuario");
				usu.add(nombreUsuario);

			}
			return usu;
		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
		}
		return null;
	}

	public void updateValorJugadores(int id, int valor) {
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE jugadores SET valor = ? WHERE idJugador = " + id)) {

			stmt.setInt(1, valor);

			stmt.executeUpdate();

			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.format("No se puedo actualizar el valor del jugador");
		}
	}

	public void updatePuntosUsuarios(int id, int puntos) {
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE usuario SET puntos = ? WHERE idUsuario = " + id)) {

			stmt.setInt(1, puntos);

			stmt.executeUpdate();

			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.format("No se puedo actualizar los puntos del usuario");
		}
	}

	public void updateDineroUsuarios(int id, int dinero) {
		try (PreparedStatement stmt = conn
				.prepareStatement("UPDATE usuario SET dineroDisponible = ? WHERE idUsuario = " + id)) {

			stmt.setInt(1, dinero);

			stmt.executeUpdate();

			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.format("No se pudo actualizar el dinero del usuario");
		}
	}

	public DefaultListModel<String> implementacionHilo() {
		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt
					.executeQuery("SELECT nombreJugador, valor FROM jugadores ORDER BY valor DESC LIMIT 5 ");
			DefaultListModel<String> model = new DefaultListModel<>();
			while (resultSet.next()) {
				model.addElement(resultSet.getString("nombreJugador"));
				model.addElement(resultSet.getString("valor"));
			}
			return model;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Integer> sacarpuntos() {
		List<Integer> arr = new ArrayList<Integer>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT puntos FROM usuario");

			while (rs.next()) {
				int puntos = rs.getInt("puntos");
				arr.add(puntos);

			}
			return arr;
		} catch (SQLException e) {
			System.out.format("Error creando lista", e);
		}

		return null;

	}

	public Connection getConn() {
		return conn;
	}

}
