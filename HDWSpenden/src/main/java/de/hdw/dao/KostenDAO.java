package de.hdw.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.hdw.model.Kosten;
import de.hdw.repository.KostenRepository;

@Repository
public class KostenDAO {
	
	@Autowired
	KostenRepository repository;
	
	public Optional<Kosten> findById(Long iban) {
		return repository.findById(iban);
	}
	
	public List<Kosten> findAll() {
		return repository.findAll();
	}

	public void saveAllKosten(List<Kosten> entities) {
		repository.saveAll(entities);
	}

	public Kosten save(Kosten entity) {
		return repository.save(entity);
	}

	public void deleteById(Long iban) {
		repository.deleteById(iban);		
	}

}
