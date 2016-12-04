import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StoreGUI extends JFrame {

	String[] listOfFruit;
	ArrayList inventory;

	ArrayList<JPanel> panels;
	//Create Login Panel
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

	/**
	 * Constructor
	 * 
	 * @param listOfFruit array of fruit to be included in drop down menu
	 */
	public StoreGUI(ArrayList inventory, ArrayList listOfFruit) {

		this.listOfFruit = toStringArray(listOfFruit);
		this.inventory = inventory;
		panels = new ArrayList<JPanel>();
		
		JPanel addFruitPanel = new addFruitPanel();
		JPanel inventoryPanel = new inventoryPanel();
		JPanel addInventoryPanel = new addInventoryPanel();
		JPanel createUserPanel = new createUserPanel();
		JPanel loginUserPanel = new createLoginPanel();
		//JPanel orderPanel = new makeOrderPanel();
		//JPanel orderItemPanel = new orderItemPanel();
		
		this.add(addFruitPanel);
		this.add(inventoryPanel);
		this.add(addInventoryPanel);
		this.add(createUserPanel);
		this.add(loginUserPanel);
		//this.add(orderPanel);
		//this.add(orderItemPanel);

		setLayout(new FlowLayout());
		setTitle("Fruit Store");
		setSize(350, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**/
	public void updateValues(ArrayList listOfFruit, ArrayList inventory) {

		this.listOfFruit = toStringArray(listOfFruit);
		this.inventory = inventory;
		
	}

	/**
	 * ArrayList -> String[]
	 * 
	 * @param list
	 * */
	public String[] toStringArray(ArrayList list) {
		String arrlist[] = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {
			arrlist[i] = list.get(i).toString();
		}

		return arrlist;
	}
	
	/**
	 * Main panel for add to order menu
	 * 
	 * @return JPanel of main order menu
	 */
	public class makeOrderPanel extends JPanel  {

		public makeOrderPanel(){
		this.setLayout(new FlowLayout());

		Label titleLabel = new Label("Place an order");

		addButton = new JButton("+");

		JButton button = new JButton("Order");
		
		this.add(titleLabel);
		this.add(addButton);
		this.add(button);

		}

	}

	/**
	 * Displays a menu to add fruit to order
	 * 
	 * @return JPanel of add to order menu
	 */
	public class orderItemPanel extends JPanel  {
		
		public orderItemPanel(){
		this.setLayout(new FlowLayout());

		Label amountLabel = new Label("Amount: ");
		Label fruitLabel = new Label("Fruit: ");

		tforderItem = new TextField(10);

		JComboBox<String> fruitSelection = new JComboBox<String>(listOfFruit);

		this.add(fruitLabel);
		this.add(fruitSelection);

		this.add(amountLabel);
		this.add(tforderItem);
		
		}

	}

	/**
	 * Displays the amount of fruit in stock
	 * 
	 */
	public class inventoryPanel extends JPanel {

		public inventoryPanel() {

			this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

			for (int i = 0; inventory.size() > i; i++) {
				this.add(new Label(inventory.get(i).toString()));
			}

		}
	}

	public class addInventoryPanel extends JPanel {
		
		public addInventoryPanel(){
		this.setLayout(new GridLayout(4, 2));

		Label nameLabel = new Label("Fruit: ");
		Label expLabel = new Label("Expiration Date: ");
		Label amountLabel = new Label("Amount: ");

		fruitSelectionaddInv = new JComboBox<String>(listOfFruit);
		tfeaddInv = new TextField(10);
		tfaaddInv = new TextField(10);

		this.add(nameLabel);
		this.add(fruitSelectionaddInv);

		this.add(expLabel);
		this.add(tfeaddInv);

		this.add(amountLabel);
		this.add(tfaaddInv);

		submitaddInv = new JButton("Submit");

		this.add(submitaddInv);
		}

	}

	/*
	 * Menu for employee types to add fruit types of fruit
	 */

	public class addFruitPanel extends JPanel {
		
		public addFruitPanel(){
		
		this.setLayout(new GridLayout(3, 2));

		Label nameLabel = new Label("Fruit name: ");
		Label priceLabel = new Label("Price: ");

		tfnaddFruit = new TextField(10);
		tfpaddFruit = new TextField(10);

		this.add(nameLabel);
		this.add(tfnaddFruit);

		this.add(priceLabel);
		this.add(tfpaddFruit);

		submitaddFruit = new JButton("Submit");

		this.add(submitaddFruit);

		}
	}

	/*
	 * Menu for employee types to remove fruit from inventory
	 */
	public class removeFruitPanel extends JPanel {
		
		removeFruitPanel(){
		JComboBox<String> fruitSelection = new JComboBox<String>(listOfFruit);

		JButton remove = new JButton("Remove");
		this.add(remove);
		}

	}

	/*
	 * Leaving a review
	 * 
	 * 
	 */
	public class addRatingPanel extends JPanel  {

	}

	/*
	 * Edit / delete a rating
	 */
	public class editRatingPanel  extends JPanel  {

	}

	/*
	 * Displays statistics of the store
	 * 
	 */
	public class statPanel extends JPanel {

	}

	/*
	 * Displays past order and menu to edit order
	 * 
	 */
	public class pastOrderPanel extends JPanel  {

	}

	/*
	 * 
	 * */
	public class createUserPanel extends JPanel {

		public createUserPanel(){
		this.setLayout(new GridLayout(4, 2));

		Label userLabel = new Label("Username: ");
		Label emailLabel = new Label("Email: ");
		Label passLabel = new Label("Password: ");

		tfuCreateUser = new TextField(10);
		tfeCreateUser = new TextField(10);
		tfpCreateUser = new TextField(10);

		this.add(userLabel);
		this.add(tfuCreateUser);

		this.add(emailLabel);
		this.add(tfeCreateUser);

		this.add(passLabel);
		this.add(tfpCreateUser);

		submitCreateUser = new JButton("Submit");

		this.add(submitCreateUser);
		}
	}
	/*
	 * 
	 * */
	public class createLoginPanel extends JPanel {

		public createLoginPanel(){
		this.setLayout(new GridLayout(4, 2));

		Label userLabel = new Label("Username: ");
		Label passLabel = new Label("Password: ");

		tfuLoginUser = new TextField(10);
		tfpLoginUser = new TextField(10);

		this.add(userLabel);
		this.add(tfuLoginUser);


		this.add(passLabel);
		this.add(tfpLoginUser);

		submitLoginUser = new JButton("Submit");

		this.add(submitLoginUser);
		}
	}

}
