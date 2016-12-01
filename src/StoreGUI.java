import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StoreGUI extends JFrame {
	
	String[] listOfFruit;
	
	/*
	 * Constructor
	 * 
	 * @param listOfFruit array of fruit to be included in drop down menu
	 * */
	public StoreGUI(String[] listOfFruit) {

		this.listOfFruit = listOfFruit;
		
		
		//JPanel makeOrder = makeOrderPanel();
		//add(makeOrder);
		
		JPanel createUser = createUserPanel();
		add(createUser);
		
		setTitle("Fruit Store");
		setSize(350, 700);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}
	
	
	/*
	 * Main panel for add to order menu
	 * 
	 * @return JPanel of main order menu
	 * */
	public JPanel makeOrderPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		Label titleLabel = new Label("Place an order");

		JButton add = new JButton("+");
		add.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  JPanel newb = orderItemPanel();
			  panel.add(newb);
			  panel.revalidate();
			  panel.repaint();
		  }
		});
		
		JButton button = new JButton("Order");


		panel.add(titleLabel);
		panel.add(add);		
		panel.add(button);
		panel.add(orderItemPanel());


		return panel;

	}
	
	/*
	 * Displays a menu to add fruit to order
	 * 
	 * @return JPanel of add to order menu
	 * */
	public JPanel orderItemPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		Label amountLabel = new Label("Amount: ");
		Label fruitLabel = new Label("Fruit: ");
		
		TextField tfpanel = new TextField(10);
		add(tfpanel);

		
		JComboBox<String> fruitSelection = new JComboBox<String>(listOfFruit);
		
		panel.add(fruitLabel);
		panel.add(fruitSelection);
		
		panel.add(amountLabel);
		panel.add(tfpanel);
				
		return panel;
	}
	
	/*
	 * Displays the amount of fruit in stock
	 * 
	 * */
	public JPanel inventoryPanel(){
		JPanel panel = new JPanel();
		
		return panel;
	}
	
	/*
	 * Menu for employee types to add fruit to inventory
	 * */
	
	public JPanel addFruitPanel(){
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(3,2));
		
		Label nameLabel = new Label("Fruit name: ");
		Label priceLabel = new Label("Price: ");
		
		TextField tfpaneln = new TextField(10);
		TextField tfpanelp = new TextField(10);

		panel.add(nameLabel);
		panel.add(tfpaneln);

		panel.add(priceLabel);
		panel.add(tfpanelp);
		
		JButton submit = new JButton("Submit");
		
		panel.add(submit);
		
		return panel;
	}
	
	/*
	 * Menu for employee types to remove fruit from inventory
	 * */
	public JPanel removeFruitPanel(){
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
	 * */
	public JPanel addRatingPanel(){
		JPanel panel = new JPanel();
		
		return panel;
	}
	
	/*
	 * Edit / delete a rating
	 * */
	public JPanel editRatingPanel(){
		JPanel panel = new JPanel();
		
		return panel;
	}
	
	/*
	 * Displays statistics of the store
	 * 
	 * */
	public JPanel statPanel(){
		JPanel panel = new JPanel();
		
		return panel;
	}
	
	/*
	 * Displays past order and menu to edit order
	 * 
	 * */
	public JPanel pastOrderPanel(){
		JPanel panel = new JPanel();
		
		return panel;
	}
	
	/*
	 * 
	 * */
	public JPanel createUserPanel(){
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(4,2));
		
		Label userLabel = new Label("Username: ");
		Label emailLabel = new Label("Email: ");
		Label passLabel = new Label("Password: ");
		
		TextField tfpanelu = new TextField(10);
		TextField tfpanele = new TextField(10);
		TextField tfpanelp = new TextField(10);

		panel.add(userLabel);
		panel.add(tfpanelu);

		panel.add(emailLabel);
		panel.add(tfpanele);

		panel.add(passLabel);
		panel.add(tfpanelp);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  System.out.println(tfpanelu.getText());
		  }
		});
		
		panel.add(submit);

		return panel;

	}
	

}
