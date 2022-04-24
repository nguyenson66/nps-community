package npscommunity.admin.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("admins")
public class Admin {
	@Id
	private String id;
	
	private String username;
	private String password;
	private String name;	
	
	public Admin(String username, String password, String name) {
		super();
		this.username = username;
		this.name = name;
		this.password = password;
	}

}
