package com.student.school.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.student.school.entity.Classe;
import com.student.school.entity.Grade;
import com.student.school.entity.Student;
import com.student.school.repository.ClasseRepository;
import com.student.school.repository.GradeRepository;

class GradeControllerTest {
	
	@Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private GradeController gradeController;
    
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testGetAllStudent() {
    	
        Classe classe = new Classe("Classe A");
        Student student = new Student("Alice", "Durand", "2000-05-12", classe);
        
    	List<Grade> grades = Arrays.asList(
    		new Grade("Math", 15.4, student)
        );
    	
    	
    	when(gradeRepository.findAll()).thenReturn(grades);
    	ResponseEntity<List<Grade>> response = gradeController.getAllGrades();
    	
    	assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(gradeRepository, times(1)).findAll();
    }
    
    
    
    
}