package npscommunity.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "answer")
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Id auto increment
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private AppUser user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Question question;

	@Column()
	private String content;

	@Column()
	private long vote_up;

	@Column()
	private long vote_down;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public Long getScore() {
		return this.vote_up - this.vote_down;
	}

	public void voteUp() {
		this.vote_up += 1;
	}

	public void voteDown() {
		this.vote_down += 1;
	}
}
