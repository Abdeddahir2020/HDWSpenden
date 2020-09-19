package de.hdw.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kosten")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value="KOSTEN")
public class Kosten extends Spenden{
	

	@Column(name = "kosten_type")
	private String kostenType;
	
	@Column(name = "kosten_iban")
    private String kostenIban;
	
	@Column(name = "name")
	private String name;
	
	

	public Kosten() {
		super();
	}

	public String getKostenType() {
		return kostenType;
	}

	public void setKostenType(String kostenType) {
		this.kostenType = kostenType;
	}

	public String getKostenIban() {
		return kostenIban;
	}

	public void setKostenIban(String kostenIban) {
		this.kostenIban = kostenIban;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}