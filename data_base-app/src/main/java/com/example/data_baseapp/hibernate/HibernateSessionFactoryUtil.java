package com.example.data_baseapp.hibernate;

/**
 * @author Sacuta V.A.
 */


import com.example.data_baseapp.domain.model.*;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    @SneakyThrows
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure("Hibernate.cfg.xml");
            configuration.addAnnotatedClass(A.class);
            configuration.addAnnotatedClass(B.class);
            configuration.addAnnotatedClass(C.class);
            configuration.addAnnotatedClass(D.class);
            configuration.addAnnotatedClass(Single.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;
    }
}