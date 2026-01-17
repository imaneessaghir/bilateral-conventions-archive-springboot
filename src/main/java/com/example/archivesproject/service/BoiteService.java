package com.example.archivesproject.service;

import com.example.archivesproject.model.Accord;
import com.example.archivesproject.model.Boite;
import com.example.archivesproject.repository.AccordRepository;
import com.example.archivesproject.repository.BoiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoiteService {
    private final BoiteRepository boiteRepository;

    public BoiteService(BoiteRepository boiteRepository) {
        this.boiteRepository = boiteRepository;
    }

    public List<Boite> getAll() {
        return boiteRepository.findAll();
    }

    public Boite save(Boite boite) {
        return boiteRepository.save(boite);
    }
}
