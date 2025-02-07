package com.student.school.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Absent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date absenceDate;

    private String reason;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    protected Absent() {}

    public Absent(Date absenceDate, String reason, Student student) {
        this.absenceDate = absenceDate;
        this.reason = reason;
        this.student = student;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getAbsenceDate() { return absenceDate; }
    public void setAbsenceDate(Date absenceDate) { this.absenceDate = absenceDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
}