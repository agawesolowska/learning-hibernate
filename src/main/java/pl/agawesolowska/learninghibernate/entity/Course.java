package pl.agawesolowska.learninghibernate.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing course table in the PostgreSQL database.
 * 
 * @author Aga
 *
 */
@Entity
@Table(name="course")
@NoArgsConstructor
@Getter @Setter
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="course_id")
	private long courseId;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne
	@JoinColumn(name="instructor_id")
	private Instructor instructor;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="course_id")
	private List<Review> reviews;
	
	@ManyToMany
	@JoinTable(name="course_student", joinColumns=@JoinColumn(name="course_id"), inverseJoinColumns=@JoinColumn(name="student_id"))
	private List<Student> students;
	
	public Course(String title) {
		this.title = title;
	}
	
	public void addReview(Review reviewToAdd) {
		if(reviews == null) {
			reviews = new ArrayList<>();
		}
		reviews.add(reviewToAdd);
	}
	
	public void addStudent(Student studentToAdd) {
		if(students == null) {
			students = new ArrayList<>();
		}
		students.add(studentToAdd);
	}

	@Override
	public String toString() {
		return "Course_id: " + courseId + ", title: " + title + ", instructor: " + instructor;
	}
	
}
