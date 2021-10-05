package com.example.data_baseapp.dao.entitymanager.impl;

import com.example.data_baseapp.dao.entitymanager.DDaoEntity;
import com.example.data_baseapp.domain.model.C;
import com.example.data_baseapp.domain.model.D;
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
public class DDaoEntityImpl implements DDaoEntity {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(D d) {
//        entityManager.persist(d);                     //Entity Manager
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(d);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(D d) {
//        entityManager.merge(d);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(d);
        tx1.commit();
        session.close();

    }

    @Override
    public D findById(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        D d  = session.get(D.class, id);
        session.close();
        return d;
//        return entityManager.find(D.class, id);

    }

    @Override
    public void delete(D d) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(d);
        tx1.commit();
        session.close();
//        entityManager.remove(d);
    }

    @Override
    public List<D> getGuestList() {
        List<D> dList = (List<D>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From D").list();

        return dList;
    }
}
