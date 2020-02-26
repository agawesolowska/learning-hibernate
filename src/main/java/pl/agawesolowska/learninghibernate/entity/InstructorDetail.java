package pl.agawesolowska.learninghibernate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing instructors' details table in the PostgreSQL database.
 * 
 * @author Aga
 *
 */
@Entity
@Table(name="instructor_detail")
@NoArgsConstructor
@Getter @Setter
public class InstructorDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="web_page")
	private String webPage;
	
	@Column(name="hobby")
	private String hobby;
	
	@OneToOne(mappedBy="instructorDetail", cascade=CascadeType.ALL)
	private Instructor instructor;

	public InstructorDetail(String webPage, String hobby) {
		this.webPage = webPage;
		this.hobby = hobby;
	}
	
}
