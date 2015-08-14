package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Model.Item;
import Model.Price;
import Model.Stock;
import Service.ItemServiceImplementation;
import Service.StockServiceImplementation;

public class Restock extends JPanel implements ActionListener{

	JTextField qtyTF, itemTF;
	JButton restokB;
	JComboBox itemList;
	
	StockServiceImplementation sServe;
	ItemServiceImplementation iServe;
	Stock st;
	Date day = new Date();
	String date = new SimpleDateFormat ("yyyy-MM-dd, HH:mm:ss").format (day);
	
	public Restock() {
		
		sServe = new StockServiceImplementation();
		iServe = new ItemServiceImplementation();
		st = new Stock();
		this.setLayout(null);
		
		JLabel banner = new JLabel("Make Restock:");
		banner.setFont(new Font("Algerian", Font.PLAIN, 14));
		banner.setForeground(Color.BLUE);
		
		JLabel itemLab = new JLabel("Enter product name");
		JLabel qtyLab = new JLabel("Enter quantity to add");
		restokB = new JButton("Add");
		restokB.addActionListener(this);
		qtyTF = new JTextField(10);
		itemTF = new JTextField(10);
		
		itemList = new JComboBox();
		itemList.setPreferredSize(new Dimension(100, 40));
		
		banner.setBounds(200, 20, 400, 30);
		itemLab.setBounds(100, 70, 200, 30);
		qtyLab.setBounds(100, 110, 200, 30);
		itemList.setBounds(300, 70, 200, 30);
		qtyTF.setBounds(300, 110, 200, 30);
		restokB.setBounds(300, 150, 100, 30);
		
		add(banner);
		add(itemLab);
		add(itemList);
		add(qtyLab);
		add(qtyTF);
		add(restokB);
		this.setVisible(true);
		try {
			getDBProducts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void getDBProducts() throws Exception {
		List<Item> itList = iServe.findAllItem();
		for(Item i : itList){
			itemList.addItem(i.getName());
		}
		itemList.setSelectedIndex(0);
	}
	
	public void addDetails(){
		try {
		st.setItem((String) itemList.getSelectedItem());
		String key = (String) itemList.getSelectedItem();
		st = sServe.findItemByName(key);
		//itemTF.setText("");
		if(st == null){
			JOptionPane.showMessageDialog(null, "Product with name "+key+ " is not instock");
		}
		//need to sum the qty in stock and qty to be added.
		int getqt = Integer.parseInt (qtyTF.getText());
		st.setQuantity(st.getQuantity() + getqt );
		qtyTF.setText("");
		
			sServe.updateStock(st);
			JOptionPane.showMessageDialog(null, "Restock successful!"); 
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//display();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(restokB)){
			
			if(qtyTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter quantity!"); 
			
			//else if(itemTF.getText().equals(""))
				//JOptionPane.showMessageDialog(null, "Please enter product name!");
			else{
				Boolean pass = new Boolean(true);
				try{
					Integer.parseInt(qtyTF.getText());
				
				}	catch(NumberFormatException nfe){
					qtyTF.setText("");
					JOptionPane.showMessageDialog(null, "You must enter number!");
				}
					addDetails();
			}
		}
	}

}
