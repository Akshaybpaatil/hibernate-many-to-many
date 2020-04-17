package com.practice.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.hibernate.demo.entity.Course;
import com.practice.hibernate.demo.entity.Instructor;
import com.practice.hibernate.demo.entity.InstructorDetail;
import com.practice.hibernate.demo.entity.Review;
import com.practice.hibernate.demo.entity.Student;

public class DeleteCourseForStudent {

	public static void main(String[] args) {

		// create a session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start the transaction
			session.beginTransaction();

			int id = 10;

			Course course = session.get(Course.class, id);

			System.out.println("Cporse:" + course);

			session.delete(course);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {

			session.close();

			factory.close();
		}
	}

}
