package de.hdw.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SPENDER")
public class Spender {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spender_iban", nullable = false, columnDefinition = "BINARY(16)")
    private UUID spenderIban;
	
	@Column(name = "name")
	private String name;

	@OneToOne
	@JoinColumn(name="adresse_Id", insertable=false , updatable=false)
	private Adresse adresse;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "spender")
	private List<Spenden> spendenList;
	
	
	public Spender() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getSpenderIban() {
		return spenderIban;
	}

	public void setSpenderIban(UUID spenderIban) {
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