import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

// abstract class User
// must implement Serializable
abstract public class User implements java.io.Serializable{

	// create properties
	private static final long serialVersionUID = 1L;
	protected String firstName;
	protected String lastName;
	protected String username;
	protected String password;
	
	// getters
	String getFirstName() {return firstName;}
	String getLastName() {return lastName;}
	
	// setters
	void setFirstName(String a) {firstName = a;}
	void setLastName(String a) {lastName = a;}
	
	// padding method for methods that require printing
	public static String padding(String a, int b) {return String.format("%-"+b+"s", a);}
		
	// viewAllCourses method that will be inherited by Student and Admin
	public void viewAllCourses(AllData a) {
		// iterator through AllData a.courseArray
		System.out.println(padding("|Course Name", 50) + padding("|Course ID",20) + padding("|Section", 20) + padding("|Current Num Students", 30) + padding("|Max Num Students|", 30));
		System.out.println(padding("-",130).replace(" ", "-"));
		for (Course c: a.getCourseArray()) {
			// display course info
			System.out.println();
			System.out.print(padding(c.getCourseName(),50));
			System.out.print(padding(c.getCourseID(),20));
			System.out.print(padding(Integer.toString(c.getSectionNum()),20));
			System.out.print(padding(Integer.toString(c.getCurrentStudents()),30));
			System.out.print(padding(Integer.toString(c.getMaxStudents()),30));	
		}
		System.out.println();
	}
	
	public static void serializeObject(AllData a) {
		try {
			// create file and serialize AllData a to file. 
			FileOutputStream file = new FileOutputStream("data.ser");
			ObjectOutputStream objStream = new ObjectOutputStream(file);
			objStream.writeObject(a);
			objStream.close();
			file.close();
			System.out.println("Changes saved.");
		}  catch (FileNotFoundException e) {
			System.out.println("FileNotFound Exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
	}

	public boolean menu(AllData a, Scanner sc, boolean logOut) {
		System.out.println(String.format("%-100s", "-").replace(' ', '-'));
		System.out.println("Logged in as: " + this.firstName);
		System.out.println("1. Log out");
		try {
			// get option input as int
			int option = sc.nextInt();
			sc.nextLine();
			if (option == 1) {
				System.out.println("Logging out..");
				logOut = true;
				return logOut;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("List menu options according to account type.");
		return logOut;
	}
}
