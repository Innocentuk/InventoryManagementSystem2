package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Model.Item;
import Model.Stock;
import Service.ItemServiceImplementation;
import Service.StockServiceImplementation;
/**
 * Class that contain a user interface for serach for item in stock
 * @author Eucheria innocent
 *
 */
public class SearchProduct extends JPanel implements ActionListener {
	
	JTextField nameField;
	 TextArea information; 
	 JButton checkB;
	 JLabel checkL;
	 JComboBox itemList;
	 
	 Locale lc = new Locale("NGA", "NG");
	 NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(lc);
	 StockServiceImplementation sServe;
	 ItemServiceImplementation iServe;
	 Stock st;
	 
	public SearchProduct() {
		sServe = new StockServiceImplementation();
		iServe = new ItemServiceImplementation();
		st = new Stock();
		//this.setLayout(new FlowLayout());

		setBackground(new Color(240, 240, 240));
		setVisible(true);
		setLayout(new BorderLayout(0, 0));
		
		JPanel cnterPan = new JPanel();
		add(cnterPan, BorderLayout.WEST);
		cnterPan.setLayout(new GridLayout(0, 1));
		
		JPanel checkStockPanel = new JPanel();
		checkStockPanel.setLayout(new BorderLayout());
		checkStockPanel.setBorder(new TitledBorder("Product found"));
		cnterPan.add(checkStockPanel);
		
		
		JPanel checkBPanel = new JPanel();
		checkBPanel.setBorder(new TitledBorder("Serach for a product in stock"));
		checkBPanel.setLayout(new FlowLayout());
		add(checkBPanel, BorderLayout.NORTH);
		
		itemList = new JComboBox();
		itemList.setPreferredSize(new Dimension(90, 30));
		try {
			getProductFromDatabase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		checkB = new JButton("Search");
		checkB.addActionListener(this);
		//nameField = new JTextField(10);
		checkL = new JLabel("Enter Product name");
		
		checkBPanel.add(checkL);
		checkBPanel.add(itemList);
		checkBPanel.add(checkB, "South");
		
		information = new TextArea(3, 50);
		information.setPreferredSize(new Dimension(50, 100));
		information.setEditable(false);
		//information.
		JScrollPane scrollPane_1 = new JScrollPane(information);
		checkStockPanel.add(scrollPane_1); //BorderLayout.CENTER);
	}
	private void getProductFromDatabase() throws Exception {
		List<Item> listItem = iServe.findAllItem();
		for(Item i : listItem){
			itemList.addItem(i.getName());
		}
		itemList.setSelectedIndex(0);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String key = (String) itemList.getSelectedItem();
		 try {
			st = sServe.findItemByName(key);
		 if (st == null) {
			 information.setText("Item with name: "+ key+ " does not exist in stock.");
		 }
		 else if(st.getQuantity() < 1){
			 information.setText(key+", is finished!");
		 }
		 else {
			 information.setText("Item number: "+ st.getId() +
					 "\nItem name: "+ st.getItem() +
					 "\nItem quantity instock: "+ st.getQuantity()+
					 "\nItem quantity sold: "+ st.getSoldNo()+
					 "\nItem quantity damaged: "+ st.getDamagedNo()+
					 "\nItem date: "+ st.getDate()
					 
					 );
		 }
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

}
