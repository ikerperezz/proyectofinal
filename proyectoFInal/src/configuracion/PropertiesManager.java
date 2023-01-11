package configuracion;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

public static int saldoInicial() {
	int saldo=0;
	
	try(FileReader reader = new FileReader("src/configuracion/configSaldoInicial.properties")){
		Properties properties = new Properties();
		properties.load(reader);	
		saldo = Integer.parseInt(properties.getProperty("saldoInicial"));
	} catch (IOException e) {
		System.out.println("Error con el archivo de properties");
		e.printStackTrace();
	}
	
	
	
	
	return saldo;
}	

}
