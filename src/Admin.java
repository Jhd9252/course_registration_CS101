import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.FileWriter;   
import java.io.IOException;  
import java.util.*;

public class Admin extends User implements AdminInterface {

	Admin() {
		this.username = "Admin";
		this.password = "Admin001";
	}
	
	Admin(String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	@Override 
	public void createCourse(AllData a) {
		String courseName;
		String courseID;
		int maxStudents;
		String instructor;
		int section;
		String loc;
		while (true) {
			try {
				Scanner input = new Scanner(System.in);
				System.out.println("Enter a course name: ");
				courseName = input.nextLine();
				System.out.println("Enter a Course_Id: ");
				courseID = input.nextLine();
				System.out.println("Max Number of Students: ");
				maxStudents = input.nextInt();
				System.out.println("Enter Course Instructor: ");
				instructor = input.nextLine();
				System.out.println("Enter course section: ");
				section = input.nextInt();
				System.out.println("Enter course location: ");
				loc = input.nextLine();
				input.close();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Try again.");
			}
		}
		for (Course c : a.courseArray ) {
			if (c.courseName == courseName && c.sectionNum == section) {
				System.out.println("Course already exists.");
				break;
			} else {
				a.courseArray.add(new Course(courseName, courseID, maxStudents, instructor, section, loc));
				System.out.println("Course created.");
			}		
		}
	}
		
	@Override
	public void deleteCourse(AllData a) {
		while (true) {
			try {
				Scanner input = new Scanner(System.in);
				System.out.println("Enter a course name: ");
				String name = input.nextLine();
				System.out.println("Enter course section number: ");
				int section = input.nextInt();
				for (Course c: a.courseArray) {
					if (c.courseName == name && c.sectionNum == section) {
						a.courseArray.remove(c);
						System.out.println("Course has been deleted.");
					} else {
						System.out.println("Course not found.");
					}
				}
				input.close();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Try again.");
			}
		}	
	}

	@Override
	public void editCourse(AllData a) {
		// check each course in a.courseArray for matching course name and section 
		// ask what to edit using setters in Course class
		 while (true) {
			 try {
				 Scanner input = new Scanner(System.in);
				 System.out.println("Enter course name: ");
				 String name = input.nextLine();
				 System.out.println("Enter course section num: ");
				 int section = input.nextInt();
				 for (Course c: a.courseArray) {
					 if (c.courseName == name && c.sectionNum == section) {
						 System.out.println("What do you want to edit? Enter corresponding number: \n "
							 		+ "1. Max Number of Students \n 2. Course Instructor \n 3. Course Section \n 4. Course Location");
						 int change = input.nextInt();
						
						 if (change ==1) {
							 System.out.println("Current Max Students: " + c.getMaxStudents());
							 System.out.println("Enter New Max: ");
							 int max = input.nextInt();
							 if (c.maxStudents < max) {
								 System.out.println("New Max cannot be less than current amount of enrolled students.");
								 break;
							 } else {
							 c.setMaxStudents(input.nextInt());
							 }
						 } else if (change == 2) {
							 System.out.println("Current Instructor: " + c.getCourseInstructor());
							 System.out.println("Enter New Instructor: ");
							 c.setCourseInstructor(input.nextLine());
						 } else if (change == 3) {
							 System.out.println("Current Section: " + c.getSectionNum());
							 System.out.println("Enter New Section Num: ");
							 c.setSectionNum(input.nextInt());
						 } else if (change == 4) {
							 System.out.println("Current Location: " + c.getLocation());
							 System.out.println("Enter New Location: ");
							 c.setLocation(input.nextLine());
						 }
						input.close();
						System.out.println("Course edited!");
					
					 } else {
						 System.out.println("Course not found.");
					 } 
				}
				 break;
			 } catch (InputMismatchException e) {
				 System.out.println("Invalid input. Try again.");
			 }
		 }	
	}

	@Override
	// display course info by courseID
	public void displayCourse(AllData a) {
		try {
			Scanner input = new Scanner (System.in);
			System.out.println("Enter course name to search: ");
			String name = input.nextLine();
			System.out.println("Enter course ID to search: ");
			String id = input.nextLine();
			for (Course c: a.courseArray ) {
				if (name == c.courseName && id == c.courseID) {
					//display information
					System.out.println("-------------------");
					System.out.println("Course Name: " + c.getCourseName());
					System.out.println("Course ID: " + c.getCourseID());
					System.out.println("Max Num of Students: " + c.getMaxStudents());
					System.out.println("Current Num of Students: " + c.getCurrentStudents());
					System.out.println("Students Enrolled: " + c.getNames());
					System.out.println("Course Instructor: " + c.getCourseInstructor());
					System.out.println("Course Location: " + c.getLocation());
					
				} else {
					System.out.println("Course not found.");
					break;
				}
			}
			input.close();
		} catch (InputMismatchException e){
			System.out.println("Invalid input. Try again.");
		}
	}

	@Override
	public void registerStudent(AllData a) {
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter student's first name: ");    
			String firstName = input.nextLine(); 
			System.out.println("Enter student's last name: "); 
			String lastName = input.nextLine();
			System.out.println("Enter student username: ");
			String username = input.nextLine();
			System.out.println("Enter student password: ");
			String password = input.nextLine();
			a.studentArray.add(new Student(firstName, lastName, username, password));
			input.close();
			System.out.println("Student account created.");
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Try again.");
		}
		
	}

	@Override
	public void viewFullCourses(AllData a) {
		// iterator through AllData a.courseArray
		// check if students = max students
		// display course name, course ID, current number of students, max number of students. 
		System.out.println(padding("|Course Name", 50) + padding("|Course ID",20) + padding("|Current Num Students", 30) + padding("|Max Num Students|", 30));
		System.out.println(padding("-",130).replace(" ", "-"));
		for (Course c: a.courseArray) {
			if (c.maxStudents == c.currentStudents) {
				System.out.println();
				System.out.print(padding(c.getCourseName(),50));
				System.out.print(padding(c.getCourseID(),20));
				System.out.print(padding(Integer.toString(c.getCurrentStudents()),30));
				System.out.print(padding(Integer.toString(c.getMaxStudents()),30));	
			}	
		}
	}

	@Override
	public void toFileFull(AllData a) {		
		try {
			FileWriter file = new FileWriter("full_courses.txt");
			System.out.println("New file created successfully.");
			for (Course c: a.courseArray) {
				file.write(c.courseName + "\n");
			} 
			file.close();
		} catch (IOException e) {
			System.out.println("Error occured.");
			e.printStackTrace();
		} 
		
	}

	@Override
	public void studentCourses(AllData a) {
		// input for student first and last
		// iterate through a.courseArray
		// if student "Last, First" in c.names
		// print out course
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Enter student first name: ");
			String firstName = input.nextLine();
			System.out.println("Enter student last name: ");
			String lastName = input.nextLine();
			System.out.println();
			for (Student s: a.studentArray) {
				if (s.lastName == lastName && s.firstName == firstName) {
					s.viewMyCourses();
				}
			}
			input.close();
		} catch (InputMismatchException e) {
			System.out.println("Inavlid input.");
			e.printStackTrace();
		}
	}

	@Override
	public void sortCourses(AllData a) {
		Collections.sort(a.courseArray);
		viewAllCourses(a);
	}
	

}
