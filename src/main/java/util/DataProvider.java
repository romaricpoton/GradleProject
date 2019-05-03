package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataProvider {

	static Workbook book;
	static org.apache.poi.ss.usermodel.Sheet sheet;
	public static String TestDATA_SHEET_PATH = "";

	/*
	 * @param sheetName : le nom de la feuille de calcul
	 * 
	 * @param TestDATA_SHEET_PATH : variable d'environnement indiquant le chemin du
	 * fichier Excel. la fonction donne acces au données contenant une feuille de
	 * calcule et un tableau a deux colonnes.
	 */
	public static Object[][] getTestData(String sheetName) {

		FileInputStream file = null;
		try {

			file = new FileInputStream(TestDATA_SHEET_PATH);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);

		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}
}
