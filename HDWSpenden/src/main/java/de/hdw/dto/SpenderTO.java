package de.hdw.dto;

public class SpenderTO {

	private String spenderId;
	
	private String buchungstag;

	private String buchungstext;

	private String verwendungszweck;

	private String name;

	private String iban;

	private String bic;

	private String betrag;

	private String waehrung;

	private String adresse;

	
	
	public SpenderTO() {
		super();
	}

	public String getSpenderId() {
		return spenderId;
	}

	public void setSpenderId(String spenderId) {
		this.spenderId = spenderId;
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

	public String getAdresse() {
		return adresse;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

}