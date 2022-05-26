package npscommunity.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import npscommunity.dto.ManagerQuestionDto;

@Entity
@Data
@Table(name = "question")

@NamedNativeQuery(name = "get_list_question", query = "select q.id as id, q.title as title, q.content as content , u.name as name, count(a.id) as total_answer, q.viewed as view from Question q"
		+ " inner join user u on u.id = q.user_id"
		+ " left join answer a on q.id = a.question_id"
		+ " group by q.id order by q.viewed desc", resultSetMapping = "manager_question_dto")

@NamedNativeQuery(name = "get_list_question_by_key", query = "select q.id as id, q.title as title, q.content as content , u.name as name, count(a.id) as total_answer, q.viewed as view from Question q"
		+ " inner join user u on u.id = q.user_id"
		+ " left join answer a on q.id = a.question_id"
		+ " where q.title LIKE ?1 or q.content LIKE ?1 or u.name LIKE ?1 or u.username LIKE ?1"
		+ " group by q.id order by q.viewed desc", resultSetMapping = "manager_question_dto")

@NamedNativeQuery(name = "get_list_question_by_username", query = "select q.id as id, q.title as title, q.content as content , u.name as name, count(a.id) as total_answer, q.viewed as view from Question q"
		+ " inner join user u on u.id = q.user_id"
		+ " left join answer a on q.id = a.question_id"
		+ " where u.id = ?1"
		+ " group by q.id order by q.viewed desc", resultSetMapping = "manager_question_dto")

@SqlResultSetMapping(name = "manager_question_dto", classes = @ConstructorResult(targetClass = ManagerQuestionDto.class, columns = {
		@ColumnResult(name = "id", type = Long.class),
		@ColumnResult(name = "title", type = String.class),
		@ColumnResult(name = "content", type = String.class),
		@ColumnResult(name = "name", type = String.class),
		@ColumnResult(name = "total_answer", type = Long.class),
		@ColumnResult(name = "view", type = Long.class)
}))

public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Id auto increment
	private Long id;

	@Column
	private String title;

	@Column
	private String content;

	@Column
	private Long viewed = (long) 0;

	@Column()
	private Long vote_up = (long) 0;

	@Column
	private Long vote_down = (long) 0;

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

	public String getTitle() {
		return title;
	}

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
