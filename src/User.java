
abstract public class User {
	String firstName;
	String lastName;
	String username;
	String password;
	
	
	void getFirstName() {
		System.out.println(firstName);
	}
	void getLastName() {
		System.out.println(lastName);
	}
	void setFirstName(String a) {
		firstName = a;
	}
	void setLastName(String a) {
		lastName = a;
	}
	public static String padding(String a, int b) {
		// String.format("%-10s", "bar").replace(' ', '*');
		return String.format("%-"+b+"s", a);
	}
	public void viewAllCourses(AllData a) {
		// iterator through AllData a.courseArray
		// display course name, course ID, current number of students, max number of students. 
		System.out.println(padding("|Course Name", 50) + padding("|Course ID",20) + padding("|Current Num Students", 30) + padding("|Max Num Students|", 30));
		System.out.println(padding("-",130).replace(" ", "-"));
		for (Course c: a.courseArray) {
			System.out.println();
			System.out.print(padding(c.getCourseName(),50));
			System.out.print(padding(c.getCourseID(),20));
			System.out.print(padding(Integer.toString(c.getCurrentStudents()),30));
			System.out.print(padding(Integer.toString(c.getMaxStudents()),30));	
		}
	}
}
