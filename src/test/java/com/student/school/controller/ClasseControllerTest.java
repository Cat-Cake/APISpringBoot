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
import com.student.school.repository.ClasseRepository;

class ClasseControllerTest {
	
	@Mock
    private ClasseRepository classeRepository;

    @InjectMocks
    private ClasseController classeController;
    
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testGetAllStudent() {
        
    	List<Classe> classes = Arrays.asList(
    		new Classe("Classe 1"),
            new Classe("Classe 2")
        );
    	
    	
    	when(classeRepository.findAll()).thenReturn(classes);
    	ResponseEntity<List<Classe>> response = classeController.getAllClasses();
    	
    	assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(classeRepository, times(1)).findAll();
    }
    
    
    
    
}