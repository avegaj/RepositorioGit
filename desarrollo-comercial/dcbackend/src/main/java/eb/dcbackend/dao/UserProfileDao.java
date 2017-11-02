package eb.dcbackend.dao;

import java.util.List;

import eb.dcbackend.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
