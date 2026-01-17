package com.example.archivesproject.controller;

import com.example.archivesproject.model.Accord;
import com.example.archivesproject.model.Continent;
import com.example.archivesproject.repository.ContinentRepository;
import com.example.archivesproject.repository.PaysRepository;
import com.example.archivesproject.service.ContinentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/continents")
public class ContinentController {


    private final ContinentRepository continentRepository;
    private final PaysRepository paysRepository;

    public ContinentController(ContinentRepository continentRepository, PaysRepository paysRepository) {
        this.continentRepository = continentRepository;
        this.paysRepository = paysRepository;
    }


    @GetMapping
    public String listContinents(Model model) {
        List<Continent> continents = continentRepository.findAll();
        model.addAttribute("continents", continents);

        return "continent_list";
    }

    // Page des pays d'un continent
    @GetMapping("/{id}/pays")
    public String showPaysByContinent(@PathVariable Long id, Model model) {
        Continent continent = continentRepository.findById(id).orElse(null);
        if (continent == null) {
            return "redirect:/";
        }
        model.addAttribute("continent", continent);
        model.addAttribute("paysList", continent.getPays());
        return "pays-par-continent"; // fichier HTML
    }
}


