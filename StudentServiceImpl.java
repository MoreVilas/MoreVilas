package com.collegefes.CollegeFest.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.collegefes.CollegeFest.entity.Student;
import com.collegefes.CollegeFest.repo.StudentRepo;

public class StudentServiceImpl implements StudentService{
	
	
@Autowired
private StudentRepo studentRepo;

@Override
public List<Student> findAll() {
	// TODO Auto-generated method stub
	return studentRepo.findAll();
}

@Override
public void save(Student theStudent) {
	// TODO Auto-generated method stub
	studentRepo.save(theStudent);
}

@Override
public void deleteById(int Id) {
	// TODO Auto-generated method stub
	studentRepo.deleteById(Id);
}

@Override
public Student findById(int theId) {
	// TODO Auto-generated method stub
	return studentRepo.findById(theId).get();
}

}
