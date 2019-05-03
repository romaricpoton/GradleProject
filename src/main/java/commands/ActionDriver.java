package commands;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.MediaEntityBuilder;


import config.StartBrowser;

public class ActionDriver {

	WebDriver driver;

	/*
	 * constructeur de la class ActionDriver elle fait appel au Webdriver
	 */
	public ActionDriver() {

		driver = StartBrowser.driver;
	}

	/*
	 * method permettant d'ajouter au navigateur l'url
	 * 
	 * @param url Elle ecrit les logs dans le ExtentReport en fonction de la reponse
	 * de l'action
	 */
	public void navigateToApplication(String url) {

		try {
			driver.get(url);
			StartBrowser.childTest.pass("Succesfully nagivated to : " + url);
		} catch (Exception e) {
			StartBrowser.childTest.fail("Unable to nagivated to : " + url);
		}

	}

	/*
	 * method permettant de faire un clique
	 * 
	 * @param locator : l'objet sur laquel on clique
	 * 
	 * @param elemNam : le nom de l'objet que l'utilisateur ajout pour se retrouvé
	 * dans les logs.Elle ecrit également les logs dans le ExtentReport en fonction
	 * de la reponse de l'action. Elle prend aussi la capture d'écran aucas ou
	 * l'élement n'est pas retrouvé en ajoutant l'erreur comme nom a la capture
	 * d'ecran
	 */
	public void click(By locator, String elemNam) throws Exception {

		try {
			driver.findElement(locator).click();
			StartBrowser.childTest.pass("Succesfully clicked on : " + elemNam);
		} catch (Exception e) {
			StartBrowser.childTest.fail("Unable to click on : " + elemNam,
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
			StartBrowser.childTest.info(e);
			throw e;
		}
	}

	/*
	 * method permettant d'ecrire du texte dans un textBox
	 * 
	 * @param locator : l'objet sur laquel on envoi du texte
	 * 
	 * @param elemNam : le nom de l'objet que l'utilisateur ajout pour se retrouvé
	 * dans les logs.Elle ecrit également les logs dans le ExtentReport en fonction
	 * de la reponse de l'action. Elle prend aussi la capture d'écran aucas ou
	 * l'élement n'est pas retrouvé en ajoutant l'erreur comme nom a la capture
	 * d'ecran
	 */
	public void type(By locator, String text, String elemNam) throws Exception {

		try {
			driver.findElement(locator).sendKeys(text);
			StartBrowser.childTest.pass("Succesfully typed in : " + elemNam + " with data : " + text);
		} catch (Exception e) {
			StartBrowser.childTest.fail("Unable type in : " + elemNam + " with data : " + text,
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
			StartBrowser.childTest.info(e);
			throw e;

		}

	}

	/*
	 * method permettant de prendre la capture d'ecran sous format BASE64 Elle est
	 * utiliser dans les logs pour extentReport
	 */
	public String screenShot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

	}
}
