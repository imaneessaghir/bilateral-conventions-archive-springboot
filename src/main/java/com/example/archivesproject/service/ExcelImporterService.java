package com.example.archivesproject.service;



import com.example.archivesproject.model.Accord;
import com.example.archivesproject.model.Boite;
import com.example.archivesproject.model.Document;
import com.example.archivesproject.repository.AccordRepository;
import com.example.archivesproject.repository.BoiteRepository;
import com.example.archivesproject.repository.DocumentRepository;
import com.example.archivesproject.repository.PaysRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class ExcelImporterService {

    private final AccordRepository accordRepository;
    private final BoiteRepository boiteRepository;
    private final DocumentRepository documentRepository;

    public ExcelImporterService(AccordRepository accordRepository,
                                BoiteRepository boiteRepository,
                                 DocumentRepository documentRepository) {
        this.accordRepository = accordRepository;
        this.boiteRepository = boiteRepository;
        this.documentRepository = documentRepository;

    }

    public void importerDepuisExcel(MultipartFile file) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String numeroDossier = row.getCell(0).getStringCellValue();
                String intitule = row.getCell(1).getStringCellValue();
                String observation = row.getCell(2).getStringCellValue();
                String pdfFilenames = row.getCell(3).getStringCellValue(); // a.pdf,b.pdf

                Accord accord = new Accord();
                accord.setNumeroDossier(numeroDossier);
                accord.setIntitule(intitule);
                accord.setObservation(observation);

                // Tu peux associer une boîte fixe ou dynamique ici selon ta logique
                Boite boite = boiteRepository.findById(1).orElse(null);
                accord.setBoite(boite);

                // Sauvegarde de l'accord
                Accord savedAccord = accordRepository.save(accord);

                // Enregistrer chaque PDF comme Document
                if (pdfFilenames != null && !pdfFilenames.isEmpty()) {
                    String[] filenames = pdfFilenames.split(",");
                    for (String filename : filenames) {
                        Document doc = new Document();
                        doc.setFilename(filename.trim()); // retire les espaces
                        doc.setAccord(savedAccord);
                        documentRepository.save(doc);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // ou loguer proprement
        }
    }
}