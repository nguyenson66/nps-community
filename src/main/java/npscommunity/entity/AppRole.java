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

import lombok.Data;

@Entity(name="role")
@Data
@Table(name = "role")
public class AppRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Id auto increment
    private Long id;
     
	@Column
	private String name;
	
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private List<AppUser> users;
}
