package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface GenericDao<T>//, id extends Serializable> 
{
	public boolean save(T t) throws Exception;
	public boolean update(T t) throws Exception;
	public void reLoadData(T t) throws Exception;
	public boolean delete(T t) throws Exception;
	public T findByName(String name)throws Exception;
	/*public int count()throws Exception;*/
	//public boolean validate_login(String username,String password);
	
	public List<T> findAll() throws Exception;
    

}
