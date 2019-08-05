package Reporte;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Variables.Variables;

public class ReporteER extends Variables {

	public static ExtentTest testER;
	public static ExtentReports reporteER;
	public static String pathER;
	
	public static void CreateReporterER() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		pathER = System.getProperty("user.dir") + "\\Reportes\\Ejecucion-" + timeStamp + "\\";
		File Dir = new File(pathER);
		try {
			Dir.mkdir();
		} catch (Exception e) {
			
		}
		reporteER = new ExtentReports(pathER + "Reporte de ejecución.html");
		testER = reporteER.startTest("Reporte de ejecucion");
	}
	
	public static void LogReportER(String message, int status, String msgScreen) throws IOException {
		switch (status) {
		case 1:
			testER.log(LogStatus.PASS, message, testER.addScreenCapture(screenCaptureRE(driver)) + msgScreen);
			break;
		case 2:
			testER.log(LogStatus.FATAL, message, testER.addScreenCapture(screenCaptureRE(driver)) + msgScreen);
			break;
		case 3:
			testER.log(LogStatus.INFO, message, testER.addScreenCapture(screenCaptureRE(driver)) + msgScreen);
			break;
		}
	}
	
	public static String screenCaptureRE(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		File Dest = new File (pathER + timeStamp + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
	}
	
	public static void FinishReporterER() {
		reporteER.endTest(testER);
		reporteER.flush();
	}
	
}
