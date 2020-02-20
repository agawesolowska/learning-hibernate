package pl.agawesolowska.learninghibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.agawesolowska.learninghibernate.entity.Course;
import pl.agawesolowska.learninghibernate.entity.Instructor;
import pl.agawesolowska.learninghibernate.entity.InstructorDetail;
import pl.agawesolowska.learninghibernate.entity.Review;
import pl.agawesolowska.learninghibernate.entity.Student;

public class HibernateCrudApp {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			// create object
			Student student = new Student("Sven", "Aylwin", "saylwin2@yahoo.com");
			
			Instructor instructor = new Instructor("Phillipp", "Gillebert", "pgillebert5@privacy.gov.au");
			InstructorDetail instructorDetail = new InstructorDetail("www.gillebert.com", "programming");
			instructor.setInstructorDetail(instructorDetail);
			
			Course course1 = new Course("Learning Spring");
			Course course2 = new Course("Spring MVC");
			Course course3 = new Course("Basics of Hibernate");
			
			course1.addReview(new Review("Awesome."));
			course2.addReview(new Review("A huge dose of knowledge."));
			course2.addReview(new Review("Excellent."));
			course3.addReview(new Review("A bit difficult."));
			
			instructor.addCourse(course1);
			instructor.addCourse(course2);
			instructor.addCourse(course3);
			
			course1.addStudent(student);
			
			session.beginTransaction();
			session.save(student);
			session.save(instructor);
			session.save(course1);
			session.save(course2);
			session.save(course3);
			session.getTransaction().commit();
			
			// read object
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Course> listOfCourses = session.createQuery("from Course").getResultList();
			for(Course course : listOfCourses) {
				System.out.println(course);				
			}
			
			// update object
			long studentId = 2;
			Student studentToUpdate = session.get(Student.class, studentId);
			studentToUpdate.setEmail("saylwin@yahoo.com");
			
			// delete object
			session.createQuery("delete from Review where id=5").executeUpdate();
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
		
	}

}
