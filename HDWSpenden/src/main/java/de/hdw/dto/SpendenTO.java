package de.hdw.dto;

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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class SpendenTO{


	private String spendenId;
	
	private String type;
	
    private String spenderIban;
	
	private String buchungstag;

	private String buchungstext;

	private String verwendungszweck;

	private String bic;

	private String betrag;

	private String waehrung;

	private String spendenMonat;
	
	private String spendenJahr;
	
	private String kostenType;
	
	private String sammelGruppenName;
	
	private String mandatsreferenz;
	
	private String mandatsdatum;

	
	
	public SpendenTO() {
		super();
	}
	
	
	
}