import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class StoreGUI extends Frame {

	public StoreGUI() {

		String[] dummy = { "pear", "apple" };

		JPanel makeOrder = makeOrderPanel(dummy);

		add(makeOrder);

		setTitle("Fruit Store");
		setSize(350, 700);
		setVisible(true);
	}

	public JPanel makeOrderPanel(String[] listOfFruit) {

		JPanel input = new JPanel();
		input.setLayout(new FlowLayout());

		Label titleLabel = new Label("Place an order");

		JButton add = new JButton("+");
		add.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  JPanel newb = orderItemPanel(listOfFruit);
			  input.add(newb);
			  input.revalidate();
			  input.repaint();
		  }
		});
		
		JButton button = new JButton("Order");


		input.add(titleLabel);
		input.add(add);		
		input.add(button);
		input.add(orderItemPanel(listOfFruit));


		return input;

	}
	
	public JPanel orderItemPanel(String[] listOfFruit){
		JPanel input = new JPanel();
		input.setLayout(new FlowLayout());
		
		Label amountLabel = new Label("Amount: ");
		Label fruitLabel = new Label("Fruit: ");
		
		TextField tfInput = new TextField(10);
		add(tfInput);

		
		JComboBox<String> fruitSelection = new JComboBox<String>(listOfFruit);
		
		input.add(fruitLabel);
		input.add(fruitSelection);
		
		input.add(amountLabel);
		input.add(tfInput);
				
		return input;
	}

}
