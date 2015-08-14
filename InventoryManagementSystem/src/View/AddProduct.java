package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Model.Item;
import Model.Price;
import Model.Stock;
import Service.ItemServiceImplementation;
import Service.PriceServiceImplementation;
import Service.StockServiceImplementation;

/**
 *  Class that contains user interface for adding a product in to stock list.
 * @author Eucheria innocent
 *
 */
public class AddProduct extends JPanel implements ActionListener{

	JTextField qtyTF, costPriceTF, sellPriceTF, dateTF, itemTF, restockQtyTF;
	JLabel dateLab;
	JButton addB, restokB;
	JComboBox itemList, itemList2; 
	TextArea info;
	
	PriceServiceImplementation pServe;
	Price p = new Price();
	StockServiceImplementation sServe;
	Stock st = new Stock();
	ItemServiceImplementation iServe;
	
	Date day = new Date();
	String date = new SimpleDateFormat ("yyyy-MM-dd, HH:mm:ss").format (day);
	
	public AddProduct() {
		pServe = new PriceServiceImplementation();
		sServe = new StockServiceImplementation();
		iServe = new ItemServiceImplementation();
		this.setVisible(true);
		this.setLayout(null);
		
		JLabel banner = new JLabel("Enter stock detail");
		banner.setFont(new Font("Algerian", Font.PLAIN, 20));
		banner.setForeground(Color.BLUE);
		
		itemList = new JComboBox();
		itemList.setPreferredSize(new Dimension(100, 40));
		itemList.setBorder(new TitledBorder("Select product"));
		
		try {
			getDBProducts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel qtylab = new JLabel("Enter quantity");
		JLabel costLab = new JLabel("Enter cost price");
		JLabel sellLab = new JLabel("Enter sell price");
		JLabel dateLab = new JLabel("today's date");
		
		addB = new JButton("Add");
		addB.addActionListener(this);
		
		qtyTF = new JTextField(5);
		costPriceTF = new JTextField(5);
		sellPriceTF = new JTextField(5);
		dateTF = new JTextField(""+ date);
		
		info = new TextArea(3,5);
		info.setPreferredSize(new Dimension(50, 100));
		info.setEditable(false);
		
		banner.setBounds(200, 20, 600, 30);
		itemList.setBounds(300, 190, 200, 50);
		qtylab.setBounds(100, 240, 200, 30);
		qtyTF.setBounds(300, 240, 200, 30);
		costLab.setBounds(100, 280, 200, 30);
		costPriceTF.setBounds(300, 280, 200, 30);
		sellLab.setBounds(100, 320, 200, 30);
		sellPriceTF.setBounds(300, 320, 200, 30);
		dateLab.setBounds(100, 360, 200, 30);
		dateTF.setBounds(300, 360, 200, 30);
		addB.setBounds(300, 400, 100, 30);
		//info.setBounds(100, 450, 500, 200);
		info.setBounds(700, 110, 600, 200);
		
		add(banner);
		add(itemList);
		add(qtylab); 
		add(qtyTF);
		add(costLab); 
		add(costPriceTF);
		add(sellLab); 
		add(sellPriceTF);
		add(dateLab); 
		add(dateTF);
		add(addB);
		add(info);
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
		p.setItem((String) itemList.getSelectedItem());
		
		st.setQuantity(Integer.parseInt(qtyTF.getText()));
		qtyTF.setText("");
		
		st.setDate(dateTF.getText());
		p.setDate(dateTF.getText());
		dateTF.setText("");
		
		p.setCostPrice(Double.parseDouble(costPriceTF.getText()));
		costPriceTF.setText("");
		
		p.setSellPrice(Double.parseDouble(sellPriceTF.getText()));
		sellPriceTF.setText("");
		
		    pServe.savePrice(p);
			sServe.saveStock(st);
			disPlay();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	public void disPlay(){
		info.setText("Item number: "+ st.getId() +
				 "\nProduct name: "+ st.getItem() +
				 "\nProduct quantity instock: "+ st.getQuantity()+
				 "\nProduct quantity sold: "+ st.getSoldNo()+
				 "\nProduct quantity damaged: "+ st.getDamagedNo()+
				 "\nProduct date: "+ st.getDate()
				 
				 );
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(addB)){
			
			if(qtyTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter quantity!"); 
			
			else if(costPriceTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter cost price!");
			
			else if(sellPriceTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter selling price!");
			
			else if(itemList.getSelectedItem().equals(st.getItem()))
				JOptionPane.showMessageDialog(null, "Product already instock");
				
			else{
				Boolean pass = new Boolean(true);
				try{
					Integer.parseInt(qtyTF.getText());
					Double.parseDouble(costPriceTF.getText());
					Double.parseDouble(sellPriceTF.getText());
				}	catch(NumberFormatException nfe){
					qtyTF.setText("");
					costPriceTF.setText("");
					sellPriceTF.setText("");
					JOptionPane.showMessageDialog(null, "You must enter number!");
				}
					addDetails();
					JOptionPane.showMessageDialog(null, "Product added successfully!");
			}
		}
	}

}
