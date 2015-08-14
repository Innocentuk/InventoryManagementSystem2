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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.sound.midi.Receiver;
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

import DAO.LoginDAO;
import Model.Item;
import Model.Login;
import Model.Price;
import Model.SaleModel;
import Model.Sales;
import Model.Stock;
import Service.ItemServiceImplementation;
import Service.LoginServiceImplementation;
import Service.PriceServiceImplementation;
import Service.SalesServiceImplementation;
import Service.StockServiceImplementation;
/**
 * 
 * @author Eucheria
 *
 */
public class MakeSale extends JPanel implements ActionListener{

	JTextField qtyTF, staffTF, statusTF, dateTF, itemTF, nameField;
	JTextArea recieptTF;
	TextArea information;
	JButton addB, clearB, saleB;
	JComboBox itemList, statusBox; 
	String[] state = {"Paid", "Unpaid"}; 
	JLabel priceLab = new JLabel();
	
	ArrayList<SaleModel>  smodel = new ArrayList<SaleModel>();
	
	Locale lc = new Locale("NGA", "NG");
	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(lc);
	//SaleModel sm;
	ItemServiceImplementation iServe;
	PriceServiceImplementation pServe;
	StockServiceImplementation sServe;
	SalesServiceImplementation saServe;
	
	Sales sa;
	Price p;
	Item it;
	Stock st;
	
public MakeSale() {
	
	iServe = new ItemServiceImplementation();
	pServe = new PriceServiceImplementation();
	sServe = new StockServiceImplementation();
	saServe = new SalesServiceImplementation();
	
	st = new Stock();
	sa = new Sales();
	it = new Item();
	p = new Price();
	
	this.setLayout(null);
	this.setVisible(true);
	JLabel banner = new JLabel("Sell Products");
	banner.setFont(new Font("Algerian", Font.PLAIN, 20));
	banner.setForeground(Color.BLUE);
	
	JPanel display = new JPanel();
	display.setBorder(new TitledBorder("Reciept"));
	recieptTF = new JTextArea(100, 80);
	JScrollPane scrollP = new JScrollPane(recieptTF);
	//scrollP.getHorizontalScrollBar();
	recieptTF.setEditable(false);
	display.add(scrollP);
	
	itemList = new JComboBox();
	itemList.setPreferredSize(new Dimension(100, 40));
	itemList.setBorder(new TitledBorder("choose product"));
	itemList.addItemListener(new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent ie) {

			String name = (String) ie.getItem();
			System.out.println(name);
			try {
				Price p = pServe.findPriceByItem(name);
				priceLab.setText(""+p.getSellPrice());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	
	statusBox = new JComboBox(state);
	statusBox.setPreferredSize(new Dimension(100, 40));
	
	JLabel qtylab = new JLabel("Enter quantity");
	JLabel staffLab = new JLabel("Staff stamp");//
	JLabel statusLab = new JLabel("status");
	
	addB = new JButton("Add");
	addB.addActionListener(this);
	clearB = new JButton("Clear");
	clearB.addActionListener(this);
	saleB = new JButton("sale");
	saleB.addActionListener(this);
	
	qtyTF = new JTextField(5);
	staffTF = new JTextField(5);//
	
	banner.setBounds(200, 20, 400, 30);
	staffLab.setBounds(100, 110, 200, 30);
	staffTF.setBounds(300, 110, 200, 30);
	itemList.setBounds(300, 150, 200, 50);
	priceLab.setBounds(300, 200, 200, 50);
	qtylab.setBounds(100, 260, 200, 30);
	qtyTF.setBounds(300, 260, 200, 30);
	//statusLab.setBounds(100, 310, 200, 30);
	//statusBox.setBounds(300, 310, 200, 30);
	clearB.setBounds(300, 310, 100, 30);
	addB.setBounds(450, 310, 100, 30);
	statusLab.setBounds(700, 610, 200, 30);
	statusBox.setBounds(800, 610, 200, 30);
	saleB.setBounds(800, 650, 100, 30);
	display.setBounds(700, 100, 700, 500);
	
	add(banner);
	add(staffLab); 
	add(staffTF);
	add(itemList); 
	add(priceLab);
	add(qtylab); 
	add(qtyTF);
	add(statusLab); 
	add(statusBox);
	add(addB);
	add(clearB);
	add(saleB);
	add(display);
	try {
		getProductFromDatabase();
	} catch (Exception e) {
		e.printStackTrace();
	}
}

private void getProductFromDatabase() throws Exception {
	List<Item> listItem = iServe.findAllItem();
	for(Item i : listItem){
		itemList.addItem(i.getName());
	}
	itemList.setSelectedIndex(0);
}

	public void clearField(){
		qtyTF.setText("");
		recieptTF.setText("");
	}
	public void printinvoice(){
		SaleModel sm = new SaleModel();
		Double total = 0.00;
		String txt = new String();
		recieptTF.setText("");
		recieptTF.setForeground(Color.black);
		recieptTF.setBackground(Color.yellow);
		
			txt = "\t\t\tChimoris Fashion shop"+ "\r\n";
				txt += "\t\t\t\t\tInvoice id: " + sm.getInvoceId() + "\r\n";
				txt += "\t\t\t\t\tDate: " + sm.getDate()+ "\r\n";
				txt += "\t\t\t\t\t Served by: " + staffTF.getText() + "\r\n\n";
				recieptTF.append(txt);
				
				txt = "\t\tItem \t\t" + "Quantity \t\t" + "Price \t\t" + "Total ";
				recieptTF.append(txt);
				for(SaleModel sales : smodel){
					double amount = sales.getQty()*sales.getPrice();
						txt = "\r\n\t\t"+sales.getItemname()+"\t\t" + sales.getQty()+"\t\t" + sales.getPrice()+"\t\t" + amount;
						total += amount;
						recieptTF.append(txt);
				}
				txt = "\r\n\n\n\t\t\t\t\tTotal to pay:\t" + currencyFormat.format(total) + "\r\n\n\n";
				recieptTF.append(txt);
				txt = "\t\tThanks for your patronage!\r\n";
				recieptTF.append(txt);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String key = (String) itemList.getSelectedItem();
		String staffname = staffTF.getText();
		int qty = Integer.parseInt(qtyTF.getText());
		double price = Double.parseDouble(priceLab.getText());
		String state = (String) statusBox.getSelectedItem();
		statusBox.setSelectedIndex(0);
		try {
			st = sServe.findItemByName(key);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		if(e.getSource().equals(addB)){
			
			 if(staffTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter your name!");
			
			 if(qtyTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter quantity!"); 
			
				else if (st == null) 
					 JOptionPane.showMessageDialog(null, "Product with name: "+ key+ " is not in stock.");
				 //}
				 else if(st.getQuantity() < 1)//{
					 JOptionPane.showMessageDialog(null,  key+ " is finished.");
				 //}
				 else if(st.getQuantity() < qty)//{
					 JOptionPane.showMessageDialog(null, "less quantity left");
				 //}
			else{
				SaleModel sal = new SaleModel();
				sal.setDate(sal.getDate());
				sal.setStaffname(staffname);
				sal.setItemname(key);
				sal.setPrice(price);
				sal.setQty(qty);
				sal.setStatus(state);
			
			smodel.add(sal);
			printinvoice();
		}
	}
		else if(e.getSource().equals(clearB)){
			clearField();
		}
		else if(e.getSource().equals(saleB)){
			
			for(SaleModel sm : smodel)
			{
				 String itemname = (String) itemList.getSelectedItem();
				 //String payment = (String) statusBox.getSelectedItem();
				 try {
					st = sServe.findItemByName(itemname);
					st.setSoldNo(st.getSoldNo() + (int)sm.getQty());
					st.setQuantity(st.getQuantity() - (int)sm.getQty());
					
					sServe.updateStock(st);
					
					Sales s = new Sales();
					s.setDate(sm.getDate());
					s.setItem(sm.getItemname());
					s.setQuantity((int)sm.getQty());
					s.setTotalprice(sm.getPrice() * sm.getQty());
					s.setStatus(sm.getStatus());
					s.setStaffStamp(sm.getStaffname());
					
					saServe.saveSales(s);
					JOptionPane.showMessageDialog(null, "Item successfully sold!");
					clearField();
				 } catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		}
		
	}
}
