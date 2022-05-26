package npscommunity.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity(name = "Role")
@Data
@Table(name = "role")
public class AppRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Id auto increment
	private Long id;

	@Column
	private String name;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
	@JsonIgnore
	private List<AppUser> users;
}
