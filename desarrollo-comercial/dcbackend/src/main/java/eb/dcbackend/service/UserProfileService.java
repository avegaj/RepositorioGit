package eb.dcbackend.service;

import java.util.List;

import eb.dcbackend.model.UserProfile;


public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
