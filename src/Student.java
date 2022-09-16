// imports
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Student class must implement the StudentInterface and Serializable
public class Student extends User implements StudentInterface, java.io.Serializable {
	
	// create properties (class will also have a myCourses ArrayList for simplicity's sake)
	ArrayList<Course> myCourses= new ArrayList<Course>();
	
	// default constructor
	Student(){}
	
	// overloaded constructor
	Student(String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	// method to view courses that student can register for (not full)
	@Override
	public void viewNotFullCourses(AllData a) {
		// iterate through AllData a.course array
		// if course.currentStudents < course.maxStudents
		// then print the data for that class
		System.out.println(padding("|Course Name", 50) + padding("|Course ID",20) + padding("|Current Num Students", 30) + padding("|Max Num Students|", 30));
		System.out.println(padding("-",130).replace(" ", "-"));
		for (Course c: a.getCourseArray()) {
			if (c.getMaxStudents() > c.getCurrentStudents()) {
				System.out.println();
				System.out.print(padding(c.getCourseName(),50));
				System.out.print(padding(c.getCourseID(),20));
				System.out.print(padding(Integer.toString(c.getCurrentStudents()),30));
				System.out.print(padding(Integer.toString(c.getMaxStudents()),30));	
			}	
		}
		System.out.println();
	}

	@Override
	// registerCourse method
	public void registerCourse(AllData a, Scanner sc) {
		try {
			// get course input
			System.out.println("Enter course name: ");
			String name = sc.nextLine();
			System.out.println("Enter course section: ");
			int section = sc.nextInt();
			sc.nextLine();
			boolean found = false;
			// iterate through courses
			for (Course c: a.getCourseArray()) {
				// if found
				if (c.getCourseName().equals(name) && c.getSectionNum() == section && (c.getMaxStudents() > c.getCurrentStudents())) {
					// add student name to the ArrayList names of course property
					// also add the course to this.myCourses for a later method
					found = true;
					c.setNames(this.lastName + " " + this.firstName);
					c.setCurrentStudents(c.getCurrentStudents()+1);
					myCourses.add(c);
					System.out.println("Successfully registered for course.");
					break;
				} 
			}
			if (found == false) {
				System.out.print("Course not found.");
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid input.");
			e.printStackTrace();
		}
		
	}

	@Override
	// withdrawCourse method
	public void withdrawCourse(AllData a, Scanner sc) {
		// Since myCourses reflects the AllData a.courseArray courses with the student name in it, 
		// if myCourses size is 0, the student is not enrolled in any course
		if (myCourses.size() == 0) {
			System.out.println("You are not enrolled in any courses.");
		} else {
			try {
				// print out myCourses and get user input
				viewMyCourses();
				System.out.println("Enter number of course to withdraw: ");
				int index = sc.nextInt();
				sc.nextLine();
				// if the input is valid
				if (index > 0 && index <= myCourses.size()) {
					// set the course's current student count -= 1
					// iterate through the names of the course and remove student name
					// remove the course from myCourses
					int holder = myCourses.get(index-1).getCurrentStudents();
					myCourses.get(index-1).setCurrentStudents(holder -1);
					for (String s: myCourses.get(index-1).getNames()) {
						if (s.equals(this.lastName + " " + this.firstName)) {
							myCourses.get(index-1).getNames().remove(s);
							break;
						}
					}
					myCourses.remove(index-1);
					System.out.println("Successfully withdrawn from course");
					
				} else {
					System.out.println("Invalid input.");
					
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void viewMyCourses() {
		System.out.println(padding("-",100).replace(" ", "-"));
		int counter = 1;
		// if student.myCourses size is 0, student not enrolled in any courses
		if (this.myCourses.size() == 0) {
			System.out.println("Student not enrolled in any courses.");
		} else {
			// else just print all Course c: myCourses
			for (Course c: myCourses) {
				System.out.print(counter +": " + padding(c.getCourseName(), 50) +  "Section: " + c.getSectionNum());
				System.out.println();
				counter += 1;
			}
		}
		
		
	}
	
	// menu method for student account
	// logging out will return logOut = false, which will end the main method() loop.
	public boolean menu(AllData a, Scanner sc, boolean logOut) {
		while (true) {
			// display menu options
			System.out.println(String.format("%-100s", "-").replace(' ', '-'));
			System.out.println("Logged in as: " + this.firstName);
			System.out.println("1. View open courses");
			System.out.println("2. Register for a course");
			System.out.println("3. Withdraw from a course");
			System.out.println("4. View my courses");
			System.out.println("5. Logout");
			System.out.println("Enter option number: ");
			// call method depending on option input
			try {
				int option = sc.nextInt();
				sc.nextLine();
				if (option > 0 && option <= 9) {
					switch (option) {
		            case 1:  viewNotFullCourses(a);	   
		            	break;
		            case 2:  registerCourse(a, sc);
		            	serializeObject(a);
		            	break;
		            case 3:  withdrawCourse(a, sc);	
		            	serializeObject(a);
		            	break;
		            case 4:  viewMyCourses();	
		            	break;
		            case 5:  System.out.println("Logging out.");
	            		serializeObject(a);
		            	logOut = true;
	            		return logOut;                   
					}
				} else {
					System.out.println("Invalid input.");
				}
			} catch (InputMismatchException e) {
				e.printStackTrace();

			}
		}
		
	}
	
}
