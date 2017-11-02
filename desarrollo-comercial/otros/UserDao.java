package eb.dc.dcbackend.dao;

import java.util.List;

import eb.dc.dcbackend.model.User;

public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);
	
	void save(User user);
	
	void deleteBySSO(String sso);
	
	List<User> findAllUsers();

}

