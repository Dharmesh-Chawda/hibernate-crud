package com.hibtest.util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Utils
{
	static SessionFactory factory;
	static
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		factory = configuration.buildSessionFactory();
	}

	public static Session getSession()
	{
		Session session = factory.openSession();
		return session;
	}
}
