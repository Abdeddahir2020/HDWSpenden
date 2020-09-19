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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import de.hdw.constants.HDWConstants;
import de.hdw.dao.KostenDAO;
import de.hdw.dao.SammelLastSchriftDAO;
import de.hdw.dao.SpendenDAO;
import de.hdw.dao.SpenderDAO;
import de.hdw.model.Kosten;
import de.hdw.model.SammelLastSchrift;
import de.hdw.model.Spenden;
import de.hdw.model.Spender;
import de.hdw.utils.HDWUtils;

@Service
public class FileUploadService {

	@Autowired
	SpenderDAO spenderDAO;

	@Autowired
	SpendenDAO spendenDAO;

	@Autowired
	SammelLastSchriftDAO spenderSammelLastSchriftDAO;

	@Autowired
	KostenDAO kostenDAO;

	public List<Spender> uploadFileOLD(MultipartFile multipartFile)
			throws IOException, NumberFormatException, ParseException {

		File file = convertMultiPartToFile(multipartFile);

		// parse CSV file to create a list of `User` objects
		
		try (@SuppressWarnings("deprecation")
		CSVReader reader = new CSVReader(new FileReader(file), ';', '"', 1)) {
			
			// Read all rows at once
			List<String[]> allRows = reader.readAll();

			List<Spender> spdList = new ArrayList<>();
			List<Spenden> spendenList = new ArrayList<>();
			List<Kosten> kstList = new ArrayList<>();
			List<SammelLastSchrift> sLTSList = new ArrayList<>();
			Set<String> ibanSet = new HashSet<>();

			// Read CSV line by line and use the string array as you want
			for (String[] row : allRows) {
				Spender spender= new Spender();
				//Neuer IBAN
				if(!ibanSet.contains(row[12])) {
					spender.setName(row[11]); // Beguenstigter/Zahlungspflichtiger
					spender.setSpenderIban(row[12]); // Kontonummer/IBAN
					
					Spenden spenden = new Spenden();
					spenden.setSpender(spender);
					spenden =setzeSpenden(row, spenden);
					
					if (spender.getSpendenList() == null) spender.setSpendenList(new ArrayList<>());
					spender.getSpendenList().add(spenden);
				}
				//Die SpendenList ergängen
				else if(!spdList.isEmpty()){
					for(Spender spd : spdList) {
						if(row[12].equals(spd.getSpenderIban())) {
							Spenden spenden = new Spenden();
							//spenden.setSpendenId(spd.getSpenderIban());
							spenden.setSpender(spd);
							spenden = setzeSpenden(row, spenden);
							
//							spd.setName(row[11]); // Beguenstigter/Zahlungspflichtiger
//							spd.setSpenderIban(row[12]); // Kontonummer/IBAN
							
							if (spd.getSpendenList() == null) spd.setSpendenList(new ArrayList<>());
							spd.getSpendenList().add(spenden);
						}
					}
				}
				
			ibanSet.add(row[12]);
			spdList.add(spender);
			spendenList.addAll(spender.getSpendenList());
		}
		
			spenderDAO.saveAllSpender(spdList);
			spendenDAO.saveAllSpenden(spendenList);
			kostenDAO.saveAllKosten(kstList);
			spenderSammelLastSchriftDAO.saveAllSpenderSammelLastSchrift(sLTSList);
		}
		return null;

	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

//	private Long convertIbanToLong(String iban) {
//		String subIban = iban != null ? iban.substring(2) : null;
//
//		return subIban != null ? Long.parseLong(subIban) : new Long(0);
//	}

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

	public List<Spender> uploadFile(MultipartFile multipartFile)
			throws IOException, NumberFormatException, ParseException {

		File file = convertMultiPartToFile(multipartFile);

		// parse CSV file to create a list of `User` objects
		
		try (@SuppressWarnings("deprecation")
		CSVReader reader = new CSVReader(new FileReader(file), ';', '"', 1)) {
			
			// Read all rows at once
			List<String[]> allRows = reader.readAll();

			List<Spender> spenderList = new ArrayList<>();
			List<Spenden> spendenList = new ArrayList<>();
			List<Kosten> kstList = new ArrayList<>();
			List<SammelLastSchrift> spdSLSTList = new ArrayList<>();
			Set<String> ibanSet = new HashSet<>();

			// Read CSV line by line
			for (String[] row : allRows) {
				
				if(row[3] != null && row[3].contains("SAMMEL-LS-EINZUG")){
					Spenden spenden = new SammelLastSchrift();
					spenden = setzeSpenden(row, spenden);
					spdSLSTList.add((SammelLastSchrift) spenden);
				}
				else if(row[14] != null && HDWUtils.isNegative(Double.parseDouble(row[14].replace(",",".")))){
					Kosten kosten = new Kosten();
					//KostenType
					if(row[11] != null && row[11].equals(HDWConstants.MIETE))
						kosten.setKostenType("Miete");
					
					else if(row[11] != null && row[11].contains(HDWConstants.TELEFONINTERNET))
						kosten.setKostenType("Telefon und Internet");
					
					else if(row[3] != null && row[3].equals(HDWConstants.ENTGELTABSCHLUSS))
						kosten.setKostenType("Entgeltabschluss");
					
					kosten = (Kosten) setzeSpenden(row, kosten);
					kosten.setName(row[11]); // Beguenstigter/Zahlungspflichtiger
					kosten.setKostenIban(row[12]); // Kontonummer/IBAN
					
					kstList.add(kosten);
				}
				else{
					//Neuer IBAN
					if(!ibanSet.contains(row[12])) {
						Spender spender= new Spender();
						Spenden spenden = new Spenden();
						spenden =setzeSpenden(row, spenden);
						spender.setName(row[11]); // Beguenstigter/Zahlungspflichtiger
						spender.setSpenderIban(row[12]); // Kontonummer/IBAN
						
						if (spender.getSpendenList() == null) spender.setSpendenList(new ArrayList<>());
						spender.getSpendenList().add(spenden);
						
						spenderList.add(spender);
						spendenList.add(spenden);
					}
					//Die SpendenList ergänzen
					else if(!spenderList.isEmpty()){
						for(Spender spd : spenderList) {
							if(row[12].equals(spd.getSpenderIban())) {
								Spenden spenden = new Spenden();
								spenden.setSpender(spd);
								spenden = setzeSpenden(row, spenden);
								
								if (spd.getSpendenList() == null) spd.setSpendenList(new ArrayList<>());
								spd.getSpendenList().add(spenden);
								
								spendenList.add(spenden);
							}
						}
					}
//					spenderList.add(spender);
//					spendenList.addAll(spender.getSpendenList());
				}
				ibanSet.add(row[12]);
			}
			
			spenderDAO.saveAllSpender(spenderList);
			spendenDAO.saveAllSpenden(spendenList);
			kostenDAO.saveAllKosten(kstList);
			spenderSammelLastSchriftDAO.saveAllSpenderSammelLastSchrift(spdSLSTList);
		}
		return null;

	}
}