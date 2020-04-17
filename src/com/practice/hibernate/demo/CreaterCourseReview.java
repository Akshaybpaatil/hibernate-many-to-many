package com.practice.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.hibernate.demo.entity.Course;
import com.practice.hibernate.demo.entity.Instructor;
import com.practice.hibernate.demo.entity.InstructorDetail;
import com.practice.hibernate.demo.entity.Review;
import com.practice.hibernate.demo.entity.Student;

public class CreaterCourseReview {

	public static void main(String[] args) {

		// create a session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(InstructorDetail.class)
				                 .addAnnotatedClass(Instructor.class)
				                 .addAnnotatedClass(Course.class)
				                 .addAnnotatedClass(Review.class)
				                 .buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//start the transaction
			session.beginTransaction();
			
			Course course = new Course("Pirates of India");
			
			course.addReview(new Review("Great Book"));
			course.addReview(new Review("Best Biography"));
			
			System.out.println("Saving the Courses");
			System.out.println(course);
			System.out.println(course.getReviews());
			
			session.save(course);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			
			session.close();
			
			factory.close();
		}
	}

}
