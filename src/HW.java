import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HW {
	

	public static void main(String[] args) {
		// check for presence of .ser file
		// if true, proceed to deserialize it to a copy.
		// if not there, create AllData Class, read the csv file into the courseArray.
		// display login (create admin account first and add to AllData array) + exit + check with AllData student and admin arrays
			// if object type student, display avail options
			// else if object type admin, display avail options
	}
	
	public static void serializeObject(AllData a, String fileName) {
		try {
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream objStream = new ObjectOutputStream(file);
			objStream.writeObject(a);
			objStream.close();
			file.close();
		}  catch (FileNotFoundException e) {
			System.out.println("FileNotFound Exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
	}
	
	public static AllData deserializeObject(String fileName) {
		try {
			FileInputStream file = new FileInputStream(new File(fileName));
			ObjectInputStream objStream = new ObjectInputStream(file);
			AllData data = (AllData) objStream.readObject();
			objStream.close();
			file.close();
			return data;
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException");
			e.printStackTrace();
		}
		return null;
	}
	
}
