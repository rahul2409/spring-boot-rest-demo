package com.rest.springbootdemo.demo.RestController;

// Basic imports 
import java.util.*;

// Project specific imports
import com.rest.springbootdemo.demo.entity.*;
import com.rest.springbootdemo.demo.RestController.StudentErrorResponse;

import jakarta.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/api")
public class StudentRestController {
	
	List<Student> theStudents;

	// Use @PostConstruct to load the student data
	@PostConstruct
	public void loadData() {
		theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Rahul", "Nandrajog"));
		theStudents.add(new Student("Walsh", "Fernandes"));
		theStudents.add(new Student("Rohan", "Tiwari"));
		theStudents.add(new Student("Chirag", "Bellara"));
	}
	
	// Get student by id endpoint is ("/api/student/{studentId}")
	@GetMapping("/students/{studentId}")
	public Student getStudentById(@PathVariable int studentId) {
		// Check the studentId against list size 
		if(studentId >= theStudents.size() || (studentId<0)) {
			throw new StudentNotFoundException("Student not found - "+ studentId);
		}
		return theStudents.get(studentId);
	}
	
	// Get all the students 
	@GetMapping("/students")
	public List<Student> getStudents(){
		return theStudents;
	}
	
}
