package com.student.school.repository;

import com.student.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface StudentRepository extends JpaRepository<Student, Long> {

	  // public static Student saveStudent(Student student) {
	//	   return StudentRepository.saveStudent(student);
	   //}
	
}