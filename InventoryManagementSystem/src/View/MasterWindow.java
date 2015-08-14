package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;


public class MasterWindow extends JFrame {
	
	 JTabbedPane tabbedPane;
	 JPanel contentPane = new JPanel();
	 JMenuBar menuBar = new JMenuBar();
	 JPanel layer2 = new JPanel();
	 
	 public MasterWindow(){
		 
		 setBounds(100, 60, 1500, 800);
		 setBackground(new Color(53, 56, 64));
		 setTitle("Inventory Management System: HENZ NIG LTD");
		 setFont(new Font("Algerian", Font.PLAIN, 16));
		 // close application only by clicking the quit button
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setVisible(true);
		// Add the menubar to the frame
	     setJMenuBar(menuBar);
		 contentPane.setBorder(new EmptyBorder (5, 5, 5, 5));
		 contentPane = new JPanel(new BorderLayout());
		 setContentPane(contentPane);
		 //setLayout(new BorderLayout())
		 
		 JMenu fileMenu = new JMenu("File");
		 JMenu editMenu = new JMenu("Update");
		 JMenu optionMenu = new JMenu("Options");
		 JMenu viewMenu = new JMenu("View");
		 menuBar.add(fileMenu);
		 menuBar.add(editMenu);
		 menuBar.add(viewMenu);
		 menuBar.add(optionMenu);
		 
		// Create and add simple menu item to one of the drop down menu
	        JMenuItem regProduct = new JMenuItem("Register New Product");
	        JMenuItem addStockAction = new JMenuItem("Add Product");
	        JMenuItem sellAction = new JMenuItem("Make New Sell");
	        JMenuItem damageAction = new JMenuItem("Record Damaged Product");
	        JMenuItem findAction = new JMenuItem("Search Product");
	        
	        JMenuItem logAction = new JMenuItem("Logout");
	        JMenuItem exitAction = new JMenuItem("Exit");
	        
	        JMenuItem restockAction = new JMenuItem("Restock");
	        JMenuItem updateSale = new JMenuItem("Update sale");
	       // JMenuItem updatePrice = new JMenuItem("Update price");
	        
	        JMenuItem viewPrice = new JMenuItem("View prices");
	        JMenuItem viewStockAction = new JMenuItem("View all Products instock");
	        JMenuItem viewSoldAction = new JMenuItem("View Sold Products");
	        JMenuItem viewDamageAction = new JMenuItem("View Damage Products");
	        JMenuItem viewProducts = new JMenuItem("View product");

	        fileMenu.add(logAction);
	        fileMenu.add(exitAction);
	        
	        editMenu.add(restockAction);
	        editMenu.add(updateSale);
	        //editMenu.add(updatePrice);
	        
	        optionMenu.add(regProduct);
	        optionMenu.add(addStockAction);
	        optionMenu.add(sellAction);
	        optionMenu.add(damageAction);
	        optionMenu.add(findAction);
	        
	        viewMenu.add(viewProducts);
	        viewMenu.add(viewStockAction);
	        viewMenu.add(viewPrice);
	        viewMenu.add(viewSoldAction);
	        viewMenu.add(viewDamageAction);
	        
	        regProduct.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					contentPane.setLayout(new BorderLayout());
					RegisterProduct  ps = new RegisterProduct();
					
					contentPane.add(ps, BorderLayout.CENTER);
					contentPane.revalidate();
						System.out.println("You clicked: " + e.getActionCommand());		
				}
			});
	        
	        addStockAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					contentPane.removeAll();
					contentPane.setLayout(new BorderLayout());
					contentPane.add(new AddProduct(), BorderLayout.CENTER);
					contentPane.revalidate();
					System.out.println("You clicked: " + ae.getActionCommand());
				}
			});
	        
	        sellAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					contentPane.setLayout(new BorderLayout());
					contentPane.add(new MakeSale(), BorderLayout.CENTER);
					contentPane.revalidate();
				}
			});
	        
	        damageAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					contentPane.removeAll();
					contentPane.setLayout(new BorderLayout());
					contentPane.add(new RecordDamage(), BorderLayout.CENTER);
					contentPane.revalidate();
				}
			});
	        
	        findAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					contentPane.removeAll();
					contentPane.setLayout(new BorderLayout());
					contentPane.add(new SearchProduct(), BorderLayout.CENTER);
					contentPane.revalidate();
				}
			});
	        
	        restockAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					contentPane.removeAll();
					contentPane.setLayout(new BorderLayout());
					contentPane.add(new Restock(), BorderLayout.CENTER);
					contentPane.revalidate();
				}
			});
	        
	        updateSale.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					contentPane.removeAll();
					contentPane.setLayout(new BorderLayout());
					contentPane.add(new updateSale(), BorderLayout.CENTER);
					contentPane.revalidate();
				}
			});
	        viewStockAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent a) {
					try {
						contentPane.removeAll();
						contentPane.setLayout(new BorderLayout());
						contentPane.add(new ViewStockList(), BorderLayout.CENTER);
						contentPane.revalidate();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
	        viewProducts.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					contentPane.removeAll();
					contentPane.setLayout(new BorderLayout());
					contentPane.add(new ViewProduct(), BorderLayout.CENTER);
					contentPane.revalidate();
				}
			});
	        
	        viewSoldAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					contentPane.setLayout(new BorderLayout());
					contentPane.add(new ViewSoldProducts(), BorderLayout.CENTER);
					contentPane.revalidate();
				}
			});
	        viewDamageAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					contentPane.setLayout(new BorderLayout());
					contentPane.add(new ViewDamagedProducts(), BorderLayout.CENTER);
					contentPane.revalidate();
				}
			});
	        
	        viewPrice.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					contentPane.setLayout(new BorderLayout());
					contentPane.add(new ViewPrices(), BorderLayout.CENTER);
					contentPane.revalidate();
				}
			});
	        
	        logAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Are you sure you want to logout!");
					contentPane.removeAll();
					System.exit(0);
					//contentPane.setLayout(new BorderLayout());
					LoginGUI log = new LoginGUI();
					//contentPane.add(new LoginGUI(), BorderLayout.CENTER);
					//contentPane.revalidate();
				}
			});
	        exitAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
					
				}
			});
		 
		 
		 }
	 
	}
