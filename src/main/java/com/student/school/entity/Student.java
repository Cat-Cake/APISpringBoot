package com.student.school.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String birthDate;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    @JsonBackReference
    private Classe classe;

    @OneToMany(mappedBy = "student")
    @JsonManagedReference
    private List<Grade> grades;

    @OneToMany(mappedBy = "student")
    private List<Absent> absences;

    public Student() {}

    public Student(String firstName, String lastName, String birthDate, Classe classe) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.classe = classe;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public Classe getClasse() { return classe; }
    public void setClasse(Classe classe) { this.classe = classe; }

    public List<Grade> getGrades() { return grades; }
    public void setGrades(List<Grade> grades) { this.grades = grades; }

    public List<Absent> getAbsences() { return absences; }
    public void setAbsences(List<Absent> absences) { this.absences = absences; }
}