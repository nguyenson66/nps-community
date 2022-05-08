package npscommunity.entity;


import java.sql.Timestamp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.ToString;

@Data
@Entity(name = "user")
@Table(name = "user")
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Id auto increment
	private long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column
	private String email;

	@Column(columnDefinition = "varchar(255) default 'https://bit.ly/3McoJN1'")
	private String avatar;

	@Column
	private String name;

	@Column
	private String address;

	@Column
	private Timestamp birthday;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
	private List<AppRole> roles;

	@Column(name = "created_at")
	@CreationTimestamp
	private Timestamp createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private Timestamp updatedAt;
	
}
