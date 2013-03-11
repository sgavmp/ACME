package DAO;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.openxma.dsl.platform.dao.FinderExecutor;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDaoHibernateImpl<T extends Serializable, E extends Serializable> extends HibernateDaoSupport implements BaseDao<T, E>, FinderExecutor<Object> {
	private Class<T> type;

	 public BaseDaoHibernateImpl(Class<T> type) {
		         this.type = type;
		     }

	
	public void deleteAll( Collection<T> instances) throws Exception {
		try {
			getHibernateTemplate().deleteAll(instances);
		} catch (final Exception e) {
			throw e;
		}
	}

	public int bulkUpdate(String query) throws Exception {
		try {
			return getHibernateTemplate().bulkUpdate(query);
		} catch (final Exception e) {
			throw e;
		}
	}

	public E save( T instance) throws Exception {
		try {
			return (E) getHibernateTemplate().save(instance);
		} catch (final Exception e) {
			throw e;
		}
	}

	public void saveOrUpdateAll( Collection<T> instances) throws Exception {
		try {
			getHibernateTemplate().saveOrUpdateAll(instances);
		} catch (final Exception e) {
			throw e;
		}
	}

	public void saveOrUpdate( T instance) throws Exception {
		try {
			getHibernateTemplate().saveOrUpdate(instance);
		} catch (final Exception e) {
			throw e;
		}
	}

	public void persist( T transientInstance) throws Exception {
		try {
			getHibernateTemplate().persist(transientInstance);
		} catch (final Exception e) {
			throw e;
		}
	}

	public void attachDirty( T instance) throws Exception {
		try {
			getHibernateTemplate().saveOrUpdate(instance);
		} catch (final Exception e) {
			throw e;
		}
	}

	public void attachClean( T instance) throws Exception {
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
		} catch (final Exception e) {
			throw e;
		}
	}

	public void delete( T persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
		} catch (final Exception e) {
			throw e;
		}
	}

	public T merge( T detachedInstance) throws Exception {
		try {
			T result = getHibernateTemplate().merge(detachedInstance);
			return result;
		} catch (final Exception e) {
			throw e;
		}
	}

	public List<T> findByExample( T instance) throws Exception {
		try {
			List<T> results = getHibernateTemplate().findByExample(instance);
			return results;
		} catch (final Exception e) {
			throw e;
		}
	}

	public List<T> findByQuery( String queryString) throws Exception {
		try {
			List<T> results = getHibernateTemplate().find(queryString);
			return results;
		} catch (final Exception e) {
			throw e;
		}
	}

	public List<Map<String, Object>> findMapByQuery( String queryString)
			throws Exception {
		try {
			List<Map<String, Object>> results = getHibernateTemplate().find(queryString);
			return results;
		} catch (final Exception e) {
			throw e;
		}
	}

	public List<T> findByCriteria(DetachedCriteria criteria)
			throws Exception {
		try {
			return getHibernateTemplate().findByCriteria(criteria);
		} catch (final Exception e) {
			throw e;
		}
	}

	public List<T> findAll() throws Exception{
		return getHibernateTemplate().loadAll(type);
	}

	public T findById(E id) throws Exception {
		return (T) getHibernateTemplate().load(type, id);
	}

	public List executeFinder(Method method, final Object[] queryArgs) {
         String queryName = queryNameFromMethod(method);
         Query namedQuery = getSession().getNamedQuery(queryName);
        String[] namedParameters = namedQuery.getNamedParameters();
        for(int i = 0; i < queryArgs.length; i++) {
                Object arg = queryArgs[i];
                namedQuery.setParameter(i, arg);
         }
         return (List) namedQuery.list();
    }

    public String queryNameFromMethod(Method finderMethod) {
        return type.getSimpleName() + "." + finderMethod.getName();
    }


	@Override
	public Iterator iterateFinder(Method arg0, Object[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}
