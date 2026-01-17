package com.example.archivesproject.repository;

import com.example.archivesproject.model.Accord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface AccordRepository extends JpaRepository<Accord, Integer> {
    List<Accord> findByBoite_Pays_NomIgnoreCase(String nom);
    Optional<Accord> findById(Integer id);



    @Query("SELECT a FROM Accord a WHERE LOWER(a.intitule) LIKE LOWER(CONCAT('%', :motCle, '%'))")
    List<Accord> findByIntituleContainingIgnoreCase(@Param("motCle") String motCle);
}
