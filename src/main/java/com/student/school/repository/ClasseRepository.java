package com.student.school.repository;

import com.student.school.entity.Classe;
import com.student.school.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface ClasseRepository extends JpaRepository<Classe, Long> {

}