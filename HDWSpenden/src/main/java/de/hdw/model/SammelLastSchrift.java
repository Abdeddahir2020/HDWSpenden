package de.hdw.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value="SMLLSTSCH")
public class SammelLastSchrift extends Spenden{


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


}