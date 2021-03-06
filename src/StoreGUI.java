import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StoreGUI extends JFrame {

	String[] listOfFruit;
	ArrayList inventory;

	HashMap<String,JPanel> panels;
	// Login User Panel
	TextField tfuLoginUser;
	TextField tfpLoginUser;
	JButton submitLoginUser;

	// Create User Panel
	TextField tfuCreateUser;
	TextField tfeCreateUser;
	TextField tfpCreateUser;
	JButton submitCreateUser;

	// Order Item Panel
	TextField tforderItem;
	JButton addButton;

	// Add Fruit Panel
	JButton submitaddFruit;
	TextField tfnaddFruit;
	TextField tfpaddFruit;

	// Add Inv Panel
	TextField tfnaddInv;
	TextField tfeaddInv;
	TextField tfaaddInv;
	JButton submitaddInv;
	JComboBox<String> fruitSelectionaddInv;

	//left and right panels
	JPanel left = new JPanel();
	JPanel right = new JPanel();
	
	//inventory
	JLabel inv;
	
	
	// Add Review Panel
	TextField tfcaddRvw;
	JButton submitaddRvw;
	JComboBox<String> fruitSelectionaddRate;
	JComboBox<String> fruitSelectionaddRvw;
	String[] rates = {"1","2","3","4","5"};
	//Integer[] rates = {1,2,3,4,5};
	/*
	 * Constructor
	 * 
	 * @param listOfFruit array of fruit to be included in drop down menu
	 */
	public StoreGUI(ArrayList inventory, ArrayList listOfFruit) {
		panels = new HashMap<>();
		
		//this.listOfFruit = toStringArray(listOfFruit);
		String[] listOfFruitStore = new String[listOfFruit.size()];
		for(int i = 0; i<listOfFruit.size();i++){
			HashMap<String, String> fruit = (HashMap)listOfFruit.get(i);
			listOfFruitStore[i] = fruit.get("Name"); 
		}
		
		
		this.listOfFruit = listOfFruitStore;
		this.inventory = inventory;
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		
		left.setLayout(new GridLayout(8,1));
		//JPanel makeOrder = makeOrderPanel();
		//add(makeOrder);
		
		JPanel createUser = createUserPanel();
		left.add(createUserPanel());
		left.add(new JLabel(" "));

		left.add(createLoginPanel());
		left.add(new JLabel(" "));

		JPanel addFruit = addFruitPanel();
		left.add(addFruit);
		left.add(new JLabel(" "));
				
		JPanel addInv = addInventoryPanel();
		fruitSelectionaddInv = new JComboBox<String>(toStringArray(listOfFruit));
		addInv.add(fruitSelectionaddInv);
		left.add(addInv);
		left.add(new JLabel(" "));
		
		//left.add(removeFruitPanel());
		
		//Camvuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu
		// Add Review Panel - add button out of the method to push it to the bottom
		JPanel addRvw = addReviewPanel();
		fruitSelectionaddRate = new JComboBox<String>(rates);
		addRvw.add(fruitSelectionaddRate);
		fruitSelectionaddRvw = new JComboBox<String>(toStringArray(listOfFruit));
		addRvw.add(fruitSelectionaddRvw);
		submitaddRvw = new JButton("Submit Review");
		addRvw.add(submitaddRvw);
		left.add(addRvw);
				
		// Right Panel
		inv = new JLabel("testing");
		updateInventory();
		right.add(inv);
		
		panels.put("createUser",createUser);
		//panels.put("inv",inv);
		panels.put("addFruit",addFruit);
		panels.put("addInv",addInv);

		
		frame.add(left);
		frame.add(right);
		frame.setTitle("Fruit Store");
		setSize(700, 700);

	    frame.pack();
	    frame.setVisible(true);
	}
	/**/
	public void updateValues(ArrayList listOfFruit, ArrayList inventory) {

		//this.listOfFruit = toStringArray(listOfFruit);
		String[] listOfFruitStore = new String[listOfFruit.size()];
		for(int i = 0; i<listOfFruit.size();i++){
			HashMap<String, String> fruit = (HashMap)listOfFruit.get(i);
			listOfFruitStore[i] = fruit.get("Name"); 
		}
		this.listOfFruit = listOfFruitStore;
		this.inventory = inventory;
	}
	
	public void updateFruitCombo(String s){
		fruitSelectionaddInv.addItem(s);		
	}

	/**
	 * ArrayList -> String[]
	 * 
	 * @param list
	 * */
	public String[] toStringArray(ArrayList listOfFruit) {
		String[] listOfFruitStore = new String[listOfFruit.size()];
		for(int i = 0; i<listOfFruit.size();i++){
			HashMap<String, String> fruit = (HashMap)listOfFruit.get(i);
			listOfFruitStore[i] = fruit.get("Name"); 
		}
		return listOfFruitStore;
	}

	/*
	 * Main panel for add to order menu
	 * 
	 * @return JPanel of main order menu
	 */
	public JPanel makeOrderPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBorder(BorderFactory.createBevelBorder(0));
		Label titleLabel = new Label("Place an order");
		this.addButton = new JButton("+");
		JButton button = new JButton("Order");

		panel.add(titleLabel);
		panel.add(addButton);
		panel.add(button);
		panel.add(orderItemPanel());

		return panel;

	}

	/*
	 * Displays a menu to add fruit to order
	 * 
	 * @return JPanel of add to order menu
	 */
	public JPanel orderItemPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBorder(BorderFactory.createBevelBorder(0));
		Label amountLabel = new Label("Amount: ");
		Label fruitLabel = new Label("Fruit: ");

		this.tforderItem = new TextField(10);
		add(tforderItem);

		JComboBox<String> fruitSelection = new JComboBox<String>(listOfFruit);

		panel.add(fruitLabel);
		panel.add(fruitSelection);

		panel.add(amountLabel);
		panel.add(tforderItem);

		return panel;
	}

	/*
	 * Displays the amount of fruit in stock
	 * 
	 */
	//public JPanel inventoryPanel() {
		//JPanel panel = new JPanel();
		//panel.setBorder(BorderFactory.createBevelBorder(0));
		//panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		//Label amountLabel = new Label("Inventory");
		//for (int i = 0; inventory.size() > i; i++) {
		//	panel.add(new Label(inventory.get(i).toString()));
		//}

	//}
	public void updateInventory(){
		String text = "<html>";
		for (int i = 0; inventory.size() > i; i++) {
			text = text +"<br>"+inventory.get(i).toString();
		}
		text = text +"</html>";
		System.out.println(text);
		inv.setText(text);
	}
	
	public JPanel addInventoryPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(4, 1));
		panel.setBorder(BorderFactory.createBevelBorder(0));
		//Label nameLabel = new Label("Fruit name: ");
		Label expLabel = new Label("Expiration Date: ");
		Label amountLabel = new Label("Amount: ");

		this.tfeaddInv = new TextField(10);
		this.tfaaddInv = new TextField(10);

		
		//panel.add(fruitSelectionaddInv);
		panel.add(expLabel);
		panel.add(tfeaddInv);

		panel.add(amountLabel);
		panel.add(tfaaddInv);

		submitaddInv = new JButton("Add Fruit to Inventory");

		panel.add(submitaddInv);

		return panel;
	}
	
	/*
	 * Menu for employee types to add fruit types of fruit
	 */

	public JPanel addFruitPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1));
		panel.setBorder(BorderFactory.createBevelBorder(0));
		Label nameLabel = new Label("Fruit name: ");
		Label priceLabel = new Label("Price: ");

		this.tfnaddFruit = new TextField(10);
		this.tfpaddFruit = new TextField(10);

		panel.add(nameLabel);
		panel.add(tfnaddFruit);

		panel.add(priceLabel);
		panel.add(tfpaddFruit);

		submitaddFruit = new JButton("Add Fruit");

		panel.add(submitaddFruit);

		return panel;
	}

	/*
	 * Menu for employee types to remove fruit from inventory
	 */
	public JPanel removeFruitPanel() {
		JPanel panel = new JPanel();

		JComboBox<String> fruitSelection = new JComboBox<String>(listOfFruit);

		JButton remove = new JButton("Remove");
		panel.add(remove);

		return panel;
	}

	/*
	 * Leaving a review
	 * 
	 * 
	 */
	public JPanel addRatingPanel() {
		JPanel panel = new JPanel();

		return panel;
	}

	/*
	 * Edit / delete a rating
	 */
	public JPanel editRatingPanel() {
		JPanel panel = new JPanel();

		return panel;
	}

	/*
	 * Displays statistics of the store
	 * 
	 */
	public JPanel statPanel() {
		JPanel panel = new JPanel();

		return panel;
	}

	/*
	 * Displays past order and menu to edit order
	 * 
	 */
	public JPanel pastOrderPanel() {
		JPanel panel = new JPanel();

		return panel;
	}

	/*
	 * 
	 * */
	public JPanel createUserPanel() {

		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(4, 1));
		panel.setBorder(BorderFactory.createBevelBorder(0));
		Label userLabel = new Label("Username: ");
		Label emailLabel = new Label("Email: ");
		Label passLabel = new Label("Password: ");

		this.tfuCreateUser = new TextField(10);
		this.tfeCreateUser = new TextField(10);
		this.tfpCreateUser = new TextField(10);

		panel.add(userLabel);
		panel.add(tfuCreateUser);

		panel.add(emailLabel);
		panel.add(tfeCreateUser);

		panel.add(passLabel);
		panel.add(tfpCreateUser);

		this.submitCreateUser = new JButton("Create Account");

		panel.add(submitCreateUser);

		return panel;

	}

	/*
	 * 
	 * */

	public JPanel createLoginPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(4, 2));
		panel.setBorder(BorderFactory.createBevelBorder(0));
		Label userLabel = new Label("Username: ");
		Label passLabel = new Label("Password: ");

		tfuLoginUser = new TextField(10);
		tfpLoginUser = new TextField(10);

		panel.add(userLabel);
		panel.add(tfuLoginUser);

		panel.add(passLabel);
		panel.add(tfpLoginUser);

		submitLoginUser = new JButton("Login");

		panel.add(submitLoginUser);
		return panel;
	}

	// Camvuuuuuuuuuuuuuuuuuuu
	public JPanel addReviewPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(4, 1));
		panel.setBorder(BorderFactory.createBevelBorder(0));
		Label reviewCommentLabel = new Label("Comment:");
		Label ratingLabel = new Label("Rating Number:");
		Label fruitReviewLabel = new Label("Fruit to Review:");
		
		
		panel.add(reviewCommentLabel);
		this.tfcaddRvw = new TextField();
		panel.add(tfcaddRvw);
		
		panel.add(ratingLabel);
		panel.add(fruitReviewLabel);
		return panel;
	}
	
	
}