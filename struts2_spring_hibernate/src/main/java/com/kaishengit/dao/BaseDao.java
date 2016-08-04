package com.kaishengit.dao;

import com.kaishengit.util.Page;
import com.kaishengit.util.SearchParam;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
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

    public Page<T> findByPageNo(Criteria criteria,Integer pageNo, Integer pageSize, List<SearchParam> searchParamList) {
        criteria = buildCriteriaBySearchParam(criteria,searchParamList);

        Integer totalSize = count(criteria).intValue();

        Page<T> page = new Page<>(pageNo,pageSize,totalSize);
        criteria.setFirstResult(page.getStart());
        criteria.setMaxResults(page.getPageSize());

        List<T> result = criteria.list();
        page.setItems(result);


        return page;
    }

    private Criteria buildCriteriaBySearchParam(Criteria criteria,List<SearchParam> searchParamList) {
        for(SearchParam searchParam : searchParamList) {
            String propertyName = searchParam.getProtertyName();
            Object value = searchParam.getValue();
            String type = searchParam.getType();

            if(propertyName.contains("_or_")) {
                String[] nameArray = propertyName.split("_or_"); //name author

                Disjunction disjunction = Restrictions.disjunction();
                for(String name : nameArray) {
                    Criterion c = buildCondition(name,value,type);
                    disjunction.add(c);
                }

                criteria.add(disjunction);
            } else {
                Criterion criterion = buildCondition(propertyName, value, type);
                criteria.add(criterion);
            }
        }

        return criteria;
    }

    private Criteria buildCriteriaBySearchParam(List<SearchParam> searchParamList) {
        Criteria criteria = getSession().createCriteria(entityClass);
        return buildCriteriaBySearchParam(criteria,searchParamList);
    }

    private Criterion buildCondition(String propertyName, Object value, String type) {
        if("eq".equalsIgnoreCase(type)) {
            return Restrictions.eq(propertyName,value);
        } else if("like".equalsIgnoreCase(type)) {
            return Restrictions.like(propertyName,value.toString(), MatchMode.ANYWHERE);
        } else if("ge".equalsIgnoreCase(type)) {
            return Restrictions.ge(propertyName,value);
        } else if("gt".equalsIgnoreCase(type)) {
            return Restrictions.gt(propertyName,value);
        } else if("le".equalsIgnoreCase(type)) {
            return Restrictions.le(propertyName,value);
        } else if("lt".equalsIgnoreCase(type)) {
            return Restrictions.lt(propertyName,value);
        }
        return  null;
    }

}
