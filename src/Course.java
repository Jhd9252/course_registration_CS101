import java.util.ArrayList;

/*
[x] class Course
	course name
	course id
	max students
	current students
	list of names
	course intructor
	course section number
	course location
*/

public class Course implements Comparable<Course>{
	String courseName; // in constructor
	String courseID; // in constructor
	int maxStudents; // in constructor
	int currentStudents;
	ArrayList<String> names = new ArrayList<String>();
	String courseInstructor; // in constructor
	int sectionNum; // in constructor
	String location; // in constructor
	
	Course(){}
	Course(String a, String b, int c, String d, int e, String f) {

		courseName = a;
		courseID = b;
		maxStudents = c;
		courseInstructor = d;
		sectionNum = e;
		location = f;
	}
	
	@Override
	public int compareTo(Course a){
		return this.getCurrentStudents() - a.getCurrentStudents();
	}
	
	// getters
	String getCourseName () { return courseName; }
	String getCourseID () {return courseID;}
	int getMaxStudents () {return maxStudents;}
	int getCurrentStudents () {return currentStudents;}
	ArrayList<String> getNames () {return names;}
	String getCourseInstructor () {return courseInstructor;}
	int getSectionNum () {return sectionNum;}
	String getLocation () {return location;}
	
	// setters
	void setCourseName (String a) {courseName = a;}
	void setCourseID (String a) {courseID = a;}
	void setMaxStudents (int a) {maxStudents = a;}
	void setCurrentStudents (int a) {currentStudents = a;}
	void setNames (ArrayList<String> a) {names = a;}
	void setCourseInstructor (String a) {courseInstructor = a;}
	void setSectionNum (int a) {sectionNum = a;}
	void setLocation (String a) {location = a;}
}
