package sk.hopvinna.coursetracker.entity;

public class CourseToAdd {
	
	private Integer instructorId;
	private String course;
	
	public CourseToAdd() {
		
	}
	
	public CourseToAdd(Integer instructorId, String course) {
		this.instructorId = instructorId;
		this.course = course;
	}

	public Integer getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Integer instructorId) {
		this.instructorId = instructorId;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
}
