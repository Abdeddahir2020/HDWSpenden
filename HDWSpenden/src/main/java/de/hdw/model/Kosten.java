package de.hdw.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Kosten extends Spenden{
	

	@Column(name = "kosten_type")
	private String kostenType;
	
	

	public Kosten() {
		super();
	}

	public String getKostenType() {
		return kostenType;
	}

	public void setKostenType(String kostenType) {
		this.kostenType = kostenType;
	}

	
}