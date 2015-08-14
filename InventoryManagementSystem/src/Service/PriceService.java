package Service;

import java.util.List;

import Model.Price;

public interface PriceService {

	boolean savePrice(Price P) throws Exception;
	boolean updatePrice(Price s)  throws Exception;
	boolean deletePrice(Price s)  throws Exception;
	Price findPriceByItem(String item) throws Exception;
	List<Price>  findAllPrice() throws Exception;
}
