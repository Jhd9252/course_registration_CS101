// imports
import java.util.Scanner;

// StudentInterface will be used by Student
// holds methods that are required by Student
public interface StudentInterface {
	void viewAllCourses(AllData a);
	void viewNotFullCourses(AllData a);
	void registerCourse(AllData a, Scanner sc);
	void withdrawCourse(AllData a, Scanner sc);
	void viewMyCourses();
}
