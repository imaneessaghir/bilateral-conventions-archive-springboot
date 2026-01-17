package com.example.archivesproject.model;

import jakarta.persistence.*;
import java.util.List;
@Entity
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    @OneToMany(mappedBy = "pays", cascade = CascadeType.ALL)
    private List<Boite> boites;


    @ManyToOne
    @JoinColumn(name = "continent_id")
    private Continent continent;


    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Boite> getBoites() {
        return boites;
    }

    public void setBoites(List<Boite> boites) {
        this.boites = boites;
    }
}
