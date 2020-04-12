package de.hdw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.hdw.model.Adresse;
 
@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {
 
}
