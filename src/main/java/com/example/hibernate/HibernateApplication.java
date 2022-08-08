package com.example.hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
		/*User user1 = new User("ime i prezime 1");
		User user2 = new User("ime i prezime 2");
		User user3 = new User("ime i prezime 3");


		Configuration configuration = new Configuration().configure() ;
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.save(user1);
		session.save(user2);
		session.save(user3);

		 */


	}

}
