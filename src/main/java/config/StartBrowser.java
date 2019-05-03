package config;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import util.ConfiguratorSupport;

public class StartBrowser {

	public static WebDriver driver;
	ExtentHtmlReporter htmlreporter;
	public static ExtentReports extent;
	public static ExtentTest parentTest;
	public static ExtentTest childTest;
	/*
	 * Ouvrir une instance puis indiquer le fichier ou il va récupéré les données
	 */
	ConfiguratorSupport cs = new ConfiguratorSupport("PropertiesFiles/config.properties");

	/*
	 * Avant tous test préparer le fichier de rapport en indiquant le chemin ou
	 * ExtentReport conserve le rapport sous format html.
	 */
	@BeforeTest
	public void report() {
		htmlreporter = new ExtentHtmlReporter("Reports/MyResult.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
	}

	/*
	 * Avant tout method recupere la nom de la method qui sera ecrire dans le
	 * rapport
	 */
	@BeforeMethod
	public void method(Method method) {

		parentTest = extent.createTest(method.getName());

	}

	/*
	 * Avant apres la class lancé le navigateur selon celui specifier dans lefichier
	 * properties
	 */
	@BeforeClass
	public void beforeClass() {

		if (cs.getProperty("browser").contentEquals("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		} else if (cs.getProperty("browser").contentEquals("firefox")) {
			WebDriverManager.firefoxdriver().arch64().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} else {
			System.out.println("Provide a valid browser");
		}
	}

	/*
	 * Après l'execution de la class de test quitté le navigateur enregistrer les
	 * resultats dans extentReport
	 */

	@AfterClass
	public void afterClass() {
		driver.quit();
		extent.flush();

	}
}