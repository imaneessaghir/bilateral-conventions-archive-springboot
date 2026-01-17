package com.example.archivesproject.service;

import com.example.archivesproject.model.Accord;

import com.example.archivesproject.model.Boite;
import com.example.archivesproject.model.Pays;
import com.example.archivesproject.repository.AccordRepository;
import com.example.archivesproject.repository.BoiteRepository;
import com.example.archivesproject.repository.PaysRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class AccordService {
    private final PaysRepository paysRepository;
    private final AccordRepository accordRepository;
    private final BoiteRepository boiteRepository;

    public AccordService(PaysRepository paysRepository, AccordRepository accordRepository, BoiteRepository boiteRepository) {
        this.paysRepository = paysRepository;
        this.accordRepository = accordRepository;
        this.boiteRepository = boiteRepository;
    }


    public List<Accord> getAll() {
        return accordRepository.findAll();
    }

    public Accord save(Accord accord){
        return accordRepository.save(accord);
    }

    public List<Accord> getByPays(String nomPays) {
        return accordRepository.findByBoite_Pays_NomIgnoreCase(nomPays);
    }

    public void deleteById(Integer id) {
        accordRepository.deleteById(id);
    }

    public Optional<Accord> getById(Integer id) {
    return  accordRepository.findById(id);
    }
    public List<Accord> getByMotCle(String motCle) {
        return accordRepository.findByIntituleContainingIgnoreCase(motCle);
    }


}
