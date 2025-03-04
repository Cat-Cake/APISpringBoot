package com.student.school.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.student.school.entity.Absent;
import com.student.school.entity.Classe;
import com.student.school.entity.Student;
import com.student.school.repository.AbsentRepository;

class AbsentControllerTest {
	
	@Mock
    private AbsentRepository absentRepository;

    @InjectMocks
    private AbsentController absentController;
    
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testGetAllStudent() {
    	
        Classe classe = new Classe("Classe A");
        Student student = new Student("Alice", "Durand", "2000-05-12", classe);
        
    	List<Absent> absents = Arrays.asList(
    		new Absent(new Date(), "test", student),
    		new Absent(new Date(), "test", student)
        );
    	
    	
    	when(absentRepository.findAll()).thenReturn(absents);
    	ResponseEntity<List<Absent>> response = absentController.getAllAbsents();
    	
    	assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(absentRepository, times(1)).findAll();
    }
    
    
    
    
}