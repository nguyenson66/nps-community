package npscommunity.admin.Entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
public class Admin {
	@Id
	private String id;
	
	@NotBlank
	private String username;
	@NotBlank
	@Size(max = 100)
	private String password;
	@NotBlank
	@Size(max = 100, min = 5)
	private String name;	
	
}
