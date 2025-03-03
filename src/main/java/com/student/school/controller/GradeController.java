package com.student.school.controller;

import com.student.school.entity.Grade;
import com.student.school.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GradeController {
    @Autowired
    GradeRepository gradesRepo;
    
    @PostMapping("/grade")
    public ResponseEntity<Grade> saveGarde(@RequestBody Grade grade) {
    	try {
    	    Grade savedGrade = gradesRepo.save(grade);
    	    return new ResponseEntity<>(savedGrade, HttpStatus.CREATED);
    	} catch (Exception e) {
    		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }

    @GetMapping(path = "/grades")
    public ResponseEntity<List<Grade>> getAllGrades() {
        try {
            List<Grade> list = gradesRepo.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping(path = "/grade")
    public ResponseEntity<Grade> getGrade(@RequestParam(name = "gradeId") long gradeId) {
    	try {
            if (gradesRepo.existsById(gradeId)) {
            	Optional<Grade> grade = gradesRepo.findById(gradeId);
            	return grade.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PatchMapping(path = "/grade")
    public ResponseEntity<Grade> updateGrade(@RequestParam(name = "gradeId") long gradeId, @RequestBody Grade gradeDetails) {
        try {
            Optional<Grade> optionalGrade = gradesRepo.findById(gradeId);
            if (optionalGrade.isPresent()) {
                Grade grade = optionalGrade.get();
                
                if (gradeDetails.getSubject() != null) grade.setSubject(gradeDetails.getSubject());
                if (gradeDetails.getScore() != null) grade.setScore(gradeDetails.getScore());
                if (gradeDetails.getStudent() != null) grade.setStudent(gradeDetails.getStudent());

                Grade updatedGrade = gradesRepo.save(grade);
                return new ResponseEntity<>(updatedGrade, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping(path = "/grade")
    public ResponseEntity<Grade> deleteGrade(@RequestParam(name = "gradeId") long gradeId) {
    	try {
            if (gradesRepo.existsById(gradeId)) {
            	gradesRepo.deleteById(gradeId);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
