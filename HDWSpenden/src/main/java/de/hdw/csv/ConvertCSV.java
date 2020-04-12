package de.hdw.csv;

import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;

public class ConvertCSV {


	   @SuppressWarnings("resource")
	   public void parseFullCSVExample() throws Exception
	   {
	      //Build reader instance
	      @SuppressWarnings("deprecation")
		CSVReader reader = new CSVReader(new FileReader("D:/hdw/Umsaetze-2019/Dez-2019.csv"), ';', '"', 1);
	       
	      //Read all rows at once
	      List<String[]> allRows = reader.readAll();
	       
	      //Read CSV line by line and use the string array as you want
	     for(String[] row : allRows){
	        System.out.println(row[1]);		//Buchungstag
	        System.out.println(row[3]);		//Buchungstext
	        System.out.println(row[4]);		//Verwendungszweck
	        System.out.println(row[11]);	//Beguenstigter/Zahlungspflichtiger
	        System.out.println(row[12]);	//Kontonummer/IBAN
	        System.out.println(row[13]);	//BIC (SWIFT-Code)
	        System.out.println(row[14]);	//Betrag
	        System.out.println(row[15]);	//Waehrung
	        System.out.println(row[16]);	//Info
	     }
	   }
}
