// imports
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class HW {
	// main method
	public static void main(String[] args) {
		// create properties
		AllData a = new AllData();
		Scanner sc = new Scanner(System.in);
		boolean logOut = false;
		// login and menu while loop
		while (logOut == false) {
			// deserialize the object before each login
			a = deserializeObject(a);
			// user and pass input
			System.out.println(String.format("%-100s", "-").replace(' ', '-'));
			System.out.println("Enter username: ");
			String user = sc.nextLine();
			System.out.println("Enter password: ");
			String pass = sc.nextLine();
			// if the input matches an admin or student account, call the menu() according to the object found
			// serialization occurs after each method call within o.menu();
			if (a.checkAdminExists(user, pass) == false && a.checkStudentExists(user, pass) == false) {
				System.out.println("Invalid username and password");
			} else if (a.checkAdminExists(user, pass) == true) {
				Admin adminPlaceHolder = a.getAdmin(user, pass);
				adminPlaceHolder.menu(a, sc, logOut);
			} else if (a.checkStudentExists(user, pass) == true) {
				Student studentPlaceHolder = a.getStudent(user, pass);
				studentPlaceHolder.menu(a, sc, logOut);		
			}
		}

	}
	
	// copy of User serializeObject method
	public static void serializeObject(AllData a) {
		try {
			FileOutputStream file = new FileOutputStream("data.ser");
			ObjectOutputStream objStream = new ObjectOutputStream(file);
			objStream.writeObject(a);
			objStream.close();
			file.close();
			System.out.println("AllData serialized.");
			System.out.println(String.format("%-100s", "-").replace(' ', '-'));
		}  catch (FileNotFoundException e) {
			System.out.println("FileNotFound Exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
	}
	
	
	public static AllData deserializeObject(AllData a) {
		try {
			// attempt to deserialize the object AllData a from "data.ser"
			FileInputStream file = new FileInputStream("data.ser");
			ObjectInputStream objStream = new ObjectInputStream(file);
			a = (AllData) objStream.readObject();
			objStream.close();
			file.close();
			System.out.println(String.format("%-100s", "-").replace(' ', '-'));
			System.out.println("AllData deserialization successful.");
		} catch (FileNotFoundException e) {
			// if unsuccessful, read the CSV to AllData a, serialize it
			System.out.println("FileNotFoundException. AllData created.");			
			System.out.println("Reading CSV...");
			a.readCSV();
			System.out.println("Successfully read CSV");
			serializeObject(a);
		} catch (IOException e) {
			System.out.println("IOException.");
			e.printStackTrace();
			System.out.println("Before reading CSV");
			a.readCSV();
			System.out.println("After reading CSV");
			serializeObject(a);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException");
			e.printStackTrace();
		} finally {
			// for testing and grading purposes
			System.out.println("Amount of Courses: "+ a.getCourseArray().size());
			System.out.println("Amount of Admin: "+ a.getAdminArray().size());
			System.out.println("Amount of Students: "+ a.getStudentArray().size());
		}
		return a;
	}
	

}
