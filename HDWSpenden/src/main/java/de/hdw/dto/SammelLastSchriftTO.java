package de.hdw.dto;

import java.util.Date;

public class SammelLastSchriftTO{

	private String spenderSLtSchriftId;

	private String sammelGruppenName;
	
	private String mandatsreferenz;
	
	private String mandatsdatum;

	private String buchungstag;

	private String buchungstext;

	private String name;
	
	private String iban;

	private String bic;

	private String betrag;

	private String waehrung;


	public SammelLastSchriftTO() {
		super();
	}

	public String getSammelGruppenName() {
		return sammelGruppenName;
	}

	public void setSammelGruppenName(String sammelGruppenName) {
		this.sammelGruppenName = sammelGruppenName;
	}

	public String getMandatsreferenz() {
		return mandatsreferenz;
	}

	public void setMandatsreferenz(String mandatsreferenz) {
		this.mandatsreferenz = mandatsreferenz;
	}

	public String getMandatsdatum() {
		return mandatsdatum;
	}

	public void setMandatsdatum(String mandatsdatum) {
		this.mandatsdatum = mandatsdatum;
	}

	public String getSpenderSLtSchriftId() {
		return spenderSLtSchriftId;
	}

	public void setSpenderSLtSchriftId(String spenderSLtSchriftId) {
		this.spenderSLtSchriftId = spenderSLtSchriftId;
	}

	public String getBuchungstag() {
		return buchungstag;
	}

	public void setBuchungstag(String buchungstag) {
		this.buchungstag = buchungstag;
	}

	public String getBuchungstext() {
		return buchungstext;
	}

	public void setBuchungstext(String buchungstext) {
		this.buchungstext = buchungstext;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}
	
	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getBetrag() {
		return betrag;
	}

	public void setBetrag(String betrag) {
		this.betrag = betrag;
	}

	public String getWaehrung() {
		return waehrung;
	}

	public void setWaehrung(String waehrung) {
		this.waehrung = waehrung;
	}

}