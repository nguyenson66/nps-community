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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import npscommunity.dto.ManagerUser;

@Data
@Entity(name = "User")
@Table(name = "user")

@NamedNativeQuery(name = "get_manager_user", query = "select u.id as id, u.name as name, u.username as username, u.email as email, count_question.total_question as total_question, count(a.id) as total_answer, sum(a.vote_up - a.vote_down) as total_vote from"
		+ " (select count(q.id) as total_question, User.id as id from User "
		+ " left join Question q"
		+ " on User.id=q.user_id" + " group by User.id ) as count_question, "
		+ " User u"
		+ " left join Answer a on u.id = a.user_id"
		+ " where count_question.id = u.id"
		+ " group by u.id order by total_answer desc limit 10", resultSetMapping = "manager_user_dto")

@NamedNativeQuery(name = "find_user_by_username_email", query = "select u.id as id, u.name as name, u.username as username, u.email as email, count_question.total_question as total_question, count(a.id) as total_answer, sum(a.vote_up - a.vote_down) as total_vote from"
		+ " (select count(q.id) as total_question, User.id as id from User "
		+ " left join Question q"
		+ " on User.id=q.user_id"
		+ " group by User.id ) as count_question, "
		+ " User u"
		+ " left join Answer a on u.id = a.user_id"
		+ " where count_question.id = u.id"
		+ " and (u.name = ?1 or u.username = ?1 or u.email = ?1)"
		+ " group by u.id", resultSetMapping = "manager_user_dto")

@SqlResultSetMapping(name = "manager_user_dto", classes = @ConstructorResult(targetClass = ManagerUser.class, columns = {
		@ColumnResult(name = "id", type = Long.class),
		@ColumnResult(name = "name", type = String.class), @ColumnResult(name = "username", type = String.class),
		@ColumnResult(name = "email", type = String.class), @ColumnResult(name = "total_question", type = Long.class),
		@ColumnResult(name = "total_answer", type = Long.class),
		@ColumnResult(name = "total_vote", type = Long.class) }))

public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Id auto increment
	private long id;

	@Column(nullable = false)
	@NotEmpty(message = "Field can't be empty!")
	private String username;

	@Column(nullable = false)
	@NotEmpty(message = "Field can't be empty!")
	@JsonIgnore
	private String password;

	@Column
	private String email;

	@Column
	private String avatar = "https://bit.ly/3McoJN1";

	@Column
	private String name;

	@Column
	private String address;

	@Column
	private Timestamp birthday;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
	@JsonIgnore
	private List<AppRole> roles;

	// @OneToMany(fetch = FetchType.EAGER)
	// private List<Question> questions;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	private List<Answer> answers;

	@Column(name = "created_at")
	@CreationTimestamp
	private Timestamp createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private Timestamp updatedAt;

	public String toString() {
		return this.id + " " + this.name + " " + this.username + " " + this.address;
	}

	public Long getId() {
		return id;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	private List<Question> questions;

}
