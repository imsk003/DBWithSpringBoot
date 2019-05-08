package sk.hopvinna.coursetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sk.hopvinna.coursetracker.daoservice.DaoService;
import sk.hopvinna.coursetracker.entity.Course;
import sk.hopvinna.coursetracker.entity.CourseToAdd;
import sk.hopvinna.coursetracker.entity.Instructor;
import sk.hopvinna.coursetracker.entity.InstructorAllDetail;
import sk.hopvinna.coursetracker.entity.Student;
import sk.hopvinna.coursetracker.entity.StudentToAdd;

@RestController
public class CourseTrackerController {

	@Autowired
	private DaoService daoService;
	
	@GetMapping("/instructor")
	public List<Instructor> retrieveAllInstructor() {
		return daoService.findAllInstructor();
	}
	
	@GetMapping("/course")
	public List<Course> retrieveAllCourse() {
		return daoService.findAllCourse();
	}
	
	@GetMapping("/student")
	public List<Student> retrieveAllStudent() {
		return daoService.findAllStudent();
	}
	
	@GetMapping("/instructor/{id}")
	public Instructor retrieveOneInstructor(@PathVariable int id) {
		return daoService.findOneInstructor(id);
	}
	
	@GetMapping("/course/{id}")
	public Course retrieveOneCourse(@PathVariable int id) {
		return daoService.findOneCourse(id);
	}
	
	@GetMapping("/student/{id}")
	public Student retrieveOneStudent(@PathVariable int id) {
		return daoService.findOneStudent(id);
	}
	
	@PostMapping("/instructor")
	public InstructorAllDetail createUser(@RequestBody InstructorAllDetail instructorAllDetail) {
		
		InstructorAllDetail addedInstructorAllDetail = daoService.addInstructor(instructorAllDetail);
		
		return addedInstructorAllDetail;
	}
	
	@DeleteMapping("/instructor/{id}")
	public Instructor deleteOneInstructor(@PathVariable int id) {
		return daoService.removeOneInstructor(id);
	}
	
	@PostMapping("/addCourse")
	public CourseToAdd addCourseToInstructor(@RequestBody CourseToAdd courseToAdd) {
		return daoService.addCourseToInstructor(courseToAdd);
	}
	
	@DeleteMapping("/course/{id}")
	public Course deleteOneCourse(@PathVariable int id) {
		return daoService.removeOneCourse(id);
	}
	
	@PostMapping("/addStudent")
	public StudentToAdd addStudent(@RequestBody StudentToAdd studentToAdd) {
		return daoService.addStudent(studentToAdd);
	}
	
	@DeleteMapping("/student/{id}")
	public Student deleteOneStudent(@PathVariable int id) {
		return daoService.removeOneStudent(id);
	}
}
