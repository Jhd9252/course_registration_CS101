// imports
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.FileWriter;   
import java.io.IOException;
import java.util.*;


// Parent Class: User
// Child Class: Admin
// Implement AdminInterface and Serializable
public class Admin extends User implements AdminInterface, java.io.Serializable{


	// default constructor with default username and password (requirement)
	Admin() {
		this.username = "Admin";
		this.password = "Admin001";
		this.firstName = "Admin";
	}
	
	// overloaded constructor
	Admin(String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	@Override
	// menu method: While loop. If logging out, will return logOut = true, ending the main method While loop.
	public boolean menu(AllData a, Scanner sc, boolean logOut) {
		while (true) {
			// print menu options
			System.out.println(String.format("%-100s", "-").replace(' ', '-'));
			System.out.println("Logged in as: " + this.firstName);
			System.out.println("1. Create a course");
			System.out.println("2. Delete a course");
			System.out.println("3. Edit a course");
			System.out.println("4. Display a course");
			System.out.println("5. Register a student");
			System.out.println("6. View full courses");
			System.out.println("7. Create text file of full courses");
			System.out.println("8. View a student's courses");
			System.out.println("9. Sort courses by number of students");
			System.out.println("10. View all courses");
			System.out.println("11. Register an admin");
			System.out.println("12. Log off");
			System.out.println("Enter option number: ");

			try {
				// get option input as int
				int option = sc.nextInt();
				sc.nextLine();
				if (option > 0 && option <= 12) {
					// switch case for menu options
					switch (option) {
			            case 1:  createCourse(a, sc);
			            	serializeObject(a);			    
			            	break;
			            case 2:  deleteCourse(a,sc);
			            	serializeObject(a);			            	
			            	break;
			            case 3:  editCourse(a,sc);	
			            	serializeObject(a);
			            	break;
			            case 4:  displayCourse(a,sc);
			            	break;
			            case 5:  registerStudent(a,sc);
			            	serializeObject(a);
			            	break;
			            case 6:  viewFullCourses(a);	
			            	break;
			            case 7:  toFileFull(a);	
			        		break;
			            case 8:  studentCourses(a,sc);	
			            	break;
			            case 9:  sortCourses(a);
			            	serializeObject(a);
			            	break;
			            case 10: viewAllCourses(a);
			            	//sc.nextLine();
			            	break;
			            case 11: registerAdmin(a, sc);
		            		break;
			            case 12: System.out.println("Logging out...");
		            		serializeObject(a);
				            logOut = true;
			            	return logOut;
						}
					
				} else {
					System.out.println("Invalid input.");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void createCourse(AllData a, Scanner sc) {
		// create variables
		String courseName;
		String courseID;
		int maxStudents;
		String instructor;
		int section;
		String loc;
		while (true) {
			try {
				// get input on course object properties
				System.out.println("Enter a course name: ");
				courseName = sc.nextLine();
				System.out.println("Enter a Course_Id: ");
				courseID = sc.nextLine();
				System.out.println("Max Number of Students: ");
				maxStudents = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Course Instructor: ");
				instructor = sc.nextLine();
				System.out.println("Enter course section: ");
				section = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter course location: ");
				loc = sc.nextLine();
				// create a new course object and add to AllData a.courseArray using getter
				a.getCourseArray().add(new Course(courseName, courseID, maxStudents, 0, instructor, section, loc));
				System.out.println("Course created.");
				break;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Try again.");
				break;
			}	
		}
		
		
		

		
	}
		
	@Override
	public void deleteCourse(AllData a, Scanner sc) {
		while (true) {
			try {
				// get course input
				System.out.println("Enter a course name: ");
				String name = sc.nextLine();
				System.out.println("Enter course section number: ");
				int section = sc.nextInt();
				sc.nextLine();
				boolean found = false;
				// find the course if it exists -> remove it if found
				for (Course c: a.getCourseArray()) {
					if (c.getCourseName().equals(name) && c.getSectionNum() == section) {
						a.getCourseArray().remove(c);
						System.out.println("Course has been deleted.");
						found = true;
						break;
					} 
				}
				// if not found, display error and go back to menu
				if (found == false) {
					System.out.println("Course not found.");
					break;
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Try again.");
			}
		}	
	}

	@Override
	public void editCourse(AllData a, Scanner sc) {
		 while (true) {
			 try {
				 // get course input
				 System.out.println("Enter course name: ");
				 String name = sc.nextLine();
				 System.out.println("Enter course section num: ");
				 int section = sc.nextInt();
				 sc.nextLine();
				 boolean found = false;
				 // iterate through a.courseArray using getter. 
				 for (Course c: a.getCourseArray()) {
					 // check each course property
					 if (c.getCourseName().equals(name)&& c.getSectionNum() == section) {
						 found = true;
						 // display edit options
						 System.out.println("What do you want to edit? Enter corresponding number: \n "
							 		+ "1. Max Number of Students \n 2. Course Name \n 3. Course Section \n 4. Course ID");
						 int change = sc.nextInt();
						 sc.nextLine();
						 // if else structure for the chosen edit option. Make the changes with setters.
						 if (change ==1) {
							 System.out.println("Current Max Students: " + c.getMaxStudents());
							 System.out.println("Enter New Max: ");
							 int max = sc.nextInt();
							 sc.nextLine();
							 if (c.getCurrentStudents() > max) {
								 System.out.println(c.getCurrentStudents());
								 System.out.println("New max cannot be less than current amount of enrolled students.");
							 } else {
								 c.setMaxStudents(max);
								 sc.nextLine();
							 }
						 } else if (change == 2) {
							 System.out.println("Current Name: " + c.getCourseName());
							 System.out.println("Enter New Name: ");
							 c.setCourseName(sc.nextLine());
						 } else if (change == 3) {
							 System.out.println("Current Section: " + c.getSectionNum());
							 System.out.println("Enter New Section Num: ");
							 c.setSectionNum(sc.nextInt());
							 sc.nextLine();
						 } else if (change == 4) {
							 System.out.println("Current ID: " + c.getCourseID());
							 System.out.println("Enter New ID: ");
							 c.setCourseID(sc.nextLine());
						 }
						System.out.println("Course edited!");
					
					 } 
				}
				 if (found == false) {
					 System.out.println("Course not found.");
				 }
				 break;
			 } catch (InputMismatchException e) {
				 System.out.println("Invalid input. Try again.");
			 }
		 }	
	}

	@Override
	public void displayCourse(AllData a, Scanner sc) {
		try {
			// get course input
			System.out.println("Enter course name to search: ");
			String name = sc.nextLine();
			System.out.println("Enter course section to search: ");
			int section = sc.nextInt();
			sc.nextLine();
			boolean found = false;
			// iterate through a.courseArray
			for (Course c: a.getCourseArray() ) {
				if (c.getCourseName().equals(name) && section == c.getSectionNum()) {
					found = true;
					// if found -> display information about the course
					System.out.println("-------------------");
					System.out.println("Course Name: " + c.getCourseName());
					System.out.println("Course ID: " + c.getCourseID());
					System.out.println("Max Num of Students: " + c.getMaxStudents());
					System.out.println("Current Num of Students: " + c.getCurrentStudents());
					System.out.println("Students Enrolled: " + c.getNames());
					System.out.println("Course Instructor: " + c.getCourseInstructor());
					System.out.println("Course Location: " + c.getLocation());					
				} 
			}
			if (found == false) {
				System.out.println("Course not found.");
			}
		} catch (InputMismatchException e){
			System.out.println("Invalid input. Try again.");
		}
	}

	@Override
	public void registerStudent(AllData a, Scanner sc) {
		try {
			// get student information
			System.out.println("Enter student's first name: ");    
			String firstName = sc.nextLine(); 
			System.out.println("Enter student's last name: "); 
			String lastName = sc.nextLine();
			System.out.println("Enter student username: ");
			String username = sc.nextLine();
			System.out.println("Enter student password: ");
			String password = sc.nextLine();
			// add the student to a.studentArray
			a.getStudentArray().add(new Student(firstName, lastName, username, password));
			System.out.println("Student account created.");
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Try again.");
		}
		
	}
	
	@Override
	public void registerAdmin(AllData a, Scanner sc) {
		try {
			// get student information
			System.out.println("Enter admin's first name: ");    
			String firstName = sc.nextLine(); 
			System.out.println("Enter admin's last name: "); 
			String lastName = sc.nextLine();
			System.out.println("Enter admin username: ");
			String username = sc.nextLine();
			System.out.println("Enter admin password: ");
			String password = sc.nextLine();
			// add the student to a.studentArray
			a.getAdminArray().add(new Admin(firstName, lastName, username, password));
			System.out.println("Admin account created.");
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Try again.");
		}
		
	}

	@Override
	public void viewFullCourses(AllData a) { 
		int count = 0;
		System.out.println(padding("|Course Name", 50) + padding("|Course ID",20) + padding("|Current Num Students", 30) + padding("|Max Num Students|", 30));
		System.out.println(padding("-",130).replace(" ", "-"));
		// iterate through courseArray
		for (Course c: a.getCourseArray()) {
			// if the current amount of students == max amount of students
			if (c.getMaxStudents() == c.getCurrentStudents()) {
				count += 1;
				// print the course information
				System.out.println();
				System.out.print(padding(c.getCourseName(),50));
				System.out.print(padding(c.getCourseID(),20));
				System.out.print(padding(Integer.toString(c.getCurrentStudents()),30));
				System.out.print(padding(Integer.toString(c.getMaxStudents()),30));	
			}	
		}
		if (count == 0) {
			System.out.println("N/A");
		}
	}

	@Override
	public void toFileFull(AllData a) {		
		try {
			// create file object to write to.
			FileWriter file = new FileWriter("full_courses.txt");
			System.out.println("New file created successfully.");
			// iterate through courses
			for (Course c: a.getCourseArray()) {
				// if max students == current students 
				//  then write to file object + \n
				if (c.getMaxStudents() == c.getCurrentStudents()) {
					file.write(c.getCourseName() + "\n");
				}
			} 
			file.close();
		} catch (IOException e) {
			System.out.println("Error occured.");
			e.printStackTrace();
		} 
		
	}

	@Override
	public void studentCourses(AllData a, Scanner sc) {
		// get student information
		try {
			System.out.println("Enter student first name: ");
			String firstName = sc.nextLine();
			System.out.println("Enter student last name: ");
			String lastName = sc.nextLine();
			System.out.println();
			boolean found = false;
			// iterate through studentArray
			for (Student s: a.getStudentArray()) {
				// if student is found
				if (s.lastName.equals(lastName)&& s.firstName.equals(firstName)) {
					found = true;
					// call the method viewMyCourses from the Student class
					s.viewMyCourses();
				}
			}
			if (found == false) {
				System.out.println("Student does not exist.");
			}

		} catch (InputMismatchException e) {
			System.out.println("Inavlid input.");
			e.printStackTrace();
		}
	}

	@Override
	public void sortCourses(AllData a) {
		// sort the array according the current students property
		Collections.sort(a.getCourseArray());
		// display the courses list using previously defined method
		viewAllCourses(a);
	}
	

	
}
