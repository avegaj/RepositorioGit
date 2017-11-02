package eb.dcbackend.daoimpl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eb.dcbackend.dao.AbstractDAO;
import eb.dcbackend.dao.SecondEntityDAO;
import eb.dcbackend.model.SecondEntity;

@Transactional
@Repository("secondEntityDAO")
public class SecondEntityDAOImpl extends AbstractDAO<Integer, SecondEntity> implements SecondEntityDAO {

	static final Logger logger = LoggerFactory.getLogger(SecondEntityDAOImpl.class);
	
	@Override
	public SecondEntity findById(int id) {
		SecondEntity secondEntity = getByKey(id);
		if(secondEntity!=null) {
			Hibernate.initialize(secondEntity.getId());
		}
		return secondEntity;
	}

	@Override
	public SecondEntity findByName(String name) {	
		CriteriaQuery<SecondEntity> criteria = createEntityCriteria("name", "where for string", name, null);
		Query<SecondEntity> query = getSession().createQuery(criteria);
		SecondEntity secondEntity = (SecondEntity)query.getSingleResult();
		if(secondEntity!=null){
			Hibernate.initialize(secondEntity.getName());
		}
		return secondEntity;
	}

	@Override
	public void deleteById(int id) {
		CriteriaQuery<SecondEntity> criteria = createEntityCriteria("id", "where for int", null, id);
		Query<SecondEntity> query = getSession().createQuery(criteria);
		SecondEntity secondEntity = (SecondEntity)query.getSingleResult();
		delete(secondEntity);
	}

	@Override
	public List<SecondEntity> list() {
		CriteriaQuery<SecondEntity> criteria = createEntityCriteria(null, "list", null, null);
		List<SecondEntity> list = getSession().createQuery(criteria).getResultList();
		return list;
	}
	
	@Override
	public void save(SecondEntity secondEntity) {
		try 
		{
			persist(secondEntity);
		}catch(Exception ex) 
		{
			logger.info("No se puede ejecutar");
		}
	}

}
