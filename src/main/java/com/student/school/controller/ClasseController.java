package com.student.school.controller;

import com.student.school.entity.Classe;
import com.student.school.repository.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClasseController {
    @Autowired
    ClasseRepository classesRepo;

    @GetMapping(path = "/classes")
    public ResponseEntity<List<Classe>> getAllClasses() {
        try {
            List<Classe> list = classesRepo.findAll();

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/classe")
    public ResponseEntity<Classe> saveClasse(@RequestBody Classe classe) {
    	try {
    	    Classe savedClasse = classesRepo.save(classe);
    	    return new ResponseEntity<>(savedClasse, HttpStatus.CREATED);
    	} catch (Exception e) {
    		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @GetMapping(path = "/classe")
    public ResponseEntity<Classe> getClasse(@RequestParam(name = "classeId") long classeId) {
    	try {
            if (classesRepo.existsById(classeId)) {
            	Optional<Classe> grade = classesRepo.findById(classeId);
            	return grade.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PatchMapping(path = "/classe")
    public ResponseEntity<Classe> updateClasse(@RequestParam(name = "classeId") long classeId, @RequestBody Classe classeDetails) {
        try {
            Optional<Classe> optionalClasse = classesRepo.findById(classeId);
            if (optionalClasse.isPresent()) {
                Classe classe = optionalClasse.get();
                
                if (classeDetails.getName() != null) classe.setName(classeDetails.getName());

                Classe updatedClasse = classesRepo.save(classe);
                return new ResponseEntity<>(updatedClasse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping(path = "/classe")
    public ResponseEntity<Classe> deleteClasse(@RequestParam(name = "classeId") long classeId) {
    	try {
            if (classesRepo.existsById(classeId)) {
            	classesRepo.deleteById(classeId);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
