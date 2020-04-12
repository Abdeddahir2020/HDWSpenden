package de.hdw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hdw.dao.SpendenDAO;
import de.hdw.exception.RecordNotFoundException;
import de.hdw.model.Spenden;

@Service
public class SpendenService {

	@Autowired
	SpendenDAO spendenDAO;

	public List<Spenden> getAllSpenden() {
		List<Spenden> spendenList = spendenDAO.findAll();

		if (spendenList.size() > 0) {
			return spendenList;
		} else {
			return new ArrayList<Spenden>();
		}
	}

	public Spenden getSpenderByIban(Long spenderID) throws RecordNotFoundException {
		Optional<Spenden> spender = spendenDAO.findById(spenderID);

		if (spender.isPresent()) {
			return spender.get();
		} else {
			throw new RecordNotFoundException("No Spenden record exist for given iban");
		}
	}

	public Spenden createOrUpdateSpender(Spenden entity) throws RecordNotFoundException {
		Optional<Spenden> kosten = spendenDAO.findById(entity.getSpendenId());

		if (kosten.isPresent()) {
			Spenden newEntity = kosten.get();
			newEntity.getSpender().setName(entity.getSpender().getName());

			newEntity = spendenDAO.save(newEntity);

			return newEntity;
		} else {
			entity = spendenDAO.save(entity);

			return entity;
		}
	}

	public void saveSpenden(Spenden entity) throws RecordNotFoundException {
		Spenden savedEntity = spendenDAO.save(entity);

		if (savedEntity == null) {
			throw new RecordNotFoundException("The Entity could not save");
		}
	}

	public void saveAllSpender(List<Spenden> entities) throws RecordNotFoundException {
		for (Spenden entity : entities) {
			saveSpenden(entity);
		}
	}

	public void deleteSpenderByIban(Long spenderID) throws RecordNotFoundException {
		Optional<Spenden> spender = spendenDAO.findById(spenderID);

		if (spender.isPresent()) {
			spendenDAO.deleteById(spenderID);
		} else {
			throw new RecordNotFoundException("No Spenden record exist for given iban");
		}
	}

}