package de.hdw.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "spender")
public class Spender {

	@Id
	@Column(name = "spender_iban" ,unique = true, nullable = false, columnDefinition = "VARCHAR(22)")
    private String spenderIban;
	
	@Column(name = "name")
	private String name;

	@OneToOne
	@JoinColumn(name="adresse_Id", insertable=false , updatable=false)
	private Adresse adresse;

	@OneToMany(mappedBy = "spender")
	private List<Spenden> spendenList;
	
	public Spender() {
		super();
	}
	
	public Spender(String name, String spenderIban) {
		super();
		this.name = name;
		this.spenderIban = spenderIban;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpenderIban() {
		return spenderIban;
	}

	public void setSpenderIban(String spenderIban) {
		this.spenderIban = spenderIban;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	public List<Spenden> getSpendenList() {
		return spendenList;
	}

	public void setSpendenList(List<Spenden> spendenList) {
		this.spendenList = spendenList;
	}
	
}