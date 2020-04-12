package de.hdw.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.hdw.model.Adresse;
import de.hdw.repository.AdresseRepository;

@Repository
public class AdresseDAO {
	
	@Autowired
	AdresseRepository repository;
	
	public Optional<Adresse> findById(Long iban) {
		return repository.findById(iban);
	}
	
	public List<Adresse> findAll() {
		return repository.findAll();
	}

	public void saveAllSpender(List<Adresse> entities) {
		repository.saveAll(entities);
	}

	public Adresse save(Adresse entity) {
		return repository.save(entity);
	}

	public void deleteById(Long iban) {
		repository.deleteById(iban);		
	}

}
