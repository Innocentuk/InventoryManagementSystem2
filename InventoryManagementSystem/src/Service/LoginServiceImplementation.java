package Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import DAO.DatabaseManager;
import DAO.LoginDAO;
import Model.Login;

public class LoginServiceImplementation implements LoginService{

	DatabaseManager db = new DatabaseManager();
	private Connection con;
	ResultSet rs;
	LoginDAO ldao = new LoginDAO();
	@Override
	public boolean saveUser(Login l) throws Exception {
		try {
			ldao.save(l);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}

	@Override
	public boolean updateUser(Login l) throws Exception {
		ldao.update(l);
		return true;
	}

	@Override
	public List<Login> findAllUser() throws Exception {
		
		return ldao.findAll();
	}

	@Override
	public Login findItemByName(String name) throws Exception {
		return ldao.findByName(name);
	}

	@Override
	public boolean validate(String username, String password) throws Exception {
		String sql = "select * from login where userName =? and password =?";
		   try{           
			   con = db.getConnect();
			   PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			   Login l = new Login();
			   pst.setString(1, l.getUsername());
			   pst.setString(2, l.getPassword());
		       
		       ResultSet rs = pst.executeQuery();                        
		       if(rs.next())            
		           return true;    
		       else
		           return false;            
		   }
		   catch(Exception e){
		       e.printStackTrace();
		       return false;
		   }       
	}

}
