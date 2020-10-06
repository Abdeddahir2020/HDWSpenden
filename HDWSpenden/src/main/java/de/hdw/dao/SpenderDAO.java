package de.hdw.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.hdw.model.Spender;
import de.hdw.repository.SpenderRepository;

@Repository
public class SpenderDAO {
	
	@Autowired
	SpenderRepository repository;
	
	public Set<String> getAllIds() {
		return repository.getAllIds();
	}

	public Optional<Spender> findById(Long iban) {
		return repository.findById(iban);
	}
	
	public List<Spender> findAll() {
		return repository.findAll();
	}

	public void saveAllSpender(List<Spender> entities) {
		repository.saveAll(entities);
	}

	public Spender save(Spender entity) {
		return repository.save(entity);
	}

	public void deleteById(Long iban) {
		repository.deleteById(iban);		
	}

}
