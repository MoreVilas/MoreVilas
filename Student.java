package com.collegefes.CollegeFest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	public String fname;
	public String lname;
	public String course;
	public String country;
	
	public Student(String fname, String lname, String course, String country) {
	     super();
		this.fname = fname;
		this.lname = lname;
		this.course = course;
		this.country = country;
	}
	

}
