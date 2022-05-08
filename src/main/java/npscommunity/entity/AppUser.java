package npscommunity.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
	
	@Column
	private String avatar;
	
	@Column
	private String name;
	
	@Column
	private String address;
	
	@Column
	private Timestamp birthday;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="user_roles", joinColumns = @JoinColumn())
	private List<AppRole> roles;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
}
