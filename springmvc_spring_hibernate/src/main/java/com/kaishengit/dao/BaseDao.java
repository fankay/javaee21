package com.kaishengit.dao;

import com.kaishengit.util.Page;
import com.kaishengit.util.SearchParam;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;

import javax.inject.Inject;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDao<T,PK extends Serializable> {

    @Inject
    private SessionFactory sessionFactory;
    private Class<?> entityClass;

    public BaseDao() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        entityClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
    }



    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(T entity) {
        getSession().saveOrUpdate(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    public void delete(PK id) {
        delete(findById(id));
    }

    public T findById(PK id) {
        return (T)getSession().get(entityClass,id);
    }

    public List<T> findAll() {
        Criteria criteria = getSession().createCriteria(entityClass);
        return criteria.list();
    }

    public Long count() {
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    public Long count(Criteria criteria) {
        ResultTransformer resultTransformer = criteria.ROOT_ENTITY;

        criteria.setProjection(Projections.rowCount());
        Long count = (Long) criteria.uniqueResult();

        criteria.setProjection(null);
        criteria.setResultTransformer(resultTransformer);

        return count;
    }

    public Page<T> findByPageNo(Integer pageNo,Integer pageSize) {
        Integer totalSize = count().intValue();
        Page<T> page = new Page<>(pageNo,pageSize,totalSize);

        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.setFirstResult(page.getStart());
        criteria.setMaxResults(page.getPageSize());

        List<T> result = criteria.list();
        page.setItems(result);
        return page;
    }

    public Page<T> findByPageNo(Integer pageNo, Integer pageSize, List<SearchParam> searchParamList) {
        Criteria criteria = buildCriteriaBySearchParam(searchParamList);

        Integer totalSize = count(criteria).intValue();

        Page<T> page = new Page<>(pageNo,pageSize,totalSize);
        criteria.setFirstResult(page.getStart());
        criteria.setMaxResults(page.getPageSize());

        List<T> result = criteria.list();
        page.setItems(result);


        return page;
    }


    private Criteria buildCriteriaBySearchParam(List<SearchParam> searchParamList) {
        Criteria criteria = getSession().createCriteria(entityClass);

        for(SearchParam searchParam : searchParamList) {
            String propertyName = searchParam.getProtertyName();
            Object value = searchParam.getValue();
            String type = searchParam.getType();

            if("eq".equalsIgnoreCase(type)) {
                criteria.add(Restrictions.eq(propertyName,value));
            } else if("like".equalsIgnoreCase(type)) {
                criteria.add(Restrictions.like(propertyName,value.toString(), MatchMode.ANYWHERE));
            } else if("ge".equalsIgnoreCase(type)) {
                criteria.add(Restrictions.ge(propertyName,value));
            } else if("gt".equalsIgnoreCase(type)) {
                criteria.add(Restrictions.gt(propertyName,value));
            } else if("le".equalsIgnoreCase(type)) {
                criteria.add(Restrictions.le(propertyName,value));
            } else if("lt".equalsIgnoreCase(type)) {
                criteria.add(Restrictions.lt(propertyName,value));
            }
        }

        return criteria;

    }

}
