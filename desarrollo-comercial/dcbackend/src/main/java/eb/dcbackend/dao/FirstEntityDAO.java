package eb.dcbackend.dao;

import java.util.List;

import eb.dcbackend.model.FirstEntity;

public interface FirstEntityDAO {
	
	FirstEntity get(int id);
	List<FirstEntity> list();
	boolean add(FirstEntity firstEntity);
	boolean update(FirstEntity firstEntity);
	boolean delete(FirstEntity firstEntity);
	
	
}

