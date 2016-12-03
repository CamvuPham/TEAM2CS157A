import java.util.ArrayList;

import javax.swing.JPanel;

import java.sql.* ;  // for standard JDBC programs
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.* ; // for BigDecimal and BigInteger support

public class Store {
	
	StoreGUI view;
	StoreModel model;
	
	public Store(StoreGUI view, StoreModel model){
		
		this.view = view;
		this.model = model;
		
		view.submitCreateUser.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  createUser();
			  }
		});

		
		view.submitaddFruit.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  addFruit();
			  }
		});
		
		view.submitaddInv.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  addInventory();
			  }
		});

	}
	
	/*
	 * 
	 * */
	public void createUser(){

		  String username = view.tfuCreateUser.getText();
		  String password = view.tfpCreateUser.getText();
		  String email = view.tfeCreateUser.getText();
		  
		  model.createUser(username, password, email);
		  
		  System.out.println(model.getUser(username));
	      
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
		
		String name = view.tfnaddFruit.getText();
		int price = Integer.parseInt(view.tfpaddFruit.getText());
		
		model.addFruit(name, price);
		view.updateValues(model.getListOfFruit(), model.getInventory());
		System.out.println(model.getListOfFruit());
		
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
	
	/**/
	public void addInventory(){
		
		String name = view.fruitSelectionaddInv.getSelectedItem().toString();
		String expirationDate = view.tfeaddInv.getText();
		int amount = Integer.parseInt(view.tfaaddInv.getText());
		
		model.addInventory(0, expirationDate, amount);
		view.updateValues(model.getListOfFruit(), model.getInventory());
		System.out.println(model.getInventory());
		System.out.println(name);
	}
	
}
