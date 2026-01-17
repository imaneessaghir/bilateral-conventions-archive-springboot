package com.example.archivesproject.repository;

import com.example.archivesproject.model.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinentRepository extends JpaRepository<Continent, Long> {

}
