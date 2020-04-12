//package de.hdw.model;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//@Entity
//@Table(name = "SPENDER")
//public class Spender2 {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "spenderId", nullable = false)
//	private Long spenderId;
//	
//	@Temporal(TemporalType.DATE)
////	@Type(type="date")
//	@Column(name = "buchungstag")
//	private Date buchungstag;
//
//	@Column(name = "buchungstext", nullable = false, length = 200)
//	private String buchungstext;
//
//	@Column(name = "verwendungszweck", nullable = false, length = 200)
//	private String verwendungszweck;
//
//	@Column(name = "name")
//	private String name;
//
//	@Column(name = "IBAN", nullable = false, length = 200)
//	private String iban;
//
//	@Column(name = "BIC", nullable = false, length = 80)
//	private String bic;
//
//	@Column(name = "betrag", nullable = false, length = 100)
//	private Double betrag;
//
//	@Column(name = "waehrung", nullable = false)
//	private String waehrung;
//
//	@Column(name = "info", nullable = false)
//	private String info;
//	
//	@OneToOne
//	@JoinColumn(name="adresse", insertable=false , updatable=false)
//	private Adresse adresse;
//	
//	@OneToMany
//	@JoinColumn(name="IBAN", insertable=false , updatable=false)
//	private Spenden spenden;
//
//	
//	
//	public Spender2() {
//		super();
//	}
//
//	public Long getSpenderId() {
//		return spenderId;
//	}
//
//	public void setSpenderId(Long spenderId) {
//		this.spenderId = spenderId;
//	}
//
//	public Date getBuchungstag() {
//		return buchungstag;
//	}
//
//	public void setBuchungstag(Date buchungstag) {
//		this.buchungstag = buchungstag;
//	}
//
//	public String getBuchungstext() {
//		return buchungstext;
//	}
//
//	public void setBuchungstext(String buchungstext) {
//		this.buchungstext = buchungstext;
//	}
//
//	public String getVerwendungszweck() {
//		return verwendungszweck;
//	}
//
//	public void setVerwendungszweck(String verwendungszweck) {
//		this.verwendungszweck = verwendungszweck;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getIban() {
//		return iban;
//	}
//
//	public void setIban(String iban) {
//		this.iban = iban;
//	}
//
//	public String getBic() {
//		return bic;
//	}
//
//	public void setBic(String bic) {
//		this.bic = bic;
//	}
//
//	public Double getBetrag() {
//		return betrag;
//	}
//
//	public void setBetrag(Double betrag) {
//		this.betrag = betrag;
//	}
//
//	public String getWaehrung() {
//		return waehrung;
//	}
//
//	public void setWaehrung(String waehrung) {
//		this.waehrung = waehrung;
//	}
//
//	public String getInfo() {
//		return info;
//	}
//
//	public void setInfo(String info) {
//		this.info = info;
//	}
//
//	@Override
//	public String toString() {
//		return "Spender [IBAN=" + iban + " , Name=" + name + ", Betrag=" + betrag + "]";
//	}
//
//	public Adresse getAdresse() {
//		return adresse;
//	}
//
//	public void setAdresse(Adresse adresse) {
//		this.adresse = adresse;
//	}
//}