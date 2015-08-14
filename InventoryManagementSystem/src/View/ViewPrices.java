package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Model.Item;
import Model.Price;
import Service.PriceServiceImplementation;
import Service.StockServiceImplementation;

public class ViewPrices extends JPanel{
	private JTable priceTab;
	private JLabel banner;
	private DefaultTableModel model2;
	private JButton deleteB;
	private final String[] headers2 = {"price no","Product name", "cost price", "sell price", "Price Date"};
	//Stock st;
	PriceServiceImplementation priceserve ;
	
	Locale lc = new Locale("NGA", "NG");
	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(lc);
	
	public ViewPrices() {
		priceserve = new PriceServiceImplementation();
		JPanel content = new JPanel();
		setLayout(new BorderLayout()); 
		setVisible(true);
		
		banner = new JLabel("Products Prices!");
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
		
		model2 = new DefaultTableModel();
		model2.setColumnIdentifiers(headers2);
		
		List<Price> pricelist;
		try {
			pricelist = priceserve.findAllPrice();
			for(Price pz : pricelist){
				String[] values = {""+pz.getId(),""+pz.getItem(), ""+currencyFormat.format(pz.getCostPrice()),""+ currencyFormat.format(pz.getSellPrice()),""+ pz.getDate()};
				model2.addRow(values);
			}
			priceTab = new JTable(model2){
				public boolean isCellEditable(int row, int column){
					return false;
				}
			};
			priceTab.setRowSelectionAllowed(true);
			priceTab.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			//priceTab.setFillsViewportHeight(true);
			
			JScrollPane pane = new JScrollPane(priceTab);
			pane.setBorder(new TitledBorder(""));
			pane.setHorizontalScrollBarPolicy(
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			pane.setVerticalScrollBarPolicy(
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			content.add(pane, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		deleteB.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		        try{
		        	int i = priceTab.getSelectedRow();
		        	String n = (String) priceTab.getValueAt(i, 0);
		        	int id = Integer.parseInt(n);
		        	System.out.println(n);
		        	if(i >= 0){
		        		List<Price> Pr = priceserve.findAllPrice();
		        		for(Price p : Pr){
		        			if(p.getId() == id){
		        				priceserve.deletePrice(p);
		        			}
		        		}
		        		model2.removeRow(i);
		        		}
		        }catch(Exception ex){
		            ex.printStackTrace();
		        }
		    }
		});
	}

}
