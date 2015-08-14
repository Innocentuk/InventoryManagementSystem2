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
import Model.Price;
import Model.Stock;
import Service.DamageServiceImplementation;
import Service.StockServiceImplementation;

public class ViewDamagedProducts extends JPanel{
	
	private JTable damageTable;
	private JLabel banner;
	private DefaultTableModel model;
	private JButton deleteB;
	private final String[] headers = {"damage number","Product name", "quantity", "Reason", "Staff", "Date entered"};
	//Stock st;
	DamageServiceImplementation damageserve;
	StockServiceImplementation sServe;
	
	Locale lc = new Locale("NGA", "NG");
	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(lc);
	
	public ViewDamagedProducts(){
		sServe = new StockServiceImplementation();
		damageserve = new DamageServiceImplementation();
		
		JPanel content = new JPanel();
		setLayout(new BorderLayout()); 
		setVisible(true);
		
		banner = new JLabel("All damaged products.");
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
			List<DamageItem> list = damageserve.findAlldamaged();
			System.out.println(list.size());
			/*for (int i = 0; i < list.size(); i++) {
				DamageItem di = list.get(i);
				System.out.println(di.getItem()+": "+ di.getQty()+", "+ di.getReason()+", "+ di.getDate()+", "+di.getStaffstamp());
			}*/
			for(DamageItem dam :list){
				String[] values = {""+dam.getId(),""+dam.getItem(),""+dam.getQty(), ""+dam.getReason(), ""+dam.getStaffstamp(), dam.getDate() };
				model.addRow(values);
				
			}
			damageTable = new JTable(model){
				public boolean isCellEditable(int row, int column){
					return false;
				}
			};
			damageTable.setRowSelectionAllowed(true);
			damageTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			damageTable.setFillsViewportHeight(true);
			
			JScrollPane pane = new JScrollPane(damageTable);
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
		        	int i = damageTable.getSelectedRow();
		        	String n = (String) damageTable.getValueAt(i, 0);
		        	int id = Integer.parseInt(n);
		        	System.out.println(n);
		        	if(i >= 0){
		        		List<DamageItem> di = damageserve.findAlldamaged();
		        		for(DamageItem d : di){
		        			if(d.getId() == id){
		        				damageserve.deleteDamage(d);
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
