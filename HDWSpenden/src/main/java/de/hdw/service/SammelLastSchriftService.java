package de.hdw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hdw.dao.SammelLastSchriftDAO;
import de.hdw.exception.RecordNotFoundException;
import de.hdw.model.Spender;
import de.hdw.model.SammelLastSchrift;

@Service
public class SammelLastSchriftService {

	@Autowired
	SammelLastSchriftDAO spenderSammelLastSchriftDAO;

	public List<SammelLastSchrift> getAllSpender() {
		List<SammelLastSchrift> spenderSammelLastSchriftList = spenderSammelLastSchriftDAO.findAll();

		if (spenderSammelLastSchriftList.size() > 0) {
			return spenderSammelLastSchriftList;
		} else {
			return new ArrayList<SammelLastSchrift>();
		}
	}

	public SammelLastSchrift getSpenderByIban(Long spenderID) throws RecordNotFoundException {
		Optional<SammelLastSchrift> spenderSammelLastSchrift = spenderSammelLastSchriftDAO.findById(spenderID);

		if (spenderSammelLastSchrift.isPresent()) {
			return spenderSammelLastSchrift.get();
		} else {
			throw new RecordNotFoundException("No spender record exist for given iban");
		}
	}

	public SammelLastSchrift createOrUpdateSpender(SammelLastSchrift entity) throws RecordNotFoundException {
		Optional<SammelLastSchrift> spenderSammelLastSchrift = spenderSammelLastSchriftDAO.findById(entity.getSpendenId());

		if (spenderSammelLastSchrift.isPresent()) {
			SammelLastSchrift newEntity = spenderSammelLastSchrift.get();
			newEntity.getSpender().setName(entity.getSpender().getName());

			newEntity = spenderSammelLastSchriftDAO.save(newEntity);

			return newEntity;
		} else {
			entity = spenderSammelLastSchriftDAO.save(entity);

			return entity;
		}
	}

	public void saveSpender(SammelLastSchrift entity) throws RecordNotFoundException {
		SammelLastSchrift savedEntity = spenderSammelLastSchriftDAO.save(entity);

		if (savedEntity == null) {
			throw new RecordNotFoundException("The Entity could not save");
		}
	}

	public void saveAllSpender(List<SammelLastSchrift> entities) throws RecordNotFoundException {
		for (SammelLastSchrift entity : entities) {
			saveSpender(entity);
		}
	}

	public void deleteSpenderByIban(Long id) throws RecordNotFoundException {
		Optional<SammelLastSchrift> spender = spenderSammelLastSchriftDAO.findById(id);

		if (spender.isPresent()) {
			spenderSammelLastSchriftDAO.deleteById(id);
		} else {
			throw new RecordNotFoundException("No spender record exist for given iban");
		}
	}

}