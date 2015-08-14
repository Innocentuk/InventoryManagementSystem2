package Service;

import java.util.List;

import Model.Stock;


public interface StockService {

	boolean saveStock(Stock s) throws Exception;
	boolean updateStock(Stock s)  throws Exception;
	public List<Stock >  findAllStock() throws Exception;
	Stock findItemByName(String name) throws Exception;
	boolean deleteStock(Stock s) throws Exception;
 }
