package tn.esprit.course.entities;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namecourse;
    private String description;
    private int nbhours;
    private String pdfcourse;

    // Constructors
    public Course() {
    }

    public Course(Long id, String namecourse, String description, int nbhours, String pdfcourse) {
        this.id = id;
        this.namecourse = namecourse;
        this.description = description;
        this.nbhours = nbhours;
        this.pdfcourse = pdfcourse;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNamecourse() {
        return namecourse;
    }

    public String getDescription() {
        return description;
    }

    public int getNbhours() {
        return nbhours;
    }

    public String getPdfcourse() {
        return pdfcourse;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNamecourse(String namecourse) {
        this.namecourse = namecourse;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNbhours(int nbhours) {
        this.nbhours = nbhours;
    }

    public void setPdfcourse(String pdfcourse) {
        this.pdfcourse = pdfcourse;
    }
}
