// imports
import java.util.Scanner;


// interface with Admin methods
// Most will require (AllData a, Scanner sc)
public interface AdminInterface {
	void createCourse(AllData a, Scanner sc);
	void deleteCourse(AllData a, Scanner sc);
	void editCourse(AllData a, Scanner sc);
	void displayCourse(AllData a, Scanner sc);
	void registerStudent(AllData a, Scanner sc);
	void viewFullCourses(AllData a);
	void toFileFull(AllData a);
	void studentCourses(AllData a, Scanner sc);
	void sortCourses(AllData a);	
	void registerAdmin(AllData a, Scanner sc);
}
