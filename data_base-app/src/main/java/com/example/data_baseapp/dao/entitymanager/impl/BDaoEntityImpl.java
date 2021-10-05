package com.example.data_baseapp.dao.entitymanager.impl;

import com.example.data_baseapp.dao.entitymanager.BDaoEntity;
import com.example.data_baseapp.domain.model.A;
import com.example.data_baseapp.domain.model.B;
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
public class BDaoEntityImpl implements BDaoEntity {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(B b) {
//        entityManager.persist(b);                     //Entity Manager
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(b);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(B b) {
//        entityManager.merge(b);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(b);
        tx1.commit();
        session.close();

    }

    @Override
    public B findById(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        B b = session.get(B.class, id);
        session.close();
        return b;
//        return entityManager.find(B.class, id);

    }

    @Override
    public void delete(B b) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(b);
        tx1.commit();
        session.close();
//        entityManager.remove(b);
    }

    @Override
    public List<B> getGuestList() {
        List<B> bList = (List<B>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From B").list();

        return bList;
    }

}
