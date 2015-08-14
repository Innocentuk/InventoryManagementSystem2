package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

import Model.Item;
import Model.Price;
import Service.ItemServiceImplementation;
import Service.PriceServiceImplementation;

/**
 * Class containing the user interface for registering new Product.
 * @author Eucheria Innocent
 *
 */
public class RegisterProduct extends JPanel implements ActionListener{//JPanel{

	private JButton addB;
	private JTextField itemNameTF;
	private JTextArea descriptionTA;
	private TextArea displayArea;
	//public static Boolean productCreated = new Boolean(false); 
	
	ItemServiceImplementation iServe;
	PriceServiceImplementation pServe;
	Item t = new Item();
	Price p = new Price();
	
	Date day = new Date();
	String date = new SimpleDateFormat ("yyyy-MM-dd").format(day);
	
	/**
	 * Constructor which instantiate every component of the user interface.
	 */
	public RegisterProduct() {
		
		iServe = new ItemServiceImplementation();
		pServe = new PriceServiceImplementation();
		this.setLayout(null);
		
		JLabel banner = new JLabel("Register product detail:");
		banner.setFont(new Font("Algerian", Font.PLAIN, 14));
		banner.setForeground(Color.BLUE);
		
		JLabel nameLab = new JLabel("Enter product name");
		JLabel descriptionLab = new JLabel("Enter product description");
		addB = new JButton("Add");
		addB.addActionListener(this);
		
		itemNameTF = new JTextField();
		descriptionTA = new JTextArea(5, 3);
		JScrollPane descriptionScrollPane = new JScrollPane(descriptionTA);
		
		
		JPanel display = new JPanel();
		display.setBorder(new TitledBorder("Product Entered"));
		display.add(new JSeparator(JSeparator.VERTICAL), BorderLayout.LINE_START);
		displayArea = new TextArea(80, 50);
		displayArea.setEditable(false);
		JScrollPane scrollP = new JScrollPane(displayArea);
		display.add(scrollP, BorderLayout.CENTER);
		
		banner.setBounds(200, 20, 400, 30);
		nameLab.setBounds(80, 70, 200, 30);
		descriptionLab.setBounds(80, 110, 200, 30);
		itemNameTF.setBounds(300, 70, 200, 30);
		descriptionScrollPane.setBounds(300, 110, 200, 30);
		addB.setBounds(300, 150, 100, 30);
		display.setBounds(700, 70, 500, 200);
		
		add(banner);
		add(nameLab);
		add(itemNameTF);
		add(descriptionLab);
		add(descriptionScrollPane);
		add(addB);
		add(display);
		
	}
	
	private void addDetails(){
		
		t.setName(itemNameTF.getText());
		itemNameTF.setText("");
		t.setDescription(descriptionTA.getText());
		descriptionTA.setText("");
		
			try {
				iServe.saveItem(t);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		displayAddedProduct();
	}
	
	public void displayAddedProduct(){
		displayArea.setText("Product name: "+ t.getName() +
				 "\nProduct description: "+ t.getDescription());
			}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			
		
		if(e.getSource().equals(addB)){
			
			
			if(itemNameTF.getText().equals(""))//{
				JOptionPane.showMessageDialog(null, "You must enter product name!");
			
			else {
				String key = itemNameTF.getText();
				t = iServe.findItemByName(key);
				
				if(t == null){
					addDetails();
					
				}else{
					JOptionPane.showMessageDialog(null, "Product already exist!");
				}
					
			}
			
		}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}	
}
