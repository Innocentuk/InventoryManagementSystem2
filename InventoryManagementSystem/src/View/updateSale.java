package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Model.Sales;
import Service.SalesServiceImplementation;

public class updateSale extends JPanel implements ActionListener{

	SalesServiceImplementation saServe;
	Sales sa;
	
	JTextField statusTF,itemTF;
	JButton updateB;
	
	public updateSale(){
		sa = new Sales();
		saServe = new SalesServiceImplementation();
		
		this.setLayout(null);
		this.setVisible(true);
		JLabel itemLab = new JLabel("enter item");
		JLabel statusLab = new JLabel("enter status");
		JLabel banner = new JLabel("Status Update");
		itemTF = new JTextField(10);
		statusTF = new JTextField(10);
		
		updateB = new JButton("update");
		updateB.addActionListener(this);
		
		banner.setBounds(200, 20, 400, 30);
		itemLab.setBounds(80, 70, 200, 30);
		statusLab.setBounds(80, 110, 200, 30);
		itemTF.setBounds(300, 70, 200, 30);
		statusTF.setBounds(300, 110, 200, 30);
		updateB.setBounds(300, 150, 100, 30);
		
		add(banner);
		add(itemLab);
		add(itemTF);
		add(statusLab);
		add(statusTF);
		add(updateB);
	}
	public void updateDetail() {
		try {
			String key = itemTF.getText();
			sa = saServe.findItemByName(key);
			itemTF.setText("");
			
			if(sa == null){
				JOptionPane.showMessageDialog(null, "Product with name "+key+ " does not in exist");
			}
			sa.setStatus(statusTF.getText());//
			statusTF.setText("");
			
			saServe.updateSale(sa);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(updateB)){
				
			if(itemTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "you forgot to enter product!");
			
			else if(statusTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter status!");
		else{
			Boolean pass = new Boolean(true);
				updateDetail();
		}
		}
	}
}
