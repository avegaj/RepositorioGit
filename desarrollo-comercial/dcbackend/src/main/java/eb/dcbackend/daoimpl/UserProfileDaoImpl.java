package eb.dcbackend.daoimpl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;


import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import eb.dcbackend.dao.AbstractDAO;
import eb.dcbackend.dao.UserProfileDao;
import eb.dcbackend.model.UserProfile;



@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDAO<Integer, UserProfile>implements UserProfileDao{

	public UserProfile findById(int id) {
		return getByKey(id);
	}

	public UserProfile findByType(String type) {
		CriteriaQuery<UserProfile> crit = createEntityCriteria("type", "where for String", type, null);
		Query<UserProfile> query = getSession().createQuery(crit);
		return (UserProfile)query.getSingleResult();
	}
	
	public List<UserProfile> findAll(){
		CriteriaQuery<UserProfile> criteria = createEntityCriteria(null, "list", null, null);
		List<UserProfile> usersprofile = getSession().createQuery(criteria).getResultList();
		return usersprofile;
	}
	
}
