package com.student.school.entity;

import jakarta.persistence.*;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;
    private Double score;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    protected Grade() {}

    public Grade(String subject, Double score, Student student) {
        this.subject = subject;
        this.score = score;
        this.student = student;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
}