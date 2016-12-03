import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateUser extends JPanel {
	public JButton btnBack;
	public JButton btnCreate;
	private JTextField email;
	private JTextField uName;
        private JTextField password;

	public CreateUser() {
            
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		setLayout(gridBagLayout);
		
		JLabel lblNewUser = new JLabel("Create New Account");
		GridBagConstraints gbc_lblNewUser = new GridBagConstraints();
		gbc_lblNewUser.insets = new Insets(0, 0, 3, 3);
		gbc_lblNewUser.gridx = 2;
		gbc_lblNewUser.gridy = 1;
		add(lblNewUser, gbc_lblNewUser);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 3, 3);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 3;
		add(lblName, gbc_lblName);
		
		uName = new JTextField();
		GridBagConstraints gbc_Name = new GridBagConstraints();
		gbc_Name.insets = new Insets(0, 0, 3, 3);
		gbc_Name.fill = GridBagConstraints.HORIZONTAL;
		gbc_Name.gridx = 2;
		gbc_Name.gridy = 4;
		add(uName, gbc_Name);
		uName.setColumns(10);		

		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 3, 3);
		gbc_lblEmail.gridx = 2;
		gbc_lblEmail.gridy = 5;
		add(lblEmail, gbc_lblEmail);
		
		email = new JTextField();
		GridBagConstraints gbc_email = new GridBagConstraints();
		gbc_email.insets = new Insets(0, 0, 3, 3);
		gbc_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_email.gridx = 2;
		gbc_email.gridy = 6;
		add(email, gbc_email);
		email.setColumns(10);
                
                JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 3, 3);
		gbc_lblPassword.gridx = 2;
		gbc_lblPassword.gridy = 7;
		add(lblPassword, gbc_lblPassword);
		
		password = new JTextField();
		GridBagConstraints gbc_password = new GridBagConstraints();
		gbc_password.insets = new Insets(0, 0, 3, 3);
		gbc_password.fill = GridBagConstraints.HORIZONTAL;
		gbc_password.gridx = 2;
		gbc_password.gridy = 8;
		add(password, gbc_password);
		password.setColumns(10);
		
		btnBack = new JButton("Back");
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 3, 3);
		gbc_btnBack.gridx = 1;
		gbc_btnBack.gridy = 9;
		add(btnBack, gbc_btnBack);
		
		btnCreate = new JButton("Create");
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.insets = new Insets(0, 0, 5, 0);
		gbc_btnCreate.gridx = 3;
		gbc_btnCreate.gridy = 9;
		add(btnCreate, gbc_btnCreate);
               
	}
        
        public void createUser(Controller cont) {
           cont.createUser(uName.getText(), email.getText(), password.getText());
        }

}
