package com.student.school.controller;

import com.student.school.entity.Grade;
import com.student.school.entity.Student;
import com.student.school.repository.GradeRepository;
import com.student.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GradeController {
    @Autowired
    GradeRepository gradesRepo;

    @GetMapping(path = "/grades")
    public ResponseEntity<List<Grade>> getAllGrades() {
        try {
            List<Grade> list = gradesRepo.findAll();

//            if (list.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
