package npscommunity.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Entity
@Data
@Table(name = "question")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Id auto increment
	private Long id;

	@Column
	private String title;

	@Column
	private String content;

	@Column
	private Long viewed;

	@Column
	private Long vote_up;

	@Column
	private Long vote_down;

	@Column(name = "created_at")
	@CreationTimestamp
	private Timestamp createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private Timestamp updatedAt;

	@ManyToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	List<Category> categories;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
	@JsonIgnore
	List<Answer> answers;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private AppUser user;

	public Long getScore() {
		return this.vote_up - this.vote_down;
	}

	public void voteUp() {
		this.vote_up += 1;
	}

	public void voteDown() {
		this.vote_down += 1;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + ", content=" + content + ", viewed=" + viewed + ", vote_up="
				+ vote_up + ", vote_down=" + vote_down + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", user=" + user + "]";
	}

}
