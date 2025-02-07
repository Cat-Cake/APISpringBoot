package com.student.school.repository;

import com.student.school.entity.Grade;
import com.student.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface GradeRepository extends JpaRepository<Grade, Long> {

}