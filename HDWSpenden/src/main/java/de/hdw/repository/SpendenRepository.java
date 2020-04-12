package de.hdw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.hdw.model.Spenden;
 
@Repository
public interface SpendenRepository extends JpaRepository<Spenden, Long> {
 
}
