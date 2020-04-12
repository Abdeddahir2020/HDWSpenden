package de.hdw.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.hdw.repository.SammelLastSchriftRepository;

import de.hdw.model.SammelLastSchrift;;

@Repository
public class SammelLastSchriftDAO {
	
	@Autowired
	SammelLastSchriftRepository repository;
	
	public Optional<SammelLastSchrift> findById(Long id) {
		return repository.findById(id);
	}
	
	public List<SammelLastSchrift> findAll() {
		return repository.findAll();
	}

	public void saveAllSpenderSammelLastSchrift(List<SammelLastSchrift> entities) {
		repository.saveAll(entities);
	}

	public SammelLastSchrift save(SammelLastSchrift entity) {
		return repository.save(entity);
	}

	public void deleteById(Long iban) {
		repository.deleteById(iban);		
	}

}
