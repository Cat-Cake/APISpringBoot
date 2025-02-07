package com.student.school.controller;

import java.util.List;

import com.student.school.entity.Student;
import com.student.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentRepository studentsRepo;

    @GetMapping(path = "/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> list = studentsRepo.findAll();

//            if (list.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
