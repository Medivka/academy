package com.example.data_baseapp.hibernate;

/**
 * @author Sacuta V.A.
 */


import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
    @Getter
    private EntityManager entityManager;

    public EntityManagerUtil() {
        init();
    }

    private void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence");
        this.entityManager = factory.createEntityManager();
    }

    public void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    public void commit() {
        entityManager.getTransaction().commit();
    }

    public void rollBack() {
        entityManager.getTransaction().rollback();
    }


}
