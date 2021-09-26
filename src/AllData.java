import java.util.ArrayList; // import the ArrayList class

public class AllData implements java.io.Serializable {
	private static final long serialVersionUID = 1924812048511231257L;
	ArrayList<Course> courseArray = new ArrayList<Course>();
	ArrayList<Student> studentArray = new ArrayList<Student>();
	ArrayList<Admin> adminArray = new ArrayList<Admin>();
}
