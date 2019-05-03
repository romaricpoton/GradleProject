package scripts;

import org.testng.annotations.Test;

import config.StartBrowser;
import fonctions.Application;
//import util.MyScreenRecorder;
import util.MyScreenRecorder;

public class TestExamples extends StartBrowser {

	/*
	 * Lance le text en enregistrant les actions 
	 * Fait appel au méthodes specifier
	 * dans les actions (Applications)
	 */
	@Test
	public void testLogin_Logout() throws Exception {
		MyScreenRecorder.startRecording("VideoTest");
		Application Application = new Application();
		Application.Login();
		MyScreenRecorder.stopRecording();
	}
}