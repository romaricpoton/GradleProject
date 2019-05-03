package fonctions;

import org.openqa.selenium.WebDriver;
import PagesObjectModele.IDs;
import commands.ActionDriver;
import config.StartBrowser;
import util.ConfiguratorSupport;

public class Application {
	WebDriver driver;
	ActionDriver aDriver;
	ConfiguratorSupport cs = new ConfiguratorSupport("PropertiesFiles/data.properties");

	public Application() {
		driver = StartBrowser.driver;
		aDriver = new ActionDriver();

	}

	/*
	 * La method permet de récupere les donnée depuis une fichier Excel
	 * 
	 * @parametre sheetName = la feuille de calcule contenant les données Ici il
	 * s'agit d'un tableau de deux(2) colonnes contenant le Email et mots de Passe
	 * Si on veux l'utiliser dans le cas d'espèce, il faudrais ajouter des
	 * paramètres a la method login c'est-a-dire Login(String email, String
	 * password) puis passé les paramètres a textUserName et a textPassword; cet cas
	 * est utiliser pour les jeux de donnée.
	 * 
	 * @DataProvider public Object[][] getLoginData(){ Object[][] data =
	 * util.DataProvider.getTestData("sheetName"); return data; }
	 */
	public void Login() throws Exception {

		StartBrowser.childTest = StartBrowser.parentTest.createNode("Login to Facebook");
		aDriver.navigateToApplication(cs.getProperty("url"));
		aDriver.type(IDs.textUserName, cs.getProperty("email"), "UserName");
		aDriver.type(IDs.textPassword, cs.getProperty("password"), "Password");
		aDriver.click(IDs.BtnLogin, "Login button");

	}
}