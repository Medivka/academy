package com.example.data_baseapp.dao.entitymanager.impl;

import com.example.data_baseapp.dao.entitymanager.ADaoEntity;
import com.example.data_baseapp.domain.model.A;
import com.example.data_baseapp.hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Sacuta V.A.
 */

@Repository
public class ADaoEntityImpl implements ADaoEntity {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(A a) {
//        entityManager.persist(a);                     //Entity Manager
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(a);
        tx1.commit();
        session.close();

    }

    @Override
    public void update(A a) {
//        entityManager.merge(a);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(a);
        tx1.commit();
        session.close();
    }

    @Override
    public A findById(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        A a = session.get(A.class, id);
        session.close();
        return a;
//        return entityManager.find(A.class, id);
    }

    @Override
    public void delete(A a) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(a);
        tx1.commit();
        session.close();
//        entityManager.remove(a);
    }

    @Override
    public List<A> getGuestList() {
        List<A> aList = (List<A>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From A").list();
        return aList;
    }
}
