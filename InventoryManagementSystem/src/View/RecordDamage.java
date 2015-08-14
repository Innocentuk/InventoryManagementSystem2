package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import Model.DamageItem;
import Model.Item;
import Model.Price;
import Model.Sales;
import Model.Stock;
import Service.DamageServiceImplementation;
import Service.ItemServiceImplementation;
import Service.PriceServiceImplementation;
import Service.SalesServiceImplementation;
import Service.StockServiceImplementation;

public class RecordDamage extends JPanel implements ActionListener{

	JTextField itemTF, qtyTF, staffTF, reasonTF, dateTF;
	JButton addB;
	JTextArea displayArea;
	JComboBox itemList;
	StockServiceImplementation sServe;
	DamageServiceImplementation dServe;
	ItemServiceImplementation iServe;
	DamageItem di;
	Stock st;
	
	Date day = new Date();
	String date = new SimpleDateFormat ("yyyy-MM-dd, HH:mm:ss").format (day);
	
	public RecordDamage() {
		sServe = new StockServiceImplementation();
		dServe = new DamageServiceImplementation();
		iServe = new ItemServiceImplementation();
		di = new DamageItem();
		st = new Stock();
		
		this.setLayout(null);
		this.setVisible(true);
		//setBounds(100, 60, 700, 500);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel banner = new JLabel("Record Damage");
		banner.setFont(new Font("Algerian", Font.PLAIN, 14));
		banner.setForeground(Color.BLUE);
		
		JLabel qtylab = new JLabel("Enter quantity");
		JLabel staffLab = new JLabel("Staff stamp");//
		//JLabel itemLab = new JLabel("Enter item");
		JLabel reasonLab = new JLabel("Reason");//
		JLabel datLab = new JLabel("today's date");
		
		addB = new JButton("Add");
		addB.addActionListener(this);
		
		itemList = new JComboBox();
		itemList.setPreferredSize(new Dimension(100, 40));
		itemList.setBorder(new TitledBorder("choose product"));
		
		try {
			getProductFromDatabase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//itemTF = new JTextField(5);
		qtyTF = new JTextField(5);
		staffTF = new JTextField(5);//
		reasonTF = new JTextField(5);
		dateTF = new JTextField(""+ date);//
		
		JPanel display = new JPanel();
		display.setBorder(new TitledBorder("Product Entered"));
		display.add(new JSeparator(JSeparator.VERTICAL), BorderLayout.LINE_START);
		displayArea = new JTextArea(80, 50);
		displayArea.setEditable(true);
		JScrollPane scrollP = new JScrollPane(displayArea);
		display.add(scrollP, BorderLayout.CENTER);
		
		banner.setBounds(200, 20, 400, 30);
		staffLab.setBounds(80, 70, 200, 30);
		//itemLab.setBounds(80, 110, 200, 30);
		qtylab.setBounds(80, 150, 200, 30);
		reasonLab.setBounds(80, 190, 200, 30);
		datLab.setBounds(80, 230, 200, 30);
		staffTF.setBounds(300, 70, 200, 30);
		itemList.setBounds(300, 100, 200, 50);
		qtyTF.setBounds(300, 150, 200, 30);
		reasonTF.setBounds(300, 190, 200, 30);
		dateTF.setBounds(300, 230, 200, 30);
		addB.setBounds(300, 270, 100, 30);
		display.setBounds(700, 70, 500, 200);
		
		//add(itemLab); 
		add(itemList);
		add(qtylab); 
		add(qtyTF);
		add(reasonLab); 
		add(reasonTF);
		add(datLab); 
		add(dateTF);
		add(staffLab); 
		add(staffTF);
		add(banner);
		add(addB);
		add(display);
	}
	
	private void getProductFromDatabase() throws Exception {
		List<Item> listItem = iServe.findAllItem();
		for(Item i : listItem){
			itemList.addItem(i.getName());
		}
		itemList.setSelectedIndex(0);
	}
	
	public void addDetail(){
		
		try {
		di.setItem((String) itemList.getSelectedItem());
		
		di.setQty(Integer.parseInt(qtyTF.getText()));
		qtyTF.setText("");
		
		di.setStaffstamp(staffTF.getText());
		staffTF.setText("");
		
		di.setReason(reasonTF.getText());
		reasonTF.setText("");
		
		di.setDate(dateTF.getText());
		dateTF.setText("");
		
		dServe.saveItem(di);
		JOptionPane.showMessageDialog(null, "Product recorded!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(addB)){
			if(qtyTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter quantity!"); 
			
			else if(staffTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter your name!");
			
			//else if(itemTF.getText().equals(""))
				//JOptionPane.showMessageDialog(null, "You forgot to enter product!");
			
			else if(reasonTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter reason!");
			
			else{
				try {
					String key = (String) itemList.getSelectedItem();
					st = sServe.findItemByName(key);
					
				if(st.getQuantity() < 1){
					//System.out.println("no item in stock ");
					JOptionPane.showMessageDialog(null, "product is finished");
					return;
				}
				int getqt = Integer.parseInt (qtyTF.getText());
				st.setDamagedNo(st.getDamagedNo()+ getqt);
				st.setQuantity(st.getQuantity()- getqt);
				sServe.updateStock(st);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				addDetail();
			}
		}
	}

}
