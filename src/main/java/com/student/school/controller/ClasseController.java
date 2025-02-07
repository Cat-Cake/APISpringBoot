package com.student.school.controller;

import com.student.school.entity.Classe;
import com.student.school.entity.Grade;
import com.student.school.repository.ClasseRepository;
import com.student.school.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClasseController {
    @Autowired
    ClasseRepository classesRepo;

    @GetMapping(path = "/classes")
    public ResponseEntity<List<Classe>> getAllClasses() {
        try {
            List<Classe> list = classesRepo.findAll();

//            if (list.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
