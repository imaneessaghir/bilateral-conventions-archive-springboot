package com.example.archivesproject.controller;

import com.example.archivesproject.service.AccordService;
import com.example.archivesproject.service.ExcelImporterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Controller

public class BaseController {
    private final AccordService accordService;
    private ExcelImporterService excelImporterService;
    public BaseController(AccordService accordService, ExcelImporterService excelImporterService) {
        this.accordService = accordService;
        this.excelImporterService = excelImporterService;
    }




    @GetMapping("/import-excel")
    public String importExcel() {
        try (InputStream is = getClass().getResourceAsStream("/importer/exemple_accords.xlsx")) {
            if (is == null) {
                // Si fichier non trouvé, rediriger avec message d'erreur
                return "redirect:/accords?message=fichier-non-trouve";
            }
            excelImporterService.importerDepuisExcel((MultipartFile) is);
            // Redirection avec paramètre message indiquant succès
            return "redirect:/accords?message=import-reussi";
        } catch (Exception e) {
            // Redirection avec message d'erreur
            return "redirect:/accords?message=erreur-import";
        }
    }

}
