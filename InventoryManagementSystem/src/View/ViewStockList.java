package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Model.DamageItem;
import Model.Price;
import Model.Stock;
import Service.PriceServiceImplementation;
import Service.StockServiceImplementation;

public class ViewStockList extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JTable stockTable;
	private JLabel banner;
	private DefaultTableModel model;
	private JButton deleteB;
	private final String[] headers = {"id", "Product name", "quantity", "amount sold", "amount damaged", "Date entered"};
	//Stock st;
	StockServiceImplementation stockserve;
	Stock st ;
	
	Locale lc = new Locale("NGA", "NG");
	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(lc);
	
	public ViewStockList() throws Exception {
		st = new Stock();
		stockserve = new StockServiceImplementation();
		
		JPanel content = new JPanel();
		setLayout(new BorderLayout()); 
		setVisible(true);
		
		banner = new JLabel("All products in stock.");
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
			List<Stock> list = stockserve.findAllStock();
			//System.out.println(list.size());
			/*for (int i = 0; i < list.size(); i++) {
				Stock st = list.get(i);
				System.out.println(st.getId()+", "+st.getItem()+", "+ st.getQuantity()+", "+ st.getSoldNo()+", "+ st.getDamagedNo()+", "+ st.getDate());
			}*/
			for(Stock stk :list){
				String[] values = {""+ stk.getId(),""+stk.getItem(),""+stk.getQuantity(), ""+stk.getSoldNo(), ""+stk.getDamagedNo(), stk.getDate() };
				model.addRow(values);
				
			}
			stockTable = new JTable(model){
				public boolean isCellEditable(int row, int column){
					return false;
				}
			};
			stockTable.setRowSelectionAllowed(true);
			stockTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			//stockTable.setFillsViewportHeight(true);
			
			JScrollPane pane = new JScrollPane(stockTable);
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
		        	
		        	int i = stockTable.getSelectedRow();
		        	String n = (String) stockTable.getValueAt(i, 0);
		        	int id = Integer.parseInt(n);
		        	System.out.println(n);
		        	if(i >= 0){
		        		List<Stock> st = stockserve.findAllStock();
		        		for(Stock s : st){
		        			if(s.getId() == id){
		        				stockserve.deleteStock(s);
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
