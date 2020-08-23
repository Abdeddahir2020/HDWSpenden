package de.hdw.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import de.hdw.constants.HDWConstants;
import de.hdw.dao.KostenDAO;
import de.hdw.dao.SpenderDAO;
import de.hdw.dao.SammelLastSchriftDAO;
import de.hdw.model.Kosten;
import de.hdw.model.Spenden;
import de.hdw.model.SammelLastSchrift;
import de.hdw.model.Spender;
import de.hdw.utils.HDWUtils;

@Service
public class FileUploadService {

	@Autowired
	SpenderDAO spenderDAO;

	@Autowired
	SammelLastSchriftDAO spenderSammelLastSchriftDAO;

	@Autowired
	KostenDAO kostenDAO;

	public List<Spender> uploadFile(MultipartFile multipartFile)
			throws IOException, NumberFormatException, ParseException {

		File file = convertMultiPartToFile(multipartFile);

		// parse CSV file to create a list of `User` objects
		
		try (@SuppressWarnings("deprecation")
		CSVReader reader = new CSVReader(new FileReader(file), ';', '"', 1)) {
			

			// read line by line

			List<Spender> spdList = new ArrayList<>();
//			List<Kosten> kstList = new ArrayList<>();
//			List<SammelLastSchrift> sLTSList = new ArrayList<>();
			
			List<Spenden> spendenList = new ArrayList<>();

			// Read all rows at once
			List<String[]> allRows = reader.readAll();

			// Read CSV line by line and use the string array as you want
			for (String[] row : allRows) {
				
				if(row[3] != null && row[3].contains("SAMMEL-LS-EINZUG"))
				{
					Spenden spenden = new SammelLastSchrift();
					
//					sltsch.setBuchungstag(HDWUtils.convertStringToTimestamp(row[1])); // Buchungstag
//					sltsch.setBuchungstext(row[3]); // Buchungstext
//					sltsch.setBic(row[13]); // BIC (SWIFT-Code)
//					sltsch.setBetrag(Double.parseDouble(row[14].replace(",","."))); // Betrag
//					sltsch.setWaehrung(row[15]); // Waehrung
					
					spenden = setzeSpenden(row, spenden);
					
					if(spenden.getSpender() != null) {
						spenden.getSpender().setName(row[11]); // Beguenstigter/Zahlungspflichtiger
						spenden.getSpender().setSpenderIban(UUID.fromString(row[12])); // Kontonummer/IBAN
					}
					
					spendenList.add(spenden);
							
				}
				else if(row[14] != null && HDWUtils.isNegative(Double.parseDouble(row[14].replace(",","."))))
				{
					Kosten kosten = new Kosten();
					//KostenType
					if(row[11] != null && row[11].equals(HDWConstants.MIETE))
						kosten.setKostenType("Miete");
					
					else if(row[11] != null && row[11].contains(HDWConstants.TELEFONINTERNET))
						kosten.setKostenType("Telefon und Internet");
					
					else if(row[3] != null && row[3].equals(HDWConstants.ENTGELTABSCHLUSS))
						kosten.setKostenType("Entgeltabschluss");
					
//					kst.setBuchungstag(HDWUtils.convertStringToTimestamp(row[1])); // Buchungstag
//					kst.setBuchungstext(row[3]); // Buchungstext
//					kst.setVerwendungszweck(row[4]); // Verwendungszweck
//					kst.setBic(row[13]); // BIC (SWIFT-Code)
//					kst.setBetrag(Double.parseDouble(row[14].replace(",","."))); // Betrag
//					kst.setWaehrung(row[15]); // Waehrung
					
					kosten = (Kosten) setzeSpenden(row, kosten);
					
					if(kosten.getSpender() != null) {
						kosten.getSpender().setName(row[11]); // Beguenstigter/Zahlungspflichtiger
						kosten.getSpender().setSpenderIban(UUID.fromString(row[12])); // Kontonummer/IBAN
					}
					
					spendenList.add(kosten);
				}
				
				else
				{
					Spender spender= new Spender();
					
					Set<String> ibanSet = new HashSet<>();
					
					if(!ibanSet.contains(row[12])) {
						Spenden spenden = new Spenden();
						
//						spenden.setBuchungstag(HDWUtils.convertStringToTimestamp(row[1])); // Buchungstag
//						spenden.setBuchungstext(row[3]); // Buchungstext
//						spenden.setVerwendungszweck(row[4]); // Verwendungszweck
//						spenden.setBic(row[13]); // BIC (SWIFT-Code)
//						spenden.setBetrag(Double.parseDouble(row[14].replace(",","."))); // Betrag
//						spenden.setWaehrung(row[15]); // Waehrung
						
						spenden =setzeSpenden(row, spenden);
						
						spender.setName(row[11]); // Beguenstigter/Zahlungspflichtiger
						spender.setSpenderIban(UUID.fromString(row[12])); // Kontonummer/IBAN
						
						spender.getSpendenList().add(spenden);
					}
					else {
						for(Spender spd : spdList) {
							if(row[12].equals(spd.getSpenderIban())) {
								Spenden spenden = new Spenden();
								
//								spenden.setBuchungstag(HDWUtils.convertStringToTimestamp(row[1])); // Buchungstag
//								spenden.setBuchungstext(row[3]); // Buchungstext
//								spenden.setVerwendungszweck(row[4]); // Verwendungszweck
//								spenden.setBic(row[13]); // BIC (SWIFT-Code)
//								spenden.setBetrag(Double.parseDouble(row[14].replace(",","."))); // Betrag
//								spenden.setWaehrung(row[15]); // Waehrung
								
								spenden = setzeSpenden(row, spenden);
								
								spd.setName(row[11]); // Beguenstigter/Zahlungspflichtiger
								spd.setSpenderIban(UUID.fromString(row[12])); // Kontonummer/IBAN
								
								spd.getSpendenList().add(spenden);
							}
						}
					}
					
					ibanSet.add(row[12]);
					spdList.add(spender);
				}
			}
			
			spenderDAO.saveAllSpender(spdList);
//			kostenDAO.saveAllKosten(kstList);
//			spenderSammelLastSchriftDAO.saveAllSpenderSammelLastSchrift(sLTSList);
		}return null;

	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	private Long convertIbanToLong(String iban) {
		String subIban = iban != null ? iban.substring(2) : null;

		return subIban != null ? Long.parseLong(subIban) : new Long(0);
	}

	private Spenden setzeSpenden(String[] row, Spenden spenden) {
		
		spenden.setBuchungstag(HDWUtils.convertStringToTimestamp(row[1])); // Buchungstag
		spenden.setBuchungstext(row[3]); // Buchungstext
		spenden.setVerwendungszweck(row[4]); // Verwendungszweck
		spenden.setBic(row[13]); // BIC (SWIFT-Code)
		spenden.setBetrag(Double.parseDouble(row[14].replace(",","."))); // Betrag
		spenden.setWaehrung(row[15]); // Waehrung
		
		spenden.setSpendenMonat(HDWUtils.getMonat(spenden.getBuchungstag()));
		spenden.setSpendenJahr(HDWUtils.getJahr(spenden.getBuchungstag()));
		
		return spenden;
	}

//	@SuppressWarnings("resource")
//	public void parseFullCSVExample(MultipartFile multipartFile) throws Exception {
//		File file = convertMultiPartToFile(multipartFile);
//
//		// Build reader instance
//		@SuppressWarnings("deprecation")
//		CSVReader reader = new CSVReader(new FileReader(file), ';', '"', 1);
//
//		// Read all rows at once
//		List<String[]> allRows = reader.readAll();
//
//		// Read CSV line by line and use the string array as you want
//		for (String[] row : allRows) {
//			System.out.println(row[1]); // Buchungstag
//			System.out.println(row[3]); // Buchungstext
//			System.out.println(row[4]); // Verwendungszweck
//			System.out.println(row[11]); // Beguenstigter/Zahlungspflichtiger
//			System.out.println(row[12]); // Kontonummer/IBAN
//			System.out.println(row[13]); // BIC (SWIFT-Code)
//			System.out.println(row[14]); // Betrag
//			System.out.println(row[15]); // Waehrung
//			System.out.println(row[16]); // Info
//		}
//	}
}