// imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList; // import the ArrayList class
import java.util.Scanner;

// AllData class (will be the serialized class that holds arraylist<objects>)
// must implement Serializable
public class AllData implements java.io.Serializable {
	
	// create required arrays and UID
	private static final long serialVersionUID = 1924812048511231257L;
	private ArrayList<Course> courseArray = new ArrayList<Course>();
	private ArrayList<Student> studentArray = new ArrayList<Student>();
	private ArrayList<Admin> adminArray = new ArrayList<Admin>();
	
	// getters
	ArrayList<Course> getCourseArray() {return this.courseArray;}
	ArrayList<Student> getStudentArray() {return this.studentArray;}
	ArrayList<Admin> getAdminArray() {return this.adminArray;}
	
	// default constructor will create a default admin account
	AllData() {
		this.adminArray.add(new Admin());
	}
	
	// method to read the CSV into the object if program is running for the first time
	public void readCSV() {
		// MyUniversityCourses.csv is in the project folder.
		java.io.File file = new File("MyUniversityCourses.csv");
		try {
			Scanner input = new Scanner(file);
			input.nextLine(); //skip header
			input.useDelimiter(",");
			int lineNum = 0;
			// if EOF has not been reached, and the line is not the header, grab and parse the data
			while (input.hasNext()) {					
				String placeholder = input.nextLine();
				String [] rowArray = placeholder.split(",");
				String a = rowArray[0];
				String b = rowArray[1];
				int c = Integer.parseInt(rowArray[2]);
				int d = Integer.parseInt(rowArray[3]);
				String e = rowArray[5];
				int f = Integer.parseInt(rowArray[6]);
				String g = rowArray[7];
				// add the parsed data to a new course object, add to AllData.courseArray
				this.courseArray.add(new Course(a,b,c,d,e,f,g));	
				lineNum+=1;				
			}
			System.out.println(lineNum + " course(s) added");
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	// simple method to check if a student object exists using username + password
	public boolean checkStudentExists(String user, String pass) {
		for (Student s: this.studentArray) {
			if (s.username.equals(user) && s.password.equals(pass)) {
				return true;
			}
		}
		return false;
	}
	
	// simple method to check if admin exists using user + password
	// note that this should always return true, as a default admin object is created
	public boolean checkAdminExists(String user, String pass) {
		for (Admin a: this.adminArray) {
			if (a.username.equals(user) && a.password.equals(pass) ) {
				return true;
			}
		}
		return false;
	}
	// getter for student object
	public Student getStudent(String user, String pass) {

		for (Student s: this.studentArray) {
			if (s.username.equals(user) && s.password.equals(pass)) {
				return s;
			}
		}
		return null;
	}
	// getter for admin object
	public Admin getAdmin(String user, String pass) {
		for (Admin a: this.adminArray) {
			if (a.username.equals(user) && a.password.equals(pass)) {
				return a;
			}
		}
		return null;
	}
}
