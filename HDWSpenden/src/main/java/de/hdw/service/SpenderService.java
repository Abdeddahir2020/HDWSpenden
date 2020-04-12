package de.hdw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hdw.dao.KostenDAO;
import de.hdw.dao.SpenderDAO;
import de.hdw.dao.SammelLastSchriftDAO;
import de.hdw.exception.RecordNotFoundException;
import de.hdw.model.Spender;

@Service
public class SpenderService {

	@Autowired
	SpenderDAO spenderDAO;
	
	@Autowired
	SammelLastSchriftDAO spenderSammelLastSchriftDAO;
	
	@Autowired
	KostenDAO kostenDAO;

	public List<Spender> getAllSpender() {
		List<Spender> spenderList = spenderDAO.findAll();

		if (spenderList.size() > 0) {
			return spenderList;
		} else {
			return new ArrayList<Spender>();
		}
	}

	public Spender getSpenderByIban(Long spenderID) throws RecordNotFoundException {
		Optional<Spender> spender = spenderDAO.findById(spenderID);

		if (spender.isPresent()) {
			return spender.get();
		} else {
			throw new RecordNotFoundException("No spender record exist for given iban");
		}
	}

	public Spender createOrUpdateSpender(Spender entity) throws RecordNotFoundException {
		Optional<Spender> spender =  spenderDAO.findById(entity.getSpenderIban());

		if (spender.isPresent()) {
			Spender newEntity = spender.get();
			newEntity.setName(entity.getName());

			newEntity = spenderDAO.save(newEntity);

			return newEntity;
		} else {
			entity = spenderDAO.save(entity);

			return entity;
		}
	}

	public void saveSpender(Spender entity) throws RecordNotFoundException {
		Spender savedEntity = spenderDAO.save(entity);

		if (savedEntity == null) {
			throw new RecordNotFoundException("The Entity could not save");
		}
	}

	public void saveAllSpender(List<Spender> entities) throws RecordNotFoundException {
		for (Spender entity : entities) {
			saveSpender(entity);
		}
	}

	public void deleteSpenderByIban(Long spenderID) throws RecordNotFoundException {
		Optional<Spender> spender = spenderDAO.findById(spenderID);

		if (spender.isPresent()) {
			spenderDAO.deleteById(spenderID);
		} else {
			throw new RecordNotFoundException("No spender record exist for given iban");
		}
	}

}