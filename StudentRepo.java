package com.collegefes.CollegeFest.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collegefes.CollegeFest.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{

	List<Student> findAll();
	
	

}
