package de.hdw.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.hdw.model.Spender;
 
@Repository
public interface SpenderRepository extends JpaRepository<Spender, Long> {
	
    Set<String> getAllIds();
 
}
