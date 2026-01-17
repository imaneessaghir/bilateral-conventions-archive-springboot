package com.example.archivesproject.repository;

import com.example.archivesproject.model.Continent;
import com.example.archivesproject.model.Pays;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaysRepository extends JpaRepository<Pays, Integer> {
    List<Pays> findByContinent(Continent continent);
    Optional<Pays> findByNom(String nom);
}
