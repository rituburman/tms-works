package com.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.bean.EmployeeBean;


public class HibernateUtil {
	static SessionFactory sessionFactory = null;
	private HibernateUtil() {}
	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		
		configuration.addAnnotatedClass(EmployeeBean.class);
		//configuration.addAnnotatedClass(Trainer.class);
		//configuration.addAnnotatedClass(TOC.class)
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
				applySettings(configuration.getProperties()).build();
		
		 return configuration.buildSessionFactory(serviceRegistry);
	       
	        }
}
