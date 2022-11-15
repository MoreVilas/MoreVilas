package com.collegefes.CollegeFest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.collegefes.CollegeFest.entity.Student;
import com.collegefes.CollegeFest.serviceImpl.StudentService;

@Controller
@RequestMapping("/api/students")
public class StudentController {
	

	private StudentService studentService;

	@GetMapping("/findAll")
	public String findAll(Map<String, List<Student>> map) {
		
		List<Student> students = this.studentService.findAll();
		map.put("Students", students);
		return "studentList";

	}

	@GetMapping("/findById")
	public String findById(@RequestParam("studentId") int id) {
		studentService.findById(id);
		return "student";
	}

	@RequestMapping("/deleteById")
	public String deleteById(@RequestParam("id") int id) {
		studentService.deleteById(id);
		return "redirect:studentList";
	}

	@RequestMapping("/save")
	public String saveStudent(Student student) {
		Student theStudent;
		if (student.getId() != 0) {
			theStudent = studentService.findById(student.getId());
			theStudent.setFname(student.getFname());
			theStudent.setLname(student.getLname());
			theStudent.setCountry(student.getCountry());
			theStudent.setCourse(student.getCourse());
		} else {
			theStudent = new Student(student.getFname(), student.getLname(), student.getCountry(), student.getCourse());
			studentService.save(theStudent);
		}
		return "redirect:studentList";
	}
    @RequestMapping("/showformForAdd")
    public String showformForAdd(Model theModel) {
    	Student theStudent = new Student();
    	
    	theModel.addAttribute("Students", theStudent);
    	return "studentAddForm";
    }
    @RequestMapping("/showformForUpdate")
    public String showformForUpdate(@RequestParam("studentId") int id, Model theModel) {
    	
    	Student theStudent = studentService.findById(id);
    	
    	theModel.addAttribute("Student", theStudent);
    	return "studentForm";
    }
}
