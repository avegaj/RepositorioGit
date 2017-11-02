package eb.dcbackend.dao;

import java.util.List;

import eb.dcbackend.model.SecondEntity;

public interface SecondEntityDAO {
	
	SecondEntity findById(int id);
	SecondEntity findByName(String name);
	void save(SecondEntity secondEntity);
	void deleteById(int i);
	List<SecondEntity> list();

}
