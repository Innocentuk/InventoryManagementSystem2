package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Service.LoginServiceImplementation;
import View.LoginGUI;

public class SaleModel 
{
	private String staffname;
	private String  itemname;
	private double price;
	private int qty;
	private String status;
	private String date;
	private long invoceId;

	LoginServiceImplementation lServe;

	public SaleModel() {
		Date dat = new Date();
		DateFormat idFormat = new SimpleDateFormat("yyMMddHHmm");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:MM");
		setInvoceId(Long.parseLong(idFormat.format(dat)));
		setDate(dateFormat.format(dat));
		//setStaffname();
	}
	
	public String getStaffname() {
		return staffname;
	}
	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getInvoceId() {
		return invoceId;
	}
	public void setInvoceId(long invoceId) {
		this.invoceId = invoceId;
	}

}
