package eb.dcbackend.daoimpl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import eb.dcbackend.dao.AbstractDAO;
import eb.dcbackend.dao.UserDao;
import eb.dcbackend.model.User;



@Repository("userDao")
public class UserDaoImpl extends AbstractDAO<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	public User findById(int id) {
		User user = getByKey(id);
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findBySSO(String sso) {
		logger.info("SSO : {}", sso);
		CriteriaQuery<User> crit = createEntityCriteria("ssoId", "where for String", sso, null);
		Query<User> query = getSession().createQuery(crit);
		User user = (User)query.getSingleResult();
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public List<User> findAllUsers() {
		CriteriaQuery<User> criteria = createEntityCriteria(null, "list", null, null);
		criteria.distinct(true);
		List<User> users = getSession().createQuery(criteria).getResultList();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return users;
	}

	public void save(User user) {
		persist(user);
	}

	public void deleteBySSO(String sso) {
		CriteriaQuery<User> crit = createEntityCriteria("ssoId", "where for String", sso, null);
		Query<User> query = getSession().createQuery(crit);
		User user = (User)query.getSingleResult();
		delete(user);
	}

}
