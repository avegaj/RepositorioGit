package eb.dcbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eb.dcbackend.dao.FirstEntityDAO;
import eb.dcbackend.model.FirstEntity;

@Repository("firstEntityDAO")
@Transactional
public class FirstEntityDAOImpl implements FirstEntityDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FirstEntity> list() {
			
			String sql = "from FirstEntity where name = 'Eloy'";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			return query.getResultList();
	}
	
	@Override
	public FirstEntity get(int id) {
		return (FirstEntity) sessionFactory.getCurrentSession().get(FirstEntity.class, Integer.valueOf(id));
	}

	@Override
	@Transactional
	public boolean add(FirstEntity firstEntity) {
		try {
			sessionFactory.getCurrentSession().persist(firstEntity);
			return true;
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(FirstEntity firstEntity) {
		try {
			sessionFactory.getCurrentSession().update(firstEntity);
			return true;
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(FirstEntity firstEntity) {		
		try {
			sessionFactory.getCurrentSession().delete(firstEntity);
			return true;
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
}
