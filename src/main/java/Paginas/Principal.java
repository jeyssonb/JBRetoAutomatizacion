package Paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Reporte.ReporteER;
import Variables.Variables;

public class Principal extends Variables
{
    
	private static By lblUltra = By.xpath("//div[@class=\"content___3Uzc5\"]/div[2]/h1[contains(text(),'ULTRABOOST 19')]");
	private static By txtBuscar = By.xpath("//input[contains(@class, 'searchinput___3ump9')]");
	private static By btnBuscar = By.xpath("//div[contains(@class, 'search-icon___izpuX')]");
	private static By btnResult1 = By.xpath("//li[contains(@class, 'product___3VZEy')][1]/a");
	
	public static void BuscarProducto() {
		try {
			if (driver.findElements(lblUltra).size() != 0) {
				pagPrincipal = driver.getWindowHandle(); // definir la ubicación inicial
				driver.findElement(txtBuscar).sendKeys(busqueda);
				driver.findElement(btnBuscar).click();
				ReporteER.LogReportER("Consulta de producto - Se consulto correctamente el producto: " + busqueda + ".", 1, "Paso exitoso");
				WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(btnResult1));
				if (driver.findElements(btnResult1).size() != 0) {
					ReporteER.LogReportER("Acceso a producto - Se accedio al primer resultado retornado de la consulta generada.", 1, "Paso exitoso");
					driver.findElement(btnResult1).click();
				} else {
					ReporteER.LogReportER("Acceso a producto - Hubo un error al acceder al resultado del producto consultado.", 2, "Paso fallido");
				}
			} else {
				ReporteER.LogReportER("Consulta de producto - Hubo un error al consultar el producto: " + busqueda + "en la página seleccionada.", 2, "Paso fallido");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
