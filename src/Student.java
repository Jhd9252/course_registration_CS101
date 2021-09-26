import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Student extends User implements StudentInterface {

	ArrayList<Course> myCourses= new ArrayList<Course>();
	
	Student(){}
	Student(String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	@Override
	public void viewNotFullCourses(AllData a) {
		// iterate through AllData a.course array
		// if course.currentStudents < course.maxStudents
		// then print the data for that class
		System.out.println(padding("|Course Name", 50) + padding("|Course ID",20) + padding("|Current Num Students", 30) + padding("|Max Num Students|", 30));
		System.out.println(padding("-",130).replace(" ", "-"));
		for (Course c: a.courseArray) {
			if (c.maxStudents > c.currentStudents) {
				System.out.println();
				System.out.print(padding(c.getCourseName(),50));
				System.out.print(padding(c.getCourseID(),20));
				System.out.print(padding(Integer.toString(c.getCurrentStudents()),30));
				System.out.print(padding(Integer.toString(c.getMaxStudents()),30));	
			}	
		}	
	}

	@Override
	// enter course name, section, student first and last name, name will be added to course)
	public void registerCourse(AllData a) {
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter course name: ");
			String name = input.nextLine();
			System.out.println("Enter course section: ");
			int section = input.nextInt();
			for (Course c: a.courseArray) {
				if (c.courseName == name && c.sectionNum == section) {
					c.names.add(lastName + " " + firstName);
					myCourses.add(c);
					System.out.println("Successfully registered for course.");
				} else {
					System.out.print("Course not found.");
				}
			}
			input.close();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input.");
			e.printStackTrace();
		}
		
	}

	@Override
	// get input for course name and section
	// iterate through until match
	// iterate through names until match
	// remove
	public void withdrawCourse(AllData a) {
		if (myCourses.size() == 0) {
			System.out.println("You are not enrolled in any courses.");
		} else {
			try {
				viewMyCourses();
				Scanner input = new Scanner(System.in);
				System.out.println("Enter number of course to withdraw: ");
				int index = input.nextInt();
				if (index > 0 && index <= myCourses.size()) {
					myCourses.remove(index-1);
					System.out.println("Successfully withdrawn from course");
				} else {
					System.out.println("Invalid input.");
				}
				input.close();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void viewMyCourses() {
		System.out.println(padding("-",50).replace(" ", "-"));
		int counter = 1;
		for (Course c: myCourses) {
			System.out.print(counter +": " + padding(c.courseName, 50) + c.sectionNum);
		}
		
	}

}
