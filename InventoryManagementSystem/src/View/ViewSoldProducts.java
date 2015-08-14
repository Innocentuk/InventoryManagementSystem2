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
import javax.swing.table.DefaultTableModel;

import Model.DamageItem;
import Model.Sales;
import Model.Stock;
import Service.SalesServiceImplementation;
import Service.StockServiceImplementation;


public class ViewSoldProducts extends JPanel {
	
	private JTable soldTable;
	private JLabel banner;
	private DefaultTableModel model;
	private JButton deleteB;
	private final String[] headers = {"sales No","Product name", "quantity", "total price", "payment status", "staff", "Date"};
	SalesServiceImplementation saleserve;
	
	Locale lc = new Locale("NGA", "NG");
	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(lc);
	
	public ViewSoldProducts() {
		int row = 0;
		saleserve = new SalesServiceImplementation();
		
		JPanel content = new JPanel();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout()); 
		//setSize(500, 400);
		setVisible(true);
		
		banner = new JLabel("Sold out products.");
		banner.setFont(new Font("Algerian", Font.PLAIN, 15));
		banner.setForeground(Color.BLUE);
		//banner.setAlignmentX(Component.CENTER_ALIGNMENT);
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
			List<Sales> list = saleserve.findAllSale();
			System.out.println(list.size());
			/*for (int i = 0; i < list.size(); i++) {
				Sales sa = list.get(i);
				System.out.println(sa.getId()+", "+sa.getItem()+", "+ sa.getQuantity()+", "+ sa.getTotalprice() +", "+sa.getStatus()+", "+ sa.getStaffStamp()+", "+ sa.getDate());
			}*/
			for(Sales sl :list){
				String[] values = {""+sl.getId(),""+sl.getItem(),"" +sl.getQuantity(), ""+sl.getTotalprice(), ""+sl.getStatus(), ""+sl.getStaffStamp(), sl.getDate() };
				model.addRow(values);
				
			}
			//soldTable.setModel(model);
			soldTable = new JTable(model){
				public boolean isCellEditable(int row, int column){
					return false;
				}
			};
			soldTable.setRowSelectionAllowed(true);
			soldTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			soldTable.setFillsViewportHeight(true);
			
			JScrollPane pane = new JScrollPane(soldTable);
			pane.setHorizontalScrollBarPolicy(
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			pane.setVerticalScrollBarPolicy(
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			content.add(pane, BorderLayout.CENTER);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		deleteB.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		        try{
		        	int i = soldTable.getSelectedRow();
		        	String n = (String) soldTable.getValueAt(i, 0);
		        	int id = Integer.parseInt(n);
		        	System.out.println(n);
		        	if(i >= 0){
		        		List<Sales> sa = saleserve.findAllSale();
		        		for(Sales s : sa){
		        			if(s.getId() == id){
		        				saleserve.deleteSale(s);
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
