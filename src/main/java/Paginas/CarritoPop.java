package Paginas;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Reporte.ReporteER;
import Variables.Variables;

public class CarritoPop extends Variables {
	
	// validar el mensaje de exito del carrito
	public static By lblExitoCarrito = By.xpath("//h5[contains(@class, 'gl-modal__title gl-heading--m')]");
	
	// objetos a almacenar para informe
	public static By lblProducto = By.xpath("//h5[contains(@class, 'gl-body--s')]"); // descripcion producto agregado
	public static By lblCosto = By.xpath("//div[contains(@class, 'product-price___2gKs9')]/div[contains(@class, 'gl-price')]/span[contains(@class, 'gl-price__value')]"); // costo producto agregado
	public static By lblTalla = By.xpath("//p/span[contains(text(),'Talla')]"); // talla del producto
	public static By lblCantidad = By.xpath("//p/span[contains(text(),'Cantidad')]"); // cantidad
	
	public static By lblCostoTAdd = By.xpath("//div[contains(@class, 'price-row___3Kw5S')][1]/div[contains(@class, 'gl-price')]/span[contains(@class, 'gl-price__value')]"); // costo total compra
	public static By lblCostoEnvioAdd = By.xpath("//div[contains(@class, 'price-row___3Kw5S')][1]/div[contains(@class, 'gl-price')]/span[contains(@class, 'gl-price__value')]"); // costo envio
	public static By lblTotal = By.xpath("//strong/div[contains(@class, 'gl-price')]/span[contains(@class, 'gl-price__value')]"); // total compra
	public static By btnContinuar = By.xpath("//a[contains(@class, 'gl-cta gl-cta--secondary gl-cta--full-width')]"); // boton continuar
	
	public static String [] datos = new String [7];
	
	public static void ValidarCarrito () throws Exception {
		try {
			WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(lblExitoCarrito));
			if (driver.findElements(lblExitoCarrito).size() != 0) {
				
				ReporteER.LogReportER("Carrito - Se logro acceder a la pagina de carrito despues de agregar un producto.", 1, "Paso exitoso");
				datos[0] = driver.findElement(lblProducto).getText();
				datos[1] = driver.findElement(lblCosto).getText();
				datos[2] = driver.findElement(lblTalla).getText();
				datos[2] = datos[2].replace("Talla: ", "");
				datos[3] = driver.findElement(lblCantidad).getText();
				datos[3] = datos[3].replace("Cantidad: ", "");
				datos[4] = driver.findElement(lblCostoTAdd).getText();
				datos[5] = driver.findElement(lblCostoEnvioAdd).getText();
				datos[6] = driver.findElement(lblTotal).getText();
				
				if (datos[0].equals(descrp) && datos[2].equals(talla) && datos[3].equals(unidades)) {
					ReporteER.LogReportER("Carrito - Se valido correctamente los sgtes datos: " + datos[0] + "Precio: " + datos[1] + 
							", Talla: " + datos[2] + ", Cantidad: " + datos[3] + ", Costo producto(s): " + datos[4] + ", Costo Envio: " +
							datos[5] + ", Valor Total: " + datos[6], 1, "Paso exitoso");
					driver.findElement(btnContinuar).click();
				} else {
					ReporteER.LogReportER("Carrito - Hubo un error al validar los valores del producto: " + datos[0] + "Precio: " + datos[1] + 
					", Talla: " + datos[2] + ", Cantidad: " + datos[3] + ", Costo producto(s): " + datos[4] + ", Costo Envio: " + 
					datos[5] + ", Valor Total: " + datos[6], 2, "Paso fallido");
				}
				
			} else {
				ReporteER.LogReportER("Carrito - Hubo un error al acceder a la pagina de carrito despues de agregar un producto.", 2, "Paso fallido");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
