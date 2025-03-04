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
import com.student.school.entity.Student;
import com.student.school.repository.ClasseRepository;
import com.student.school.repository.StudentRepository;

class StudentControllerTest {
	@Mock
    private StudentRepository studentRepository;
	
	@Mock
    private ClasseRepository classeRepository;

    @InjectMocks
    private StudentController studentController;
    
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testGetAllStudent() {
        Classe classe = new Classe("Classe A");

    	
    	List<Student> students = Arrays.asList(
    			new Student("Alice", "Durand", "2000-05-12", classe),
                new Student("Bob", "Martin", "2001-09-25", classe)
            );
    	
    	
    	when(studentRepository.findAll()).thenReturn(students);
    	ResponseEntity<List<Student>> response = studentController.getAllStudents();
    	
    	assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(studentRepository, times(1)).findAll();
    }
    
    
    
    
}