package com.example.archivesproject.service;

import com.example.archivesproject.model.Pays;
import com.example.archivesproject.repository.PaysRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaysService {


    private final PaysRepository paysRepository;

    public PaysService(PaysRepository paysRepository) {
        this.paysRepository = paysRepository;
    }

    public List<Pays> getAll() {
        return paysRepository.findAll();
    }

    public Pays save(Pays pays) {
        return paysRepository.save(pays);
    }

    }
