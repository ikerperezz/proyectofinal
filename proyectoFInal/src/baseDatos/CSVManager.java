package baseDatos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import clases.Administrador;
import interfaces.IManejoDeDatos;

public class CSVManager implements IManejoDeDatos {

	public List<Administrador> crearLista() {

		List<Administrador> admins = new ArrayList<Administrador>();

		try (BufferedReader reader = new BufferedReader(new FileReader("src/ficheroExterno/Administradores.csv"))) {
			reader.readLine();
			String line = null;

			while ((line = reader.readLine()) != null) {
				String[] tokens = line.split(";");
				String nombreUsuario = tokens[0];

				String contraseina = tokens[1];

				admins.add(new Administrador(nombreUsuario, contraseina));

			}

			reader.close();

		} catch (Exception e) {
			System.out.format("Error creando lista", e);
			e.printStackTrace();
		}

		return admins;

	}

}
