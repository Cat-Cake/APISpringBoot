package com.student.school.controller;

import com.student.school.entity.Absent;
import com.student.school.entity.Classe;
import com.student.school.repository.AbsentRepository;
import com.student.school.repository.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AbsentController {
    @Autowired
    AbsentRepository absentsRepo;

    @GetMapping(path = "/absents")
    public ResponseEntity<List<Absent>> getAllAbsents() {
        try {
            List<Absent> list = absentsRepo.findAll();

//            if (list.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
