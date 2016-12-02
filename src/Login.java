import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JPanel {
	
	private JTextField uName;
	private JPasswordField passwordField;
	public JButton btnCreateUser;
	public JButton btnLogin;
	private JLabel lblWelcomeToThe;
	private JTextField login;
	private JTextField password;

	/**
	 * Create the panel.
	 */
	public Login() {
            
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		setLayout(gridBagLayout);
				
		JLabel lblWelcomeToThe = new JLabel("Welcome to Fruit Store Team2");
		GridBagConstraints gbc_lblWelcomeToThe = new GridBagConstraints();
		gbc_lblWelcomeToThe.insets = new Insets(0, 0, 3, 3);
		gbc_lblWelcomeToThe.gridx = 3;
		gbc_lblWelcomeToThe.gridy = 1;
		add(lblWelcomeToThe, gbc_lblWelcomeToThe);
		
		JLabel lblLogin = new JLabel("User Email");
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.insets = new Insets(0, 0, 3, 3);
		gbc_lblLogin.gridx = 3;
		gbc_lblLogin.gridy = 3;
		add(lblLogin, gbc_lblLogin);
		
		login = new JTextField("");
		GridBagConstraints gbc_login = new GridBagConstraints();
		gbc_login.insets = new Insets(0, 0, 3, 3);
		gbc_login.fill = GridBagConstraints.HORIZONTAL;
		gbc_login.gridx = 3;
		gbc_login.gridy = 4;
		add(login, gbc_login);
		login.setColumns(10);
		
		JLabel lblPassword = new JLabel("Account Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 3, 3);
		gbc_lblPassword.gridx = 3;
		gbc_lblPassword.gridy = 5;
		add(lblPassword, gbc_lblPassword);
		
		password = new JTextField("");
		GridBagConstraints gbc_password = new GridBagConstraints();
		gbc_password.insets = new Insets(0, 0, 3, 3);
		gbc_password.fill = GridBagConstraints.HORIZONTAL;
		gbc_password.gridx = 3;
		gbc_password.gridy = 6;
		add(password, gbc_password);
		password.setColumns(10);
		
		btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.gridx = 3;
		gbc_btnLogin.gridy = 7;
		add(btnLogin, gbc_btnLogin);
		
		
		btnCreateUser = new JButton("Sign Up");
		GridBagConstraints gbc_btnCreateUser = new GridBagConstraints();
		gbc_btnCreateUser.insets = new Insets(0, 0, 0, 3);
		gbc_btnCreateUser.gridx = 3;
		gbc_btnCreateUser.gridy = 11;
		add(btnCreateUser, gbc_btnCreateUser);
				

	}
        
        public void login(Controller cont) {
            cont.login(login.getText(), password.getText());
        }
	
        public void setFalseCred() {
            JLabel lblIncorrect = new JLabel("Incorrect Username or Password");
            lblIncorrect.setForeground(Color.red);
            GridBagConstraints gbc_lblIncorrect = new GridBagConstraints();
            gbc_lblIncorrect.insets = new Insets(0, 0, 3, 3);
            gbc_lblIncorrect.gridx = 3;
            gbc_lblIncorrect.gridy = 8;
            add(lblIncorrect, gbc_lblIncorrect);
        }
        
	

}
