package Test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Paginas.CarritoPop;
import Paginas.Principal;
import Paginas.Producto;
import Reporte.ReporteER;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import Variables.Variables;

public class AppTest extends Variables
{
	@BeforeTest
	private void IniciarNavegador() throws Exception {
		System.setProperty("webdriver.chrome.driver", Variables.driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(Variables.url);
		ReporteER.CreateReporterER();
		ReporteER.LogReportER("URL - Se accedio correctamente a la ruta definida", 1, "Paso exitoso");
	}
	
	@Test
	private void ejecucion() throws Exception {
		Principal.BuscarProducto();
		Producto.AgregarCarrito();
		CarritoPop.ValidarCarrito();
	}
	
	@AfterTest
	private void CerrarProcesos() throws Exception {
		try {
			ReporteER.FinishReporterER();
			Runtime.getRuntime().exec("TASKKILL /F /IM chromedriver.exe");
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
