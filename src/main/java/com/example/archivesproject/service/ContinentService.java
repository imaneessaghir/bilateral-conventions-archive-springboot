package com.example.archivesproject.service;

import com.example.archivesproject.model.Continent;
import com.example.archivesproject.repository.ContinentRepository;
import com.example.archivesproject.repository.PaysRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContinentService {
    private final ContinentRepository continentRepository;
    private final PaysRepository paysRepository;

    public ContinentService(ContinentRepository continentRepository, PaysRepository paysRepository) {
        this.continentRepository = continentRepository;
        this.paysRepository = paysRepository;
    }

    public List<Continent> getAll(){
        return continentRepository.findAll();
    }
}
