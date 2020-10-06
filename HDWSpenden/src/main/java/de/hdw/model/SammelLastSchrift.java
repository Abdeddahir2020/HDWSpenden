package de.hdw.model;

import java.util.Date;

import javax.persistence.Column;
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
@Table(name = "sammelLastSchrift")
//@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
//@DiscriminatorValue(value="SMLLSTSCH")
public class SammelLastSchrift {//extends Spenden
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sllst_id", nullable = false)
	private Long sllstd;
	
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
	
	@Column(name = "sammel_gruppen_name")
	private String sammelGruppenName;
	
	@Column(name = "mandats_referenz")
	private String mandatsReferenz;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "mandats_datum")
	private Date mandatsDatum;

	

	public SammelLastSchrift() {
		super();
	}

	public Long getSllstd() {
		return sllstd;
	}

	public void setSllstd(Long sllstd) {
		this.sllstd = sllstd;
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
	
	
	public String getSammelGruppenName() {
		return sammelGruppenName;
	}

	public void setSammelGruppenName(String sammelGruppenName) {
		this.sammelGruppenName = sammelGruppenName;
	}

	public String getMandatsReferenz() {
		return mandatsReferenz;
	}

	public void setMandatsReferenz(String mandatsReferenz) {
		this.mandatsReferenz = mandatsReferenz;
	}

	public Date getMandatsDatum() {
		return mandatsDatum;
	}

	public void setMandatsDatum(Date mandatsDatum) {
		this.mandatsDatum = mandatsDatum;
	}

	@Override
	public String toString() {
		return "SammelLastSchrift [sammelGruppenName=" + sammelGruppenName + ", mandatsReferenz=" + mandatsReferenz
				+ ", mandatsDatum=" + mandatsDatum + ", spenderIban=" + getSpender().getSpenderIban() + ", spenderName=" + getSpender().getName()+ "]";
	}

}