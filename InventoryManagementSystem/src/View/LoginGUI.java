package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import Model.Login;
import Service.LoginServiceImplementation;

public class LoginGUI extends JFrame implements ActionListener{
	
	JButton blogin;
	JButton closeB;
	//JButton createB = new JButton("New account");
	JPanel panel = new JPanel();
	JLabel userLab;
	JLabel passLab;
	JTextField txuser;
	JPasswordField pass;

	LoginServiceImplementation lServe;
	Login log;
	
	public LoginGUI() {
		super("Login Autentification");
		
		lServe = new LoginServiceImplementation();
		log = new Login();
		
		setSize(400,300);
		setLocation(500,280);
		setLayout (null); 

		userLab = new JLabel("UserName");
		passLab = new JLabel("Password");
		txuser = new JTextField(15);
		pass = new JPasswordField(15);
		
		closeB = new JButton("close");
		closeB.addActionListener(this);
		
		blogin = new JButton("Login");
		blogin.addActionListener(this);
		
		userLab.setBounds(30, 30, 150, 20);
		txuser.setBounds(100,30,150,20);
		passLab.setBounds(30, 65, 150, 20);
		pass.setBounds(100,65,150,20);
		blogin.setBounds(100,100,80,20);
		closeB.setBounds(190, 100, 80, 20);

		add(userLab);
		add(txuser);
		add(passLab);
		add(pass);
		add(blogin);
		add(closeB);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		//showUser();
	}
	public void addDetail(){
		 try {
			
			log = lServe.findItemByName(txuser.getText());
			
			if(log.getPassword().equals(pass.getText()))
			{
				showUser();
				MasterWindow mw = new MasterWindow();
				mw.setVisible(true);
			}
				else{
					JOptionPane.showMessageDialog(null, "Incorrect username or password..Try Again with correct detail");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	}
	public String showUser(){
		System.out.println(log.getUsername());
		return log.getUsername();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(blogin)){
			if(txuser.getText().equals("")) 
			      JOptionPane.showMessageDialog(null, "Empty fields detected! Please fill up all fields");
			   else if(pass.getText().equals(""))  
			      JOptionPane.showMessageDialog(null, "Empty fields detected! Please fill up all fields");
			   else
				   addDetail();
	}
		else if (e.getSource().equals(closeB)){
			System.exit(0);
		}
	}
}
