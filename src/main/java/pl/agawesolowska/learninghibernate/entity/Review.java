package pl.agawesolowska.learninghibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing review table in the PostgreSQL database.
 * 
 * @author Aga
 *
 */
@Entity
@Table(name="review")
@NoArgsConstructor
@Getter @Setter
public class Review {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="review_id")
	private long reviewId;
	
	@Column(name="comment")
	private String comment;

	public Review(String comment) {
		this.comment = comment;
	}
	
}
