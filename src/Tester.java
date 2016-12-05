import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.management.modelmbean.ModelMBean;
import javax.swing.SwingUtilities;

public class Tester {
	
	//Credentials for Database
	public static final String USER = "root";
	public static final String PASSWORD = "aaaaaa";
	
	
	public static void main(String args[]){
			StoreModel test = new StoreModel(USER, PASSWORD);
			
			//testing create a user, will check if user already exists in database and print to console
			test.createUser("bridgegade", "!@)59380", "test");

			//testing getting uID by inputting 
			System.out.println(test.getUser("dasd","sda"));
			
			//testing makeOrder
			test.makeOrder(1, 400);
		
			test.addFruit("grape", 100);
			test.addFruit("bananna", 500);
			
			//testing add Review
			//test.addReview(3, 4, 5, "Very yummy!");
			
			ArrayList listOfFruit = test.getListOfFruit();
			System.out.println(listOfFruit);

			
			//String[] dummy = {"apple"};
			StoreGUI fruitStoreGUI = new StoreGUI(test.getInventory(), test.getListOfFruit());
			Store fruitStore = new Store(fruitStoreGUI, test);
	}
}
