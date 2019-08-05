package Variables;

import org.openqa.selenium.WebDriver;

public class Variables {

	// Variables de configuración
	public static String driverPath = System.getProperty("user.dir") + "\\src\\chromedriver.exe";
	public static WebDriver driver = null;
	public static String pagPrincipal = ""; // ubicacion de la pagina principal
	
	// Variables de entrada
	public static String url = "https://www.adidas.co/";
	public static String busqueda = "energy cloud";
	public static String talla = "US 9"; // "L";
	public static String unidades = "3";
	
	// Variables de almacenamiento
	public static String descrp = "";
	public static String precio = "";
	public static String total = "";
	
}
