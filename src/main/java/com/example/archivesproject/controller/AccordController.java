package com.example.archivesproject.controller;

import com.example.archivesproject.model.Accord;
import com.example.archivesproject.model.Boite;
import com.example.archivesproject.model.Document;
import com.example.archivesproject.model.Pays;
import com.example.archivesproject.repository.AccordRepository;
import com.example.archivesproject.repository.BoiteRepository;
import com.example.archivesproject.repository.DocumentRepository;
import com.example.archivesproject.repository.PaysRepository;
import com.example.archivesproject.service.AccordService;
import com.example.archivesproject.service.BoiteService;
import com.example.archivesproject.service.PaysService;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.UUID;

@Controller
@RequestMapping("/accords")
public class AccordController {
    private final AccordService accordService;
    private final BoiteRepository boiteRepository;
    private final PaysService paysService;
    private final BoiteService boiteService;
    private final AccordRepository accordRepository;
    private final PaysRepository paysRepository;
    private final DocumentRepository documentRepository;


    public AccordController(AccordService accordService, BoiteRepository boiteRepository, PaysService paysService, BoiteService boiteService, AccordRepository accordRepository, PaysRepository paysRepository, DocumentRepository documentRepository) {
        this.accordService = accordService;
        this.boiteService = boiteService;
        this.accordRepository = accordRepository;
        this.boiteRepository = boiteRepository;
        this.paysService = paysService;
        this.paysRepository = paysRepository;

        this.documentRepository = documentRepository;
    }


    @GetMapping
    public String listAccords(Model model) {
        List<Accord> accords = accordService.getAll();
        model.addAttribute("accords", accords);

        return "index";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("accord", new Accord());
        model.addAttribute("boites", boiteRepository.findAll()); // pour choisir une boîte
        return "formulaire";
    }

    @PostMapping("/save")
    public String saveAccord(@ModelAttribute Accord accord,
                             @RequestParam("pdfFiles") MultipartFile[] pdfFiles) throws IOException {

        String uploadDir = "C:/uploads";

        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        List<Document> documents = new ArrayList<>();

        for (MultipartFile pdfFile : pdfFiles) {
            if (!pdfFile.isEmpty()) {
                String pdfFilename = UUID.randomUUID().toString() + "_" + pdfFile.getOriginalFilename();
                File destFile = new File(uploadDirFile, pdfFilename);
                pdfFile.transferTo(destFile);

                Document document = new Document();
                document.setFilename(pdfFilename);
                document.setAccord(accord);  // lien vers accord
                documents.add(document);     // ajoute à la liste
            }
        }

        accord.setDocuments(documents); // met la liste dans l'accord
        accordRepository.save(accord);  // enregistre tout

        return "redirect:/accords";
    }




    @GetMapping("/recherche")
    public String showSearchForm(Model model) {
        model.addAttribute("paysList", paysRepository.findAll()); // liste des pays
        return "recherche-par-pays";
    }

    @GetMapping("/chercher")
    public String redirectToAccords(@RequestParam("nom") String nomPays) {
        return "redirect:/accords/pays/" + nomPays;
    }

    @GetMapping("/pays/{nom}")
    public String accordsParPays(@PathVariable String nom, Model model) {
        List<Accord> accords = accordService.getByPays(nom);
        model.addAttribute("accords", accords);
        model.addAttribute("nomPays", nom);
        return "accords-par-pays";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccord(@PathVariable("id") Integer id) {
        accordService.deleteById(id);
        return "redirect:/accords";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Accord accord = accordService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Accord non trouvé : " + id));

        model.addAttribute("accord", accord);  // plus un Optional
        model.addAttribute("documents", accord.getDocuments()); // liste des documents liés
        model.addAttribute("boites", boiteRepository.findAll());

        return "formulaire";
    }


    @GetMapping("/view-pdf/{pdfFilename}")
    public void viewPdf(@PathVariable String pdfFilename, HttpServletResponse response) throws IOException {
        File pdfFile = new File("C:/uploads/" + pdfFilename);

        if (!pdfFile.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            return;
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"" + pdfFilename + "\"");
        Files.copy(pdfFile.toPath(), response.getOutputStream());
        response.getOutputStream().flush();
    }


    //ajouter un pays
    @GetMapping("/ajoutpays")
    public String showPaysForm(Model model) {
        model.addAttribute("pay", new Pays());
        return "form-pays";}


      @PostMapping("/savepays")
        public String savePays(@ModelAttribute Pays pay)
       {
           paysService.save(pay);
        return "redirect:/accords";
    }

//ajouter une boite
@GetMapping("/ajoutboite")
public String showBoiteForm(Model model) {
    model.addAttribute("paysList", paysRepository.findAll());
    model.addAttribute("boite", new Boite());
    return "form-boite";
    }


    @PostMapping("/ajouterboites")
    public String addBoite(@ModelAttribute Boite boite) {

       Integer paysId = boite.getPays().getId();
        Pays pays = paysRepository.findById(paysId).orElse(null);
        boite.setPays(pays);

        boiteRepository.save(boite);
        return "redirect:/accords/listeDesBoites";
    }

    @GetMapping("/listeDesBoites")
    public String listeBoites(Model model) {
        model.addAttribute("boites", boiteRepository.findAll());
        return "boite_liste";
    }


@GetMapping("/intitule")
public String showIntituleSearchForm() {
    return "recherche-intitule";
}

    @GetMapping("/chercher-intitule")
    public String chercherParIntitule(@RequestParam("motCle") String motCle, Model model) {
        List<Accord> accords = accordService.getByMotCle(motCle);
        model.addAttribute("accords", accords);
        model.addAttribute("motCle", motCle);
        return "resultats-recherche-intitule";
    }











}


