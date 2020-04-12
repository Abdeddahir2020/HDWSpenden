package de.hdw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hdw.dao.AdresseDAO;
import de.hdw.exception.RecordNotFoundException;
import de.hdw.model.Adresse;

@Service
public class AdresseService {

	@Autowired
	AdresseDAO adresseDAO;
	
	
	public List<Adresse> getAllSpender() {
		List<Adresse> spenderList = adresseDAO.findAll();

		if (spenderList.size() > 0) { 
			return spenderList;
		} else {
			return new ArrayList<Adresse>();
		}
	}

	public Adresse getSpenderByIban(Long adresseID) throws RecordNotFoundException {
		Optional<Adresse> adresse = adresseDAO.findById(adresseID);

		if (adresse.isPresent()) {
			return adresse.get();
		} else {
			throw new RecordNotFoundException("No Adresse record exist for given iban");
		}
	}

	public Adresse createOrUpdateAdresse(Adresse entity) throws RecordNotFoundException {
		Optional<Adresse> adresse =  adresseDAO.findById(entity.getAdresseId());

		if (adresse.isPresent()) {
			Adresse newEntity = adresse.get();
			newEntity.setLand(entity.getLand());
			newEntity.setStadt(entity.getStadt());
			newEntity.setStrasse(entity.getStrasse());
			newEntity.setHausNr(entity.getHausNr());
			newEntity.setPlz(entity.getPlz());
			
			newEntity = adresseDAO.save(newEntity);

			return newEntity;
		} else {
			entity = adresseDAO.save(entity);

			return entity;
		}
	}

	public void saveAdresse(Adresse entity) throws RecordNotFoundException {
		Adresse savedEntity = adresseDAO.save(entity);

		if (savedEntity == null) {
			throw new RecordNotFoundException("The Entity could not save");
		}
	}

	public void saveAllAdresse(List<Adresse> entities) throws RecordNotFoundException {
		for (Adresse entity : entities) {
			saveAdresse(entity);
		}
	}

	public void deleteAdresseById(Long adresseId) throws RecordNotFoundException {
		Optional<Adresse> adresse = adresseDAO.findById(adresseId);

		if (adresse.isPresent()) {
			adresseDAO.deleteById(adresseId);
		} else {
			throw new RecordNotFoundException("No address record exist for given iban");
		}
	}

}