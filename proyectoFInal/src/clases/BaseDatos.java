package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatos {

	public static void main(String[] args){
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
		}
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:/src/baseDatos/baseDatosProyecto.db");
			
			Statement stmt = conn.createStatement();
		
			
			ResultSet rs = stmt.executeQuery("SELECT idUsuario, contraseña from usuario");
			ResultSet rs1 = stmt.executeQuery("SELECT idJugador, nombreDeJugador from jugadorenliga");
			ResultSet rs2 = stmt.executeQuery("SELECT idJugador, nombreJugador from jugadores");
			
			while(rs.next()) {
				
				String idUsuario = rs.getString("idUsuario");
				String contraseña = rs.getString("contraseña");
				System.out.println(idUsuario);
				System.out.println(contraseña);
			}
				
			while(rs1.next()) {
					
					String idJugador = rs1.getString("idJugador");
					String nombreDeJugador = rs1.getString("nombreDeJugador");
					System.out.println(idJugador);
					System.out.println(nombreDeJugador);
			}
			
			while(rs2.next()) {
				
				String idJugador = rs1.getString("idJugador");
				String nombreJugador = rs1.getString("nombreJugador");
				System.out.println(idJugador);
				System.out.println(nombreJugador);
				
			}
			rs.close();
			rs1.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("No se ha podido establecer la conexión a la base de datos");
			System.out.println(e.getMessage());
		}

	}
}
