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
	
	Set<String> fileNamenSet = new HashSet<String>();

	public List<Spender> uploadFile(MultipartFile multipartFile)
			throws IOException, NumberFormatException, ParseException {

		File file = convertMultiPartToFile(multipartFile);

		// parse CSV file to create a list of `User` objects
		
		try (@SuppressWarnings("deprecation")
		CSVReader reader = new CSVReader(new FileReader(file), ';', '"', 1)) {
			
			// Read all rows at once
			List<String[]> allRows = new ArrayList<String[]>();
			if(!fileNamenSet.contains(file.getName()))
				allRows = reader.readAll();

			List<Spender> spenderList = new ArrayList<>();
			List<Spenden> spendenList = new ArrayList<>();
			List<Kosten> kstList = new ArrayList<>();
			List<SammelLastSchrift> spdSLSTList = new ArrayList<>();
			Set<String> ibanSet = spenderDAO.getAllIds();

			// Read CSV line by line
			for (String[] row : allRows) {
				
				if(row[3] != null && row[3].contains("SAMMEL-LS-EINZUG")){
					SammelLastSchrift slst = new SammelLastSchrift();
					slst = setzeSammelLastSchrift(row, slst);
					
					//Spender setzen
					if(!ibanSet.contains(row[12])) {
						Spender spender= new Spender(row[11], row[12]);
						slst.setSpender(spender);
						
						if (spender.getSllstList() == null) spender.setSllstList(new ArrayList<>());
						spender.getSllstList().add(slst);
						
						spenderList.add(spender);
					}
					//Bestehende Spender mit neue SAMMEL-LS ergänzen
					else if(!spenderList.isEmpty()){
						for(Spender spd : spenderList) {
							if(row[12].equals(spd.getSpenderIban())) {
								slst.setSpender(spd);
								
								if (spd.getSllstList() == null) spd.setSllstList(new ArrayList<>());
								spd.getSllstList().add(slst);
							}
							//spenderList.add(spd);
						}
					}
					spdSLSTList.add(slst);
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
					
					kosten = setzeKosten(row, kosten);
					//Spender setzen
					if(!ibanSet.contains(row[12])) {
						Spender spender= new Spender(row[11], row[12]);
						kosten.setSpender(spender); 

						if (spender.getKostenList() == null) spender.setKostenList(new ArrayList<>());
						spender.getKostenList().add(kosten);
						
						spenderList.add(spender);
					}
					// Bestehende Spender mit neue Kosten ergänzen
					else if(!spenderList.isEmpty()){
						for(Spender spd : spenderList) {
							if(row[12].equals(spd.getSpenderIban())) {
								kosten.setSpender(spd);
								
								if (spd.getKostenList() == null) spd.setKostenList(new ArrayList<>());
								spd.getKostenList().add(kosten);
							}
							//spenderList.add(spd);
						}
					}
					kstList.add(kosten);
				}
				else{
					//Neuer IBAN
					if(!ibanSet.contains(row[12])) {
						Spender spender= new Spender(row[11], row[12] );
						Spenden spenden = new Spenden();
						spenden.setSpender(spender);
						spenden =setzeSpenden(row, spenden);
						
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
				}
				ibanSet.add(row[12]);
			}
			fileNamenSet.add(file.getName());
			spenderDAO.saveAllSpender(spenderList);
			spendenDAO.saveAllSpenden(spendenList);
			kostenDAO.saveAllKosten(kstList);
			spenderSammelLastSchriftDAO.saveAllSpenderSammelLastSchrift(spdSLSTList);
			
			file.delete();
			((File) multipartFile).delete();
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
	
	private Kosten setzeKosten(String[] row, Kosten kosten) {
		
		kosten.setBuchungstag(HDWUtils.convertStringToTimestamp(row[1])); // Buchungstag
		kosten.setBuchungstext(row[3]); // Buchungstext
		kosten.setVerwendungszweck(row[4]); // Verwendungszweck
		kosten.setBic(row[13]); // BIC (SWIFT-Code)
		kosten.setBetrag(Double.parseDouble(row[14].replace(",","."))); // Betrag
		kosten.setWaehrung(row[15]); // Waehrung
		kosten.setSpendenMonat(HDWUtils.getMonat(kosten.getBuchungstag()));
		kosten.setSpendenJahr(HDWUtils.getJahr(kosten.getBuchungstag()));
		
		return kosten;
	}
	
	private SammelLastSchrift setzeSammelLastSchrift(String[] row, SammelLastSchrift sllst) {
		
		sllst.setBuchungstag(HDWUtils.convertStringToTimestamp(row[1])); // Buchungstag
		sllst.setBuchungstext(row[3]); // Buchungstext
		sllst.setVerwendungszweck(row[4]); // Verwendungszweck
		sllst.setBic(row[13]); // BIC (SWIFT-Code)
		sllst.setBetrag(Double.parseDouble(row[14].replace(",","."))); // Betrag
		sllst.setWaehrung(row[15]); // Waehrung
		sllst.setSpendenMonat(HDWUtils.getMonat(sllst.getBuchungstag()));
		sllst.setSpendenJahr(HDWUtils.getJahr(sllst.getBuchungstag()));
		
		return sllst;
	}
	
//	private Long convertIbanToLong(String iban) {
//	String subIban = iban != null ? iban.substring(2) : null;
//
//	return subIban != null ? Long.parseLong(subIban) : new Long(0);
//}

}