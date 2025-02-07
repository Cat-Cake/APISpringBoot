package com.student.school.repository;

import com.student.school.entity.Absent;
import com.student.school.entity.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface AbsentRepository extends JpaRepository<Absent, Long> {

}