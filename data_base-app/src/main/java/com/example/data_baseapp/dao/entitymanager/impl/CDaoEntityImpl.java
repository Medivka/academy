package com.example.data_baseapp.dao.entitymanager.impl;

import com.example.data_baseapp.dao.entitymanager.CdaoEntity;
import com.example.data_baseapp.domain.model.C;
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
public class CDaoEntityImpl implements CdaoEntity {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(C c) {
//        entityManager.persist(c);                     //Entity Manager
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(c);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(C c) {
//        entityManager.merge(b);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(c);
        tx1.commit();
        session.close();
    }

    @Override
    public C findById(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        C c = session.get(C.class, id);
        session.close();
        return c;
//        return entityManager.find(C.class, id);
    }

    @Override
    public void delete(C c) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(c);
        tx1.commit();
        session.close();
//        entityManager.remove(c);
    }

    @Override
    public List<C> getGuestList() {
        List<C> cList = (List<C>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From C").list();
        return cList;
    }
}
