package clases;

import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.io.FileInputStream;
import java.io.IOException;

public class LoggerSeguimiento {

	private static Logger logger = Logger.getLogger(LoggerSeguimiento.class.getName());

	public static void main(String[] args) {
		FileHandler handler = null;
		try {
			handler = new FileHandler("seguimiento.log");
		}catch (IOException e1) {
			e1.printStackTrace();
		}
		logger.addHandler(handler);

		try (FileInputStream fis = new FileInputStream("logger.properties")) {
			LogManager.getLogManager().readConfiguration(fis);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "No se pudo leer el fichero de configuración del logger");
		}

		logger.info("Programa comenzado");

		for (int i = 0; i < 10; i++) {
			logger.log(Level.FINE, "Voy por la iteración " + i);
		}

		logger.info("Programa finalizado");
	}
}
