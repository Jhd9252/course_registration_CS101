// imports
import java.util.ArrayList;

// Course class
// must implement Comparable<Course> and Serializable
public class Course implements Comparable<Course>, java.io.Serializable{
	
	// create properties
	private static final long serialVersionUID = 1L;
	private String courseName; // in constructor
	private String courseID; // in constructor
	private int maxStudents; // in constructor
	private int currentStudents;
	private ArrayList<String> names = new ArrayList<String>();
	private String courseInstructor; // in constructor
	private int sectionNum; // in constructor
	private String location; // in constructor
	
	// default constructor
	Course(){}
	
	// overloaded constructor
	Course(String a, String b, int c, int d, String e, int f, String g) {
		courseName = a;
		courseID = b;
		maxStudents = c;
		currentStudents = d;
		courseInstructor = e;
		sectionNum = f;
		location = g;
	}
	
	// Override the compareTo method to allow sorting of courses in Admin method
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
	void setNames (String a) {names.add(a);}
	void setCourseInstructor (String a) {courseInstructor = a;}
	void setSectionNum (int a) {sectionNum = a;}
	void setLocation (String a) {location = a;}
}
