package Paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Reporte.ReporteER;
import Variables.Variables;

public class Producto extends Variables {

	public static By descrpProd = By.xpath("//h1[@data-auto-id='product-title']");
	public static By precioProd = By.xpath("//div[contains(@class, 'product_information___1Tt1L gl-vspacing-m')]/div/span[contains(@class, 'gl-price__value')]");
	
	public static By btnTallas = By.xpath("//div/button[./span/span[text() = 'Elige tu talla']]");
	public static String btnValorTalla = "//li[contains(@class, 'gl-menu__item')]/button[text() = 'TALLA']";
	
	public static By btnCantidad = By.xpath("//div[contains(@class, 'gl-dropdown gl-dropdown--small')]/button[contains(@class, 'gl-dropdown__select label dropdown-select')]");
	public static String btnValCantidad = "//li[contains(@class, 'gl-dropdown__item')]/button[text() = 'UNIDADES']";
	
	public static By btnAddCarrito = By.xpath("//button[contains(@class, 'gl-cta gl-cta--primary gl-cta--full-width btn-bag')]");
	
	public static void AgregarCarrito() {
		try {
			WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(descrpProd));
			if (driver.findElements(descrpProd).size() != 0 && driver.findElements(precioProd).size() != 0) {
				descrp = driver.findElement(descrpProd).getText();
				precio = driver.findElement(precioProd).getText();
				ReporteER.LogReportER("Descripcion de producto - Se accedio exitosamente al producto: " + descrp + " con valor: " + precio + ".", 1, "Paso exitoso");
				
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,500)");
				
				driver.findElement(btnTallas).click();
				ReporteER.LogReportER("Tallas producto - Almacenando las tallas habilitadas.", 1, "Paso exitoso");
				btnValorTalla = btnValorTalla.replace("TALLA", talla);
				if (driver.findElements(By.xpath(btnValorTalla)).size() != 0) {
					driver.findElement(By.xpath(btnValorTalla)).click();
					ReporteER.LogReportER("Tallas producto - La talla se selecciono correctamente.", 1, "Paso exitoso");
					
					Thread.sleep(2000);
					driver.findElement(btnCantidad).click();
					ReporteER.LogReportER("Cantidad producto - Almacenando las cantidades habilitadas.", 1, "Paso exitoso");
					
					btnValCantidad = btnValCantidad.replace("UNIDADES", unidades);
					if (driver.findElements(By.xpath(btnValCantidad)).size() != 0) {
						driver.findElement(By.xpath(btnValCantidad)).click();
						ReporteER.LogReportER("Cantidad producto - La cantidad se selecciono correctamente.", 1, "Paso exitoso");
						
						Thread.sleep(2000);
						driver.findElement(btnAddCarrito).click();
					} else {
						ReporteER.LogReportER("Cantidad producto - Hubo un error en la seleccion de cantidad del producto.", 2, "Paso fallido");
					}
				} else {
					ReporteER.LogReportER("Tallas producto - Hubo un error en la seleccion de talla del producto.", 2, "Paso fallido");
				}
			} else {
				ReporteER.LogReportER("Descripcion de producto - Hubo un error en el direccionamiento a la pagina definida.", 2, "Paso fallido");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
