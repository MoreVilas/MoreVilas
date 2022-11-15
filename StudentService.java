package com.collegefes.CollegeFest.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.collegefes.CollegeFest.entity.Student;

@Service
public interface StudentService {
	
	public List<Student> findAll();
	
	public void save(Student theStudent);
	
	public void deleteById(int Id);
	
	public Student findById(int theId);

}
