package com.example.archivesproject.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Accord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String numeroDossier;
    private String intitule;
    private String observation;

    @ManyToOne
    @JoinColumn(name = "boite_id")
    private Boite boite;




    @OneToMany(mappedBy = "accord", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Document> documents = new ArrayList<>();

    // Getters and setters







    public int getId() {
        return id;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getObservation() {
        return observation;
    }

    public Boite getBoite() {
        return boite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public void setBoite(Boite boite) {
        this.boite = boite;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

}


