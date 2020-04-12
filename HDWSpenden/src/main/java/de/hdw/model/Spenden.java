package de.hdw.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Spenden{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spenden_id", unique = true, nullable = false)
	private Long spendenId;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="spender_iban", nullable = false)
    private Spender spender;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "buchungstag", nullable = false)
	private Date buchungstag;

	@Column(name = "buchungstext", nullable = false, length = 200)
	private String buchungstext;

	@Column(name = "verwendungszweck", nullable = false, length = 200)
	private String verwendungszweck;

	@Column(name = "BIC", nullable = false, length = 80)
	private String bic;

	@Column(name = "betrag", nullable = false, length = 100)
	private Double betrag;

	@Column(name = "waehrung", nullable = false)
	private String waehrung;

	@Temporal(TemporalType.DATE)
	@Column(name = "spendenMonat", nullable = false)
	private Date spendenMonat;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "spendenJahr", nullable = false)
	private Date spendenJahr;
	
	
	public Spenden() {
		super();
	}
	
	public Date getBuchungstag() {
		return buchungstag;
	}

	public void setBuchungstag(Date buchungstag) {
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

	public Spender getSpender() {
		return spender;
	}

	public void setSpender(Spender spender) {
		this.spender = spender;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public Double getBetrag() {
		return betrag;
	}

	public void setBetrag(Double betrag) {
		this.betrag = betrag;
	}

	public String getWaehrung() {
		return waehrung;
	}

	public void setWaehrung(String waehrung) {
		this.waehrung = waehrung;
	}

	public Long getSpendenId() {
		return spendenId;
	}

	public void setSpendenId(Long spendenId) {
		this.spendenId = spendenId;
	}

	public Date getSpendenMonat() {
		return spendenMonat;
	}

	public void setSpendenMonat(Date spendenMonat) {
		this.spendenMonat = spendenMonat;
	}

	public Date getSpendenJahr() {
		return spendenJahr;
	}

	public void setSpendenJahr(Date spendenJahr) {
		this.spendenJahr = spendenJahr;
	}

	@Override
	public String toString() {
		return "";
	}
}