package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Model.Item;
import Model.Price;
import Model.Stock;
import Service.ItemServiceImplementation;
import Service.PriceServiceImplementation;
import Service.StockServiceImplementation;

public class ViewProduct extends JPanel{ //implements ActionListener{
	
	private JTable productTable;
	private JLabel banner;
	private DefaultTableModel model;
	private final String[] headers = {"Product No","Product name", "Description"};
	private JButton deleteB;
	ItemServiceImplementation itemserve;
	PriceServiceImplementation priceserve;
	
	Locale lc = new Locale("NGA", "NG");
	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(lc);
	
public ViewProduct() {
	itemserve = new ItemServiceImplementation();
	priceserve = new PriceServiceImplementation();
	
	JPanel content = new JPanel();
	setLayout(new BorderLayout()); 
	setVisible(true);
	
	banner = new JLabel("All products the company deals on!");
	banner.setFont(new Font("Algerian", Font.PLAIN, 15));
	banner.setForeground(Color.BLUE);
	
	content.setLayout(new BorderLayout());
	content.add(banner, BorderLayout.NORTH);
	this.add(content);
	
	JPanel btnP = new JPanel();
	btnP.setLayout(new FlowLayout());
	
	deleteB = new JButton("Delete");
	btnP.add(deleteB);
	add(btnP, BorderLayout.SOUTH);
	
	model = new DefaultTableModel();
	model.setColumnIdentifiers(headers);
	
	try{
		List<Item> list = itemserve.findAllItem();
		
		System.out.println(list.size()+ ", Products");
		/*for (int i = 0; i < list.size();  i++) {
			Item it = list.get(i);
			System.out.println(it.getId()+", "+it.getName()+", "+ it.getDescription());
		}*/
		for(Item itm :list){
			String[] values = {""+ itm.getId(),""+itm.getName(),""+ itm.getDescription()};
			model.addRow(values);
		}
		
		//productTable.setModel(model);
		productTable = new JTable(model){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		productTable.setRowSelectionAllowed(true);
		productTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		productTable.setFillsViewportHeight(true);
		
		JScrollPane pane = new JScrollPane(productTable);
		pane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		content.add(pane);
		
		}catch(Exception e){
		e.printStackTrace();
	}
	deleteB.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent e) {
	        try{
	        	int i = productTable.getSelectedRow();
	        	String n = (String) productTable.getValueAt(i, 0);
	        	int id = Integer.parseInt(n);
	        	System.out.println(n);
	        	if(i >= 0){
	        		List<Item> it = itemserve.findAllItem();
	        		for(Item t : it){
	        			if(t.getId() == id){
	        				itemserve.deleteItem(t);
	        			}
	        		}
	        		model.removeRow(i);
	        		}
	        }catch(Exception ex){
	            ex.printStackTrace();
	        }
	    }
	});
}

}
