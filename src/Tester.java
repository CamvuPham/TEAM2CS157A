import java.awt.Dimension;
import java.sql.SQLException;

public class Tester {
	
	//Credentials for Database
	public static final String USER = "root";
	public static final String PASSWORD = "";
	
	
	public static void main(String args[]){
		
		String[] listOfFruit = { "pear", "apple" };
		
		StoreGUI fruitStore = new StoreGUI(listOfFruit);
			StoreModel test = new StoreModel(USER, PASSWORD);
			//test.createUser();
		
		
	}
}
