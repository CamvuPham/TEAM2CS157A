import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StoreModel {
	
	private Connection conn;
	private Statement stmt;

	public StoreModel(String USER, String PASS){
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
	    } catch (ClassNotFoundException e) {
	        System.out.println(e.getMessage());
	    }
		
		String URL = "jdbc:mysql://localhost:3306/";
		
		try {
			
			//Open Connection
			conn = DriverManager.getConnection(URL, USER, PASS);
			
		    stmt = conn.createStatement();
		    
			//Drop FruitStore DB 
		    String sql = "DROP DATABASE FRUITSTORE";
		    stmt.executeUpdate(sql);
		    
		    //Create FruitStore DB
		    sql = "CREATE DATABASE FRUITSTORE";
		    stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		
		createTables();
		
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
		   File file = new File("fruitStore.sql");
		   
		   String i = "";
		   
		    try {

		        Scanner sc = new Scanner(file);

		        while (sc.hasNextLine()) {
		             i += "" + sc.nextLine();
		        }
		        sc.close();
		        
		    } 
		    catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
		    
			try {
				
			    stmt.executeUpdate(i);
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.print(e.getMessage());
			}
	}
	
	/*
	 * */
	public void createUser(String username, String password, String email){
		//PreparedStatement pStatement = conn.prepareStatement("INSERT INTO User VALUES(?????)");
	   
	    String query = "INSERT INTO User(username,password,email) VALUES('" + username +"'," + password + ",'" + email +"')";
	    
	    Statement stmtDrop;
		try {
			stmtDrop = conn.createStatement();
			stmtDrop.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	      
	}
	
	/*
	 * 
	 * */
	public void editUser(){
		
	}
	
	/*
	 * */
	public void getUser(){
		
	}
	
	/*
	 * 
	 * */
	public void makeOrder(){
		
	}
	
	/*
	 * 
	 * */
	public void editOrder(){
		
	}
	
	/*
	 * */
	public void getOrders(){
		
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
