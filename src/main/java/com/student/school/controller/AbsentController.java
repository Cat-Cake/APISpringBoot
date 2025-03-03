package com.student.school.controller;

import com.student.school.entity.Absent;
import com.student.school.repository.AbsentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AbsentController {
    @Autowired
    AbsentRepository absentsRepo;

    @GetMapping(path = "/absents")
    public ResponseEntity<List<Absent>> getAllAbsents() {
        try {
            List<Absent> list = absentsRepo.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/absent")
    public ResponseEntity<Absent> saveAbsent(@RequestBody Absent absent) {
        try {
            Absent savedAbsent = absentsRepo.save(absent);
            return new ResponseEntity<>(savedAbsent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping(path = "/absent")
    public ResponseEntity<Absent> getAbsent(@RequestParam(name = "absentId") long absentId) {
        try {
            Optional<Absent> optionalAbsent = absentsRepo.findById(absentId);
            return optionalAbsent.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                                 .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PatchMapping(path = "/absent")
    public ResponseEntity<Absent> updateAbsent(@RequestParam(name = "absentId") long absentId, @RequestBody Absent absentDetails) {
        try {
            Optional<Absent> optionalAbsent = absentsRepo.findById(absentId);
            if (optionalAbsent.isPresent()) {
                Absent absent = optionalAbsent.get();

                if (absentDetails.getAbsentDate() != null) absent.setAbsentDate(absentDetails.getAbsentDate());
                if (absentDetails.getReason() != null) absent.setReason(absentDetails.getReason());

                Absent updatedAbsent = absentsRepo.save(absent);
                return new ResponseEntity<>(updatedAbsent, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping(path = "/absent")
    public ResponseEntity<Void> deleteAbsent(@RequestParam(name = "absentId") long absentId) {
        try {
            if (absentsRepo.existsById(absentId)) {
                absentsRepo.deleteById(absentId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}