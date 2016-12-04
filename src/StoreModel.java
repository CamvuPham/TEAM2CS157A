import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;
import java.sql.Statement;
import java.util.Scanner;

public class StoreModel {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement preparedStatement;

	public StoreModel(String USER, String PASS) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		String URL = "jdbc:mysql://localhost:3306/";

		try {

			// Open Connection
			conn = DriverManager.getConnection(URL, USER, PASS);

			stmt = conn.createStatement();
			preparedStatement = null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage());
		}

		addTables();

	}

	public void addTables() {

		/*
		 * User(uID, username, password, email, isEmployee) 
		 * Fruit (fID, name, price) 
		 * Inventory(iID, fID, timeStamp, expirationDate, amount)
		 *  Order (oID, uID, timeStamp, totalPrice, updatedAt) 
		 *  OrderItem (oiID, oID, fID, amount) 
		 *  Archive(oID, uID, timeStamp, totalPrice)
		 * 
		 */
		try {
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS FRUITSTORE");
			String sql = "USE FRUITSTORE;";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		File file = new File("fruitStore.sql");

		String i = "";

		try {

			Scanner sc = new Scanner(file);

			while (sc.hasNextLine()) {

				if (i.contains(";")) {

					try {

						stmt.executeUpdate(i);

					} catch (SQLException e) {

						e.printStackTrace();
						System.out.print(e.getMessage());

					}

					i = "";

				} else {

					i += sc.nextLine() + "";
				}
			}
			stmt.execute("DROP TRIGGER IF EXISTS AddToArchive");
			stmt.execute("CREATE TRIGGER AddToArchive "
					+ "AFTER INSERT ON Orders FOR EACH ROW BEGIN "
					+ "INSERT INTO Archive(oID,uID,oTimeStamp,oTotalPrice) VALUES(NEW.oID,NEW.uID,NEW.tStamp,NEW.totalPrice); "
					+ "END;");
			stmt.execute("DROP TRIGGER IF EXISTS deleteUserTrigger");
			stmt.execute("CREATE TRIGGER deleteUserTrigger "
					+ "BEFORE DELETE ON User FOR EACH ROW "
					+ "BEGIN "
					+ "DELETE FROM Orders WHERE OLD.uID = uID; "
					+ "END;");
			
			stmt.execute("DROP TRIGGER IF EXISTS OrderItemTrigger");
			stmt.execute("CREATE TRIGGER OrderItemTrigger "
					+ "AFTER INSERT ON OrderItem FOR EACH ROW BEGIN "
					+ "UPDATE Inventory SET Inventory.amount = Inventory.amount - OrderItem.amount WHERE Inventory.fID = OrderItem.fID and MIN(expirationDate) = exprirationDate; "
					+ "END;");

			stmt.execute("DROP TRIGGER IF EXISTS OrderItemTrigger");
			 			stmt.execute("CREATE TRIGGER OrderItemTrigger "
			 					+ "AFTER INSERT ON OrderItem FOR EACH ROW BEGIN "
			 					+ "UPDATE Inventory SET Inventory.amount = Inventory.amount - OrderItem.amount WHERE Inventory.fID = OrderItem.fID and MIN(expirationDate) = exprirationDate; "
			 					+ "END;");
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * creates a User in the database, will print "Username or email already
	 * taken" if username or email are already in the database
	 * 
	 * @param username
	 * @param password
	 * @param email
	 */
	public void createUser(String username, String password, String email) {

		String sql = "SELECT * FROM User WHERE username = ? OR email = ?";
		ResultSet result = null;
		try {

			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, email);
			result = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (!result.next()) {
				// ResultSet is empty

				sql = "INSERT INTO User(username,password,email) VALUES(?,?,?)";

				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, email);
				preparedStatement.executeUpdate();

			} else {
				System.out.println("Username or email already taken.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * updates the user specified by uID in the database
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @param isEmployee
	 * @param uID
	 */
	public void editUser(String username, String password, String email, int isEmployee, int uID) {
		try {

			String sql = "UPDATE User SET username = ?,password = ?, email = ?, isEmployee = ? WHERE uID = ?";

			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, email);
			preparedStatement.setInt(4, isEmployee);
			preparedStatement.setInt(5, uID);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets an array that contains a map to each attribute of the user specified
	 * by username and password. Used for logging in.
	 * 
	 * @param username
	 * @return returns an array of the selected user's attributes
	 */
	public ArrayList getUser(String username, String password) {
		String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
		ResultSet result = null;

		try {

			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			result = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// code to retrieve an array of rows that contain maps to each attribute
		ArrayList<HashMap> listResult = new ArrayList<>();

		try {

			ResultSetMetaData md = result.getMetaData();
			int columns = md.getColumnCount();

			while (result.next()) {
				HashMap row = new HashMap(columns);
				for (int i = 1; i <= columns; ++i) {
					row.put(md.getColumnName(i), result.getObject(i));
				}
				listResult.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listResult;

	}

	/**
	 * makes an order
	 * 
	 * @param uID
	 * @param price
	 */
	public void makeOrder(int uID, int price) {
		try {

			String sql = "INSERT INTO Orders(uID,tStamp,totalPrice,updatedAt) VALUES(?,timestamp(NOW()),?,CURDATE())";

			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, uID);
			preparedStatement.setInt(2, price);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * updates an order based on oID of Order
	 * 
	 * @param oID
	 * @param uID
	 * @param price
	 */
	public void editOrder(int oID, int uID, int price) {
		try {
			String sql = "UPDATE Order SET price = ? WHERE oID = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, price);
			preparedStatement.setInt(2, oID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * gets all Orders made by a User based on uID, returns an arraylist
	 * containing rows which are represented as maps with each attribute being a
	 * key
	 * 
	 * @param uID
	 * @return
	 */
	public ArrayList getOrders(int uID) {
		String sql = "SELECT * FROM Orders WHERE uID = ?";
		ResultSet result = null;

		try {

			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, uID);
			result = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// code to retrieve an array of rows that contain maps to each attribute
		ArrayList<HashMap> listResult = new ArrayList<>();

		try {

			ResultSetMetaData md = result.getMetaData();
			int columns = md.getColumnCount();

			while (result.next()) {
				HashMap row = new HashMap(columns);
				for (int i = 1; i <= columns; ++i) {
					row.put(md.getColumnName(i), result.getObject(i));
				}
				listResult.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listResult;

	}

	/*
	 * add fruit to fruits table
	 * 
	 * @param name
	 * 
	 * @param price
	 */
	public void addFruit(String name, int price) {

		String sql = "SELECT * FROM Fruit WHERE name = ?";
		ResultSet result = null;
		try {

			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			result = preparedStatement.executeQuery();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (!result.next()) {
				// ResultSet is empty

				sql = "INSERT INTO Fruit(name, price) VALUES(?,?)";

				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, price);
				preparedStatement.executeUpdate();

			} else {
				System.out.println("Fruit already exists in table.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * */
	public ArrayList getListOfFruit() {
		
		String sql = "SELECT name FROM Fruit";

		ResultSet result = null;
		try {

			preparedStatement = conn.prepareStatement(sql);
			result = preparedStatement.executeQuery();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// code to retrieve an array of rows that contain maps to each attribute
		ArrayList<HashMap> listResult = new ArrayList<>();

		try {

			ResultSetMetaData md = result.getMetaData();
			int columns = md.getColumnCount();
			
			int count = 0;
			
			while (result.next()) {
				HashMap row = new HashMap(columns);
				for (int i = 1; i <= columns; ++i) {
					row.put(md.getColumnName(i), result.getObject(i));
				}
				listResult.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listResult;

	}
	
	/*
	 * 
	 * */
	public void addInventory(int fID, String expirationDate, int amount){
		
		String sql = "SELECT * FROM Inventory WHERE fID = ? AND expirationDate = ?";
		ResultSet result = null;
		try {
			String s = "SET FOREIGN_KEY_CHECKS=0";
			preparedStatement = conn.prepareStatement(s);
			preparedStatement.executeQuery();

			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, fID);
			preparedStatement.setString(2, expirationDate);
			//preparedStatement.setInt(3, amount);

			result = preparedStatement.executeQuery();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (!result.next()) {
				// ResultSet is empty

				sql = "INSERT INTO Inventory(fID, expirationDate, amount) VALUES(?,?,?)";

				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, fID);
				preparedStatement.setString(2, expirationDate);
				preparedStatement.setInt(3, amount);
				preparedStatement.executeUpdate();


			} else {
				
				sql = "UPDATE Inventory SET amount = amount + ? WHERE fID = ? and expirationDate = ?";
				
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, amount);
				preparedStatement.setInt(2, fID);
				preparedStatement.setString(3, expirationDate);
				preparedStatement.executeUpdate();

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * */
	public ArrayList getInventory() {

		String sql = "SELECT * FROM Inventory";

		ResultSet result = null;
		try {

			preparedStatement = conn.prepareStatement(sql);
			result = preparedStatement.executeQuery();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// code to retrieve an array of rows that contain maps to each attribute
		ArrayList<HashMap> listResult = new ArrayList<>();

		try {

			ResultSetMetaData md = result.getMetaData();
			int columns = md.getColumnCount();
			
			int count = 0;
			
			while (result.next()) {
				HashMap row = new HashMap(columns);
				for (int i = 1; i <= columns; ++i) {
					row.put(md.getColumnName(i), result.getObject(i));
				}
				listResult.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listResult;
	}

	/*
	 * 
	 * */
	public void removeExpired() {
		String sql = "DELETE FROM Inventory WHERE expirationDate > NOW() ";
		ResultSet result = null;
		try {
			String s = "SET FOREIGN_KEY_CHECKS=0";
			preparedStatement = conn.prepareStatement(s);
			preparedStatement.executeQuery();
			preparedStatement = conn.prepareStatement(sql);
			result = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/*
	 * 
	 * */
	public ArrayList getExpired() { // reuse ArrayList getInventory() code

		String sql = "SELECT * FROM Inventory WHERE expirationDate > NOW() ";
		ResultSet result = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			result = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// code to retrieve an array of rows that contain maps to each attribute
		ArrayList<HashMap> listResult = new ArrayList<>();
		try {
			ResultSetMetaData md = result.getMetaData();
			int columns = md.getColumnCount();
			int count = 0;
		
			while (result.next()) {
				HashMap row = new HashMap(columns);
				for (int i = 1; i <= columns; ++i) {
					row.put(md.getColumnName(i), result.getObject(i));
				}
				listResult.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listResult;
	}
	
	/*
	 * 
	 * */
	public void addReview() {

	}

	/*
	 * */
	public void editReview() {

	}

	/*
	 * */
	public void getReview() {

	}

	/*
	 * */
	public void getStat() {

	}

}
