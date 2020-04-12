package de.hdw.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.hdw.model.Spenden;
import de.hdw.repository.SpendenRepository;

@Repository
public class SpendenDAO {
	
	@Autowired
	SpendenRepository repository;
	
	public Optional<Spenden> findById(Long iban) {
		return repository.findById(iban);
	}
	
	public List<Spenden> findAll() {
		return repository.findAll();
	}

	public void saveAllKosten(List<Spenden> entities) {
		repository.saveAll(entities);
	}

	public Spenden save(Spenden entity) {
		return repository.save(entity);
	}

	public void deleteById(Long iban) {
		repository.deleteById(iban);		
	}

}
