package tansinjahan.tdd.assignment;

import java.util.ArrayList;
import java.util.List;

import server.logic.tables.StudentTable;

public class Student {
	private int studentNumber;
	private String studentName;
	private String status;
	private boolean fullTime;
	private int maxCourseCount;
	
	List<Integer> courseIDs=new ArrayList<>();
	
	public Student(String name,int studentID, String studentStatus){
		this.studentNumber=studentID;
		this.studentName=name;
		setMaxCourseOfStudent(studentStatus);
	}
	
	public String getName() {
		return studentName;
	}

	public int getStudentNumber() {
		return studentNumber;
}

	public void setMaxCourseOfStudent(String studentStatus) {
		if(studentStatus == "full time"){
			maxCourseCount = 4;
		}
		else 
			 if(studentStatus == "part time")
				 maxCourseCount = 2;
			 else
				 maxCourseCount = 0;
		
	}
	
	public String registerCourse(Course course){
		courseIDs.add(course.getCode());
		return "student registered for course";
	}

	public List<Course> currentCourses() {
		return CourseTable.getInstance().getCourses(courseIDs);
	}
	

}
