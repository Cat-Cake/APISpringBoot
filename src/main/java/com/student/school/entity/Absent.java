package com.student.school.entity;

import jakarta.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Absent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date absentDate;

    private String reason;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    public Absent(Date absentDate, String reason, Student student) {
        this.absentDate = absentDate;
        this.reason = reason;
        this.student = student;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getAbsentDate() { return absentDate; }
    public void setAbsentDate(Date absentDate) { this.absentDate = absentDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
}