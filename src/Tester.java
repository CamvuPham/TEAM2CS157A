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
			
			//testing create a user, will check if user already exists in database and print to console
			test.createUser("bridgegade", "!@)59380", "test");
			test.editUser("dasd", "sda", "emdsa", 1, 1);
			//testing getting uID by inputting 
			System.out.println(test.getUser("dasd"));
			
			//testing makeOrder
			test.makeOrder(1, 400);
			
			test.addFruit("grape", 100);
			test.addFruit("bananna", 500);
			
			test.addInventory(0, "2003-12-31", 10);
			System.out.println(test.getInventory());

			test.addInventory(0, "2003-12-31", 50);
			
			
			System.out.println(test.getInventory());
		
	}
}
