package de.hdw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Adresse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adresse_Id", nullable = false)
	private Long adresseId;

	@Column(name = "strasse", nullable = false)
	private String strasse;
	
	@Column(name = "hausNr", nullable = false)
	private String hausNr;

	@Column(name = "plz", nullable = false)
	private String plz;
	
	@Column(name = "stadt", nullable = false)
	private String stadt;
	
	@Column(name = "land", nullable = false)
	private String land;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Spender spender;
	
	public Adresse() {
		super();
	}

	public Long getAdresseId() {
		return adresseId;
	}

	public void setAdresseId(Long adresseId) {
		this.adresseId = adresseId;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHausNr() {
		return hausNr;
	}

	public void setHausNr(String hausNr) {
		this.hausNr = hausNr;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public Spender getSpender() {
		return spender;
	}

	public void setSpender(Spender spender) {
		this.spender = spender;
	}
	
	
	
}
