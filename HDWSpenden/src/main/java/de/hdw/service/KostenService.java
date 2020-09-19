package de.hdw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hdw.dao.KostenDAO;
import de.hdw.exception.RecordNotFoundException;
import de.hdw.model.Kosten;

@Service
public class KostenService {

	@Autowired
	KostenDAO kostenDAO;

	public List<Kosten> getAllKosten() {
		List<Kosten> kostenList = kostenDAO.findAll();

		if (kostenList.size() > 0) {
			return kostenList;
		} else {
			return new ArrayList<Kosten>();
		}
	}

	public Kosten getSpenderByIban(Long spenderID) throws RecordNotFoundException {
		Optional<Kosten> spender = kostenDAO.findById(spenderID);

		if (spender.isPresent()) {
			return spender.get();
		} else {
			throw new RecordNotFoundException("No Kosten record exist for given iban");
		}
	}

	public Kosten createOrUpdateSpender(Kosten entity) throws RecordNotFoundException {
//		Optional<Kosten> kosten = kostenDAO.findById(entity.getSpendenId());
//
//		if (kosten.isPresent()) {
//			Kosten newEntity = kosten.get();
//			newEntity.getSpender().setName(entity.getSpender().getName());
//
//			newEntity = kostenDAO.save(newEntity);
//
//			return newEntity;
//		} else {
//			entity = kostenDAO.save(entity);
//
//			return entity;
//		}
		return null;
	}

	public void saveKosten(Kosten entity) throws RecordNotFoundException {
		Kosten savedEntity = kostenDAO.save(entity);

		if (savedEntity == null) {
			throw new RecordNotFoundException("The Entity could not save");
		}
	}

	public void saveAllSpender(List<Kosten> entities) throws RecordNotFoundException {
		for (Kosten entity : entities) {
			saveKosten(entity);
		}
	}

	public void deleteSpenderByIban(Long spenderID) throws RecordNotFoundException {
		Optional<Kosten> spender = kostenDAO.findById(spenderID);

		if (spender.isPresent()) {
			kostenDAO.deleteById(spenderID);
		} else {
			throw new RecordNotFoundException("No Kosten record exist for given iban");
		}
	}

}