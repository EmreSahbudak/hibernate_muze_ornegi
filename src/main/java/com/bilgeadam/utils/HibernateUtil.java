package com.bilgeadam.utils;

import com.bilgeadam.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        try{
            if (sessionFactory==null){
                Configuration cfg=new Configuration();
                cfg.addAnnotatedClass(Eser.class);
                cfg.addAnnotatedClass(Muze.class);
                cfg.addAnnotatedClass(Sanatci.class);
                cfg.addAnnotatedClass(Sergi.class);
                cfg.addAnnotatedClass(Ziyaretci.class);
                sessionFactory=cfg.configure("hibernate.cfg.xml").buildSessionFactory();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return sessionFactory;
    }
}
