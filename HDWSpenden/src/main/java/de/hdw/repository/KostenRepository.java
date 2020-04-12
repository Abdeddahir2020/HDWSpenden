package de.hdw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.hdw.model.Kosten;
 
@Repository
public interface KostenRepository extends JpaRepository<Kosten, Long> {
 
}
