package de.hdw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.hdw.model.SammelLastSchrift;
 
@Repository
public interface SammelLastSchriftRepository extends JpaRepository<SammelLastSchrift, Long> {
 
}
