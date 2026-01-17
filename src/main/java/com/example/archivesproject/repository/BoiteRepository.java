package com.example.archivesproject.repository;

import com.example.archivesproject.model.Boite;
import com.example.archivesproject.model.Pays;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoiteRepository extends JpaRepository<Boite, Integer> {
    Optional<Boite> findByNumeroAndPays(Integer numero, Pays pays);
}
