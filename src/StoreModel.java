import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;

public class StoreModel {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement preparedStatement = null;
	public StoreModel(String USER, String PASS){
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
	    } catch (ClassNotFoundException e) {
	        System.out.println(e.getMessage());
	    }
		
		String URL = "jdbc:mysql://localhost:3306/FruitStore";
		
		try {
			
			//Open Connection
			conn = DriverManager.getConnection(URL, USER, PASS);
			
		    stmt = conn.createStatement();
		    
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		
	}
	
	public void createTables(){
		
		/*
		 * User(uID, username, password, email, isEmployee)
		   Fruit (fID, name, price)
		   Inventory(iID, fID, timeStamp, expirationDate, amount)
		   Order (oID, uID, timeStamp,  totalPrice, updatedAt)
		   OrderItem (oiID, oID, fID, amount)
		   Archive(oID, uID, timeStamp, totalPrice)
		 * 
		 * */
		
		try {
		    
			//Drop FruitStore DB 
		    String sql = "";
		    stmt.executeUpdate(sql);
		    
		    //Create FruitStore DB
		    sql = "CREATE DATABASE FRUITSTORE";
		    stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		
	}
	
	/**
	 * creates a User in the database, will print "Username or email already taken" if username or email are already in the database
	 * @param username
	 * @param password
	 * @param email
	 */
	public void createUser(String username, String password, String email){
		 String sql = "SELECT * FROM User WHERE username = ? OR email = ?";
		 ResultSet result = null;
	     try {
	    	 
			
			  preparedStatement= conn.prepareStatement(sql);
			  preparedStatement.setString(1, username);
			  preparedStatement.setString(2, email);
			  result =  preparedStatement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	     try {
			if (!result.next()){
				//ResultSet is empty
				
			 sql = "INSERT INTO User(username,password,email) VALUES(?,?,?)";
			 
				 
				
				  preparedStatement= conn.prepareStatement(sql);
				  preparedStatement.setString(1, username);
				  preparedStatement.setString(2, password);
				  preparedStatement.setString(3, email);
				  preparedStatement.executeUpdate();
		
			 }
			 else{
				 System.out.println("Username or email already taken.");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * updates the user specified by uID in the database
	 * @param username
	 * @param password
	 * @param email
	 * @param isEmployee
	 * @param uID
	 */
	public void editUser(String username, String password, String email, int isEmployee, int uID){
		 try {
				
					
				 String sql = "UPDATE User SET username = ?,password = ?, email = ?, isEmployee = ? WHERE uID = ?";
				 
					 
					
					  preparedStatement= conn.prepareStatement(sql);
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
	 * Gets an array that contains a map to each attribute of the user specified by username
	 * @param username
	 * @return
	 */
	public ArrayList getUser(String username){
		 String sql = "SELECT * FROM User WHERE username = ?";
		 ResultSet result = null;

	     try {
	    	 
			
			  preparedStatement= conn.prepareStatement(sql);
			  preparedStatement.setString(1, username);
			  result =  preparedStatement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	     
	     
	    //code to retrieve an array of rows that contain maps to each attribute
		ArrayList<HashMap> listResult = new ArrayList<>();

		try{
		
		ResultSetMetaData md = result.getMetaData();
		  int columns = md.getColumnCount();
		  
		  while (result.next()){
		     HashMap row = new HashMap(columns);
		     for(int i=1; i<=columns; ++i){           
		      row.put(md.getColumnName(i),result.getObject(i));
		     }
		     listResult.add(row);
		  }
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		 return listResult;
		
		
		
	}
	
	/**
	 * makes an order
	 * @param uID
	 * @param price
	 */
	public void makeOrder(int uID,int price){
		  try {
				
					
				String sql = "INSERT INTO Orders(uID,tStamp,totalPrice,updatedAt) VALUES(?,timestamp(NOW()),?,CURDATE())";
				 
					 
					
					  preparedStatement= conn.prepareStatement(sql);
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
	 * @param oID
	 * @param uID
	 * @param price
	 */
	public void editOrder(int oID,int uID,int price){
		try{
			String sql = "UPDATE Order SET price = ? WHERE oID = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, price);
			preparedStatement.setInt(2, oID);
			preparedStatement.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * gets all Orders made by a User based on uID, returns an arraylist containing rows which are represented as maps
	 * with each attribute being a key
	 * @param uID
	 * @return
	 */
	public ArrayList getOrders(int uID){
		 String sql = "SELECT * FROM Orders WHERE uID = ?";
		 ResultSet result = null;

	     try {
	    	 
			
			  preparedStatement= conn.prepareStatement(sql);
			  preparedStatement.setInt(1, uID);
			  result =  preparedStatement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	     
	     
	    //code to retrieve an array of rows that contain maps to each attribute
		ArrayList<HashMap> listResult = new ArrayList<>();

		try{
		
		ResultSetMetaData md = result.getMetaData();
		  int columns = md.getColumnCount();
		  
		  while (result.next()){
		     HashMap row = new HashMap(columns);
		     for(int i=1; i<=columns; ++i){           
		      row.put(md.getColumnName(i),result.getObject(i));
		     }
		     listResult.add(row);
		  }
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		 return listResult;
		
		
		
	}
	
	/*
	 * 
	 * */
	public void addFruit(){
		
	}

	/*
	 * */
	public void getListOfFruit(){

	}
	
	/*
	 * */
	public void getInventory(){
		
	}
	
	/*
	 * 
	 * */
	public void removeExpired(){
		
	}
	
	/*
	 * 
	 * */
	public void addReview(){
		
	}
	
	/*
	 * */
	public void editReview(){
		
	}
	
	/*
	 * */
	public void getReview(){
		
	}
	
	/*
	 * */
	public void getStat(){
		
	}
	
}
