

/*
[] interface Student
	view all crs (pull from AllData class courseArrayList)
	view all crs not full (pull from AllData class courseArrayList)
	register crs (pull from AllData class courseArrayList) 
	withdraw crs (pull from AllData class courseArrayList) 
	view my crs (pull from alldata class arraylist)
*/
public interface StudentInterface {
	void viewAllCourses(AllData a);
	void viewNotFullCourses(AllData a);
	void registerCourse(AllData a);
	void withdrawCourse(AllData a);
	void viewMyCourses();
}
