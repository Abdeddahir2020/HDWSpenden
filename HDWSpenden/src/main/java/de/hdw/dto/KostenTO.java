package de.hdw.dto;

import java.util.Date;

public class KostenTO {
	
	private String kostenId;

	private String buchungstag;

	private String buchungstext;

	private String verwendungszweck;

	private String name;

	private String iban;
	
	private String bic;

	private String betrag;

	private String waehrung;

	private String kostenType;
	
	

	public KostenTO() {
		super();
	}

	public String getKostenId() {
		return kostenId;
	}

	public void setKostenId(String kostenId) {
		this.kostenId = kostenId;
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

	public String getVerwendungszweck() {
		return verwendungszweck;
	}

	public void setVerwendungszweck(String verwendungszweck) {
		this.verwendungszweck = verwendungszweck;
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

	public String getKostenType() {
		return kostenType;
	}

	public void setKostenType(String kostenType) {
		this.kostenType = kostenType;
	}

	
}