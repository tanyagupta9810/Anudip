package com.project.BankingSystem.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.project.BankingSystem.model.*;

import java.util.Properties;

public class HibernateUtil {

	private static SessionFactory sessionfactory;

	static {
		Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"); // Example dialect, change for your DB
        properties.put("hibernate.hbm2ddl.auto", "update"); // Creates or updates the schema
        properties.put("hibernate.show_sql", "false"); // Shows SQL in the console
        properties.put("hibernate.format_sql", "true"); // Formats SQL for better readability
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/Banking_System"); // Your DB URL
        properties.put("hibernate.connection.username", "root"); // Your DB username
        properties.put("hibernate.connection.password", "212731"); // Your DB password

        // Configuration object
        Configuration configuration = new Configuration();
        configuration.setProperties(properties);

        // Add annotated classes (entities)
      
        configuration.addAnnotatedClass(Admin.class);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Account.class); // Replace with your entity class
      configuration.addAnnotatedClass(User.class);
      configuration.addAnnotatedClass(Branch.class);
        configuration.addAnnotatedClass(Transactions.class);
       configuration.addAnnotatedClass(Card.class);


        // ServiceRegistry for Hibernate
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        // Build and return the SessionFactory
        sessionfactory= configuration.buildSessionFactory(serviceRegistry);
	}

	private HibernateUtil() {

	}

	public static Session getSession() {

		
		return sessionfactory.openSession();

	}
}
