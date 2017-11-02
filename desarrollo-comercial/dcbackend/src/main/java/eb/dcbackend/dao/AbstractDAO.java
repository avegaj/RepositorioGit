package eb.dcbackend.dao;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDAO<PK extends Serializable, T> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDAO(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	
	protected Session getSession(){	
		return sessionFactory.getCurrentSession();
	}

	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	protected CriteriaQuery<T> createEntityCriteria(String column, String action,String a, Integer b){
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(persistentClass);
		Root<T> critRoot = criteria.from(persistentClass);
		
		switch (action) {
	    	case "where for String": 
	    		System.out.println("Find for String...");
	    		criteria.select(critRoot).where(builder.equal(critRoot.get(column), a));
	    		return criteria;
	    	case "where for int": 
	    		System.out.println("Find for int...");
	    		criteria.select(critRoot).where(builder.equal(critRoot.get(column), b));
	    		return criteria;
	    	case "list":
	    		System.out.println("List...");
	    		criteria.select(critRoot);
	    		return criteria;
	    	default:
	    		System.out.println("No found...");
	    		return null;
		}
	}
}
