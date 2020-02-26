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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing the instructor table in the PostgreSQL database.
 * 
 * @author Aga
 *
 */
@Entity
@Table(name="instructor")
@NoArgsConstructor
@Getter @Setter
public class Instructor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="instructor_id")
	private long instructorId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="instructor_detail_id")
	private InstructorDetail instructorDetail;
	
	@OneToMany(mappedBy="instructor")
	private List<Course> courses;
	
	public Instructor(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public void addCourse(Course courseToAdd) {
		if(courses == null) {
			courses = new ArrayList<>();
		}
		courses.add(courseToAdd);
		courseToAdd.setInstructor(this);
	}

	@Override
	public String toString() {
		return "Instructor_id: " + instructorId + ", firstName: " + firstName + ", lastName: " + lastName
				+ ", email: " + email;
	}

}
