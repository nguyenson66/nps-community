package npscommunity.model;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	private long id;
	
	private String username;
	private String password;
	private String email;
	private String name;
    private String address;
    private Date birthday;
    
   
	public User() {
		super();
	}


	public User(String username, String password, String email) {
		super();
		this.id = 0;
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = "";
		this.address = "";
		this.birthday = null;
	}


	public User(long id, String username, String password, String email, String name, String address,
			Date birthday) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.address = address;
		this.birthday = birthday;
	}
	
}
