package com.practice.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.hibernate.demo.entity.Course;
import com.practice.hibernate.demo.entity.Instructor;
import com.practice.hibernate.demo.entity.InstructorDetail;
import com.practice.hibernate.demo.entity.Review;
import com.practice.hibernate.demo.entity.Student;

public class CreateCourseAndStudent {

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

			Course course = new Course("Pirates of Caribean");

			System.out.println("Saving the course.....   ");
			session.save(course);
			System.out.println("Saving the course:" + course);

			Student student1 = new Student("Sumeet", "Patil", "sumeet@gmail.com");
			Student student2 = new Student("Sagar", "Patil", "sager@gmail.com");

			course.addStudent(student1);
			course.addStudent(student2);
			System.out.println("Saving the students....");
			session.save(student1);
			session.save(student2);
			System.out.println("Saved Students:" + course.getStudents());

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {

			session.close();

			factory.close();
		}
	}

}
