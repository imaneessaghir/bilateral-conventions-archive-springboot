package com.example.archivesproject.model;

import jakarta.persistence.*;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String filename; // Nom du fichier PDF

    @ManyToOne
    @JoinColumn(name = "accord_id")
    private Accord accord;

    // getters/setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Accord getAccord() {
        return accord;
    }

    public void setAccord(Accord accord) {
        this.accord = accord;
    }
}

