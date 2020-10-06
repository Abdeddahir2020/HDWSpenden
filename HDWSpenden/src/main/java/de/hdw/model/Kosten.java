package de.hdw.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "kosten")
//@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
//@DiscriminatorValue(value="KOSTEN")
public class Kosten {//extends Spenden
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kosten_id", nullable = false)
	private Long kostenId;
	
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

	@Column(name = "bic", nullable = false, length = 80)
	private String bic;

	@Column(name = "betrag", nullable = false, length = 100)
	private Double betrag;

	@Column(name = "waehrung", nullable = false)
	private String waehrung;

	@Column(name = "spendenMonat", nullable = false)
	private String spendenMonat;
	
	@Column(name = "spendenJahr", nullable = false)
	private String spendenJahr;

	@Column(name = "kosten_type")
	private String kostenType;
	
	public Kosten() {
		super();
	}

	public Long getKostenId() {
		return kostenId;
	}

	public void setKostenId(Long kostenId) {
		this.kostenId = kostenId;
	}

	public Spender getSpender() {
		return spender;
	}

	public void setSpender(Spender spender) {
		this.spender = spender;
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

	public String getSpendenMonat() {
		return spendenMonat;
	}

	public void setSpendenMonat(String spendenMonat) {
		this.spendenMonat = spendenMonat;
	}

	public String getSpendenJahr() {
		return spendenJahr;
	}

	public void setSpendenJahr(String spendenJahr) {
		this.spendenJahr = spendenJahr;
	}
	
	public String getKostenType() {
		return kostenType;
	}

	public void setKostenType(String kostenType) {
		this.kostenType = kostenType;
	}

	@Override
	public String toString() {
		return "Kosten [kostenType=" + kostenType + ", spenderIban=" + getSpender().getSpenderIban() + ", spenderName=" + getSpender().getName()+ "]";
	}
	

}