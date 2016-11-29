import java.util.ArrayList;
import java.sql.* ;  // for standard JDBC programs
import java.math.* ; // for BigDecimal and BigInteger support

public class Store {
	private Connection conn;
	public Store(String USER, String PASS){
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
	    } catch (ClassNotFoundException e) {
	        System.out.println(e.getMessage());
	    }
		
		String URL = "jdbc:mysql://localhost:3306/FruitStore";
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
	}
	
	/*
	 * 
	 * */
	public void createUser(){
		//PreparedStatement pStatement = conn.prepareStatement("INSERT INTO User VALUES(?????)");
	   
	     
	      
	    String query = "INSERT INTO User(username,password,email) VALUES('anthony',123,'test@gmail.com')";
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
