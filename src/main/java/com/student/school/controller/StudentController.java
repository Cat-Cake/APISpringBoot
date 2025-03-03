package com.student.school.controller;

import java.util.List;
import java.util.Optional;

import com.student.school.entity.Student;
import com.student.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentRepository studentsRepo;
    
    @PostMapping("/student")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
    	try {
    	    Student savedStudent = studentsRepo.save(student);
    	    return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    	} catch (Exception e) {
    		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }

    @GetMapping(path = "/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> list = studentsRepo.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping(path = "/student")
    public ResponseEntity<Student> getStudent(@RequestParam(name = "studentId") long studentId) {
    	try {
            if (studentsRepo.existsById(studentId)) {
            	Optional<Student> student = studentsRepo.findById(studentId);
            	return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PatchMapping("/student")
    public ResponseEntity<Student> updateStudent(@RequestParam(name = "studentId") long studentId, @RequestBody Student studentDetails) {
    	try {
            Optional<Student> optionalStudent = studentsRepo.findById(studentId);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                
                if (studentDetails.getFirstName() != null) student.setFirstName(studentDetails.getFirstName());
                if (studentDetails.getLastName() != null) student.setLastName(studentDetails.getLastName());
                if (studentDetails.getBirthDate() != null) student.setBirthDate(studentDetails.getBirthDate());

                Student updatedStudent = studentsRepo.save(student);
                return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping(path = "/student")
    public ResponseEntity<Student> deleteStudent(@RequestParam(name = "studentId") long studentId) {
    	try {
            if (studentsRepo.existsById(studentId)) {
                studentsRepo.deleteById(studentId);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
