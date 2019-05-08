package sk.hopvinna.coursetracker.daoservice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sk.hopvinna.coursetracker.entity.Course;
import sk.hopvinna.coursetracker.entity.CourseToAdd;
import sk.hopvinna.coursetracker.entity.Instructor;
import sk.hopvinna.coursetracker.entity.InstructorAllDetail;
import sk.hopvinna.coursetracker.entity.InstructorDetail;
import sk.hopvinna.coursetracker.entity.Student;
import sk.hopvinna.coursetracker.entity.StudentToAdd;

@Repository
public class DaoService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	class InstructorRowMapper implements RowMapper<Instructor> {

		@Override
		public Instructor mapRow(ResultSet rs, int rowNum) throws SQLException {
			Instructor instructor = new Instructor();
			instructor.setId(rs.getInt("id"));
			instructor.setFirstName(rs.getString("first_name"));
			instructor.setLastName(rs.getString("last_name"));
			instructor.setEmail(rs.getString("email"));
			return instructor;
		}
		
	}
	
	class CourseRowMapper implements RowMapper<Course> {

		@Override
		public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
			Course course = new Course();
			course.setId(rs.getInt("id"));
			course.setTitle(rs.getString("title"));
			return course;
		}
		
	}
	
	class StudentRowMapper implements RowMapper<Student> {

		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student student = new Student();
			student.setId(rs.getInt("id"));
			student.setFirstName(rs.getString("first_name"));
			student.setLastName(rs.getString("last_name"));
			student.setEmail(rs.getString("email"));
			return student;
		}
		
	}
	
	@Autowired
	private EntityManager entityManager;

	public List<Instructor> findAllInstructor() {

		return jdbcTemplate.query("select * from instructor", new InstructorRowMapper());
	}
	
	public List<Course> findAllCourse() {

		return jdbcTemplate.query("select * from course", new CourseRowMapper());			
	}
	
	public List<Student> findAllStudent() {

		return jdbcTemplate.query("select * from student", new StudentRowMapper());			
	}

	public Instructor findOneInstructor(int id) {
		Instructor instructor = entityManager.find(Instructor.class, id);
		return instructor;
	}
	
	public Course findOneCourse(int id) {
		Course course = entityManager.find(Course.class, id);
		
		return course;
	}
	
	public Student findOneStudent(int id) {
		Student student = entityManager.find(Student.class, id);
		return student;
	}

	@Transactional
	public InstructorAllDetail addInstructor(InstructorAllDetail instructorAllDetail) {

		Instructor instructor =	new Instructor(instructorAllDetail.getFirstName(), instructorAllDetail.getLastName(), instructorAllDetail.getEmail());
		
		InstructorDetail instructorDetail =	new InstructorDetail(instructorAllDetail.getYoutubeChannel(), instructorAllDetail.getHobby());
		
		instructor.setInstructorDetail(instructorDetail);
		
		entityManager.merge(instructor);
		
		return instructorAllDetail;
	}

	@Transactional
	public Instructor removeOneInstructor(int id) {
		
		Instructor instructor = entityManager.find(Instructor.class, id);
		
		entityManager.remove(instructor);
		
		return instructor;
	}

	@Transactional
	public CourseToAdd addCourseToInstructor(CourseToAdd courseToAdd) {
		
		Instructor instructor = entityManager.find(Instructor.class, courseToAdd.getInstructorId());
		
		Course course = new Course(courseToAdd.getCourse());
		
		instructor.add(course);
		
		entityManager.merge(instructor);
		
		return courseToAdd;
	}

	@Transactional
	public Course removeOneCourse(int id) {
		Course course = entityManager.find(Course.class, id);
		
		entityManager.remove(course);
		
		return course;
	}

	@Transactional
	public StudentToAdd addStudent(StudentToAdd studentToAdd) {
		Student student = new Student(studentToAdd.getFirstName(), studentToAdd.getLastName(), studentToAdd.getEmail());
		
		entityManager.merge(student);
		
		return studentToAdd;
	}

	@Transactional
	public Student removeOneStudent(int id) {
		Student student = entityManager.find(Student.class, id);
		
		entityManager.remove(student);
		
		return student;
	}
}
